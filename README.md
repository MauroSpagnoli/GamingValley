# **GamingValley**

¡Bienvenidos a la nueva aplicación web de venta de videojuegos **GamingValley**! A pesar de que somos una pequeña compañia, esperamos hacernos un hueco y conseguir un renombre dentro de la industria de la venta de videojuegos.

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
- **Vista Principal**: Desde esta pagina se puede logearse en la pagina, acceder a las noticias, ver la tienda y ver el pedido actual.
![alt text](https://github.com/MauroSpagnoli/GamingValley/blob/master/Vistas/inicio.PNG)

- **Vista Noticia**: Aqui se pueden ver las noticias o agregar una nueva.
![alt text] (https://github.com/MauroSpagnoli/GamingValley/blob/master/Vistas/noticias.PNG)

- **Vista Tienda**: Aqui se pueden ver todos los videojuegos que hay en la tienda, agregar un nuevo videojuego y volver a la pagina principal.
![alt text] (https://github.com/MauroSpagnoli/GamingValley/blob/master/Vistas/videojuegos.PNG)

- **Vista Login**: Desde aqui se puede logearse en la pagina o si no se tiene cuenta poder registrarse.
![alt text] (https://github.com/MauroSpagnoli/GamingValley/blob/master/Vistas/form_login.PNG)

- **Vista Pedido**: Aqui sale el pedido actual, ademas se puede guardar el pedido actual y volver a la pagina principal.
![alt text](https://github.com/MauroSpagnoli/GamingValley/blob/master/Vistas/pedidos.PNG)

## Organigrama de navegación:
![alt text](https://github.com/MauroSpagnoli/GamingValley/blob/master/organigrama.png)
