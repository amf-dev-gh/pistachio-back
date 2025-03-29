# Version de java utilizada en el proyecto
FROM openjdk:21-jdk-slim 

# Esto corresponde a la <version> y al <artifactId> (hacerlo coincidir con <name>) del pom.xml
ARG JAR_FILE=target/pistachio-rest-0.0.1.jar

# Esto es el nombre con el que se va a crear la imagen de la app en DOCKER
COPY ${JAR_FILE} api-rest-pistachio.jar

# Puerto en la que se va a exponer
EXPOSE 8080

# Como lo va a ejecutar es decir lenguaje, comando, nombre de aplicacion a ejecutar
ENTRYPOINT [ "java", "-jar", "api-rest-pistachio.jar" ]