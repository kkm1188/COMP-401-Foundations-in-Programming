package a1;

import java.util.Scanner;

public class A1Jedi {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in); 

		// Your code follows here.

		

		class Foods {
			String foodtype;  
			double price;
			int numberbought;
			int numcustomers;
			boolean[] customerHasBought;

			Foods (String foodtype, double price, int numberbought, int numcustomers){
				this.foodtype = foodtype;
				this.price = price;
				this.numberbought = numberbought;
				this.numcustomers = numcustomers;

			}
		}
		int numfoods = scan.nextInt();
		Foods[] foodsArray = new Foods[numfoods];

		for (int i = 0; i < numfoods; i++) {
			String items = scan.next();
			double itemprice = scan.nextDouble();
			foodsArray[i] = new Foods(items, itemprice, 0, 0);
		} 

		class People {
			String firstname;
			String lastname;
			int numberitems;

			People (String firstname, String lastname, int numberitems) {
				this.firstname = firstname;
				this.lastname = lastname;
				this.numberitems = numberitems;
			}
		}
		int numpeople = scan.nextInt();
		People[] peopleArray = new People[numpeople];
		
		for(int g = 0; g < numfoods; g++) {
			foodsArray[g].customerHasBought = new boolean [numpeople];
		}

		for (int t = 0; t < foodsArray.length; t++) {
			for (int u = 0; u < numpeople; u++) {
				foodsArray[t].customerHasBought[u] = false;
			}
		}

		// looping between people
		for (int x = 0; x < numpeople; x++) {
			String firstname = scan.next();
			String lastname = scan.next();
			int numberitems = scan.nextInt();
			peopleArray[x] = new People(firstname, lastname, numberitems);

			// looping between number items bought for each person
			for (int y = 0; y < numberitems; y++) {
				int numberspecificfood = scan.nextInt();
				String foodname = scan.next();

				//looping through foods array to find matching string
				for (int v = 0; v < foodsArray.length; v++) {
					if (foodsArray[v].foodtype.equals(foodname)) {

						foodsArray[v].numberbought += numberspecificfood;
						foodsArray[v].customerHasBought[x] = true;

					}
				}
			} 
		}
		for (int y = 0; y < foodsArray.length; y++) {
			for (int e = 0; e < numpeople; e++) {
				if (foodsArray[y].customerHasBought[e]) {
					foodsArray[y].numcustomers += 1;
				}
			}
		}

		for (int n = 0; n < foodsArray.length; n++) {
			if (foodsArray[n].numcustomers != 0){
				System.out.println(foodsArray[n].numcustomers + " customers bought " + foodsArray[n].numberbought + " " + foodsArray[n].foodtype);
			} else {
				System.out.println("No customers bought " + foodsArray[n].foodtype);
			}
		}
	}
}


