# Sistema de Gestión de Pedidos

Aplicación de consola desarrollada en Java para registrar pedidos normales y urgentes, almacenarlos en SQLite y notificar a los usuarios suscritos.

## Tecnologías utilizadas

- Java 25
- Apache NetBeans
- Maven
- SQLite
- SQLite JDBC 3.50.3.0

## Funcionalidades

1. Crear pedido normal.
2. Crear pedido urgente.
3. Registrar un usuario observador.
4. Consultar los pedidos guardados.
5. Salir del sistema.

## Patrones de diseño

- **Factory:** `PedidoFactory` crea instancias de `PedidoNormal` o `PedidoUrgente`.
- **Singleton:** `ConexionBD` centraliza la instancia de conexión a SQLite.
- **Observer:** `Evento` administra observadores y notifica a los usuarios suscritos cuando se registra un pedido.

## Principios SOLID

- **SRP (Responsabilidad única):** las clases separan el modelo, creación, conexión, notificación, persistencia y coordinación del servicio.
- **OCP (Abierto/cerrado):** la clase abstracta `Pedido` permite agregar nuevas variantes; la fábrica actual requiere actualizarse para reconocerlas, por lo que este principio se aplica parcialmente.
- **LSP (Sustitución de Liskov):** `PedidoNormal` y `PedidoUrgente` pueden sustituir a `Pedido`.
- **ISP (Segregación de interfaces):** las interfaces `RepositorioPedidos` y `Observador` son pequeñas y específicas.
- **DIP (Inversión de dependencias):** `ServicioPedidos` depende de `RepositorioPedidos`, no de la implementación SQLite concreta.

## Ejecución del proyecto

1. Descargar o clonar el repositorio.
2. Abrir la carpeta del proyecto desde Apache NetBeans como proyecto Maven.
3. Verificar que Java esté instalado.
4. Esperar a que Maven descargue la dependencia SQLite JDBC.
5. Ejecutar la clase principal `com.sistemadepedidos.SistemaPedidos`.

También puede ejecutarse desde una terminal ubicada en la carpeta del proyecto mediante:

```bash
mvn clean compile
mvn exec:java -Dexec.mainClass="com.sistemadepedidos.SistemaPedidos"
```

## Base de datos

SQLite crea automáticamente el archivo `pedidos.db` y la tabla `pedidos` cuando se inicia el sistema.

La tabla almacena los siguientes datos:

- ID del pedido.
- Nombre del producto.
- Precio.
- Tipo de pedido.

Los registros permanecen guardados después de cerrar y volver a ejecutar la aplicación.

## Estructura de paquetes

- `com.sistemadepedidos`: clase principal y menú.
- `com.sistemadepedidos.modelos`: entidades del sistema.
- `com.sistemadepedidos.factory`: fábrica de pedidos.
- `com.sistemadepedidos.singleton`: conexión SQLite.
- `com.sistemadepedidos.observer`: