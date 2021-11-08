package game;

import java.util.*;

import engine.*;


public class Application {
	public static void main(String[] args) {
		WorldAccessible world = new WorldAccessible(new Display());

		FancyGroundFactory earthGroundFactory = new FancyGroundFactory(new Floor(), new Wall(),new RocketPad(), new Door(), new OxygenDispenser(), new WaterPools());
		FancyGroundFactory moonGroundFactory = new FancyGroundFactory(new LunarFloor(), new RocketPad());
		GameMap earthLairMap;
		GameMap moonLairMap;

		List<String> mapOfEarthLair = Arrays.asList(
				".......................",
				"....#####....######....",
				"....#...#....#....#....",
				"....#...|....#....#....",
				"...&#####....##|###....",
				".......................",
				".......................",
				".......................",
				"...~~~....O............",
				"...~~~.................",
				".......................");
		earthLairMap = new GameMap(earthGroundFactory, mapOfEarthLair);
		world.addMap(earthLairMap);

		List<String> mapOfMoonLair = Arrays.asList(
				",,,,,,,,,,,,,,,,,,,,,,",
				",,,,,,,,,,,,,,,,,,,,,,",
				",,,,,,,,,,,,,,,,,,,,,,",
				",,,,,,,,,,,,,,,,,,,,,,",
				",,,,,,,,,,,,,,,,,,,,,,",
				",,,,,,,,,,,,,,,,,,,,,,",
				",,,,,,,,,,O,,,,,,,,,,,",
				",,,,,,,,,,,,,,,,,,,,,,",
                ",,,,,,,,,,,,,,,,,,,,,,",
				",,,,,,,,,,,,,,,,,,,,,,");
		moonLairMap = new GameMap(moonGroundFactory, mapOfMoonLair);
		world.addMap(moonLairMap);

		AlternatePlayer player = new AlternatePlayer("Player", '@', 1, 200);
		world.addPlayer(player, earthLairMap, 2, 2);

		// Add enemies into the map
		Grunt grunt = new Grunt("Mongo", player);
		earthLairMap.addActor(grunt, 0, 0);
		Grunt grunt2 = new Grunt("Norbert", player);
		earthLairMap.addActor(grunt2,  10, 10);
		DoctorMaybe doctorMaybe = new DoctorMaybe();
		earthLairMap.addActor(doctorMaybe, 16, 2);

		Goon goon = new Goon ("Chris", player);
		earthLairMap.addActor(goon, 21,10);
		Goon goon2 = new Goon ("Thomas", player);
		earthLairMap.addActor(goon2, 22, 2);

		Ninja ninja = new Ninja ("Madara", player);
		earthLairMap.addActor(ninja, 19, 5);
		Ninja ninja2 = new Ninja ("Obito", player);
		earthLairMap.addActor(ninja2, 0, 8);

		// Add friendly NPC into the map
		Q q = new Q();
		earthLairMap.addActor(q, 19, 9);

		// Add items into the map
		RocketPlans rocketPlans = new RocketPlans();
		earthLairMap.addItem(rocketPlans, 6, 2);
        SpaceSuit spaceSuit = new SpaceSuit();
        earthLairMap.addItem(spaceSuit, 16, 10);


		// Add enemies on moon
		YugoMaxx yugoMaxx = new YugoMaxx();
		moonLairMap.addActor(yugoMaxx,21,0);	// placed 10 squares both horizontally and vertically away from the rocket
		Grunt grunt3 = new Grunt("Rango", player);
		moonLairMap.addActor(grunt3,  0, 3);
		Grunt grunt4 = new Grunt("Dreadlock", player);
		moonLairMap.addActor(grunt4,  8, 8);
		Goon goon3 = new Goon ("Crock", player);
		moonLairMap.addActor(goon3, 15, 4);
		Ninja ninja3 = new Ninja ("Kabuto", player);
		moonLairMap.addActor(ninja3, 8, 4);

		// Add items on moon
        Rocket rocket = new Rocket(player);
        moonLairMap.addItem(rocket, 10, 6);
        WaterPistol waterPistol = new WaterPistol();
		moonLairMap.addItem(waterPistol,1,6);

		world.run();
	}
}
