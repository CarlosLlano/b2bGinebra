# DESPLIEGUE DE LA APLICACION CON DOCKER #


***Docker en Linux/macOS***

*Comandos*

```bash
docker-compose up -d
```

*Acceso*

Se puede acceder a la aplicacion con la ruta http://localhost:8080/b2bGinebra/inicio.xhtml

___

***Docker Toolbox en Windows***

*Comandos*
```bash
docker-compose up -d
```

*Acceso*

Se puede acceder a la aplicacion con la ruta http://192.168.99.100:8080/b2bGinebra/inicio.xhtml

___

***Problemas***

ERROR: Throwable while attempting to get a new connection: null

![error](https://user-images.githubusercontent.com/17281733/34595845-16e8e76c-f1a9-11e7-9e5b-3f9b3c68abc4.jpeg)


SOLUCION: Ejecutar servicios en orden

```bash
docker-compose up -d postgresdb
docker-compose up -d wildfly
```
