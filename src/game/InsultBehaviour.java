
package game;

import java.util.ArrayList;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;


public class InsultBehaviour implements ActionFactory{
	private ArrayList<String> insults=new ArrayList<String>();

	/**
	 * The constructor of this behaviour. It will add some strings into the insult list to be shouted later
	 */
	public InsultBehaviour() {
		this.insults.add("Monster!");
		this.insults.add("Stop Moving and go back home!");
		this.insults.add("This is not where you meant to be, you cannot success!");
		this.insults.add("You look like my pet Goblin!");
	}

	/**
	 * This method is to decide whether the goon would insult or not. If so, return the speak action
	 * @param actor: the actor to perform the insult
	 * @param map: the current game map
	 * @return: return the speak action for 10% chance. Otherwise return null for not insulting
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		int randomNum=(int) (Math.random()*10+1);
		if (randomNum==1) {
			int choice =(int)(Math.random()*4);
			String insult=insults.get(choice);
			return new SpeakAction(actor, insult);
		}
		return null;
	}
}