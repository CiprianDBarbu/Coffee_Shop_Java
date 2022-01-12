# Coffee Shop unsing Java
##Introduction
This project is presenting an app which could be useful for the workers of a Coffee Shop. The focus of this app is on the Orders and Coffees and not on the Client itself.

##Models
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
