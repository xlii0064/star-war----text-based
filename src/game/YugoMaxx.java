package game;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.demo.KickAction;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.IntrinsicWeapon;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.PickUpItemAction;

public class YugoMaxx extends Actor {

	public YugoMaxx( ) {
		super("Yugo Maxx", 'Y', 2, 50);
		this.addItemToInventory(new ExoskeletonItem());
	}
	/**
	 * This function is to set the initial attacking method and how much damage he can make
	 */
	public IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(10, "bombard");
	}
	
	/**
	 * This function will check if Yugo has an exoskeleton or not. If so, then he cannot be hurt by the player
	 */
	@Override
	public void hurt(int points) {
		if (!hasExoskeleton()) {
		hitPoints -= points;
		}
	}
	/**
	 * This function would check if yugo maxx has exoskeleton or not
	 * @return return true if he has one
	 */
	public boolean hasExoskeleton() {
		List<Item> inventory = new ArrayList<Item>();
		inventory=this.getInventory();
		for (Item item:inventory) {
			if (item instanceof ExoskeletonItem) {
				return true;
			}
		}
		return false;
	}
	@Override
	/**
	 * This function is to remove the pickup action and drop item action since he should not drop or pick anything during the game
	 * @param actions: the action list to be performed
	 * @param map: the current game map
	 * @param display: performs console I/O
	 */
	public Action playTurn(Actions actions, GameMap map, Display display) {
		for (int i=0;i<actions.size();i++) {
			if (actions.get(i) instanceof DropItemAction) {
				actions.remove(actions.get(i));
			}
			if (actions.get(i) instanceof PickUpItemAction) {
				actions.remove(actions.get(i));
			}
		}
		return super.playTurn(actions,  map,  display);
	}
	@Override
	/**
	 * This function is to add an function into the action list and return the list
	 * @param otherActor: the actor to perform this new action
	 * @param direction: the direction to perform the action
	 * @param map: the current game map
	 */
	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
		Actions list = super.getAllowableActions(otherActor, direction, map);
		list.add(new SquirtingWaterAction(otherActor, this));
		return list;
	}

}
