from flask import Blueprint, jsonify
import requests

# Crear un Blueprint de Flask para errores
error_bp = Blueprint('error_bp', __name__)

# Simular error de apertura de archivo
@error_bp.route('/api/simulate/file_error', methods=['GET'])
def simulate_file_error():
    try:
        with open('archivo_que_no_existe.txt', 'r') as f:
            contenido = f.read()
        return jsonify({"contenido": contenido})
    except FileNotFoundError:
        return jsonify({"error": "Error: Archivo no encontrado."}), 500

# Simular error de conexión a base de datos
@error_bp.route('/api/simulate/db_error', methods=['GET'])
def simulate_db_error():
    return jsonify({"error": "Error: No se pudo conectar a la base de datos."}), 500

# Simular error al llamar a una API externa (por ejemplo PokéAPI caída)
@error_bp.route('/api/simulate/api_error', methods=['GET'])
def simulate_api_error():
    try:
        # Llamada a una URL que no existe para forzar error
        response = requests.get('https://pokeapi.co/api/v2/nonexistent')
        response.raise_for_status()
        return jsonify(response.json())
    except requests.exceptions.RequestException:
        return jsonify({"error": "Error: No se pudo contactar con el servicio externo."}), 503

# Simular error de solicitud malformada
@error_bp.route('/api/simulate/bad_request', methods=['GET'])
def simulate_bad_request():
    return jsonify({"error": "Error: Solicitud incorrecta."}), 400
