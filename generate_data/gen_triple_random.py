import os
import json
import random
import time
import argparse
from typing import IO

def get_people_name(filepath=None):
    if filepath is None:
        curr_dir =os.path.dirname(os.path.abspath(__file__))
        filepath = os.path.join(curr_dir, 'data', 'uit_member.json')
    name_list = None
    with open(filepath) as f:
        name_list = json.load(f)
    return [person['full_name'] for person in name_list]

def gen_random_people(nb_of_people: int, output: IO):
    name_list = get_people_name()
    places = [
        'Q64',
        'Q36693',
        'Q1858',
        'Q1781',
        'Q36587',
        'Q64'
    ]
    genders = ['Q6581097', 'Q6581072']
    for _ in range(nb_of_people):
        rand_id = 'Q' + str(random.randint(10000000, 99999999))
        name = random.choice(name_list)
        output.write('{}\t{}\t{}\n'.format(rand_id, 'labels_vi', name))
        output.write('{}\t{}\t{}\n'.format(rand_id, 'labels_en', name))
        output.write('{}\t{}\t{}\n'.format(rand_id, 'descriptions_vi', 'người sinh ra ngẫu nhiên'))
        output.write('{}\t{}\t{}\n'.format(rand_id, 'descriptions_en', 'random person'))
        # gender
        output.write('{}\t{}\t{}\n'.format(rand_id, 'P21', random.choice(genders)))
        # citizen of vietnam
        output.write('{}\t{}\t{}\n'.format(rand_id, 'P27', 'Q881'))
        # date of birth
        max_time = 1000000000
        birthday = time.gmtime(random.randint(0, max_time))
        birthday_formated = time.strftime('+%Y-%m-%dT00:00:00Z', birthday)
        output.write('{}\t{}\t{}\n'.format(rand_id, 'P569', birthday_formated))
        # place of birth
        output.write('{}\t{}\t{}\n'.format(rand_id, 'P19', random.choice(places)))

if __name__ == '__main__':
    parser = argparse.ArgumentParser(description='Generate entities')
    parser.add_argument('--nb_of_entities', help='number of entities generate')
    parser.add_argument('--output', help='output file')
    args = vars(parser.parse_args())
    curr_dir = os.path.dirname(os.path.abspath(__file__))
    filepath = args['output'] or os.path.join(curr_dir, 'data', 'demo.txt')
    nb_of_entities = int(args['nb_of_entities']) if args['nb_of_entities'] is not None else 10000
    out_f = open(filepath, 'w')
    entities_f = open(os.path.join(curr_dir, 'data', 'triples.txt'))
    line = None
    while True:
        line = entities_f.readline()
        if line.startswith('P'):
            out_f.write(line)
        else:
            break
    dependent_entities = set()
    current_entities = set()
    curr_subj = line.strip().split('\t')[0]
    nb_of_props = 2622
    for i in range((nb_of_entities - nb_of_props)):
        while True:
            if len(line) == 0:
                break
            subj, pred, obj = line.strip().split('\t')
            out_f.write(line)
            line = entities_f.readline()
            current_entities.add(subj)
            dependent_entities.discard(subj)
            if obj not in current_entities and obj.startswith('Q'):
                dependent_entities.add(obj)
            if curr_subj != subj:
                curr_dir = subj
                break
            if subj in dependent_entities:
                dependent_entities.remove(subj)
    for line in entities_f:
        if '\t' in line:
            subj, _, _ = line.strip().split('\t')
            if subj in dependent_entities:
                out_f.write(line)
    nb_of_entities_wiki = 100000
    if nb_of_entities > nb_of_entities_wiki:
        gen_random_people(nb_of_entities - nb_of_entities_wiki, out_f)
    out_f.close()
    entities_f.close()