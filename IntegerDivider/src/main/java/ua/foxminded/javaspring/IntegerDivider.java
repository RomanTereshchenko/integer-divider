package ua.foxminded.javaspring;

import java.util.Collections;

public class IntegerDivider {

	public static String divideIntegers(int divident, int divisor) {

		if (divisor == 0) {
			throw new IllegalArgumentException("Can't divide by zero");
		} else if (divident == 0) {
			return (new StringBuilder().append("_" + 0 + "\t|" + divisor + "\n " + 0 + "\t|-" + "\n\t|" + 0).toString());
		} else if (divident < divisor) {
			throw new IllegalArgumentException("Divident must be greater than divisor");
		} else {

			String dividentString = Integer.toString(divident);
			int[] dividentArr = new int[dividentString.length()];
			for (int i = 0; i < dividentString.length(); i++) {
				dividentArr[i] = dividentString.charAt(i) - '0';
			}

			Integer initialTempDivident = dividentArr[0];
			while (initialTempDivident < divisor) {
				initialTempDivident = initialTempDivident * 10 + dividentArr[initialTempDivident.toString().length()];
			}

			int quotient = initialTempDivident / divisor;
			int digitAddedToQuotient = 0;
			int tempDivident = initialTempDivident;

			StringBuilder[] blocksArr = new StringBuilder[dividentArr.length];

			blocksArr[0] = new StringBuilder().append("_" + divident + "\t|" + divisor + "\n " + (quotient * divisor)
					+ "\t|-----" + "\n "
					+ String.join("", Collections.nCopies((initialTempDivident.toString().length()), "-")) + "\t|");

			for (int i = 1; i <= (dividentArr.length - initialTempDivident.toString().length()); i++) {
				tempDivident = tempDivident % divisor * 10
						+ (dividentArr[initialTempDivident.toString().length() + i - 1]);
				digitAddedToQuotient = tempDivident / divisor;
				quotient = quotient * 10 + digitAddedToQuotient;
				blocksArr[i] = new StringBuilder().append(String.join("", Collections.nCopies(i - 1, " ")) + "_"
						+ tempDivident + "\n " + String.join("", Collections.nCopies(i - 1, " "))
						+ (digitAddedToQuotient * divisor) + "\n" + String.join("", Collections.nCopies(i, " "))
						+ String.join("", Collections.nCopies((initialTempDivident.toString().length() + 1), "-")));
			}

			blocksArr[0].append(quotient);

			StringBuilder output = new StringBuilder();

			for (int i = 0; i <= (dividentArr.length - initialTempDivident.toString().length()); i++) {
				output = output.append(blocksArr[i] + "\n");
			}
			output = output.append(String.join("", Collections.nCopies((dividentArr.length), " ")) + (tempDivident % divisor));

			return output.toString();


		}

	}

	public static void main(String[] args) {

		System.out.println(divideIntegers(78945, 4));

	}
}
