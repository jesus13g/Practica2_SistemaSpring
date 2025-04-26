import os

class Config:
    SQLALCHEMY_DATABASE_URI = 'mysql+pymysql://root:root1234@localhost/db_flaskapi'
    SQLALCHEMY_TRACK_MODIFICATIONS = False
    SECRET_KEY = os.getenv("SECRET_KEY", "mi_clave_secreta")
