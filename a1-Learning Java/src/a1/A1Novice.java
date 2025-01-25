package a1;

import java.util.Scanner;

public class A1Novice {
	
	public static void main(String[] args) {

		// Your code follows here.
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		
		int numpeople = scan.nextInt();	
		
		
		
		for (int i = 0; i < numpeople; i++) {
			String firstname = scan.next();
			String lastname = scan.next();
			int totalitems = scan.nextInt();
			double totalprice = 0;
			for(int r = 0; r<totalitems; r++) {
				int numobjects = scan.nextInt();
				@SuppressWarnings("unused")
				String foodtype = scan.next();
				double price = scan.nextDouble();
				totalprice += numobjects * price;
			}
			
			System.out.println (firstname.charAt(0)+". " + lastname + ": " + String.format("%.2f", totalprice));
		}
	}
}

