FROM openjdk:11
ADD target/projetoECommerce-1.0.jar projetoECommerce.jar
EXPOSE 8089
ENTRYPOINT ["java", "-jar", "projetoECommerce.jar"]
