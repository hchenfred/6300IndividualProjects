package edu.gatech.seclass;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FaultyClassTest {
	FaultyClass myFaultyObject;
	@Before
	public void setUp() throws Exception {
		myFaultyObject = new FaultyClass();
		
	}

	//This test achieves 100% statement coverage and not reveal the fault therein
	@Test
	public void method1SC() {
		assertEquals(-100, myFaultyObject.method1(-1, -2));
		assertEquals(50, myFaultyObject.method1(1, 2));	
	}
	
	//This test achieves 100% branch coverage and not reveal the fault therein
	@Test
	public void method1BC() {
		assertEquals(-100, myFaultyObject.method1(-1, -2));
		assertEquals(50, myFaultyObject.method1(1, 2));	
	}
	
	//This test achieve 100% path coverage and reveal the fault therein
	@Test 
	public void method1PC() {
		assertEquals(-100, myFaultyObject.method1(-1, -2));	
		assertEquals(50, myFaultyObject.method1(1, 2));	
		assertEquals(100, myFaultyObject.method1(-1, 2));
		assertEquals(25, myFaultyObject.method1(1, -2));
	}
	
	//This test achieve 100% path coverage of method2 and not reveal the fault therein.
	@Test
	public void method2PC() {
		assertEquals(20, myFaultyObject.method2(3, 1, 3));
		assertEquals(25, myFaultyObject.method2(5, -1, -3));	
		assertEquals(20, myFaultyObject.method2(5, 1, -3));
		assertEquals(25, myFaultyObject.method2(3, -1, 3));
	}
	
	//This test achieve 100% statement coverage of method2 and not reveal the fault therein.
	@Test 
	public void method2SC() {
		assertEquals(20, myFaultyObject.method2(3, 1, 3));
		assertEquals(25, myFaultyObject.method2(5, -1, -3));	
	}
	
	//This test achieve 100% branch coverage, achieve 100% statement coverage, and not achieve 100% path coverage of method2 and reveal the fault therein.
	@Test 
	public void method2BC() {
		assertEquals(20, myFaultyObject.method2(3, 1, 3));
		assertEquals(25, myFaultyObject.method2(5, -1, -3));
		assertEquals(25, myFaultyObject.method2(0, 1, -2));	
	}
	
	
	

}
