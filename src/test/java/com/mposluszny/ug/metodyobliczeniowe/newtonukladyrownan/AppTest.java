package com.mposluszny.ug.metodyobliczeniowe.newtonukladyrownan;

import static org.junit.Assert.*;

import org.junit.Test;

public class AppTest {

	@Test
	public void checkCalcE() {
		
		NewtonUkladyRownan newton = new NewtonUkladyRownan();
		
		assertEquals(newton.calcE(2.0, 3.0, 4.0), Double.valueOf(Math.sqrt(29)));
		assertEquals(newton.calcE(2.0, 4.0, 8.0), Double.valueOf(Math.sqrt(84)));
	}
}
