package game;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class TalkAction extends Action{
	private Actor actor;
	private Actor subject;

	public TalkAction(Actor actor, Actor subject) {
		this.actor = actor;
		this.subject = subject;
	}
	/**
	 * Check whether there is a rocket plan in the player's inventory.
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return a description of what happened that can be displayed to the user.
	 * */
	@Override
	public String execute(Actor actor, GameMap map) {
		for (int i=0;i<actor.getInventory().size();i++) {
			if(actor.getInventory().get(i) instanceof RocketPlanItem) {
				return "Hand them over, I don’t have all day!";
			}
		}
		return "I can give you something that will help, but I’m going to need the plans.";
	}
	
	/**
	 * Return a string that shows on UI.
	 * @param actor The actor performing the action.
	 * @return the text we put on the menu
	 * */
	@Override
	public String menuDescription(Actor actor) {
		return actor+" talk with " + subject;
	}

	@Override
	public String hotKey() {
		return "";
	}
}
