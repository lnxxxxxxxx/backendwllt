# 💻 BackendWallt

#### Used technology: Java,Springboot,SpringSecurity,Hibernate,MySQL,POSTMAN

## 📋 Requirements
- Environment 
  - [Springboot](https://spring.io/projects/spring-boot)
  - [JDK-17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
  - [SpringSecurity](https://spring.io/projects/spring-security)
  - [MySQL](https://www.mysql.com/)
  - [Postman](https://www.postman.com/product/rest-client/)




## 🔧 Usage


* **URL**: https://backendwllt-production.up.railway.app/
* **ENDPOINTS SIN AUTORIZACION**:
     - **POST** /register                 -          Registro del usuario - Crea una cuenta automaticamente con un cvu aleatoreo y un alias.
     - **GET** /login                    -          Logeo del usuario    - Devuelve el berear token
* **ENDPOINTS CON AUTORIZACION(JWT)**:
* **POST**:
  - /operaciones/deposito?monto=&cuentaDestino=         -     Depositar saldo a una cuenta, monto se le pasa un numero bigDecimal y cuentaDestino recibe un alias-cvu-id de cuenta
  - /operaciones/extraccion?monto=&cuentaOrigen=         -     Extraer saldo de una cuenta
  - /operaciones/transferir?monto=&cuentaOrigen=&cuentaDestino=          -      Transferir un monto, desde una cuenta de origen hasta la cuenta que recibira el dinero
  - /tarjeta/{id_cuenta}                            -      Crea la tarjeta de credito y se le asigna a una cuenta, se lo enviamos por body aqui un ejemplo.
  {
  "nombreCompleto": "Leandro Luna",
  "clave": "123",
  "fechaExpiracion": "12/25",
  "numeroTarjeta": "1784-5178-9512-3456"
}

  - /operaciones/realizar-extraccion?monto=            -             Realiza una extraccion del dinero de la cuenta con la que estas vinculado
  - /operaciones/realizar-deposito?monto=              -             Realiza el deposito a tu cuenta vinculada como usuario
  - /operaciones/realizar-transferencia?monto=&cuentaDestino=          -        Realiza una transaccion desde tu cuenta hacia otra cuenta de destino, atraves de cvu-id-alias de la misma

 
* **GET**:
  - /cuenta/menu                         -        Trae un menu para manipular los datos abstraidos mas importantes.
  - /cuenta/mihistorial                   -           Me muestra mi historial de transacciones
  - /cuenta/mistarjetas                   -         Me muestra las tarjetas vinculadas a mi cuenta
  - /cuenta/micuenta                      -            Me muestra los datos de mi cuenta
  - /cuenta/{id_cuenta}                    -           Obtiene los datos de una cuenta
  - /cuenta/historial/{id_cuenta}         -            Obtiene el historial de una cuenta
