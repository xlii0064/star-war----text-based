package game;

import java.util.ArrayList;

import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.IntrinsicWeapon;

/**
 * Class representing the DoctorMaybe.
 */

public class DoctorMaybe extends Actor{
	/**
	 * Constructor.
	 * @param name to call the DoctorMaybe in the UI
	 * @param displayChar Character to represent the player in the UI
	 */
	public DoctorMaybe(String name, Actor player) {
		super(name,'M', 10, 25);
		}
		private List<ActionFactory> actionFactories = new ArrayList<ActionFactory>();

		private void addBehaviour(ActionFactory behaviour) {
			actionFactories.add(behaviour);
		}
		/**
		 * Run action that Doctor Maybe need to perform in this turn.
		 * @param actions collection of possible Actions for this Actor
		 * @param map     the map containing the Actor
		 * @param display the I/O object to which messages may be written
		 * @return the Action to be performed
		 *  */
		@Override
		public Action playTurn(Actions actions, GameMap map, Display display) {
			for (ActionFactory factory : actionFactories) {
				Action action = factory.getAction(this, map);
				if(action != null)
					return action;
			}
			return super.playTurn(actions,  map,  display);
		}
		/**
		 * Doctor Maybe only does half of a damage of Grunt.
		 * @return return a damage of maybe.
		 * */
		@Override
		protected IntrinsicWeapon getIntrinsicWeapon() {
			return new IntrinsicWeapon(3, "Attack");
		}
	}

