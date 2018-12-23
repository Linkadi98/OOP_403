import time
import json
from pymongo import MongoClient

client = MongoClient('mongodb://localhost:27017/')
db = client['wikidata']
wikicrawl = db['wikicrawl']

def get_label(id):
    doc = wikicrawl.find_one({'_id': id}, {'labels': 1})
    return doc.get('labels') if doc is not None else None

while True:
    query = input('>>>')
    try:
        query = json.loads(query)
    except:
        print("Query error")
        continue
    doc = wikicrawl.find_one(query)
    if doc is None:
        print('Not found')
    try:
        claims = doc['claims']
        for claim in claims:
            vals = claims[claim]
            for val in vals:
                main_val = val['mainsnak']
                main_val['label'] = get_label(main_val['property'])
                datavalue = main_val['datavalue']
                if datavalue["type"] == "wikibase-entityid":
                    datavalue["value"]['label'] = get_label(datavalue['value']['id'])
    except:
        pass
    print(json.dumps(doc, indent=4, ensure_ascii=False))
