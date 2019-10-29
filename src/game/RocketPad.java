package game;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.demo.KickAction;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.MoveActorAction;


public class RocketPad extends Ground {
	


	public RocketPad() {
		super('!');
		
	}
	@Override
	public Actions allowableActions(Actor actor, Location location, String direction){
		Actions list = new Actions();
		list.add(new BuildRocketAction(actor));
		
		return list;
	}
}
