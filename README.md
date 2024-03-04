# OpenAPI-Differ

**!!! Development is discontinued in favor of [OpenAPITools:openapi-diff](https://github.com/OpenAPITools/openapi-diff) !!!**

<hr>

Compare two OpenAPI specifications(**3.x**) and render the difference to html file or markdown file.

# Requirements
`jdk1.8+`

# Feature
* Supports OpenAPi spec v3.0.
* Depth comparison of parameters, responses, endpoint, http method(GET,POST,PUT,DELETE...)
* Supports swagger api Authorization
* Render difference of property with Expression Language
* html & markdown render

# Maven

```xml
<dependency>
    <groupId>io.github.plipp</groupId>
    <artifactId>openapi-differ</artifactId>
    <version>1.0.0</version>
</dependency>
```

# Usage
OpenDiff can read swagger api spec from json file or http.

## Using the API

see `io.github.plipp.openapi.test.OpenApiDiffTest#testNewApi`.

### Render difference

---
#### HTML
```java
String html = new HtmlRender("Changelog",
        "http://deepoove.com/swagger-diff/stylesheets/demo.css")
                .render(diff);

try {
    FileWriter fw = new FileWriter(
            "testNewApi.html");
    fw.write(html);
    fw.close();

} catch (IOException e) {
    e.printStackTrace();
}
```

#### Markdown
```java
String render = new MarkdownRender().render(diff);
try {
    FileWriter fw = new FileWriter(
            "testDiff.md");
    fw.write(render);
    fw.close();
    
} catch (IOException e) {
    e.printStackTrace();
}
```

### Extensions
This project uses Java Service Provider Inteface (SPI) so additional extensions can be added. 

To build your own extension, you simply need to create a `src/main/resources/META-INF/services/io.github.plipp.openapi.diff.compare.ExtensionDiff` file with the full classname of your implementation.  Your class must also implement the `io.github.plipp.openapi.diff.compare.ExtensionDiff` interface.  Then, including your library with the `openapi-diff` module will cause it to be triggered automatically.

# Example
### CLI Output

```text
==========================================================================
==                            API CHANGE LOG                            ==
==========================================================================
                             Swagger Petstore                             
--------------------------------------------------------------------------
--                              What's New                              --
--------------------------------------------------------------------------
- GET    /pet/{petId}

--------------------------------------------------------------------------
--                            What's Deleted                            --
--------------------------------------------------------------------------
- POST   /pet/{petId}

--------------------------------------------------------------------------
--                          What's Deprecated                           --
--------------------------------------------------------------------------
- GET    /user/logout

--------------------------------------------------------------------------
--                            What's Changed                            --
--------------------------------------------------------------------------
- PUT    /pet
  Request:
        - Deleted application/xml
        - Changed application/json
          Schema: Backward compatible
- POST   /pet
  Parameter:
    - Add tags in query
  Request:
        - Changed application/xml
          Schema: Backward compatible
        - Changed application/json
          Schema: Backward compatible
- GET    /pet/findByStatus
  Parameter:
    - Deprecated status in query
  Return Type:
    - Changed 200 OK
      Media types:
        - Changed application/xml
          Schema: Broken compatibility
        - Changed application/json
          Schema: Broken compatibility
- GET    /pet/findByTags
  Return Type:
    - Changed 200 OK
      Media types:
        - Changed application/xml
          Schema: Broken compatibility
        - Changed application/json
          Schema: Broken compatibility
- DELETE /pet/{petId}
  Parameter:
    - Add newHeaderParam in header
- POST   /pet/{petId}/uploadImage
  Parameter:
    - Changed petId in path
- POST   /user
  Request:
        - Changed application/json
          Schema: Backward compatible
- POST   /user/createWithArray
  Request:
        - Changed application/json
          Schema: Backward compatible
- POST   /user/createWithList
  Request:
        - Changed application/json
          Schema: Backward compatible
- GET    /user/login
  Parameter:
    - Delete password in query
- GET    /user/logout
- GET    /user/{username}
  Return Type:
    - Changed 200 OK
      Media types:
        - Changed application/xml
          Schema: Broken compatibility
        - Changed application/json
          Schema: Broken compatibility
- PUT    /user/{username}
  Request:
        - Changed application/json
          Schema: Backward compatible
--------------------------------------------------------------------------
--                                Result                                --
--------------------------------------------------------------------------
                 API changes broke backward compatibility                 
--------------------------------------------------------------------------
```

### Markdown
```markdown
### What's New
---
* `GET` /pet/{petId} Find pet by ID

### What's Deleted
---
* `POST` /pet/{petId} Updates a pet in the store with form data

### What's Deprecated
---
* `GET` /user/logout Logs out current logged in user session

### What's Changed
---
* `PUT` /pet Update an existing pet  
    Request

        Deleted request body : [application/xml]
        Changed response : [application/json]
* `POST` /pet Add a new pet to the store  
    Parameter

        Add tags //add new query param demo
    Request

        Changed response : [application/xml]
        Changed response : [application/json]
* `GET` /pet/findByStatus Finds Pets by status  
    Parameter

    Return Type

        Changed response : [200] //successful operation
* `GET` /pet/findByTags Finds Pets by tags  
    Return Type

        Changed response : [200] //successful operation
* `DELETE` /pet/{petId} Deletes a pet  
    Parameter

        Add newHeaderParam
* `POST` /pet/{petId}/uploadImage uploads an image for pet  
    Parameter

        petId Notes ID of pet to update change into ID of pet to update, default false
* `POST` /user Create user  
    Request

        Changed response : [application/json]
* `POST` /user/createWithArray Creates list of users with given input array  
    Request

        Changed response : [application/json]
* `POST` /user/createWithList Creates list of users with given input array  
    Request

        Changed response : [application/json]
* `GET` /user/login Logs user into the system  
    Parameter

        Delete password //The password for login in clear text
* `GET` /user/logout Logs out current logged in user session  
* `PUT` /user/{username} Updated user  
    Request

        Changed response : [application/json]
* `GET` /user/{username} Get user by user name  
    Return Type

        Changed response : [200] //successful operation
```

# License
openapi-differ is released under the Apache License 2.0.

# Thanks

* [Quentin Desramé](https://github.com/quen2404/) for his project [openapi-diff](https://github.com/quen2404/openapi-diff) 
  who was the originator of this tool
