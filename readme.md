# Documentación Book REST API

API Rest para operaciones CRUD sobre libros.

## Endpoints Disponibles

### Listar Libros

- **URL:** `/api/books`
- **Método HTTP:** GET
- **Descripción:** Recupera una lista de libros disponibles.
- **Respuesta Exitosa:** Código de estado HTTP 200 OK
- **Respuesta de Ejemplo:**
  ```json
  [
    {
      "id": 1,
      "name": "Nombre del Libro",
      "author": "Nombre del Autor",
      "edition_date": "2023-08-21"
      "codeBook" : "07122021"
    },
    // Otros libros...
  ]

### Obtener Detalles de un Libro por Id

- **URL:** `/api/books/{id}`
- **Método HTTP:** GET
- **Descripción:** Obtiene detalles de un libro específico utilizando su ID.
- **Parámetros de URL:**
  - `id` (Long) - ID del libro que se quiere obtener.
- **Respuesta Exitosa:** Código de estado HTTP 200 OK
- **Respuesta de Ejemplo:**
  ```json
  {
    "id": 1,
    "name": "Nombre del Libro",
    "author": "Nombre del Autor",
    "editionDate": "2023-12-13",
    "codeBook": "0721351"
  }

### Agregar un Nuevo Libro

- **URL:** `/api/books`
- **Método HTTP:** POST
- **Descripción:** Agrega un nuevo libro a la base de datos.
- **Respuesta Exitosa:** Código de estado HTTP 201 Created
- **Cuerpo de la Solicitud (JSON):**
  ```json
  {
    "name": "Nuevo Libro",
    "author": "Nuevo Autor",
    "editionDate": "2023-08-21"
    "codeBook" : "07122021"
  }

### Actualizar un Libro Existente

- **URL:** `/api/books`
- **Método HTTP:** PUT
- **Descripción:** Actualiza los detalles de un libro existente
- **Respuesta Exitosa:** Código de estado HTTP 200 OK
- **Cuerpo de la Solicitud (JSON):**
  ```json
  {
    "id": 1002,
    "name": "Nuevo Libro",
    "author": "Nuevo Autor",
    "editionDate": "2023-08-21"
    "codeBook" : "07122021"
  }

### Eliminar un Libro

- **URL:** `/api/books/{id}`
- **Método HTTP:** DELETE
- **Descripción:** Elimina un libro de la base de datos utilizando su ID.
- **Parámetros de URL:**
  - `id` (Long) - ID del libro que se quiere eliminar.
- **Respuesta Exitosa:** Código de estado HTTP 200 OK

- **Respuesta de Ejemplo:**
  ```json
  {
    The book has been successfully deleted
  }

### Buscar Libro por nombre o autor

-- **URL:** `/api/books/getBy`
- **Método HTTP:** GET
- **Descripción:** Busca un Libro en la base de datos por su nombre o autor (sino que busque por parte de su nombre o autor)
- **Parámetros de URL:**
  - getBy?keyword= `clave`
- **Respuesta Exitosa:** Código de estado HTTP 200 OK

- **Respuesta de Ejemplo:**
  ```json
  {
    "id": 1002,
    "name": "Nuevo Libro",
    "author": "Nuevo Autor",
    "editionDate": "2023-08-21"
    "codeBook" : "07122021"
  }

### Buscar Libro por codigo

-- **URL:** `/api/books/getByCode`
- **Método HTTP:** GET
- **Descripción:** Busca un Libro en la base de datos segun su codigo.
- **Parámetros de URL:**
  - getBy?code= `codigo`
- **Respuesta Exitosa:** Código de estado HTTP 200 OK

- **Respuesta de Ejemplo:**
  ```json
  {
    "id": 1002,
    "name": "Nuevo Libro",
    "author": "Nuevo Autor",
    "editionDate": "2023-08-21"
    "codeBook" : "07122021"
  }