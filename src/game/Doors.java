package game;


import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;


public class Doors extends Ground{
	boolean open;
	RocketBodyItem rocketBody=null;
/**
 * The constructor of the class Doors
 */
	public Doors() {
		super('D');
		open=false;
	}
	/**
	 * The method is to decide whether the actor can enter or not.
	 * @param actor: The actor to be check if he has the keys
	 * @return : return a boolean value to indicate whether the door is open or not
	 */
	public boolean canActorEnter(Actor actor) {
		for (int i=0;i<actor.getInventory().size();i++) {
			if (actor.getInventory().get(i) instanceof KeysItem) {
				open=true;
				break;
			}
		}
		return open;
	}
	

/**
 * This function is to justify can stuffs be thrown across or not
 * @return: return the current status of the door. If it is open, then things can be thrown across	
 */
	public boolean blocksThrownObjects() {
		return !open;
	}
	

}
