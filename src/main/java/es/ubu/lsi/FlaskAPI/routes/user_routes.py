from flask import Blueprint, request, jsonify
from models.tables import User
from models.database import db
import requests
from utils.exception import handle_error


user_bp = Blueprint('user_bp', __name__, url_prefix="/api")

@user_bp.route('/register', methods=['POST'])
def register_user():
    data = request.get_json()
    username = data.get("username")
    password = data.get("password")

    if User.query.filter_by(username=username).first():
        return jsonify({"error": "Usuario ya existe"}), 400

    new_user = User(username=username, password=password)
    db.session.add(new_user)
    db.session.commit()

    return jsonify({"message": "Usuario registrado correctamente"}), 201


@user_bp.route('/login', methods=['POST'])
def login_user():
    data = request.get_json()
    username = data.get("username")
    password = data.get("password")

    user = User.query.filter_by(username=username, password=password).first()

    if not user:
        return jsonify({"error": "Credenciales incorrectas"}), 401

    return jsonify({
        "message": "Login exitoso",
        "user": user.to_dict()
    }), 200


@user_bp.route('/simulate/file-error', methods=['GET'])
def simulate_file_error():
    try:
        with open("archivo_que_no_existe.txt", "r") as f:
            f.read()
    except Exception as e:
        return handle_error(e, code=500)


@user_bp.route('/simulate/db-error', methods=['GET'])
def simulate_db_error():
    try:
        # Consulta errónea a tabla que no existe
        db.session.execute("SELECT * FROM tabla_inexistente")
    except Exception as e:
        return handle_error(e, code=500)


@user_bp.route('/simulate/api-error', methods=['GET'])
def simulate_api_error():
    try:
        # Llamada a URL incorrecta
        response = requests.get("https://pokeapi.co/api/v2/pokemon/invalid_pokemon_name")
        response.raise_for_status()
        return jsonify(response.json())
    except Exception as e:
        return handle_error(e, code=502)
