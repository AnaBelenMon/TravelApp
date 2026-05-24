Proyecto TravelApp
Autor: Ana Belén Montilla López
Curso: 1º DAM
Módulo: Programación
Centro: IES Francisco de los Ríos

Descripción del proyecto:
TravelApp es una aplicación de escritorio desarrollada en Java 
cuyo objetivo es ayudar al usuario a planificar, organizar y gestionar viajes 
desde una perspectiva emocional y cultural.

Permite:
- Crear y gestionar viajes
- Añadir actividades asociadas
- Registrar documentos importantes
- Valorar experiencias
- Guardar notas, precios, fechas y lugares
- Configurar la conexión a la base de datos mediante XML
La aplicación sigue el patrón MVC, utiliza JavaFX para la interfaz gráfica,
MySQL como base de datos y Maven como sistema de construcción.

Tecnologías utilizadas:
- Java 17
- JavaFX
- MySQL
- Maven
- JDBC
- JAXB 
- SceneBuilder
- Patrón MVC

Estructura del proyecto:
/src
 ├── model        → Clases del modelo (Viaje, Actividad, Documento…)
 ├── dao          → DAOs y acceso a datos
 ├── controller   → Controladores JavaFX
 └── view         → Archivos FXML
