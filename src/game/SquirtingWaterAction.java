package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.MoveActorAction;

public class SquirtingWaterAction extends Action{
	
	private Actor actor;
	private YugoMaxx subject;
	private int random;
	WaterPistolItem w = new WaterPistolItem();
	/**
	 * This is the constructor of this class. It would initialize the player to perform this task
	 * @param actor: the actor to perform this task
	 */
	public SquirtingWaterAction(Actor actor, YugoMaxx subject) {
		this.actor = actor;
		this.subject = subject;
	}
	/**
	 * This method for player shoot the water pistol to yugomaxx
	 * @param actor: The actor to attack
	 * @param map: the current game map
	 * @return: return a statement if Yugo Maxx without exoskeleton. Otherwise return tSquirting Water Failed
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		boolean hasWater=false;
		for (int n=0;n<actor.getInventory().size();n++) {
			if(actor.getInventory().get(n) instanceof WaterItem) {
				actor.removeItemFromInventory(actor.getInventory().get(n));
			}
		}
		for (int i=0;i<actor.getInventory().size();i++) {
			if(actor.getInventory().get(i) instanceof WaterItem) {
				hasWater= true;
			}
		}
		random = (int)(Math.random()*10);
		if(random>=7&&hasWater) {
			if(subject.hasExoskeleton()) {
				for (int i=0;i<subject.getInventory().size();i++) {
					if(subject.getInventory().get(i) instanceof ExoskeletonItem) {
						subject.removeItemFromInventory(subject.getInventory().get(i));
							}
						return "Yugo Maxx without exoskeleton";
					}
				}
		}
		return "Squirting Water Failed";
	}
	@Override
	public String menuDescription(Actor actor) {
		return actor+" squirting water ";
	}

	@Override
	public String hotKey() {
		return "";
	}
}
