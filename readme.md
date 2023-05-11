# Intro to graphQL

This is a demo project for getting started with graphql. In this project there is `create`, `read` and `delete` operations.
**Implementing the update operation is left for your self practice.**

**This Project also includes the tests that covers 100% of the codebase.**

We didn't use an actual database though, we have used a fake in memory database(Simple data structures in this case - `List<Author> authors` for Authors and `List<Book> books` for Books).

This database will be empty when the application starts, and also will be removed if the application is stopped. So, restarting your application would get you a new fresh empty database everytime.

**This project has three packages:**.

### 1. controller package
In this package, there are two controllers, there are two types of mapping in this project: `@QueryMapping` and `@MutationMapping`.
`@QueryMapping` can be compared with the `GET` request, though all the requests of graphql is `POST` request, and
`@MutationMapping` can be compared with the `POST` request.

`AuthorController.java` controls the Author related request-response and the `BookController.java` controls the Book related request-response.

### 2. model package
In this package, there are two types of data model, one is `Author` and another is `Book`. These models hold the data of the Book and the Author.

### 3. service package
In this package, there are two `Interface` named `AuthorService` and `BookService`. They both hold the functionality of what a user can do with these.
We define the business logic, or the service we want to get with this model and controller.

## Sending request
We can use postman or the `graphiql` interface. Graphiql interface is a default graphical user interface to work with graphql request and response.
To enable the graphiql interface for a graphql project, we need to enable it from the `application.properties` file of our project. To enable it, we
need to add `spring.graphql.graphiql.enabled=true` in our `application.properties` file.

After running the application, the graphiql can be accessed via `http://localhost:8080/graphiql` url.

---
# Author related queries
These are the queries this project supports for Authors.

## Sample query for adding author:
```graphql
mutation{
  addAuthor(firstName: "Kazi", lastName: "Afsana")
}
```

## Sample query for removing author by name:
```graphql
mutation{
    removeAuthor(firstName: "kazi", lastName: "Afsana")
}
```

## Sample query for getting author by name:
```graphql
{
  author(firstName: "Kazi", lastName: "Afsana"){
    id
    firstName
    lastName
  }
}
```

## Sample query for all authors:
```graphql
{
  authors{
    id
    firstName
    lastName
  }
}
```

---
# Book related queries
These are the queries this project supports for Books.

## Sample query for adding book

```graphql
mutation {
  addBook(name: "101 ways to bake", pageCount: 290, authorFirstName: "Kazi")
}
```

## Sample query for removing a book
```graphql
mutation {
    removeBook(name: "101 ways to bake")
}
```
## Sample query for getting a book
```graphql
{
    book(name: "101 ways to bake"){
        id
        name
        pageCount
        author{
            id
            firstName
            lastName
        }
    }
}
```
## Sample query for getting all books
```graphql
{
   books{
  	  id
  	  name
  	  pageCount
  	  author{
  	      id
  	      firstName
  	      lastName
      }
   } 
}
```
---
**Feel free to suggest any changes. Happy coding! ❤**

You can reach me on LinkedIn from [here](https://bd.linkedin.com/in/piyalahmed).