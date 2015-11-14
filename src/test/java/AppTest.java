import static org.junit.Assert.*;

import org.junit.Test;

public class AppTest {

	@Test
	public void wyliczMnoznikiTest() {
		
		Double x[] = {2.0, 4.0, 8.0, -4.0, -1.0};
		Double y[] = {2.0, 4.0, 8.0, 16.0, 1.0};
		Double b[] = new Double[3];
		InterpolacjaNewtona interpolacja = new InterpolacjaNewtona(x, y, b);
				
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
	
}
