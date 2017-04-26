# **GamingValley**

¡Bienvenidos a la nueva aplicación web de venta de videojuegos **GamingValley**! A pesar de que somos una pequeña compañia, esperamos hacernos un hueco y conseguir un renombre dentro de la industria de la venta de videojuegos.

## Vídeo sobre la página web
[![Alt text](https://img.youtube.com/vi/hTnXefp51OE/0.jpg)](https://www.youtube.com/watch?v=hTnXefp51OE)

## Descripción de nuestra aplicación web:

Nuestra aplicación web se basa en la **venta** de videojuegos y de conocer las **últimas noticias** sobre los videojuegos actuales, futuros y los más antiguos tambien.

- **Parte pública:** En este apartado, los usuarios que visiten nuestra aplicación y que aún no estén registrados en nuestras bases de datos, podrán acceder a la sección de noticias y de ventas, pero en ésta última, solo podrán ver el estado del videojuego (Disponibilidad, precio, imágenes...). 
- **Parte privada:** Una vez los usuarios se hayan registrado, pueden tener acceso a la compra de los videojuegos que se encuentran en nuestra aplicación.

## Entidades principales dentro de nuestra aplicación:

- **Usuario:** Registrado o no registrado dentro de nuestra aplicación, es el destinatario de nuestro objetivo de venta.
- **Videojuego:** Producto a vender dentro de nuestra aplicación. Dependiendo del stock del producto, se podrá vender o no.
- **Noticias:** Dentro de la sección de noticias, se tendrán diferentes secciones diferenciando la temática de ellas mismas.
- **Pedido:** Cada usuario puede realizar diferentes compras mediante un único pedido.
- **Valoración:** Satisfacción del usuario sobre el producto y comentarios añadidos.

## Servicios internos de la aplicación:

- **Notificación por correo:** Cuando se realiza una compra, se enviará un correo con la notificación de la compra.
- **Generación de documento PDF:** Se podrá generar un documento PDF a la hora de poder visualizar los pedidos.

## Repositorios con los servicios:

- **Notificación por correo:** https://github.com/migueljrd/EmailSender
- **Generación de documento PDF:** https://github.com/migueljrd/ServicioPDFSockets

## Integrantes de desarrollo:

- [**Mauro Spagnoli:**](https://github.com/MauroSpagnoli) m.spagnoli@alumnos.urjc.es
- [**Álvaro Hinojal:**](https://github.com/AHinojal) a.hinojal@alumnos.urjc.es
- [**Miguel Robledo:**](https://github.com/migueljrd) m.robledod@alumnos.urjc.es

## Diagrama UML de clases:
![alt text](https://github.com/MauroSpagnoli/GamingValley/blob/master/UMLFase3_GamingValley.png)

## Diagrama Entidad/Relacion:
![alt text](https://github.com/MauroSpagnoli/GamingValley/blob/master/ermodel.PNG) 

## Explicación de las vistas
- **Vista Principal**: Desde esta pagina se puede logearse en la pagina, acceder a las noticias, ver la tienda y ver el pedido actual. Hay dos tipos de vistas, la vista cuando el usuario no está logeado y cuando el usuario está logeado.

_Vista con usuario no logeado_
![alt text](https://github.com/MauroSpagnoli/GamingValley/blob/master/Vistas/inicioPublica.PNG)
_Vista con usuario logeado_
![alt text](https://github.com/MauroSpagnoli/GamingValley/blob/master/Vistas/inicioPrivada.PNG)

- **Vista Noticia**: Aqui se pueden ver las noticias o agregar una nueva.
![alt text](https://github.com/MauroSpagnoli/GamingValley/blob/master/Vistas/noticias.PNG)

- **Vista Tienda**: Aqui se pueden ver todos los videojuegos que hay en la tienda, agregar un nuevo videojuego y volver a la pagina principal.
![alt text](https://github.com/MauroSpagnoli/GamingValley/blob/master/Vistas/videojuegos.PNG)

- **Vista Login**: Desde aqui se puede logearse en la pagina o si no se tiene cuenta poder registrarse.
![alt text](https://github.com/MauroSpagnoli/GamingValley/blob/master/Vistas/form_login.PNG)

- **Vista Pedido**: Aqui sale el pedido actual, ademas se puede guardar el pedido actual y volver a la pagina principal.
![alt text](https://github.com/MauroSpagnoli/GamingValley/blob/master/Vistas/pedidos.PNG)

## Instrucciones para ejecutar la aplicacion en Azure
- Generamos un certificado pem para poder acceder a la máquina mediante la siguiente instruccion:
**openssl req -x509 -nodes -days 365 -newkey rsa:2048 -keyout azureus.key -out azureus-cert.pem**
- Y protegemos la clave:
**chmod 0600 azureus.key**
- Creamos una máquina virtual en Azure, accedemos a ella con un mediante ssh usando la clave privada:
**ssh -i azure.key azureuser@ipdelamaquina**
- Luego instalamos java y mysql.
- Generamos el jar en nuestra aplicación con la opción mvn build... y en goals introducimos clean package.
- En carpeta target de nuestro proyecto,se ha creado el jar, y lo subimos mediante el comando scp -i:
**scp -i /path/to/azure.key MVC-o.o.1-SNAPSHOT.jar azureuser@ip:/home/azureuser/**
- Antes de ejecutar la aplicacion hay que abrir el puerto 443.
- Accedemos a la máquina virtual donde hemos subido el jar y ejecutamos la aplicacion:
**java -jar MVC-o.o.1-SNAPSHOT.jar**
- Con el mismo proceso de antes ejecutamos los servicios internos.

## Diagrama de la infraestructura en Azure
![alt text](https://github.com/MauroSpagnoli/GamingValley/blob/master/infraestructuraAzure.png)

## Documentación interfaz servicio interno
- En nuestra aplicación existe una clase llamada "Conexión" en la cual se crea una instancia de un REST Template, por el cual realiza un POST hacia la URL _http://ipPrivadaMV:8083/crearPdf_ por el cual se envia la direccón email del usuario que hace el pedido y un mensaje.
- En nuestra API REST ejecutada en la misma maquina se encarga de escuchar del puerto 8083 las peticiones que se realice. Se trata un metodo POST en el cual se envia hacia la dirección de correo del usuario.

## Organigrama de navegación:
![alt text](https://github.com/MauroSpagnoli/GamingValley/blob/master/organigrama.png)
