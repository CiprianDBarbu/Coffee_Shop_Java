# Coffee Shop unsing Java
## Introduction
This project is presenting an app which could be useful for the workers of a Coffee Shop. The focus of this app is on the Orders and Coffees and not on the Client itself.
When run, check [localhost:8080](localhost:8080/) for calls, or try using Postman!

## Models
**Coffee**
This is the roasted coffe, used to brew a nice cup of coffee
- Id
- Name
- Price

**Syrup**
This is the syrups that can be added in any Brewed coffee
- Id
- Name 
- Price

**Brewed Coffee**
This is the equivalent of a cup of coffee. This contains a Coffee instance, a Syrup and can even have milk in it!
- Id
- Name
- Coffee
- Syrup
- Milk (as boolean)
- Final price

**Order**
An order is placed by a client and it has a list of Brewed Coffees
- Id
- List of Brewed Coffees
- Client
- Final price (the price of all coffees) (if you have more than 3 coffees you can opt for a discount of 20%!)

**Client**
The person who places one or more orders
- Id
- Name


## The database
Check the image for the database
Coffee - Brewed Coffee: One to one
Syrup - Brewed Coffee: One to one
Client - Order: One to many
Brewed Coffee - Order: Many to many
![image](https://user-images.githubusercontent.com/48891481/149215072-6f89f83a-8a67-4ba4-af6c-805f8b9d4bdb.png)

## The available calls
CRUD calls were implemented for all the entities

**Order**
- `/new`: create a new Order, requiers ClientId via Params
- `/{orderId}/addBrewedCoffee`: adds a brewed coffee to an order, requiers brewedCoffeeId via Params
- `/{orderId}/addDiscount`: checks if there are at least 3 coffees in the Order, and adds a 20% commision to the Final Price
- `/discounts`: lists all the orders with discounts

**Brewed Coffee**
- `/list/byName`: lists all the Brewed Coffees containing one specific Coffee, requiers the name of the Coffee via Body
