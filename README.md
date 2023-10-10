# API for an e-commerce *"ShopAll"* 🛒🏪

### [es - español] 
## Introducción
¡Bienvenido a nuestra API de Productos! Esta API te permite gestionar usuarios, vendedores, carritos de compras, categorías, notificaciones por correo electrónico, inventario, productos, reseñas, tiendas y transacciones. A continuación, encontrarás detalles sobre cómo utilizar cada una de las tablas mencionadas en esta API.

## Tablas disponibles
1. Usuario:
  - usuario_id (Long, clave primaria)
  - nombre_usuario (String)
  - email (String)
  - contrasena (String)
  - tipo (String)
2. Categoria:
  - categoria_id (Long, clave primaria)
  - nombre (String)
3. Vendedor:
- vendedor_id (Long, primary key)
- usuario_id (Long, clave foránea que referencia a Usuario)
4. Producto:
  - producto_id (Long, clave primaria)
  - nombre (String)
  - precio (Double)
  - categoria_id (Long, clave foránea que referencia a Categoria)
  - vendedor_id (Long, clave foránea que referencia a Vendedor)
5. Carrito:
  - carrito_id (Long, clave primaria)
  - cantidad (Integer)
  - usuario_id (Long, clave foránea que referencia a Usuario)
  - producto_id (Long, clave foránea que referencia a Producto)
6. Inventario:
  - inventario_id (Long, clave primaria)
  - precio_venta (Float)
  - cantidad_stock (Integer)
  - vendedor_id (Long, clave foránea que referencia a Vendedor)
  - producto_id (Long, clave foránea que referencia a Producto)
7. Transaccion:
  - transaccion_id (Long, clave primaria)
  - fecha (Date)
  - monto_total (Float)
  - direccion_envio (String)
  - informacion_pago (String)

## Uso de la API
**Ruta Principal**: http://localhost:8080/shopall/api/v1

A continuación se detallan las rutas y métodos disponibles para cada tabla:
### Usuarios
- **GET /user**: Obtener todos los usuarios.
- **GET /user/:id**: Obtener un usuario por el *usuario_id*.
- **POST /user/add**: Crear un nuevo usuario (Recibe un RequestBody).
- **PUT /user/update/:id**: Actualizar un usuario (Recibe un RequestBody).
- **DELETE /user/delete/:id**: Eliminar un usuario.
### Categorías
- **GET /category**: Obtener todas las categorías.
- **GET /category/:id**: Obtener una categoría por el *categoria_id*.
- **POST /category/add**: Crear una nueva categoría (Recibe un RequestBody).
- **PUT /category/update/:id**: Actualizar una categoría (Recibe un RequestBody).
- **DELETE /category/delete/:id**: Eliminar una categoría.
### Vendedores
- **GET /seller**: Obtener todos los vendedores.
- **GET /seller/:id**: Obtener un vendedor por el *vendedor_id*.
- **POST /seller/add**: Crear un nuevo vendedor (Recibe un RequestBody).
- **PUT /seller/update/:id**: Actualizar un vendedor (Recibe un RequestBody).
- **DELETE /seller/delete/:id**: Eliminar un vendedor.
### Productos
- **GET /product**: Obtener todos los productos.
- **GET /product/:id**: Obtener un producto por el *producto_id*.
- **POST /product/add**: Crear un nuevo producto (Recibe un RequestBody).
- **PUT /product/update/:id**: Actualizar un producto (Recibe un RequestBody).
- **DELETE /product/delete/:id**: Eliminar un producto.



