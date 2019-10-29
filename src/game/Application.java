package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.FancyGroundFactory;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.MoveActorAction;
import edu.monash.fit2099.engine.Player;
import edu.monash.fit2099.engine.World;

public class Application {

	public static void main(String[] args) {
		NewWorld world = new NewWorld(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Floor(), new Wall(),new Doors(),new RocketPad(),
				new LunarSoil(),new Pool(),new OxygenDispenser());
		GameMap gameMap,moonMap;
		List<String> map = Arrays.asList(
				".......................",
				"........#####........#.",
				".....................#.",
				".....................#.",
				"..#######D####.......#.",
				".....................#.",
				".....................#.",
				".............#####.....",
				".............#=..D.....",
				".............#####.....",
				"......O....!...........");
		gameMap = new GameMap(groundFactory, map);
		world.addMap(gameMap);
		
		List<String> moon = Arrays.asList(
		"```````````````````````",
		"````````#####````````#`",
		"`````````````````````#`",
		"`````````````````````#`",
		"``#######D####```````#`",
		"`````````````````````#`",
		"`````````````````````#`",
		"`````````````#####`````",
		"`````````````#```D`````",
		"`````````````#####`````",
		"```````````````````````");
		
		moonMap = new GameMap(groundFactory, moon);
		world.addMap(moonMap);
		
		//Earth
		StunnedPlayer player = new StunnedPlayer("Player", '@', 1, 100,gameMap,moonMap);
		world.addPlayer(player, gameMap,8,12);
		
		Grunt grunt = new Grunt("Mongo", player);
		gameMap.addActor(grunt, 16, 5);
		Grunt grunt2 = new Grunt("Norbert", player);
		gameMap.addActor(grunt2,  1, 1);
		
		Goons goon=new Goons("Cute",player);
		gameMap.addActor(goon,4,10);
		goon.actionFactories.add(new InsultBehaviour());

		Ninjas ninjas = new Ninjas("Ninjas",player);
		ninjas.actionFactories.add(new StunBehaviour(player));
		gameMap.addActor(ninjas,6,2);
		
		Q q = new Q("q",player);
		gameMap.addActor(q,5,10);
		
		DoctorMaybe m = new DoctorMaybe("maybe",player);
		gameMap.addActor(m,15,8);
		
		RocketEngineItem rocketEngine = new RocketEngineItem();
		m.addItemToInventory(rocketEngine);
		
		KeysItem key = new KeysItem();
		goon.addItemToInventory(key);
		
		RocketPlanItem rp=new RocketPlanItem();
		gameMap.addItem(rp,0,5);
		
		SpaceSuit ss=new SpaceSuit();
		gameMap.addItem(ss,5,0);

		//moon
		Item moonRocket = Item.newFurniture("Rocket", '*');
		moonMap.addItem(moonRocket,5,5);
		moonRocket.getAllowableActions().add(new MoveActorAction(gameMap.at(10, 10), "back to earth"));
		
		WaterPistolItem wp=new WaterPistolItem();
		moonMap.addItem(wp,9,5);
		YugoMaxx yugo=new YugoMaxx();
		moonMap.addActor(yugo,15,1);
		
		Grunt moonGrunt = new Grunt("Moon Mongo", player);
		moonMap.addActor(moonGrunt, 16, 8);
		Grunt moonGrunt2 = new Grunt("Moon Morbert", player);
		moonMap.addActor(moonGrunt2,  1, 1);
		
		
		Goons moonGoon=new Goons("Moon Mute",player);
		moonMap.addActor(moonGoon,4,10);
		moonGoon.actionFactories.add(new InsultBehaviour());

		Ninjas moonNinjas = new Ninjas("Minjas",player);
		moonNinjas.actionFactories.add(new StunBehaviour(player));
		moonMap.addActor(moonNinjas,6,2);
			
		world.run();
	}
}
