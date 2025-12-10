# Sistema de Gestión de Bodega
Sistema de gestión de bodega desarrollado en Java y conectado a una base de datos MySQL, diseñado para administrar productos, proveedores, clientes y ventas de manera automatizada y eficiente.
# Características
- Gestión de productos con registro, edición, eliminación y control de stock.
- Control de proveedores y compras con actualización automática de inventario.
- Módulo de ventas con carrito, generación de boletas y actualización de stock.
- Gestión de clientes con búsqueda, registro y modificación de datos.
- Paneles separados para administrador y vendedor.
- Base de datos integrada para facilitar despliegue y replicación del sistema.
# Tecnologías
- Java con interfaz gráfica WindowBuilder (Swing)
- MySQL con procedimientos almacenados (CRUD, búsquedas y control de stock)
- JDBC / MySQL Connector para conectividad con la base de datos
# Estructura del Proyecto
- ConexiónBD.java: Manejo de conexión con MySQL
- ArregloClientes.java / ArregloProductos.java / ArregloProveedor.java / ArregloVentas.java: Control lógico y acceso a procedimientos almacenados
- Interfaz Administrador: Panel principal con acceso a funciones de inventario, proveedores, clientes, compras y reportes
- Interfaz Vendedor (Ventas): Módulo para registrar ventas, carrito y emisión de boletas
- Interfaz Login/Inicio: Autenticación y selección de rol de usuario
- ElBodegón_BD.sql: Esquema completo de base de datos con tablas, relaciones y datos iniciales
# Funcionalidades Principales
Módulo de Administrador
- Administración completa de productos (CRUD + control de stock)
- Registro y gestión de proveedores
- Registro y gestión de clientes
- Registro de compras y reposición de inventario
- Historial de ventas y compras con totales diarios, producto estrella y cliente frecuente
  
Módulo de Vendedor
- Registro rápido de ventas
- Carrito de compra con cantidades y precios
- Generación de boleta y actualización automática del inventario
# Instalación y Ejecución
1. Crear la base de datos ejecutando ElBodegón_BD.sql
2. Configurar credenciales en ConexiónBD.java
3. Ejecutar el proyecto desde Eclipse o cualquier IDE compatible
# Valor del Proyecto
Sistema completo que demuestra dominio en Java orientado a objetos, conexión con MySQL, gestión de inventarios y creación de interfaces funcionales para un negocio real. Facilita la administración de bodega, reduce errores y mejora la eficiencia operativa mediante procesos automatizados y seguros.

