# Prueba técnica Mercado Libre

Crear un programa con un método o función con la siguiente firma

**boolean isMutant(String[] dna);**

En donde recibirás como parámetro un array de Strings que representa cada fila de una tabla
de (NxN) con las secuencias de ADN. Las letras de los Strings solo pueden ser: (A, T, C, G), las
cuales representa cada base nitrogenada del ADN.

Sabrás si un humano es mutante, si encuentras **más de una secuencia de cuatro letras iguales,**
de forma oblicua, horizontal o vertical.

## Desafíos
#### Nivel 1:
Programa (en cualquier lenguaje de programación) que cumpla con el método pedido por
Magneto.

#### Nivel 2:
Crear una API REST, hostear esa API en un cloud computing libre (Google App Engine,
Amazon AWS, etc), crear el servicio “/mutant/” en donde se pueda detectar si un humano es
mutante enviando la secuencia de ADN mediante un HTTP POST con un Json el cual tenga el
siguiente formato:
POST → /mutant/
{
“dna”:["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
}
En caso de verificar un mutante, debería devolver un HTTP 200-OK, en caso contrario un
403-Forbidden

#### Nivel 3

Anexar una base de datos, la cual guarde los ADN’s verificados con la API.
Solo 1 registro por ADN.
Exponer un servicio extra “/stats” que devuelva un Json con las estadísticas de las
verificaciones de ADN: {“count_mutant_dna”:40, “count_human_dna”:100: “ratio”:0.4}
Tener en cuenta que la API puede recibir fluctuaciones agresivas de tráfico (Entre 100 y 1
millón de peticiones por segundo).

# Tecnologías
- Spring Boot
- MongoDB

# Cloud
- Amazon web services

### URL del api

http://meliapp-env.eba-rjstwpj7.us-east-2.elasticbeanstalk.com:8080/

#### Url para el api mutant

http://meliapp-env.eba-rjstwpj7.us-east-2.elasticbeanstalk.com:8080/mutant

#### Url para el api stats

http://meliapp-env.eba-rjstwpj7.us-east-2.elasticbeanstalk.com:8080/stats

# Instrucciones de ejecucución

Para ejecutar el api mutant

http://meliapp-env.eba-rjstwpj7.us-east-2.elasticbeanstalk.com:8080/mutant

POST → /mutant/
{
“dna”:["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
}

Para ejecutar el api stats

GET → /stats/





