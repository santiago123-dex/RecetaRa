# RecetaRa
<!-- Reto de Programación: "Plataforma de Recetas Compartidas" (Full-Stack)
¡Genial, cambiemos de enfoque! Te propongo un nuevo reto: una Plataforma de Recetas Compartidas, como un Pinterest para cocineros aficionados. Los usuarios podrán subir recetas, buscar por ingredientes, guardar favoritos y comentar en las de otros. Es ideal para practicar CRUD, búsquedas, autenticación y algo de multimedia.
Tecnologías sugeridas (elige según tu stack):

Backend: Node.js + Express (API REST), PostgreSQL o MongoDB para datos, JWT para auth. Opcional: Cloudinary para subir imágenes.
Frontend: React con Axios para API calls, y librerías como React Router para navegación.
Despliegue: Vercel para todo (fácil integración full-stack).

El reto se centra en cumplir estos requisitos funcionales y no funcionales. Construye la app paso a paso, probando cada uno al final. Al terminar, tendrás un proyecto deployado con un README que explique cómo usarlo.
Requisitos Funcionales (Lo que la app DEBE hacer):

Autenticación de Usuarios:
Registro de nuevo usuario con email, contraseña y nombre de usuario (hash de contraseñas obligatoria).
Login con validación de credenciales; genera un token JWT que se almacena en el frontend (localStorage o cookies).
Logout que borra el token y redirige al login.
Middleware para proteger rutas/endpoints privados (solo usuarios logueados acceden a sus datos).

Gestión de Recetas (CRUD Completo):
Crear receta: Formulario con título, descripción, lista de ingredientes (array editable), pasos (array de texto), tiempo de preparación, dificultad (baja/media/alta) y foto opcional (subida a cloud o base64).
Listar recetas: Vista principal con paginación (máx. 10 por página) y búsqueda por título o ingrediente (usa queries en backend).
Ver detalle de receta: Muestra toda la info, incluyendo foto, y permite editar/eliminar solo si es tuya.
Eliminar receta: Confirmación antes de borrar permanentemente.

Interacciones Sociales:
Guardar favoritos: Un usuario puede "like" o guardar una receta (no suya); muestra contador de likes en la lista.
Comentarios: Agregar/eliminar comentarios en una receta (solo texto, con autor y fecha); lista de comentarios en la vista de detalle.
Perfil de usuario: Vista personal con lista de mis recetas, mis favoritos y mis comentarios.

Búsqueda y Filtros:
Barra de búsqueda global por palabras clave (título, ingredientes o descripción).
Filtros en la lista principal: Por dificultad, tiempo (<30min, 30-60min, >60min) o popularidad (más likes).


Requisitos No Funcionales (Calidad y Seguridad):

Seguridad:
Validación de inputs en backend (e.g., no campos vacíos, emails únicos).
Rate limiting en login/registro para evitar spam (usa express-rate-limit).
Tokens JWT con expiración (e.g., 1 hora) y refresh opcional.

Interfaz y UX:
Diseño responsive (mobile-first): Usa CSS modules o Tailwind; tarjetas para recetas con imagen destacada.
Estados de carga: Spinners o placeholders mientras se cargan datos de API.
Manejo de errores: Mensajes amigables (e.g., "Receta no encontrada") con toasts o alerts.
Accesibilidad básica: Alt texts en imágenes, navegación por teclado.

Rendimiento y Estructura:
API RESTful: Endpoints como /api/recipes (GET/POST), /api/users/:id/profile (GET).
Base de datos relacional o NoSQL: Relaciona recetas con usuarios (e.g., foreign keys o refs).
Código limpio: Estructura en carpetas (models, routes, components); usa async/await para promesas.
Tests mínimos: Al menos 3 unit tests (e.g., uno para crear receta, uno para auth, uno para búsqueda).

Despliegue y Documentación:
App deployada públicamente (e.g., Vercel con backend integrado).
README en GitHub: Instrucciones para clonar, instalar dependencias, correr local (npm run dev) y seed de datos de ejemplo.
Datos de prueba: Al menos 5 recetas pre-cargadas para demo.


Consejos para el Reto:

Empieza por el backend (modelos y auth), luego integra frontend. Usa Postman para probar APIs.
Si quieres escalar: Agrega categorías de recetas o exportar a PDF, pero enfócate en lo esencial primero.
Trackea con Git: Branches por feature (e.g., git checkout -b feat-search). -->