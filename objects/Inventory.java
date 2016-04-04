package game.objects;

import java.util.LinkedList;
import java.util.List;

import game.exceptions.InventoryFullE;

public class Inventory {
	private List<Item> items;
	private int inventorySize;

	public Inventory() {
		inventorySize = 10;
		items = new LinkedList<Item>();
	}

	public void addItem(Item item) throws InventoryFullE {
		if(items.size()==10) throw new InventoryFullE();
		else items.add(item);
	}
}
