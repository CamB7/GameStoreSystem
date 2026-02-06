package com.gameStoreTests;

import com.gameStore.Game;
import com.gameStore.Cart;
import com.gameStore.Store;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameStoreTests {

	@Test
	public void CartTests() {
		Cart cartTests = new Cart();
		Game gameTest1 = new Game(1, "Contra", 9.99);
		Game gameTest2 = new Game(2, "Super Mario Sunshine", 19.99);

		cartTests.add(gameTest1);
		cartTests.add(gameTest2);
		Assertions.assertFalse(cartTests.isEmpty());

		double expected = 29.98;
		Assertions.assertEquals(expected, cartTests.total());

		cartTests.remove(gameTest2);
		Assertions.assertEquals(1, cartTests.getItems().size());

		cartTests.clear();
		Assertions.assertTrue(cartTests.isEmpty());
	}

}
