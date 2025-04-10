from flask import jsonify

def handle_error(e, code=500):
    return jsonify({
        "error": str(e),
        "message": "Ha ocurrido un error. Intenta más tarde."
    }), code
