# Documentación Book REST API

API Rest para operaciones CRUD sobre libros.
Tecnologias:
Lenguaje Java
Framework : Spring Boot
Base de datos : MySQL y H2. Se desarrollo usando MySQL pero tambien se configuro H2 ya que es una base de datos en memoria
y facilita las pruebas, con solo ejecutar el proyecto ya se puede consumir los endpoints. En el caso de querer utilizar MySQL hay que modificar el application.properties con los datos de conexion.



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
      "edition_date": "2023-08-21"
      "codeBook" : "07122021",
      "authorDto": {
            "id" : 1
            "name_author" : "nombre del autor"
            "id_books" : [
                  1
              ]
           }
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
      "edition_date": "2023-08-21"
      "codeBook" : "07122021",
      "authorDto": {
            "id" : 1
            "name_author" : "nombre del autor"
            "id_books" : [
                  1
              ]
      }

  }

### Agregar un Nuevo Libro

- **URL:** `/api/books`
- **Método HTTP:** POST
- **Descripción:** Agrega un nuevo libro a la base de datos.
- **Respuesta Exitosa:** Código de estado HTTP 201 Created
- **Cuerpo de la Solicitud (JSON):**
  ```json
  {
    "name" : "Nombre del libro",
    "editionDate" : "2023-12-12",
    "codeBook" : "111111111",
    "authorDto" : {
           "id" : 1
       }
  }

### Actualizar un Libro Existente

- **URL:** `/api/books`
- **Método HTTP:** PUT
- **Descripción:** Actualiza los campos que desees de un libro existente
- **Respuesta Exitosa:** Código de estado HTTP 200 OK
- **Cuerpo de la Solicitud (JSON):**
  ```json
  {
    {
        "id": 1,
        "name": "nuevo nombre",
        "editionDate": "2021-12-12",
        "codeBook": "1111111",
        "authorDto": {
            "id": 1
    }
}
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
  [
    {
        "id": 1,
        "name": "nombre del libro",
        "editionDate": "2023-12-12",
        "codeBook": "1111111",
        "authorDto": {
            "id": 1,
            "name_author": "nombre del autor",
            "idBooks": [
                1
            ]
        }
    }
]

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
        "id": 1,
        "name": "nombre del libro",
        "editionDate": "2023-12-12",
        "codeBook": "7",
        "authorDto": {
            "id": 1,
            "name_author": "nombre del autor",
            "idBooks": [
                1
            ]
        }
    }