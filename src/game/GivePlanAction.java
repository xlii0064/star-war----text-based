package game;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class GivePlanAction extends Action{
	private Actor actor;
	private Actor subject;
	private RocketBodyItem rocketBody = new RocketBodyItem();

	public GivePlanAction(Actor actor, Actor subject) {
		this.actor = actor;
		this.subject = subject;
	}
	/**
	 * Use a rocket body replaced rocket plan. Use a for loop check player's inventory.
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return a description of what happened that can be displayed to the user.
	 * */
	@Override
	public String execute(Actor actor, GameMap map) {
		for (int i=0;i<actor.getInventory().size();i++) {
			if(actor.getInventory().get(i) instanceof RocketPlanItem) {
				actor.getInventory().remove(actor.getInventory().get(i));
				actor.getInventory().add(rocketBody);
			}
		}
		return "Q disappear with a cheery wave.";
	}

	/**
	 * Return a string that shows on UI.
	 * @param actor The actor performing the action.
	 * @return the text we put on the menu
	 * */
	@Override
	public String menuDescription(Actor actor) {
		return actor+" give plan to " + subject;
	}

	@Override
	public String hotKey() {
		return "";
	}
}
