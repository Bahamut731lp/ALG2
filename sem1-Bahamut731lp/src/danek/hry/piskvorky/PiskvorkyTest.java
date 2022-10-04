package danek.hry.piskvorky;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PiskvorkyTest {

	private Piskvorky test;

	@Test
	void testPolozDalsiSymbol() {
		fail("Not yet implemented");
	}
	
	@Nested
	@DisplayName("Metoda getSymbol()")
    class testGetSymbol {
		
		@BeforeEach
		void setUp() {
			test = PiskvorkyInterface.getInstance(9, 9);
		}
		
		@Test
		@DisplayName("Ošetření neplatných argumentů")
		void getSymbolArgsExceptions() {
			assertThrows(PiskvorkyException.class, () -> test.getSymbol(10, 0));
			assertThrows(PiskvorkyException.class, () -> test.getSymbol(0, 10));
			assertThrows(PiskvorkyException.class, () -> test.getSymbol(-5, 0));
			assertThrows(PiskvorkyException.class, () -> test.getSymbol(0, -5));
		}
		
		@Test
		@DisplayName("Získání hodnot")
		void getSymbolVals() {
			assertEquals(0, test.getSymbol(6, 6));
			assertEquals(0, test.getSymbol(8, 6));
			assertEquals(0, test.getSymbol(1, 2));
			assertEquals(0, test.getSymbol(8, 8));
			assertEquals(0, test.getSymbol(0, 0));
		}

    }

	@ParameterizedTest
	@DisplayName("Metoda getPocetRadku()")
	@ValueSource(ints = {6, 7, 8, 15, 29}) // six numbers
	void testGetPocetRadku(int num) {
		assertEquals(num, PiskvorkyInterface.getInstance(num, PiskvorkyInterface.MIN_ROZMER).getPocetRadku());
	}

	@ParameterizedTest
	@DisplayName("Metoda getPocetSloupcu()")
	@ValueSource(ints = {6, 7, 8, 15, 29}) // six numbers
	void testGetPocetSloupcu(int num) {
		assertEquals(num, PiskvorkyInterface.getInstance(PiskvorkyInterface.MIN_ROZMER, num).getPocetSloupcu());
	}

	@Test
	void testJeKonecHry() {
		fail("Not yet implemented");
	}

	@Test
	void testGetHrajeHrac() {
		fail("Not yet implemented");
	}

	@Test
	void testGetCisloVyherce() {
		fail("Not yet implemented");
	}

}
