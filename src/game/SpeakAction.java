package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class SpeakAction extends Action{
	private Actor actor;
	private String sentence;

	/**
	 * The constructor of the class SpeakAction. It will take an actor and a string as the parameter to perform the action
	 * @param actor: the actor to speak
	 * @param sentence: the sentence to be spoken
	 */
	public SpeakAction(Actor actor, String sentence) {
		this.actor=actor;
		this.sentence=sentence;
	}

	/**
	 * This method is to show the sentence when the actor perform this action
	 * @param actor: the actor to speak
	 * @param map: the current game map
	 * @return return the statement of the actor speak something
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		return actor+" said: "+ sentence;
	}

	@Override
	public String menuDescription(Actor actor) {
		return "";
	}

	@Override
	public String hotKey() {
		return "";
	}

}
