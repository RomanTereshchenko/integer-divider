package ua.foxminded.javaspring;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class IntegerDividerTest {

	@Test
	void divideIntegers_returnsCorrectOutput_singleDigitDivisor() {
		
		assertEquals(
				"_78945	|4\n"
				+ " 4	|-----\n"
				+ " -	|19736\n"
				+ "_38\n"
				+ " 36\n"
				+ " --\n"
				+ " _29\n"
				+ "  28\n"
				+ "  --\n"
				+ "  _14\n"
				+ "   12\n"
				+ "   --\n"
				+ "   _25\n"
				+ "    24\n"
				+ "    --\n"
				+ "     1", IntegerDivider.divideIntegers(78945, 4));
	}
	
	@Test
	void divideIntegers_returnsCorrectOutput_multipleDigitDivisor() {
		
		assertEquals("_78945	|443\n"
				+ " 443	|-----\n"
				+ " ---	|178\n"
				+ "_3464\n"
				+ " 3101\n"
				+ " ----\n"
				+ " _3635\n"
				+ "  3544\n"
				+ "  ----\n"
				+ "     91", IntegerDivider.divideIntegers(78945, 443));
		
	}
	
	@Test 
	void divideIntegers_throwsCorrectIllegalArgumentException_zeroDivisor() {
	
		Throwable exception = assertThrows(IllegalArgumentException.class, () -> IntegerDivider.divideIntegers(78945, 0));
		assertTrue(exception.getMessage().contains("Can't divide by zero"));
		 
	}
	
	@Test 
	void divideIntegers_throwsCorrectIllegalArgumentException_divisorIsGraterThanDivident() {
		
		Throwable exception = assertThrows(IllegalArgumentException.class, () -> IntegerDivider.divideIntegers(78945, 789456));
		assertTrue(exception.getMessage().contains("Divident must be greater than divisor"));
		
	}
	
	}
	
	



