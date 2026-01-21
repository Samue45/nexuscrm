NexusCRM 🚀
===========

**NexusCRM** es una plataforma de gestión integral desarrollada con el ecosistema Java. Utiliza **Spring Boot** para un backend robusto y **Vaadin Flow** para crear interfaces modernas y reactivas 100% en Java.

🧠 Consulta la Documentación con IA
-----------------------------------

¿Tienes dudas sobre cómo funciona el código o cómo extender una funcionalidad?

NexusCRM está integrado con DeepWiki, lo que te permite hacer preguntas en lenguaje natural sobre este repositorio:

👉 [**Preguntar a DeepWiki sobre NexusCRM**](https://deepwiki.com/Samue45/nexuscrm)

🛠️ Stack Tecnológico
---------------------

**CapaTecnologíasBackend**Java 17+, Spring Boot 3.x**Frontend**Vaadin Flow (UI reactiva en Java)**Persistencia**Spring Data JPA / Hibernate**Bases de Datos**H2, MySQL, PostgreSQL (Configurable via JPA)**Utilidades**Lombok, Jackson (JSON)

📂 Arquitectura y Estructura
----------------------------

El proyecto sigue un patrón de diseño limpio para separar la lógica de negocio de la interfaz de usuario:

*   model/: Entidades JPA (User, Book, Author, TechDetail).
    
*   view/: Interfaz de usuario con Vaadin (Login, LibraryView).
    
*   service/: Capa de servicios y lógica de negocio.
    
*   dto/: Objetos de transferencia para evitar problemas de _Lazy Loading_.
    
*   repository/: Abstracción de acceso a datos.
    

✨ Funcionalidades Destacadas
----------------------------

### 🔑 Sistema de Autenticación

*   Validación de credenciales contra base de datos.
    
*   Gestión de sesiones mediante VaadinSession.
    
*   Vistas protegidas con la anotación @PermitAll.
    

### 📚 Gestión de Activos (Biblioteca)

*   Visualización de datos en **Grids** de alto rendimiento.
    
*   Relaciones complejas de base de datos:
    
    *   **1:1**: Libros y sus detalles técnicos específicos.
        
    *   **N:1**: Clasificación por autores.
        
    *   **N:N**: Relación entre usuarios y sus colecciones personales.
        

🚀 Instalación y Uso Rápido
---------------------------

1.  Bashgit clone https://github.com/Samue45/nexuscrm.gitcd nexuscrm
    
2.  **Requisitos:** JDK 17+ y Maven 3.8+.
    
3.  Bashmvn spring-boot:run
    
4.  **Acceso:** Abre [http://localhost:8080](https://www.google.com/search?q=http://localhost:8080).
    

📝 Notas para Desarrolladores
-----------------------------

*   **Lombok:** Asegúrate de tener instalado el plugin en tu IDE para que las anotaciones @Getter/@Setter funcionen correctamente.
    
*   **Extensibilidad:** Gracias a JPA, puedes cambiar de una base de datos H2 (memoria) a una productiva (PostgreSQL/MySQL) simplemente editando el application.properties.
    

_Mantenido por_ [_Samuel_](https://www.google.com/search?q=https://github.com/Samue45)
