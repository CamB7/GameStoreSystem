package com.gameStoreTests;

import com.gameStore.Game;
import com.gameStore.Cart;
import com.gameStore.Store;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class GameStoreTests {

	@Test
	public void CartOperationTests() {
		Cart cartTests = new Cart();
		Game gameTest1 = new Game(1, "Contra", 9.99);
		Game gameTest2 = new Game(2, "Super Mario Sunshine", 19.99);

		cartTests.add(gameTest1);
		cartTests.add(gameTest2);
		Assertions.assertFalse(cartTests.isEmpty());

		double expectedPrice = 29.98;
		Assertions.assertEquals(expectedPrice, cartTests.total());

		cartTests.remove(gameTest2);
		Assertions.assertEquals(1, cartTests.getItems().size());

		cartTests.clear();
		Assertions.assertTrue(cartTests.isEmpty());
	}

	@Test
	void purchaseClearsCart() throws Exception{
		new Store();
		Store.addToCart(1);

		Method purchase = Store.class.getDeclaredMethod("Purchase");
		purchase.setAccessible(true);
		purchase.invoke(null);

		Cart cartTest = getStoreCart();
		Assertions.assertTrue(cartTest.isEmpty());
	}

	@Test
	void gameToStringContainsCorrectVariableValues() {
		Game gameTest = new Game(5, "Zelda", 29.99);

		String string = gameTest.toString();
		Assertions.assertTrue(string.contains("5"));
		Assertions.assertTrue(string.contains("Zelda"));
		Assertions.assertTrue(string.contains("29.99"));
	}

	private Cart getStoreCart() throws Exception {
		Field cartTestField = Store.class.getDeclaredField("cart");
		cartTestField.setAccessible(true);
		return (Cart) cartTestField.get(null);
	}
}
