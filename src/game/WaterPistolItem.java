package game;

import edu.monash.fit2099.engine.Item;

public class WaterPistolItem extends Item {
	boolean b =false;
	boolean waterPistolCapacity;
	public WaterPistolItem() {
		super("WaterPistolIte", 'W');
	}
	/**
	 * Check water pistol whether has water item
	 * @return a boolean about water pistol capacity
	 * */
	public boolean getWaterPistolCapacity() {
		return waterPistolCapacity;
	}
	/**
	 * set water pistol a water item
	 * */
	public void setWaterPistolCapacity(boolean b) {
		waterPistolCapacity = b;
	}
}