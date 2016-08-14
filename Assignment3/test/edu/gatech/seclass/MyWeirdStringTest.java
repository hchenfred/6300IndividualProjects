package edu.gatech.seclass;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MyWeirdStringTest {

	private MyWeirdStringInterface myweirdstring;

	@Before
	public void setUp() throws Exception {
		myweirdstring = new MyWeirdString();
	}

	@After
	public void tearDown() throws Exception {
		myweirdstring = null;
	}
	
	//This test checks whether method countDigits returns the correct digit count when an input string contains spaces, letters, digits and other marks
	@Test
	public void testCountDigits1() {
		myweirdstring.setWeirdString("I'd better put s0me d1gits in this 5tr1n9, right?");
		assertEquals(5, myweirdstring.countDigits());
	}
	
	
	//This test checks whether method countDigits returns 0 when input string is an empty string
	@Test
	public void testCountDigits2() {
		myweirdstring.setWeirdString("");
		assertEquals(0, myweirdstring.countDigits());
	}
	
	//This test checks whether method countDigits throws NullPointerException when input is null
	@Test(expected = NullPointerException.class)
	public void testCountDigits3() {
		myweirdstring.setWeirdString(null);
		myweirdstring.countDigits();
	}

	//This test checks whether method countDigits returns 0 when input string only contains letters and other marks
	@Test
	public void testCountDigits4() {
		myweirdstring.setWeirdString("abbcskekskskwa))(^%$");
		assertEquals(0, myweirdstring.countDigits());
	}

	//This test checks whether method getEvenCharacters return the correct string when the input string contains letters, digits, and other marks
	@Test
	public void testGetEvenCharacters1() {
		myweirdstring.setWeirdString("I'd better put s0me d1gits in this 5tr1n9, right?");
		assertEquals("' etrptsm 1isi hs5rn,rgt", myweirdstring.getEvenCharacters());
	}
	
	//This test checks whether method getEvenCharacters returns an empty string when the input string is an empty string
	@Test
	public void testGetEvenCharacters2() {
		myweirdstring.setWeirdString("");
		assertEquals("", myweirdstring.getEvenCharacters());
	}
	
	//This test checks whether method getEvenCharacters returns an empty string when the input string only contains one character
	@Test
	public void testGetEvenCharacters3() {
		myweirdstring.setWeirdString("a");
		assertEquals("", myweirdstring.getEvenCharacters());
	}
	
	//This test checks whether method getEvenCharacters throws a NullPointerException when input string is null
	@Test(expected = NullPointerException.class)
	public void testGetEvenCharacters4() {
		myweirdstring.setWeirdString(null);
		myweirdstring.getEvenCharacters();
	}

	//This test checks whether method getOddCharacters returns the correct string when the input string contains letters, digits, and other marks
	@Test
	public void testGetOddCharacters1() {
		myweirdstring.setWeirdString("I'd better put s0me d1gits in this 5tr1n9, right?");
		assertEquals("Idbte u 0edgt nti t19 ih?", myweirdstring.getOddCharacters());
	}

	//This test checks whether method getOddCharacters returns an empty string when input string is an empty string
	@Test
	public void testGetOddCharacters2() {
		myweirdstring.setWeirdString("");
		assertEquals("", myweirdstring.getOddCharacters());
	}

	//This test checks whether method getOddCharacters returns one character when input string only contains one character
	@Test
	public void testGetOddCharacters3() {
		myweirdstring.setWeirdString("a");
		assertEquals("a", myweirdstring.getOddCharacters());
	}

	//This test checks whether method getEvenCharacters throws a NullPointerException when input string is null
	@Test(expected = NullPointerException.class)
	public void testGetOddCharacters4() {
		myweirdstring.setWeirdString(null);
		myweirdstring.getOddCharacters();
	}
	
	//This test checks whether method convertDigitsToRomanNumeralsInSubstring correctly changes digits in a string to Roman numbers
	@Test
	public void testConvertDigitsToRomanNumeralsInSubstring1() {
		myweirdstring.setWeirdString("I'd better put s0me d1gits in this 5tr1n9, right?");
		myweirdstring.convertDigitsToRomanNumeralsInSubstring(40, 45);
		assertEquals("I'd better put s0me d1gits in this 5tr1nIX, right?", myweirdstring.getWeirdString());
	}
	
	//This test checks whether method convertDigitsToRomanNumeralsInSubstring throws an IllegalArgumentException when startPosition is greater than endPosition
	@Test(expected = IllegalArgumentException.class)
	public void testConvertDigitsToRomanNumeralsInSubstring2() {
		myweirdstring.setWeirdString("I'd better put s0me d1gits in this 5tr1n9, right?");
		myweirdstring.convertDigitsToRomanNumeralsInSubstring(20, 10);
	}
	
	//This test checks whether method convertDigitsToRomanNumeralsInSubstring throws MyIndexOutOfBoundsException when startPosition is out of bound
	@Test(expected = MyIndexOutOfBoundsException.class)
	public void testConvertDigitsToRomanNumeralsInSubstring3() {
		myweirdstring.setWeirdString("I'd better put s0me d1gits in this 5tr1n9, right?");
		myweirdstring.convertDigitsToRomanNumeralsInSubstring(60, 90);
	}

	//This test checks whether method convertDigitsToRomanNumeralsInSubstring correctly converts 1-9 to its corresponding Roman numbers and keeps 0 as 0 
	@Test
	public void testConvertDigitsToRomanNumeralsInSubstring4() {
		myweirdstring.setWeirdString("0123456789");
		myweirdstring.convertDigitsToRomanNumeralsInSubstring(1, 10);
		assertEquals("0IIIIIIIVVVIVIIVIIIIX", myweirdstring.getWeirdString());
	}

	//This test checks whether method convertDigitsToRomanNumeralsInSubstring throws NullPointerException when input is null
	@Test(expected = NullPointerException.class)
	public void testConvertDigitsToRomanNumeralsInSubstring5() {
		myweirdstring.setWeirdString(null);
		myweirdstring.convertDigitsToRomanNumeralsInSubstring(1, 10);
	}

	//This test checks whether method convertDigitsToRomanNumeralsInSubstring only converts digits between startPosition and endPostion to Roman numbers
	@Test
	public void testConvertDigitsToRomanNumeralsInSubstring6() {
		myweirdstring.setWeirdString("123459999912345");
		myweirdstring.convertDigitsToRomanNumeralsInSubstring(6, 10);
		assertEquals("12345IXIXIXIXIX12345", myweirdstring.getWeirdString());
	}

}
