**Design Decisions**
* Each customer object contains a transaction array which contains all the transactions associated with this customer. Each transation is added to the array a purchase is done.
* VipProgram and CreditProgram classes are created to maintain the rules of how VIP status and credit program works. This is easy to maintain. If there is rule changes in either of the programs, it can be easily updated in the class.
* Customer class contains fields such as creditBalance, creditExpirationDate, isVipThisYear and isVipNextYear and totalYearlySpending to keep track of each customer's current Vip and credit status.Those values could be updated when a customer makes a purchase.  Vip status will be updated each year on Jan 1st.


**CartManager Class**
* addCustomer(Name, Email)
	* This method creates a Customer object in the system. It generates a 8-digit unique hexadecimal ID associated with the customer. It communitcates to CardPrinter by calling external libraries to print a customer card.
* processPurchase(Customer, total, CreditCard) 
	* This method uses the information it gets from VipProgram class and CreditProgram class to apply the discount from VIP program and use credit. If this transation qualifies to get credit, it will add the credit to customer object's "creditBalance" field and send an email to the customer by calling external libraries. It adds this purchase to the yearly total spending Transaction object and checks if the customer qualifies VIP status next year. and adds it to the cusomter's transaction list.