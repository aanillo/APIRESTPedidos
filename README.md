# APIRESTPedidos
## API REST REPARTOS ONLINE

![imgPortada](https://github.com/user-attachments/assets/75db554c-947e-4d1a-8822-439d5bf5e1a9)


### Alfonso Jesús Anillo Romero		2º DAW

### Desarrollo Web en el Entorno del Servidor


## Nombre del proyecto:
API REST Pedidos


## Idea de la API REST:

La API REST va a consistir en gestionar un sistema de reparto de pedidos online. 
Los usuarios podrán hacer un seguimiento de los pedidos en curso, consultando sus datos, así como consultar el historial de los pedidos ya realizados.
La aplicación se implementará en el lado del servidor.
Las tablas que van a conformar la aplicación son Usuario, Producto y Pedido.


## Justificación del proyecto

He escogido el tema de un sistema basado en una gestión de pedidos online porque el comercio online está muy a la orden del día, superando incluso a las compras presenciales en determinadas fechas del año. Bajo mi punto de vista, la idea de un proyecto debe basarse en un tema con impacto en la actualidad.

Una página web que ofrezca un funcionamiento correcto del estado de los pedidos es esencial para las empresas que ofrecen este tipo de servicios. Considero muy importante que los clientes puedan tener acceso con facilidad tanto a los datos de sus pedidos en línea como al historial.



## Tablas:

**Usuario:** se almacenarán los usuarios registrados en el sistema y que van a poder realizar los pedidos y las operaciones de consulta.

**Producto:** almacenará los datos de los productos existentes en la Base de Datos, que serán los que estarán a la venta.

**Pedido:** poseerá los datos referentes a los pedidos de productos realizados por los usuarios.



#### Campos y tipo de datos:

**Tabla Usuario:**

- Identificador de usuario: dato numérico tipo Long. Se generará automáticamente al crear un usuario. No puede ser nulo.  

- Username: cadena de texto. Nombre del usuario. No puede ser nulo ni tener menos de 4 caracteres.

- Password: cadena de texto. Contraseña del usuario. No puede ser nulo y debe tener un carácter numérico y uno especial, al menos.

- Email: cadena de texto. Correo del usuario. No puede ser nulo. Además, deberá contener “@” y acabar en “.es” o “.com”.

- Teléfono: dato numérico de tipo Int. No puede ser nulo y debe contener 9 números.

- Dirección: cadena de texto. Indica dónde reside el usuario. No puede ser nulo.

- Roles: cadena de texto: Roles del usuario. Puede ser USER o ADMIN. No puede ser nulo.


**Tabla Producto:**

- Identificador del producto: dato numérico tipo Long. Se generará automáticamente al crear un producto. No puede ser nulo.

- Nombre: cadena de texto. Nombre del producto, que especificará verdaderamente el tipo de producto que es, como por ejemplo, “Camiseta”, “Juego de cubertería”, etc. No puede ser nulo.

- Categoría: cadena de texto. Especifica la categoría del producto. Importante a la hora de realizar búsquedas. No puede ser nulo y no podrá contener una categoría que no existe.

- Stock: dato numérico tipo Int. Indica la cantidad disponible del producto. No puede ser nulo ni menor que 0.

- Precio: dato numérico tipo Double. Hace referencia al importe de una unidad del producto. No puede ser nulo ni menor que 0.

- Descripción: cadena de texto. Describe el producto.


**Tabla Pedido:**

- Identificador del pedido: dato numérico de tipo Long. Se generará automáticamente al crear un pedido. No puede ser nulo.

- Destino: cadena de texto. Indica la dirección del pedido. No puede ser nulo.

- Fecha Pedido: fecha tipo Date. Indica la fecha en la que se ha realizado la compra online. La fecha no puede ser nula y debe tener formato “YYYY-MM-DD”.

- Fecha Llegada: fecha tipo Date. Indica la fecha en la que está estipulada la llegada al destino. La fecha no puede ser nula y debe tener formato “YYYY-MM-DD”.

- Importe: dato numérico tipo Double. Indica el precio del total del pedido. No puede ser nulo.

- Estado: cadena de texto. Indica si el pedido está en curso o ya se ha realizado. Sólo pueden tener los siguientes valores: "Preparando", "En camino", "Cancelado" o "Finalizado".


#### Diagrama Entidad / Relación:
 
![image](https://github.com/user-attachments/assets/4a74b62a-1131-4f5d-9808-f5cb60d04acf)




El diagrama representa una relación ternaria entre las tres tablas.

Un usuario puede realizar muchos pedidos y en cada pedido pueden ir incluidos muchos productos.


## Endpoints requeridos

1. **Operaciones de Usuario**:

   1.1 Autenticación:
    - POST /usuarios/login: permite al usuario autenticarse.
       - RUTA PÚBLICA: todas las peticiones a este endpoint deben permitirse.
       - Entrada: la autenticación se realizará con username y password, en formato JSON.
       - Salida: token de la sesión si las credenciales son válidas.
       - HTTP:
           - 200: OK, logueado con éxito.
           - 400: BAD REQUEST, datos de entrada inválidos.
           - 404: NOT FOUND, no se ha encontrado al usuario.
         

   **1.2 Registro:**
   - POST /usuarios/registro: permite a los usuarios existentes en la base de datos registrarse.
       - RUTA PÚBLICA: todas las peticiones a este endpoint deben permitirse.
       - Entrada: JSON con todos los atributos pertenecientes a la tabla "usuarios": username, password, email, teléfono, dirección y roles.
       - Salida: JSON con el usuario registrado.
       - HTTP:
           - 201: CREATED, creado con éxito.
           - 400: BAD REQUEST, datos de entrada inválidos.
        
   **1.3 Información:**
   - GET /usuarios/{username}: permite al usuario consultar su información.
       - RUTA PROTEGIDA: sólo los usuarios autenticados pueden acceder a este recurso.
       - Pueden acceder los usuarios con rol ADMIN.
       - No pueden acceder los usuarios con rol USER, salvo que el que realice la consulta tenga el mismo username.
       - Entrada: username.
       - Salida: JSON con toda la información referente al usuario consultado.
       - HTTP:
            - 200: OK, datos obtenidos con éxito.
            - 401: UNAUTHORIZED, usuario sin autenticar.
            - 403: FORBIDDEN, usuario sin permiso para acceder al recurso.
            - 404: NOT FOUND, no se ha encontrado al usuario.


    **1.4 Actualizar usuario:**
    - PUT /usuarios/{username}: permite al usuario modificar su información.
       - RUTA PROTEGIDA: sólo los usuarios autenticados pueden acceder a este recurso.
       - Sólo pueden acceder los usuarios que tengan el mismo username.
       - Entrada: JSON con todos los atributos pertenecientes a la tabla "usuarios", tanto los modificados como los que no: username, password, email, teléfono, dirección y roles. 
       - Salida: JSON con toda la información referente al usuario consultado.
       - HTTP:
            - 200: OK, datos modificados con éxito.
            - 401: UNAUTHORIZED, usuario sin autenticar.
            - 403: FORBIDDEN, usuario sin permiso para acceder al recurso.
            - 404: NOT FOUND, no se ha encontrado al usuario.
        
    **1.5 Borrar usuario:**
    - DELETE /usuarios/{username}: permite al usuario borrar su información.
       - RUTA PROTEGIDA: sólo los usuarios autenticados pueden acceder a este recurso.
       - Sólo pueden acceder los usuarios que tengan el rol ADMIN. Los que tengan el rol user podrán solicitar al ADMIN la eliminación del perfil.
       - Salida: JSON mostrando que el usuario se eliminó correctamente.
       - HTTP:
            - 200: OK, usuario eliminado con éxito.
            - 401: UNAUTHORIZED, usuario sin autenticar.
            - 403: FORBIDDEN, usuario sin permiso para acceder al recurso.
            - 404: NOT FOUND, no se ha encontrado al usuario.


 2. **Operaciones de Productos:**
    
    RUTAS PROTEGIDAS: requiere que el usuario esté autenticado para acceder al recurso.

    **2.1 Obtener un producto:**
    - GET /productos/{id}: devuelve toda la información de un producto.
        - Entrada: id del producto.
        - Salida: JSON con toda la información referente al producto consultado.
        - HTTP:
            - 200: OK, datos obtenidos con éxito.
            - 401: UNAUTHORIZED, usuario sin autenticar.
            - 404: NOT FOUND, no se ha encontrado el producto.

     **2.2 Obtener todos los productos:**
     - GET /productos/: devuelve una lista con todos los productos.
         -  Salida: JSON con la información referente a todos los productos existentes en la base de datos.
         -  HTTP:
            - 200: OK, datos obtenidos con éxito.
            - 401: UNAUTHORIZED, usuario sin autenticar.
            - 404: NOT FOUND, no se ha encontrado ningún producto.

    **2.3 Insertar producto:**
    - POST /productos/: permite insertar un nuevo producto.
         - Sólo pueden acceder los usuarios con rol ADMIN.
         - Entrada JSON con todos los atributos de la tabla "productos": nombre, categoría, stock, precio y descripción.
         - HTTP:
            - 201: CREATED, producto creado con éxito.
            - 401: UNAUTHORIZED, usuario sin autenticar.
            - 403: FORBIDDEN, usuario sin permiso.
            - 404: NOT FOUND, no se ha encontrado el producto.

    **2.4 Modificar producto:**
    - UPDATE /productos/{id}: modifica los datos de un producto.
          - Sólo pueden acceder los usuarios con el rol ADMIN.
          - Entrada: JSON con todos los atributos de la tabla "productos", tanto los que han sido modificados como los que no: nombre, categoría, stock, precio y descripción.
          - Salida: JSON con los datos modificados.
          - HTTP:
            - 201: CREATED, producto modificado con éxito.
            - 401: UNAUTHORIZED, usuario sin autenticar.
            - 403: FORBIDDEN, usuario sin permiso.
            - 404: NOT FOUND, no se ha encontrado al producto.

    **2.5 Borrar producto:**
    - DELETE /productos/{id}: elimina un producto de la base de datos.
          - Sólo pueden acceder los usuarios con el rol ADMIN.
          - Salida: JSON indicando que el producto se ha eliminado con éxito.
          - HTTP:
            - 200: OK, producto eliminado con éxito.
            - 401: UNAUTHORIZED, usuario sin autenticar.
            - 403: FORBIDDEN, usuario sin permiso.
            - 404: NOT FOUND, no se ha encontrado el producto.

3. **Operaciones de Pedidos:**

   RUTAS PROTEGIDAS: requiere que el usuario esté autenticado para acceder al recurso.

   **3.1 Insertar pedido:**
   - POST /pedidos/: inserta un nuevo pedido
        - Pueden acceder todos los usuarios autenticados.
        - Entrada: JSON con los atributos de la tabla "pedidos", inluido nombre del producto o productos que forman parte del pedido y el username del usuario que lo ha realizado.
        - HTTP:
            - 201: CREATED, pedido realizado con éxito.
            - 401: UNAUTHORIZED, usuario sin autenticar.
            - 404: NOT FOUND, no se ha encontrado el pedido.

   **3.2 Datos de un pedido:**
   - GET /pedidos/{id}: consulta los datos de un pedido realizado por un usuario, mientras que esté en curso.
       - Pueden acceder todos los usuarios autenticados.
       - Salida: JSON con con todos los datos referentes al pedido realizado.
       - HTTP:
            - 200: OK, datos correctos.
            - 401: UNAUTHORIZED, usuario sin autenticar.
            - 404: NOT FOUND, no se ha encontrado el pedido.
        
   **3.3 Datos de todos los pedidos:**
   - GET /pedidos/: consulta los datos de todos los pedidos registrados en la base de datos.
        - Sólo pueden acceder los usuarios con el rol ADMIN.
        - Salida: JSON con los datos referentes a todos los pedidos.
        -  HTTP:
            - 200: OK, datos correctos.
            - 401: UNAUTHORIZED, usuario sin autenticar.
            - 403: FORBIDDEN, usuario sin permiso.
            - 404: NOT FOUND, no se ha encontrado el pedido.
         
   **3.4 Historial de pedidos:**
   - GET /pedidos/{username}: consulta el historial de pedidos de un usuario.
        - Sólo pueden acceder los usuarios con el rol USER y que tengan el mismo username que el que realiza la consulta.
        - Salida: JSON con los datos referentes a los pedidos del usuario que ha realizado la consulta.
        - HTTP:
            - 200: OK, datos correctos.
            - 401: UNAUTHORIZED, usuario sin autenticar.
            - 403: FORBIDDEN, usuario sin permiso.
            - 404: NOT FOUND, no se ha encontrado el pedido.
         
   **3.5 Actualizar pedido:**
   - PUT /pedidos/{nombre}: modifica los datos del pedido seleccionado.
         - Sólo pueden acceder los usuarios que tengan el mismo username que el que ha realizado la operación de insertar el pedido.
         - Entrada: JSON con los atributos de la tabla "pedidos", el nombre del producto o productos y el username del usuario que realiza la modificación.
         - Salida: JSON con los datos del pedido, tanto los modificados como los que no.
         - HTTP:
            - 200: OK, pedido modificado con éxito.
            - 401: UNAUTHORIZED, usuario sin autenticar.
            - 403: FORBIDDEN, usuario sin permiso.
            - 404: NOT FOUND, no se ha encontrado el pedido.

   **3.6 Borrar pedido:**
   - DELETE /pedidos/{nombre}: borra los datos del pedido seleccionado.
         - Sólo pueden acceder los usuarios que tengan el mismo username que el que ha realizado la operación de insertar el pedido.
         - Salida: JSON mostrando que el pedido se eliminó correctamente.
         - HTTP:
             - 200: OK, pedido eliminado con éxito.
             - 401: UNAUTHORIZED, usuario sin autenticar.
             - 403: FORBIDDEN, usuario sin permiso.
             - 404: NOT FOUND, no se ha encontrado el pedido.



## Lógica de negocio:

### Gestión de Roles:
Van a existir dos tipos de roles, que tendrán diferentes funcionalidades a la hora de gestionar las tablas. Los roles van a ser ADMIN y USER.

### Validaciones:
- La modificación y el borrado de un pedido las debe hacer el usuario con el mismo username que el que ha solicitado el recurso
- Un pedido debe tener obligatoriamente un producto como mínimo y una dirección de destino
- El precio de un producto no puede ser inferior o igual a 0.
- La contraseña de un usuario no puede ser inferior a 6 caracteres ni superior a 12, además debe tener mínimo un número y un carácter especial
- El username no puede ser superior a 10 caracteres
- Las operaciones de pedido deben afectar a la cantidad de stock de los productos.
- Se debe pedir una segunda confirmación antes de finalizar el proceso de hacer un pedido.
  

## Restricciones de seguridad:
- Autenticación basada en tokens (JWT)
- Control de registro, acceso y gestiones según rol
- Validación de los campos
- Cifrado de contraseñas usando algoritmos de hash
