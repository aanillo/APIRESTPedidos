# APIRESTPedidos
## API REST REPARTOS ONLINE

![imgPortada](https://github.com/user-attachments/assets/75db554c-947e-4d1a-8822-439d5bf5e1a9)


### Alfonso Jesús Anillo Romero		2º DAW

### Desarrollo Web en el Entorno del Servidor


## Idea de la API REST:

#### Idea:

La API REST va a consistir en gestionar un sistema de reparto de pedidos online. 
Los usuarios podrán hacer un seguimiento de los pedidos en curso, consultando sus datos, así como consultar el historial de los pedidos ya realizados.
La aplicación se implementará en el lado del servidor.
Las tablas que van a conformar la aplicación son Usuario, Producto y Pedido.




#### Tablas:

Usuario: se almacenarán los usuarios registrados en el sistema y que van a poder realizar los pedidos y las operaciones de consulta.

Producto: almacenará los datos de los productos existentes en la Base de Datos, que serán los que estarán a la venta.

Pedido: poseerá los datos referentes a los pedidos de productos realizados por los usuarios.



#### Campos y tipo de datos:

Tabla Usuario:

Identificador de usuario: dato numérico tipo Long. Se generará automáticamente al crear un usuario. No puede ser nulo.  

Username: cadena de texto. Nombre del usuario. No puede ser nulo ni tener menos de 4 caracteres.

Password: cadena de texto. Contraseña del usuario. No puede ser nulo y debe tener un carácter numérico y uno especial, al menos.

Email: cadena de texto. Correo del usuario. No puede ser nulo. Además, deberá contener “@” y acabar en “.es” o “.com”.

Teléfono: dato numérico de tipo Int. No puede ser nulo y debe contener 9 números.

Dirección: cadena de texto. Indica dónde reside el usuario. No puede ser nulo.

Roles: cadena de texto: Roles del usuario. Puede ser USER o ADMIN. No puede ser nulo.

Tabla Producto:

Identificador del producto: dato numérico tipo Long. Se generará automáticamente al crear un producto. No puede ser nulo.

Nombre: cadena de texto. Nombre del producto, que especificará verdaderamente el tipo de producto que es, como por ejemplo, “Camiseta”, “Juego de cubertería”, etc. No puede ser nulo.

Categoría: cadena de texto. Especifica la categoría del producto. Importante a la hora de realizar búsquedas. No puede ser nulo y no podrá contener una categoría que no existe.

Stock: dato numérico tipo Int. Indica la cantidad disponible del producto. No puede ser nulo ni menor que 0.

Precio: dato numérico tipo Double. Hace referencia al importe de una unidad del producto. No puede ser nulo ni menor que 0.

Descripción: cadena de texto. Describe el producto.

Tabla Pedido:

Identificador del pedido: dato numérico de tipo Long. Se generará automáticamente al crear un pedido. No puede ser nulo.

Destino: cadena de texto. Indica la dirección del pedido. No puede ser nulo.

Fecha Pedido: fecha tipo Date. Indica la fecha en la que se ha realizado la compra online. La fecha no puede ser nula y debe tener formato “YYYY-MM-DD”.

Fecha Llegada: fecha tipo Date. Indica la fecha en la que está estipulada la llegada al destino. La fecha no puede ser nula y debe tener formato “YYYY-MM-DD”.

Importe: dato numérico tipo Double. Indica el precio del total del pedido. No puede ser nulo.

Estado: booleano. Indica si el pedido está en curso o ya se ha realizado. No puede ser nulo.


#### Diagrama Entidad / Relación:
 
![imgDiagrama](https://github.com/user-attachments/assets/49db0d01-f1d7-48a0-be00-1486db7a65cf)


El diagrama representa una relación ternaria entre las tres tablas.
Un usuario puede realizar muchos pedidos y en cada pedido pueden ir incluidos muchos productos.
