import traceback
import json
import requests
from pymongo import MongoClient

client = MongoClient('mongodb://localhost:27017/')
db = client['wikidata']
wikicrawl = db['wikicrawl']
crawled_id = set()

def get_list_item_id():
    vietnamese_query = '''SELECT ?items
        WHERE 
        {
            ?items wdt:P31 wd:Q5;
                   wdt:P27 wd:Q881
        }
        '''
    scientist_nobel_query = '''SELECT ?items
        WHERE
        {
            ?items wdt:P166/wdt:P31* wd:Q7191;
                   wdt:P31 wd:Q5.
        }
        '''
    scientist_turing_query = '''SELECT ?items
        WHERE
        {
            ?items wdt:P166/wdt:P31* wd:Q185667;
                    wdt:P31 wd:Q5.
        }
        '''
    president_us_query = '''SELECT ?items
        WHERE
        {
            ?items wdt:P39 wd:Q11696;
                    wdt:P31 wd:Q5.
        }
        '''
    enterprise_us_query = '''SELECT ?items
        WHERE
        {
            ?items wdt:P31 wd:Q6881511;
                     wdt:P452 wd:Q880371.
        }
        '''
    vn_university_query = '''SELECT ?items
        WHERE
        {
            ?items wdt:P31 wd:Q3918;
                    wdt:P17 wd:Q881.
        }
        '''
    us_university_query = '''SELECT ?items
        WHERE
        {
            ?items wdt:P31 wd:Q3918;
                        wdt:P17 wd:Q30.
        }
        '''
    vn_district_query = '''SELECT ?items
        WHERE
        {
            ?items wdt:P31 wd:Q2582669;
                      wdt:P17 wd:Q881.
        }
        '''
    vn_province_query = '''SELECT ?items
        WHERE
        {
            ?items wdt:P31 wd:Q2824648;
                   wdt:P17 wd:Q881.
        }
        '''
    country_query = '''SELECT ?items
        WHERE
        {
            ?items wdt:P31 wd:Q6256.
        }
        '''
    querys = [
        vietnamese_query,
        scientist_nobel_query,
        scientist_turing_query,
        president_us_query,
        enterprise_us_query,
        vn_university_query,
        us_university_query,
        vn_district_query,
        vn_province_query,
        country_query
    ]
    result = set()
    for query in querys:
        params = {'format': 'json', 'query': query}
        r = requests.get('https://query.wikidata.org/sparql', params=params)
        data = r.json()
        for item in data['results']['bindings']:
            value_uri = item['items']['value']
            value_id = value_uri.split('/')[-1]
            result.add(value_id)
    return result

def get_property_by_id(property_id):
    if property_id in crawled_id:
        return
    crawled_id.add(property_id)
    try:
        prop_crawled = wikicrawl.find_one({"_id": property_id})
        if prop_crawled is not None:
            return
        data_url = 'http://www.wikidata.org/wiki/Special:EntityData/{}.json'.format(property_id)
        r = requests.get(data_url)
        document = r.json()
        document = document['entities'][property_id]
        normalized_document = {}
        normalized_document['_id'] = document['id']
        labels_normalized = None
        if isinstance(document.get('labels', None), dict):
            labels_normalized = {k: document['labels'].get(k, None) for k in ['vi', 'en']}
        descriptions_normalized = None
        if isinstance(document.get('descriptions', None), dict):
            descriptions_normalized = {k: document['descriptions'].get(k, None) for k in ['vi', 'en']}
        aliases_normalized = None
        if isinstance(document.get('aliases', None), dict):
            aliases_normalized = {k: document['aliases'].get(k, None) for k in ['vi', 'en']}
        normalized_document['labels'] = labels_normalized
        normalized_document['descriptions'] = descriptions_normalized
        normalized_document['aliases'] = aliases_normalized
        wikicrawl.insert_one(normalized_document)
    except Exception as e:
        traceback.print_tb(e.__traceback__)
        print(e)
        print('Error when get property id:', property_id)

def get_item_by_id(item_id, depth=0):
    if depth > 1:
        return
    if item_id in crawled_id and depth > 0:
        return
    crawled_id.add(item_id)
    try:
        item_crawled = wikicrawl.find_one({"_id": item_id})
        if item_crawled is not None and item_crawled.get('crawl_root') == True:
            return
        data_url = 'http://www.wikidata.org/wiki/Special:EntityData/{}.json'.format(item_id)
        r = requests.get(data_url)
        document = r.json()
        # Only get data in Vietnamese and English
        def extract_snak(snak):
            if 'datavalue' in snak:
                data_value = snak['datavalue']
                if data_value['type'] == 'wikibase-entityid':
                    get_item_by_id(data_value['value']['id'], depth+1)
            return snak
        document = document['entities'][item_id]
        claims_normalized = {}
        if isinstance(document.get('claims', None), dict):
            for key, val_list in document['claims'].items():
                get_property_by_id(key)
                claims_normalized[key] = []
                for val in val_list:
                    val_normalized = {}
                    main_snak = extract_snak(val['mainsnak'])
                    val_normalized['mainsnak'] = main_snak
                    if 'qualifiers' in val:
                        qualifiers = {}
                        for prop_qualifier, qualifier in val['qualifiers'].items():
                            get_property_by_id(prop_qualifier)
                            qualifiers[prop_qualifier] = [extract_snak(snak) for snak in qualifier]
                        val_normalized['qualifiers'] = qualifiers
                    claims_normalized[key].append(val_normalized)
        if item_crawled is not None:
            if item_crawled.get('crawl_root') != True and depth == 0:
                wikicrawl.update_one({'_id': item_crawled['_id']}, {'$set': {'crawl_root': True}})
            return
        normalized_document = {}
        normalized_document['_id'] = document['id']
        labels_normalized = None
        if isinstance(document.get('labels', None), dict):
            labels_normalized = {k: document['labels'].get(k, None) for k in ['vi', 'en']}
        descriptions_normalized = None
        if isinstance(document.get('descriptions', None), dict):
            descriptions_normalized = {k: document['descriptions'].get(k, None) for k in ['vi', 'en']}
        aliases_normalized = None
        if isinstance(document.get('aliases', None), dict):
            aliases_normalized = {k: document['aliases'].get(k, None) for k in ['vi', 'en']}
        sitelinks_normalized = None
        if isinstance(document.get('sitelinks', None), dict):
            sitelinks_normalized = {k: document['sitelinks'][k] for k in document['sitelinks'].keys() if 'vi' in k}
        normalized_document['labels'] = labels_normalized
        normalized_document['descriptions'] = descriptions_normalized
        normalized_document['aliases'] = aliases_normalized
        normalized_document['claims'] = claims_normalized
        normalized_document['sitelinks'] = sitelinks_normalized
        if depth == 0:
            normalized_document['crawl_root'] = True
        else:
            normalized_document['crawl_root'] = False
        wikicrawl.insert_one(normalized_document)
    except Exception as e:
        traceback.print_tb(e.__traceback__)
        print(e)
        print('Error when get item id:', item_id)

if __name__ == '__main__':
    item_ids = get_list_item_id()
    num_id = len(item_ids)
    i = 0
    for item_id in item_ids:
        i += 1
        print('Crawling {}/{}, id:{}'.format(i, num_id, item_id))
        get_item_by_id(item_id)

    # get_item_by_id('Q254')
    # get_property_by_id('P27')