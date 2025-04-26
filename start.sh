#!/bin/bash
# ---- start.sh ----

echo "Iniciando MySQL..."
service mysql start

echo "Esperando a que MySQL acepte conexiones..."
until mysqladmin ping --silent; do
  sleep 1
done

echo "Configurando contraseña de root en MySQL..."
mysql -u root -e "ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'root1234'; FLUSH PRIVILEGES;"

echo "Creando base de datos si no existe..."
mysql -u root -proot1234 -e "CREATE DATABASE IF NOT EXISTS db_flaskapi;"

echo "Iniciando Flask API..."
cd /app/src/main/java/es/ubu/lsi/FlaskAPI
python3 app.py &
FLASK_PID=$!

sleep 5

echo "Iniciando Spring Boot App..."
cd /app
java -jar app.jar

kill $FLASK_PID
