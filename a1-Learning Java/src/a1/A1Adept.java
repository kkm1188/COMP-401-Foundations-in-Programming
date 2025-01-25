package a1;

import java.util.Scanner;

public class A1Adept {

	public static void main(String[] args) {

		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);

		// Your code follows here.
		class Foods { 
			public String type;
			public double price; 

			Foods (String type, double price){
				this.type = type;
				this.price = price;
			}
		}

		int numitems = scan.nextInt();
		Foods[] foodsArray = new Foods[numitems];

		for (int i = 0; i < numitems; i++) {
			foodsArray[i] = new Foods(scan.next(), scan.nextDouble());
		}

		class People {
			public String firstname = "firstname";
			public String lastname = "lastname";
			public double totalprice = 0;

			People (String firstname, String lastname, double totalprice){
				this.firstname = firstname;
				this.lastname = lastname;
				this.totalprice = totalprice;
			}
		}

		int numpeople = scan.nextInt();
		People[] peopleArray = new People[numpeople];



		for (int r = 0; r < numpeople; r++) {
			String firstname = scan.next();
			String lastname = scan.next();
			peopleArray[r] = new People (firstname, lastname, 0);
			int numberitems = scan.nextInt();
			for(int j = 0; j < numberitems; j++) {
				int numspecificitem = scan.nextInt();
				String itemtype = scan.next();
				for (int o = 0; o < foodsArray.length; o++) {
					if (foodsArray[o].type.equals(itemtype)) {
						peopleArray[r].totalprice += (foodsArray[o].price * numspecificitem);
					}
				}
			}

		}

		People peoplemax = new People("i","i", 0);
		double max = 0;
		for (int n = 0; n < peopleArray.length; n++) {
			if (peopleArray[n].totalprice > max) {
				max = peopleArray[n].totalprice;
				peoplemax = peopleArray[n];
			} 
		}

		People peoplemin = new People("i", "o", 0);
		double min = 10000;
		for (int q = 0; q < peopleArray.length; q++) {
			if(peopleArray[q].totalprice < min) {
				min = peopleArray[q].totalprice;
				peoplemin = peopleArray[q];
			}
		}
		double sum = 0;
		for(int w = 0; w < peopleArray.length; w++) {
			sum += peopleArray[w].totalprice;
		}


		System.out.println("Biggest: " + peoplemax.firstname + " " + peoplemax.lastname + " (" + String.format("%.2f", max) + ")");
		System.out.println("Smallest: " + peoplemin.firstname + " " + peoplemin.lastname + " (" + String.format("%.2f", min) + ")");
		System.out.println("Average: " + String.format("%.2f", ((double) sum) / ((double) peopleArray.length)));

	}
}

