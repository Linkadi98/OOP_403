import json
with open('./entities.json') as f:
    with open('./triple.txt', 'w') as output:
        for line in f:
            entity = json.loads(line.strip())
            labels = entity.get('labels', {}) or {}
            _id = entity['_id']
            if 'value' in (labels.get('vi', {}) or {}):
                output.write('{}\t{}\t{}\n'.format(_id, 'labels_vi', labels['vi']['value']))
            if 'value' in (labels.get('en', {}) or {}):
                output.write('{}\t{}\t{}\n'.format(_id, 'labels_en', labels['en']['value']))
            descriptions = entity.get('descriptions', {}) or {}
            if 'value' in (descriptions.get('vi', {}) or {}):
                output.write('{}\t{}\t{}\n'.format(_id, 'descriptions_vi', descriptions['vi']['value']))
            if 'value' in (descriptions.get('en', {}) or {}):
                output.write('{}\t{}\t{}\n'.format(_id, 'descriptions_en', descriptions['en']['value']))
            aliases = entity.get('aliases', {}) or {}
            aliases = aliases.get('vi', []) or []
            if len(aliases) != 0:
                for alias in aliases:
                    output.write('{}\t{}\t{}\n'.format(_id, 'alias', alias['value']))
            claims = entity.get('claims', None)
            if not isinstance(claims, dict):
                continue
            for k, v in claims.items():
                v = v[0]
                datatype = v['mainsnak']['datatype']
                if datatype == 'wikibase-item':
                    try:
                        output.write('{}\t{}\t{}\n'.format(_id, k, v['mainsnak']['datavalue']['value']['id']))
                    except:
                        continue
                elif datatype == 'time':
                    try:
                        output.write('{}\t{}\t{}\n'.format(_id, k, v['mainsnak']['datavalue']['value']['time']))
                    except:
                        continue
