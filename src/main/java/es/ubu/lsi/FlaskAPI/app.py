from flask import Flask
from config import Config
from models.database import db
from dotenv import load_dotenv
from routes.user_routes import user_bp
from routes.pokemon_routes import pokemon_bp
from routes.error_routes import error_bp

load_dotenv()

def create_app():
    app = Flask(__name__)
    app.config.from_object(Config)

    db.init_app(app)
    app.register_blueprint(user_bp)
    app.register_blueprint(pokemon_bp)
    app.register_blueprint(error_bp)

    with app.app_context():
        db.create_all()

    return app

app = create_app()

if __name__ == "__main__":
    app.run(debug=True)
