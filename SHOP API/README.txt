
Technologies used: springboot, JPA, Web, maven and Vaadin


There are a total of five entities each with their own repository and controller. Product has a one to many relationship with Attribute, Category and Store. Attribute has a one to many relationship with values.

This means each product can have multiple categories, be in multiple stores each with its availability, and hast multiple attributes that can be assigned dynamically with value EX: attribute can be color and it can have whatever value.


You can run the program with an IDE or alternitevly in the console enter “$ mvn spring-boot:run”

Before that one must set in application.properties the datasource.url
						                 .username
						                 .password
   
This sample application runs by loading some sample data and deletes data after stopping the program.

Everything is accessed through http://localhost:8080

UI instructions: To access it only add the following at the end: /UI

API instructions: It is pageable if one adds the following at the end: 
?page={page_number}&size={size_of_entities}&sort={variable},{order}
Example: /api/products?page=0&size=2&sort=productName,desc

Product: 

Takes JSON Data as following:
	{ 
	    "productName": "String",
            "productNumber": "String",
            "price": Double
					}

GET: /api/products

POST: /api/products

PUT: /api/products/{productId}

DELETE: /api/products/{productId}

Category: 

Takes JSON Data as following: 

	{ 
	    "categoryName": "String"
					}

GET: /api/products/{productId}/categories

POST: /products/{productId}/categories

PUT: /api/products/{productId}/categories/{categoryId}

DELETE: /api/products/{productId}/categories/{categoryId}

Store: 

Takes JSON Data as following: 

	{ 
	    "storeName": "String",
            "address": "String",
            "availability": Integer
					}

GET: /api/products/{productId}/stores

POST: /products/{productId}/stores

PUT: /api/products/{productId}/stores/{storesId}

DELETE: /api/products/{productId}/stores/{storeId}

Attribute: 

Takes JSON Data as following: 

	{ 
	    "attributeName": "String"
					}

GET: /api/products/{productId}/attributes

POST: /products/{productId}/attributes

PUT: /api/products/{productId}/attributes/{attributeId}

DELETE: /api/products/{productId}/attributes/{attributeId}

Value: 

Takes JSON Data as following: 

	{ 
	     "value": "String"

				}

GET: /api/products/{productId}/attributes/{attributeId}/values

POST: /products/{productId}/attributes/{attributeId}/values

PUT: /api/products/{productId}/attributes/{attributeId}/values/{valueId}

DELETE: /api/products/{productId}/attributes/{attributeId}/values/{valueId}








