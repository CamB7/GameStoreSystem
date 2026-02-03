package com.gameStore;

import java.util.ArrayList;
import java.util.List;

public class Cart {
	private final List<Game> items = new ArrayList<>();

	public void add(Game game) {
		if (game != null) items.add(game);
	}

	public void remove(Game game) {
		if (game != null) items.remove(game);
	}

	public List<Game> getItems() {
		return new ArrayList<>(items);
	}

	public double total() {
		return items.stream().mapToDouble(Game::getPrice).sum();
	}

	public boolean isEmpty() {
		return items.isEmpty();
	}

	public void clear() {
		items.clear();
	}
}