import static org.junit.Assert.*;

import org.junit.Test;

public class AppTest {

	@Test
	public void wyliczMnoznikiTest() {
		
		Double x[] = {2.0, 4.0, 8.0, -4.0, -1.0};
		Double y[] = {2.0, 4.0, 8.0, 16.0, 1.0};
		InterpolacjaNewtona interpolacja = new InterpolacjaNewtona(x, y);
				
		assertEquals(new Double(1.0), interpolacja.wyliczMnozniki(0, 0));
		
		assertEquals(new Double(-2.0), interpolacja.wyliczMnozniki(0, 1));
		assertEquals(new Double(12.0), interpolacja.wyliczMnozniki(0, 2));
		assertEquals(new Double(72.0), interpolacja.wyliczMnozniki(0, 3));
		assertEquals(new Double(72.0 * 3), interpolacja.wyliczMnozniki(0, 4));
		
		assertEquals(new Double(2.0), interpolacja.wyliczMnozniki(1, 1));
		assertEquals(new Double(-8.0), interpolacja.wyliczMnozniki(1, 2));
		assertEquals(new Double(-64.0), interpolacja.wyliczMnozniki(1, 3));
		assertEquals(new Double(-64.0 * 5), interpolacja.wyliczMnozniki(1, 4));
		
		assertEquals(new Double(6.0), interpolacja.wyliczMnozniki(2, 1));
		assertEquals(new Double(24.0), interpolacja.wyliczMnozniki(2, 2));
		assertEquals(new Double(24.0 * 12), interpolacja.wyliczMnozniki(2, 3));
		assertEquals(new Double(24.0 * 12 * 9), interpolacja.wyliczMnozniki(2, 4));
	}
	
	@Test
	public void wyliczBTest() {
		
		Double x[] = {5.0, 6.0, 7.0, 8.0};
		Double y[] = {1.0, 2.0, 3.0, 4.0};
		InterpolacjaNewtona interpolacja = new InterpolacjaNewtona(x, y);
		
		for (int i = 0; i < interpolacja.b.length; i++) {
			
			interpolacja.b[i] = interpolacja.wyliczB(i, y[i]);
		}
		
		assertEquals(new Double(1.0), interpolacja.b[0]);
		assertEquals(new Double(1.0), interpolacja.b[1]);
		assertEquals(new Double(0.0), interpolacja.b[2]);
		assertEquals(new Double(0.0), interpolacja.b[3]);
		
		
		Double x2[] = {1.0, 2.0, 4.0, 5.0};
		Double y2[] = {0.0, 2.0, 12.0, 20.0};
		InterpolacjaNewtona interpolacja2 = new InterpolacjaNewtona(x2, y2);
		
		for (int i = 0; i < interpolacja2.b.length; i++) {
			
			interpolacja2.b[i] = interpolacja2.wyliczB(i, y2[i]);
		}
		
		assertEquals(new Double(0.0), interpolacja2.b[0]);
		assertEquals(new Double(2.0), interpolacja2.b[1]);
		assertEquals(new Double(1.0), interpolacja2.b[2]);
		assertEquals(new Double(0.0), interpolacja2.b[3]);
	}
	
}
