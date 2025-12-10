create database El_BodegónBD;
use El_BodegónBD;

create table Proveedor(
ID_Proveedor INT PRIMARY KEY,
Proveedor_Nombre VARCHAR(20) NOT NULL,
teléfono_Proveedor CHAR(9) UNIQUE NOT NULL	
);
INSERT INTO PROVEEDOR VALUES (1, 'GLORIA', 987654321);
INSERT INTO PROVEEDOR VALUES (2, 'NESTLÉ', 912345678);
INSERT INTO PROVEEDOR VALUES (3, 'LAIVE', 933221100);
INSERT INTO PROVEEDOR VALUES (4, 'SAN FERNANDO', 988776655);
INSERT INTO PROVEEDOR VALUES (5, 'ALICORP', 976543210);
INSERT INTO PROVEEDOR VALUES (6, 'BACKUS', 987123456);
INSERT INTO PROVEEDOR VALUES (7, 'FRIDAY S.A.C.', 923456789);
INSERT INTO PROVEEDOR VALUES (8, 'MOLITALIA', 998877665);
INSERT INTO PROVEEDOR VALUES (9, 'DON VITTORIO', 934567890);
INSERT INTO PROVEEDOR VALUES (10, 'DONOFRIO', 956789012);
-- procedures de Proveedor --
CREATE PROCEDURE sp_AgregarProveedor
(
    ID_PROVEEDOR INT,
    NOMBRE_PROVEEDOR VARCHAR(20),
    TELEFONO_PROVEEDOR CHAR(9)
)
INSERT INTO PROVEEDOR VALUES(ID_PROVEEDOR,NOMBRE_PROVEEDOR,TELEFONO_PROVEEDOR);

-------------------------------------------------------------------------------
CREATE PROCEDURE sp_ModificarProveedores
(
    ID_PROVEEDOR INT,
    Proveedor_Nombre VARCHAR(20),
    teléfono_Proveedor CHAR(9)
)
UPDATE PROVEEDOR SET Proveedor_Nombre = Proveedor_Nombre, teléfono_Proveedor = teléfono_Proveedor
WHERE ID_PROVEEDOR = ID_PROVEEDOR;
-------------------------------------------------------------------------------
CREATE PROCEDURE sp_EliminarProveedores
(
    ID_PROVEEDOR INT
)
DELETE FROM PROVEEDOR WHERE ID_PROVEEDOR = ID_PROVEEDOR;
-------------------------------------------------------------------------------
CREATE PROCEDURE sp_ListarProveedores()
SELECT * FROM PROVEEDOR;

CALL sp_ListarProveedores();

-- -------------------------------PRODUCTOS---------------------------------------------
create table Producto(
	Id_Producto INT PRIMARY KEY,
    Nombre_producto VARCHAR(20) NOT NULL,
    Descripcion_Producto VARCHAR(20) NOT NULL,
    Marca_producto VARCHAR(20) NOT NULL,
    precio_producto REAL NOT NULL,
    categoria_producto VARCHAR(20) NOT NULL,
    estado_producto VARCHAR(20) NOT NULL,
    stock_producto INT NOT NULL,
    ID_Proveedor INT,
    FOREIGN KEY (ID_Proveedor) REFERENCES Proveedor(ID_Proveedor)
);
-- ARROZ --
INSERT INTO PRODUCTO VALUES(100, 'Faraón Rojo', 'Grano largo saco 40k', 'Faraón', 10.90, 'Arroz', 'Disponible', 100, 5);
INSERT INTO PRODUCTO VALUES(101, 'Paisana', 'Grano largo costal  ', 'Paisana', 12.90, 'Arroz', 'Disponible', 50, 1);
INSERT INTO PRODUCTO VALUES(102, 'Bell´s Extra', 'Grano largo fino   ', 'Bell´s', 11.90, 'Arroz', 'Disponible', 90, 7);
INSERT INTO PRODUCTO VALUES(103, 'Valle norte', 'Arroz grano largo  ', 'Valle norte', 14.90, 'Arroz', 'Disponible', 20, 7);
INSERT INTO PRODUCTO VALUES(104, 'Blanca Flor', 'Arroz largo blanco ', 'Blanca Flor', 15.90, 'Arroz', 'Disponible', 10, 5);
INSERT INTO PRODUCTO VALUES(106,"Rico Arroz","Grano entero largo ", "RicoArroz",11.50,"Arroz","Disponible",60,4);
INSERT INTO PRODUCTO VALUES(107,"La Campiña","Extra grano largo ", "Campiña",13.20,"Arroz","Disponible",80,5);
INSERT INTO PRODUCTO VALUES(108,"Sol de Oro","Grano fino largo ", "Sol Oro",14.00,"Arroz","Disponible",70,6);
INSERT INTO PRODUCTO VALUES(109,"Costeño","Arroz tipo extra ", "Costeño",12.80,"Arroz","Disponible",100,4);
INSERT INTO PRODUCTO VALUES(110,"Doña Gracia","Arroz calidad top", "Doña Gracia",11.90,"Arroz","Disponible",120,2);
INSERT INTO PRODUCTO VALUES(111,"Don Lucho","Extra largo blanco", "Don Lucho",13.50,"Arroz","Disponible",90,1);

-- AZÚCAR --
INSERT INTO PRODUCTO VALUES(201, 'Dulfina',    'Azúcar blanca suave', 'Dulfina',   10.90, 'Azúcar', 'Disponible', 10, 1);
INSERT INTO PRODUCTO VALUES(202, 'Manuelita',  'Refinada calidad A ', 'Manuelita', 10.80, 'Azúcar', 'Disponible', 40, 1);
INSERT INTO PRODUCTO VALUES(203, 'Paramonga',  'Rubia dulce premium', 'Paramonga', 12.50, 'Azúcar', 'Disponible', 60, 1);
INSERT INTO PRODUCTO VALUES(204, 'Cartavio',   'Blanca extra fina  ', 'Cartavio',  15.80, 'Azúcar', 'Disponible', 30, 2);
INSERT INTO PRODUCTO VALUES(205, 'Aro',        'Azúcar de mesa top ', 'Aro',       10.80, 'Azúcar', 'Disponible', 80, 3);
INSERT INTO PRODUCTO VALUES(206, 'Chiclina',   'Azúcar blanca extra', 'Chiclina',   11.90, 'Azúcar', 'Disponible', 70, 3);
INSERT INTO PRODUCTO VALUES(207, 'Rubia Sol',  'Rubia dulce natural', 'Sol',        12.70, 'Azúcar', 'Disponible', 60, 4);
INSERT INTO PRODUCTO VALUES(208, 'Acuarela',   'Blanca suave pura  ', 'Acuarela',   10.50, 'Azúcar', 'Disponible', 40, 6);
INSERT INTO PRODUCTO VALUES(209, 'Cañaveral',  'Rubia suave premium', 'Cañaveral',  13.60, 'Azúcar', 'Disponible', 50, 5);
INSERT INTO PRODUCTO VALUES(210, 'Dulce Vida', 'Azúcar fina natural', 'DulceVida',  11.40, 'Azúcar', 'Disponible', 60, 2);
INSERT INTO PRODUCTO VALUES(211, 'Mi Caña',    'Rubia dulce calidad', 'Mi Caña',    12.20, 'Azúcar', 'Disponible', 80, 4);

-- FIDEOS --
INSERT INTO PRODUCTO VALUES(301, 'Vittorio',    'Fideo largo calidad ', 'Vittorio',    4.20, 'Fideos', 'Disponible', 50, 5);
INSERT INTO PRODUCTO VALUES(302, 'Moli italia', 'Fideo estandar 500g ', 'Moli italia', 4.10, 'Fideos', 'Disponible', 70, 6);
INSERT INTO PRODUCTO VALUES(303, 'Nicolini',    'Fideo fino nacional ', 'Nicolini',    3.50, 'Fideos', 'Disponible', 50, 8);
INSERT INTO PRODUCTO VALUES(304, 'Don Vittorio', 'Fideo largo premium ', 'Vittorio',   4.50, 'Fideos', 'Disponible', 60, 5);
INSERT INTO PRODUCTO VALUES(305, 'Molitalia',    'Fideo clásico suave ', 'Molitalia',  3.90, 'Fideos', 'Disponible', 70, 6);
INSERT INTO PRODUCTO VALUES(306, 'Nicolini',     'Fideo espagueti 500g', 'Nicolini',   3.70, 'Fideos', 'Disponible', 50, 8);
INSERT INTO PRODUCTO VALUES(307, 'Don Luigi',    'Fideo tornillo extra', 'Don Luigi',  4.20, 'Fideos', 'Disponible', 45, 7);
INSERT INTO PRODUCTO VALUES(308, 'Don Cayetano', 'Fideo pluma calidad ', 'Cayetano',   4.00, 'Fideos', 'Disponible', 55, 7);
INSERT INTO PRODUCTO VALUES(309, 'Rumba',        'Fideo corto suave   ', 'Rumba',      3.80, 'Fideos', 'Disponible', 65, 6);

-- CEREALES --
INSERT INTO PRODUCTO VALUES(401, 'AngelFlakes', 'Cereal ojuelas miel ', 'AngelFleaks', 12.50, 'Cereal', 'Disponible', 40, 8);
INSERT INTO PRODUCTO VALUES(402, 'Nestlé',      'Ojuelas maíz crujien', 'Nestlé',      15.80, 'Cereal', 'Disponible', 80, 2);
INSERT INTO PRODUCTO VALUES(403, 'ChocaPick',   'Ojuelas choco kids  ', 'Nestlé',      15.80, 'Cereal', 'Disponible', 80, 2);
INSERT INTO PRODUCTO VALUES(404, 'Kellogg´s',   'Cereal ojuelas mix  ', 'Flecks',      25.70, 'Cereal', 'Disponible', 90, 2);
INSERT INTO PRODUCTO VALUES(405, 'Granix Mix',    'Cereal mixto energía', 'Granix',     13.90, 'Cereal', 'Disponible', 60, 2);
INSERT INTO PRODUCTO VALUES(406, 'Angel Oats',    'Cereal avena dulce  ', 'AngelFlakes',12.50, 'Cereal', 'Disponible', 45, 2);
INSERT INTO PRODUCTO VALUES(407, 'Corn Choco',    'Cereal chococrocante', 'Nestlé',     14.20, 'Cereal', 'Disponible', 50, 2);
INSERT INTO PRODUCTO VALUES(408, 'Kellogg Mix',   'Cereal multigrano   ', 'Kellogg´s',  17.80, 'Cereal', 'Disponible', 65, 2);
INSERT INTO PRODUCTO VALUES(409, 'Fruity Pops',   'Cereal frutado kids ', 'Nestlé',     15.50, 'Cereal', 'Disponible', 55, 2);
INSERT INTO PRODUCTO VALUES(410, 'Energy Start',  'Cereal avena miel   ', 'Nestlé',     13.50, 'Cereal', 'Disponible', 40, 2);

-------------------------------------- INSERTAR PRODUCTOS------------------------------------------------
DELIMITER //
CREATE PROCEDURE sp_Insertar_Productos(
    IN p_Id_Producto INT,
    IN p_Nombre_producto VARCHAR(20),
    IN p_Descripcion_Producto VARCHAR(20),
    IN p_Marca_producto VARCHAR(20),
    IN p_precio_producto REAL,
    IN p_categoria_producto VARCHAR(20),
    IN p_estado_producto VARCHAR(20),
    IN p_stock_producto INT,
    IN p_ID_Proveedor INT
)
BEGIN
    -- Validar que el proveedor exista
    IF EXISTS (SELECT 1 FROM Proveedor WHERE ID_Proveedor = p_ID_Proveedor) THEN
        INSERT INTO Producto (
            Id_Producto,
            Nombre_producto,
            Descripcion_Producto,
            Marca_producto,
            precio_producto,
            categoria_producto,
            estado_producto,
            stock_producto, -- ✅ columna real
            ID_Proveedor
        )
        VALUES (
            p_Id_Producto, p_Nombre_producto, p_Descripcion_Producto,
            p_Marca_producto, p_precio_producto, p_categoria_producto,
            p_estado_producto, p_stock_producto, p_ID_Proveedor
        );
    ELSE
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Error: El proveedor no existe.';
    END IF;
END //
DELIMITER ;


-------------------------------- EDITAR PRODUCTOS ---------------------------------------
DELIMITER //
CREATE PROCEDURE sp_Editar_Productos
(
    IN p_Id_Producto INT,
    IN p_Nombre_producto VARCHAR(20),
    IN p_Descripcion_Producto VARCHAR(20),
    IN p_Marca_producto VARCHAR(20),
    IN p_precio_producto REAL,
    IN p_categoria_producto VARCHAR(20),
    IN p_estado_producto VARCHAR(20),
    IN p_stock_producto INT,
    IN p_Proveedor_Nombre VARCHAR(50)
)
BEGIN
    DECLARE v_ID_Proveedor INT;

    -- Buscar el ID del proveedor por nombre
    SELECT ID_Proveedor INTO v_ID_Proveedor
    FROM Proveedor
    WHERE Proveedor_Nombre = p_Proveedor_Nombre;

    -- Si se encontró, actualizar el producto
    UPDATE Producto
    SET 
        Nombre_producto = p_Nombre_producto,
        Descripcion_Producto = p_Descripcion_Producto,
        Marca_producto = p_Marca_producto,
        precio_producto = p_precio_producto,
        categoria_producto = p_categoria_producto,
        estado_producto = p_estado_producto,
        stock_producto = p_stock_producto,
        ID_Proveedor = v_ID_Proveedor
    WHERE Id_Producto = p_Id_Producto;
END;
//
DELIMITER ;

-- LISTAR
DELIMITER //
CREATE PROCEDURE sp_Listar_Producto()
BEGIN
    SELECT 
        p.Id_Producto,
        p.Nombre_producto,
        p.Descripcion_Producto,
        p.Marca_producto,
        p.precio_producto,
        p.categoria_producto,
        p.estado_producto,
        p.stock_producto,
        (
            SELECT pr.Proveedor_Nombre 
            FROM Proveedor pr 
            WHERE pr.ID_Proveedor = p.ID_Proveedor
        ) AS Proveedor_Nombre -- <--- aquí el cambio
    FROM Producto p
    ORDER BY p.Id_Producto;
END //
DELIMITER ;

describe proveedor;
call sp_Listar_Producto();
-- ----------------------------AGREGAR STOCK----------------------------------------
CREATE PROCEDURE sp_AgregarStock
( 
    pid INT, 
    pstock INT
)
UPDATE PRODUCTO SET stock_producto = stock_producto + pstock
WHERE ID_PRODUCTO = pid;
----------- ----------------COMPROBAR QUE EXISTE PRODUCTO-----------------------------------
CREATE PROCEDURE sp_ExisteProducto
(
    IN pid INT
)
SELECT COUNT(*) AS total FROM producto WHERE ID_Producto = pid;


-- --------------------------------BUSCAR PRODUCTO--------------------------------------
DELIMITER //
CREATE PROCEDURE sp_Buscar_Producto(
    IN pidPrefix VARCHAR(10)
)
BEGIN
    SELECT 
        p.Id_Producto,
        p.Nombre_producto,
        p.Descripcion_Producto,
        p.Marca_producto,
        p.precio_producto,
        p.categoria_producto,
        p.estado_producto,
        p.stock_producto,
        (
            SELECT pr.Proveedor_Nombre 
            FROM Proveedor pr 
            WHERE pr.ID_Proveedor = p.ID_Proveedor
        ) AS NOMBRE_PROVEEDOR
    FROM Producto p
    WHERE CAST(p.Id_Producto AS CHAR) LIKE CONCAT(pidPrefix, '%');
END;
//
DELIMITER ;
CALL sp_Buscar_Producto(20);
-- -----------------ELIMINAR PRODUCTO-------------------------

DELIMITER //
CREATE PROCEDURE sp_Eliminar_Producto(
    IN p_ID_PRODUCTO INT
)
BEGIN
    DELETE FROM PRODUCTO WHERE ID_Producto = p_ID_PRODUCTO;
END;
//
DELIMITER ;
call sp_Eliminar_Producto(105);
call sp_Listar_Producto();


-- -----------CLIENTES-------------------
	create table Cliente(
		id_Cliente INT PRIMARY KEY,
		dni CHAR(8) UNIQUE NOT NULL,
		nombre_cliente VARCHAR(20) NOT NULL,
		apellidos_cliente VARCHAR(20) NOT NULL,
		telefono_cliente CHAR(9) UNIQUE NOT NULL
	);

call sp_Insertar_Cliente(1,'72345671','Luis Fernando','Ruiz Lopez','123456789');
CALL sp_Insertar_Cliente(2, '72648935', 'María Alejandra', 'Pérez Gómez', '987654321');
CALL sp_Insertar_Cliente(3, '73012345', 'Carlos Eduardo', 'Ramírez Silva', '912345678');
CALL sp_Insertar_Cliente(4, '72109876', 'Ana Lucía', 'Torres Vela', '956789321');
CALL sp_Insertar_Cliente(5, '73456789', 'José Manuel', 'Castillo Ríos', '923456789');
CALL sp_Insertar_Cliente(6, '72876543', 'Gabriela Sofía', 'Fernández Ramos', '965432189');
CALL sp_Insertar_Cliente(7, '72034567', 'Ricardo Andrés', 'Salas Medina', '987123456');
CALL sp_Insertar_Cliente(8, '72561234', 'Lucía Fernanda', 'García Chávez', '976543210');
CALL sp_Insertar_Cliente(9, '72387654', 'Miguel Ángel', 'Flores Herrera', '998877665');
CALL sp_Insertar_Cliente(10, '72987612', 'Valeria Noemí', 'Rojas Palacios', '911122233');
CALL sp_Insertar_Cliente(11, '72233445', 'Diego Armando', 'Guzmán Peña', '900123789');
CALL sp_Insertar_Cliente(12, '76653082', 'Andre Alejandro', 'Arbayza Pareja', '963850472');
CALL sp_Insertar_Cliente(13, '75410143', 'Rodrigo Sebastian', 'Amoroto Castillo', '934510842');
CALL sp_Insertar_Cliente(14, '71407276', 'José Miguel', 'Aguilar Juaréz', '902036794');
CALL sp_Insertar_Cliente(15, '76354392', 'Fabrizio Antonio', 'Balcazar Tapia', '917494508');
CALL sp_Insertar_Cliente(16, '75417377', 'Jesús Esteban', 'Aguilar Gómez', '936105509');


DELIMITER //
CREATE PROCEDURE sp_Listar_Clientes()
BEGIN
    SELECT * FROM CLIENTE;
END //
DELIMITER ;
call sp_Listar_Clientes();


-- -----------PROCEDURES CLIENTE--------------------------------

-- 1) INSERTAR CLIENTE--
DELIMITER //
CREATE PROCEDURE sp_Insertar_Cliente(
    IN pid INT, IN pdni INT, IN pnombre VARCHAR(50),
    IN papellido VARCHAR(50), IN ptelefono VARCHAR(15)
)
BEGIN
    INSERT INTO CLIENTE (ID_CLIENTE, DNI, NOMBRE_CLIENTE, APELLIDOS_CLIENTE, TELEFONO_CLIENTE)
    VALUES (pid, pdni, pnombre, papellido, ptelefono);
END //
DELIMITER ;

-- 2) MODIFICAR CLIENTE--
DELIMITER //
CREATE PROCEDURE sp_Editar_Cliente(
    IN pid INT, IN pdni INT, IN pnombre VARCHAR(50),
    IN papellido VARCHAR(50), IN ptelefono VARCHAR(15)
)
BEGIN
    UPDATE CLIENTE
    SET DNI = pdni, NOMBRE_CLIENTE = pnombre,
        APELLIDOS_CLIENTE = papellido, TELEFONO_CLIENTE = ptelefono
    WHERE ID_CLIENTE = pid;
END //
DELIMITER ;

-- 3) ELIMINAR CLIENTE ----
DELIMITER //
CREATE PROCEDURE sp_Eliminar_Cliente(IN pid INT)
BEGIN
    DELETE FROM CLIENTE WHERE ID_CLIENTE = pid;
END //
DELIMITER ;

-- BUSCAR CLIENTE --
DELIMITER //
CREATE PROCEDURE sp_Buscar_Cliente_PorDNI (
    IN p_dni CHAR(8)
)
BEGIN
    IF EXISTS (SELECT 1 FROM Cliente WHERE dni = p_dni) THEN
        SELECT 
            id_Cliente,
            dni,
            nombre_cliente,
            apellidos_cliente,
            telefono_cliente
        FROM Cliente
        WHERE dni = p_dni;
    ELSE
        SELECT 'Cliente no encontrado' AS mensaje;
    END IF;
END //
DELIMITER ;
CALL sp_Buscar_Cliente_PorDNI('72345676');

-- ---------------VENTAS----------------------
create table Venta(
id_venta int primary key AUTO_INCREMENT,
id_Cliente int,
metodo_pago varchar(20),
fecha_venta date,
FOREIGN KEY (id_Cliente) REFERENCES Cliente(id_Cliente)
);

CREATE TABLE DetalleVenta (
    id_detalle INT AUTO_INCREMENT PRIMARY KEY,
    id_venta INT,
    id_producto INT,
    id_Cliente INT,
    cantidad INT NOT NULL,
    precio_unitario DECIMAL(10, 2) NOT NULL,
    subtotal DECIMAL(10, 2) GENERATED ALWAYS AS (cantidad * precio_unitario) STORED,
    FOREIGN KEY (id_venta) REFERENCES Venta(id_venta),
	FOREIGN KEY (id_Cliente) REFERENCES Cliente(id_Cliente)
);
-- LISTAR DETALLES VENTA -- 
DELIMITER //
CREATE PROCEDURE sp_ListarDetalleVenta()
BEGIN
    SELECT 
        v.id_venta,
        CONCAT(c.nombre_cliente, ' ', c.apellidos_cliente) AS cliente, 
        v.metodo_pago,
        v.fecha_venta,
        p.Nombre_producto,
        dv.cantidad,
        dv.precio_unitario,
        dv.subtotal
    FROM Venta v
    JOIN Cliente c ON v.id_cliente = c.id_cliente
    JOIN DetalleVenta dv ON v.id_venta = dv.id_venta
    JOIN Producto p ON dv.id_producto = p.id_producto
    ORDER BY v.fecha_venta DESC;
END //
DELIMITER ;

	call sp_ListarDetalleVenta();
-- TOTAL GANANCIAS -- 
DELIMITER //
CREATE PROCEDURE sp_TotalVentas()
BEGIN
    SELECT 
        IFNULL(SUM(dv.subtotal), 0) AS total_ventas
    FROM 
        DetalleVenta dv;
END //
DELIMITER ;

	
DELIMITER //
CREATE PROCEDURE sp_descontarStock (
    IN p_id_producto INT,
    IN p_cantidad_vendida INT
)
BEGIN
    UPDATE Producto
    SET stock_producto = stock_producto - p_cantidad_vendida
    WHERE Id_Producto = p_id_producto AND stock_producto >= p_cantidad_vendida;
END//
DELIMITER ;
-- ------------LISTAR VENTA----------------
DELIMITER //
CREATE PROCEDURE sp_ListarVentas()
BEGIN
    SELECT * FROM Venta;
END//
DELIMITER ;
call sp_ListarVentas();

-- ---------------------REGISTRAR VENTA--------------------------
DELIMITER //
CREATE PROCEDURE sp_RegistrarVenta (
    IN p_id_cliente INT,
    IN p_metodo_pago VARCHAR(20),
    IN p_fecha DATE
)
BEGIN
    INSERT INTO Venta (id_cliente, metodo_pago, fecha_venta)
    VALUES (p_id_cliente, p_metodo_pago, p_fecha);

    SELECT LAST_INSERT_ID() AS id_generado;  -- Devuelve el id_venta autogenerado
END //
DELIMITER ;

-- COMPRAS --

CREATE TABLE CompraProveedor (
    id_compra INT PRIMARY KEY AUTO_INCREMENT,
    id_proveedor INT,
    metodo_pago VARCHAR(20),
    fecha_compra DATE,
    FOREIGN KEY (id_proveedor) REFERENCES Proveedor(ID_Proveedor)
);

CREATE TABLE DetalleCompra (
    id_detalle INT PRIMARY KEY AUTO_INCREMENT,
    id_compra INT,
    id_producto INT,
    cantidad INT NOT NULL,
    precio_unitario DECIMAL(10, 2) NOT NULL,
    subtotal DECIMAL(10, 2) GENERATED ALWAYS AS (cantidad * precio_unitario) STORED,
    FOREIGN KEY (id_compra) REFERENCES CompraProveedor(id_compra),
    FOREIGN KEY (id_producto) REFERENCES Producto(Id_Producto)
);

-- PROCEDURES DE COMPRA Y DETALLECOMPRA --

DELIMITER //
CREATE PROCEDURE sp_RegistrarCompra (
    IN p_id_proveedor INT,
    IN p_metodo_pago VARCHAR(20),
    IN p_fecha DATE
)
BEGIN
    INSERT INTO CompraProveedor (id_proveedor, metodo_pago, fecha_compra)
    VALUES (p_id_proveedor, p_metodo_pago, p_fecha);

    SELECT LAST_INSERT_ID() AS id_generado;
END;
//
DELIMITER ;



DELIMITER //
CREATE PROCEDURE sp_InsertarDetalleCompra (
    IN p_id_compra INT,
    IN p_id_producto INT,
    IN p_cantidad INT,
    IN p_precio_unitario DECIMAL(10, 2)
)
BEGIN
    INSERT INTO DetalleCompra (id_compra, id_producto, cantidad, precio_unitario)
    VALUES (p_id_compra, p_id_producto, p_cantidad, p_precio_unitario);
END;
//
DELIMITER ;

-- AGREGAR STOCK DE PRODUCTOS COMPRADOS --
DELIMITER //
CREATE PROCEDURE sp_ActualizarStockProducto (
    IN p_id_producto INT,
    IN p_cantidad INT
)
BEGIN
    UPDATE Producto
    SET stock_producto = stock_producto + p_cantidad
    WHERE Id_Producto = p_id_producto;
END;
//
DELIMITER ;

-- LISTADO DE COMPRAS --
DELIMITER //
CREATE PROCEDURE sp_ListarCompras()
BEGIN
    SELECT * FROM CompraProveedor;
END//
DELIMITER ;
call sp_ListarCompras();

-- LISTAR DETALLES DE LAS COMPRAS --  
DELIMITER //
CREATE PROCEDURE sp_ListarDetalleCompra()
BEGIN
    SELECT 
        cp.id_compra,
        pr.Proveedor_Nombre AS proveedor,
        cp.metodo_pago,
        cp.fecha_compra,
        p.Nombre_producto,
        dc.cantidad,
        dc.precio_unitario,
        dc.subtotal
    FROM CompraProveedor cp
    JOIN Proveedor pr ON cp.id_proveedor = pr.ID_Proveedor
    JOIN DetalleCompra dc ON cp.id_compra = dc.id_compra
    JOIN Producto p ON dc.id_producto = p.Id_Producto
    ORDER BY cp.fecha_compra DESC;
END //
DELIMITER ;
call sp_ListarDetalleCompra();
-- TOTAL DE COMPRAS --
DELIMITER //
CREATE PROCEDURE sp_TotalCompras()
BEGIN
    SELECT IFNULL(SUM(subtotal), 0) AS total_compras
    FROM DetalleCompra;
END //
DELIMITER ;

CALL sp_ListarProveedores();

call sp_Listar_Producto();

call sp_Listar_Clientes();

call sp_ListarDetalleVenta();

call sp_ListarVentas();

call sp_ListarCompras();

call sp_ListarDetalleCompra();





