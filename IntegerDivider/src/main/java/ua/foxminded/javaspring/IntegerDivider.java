package ua.foxminded.javaspring;

import java.util.Collections;

public class IntegerDivider {

	public static String divideIntegers(int divident, int divisor) {

		if (divisor == 0) {
			throw new IllegalArgumentException("Can't divide by zero");
		} else if (divident == 0) {
			String DIVIDENT_ZERO_RESULT = "_0" + "\t|" + divisor + "\n " + 0 + "\t|-" + "\n\t|0";
			return DIVIDENT_ZERO_RESULT;

		} else if (divident < divisor) {
			throw new IllegalArgumentException("Divident must be greater than divisor");
		} else {

			int[] dividentArr = createDigitsArray(divident);
			Integer initialTempDivident = calculateNextDivident(divisor, dividentArr);
			int quotient = initialTempDivident / divisor;
			int nexQuotientDigit = 0;
			int tempDivident = initialTempDivident;

			StringBuilder[] divisionBlocks = new StringBuilder[dividentArr.length - initialTempDivident.toString().length()+1];

			buildFirstDivisionBlock(divident, divisor, initialTempDivident, quotient, divisionBlocks);

			for (int i = 1; i <= (dividentArr.length - initialTempDivident.toString().length()); i++) {
				tempDivident = tempDivident % divisor * 10
						+ (dividentArr[initialTempDivident.toString().length() + i - 1]);
				nexQuotientDigit = tempDivident / divisor;
				buildDivisionBlock(divisor, initialTempDivident, nexQuotientDigit, tempDivident, divisionBlocks, i);
				quotient = quotient * 10 + nexQuotientDigit;
			}

			divisionBlocks[0].append(quotient);

			StringBuilder output = new StringBuilder();

			for (StringBuilder block : divisionBlocks) {
				output = output.append(block + "\n");
			}
			output = output
					.append(String.join("", Collections.nCopies((dividentArr.length), " ")) + (tempDivident % divisor));

			return output.toString();

		}

	}

	private static void buildFirstDivisionBlock(int divident, int divisor, Integer initialTempDivident, int quotient,
			StringBuilder[] divisionBlocks) {
		divisionBlocks[0] = new StringBuilder()
				.append("_" + divident + "\t|" + divisor + "\n " + (quotient * divisor) + "\t|-----" + "\n "
						+ String.join("", Collections.nCopies((initialTempDivident.toString().length()), "-")) + "\t|");
	}

	private static void buildDivisionBlock(int divisor, Integer initialTempDivident, int digitAddedToQuotient,
			int tempDivident, StringBuilder[] divisionBlocks, int i) {
		divisionBlocks[i] = new StringBuilder().append(String.join("", Collections.nCopies(i - 1, " ")) + "_"
				+ tempDivident + "\n " + String.join("", Collections.nCopies(i - 1, " "))
				+ (digitAddedToQuotient * divisor) + "\n" + String.join("", Collections.nCopies(i, " "))
				+ String.join("", Collections.nCopies((initialTempDivident.toString().length() + 1), "-")));
	}

	private static Integer calculateNextDivident(int divisor, int[] dividentArr) {
		Integer initialTempDivident = dividentArr[0];
		while (initialTempDivident < divisor) {
			initialTempDivident = initialTempDivident * 10 + dividentArr[initialTempDivident.toString().length()];
		}
		return initialTempDivident;
	}

	private static int[] createDigitsArray(int divident) {
		String dividentString = Integer.toString(divident);
		int[] dividentArr = new int[dividentString.length()];
		for (int i = 0; i < dividentString.length(); i++) {
			dividentArr[i] = dividentString.charAt(i) - '0';
		}
		return dividentArr;
	}

	public static void main(String[] args) {

		System.out.println(divideIntegers(78945, 4));

	}
}
