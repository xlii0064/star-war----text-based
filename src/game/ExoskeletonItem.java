package game;

import edu.monash.fit2099.engine.Item;

public class ExoskeletonItem extends Item {
	boolean valid = true;

	public ExoskeletonItem() {
		super("Exoskeleton", 'E');
	}

	/**
	 * This function would return if this exoskeleton can still be used or not
	 * @return the status of the exoskeleton
	 */
	public boolean canBeUsed() {
		return valid;
	}
/**
 * This function would set the current status of this exoskeleton
 * @param status: the new status
 * @return: if the set was successful or not
 */
	public boolean setStatus(boolean status) {
		if (!valid) {
			return false;
		} else {
			valid = false;
			return true;
		}
	}

}
