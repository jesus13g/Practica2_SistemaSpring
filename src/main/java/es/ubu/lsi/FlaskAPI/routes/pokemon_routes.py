from flask import Blueprint, jsonify
import requests

pokemon_bp = Blueprint('pokemon_bp', __name__)

@pokemon_bp.route('/api/pokemon/<name>', methods=['GET'])
def get_pokemon(name):
    try:
        response = requests.get(f'https://pokeapi.co/api/v2/pokemon/{name.lower()}')
        response.raise_for_status()
        data = response.json()
        pokemon_info = {
            'name': data['name'],
            'id': data['id'],
            'height': data['height'],
            'weight': data['weight'],
            'types': [t['type']['name'] for t in data['types']],
            'abilities': [a['ability']['name'] for a in data['abilities']],
            'sprites': data['sprites']['front_default']
        }
        return jsonify(pokemon_info)
    except requests.exceptions.HTTPError as http_err:
        return jsonify({'error': f'HTTP error occurred: {http_err}'}), 400
    except Exception as err:
        return jsonify({'error': f'An error occurred: {err}'}), 500
