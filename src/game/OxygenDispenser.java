package game;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

public class OxygenDispenser extends Ground {

	public OxygenDispenser() {
		super('O');
	}
	@Override
	/**
	 * This function makes entering the oxygen dispenser impossible
	 * @param actor: the actor who wants to enter
	 * @return return false so no actor can enter this place
	 */
	public boolean canActorEnter(Actor actor) {
		return false;
	}
	@Override
	/**
	 * This function add a the action make oxygen action into the action list so the player can make oxygen here
	 * @param actor: the actor to perform the action
	 * @param location: the current location
	 * @param direction: the direction to perform the action
	 * @return return a list that contains all the actions that can be performed
	 */
	public Actions allowableActions(Actor actor, Location location, String direction){
		Actions list = new Actions();
		list.add(new MakeOxygenAction(actor));
		return list;
	}


}
