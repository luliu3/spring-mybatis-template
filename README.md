# spring-mybatis-template
spring mvc + mybatis + mysql + druid

# Requirements

- Spring 4.3.2.RELEASE
- Maven 3
- Tomcat 9
- Servlet 3
- [Postman](http://www.getpostman.com/) (optional): to build POST/PUT/DELETE request

# Installation

- Git clone https://github.com/luliu3/spring-restful-demo
- Use Maven build and package it
- Deploy RESTDemo.war to your Tomcat webapp directory `your_tomcat_dir/Tomcat 9.0/webapps`

# Usage

## Paste the following link into your browser

- GET:  `http://localhost:8080/rest/user`
- GET:  `http://localhost:8080/rest/user/1`

## Use Postman to do following test

- POST: `http://localhost:8080/rest/user/new`
with body
```
{

    "id": 100,
    "firstname": "lu",
    "lastname": "liu",
    "email": "luliu3@iflytek.com",
    "mobile": "131-0000-0000"

}
```
- PUT: `http://localhost:8080/rest/user/update/100`
with body
```
{

    "id": 100,
    "firstname": "lu100",
    "lastname": "liu100",
    "email": "luliu100@iflytek.com",
    "mobile": "131-1111-1111"

}
```
- DEL: `http://localhost:8080/rest/user/delete/100`
- Pagination: `http://localhost:8080/rest/user/page?low=1&high=10`