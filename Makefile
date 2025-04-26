.PHONY: all build run check-jar help

all: build

build:
	mvn clean package
	docker build -t practica2_sistemaspring .

run: check-jar
	docker run -p 8080:8080 -p 5000:5000 -p 3308:3306 practica2_sistemaspring

check-jar:
	if not exist target\*.jar ( \
		echo No se encontró el JAR, construyendo... && \
		mvn clean package \
	) else ( \
		echo JAR encontrado, continuando... \
	)

help:
	@echo "Comandos disponibles:"
	@echo "  make all   -> Construir la imagen Docker (SpringBoot + Flask + MySQL)"
	@echo "  make run   -> Ejecutar el contenedor con puertos expuestos"
	@echo "  make help  -> Mostrar esta ayuda"
