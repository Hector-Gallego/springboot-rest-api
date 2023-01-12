# SpringBoot Api REST 

Se trata de una api REST donde pongo en practica los conocimientos que he ido adquiriendo en mi aprendizaje de Java y del framwork SrpingBoot.



## Caracteristicas

Esta aplicación cuenta con las siguientes caracteristicas

* CRUD database haciendo uso de JPA y MySQL
* Validación de usuario mediante JWT
* Gestión de roles de usuario
* Registro de usuario y confirmación mediante correo eléctronico
* Manejo de errores mediante excepciones personalizadas



## Tecnologías a Utilizar 


* JAVA 17
* Spring 6.0
* SpringBoot 3.0.1 
* Spring security
* Java Mail
* MySQL
* Thymeleaf
* Spring web
* Spring JPA
* Spring resource server



# Generación de las claves privada y publica para el cifrado del JWT


Un Jason Web Token es un metodo abierto para presentar reclamaciones de forma segura entre dos partes, consta de 3 partes: 

* Header: Consta de dos propiedades {"alg":"H256","typ":"JWT"}.alg es el algoritmo que se utiliza para el cifrado del JWT.

* Playload: Aqui es donde se almacenan los datos a enviar, estos datos se almacenan con el formato propiedad-valor JSON

* Signature: se crea cifrando, con el algoritmo especificado en el header: (i) el header codificado en base64Url, (ii) la Playload en base64Url y (iii) un secreto (o una clave privada)

por lo tanto debemos cifrar el JWT, existen dos formas de cifrarlo:

* Clave simetrica: la misma clave se utiliza tanto para el cifrado (cuando se crea el JWT) como para el descifrado (MobileTogether Server utiliza la clave para verificar el JWT). La clave simétrica, también conocida como secreto compartido, se almacena como una configuración en MobileTogether Server.

* Claves Asimetricas: se utilizan diferentes claves para el cifrado (clave privada) y el descifrado (clave pública). La clave pública se almacena como una configuración en MobileTogether Server para que se pueda verificar el JWT.


> Existen desventajas y ventajas pero el enfoque mas utilizado y recomendado es el de las claves asimetricas.

## Generación de las claves publicas y privadas

La generación de las claves publicas y privadas se puede realizar mediante codigo, pero lo ideal sera crearlo de forma manual y que se guarden de forma estatica. 

primero debemos crear un directorio en donde guardar las claves
> /src/main/resource/certs

como segundo paso debemos abrir una terminal gitBash en nuestro directorio **certs**
y ejecutar los siguientes comandos

```
# create rsa key pair
openssl genrsa -out keypair.pem 2048

# extract public key
openssl rsa -in keypair.pem -pubout -out public.pem

# create private key in PKCS#8 format
openssl pkcs8 -topk8 -inform PEM -outform PEM -nocrypt -in keypair.pem -out private.pem

```
esto deberia generar las claves y guardarlas en el directorio para su posterior uso.



