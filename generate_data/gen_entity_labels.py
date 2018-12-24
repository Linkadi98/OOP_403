from pymongo import MongoClient

client = MongoClient('mongodb://localhost:27017/')
db = client['wikidata']
wikicrawl = db['wikicrawl']

entities = wikicrawl.find({"crawl_root": True}, {"claims": 0})

with open("entity_labels.txt", 'w') as f:
    for entity in entities:
        _id = entity['_id']
        labels = entity['labels']
        if labels.get('vi') is not None:
            f.write('{}\t{}\n'.format(labels['vi']['value'], _id))
        if labels.get('en') is not None:
            f.write('{}\t{}\n'.format(labels['en']['value'], _id))
        aliases = entity['aliases']
        if aliases is not None:
            if isinstance(aliases.get('vi'), list):
                for vi_alias in aliases.get('vi'):
                    f.write('{}\t{}\n'.format(vi_alias['value'], _id))
            if isinstance(aliases.get('en'), list):
                for vi_alias in aliases.get('en'):
                    f.write('{}\t{}\n'.format(vi_alias['value'], _id))
