package com.gameStore;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Optional;

public class Store {
	private static final List<Game> games = new ArrayList<>();
	private static final Cart cart = new Cart();

	public static void main(String[] args) {
		new Store();

		Scanner scanner = new Scanner(System.in);
		boolean running = true;

		while (running) {
			System.out.println("Choose a store option: B - Browse games, A - Add to cart, R - Remove from cart, P - Purchase, Q - Quit");

			String options = scanner.nextLine().trim().toUpperCase();

			switch (options) {
				case "B":
					Store.Browse();
					break;
				case "A":
					System.out.println("Choose a game to add to your cart. (Enter game number)");
					try {
						int id = Integer.parseInt(scanner.nextLine().trim());
						Store.addToCart(id);
					} catch (NumberFormatException e) {
						System.out.println("Invalid id format.");
					}
					break;
				case "R":
					System.out.println("Choose a game to remove from your cart. (Enter game id)");
					try {
						int id = Integer.parseInt(scanner.nextLine().trim());
						Store.removeFromCart(id);
					} catch (NumberFormatException e) {
						System.out.println("Invalid id format.");
					}
					break;
				case "P":
					if (cart.isEmpty()) {
						System.out.println("No games in your cart.");
						break;
					}
					System.out.println("Games in cart: ");
					cart.getItems().forEach(System.out::println);
					System.out.println("Cart total: " + cart.total());
					System.out.println("Would you like to continue with your purchase? (Y/N)");
					String answer = scanner.nextLine().trim().toUpperCase();
					if (!answer.equals("Y")) {
						System.out.println("Purchase cancelled.");
					} else {
						Purchase();
					}
					break;
				case "Q":
					running = false;
					System.out.println("Goodbye!");
					break;
				default:
					System.out.println("Unknown option.");
			}
		}

		scanner.close();
	}

	private Store() {
		games.add(new Game(1, "Hollow Knight", 19.99));
		games.add(new Game(2, "Super Mario World", 9.99));
		games.add(new Game(3, "Legend of Zelda: Majora's Mask", 14.99));
		games.add(new Game(4, "Half Life 2", 14.99));
		games.add(new Game(5, "Super Metroid", 9.99));
	}

	private static void Browse() {
		System.out.println("Available games:");
		Store.games.forEach(System.out::println);
	}

	private static Optional<Game> findById(int id) {
		return games.stream().filter(game -> game.getId() == id).findFirst();
	}

	private static void addToCart(int id) {
		Optional<Game> game = findById(id);
		if (game.isPresent()) {
			cart.add(game.get());
			System.out.println(game.get().getName() + " added to cart.");
		} else {
			System.out.println("Game not found.");
		}
	}

	private static void removeFromCart(int id) {
		Optional<Game> game = findById(id);
		if (game.isPresent()) {
			cart.remove(game.get());
			System.out.println(game.get().getName() + " has been removed from cart.");
		} else {
			System.out.println("Game not found.");
		}
	}

	private static void Purchase() {
		cart.clear();
		System.out.println("Thank you for your purchase!");
	}
}