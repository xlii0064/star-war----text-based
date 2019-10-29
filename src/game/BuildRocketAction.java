package game;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.MoveActorAction;

public class BuildRocketAction extends Action{
	
	private Actor actor;
	private List<Item> items = new ArrayList<Item>();
	
	/**
	 * This is the constructor of this class. It would initialize the player to perform this task
	 * @param actor: the actor to perform this task
	 */
	public BuildRocketAction(Actor actor) {
		this.actor = actor;
	}
	
	/**
	 * This method is to check whether the player has all parts of the rocket or not. If so, replace the parts with the rocket
	 * @param actor: The actor to build the rocket
	 * @param map: the current game map
	 * @return: return a statement if the construction is successful. Otherwise return the unsuccessful message.
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		boolean re=false;
		boolean rb=false;
		for (int i=0;i<actor.getInventory().size();i++) {
			if(actor.getInventory().get(i) instanceof RocketEngineItem) {
				actor.removeItemFromInventory(actor.getInventory().get(i));
				re = true;
			}
			if(actor.getInventory().get(i) instanceof RocketBodyItem) {
				actor.removeItemFromInventory(actor.getInventory().get(i));
				rb = true;
			}
		}
		if(re&&rb && !checkRocket(actor, map)) {
			Item rocket = Item.newFurniture("Rocket", '*');
			Location here = map.locationOf(actor);
			map.addItem(rocket,here.x(),here.y());
			return "Build Successful";
		}
		if (checkRocket(actor, map)) {
			return "There is already a rocket at your location.";
		}
		return "You miss part of the rocket " ;
	}
	public boolean checkRocket(Actor actor, GameMap map) {
		List<Item> items = new ArrayList<>();
		Location here = map.locationOf(actor);
		items=here.getItems();
		for (Item item:items) {
			if (item.getDisplayChar()=='*') {
				return true;
			}
		}
		return false;
	}

	/**
	 * This method is to decide which statement would be shown on the game console
	 * @param actor : the actor to build the rocket
	 * @return : return a statement that shows an actor build a rocket
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor+" build a rocket ";
	}

	@Override
	public String hotKey() {
		return "";
	}
}
