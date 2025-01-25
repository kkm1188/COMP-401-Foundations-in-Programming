package a3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleHistogram {

	@SuppressWarnings("null")
	public static void main(String[] args) {

		// Set up scanner for input from console.

		Scanner scan = new Scanner(System.in);

		// Your code here.

		int numBins = scan.nextInt();

		class Numbers{
			int number;
			int numberofnumbers;
			boolean alreadySeen = false;
			String stars = "";

			Numbers(int number, int numberofnumbers){
				this.number = number;
				this.numberofnumbers = numberofnumbers;
			}
		}

		List<Numbers> numbersList = new ArrayList();

		while(scan.hasNextInt()) {
			numbersList.add(new Numbers (scan.nextInt(), 1));
		}

		List<Numbers> numbersListFinal = new ArrayList();

		for(int er = 0; er < numbersList.size(); er++) {
			if(er != 0) {
				for(int e = 0; e < er; e++) {
					if(numbersList.get(e).number == numbersList.get(er).number) {
						numbersList.get(er).alreadySeen = true;
					} 
				} if (!numbersList.get(er).alreadySeen) {
					numbersListFinal.add(numbersList.get(er));
				} else {
					for(int y = 0; y < numbersListFinal.size(); y++) {
						if(numbersListFinal.get(y).number == numbersList.get(er).number) {
							numbersListFinal.get(y).numberofnumbers += 1;
						}
					}
				}
			} else {
				numbersListFinal.add(numbersList.get(er));
			}
		}



		Numbers maxNumber = new Numbers (0, 0);
		for(int y = 0; y < numbersListFinal.size(); y++) {
			if(numbersListFinal.get(y).number > maxNumber.number) {
				maxNumber = numbersListFinal.get(y);
			}
		}
		Numbers minNumber = new Numbers (1000, 0);
		for(int x = 0; x < numbersListFinal.size(); x++) {
			if(numbersListFinal.get(x).number < minNumber.number) {
				minNumber = numbersListFinal.get(x);
			}
		}
		double range = maxNumber.number - minNumber.number;
		double binRange = range/numBins;

		int starworth = maxNumber.numberofnumbers;
		
		for(int c = 0; c < numbersListFinal.size(); c++) {
			for(int n = 0; n < numbersListFinal.get(c).numberofnumbers/starworth; n++) {
				numbersListFinal.get(c).stars += "*";
			}
		}
		
		double total = 0;
		double totalnumbers = 0;
		for(int d = 0; d < numbersListFinal.size(); d++) {
			total += numbersListFinal.get(d).number * numbersListFinal.get(d).numberofnumbers;
			totalnumbers += numbersListFinal.get(d).numberofnumbers;
		}
		double average = total/totalnumbers;
		
		
		System.out.println("Num bins: " + numBins);
		System.out.println("Min: " + minNumber.number);
		System.out.println("Max: " + maxNumber.number);
		System.out.println("Avg: " + average);
		
		
		
		for(int r = 0; r < numBins + 1; r++) {
			String numstars = "";
			int totalnum = 0;
			System.out.println((minNumber.number + (int)((r * binRange) + 0.5) + "|"));
			for(int v = 0; v < numbersListFinal.size(); v++) {
				if(numbersListFinal.get(v).number >= (minNumber.number + (r * binRange)) && numbersListFinal.get(v).number < (minNumber.number + ((r + 1) * binRange))) {
					numstars += numbersListFinal.get(v).stars;
					totalnum += numbersListFinal.get(v).numberofnumbers;
				}
			}
			System.out.println("  |" + numstars + "(" + totalnum + ")");
		}
	}
}
