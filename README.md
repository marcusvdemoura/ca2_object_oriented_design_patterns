# ca2_object_oriented_design_patterns

CCT College Dublin



Module Title:
	Object Oriented Design Patterns
Assessment Title:
	CA 2 [30%] 
Lecturer Name:
	David McQuaid
Student Full Name:
	Marcus Vinicius de Freitas Moura
Student Number:
	2021247
Assessment Due Date:
	22/04/2022
Date of Submission:
	


Marcus Moura


1 – About the project

The assignment consists in designing and implementing an application that simulates sales of products between three companies: BIG-Alpha, BIG-Beta and BIG-Cappa.
Each company has a native (or internal) product. BIG-Alpha produces widgets, BIG-Beta produces braces and BIG-Cappa produces crates.
Each company has a fixed amount of 40 depots each. The depots store the products. 
Each depot must have a minimum of 15 native products and 2 external products and a maximum of 40 native and 30 externals. Within this range, the number of products is random and must vary. They also have an allowance. The initial allowance is between 50-100€ and must be randomly set.
The companies can trade with one another through their depots. They can sell and they can buy products but must respect the limitations of the number of products explained previously. Also, the allowance balance changes accordingly to the sales/purchases.

2 – Walkthrough 

2.1 – Products (Enum)

There’s a fixed list of products, therefore I chose to create an Enum class for them. It’s a basic class, and each item has a code and a description. There’s also a constructor for the elements, getters and a toEnum() function that accepts an Integer code and returns the Enum object that has that code.
 ![image](https://user-images.githubusercontent.com/63955638/161273983-8cdc8ba8-8afa-45d6-b906-a2d6ecb5e622.png)


2.2 – Company (Abstract class)

This abstract class will be the ‘blueprint’ of the classes for each company. The company class will have the variables and functions that’ll be shared across all companies that are created.
 
![image](https://user-images.githubusercontent.com/63955638/161274014-6e6a90a2-30e2-423c-b399-6c2af1ee5652.png)

2.3 – BigAlpha, BigBeta, BigCappa

Each of these classes will extend the company class.

Each of them will have a constructor based on the Company constructor.

Also, within the constructor, we’ll create the 40 depots for the specific company and set a random initial allowance balance for each depot.

Finally, at the end of the constructor, it’ll create two .txt files. One for the company and another for the transactions of the company.

 ![image](https://user-images.githubusercontent.com/63955638/161274044-b50cdf6d-ff86-4d1d-aed1-35509d819118.png)



2.4 – Depot

The depot will have attributes for its number, the allowance, the company it belongs to and which native product (must be the same as the company it belongs to).

It also has a Map for the products and its price, as it must vary for each depot, and a price for the delivery.

Finally, it has an ArrayList of Products stored.

 ![image](https://user-images.githubusercontent.com/63955638/161274080-2e88f606-e0ac-4d86-8562-ebae1a61549e.png)



As for its functions, besides the getters and setters, it has a sellProduct(Product p) and a buyProduct(Product p, Company c).

Selling a product requires a Product parameter. It’ll loop through the list of products it has and, if the minimum amount of native or external products allow, it’ll be sold. Also, when selling, the allowance balance is summed up with the value of the product.

It will return the product sold or, in case it’s not possible to sell, return null.

 
![image](https://user-images.githubusercontent.com/63955638/161274108-3a717663-03e5-45a7-b842-9f895f555688.png)


Buying a product requires two parameters: The product it wants to buy and the company it wants to buy from.
First, it defines if the product is a native for the buyer or not.

After that, it sets a few if statements to manage the limitations of the buying products. If it’s a native, the buyer must have less than 40 in the stock, or if it’s an external product, it needs less than 30. Then, looping through the depots that belong to the company the buyer wants to buy from, it’ll check which can sell. If possible to sell, it’ll write to a file the transactions and overwrite the company .txt file with the new balance and the new products in its depots. Whenever a purchase is possible it manages the allowance balance and a break is called to stop the loop.

 ![image](https://user-images.githubusercontent.com/63955638/161274127-b1736234-1b17-48b5-bfcc-4ae3edfb0f17.png)

 ![image](https://user-images.githubusercontent.com/63955638/161274155-aef67a70-cd83-4849-9241-1a5fe210ee03.png)

   ![image](https://user-images.githubusercontent.com/63955638/161274175-3543b516-242b-44c5-a940-1847c32f234d.png)


![image](https://user-images.githubusercontent.com/63955638/161274201-34d32cfc-90d5-4c5e-8863-02bb87b1dabe.png)




3 – Menu

The user has the option to see all transactions of the three companies, or to choose a company to: see its transactions, see its balance, and see the amount of native and external product is has in stock in each depot. 

![image](https://user-images.githubusercontent.com/63955638/161274268-4c0bd449-573c-4e3b-a5d0-2dca97ee8afc.png)

![image](https://user-images.githubusercontent.com/63955638/161274300-d672fd32-c86d-4f84-8488-36a76eafe58d.png)

![image](https://user-images.githubusercontent.com/63955638/161274342-58bdde5b-0323-40ca-ad47-59628c87ea68.png)



4 - UML 

 ![image](https://user-images.githubusercontent.com/63955638/161274387-99665411-d21e-4f61-a8fc-043f53db2f49.png)


