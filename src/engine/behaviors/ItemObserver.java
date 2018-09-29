package engine.behaviors;

import java.util.List;

import engine.entities.items.Item;

public interface ItemObserver {
	
	public void notifyOfItems(List<Item> items);

}
