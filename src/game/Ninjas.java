package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.List;

public class Ninjas extends Actor{
	
	public Ninjas(String name, Actor player) {
		super(name, 'N', 4, 50);
		
	}
	
	public List<ActionFactory> actionFactories = new ArrayList<ActionFactory>();
	
	private void addBehaviour(ActionFactory behaviour) {
		actionFactories.add(behaviour);
	}
	/**
     * Run the Ninjas need to run in the turn.
     *
     * @param actions collection of possible Actions for this Actor
	 * @param map     the map containing the Actor
	 * @param display the I/O object to which messages may be written
	 * @return the Action to be performed
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
	
	
}
