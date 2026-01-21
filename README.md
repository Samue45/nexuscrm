NexusCRM 🚀
===========

**NexusCRM** es una plataforma de gestión integral desarrollada con el ecosistema Java, utilizando **Spring Boot** para el backend y **Vaadin Flow** para una interfaz de usuario moderna y reactiva sin salir de Java. El sistema incluye gestión de usuarios, biblioteca personal y detalles técnicos de activos.

🛠️ Stack Tecnológico
---------------------

*   **Backend:** Java 17+, Spring Boot 3.x.
    
*   **Frontend:** Vaadin Flow (Componentes UI basados en Java).
    
*   **Persistencia:** Spring Data JPA / Hibernate.
    
*   **Base de Datos:** H2 / MySQL / PostgreSQL (Soporta múltiples via JPA).
    
*   **Utilidades:** Lombok, Jackson (JSON Mapping).
    

📂 Estructura del Proyecto
--------------------------

Basado en la arquitectura del repositorio:

*   model/: Entidades JPA (User, Book, Author, TechDetail).
    
*   view/: Vistas de la interfaz de usuario con Vaadin (MainView, LibraryView).
    
*   service/: Lógica de negocio y procesamiento de datos.
    
*   dto/: Objetos de transferencia de datos para desacoplar la vista del modelo.
    
*   repository/: Interfaces de acceso a datos (Spring Data Repositories).
    

✨ Funcionalidades Implementadas
-------------------------------

### 🔑 Sistema de Autenticación

Implementado en MainView.java, utiliza el componente LoginForm de Vaadin.

*   Validación de credenciales contra la base de datos.
    
*   Manejo de sesión mediante VaadinSession para persistir el usuario logueado.
    

### 📚 Gestión de Biblioteca Personal

Localizada en LibraryView.java, esta sección permite:

*   Visualización de libros en un Grid de alto rendimiento.
    
*   Uso de **DTOs** (BookDTO) para optimizar la carga de datos y evitar problemas de Lazy Loading.
    
*   Relaciones complejas: Muchos-a-Muchos entre Usuarios y Libros.
    

### ⚙️ Modelo de Datos Avanzado

El sistema cuenta con una estructura relacional robusta:

*   **Relación 1:1**: Libros con detalles técnicos (TechDetail).
    
*   **Relación N:1**: Libros asociados a un autor.
    
*   **Relación N:N**: Usuarios y sus colecciones de libros.
    

🚀 Configuración e Instalación
------------------------------

1.  Bashgit clone https://github.com/Samue45/nexuscrm.gitcd nexuscrm
    
2.  **Requisitos:**
    
    *   JDK 17 o superior.
        
    *   Maven 3.8+.
        
3.  Bashmvn spring-boot:run
    
4.  **Acceso:**Abre tu navegador en http://localhost:8080.
    

📝 Notas de Desarrollo
----------------------

*   **Lombok:** El proyecto requiere tener instalado el plugin de Lombok en tu IDE (IntelliJ/VSCode) para procesar las anotaciones @Getter, @Setter y @NoArgsConstructor.
    
*   **Seguridad:** Las vistas protegidas utilizan la anotación @PermitAll y requieren una sesión activa de Vaadin.
