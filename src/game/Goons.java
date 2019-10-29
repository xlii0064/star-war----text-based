package game;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.IntrinsicWeapon;

public class Goons extends Actor{
	
	/**
	 * This is the constructor of the class Goons. It will initialize the goon and addBehavious into its arrayList
	 * @param name: The name of the goon object
	 * @param player: The player in this game
	 */
	public Goons(String name, Actor player) {
		super(name, 'G', 8, 50);
		addBehaviour(new InsultBehaviour());
		addBehaviour(new FollowBehaviour(player));

		
	}
	/**
	 * The arrayList to keep all it's possible actions
	 */
	public List<ActionFactory> actionFactories = new ArrayList<ActionFactory>();

	private void addBehaviour(ActionFactory behaviour) {
		actionFactories.add(behaviour);
		
	}
	
	/**
	 * This method is to decide which action this goon will perform. It will loop through the action list and return the 
	 * @param actions: the action list to be chosen from
	 * @param map: The current game map
	 * @param display: The display object
	 * @return: return an action to be executed by this goon this turn
	 */
	@Override
	public Action playTurn(Actions actions, GameMap map, Display display) {
		if (this.isConscious()) {
			for (int i=0;i<actions.size();i++) {
				if (actions.get(i) instanceof DropItemAction) {
					actions.remove(actions.get(i));
				}
			}
		}
		for (ActionFactory factory : actionFactories) {
			Action action = factory.getAction(this, map);
			if(action != null)
				return action;
		}
		return super.playTurn(actions,  map,  display);
	}
	@Override
	public IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(10, "slaps");
	}


}
