package game;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

public class MakeOxygenAction extends Action {
	Actor actor;
	public MakeOxygenAction(Actor actor) {
		this.actor=actor;
	}

	@Override
	/**
	 * This function would check if the player can make a new oxygen tank or not. If so, it will create a new oxygen tank at the current location
	 * If not, it will print the message
	 * @param actor: the actor to perform this action
	 * @param map: the current game map
	 */
	public String execute(Actor actor, GameMap map) {
		List<Item> items = new ArrayList<>();
		Location here = map.locationOf(actor);
		items=here.getItems();
		for (Item item:items) {
			if (item instanceof OxygenTankItem) {
				return "There is an oxygen tank already.";
			}
		}
		OxygenTankItem ot=new OxygenTankItem();
		map.addItem(ot,here.x(),here.y());
		return "The player build a new oxygen tank.";
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor+" make a new oxygen tank";
	}

	@Override
	public String hotKey() {
		return "";
	}

}
