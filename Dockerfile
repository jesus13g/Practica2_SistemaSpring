# Imagen base Ubuntu
FROM ubuntu:20.04

ENV DEBIAN_FRONTEND=noninteractive

RUN apt-get update && apt-get install -y \
    python3.12 \
    python3-pip \
    openjdk-8-jdk \
    maven \
    mysql-server \
    netcat \
    && apt-get clean

WORKDIR /app

# Copiamos todo el proyecto
COPY . .

# Instalamos dependencias de Python
RUN pip3 install --no-cache-dir -r src/main/java/es/ubu/lsi/FlaskAPI/requirements.txt

# Construimos la app Spring Boot
RUN mvn clean package -DskipTests

# Copiamos y renombramos el jar generado a un nombre estable
RUN cp target/*.jar app.jar

# Copiamos el script de arranque
COPY start.sh /start.sh
RUN chmod +x /start.sh

# Exponer puertos
EXPOSE 8080 5000 3306

# Comando de arranque
CMD ["/start.sh"]
