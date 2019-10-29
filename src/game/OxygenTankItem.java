package game;

import edu.monash.fit2099.engine.Item;

public class OxygenTankItem extends Item {
	int oxygen = 10;

	public OxygenTankItem() {
		super("Oxygen Tank", 'T');
	}
/**
 * This function is to check whether this current oxygen tank can be used or not
 * @return return true if there's oxygen left in the tank
 */
	public boolean canUse() {
		if (oxygen >= 1) {
			return true;
		}
		return false;
	}
/**
 * This function allows player to use oxygen inside the tank
 */
	public void use() {
		oxygen--;
	}

	/**
	 * This function is to check if the tank is empty or not
	 * @return returns true if there's nothing left in the tank
	 */
	public boolean isEmpty() {
		return oxygen == 0;
	}
}
