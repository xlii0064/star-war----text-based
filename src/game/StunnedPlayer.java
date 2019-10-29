
package game;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.MoveActorAction;
import edu.monash.fit2099.engine.Player;
import edu.monash.fit2099.engine.SkipTurnAction;

public class StunnedPlayer extends Player{
	int stunnedTurns=0;
	boolean stunned=false;
	GameMap earth,moon;

	int i=0;
	/**
	 * Constructor.
	 *
	 * @param name Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param priority How early in the turn the player can act
	 * @param hitPoints Player's starting number of hitpoints
	 */
	public StunnedPlayer(String name, char displayChar, int priority, int hitPoints,GameMap init,GameMap target) {
		super(name, displayChar, priority, hitPoints);
		this.moon=target;
		this.earth=init;
		// TODO Auto-generated constructor stub
	}
	/**
	 * Play a turn. Doing this means displaying a menu to the user and getting their selected option.
	 * Check player stunned or not. return a different menu.
	 * @param actions the actions to display
	 * @param map the map to display
	 * @param display the object that performs the console I/O
	 * @return the Action that the user selects
	 */
	@Override
	public Action playTurn(Actions actions, GameMap map, Display display) {
		Location here=map.locationOf(this);
		//check if the player is still awake or not
		if (!this.isConscious()) {
			return new LoseAction(this);
		}
		//check if the player has win the game
		if(here.map()!=moon) {
			for(int i=0;i<this.getInventory().size();i++) {
				if(this.getInventory().get(i).toString().equals("Sleeping Yugo Maxx")) {
					return new WinAction(this);
				}
			}
		}
		//check if the player can stay on the moon
		if (here.map()==moon) {
			i=shouldGoBack();
			if (i==-1) {
				return new MoveActorAction(earth.at(5, 5), "back to earth beacuse of lacking of oxygen");
			}
			else {
				((OxygenTankItem) this.getInventory().get(i)).use();
			}
		}
		//check if the player is stunned
		if (stunned && stunnedTurns!=2) {
			stunnedTurns+=1;
			return new SkipTurnAction();
		}
		if (stunned && stunnedTurns==2) {
			stunnedTurns=0;
			stunned=false;
			if (checkRocket(here,actions) && here.map()!=moon &&canGoToMoon()) {
				actions.add(new MoveActorAction(moon.at(5, 5), "to moon"));
			}
			actions.add(new QuitGameAction(this));
			return showMenu(actions,display);
		}
		if (checkRocket(here,actions)&& here.map()!=moon &&canGoToMoon()) {
			actions.add(new MoveActorAction(moon.at(5, 5), "to moon"));
		}
		actions.add(new QuitGameAction(this));
		return showMenu(actions, display);
		
	}
	/**
	 * @param a array of item.
	 * */
	public void setInventory(List<Item> Inventory) {
		inventory=Inventory;
	}
	public int shouldGoBack() {
		List<Item> items = new ArrayList<>();
		items=this.getInventory();
		for (int i=0;i<items.size();i++) {
			if (items.get(i) instanceof OxygenTankItem) {
				if (!((OxygenTankItem) items.get(i)).isEmpty()) {
					return i;
				}
				else {
					items.remove(i);
				}
			}
		}
		return -1;
	}
	public boolean canGoToMoon() {
		boolean enoughOxygen=false;
		boolean spaceSuit=false;
		List<Item> items = new ArrayList<>();
		items=this.getInventory();
		for (Item item:items) {
			if (item instanceof SpaceSuit) {
				spaceSuit=true;
			}
			if (item instanceof OxygenTankItem) {
				if (!((OxygenTankItem) item).isEmpty()) {
				enoughOxygen=true;
				}
			}
		}
		if (spaceSuit && enoughOxygen ) {
			return true;
		}
		System.out.println("You need to have space suit and oxygen tank first before going to the moon.");
		return false;
	}
	public boolean checkRocket(Location location,Actions actions) {
		//if loaction have rocket furniture Item
		List<Item> items = new ArrayList<>();
		items=location.getItems();
		for (Item item:items) {
			if (item.getDisplayChar()=='*') {
				actions.remove(new BuildRocketAction(this));
				return true;
			}
		}
		return false;
	}
	public int getStunedTurns() {
		return stunnedTurns;
	}
	public boolean getStatus() {
		return stunned;
	}
	/**
	 * Set player status. stunned or not.
	 * @param boolean Stunned or not.
	 * */
	public void setStatus(Boolean newStatus) {
		stunned=newStatus;
	}
}

