package game;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

public class Pool extends Ground {

	public Pool() {
		super('=');
	}
	/**
	 * The method is to decide whether the actor can enter or not.
	 * @param actor: The actor to be check if he has the keys
	 * @return : return a boolean value to indicate whether the door is open or not
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		return false;
	}
	/**
	 * The method allowed palyer to perform action
	 * @return : return action list that player need to perform
	 */
	@Override
	public Actions allowableActions(Actor actor, Location location, String direction){
		Actions list = new Actions();
		List<Item> items = new ArrayList<>();
		items=location.getItems();
		for (Item item:items) {
			if (item.getDisplayChar()=='*') {
				list.clear();
				return list;
			}
		}
		list.add(new AddWaterAction(actor));
		
		return list;
	}
}
