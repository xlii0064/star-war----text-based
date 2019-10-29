package game;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.SkipTurnAction;
import edu.monash.fit2099.engine.World;

public class NewWorld extends World {
	private Display display;
	private String result;
	public NewWorld(Display display) {
		super(display);
		this.display = display;
	}

/**
 * add an attribute to record the result after execution
 */
	public void processActorTurn(Actor actor) {
		Location here = actorLocations.locationOf(actor);
		GameMap map = here.map();

		Actions actions = new Actions();
		for (Item item : actor.getInventory()) {
			actions.add(item.getAllowableActions());
		}

		for (Exit exit : here.getExits()) {
			Location destination = exit.getDestination();
			if (actorLocations.isAnActorAt(destination)) {
				Actor adjacentActor = actorLocations.actorAt(destination);
				actions.add(adjacentActor.getAllowableActions(actor, exit.getName(), map));
			} else {
				Ground adjacentGround = map.groundAt(destination);
				actions.add(adjacentGround.allowableActions(actor, destination, exit.getName()));
				actions.add(adjacentGround.getMoveAction(actor, destination, exit.getName(), exit.getHotKey()));
			}
		}

		for (Item item : here.getItems()) {
			actions.add(item.getAllowableActions());
		}
		actions.add(new SkipTurnAction());
		
		Action action = actor.playTurn(actions, map, display);
		String result = action.execute(actor, map);
		this.result=result;
		display.println(result);
	}
	
/**
 *  This function override the function from the class World and before processing each actor, it will check if the game should end at this
 * point. If so, the loop will be broken and the program ends
 */
	public void run() {
		if(player == null)
			throw new IllegalStateException();
		
		while (stillRunning()) {
			GameMap playersMap = actorLocations.locationOf(player).map();
			playersMap.draw(display);
			for (Actor actor : actorLocations) {
				processActorTurn(actor);
				//check if the result means game should end
				boolean checkResult=(this.result=="Game Over." || this.result=="Player wins." || this.result=="Player loses.");
				if (checkResult && actor instanceof StunnedPlayer) {
					break;
				}
			}
		}
		//display.println(endGameMessage());
	}
	
}
