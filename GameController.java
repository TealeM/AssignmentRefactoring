
/*
	Assignment 3, CPSC 219, Tutorial Section: T04
	
	Author: Teale Masrani, 30000053
	Verion 3: Debug messages added, documentation added before methods.

	Features: 
		-This is a racing game, there is an SUV in an arctic track and a Sports car in a desert track.
		-There is a 10% chance each turn of a blizzard hitting the arctic track, or a heatwave hitting the desert track.
		-The SUV has 50 units of fuel to start, uses 3 units each turn, and moves 1 unit in all wheel drive (AWD) mode, or 2 units in normal drive mode.
		-If a blizzard occurs and the SUV is in AWD mode, it will still move 1 unit, if it is in normal drive mde, it will not move. Still 3 units of fuel will be used in a blizzard.
		-The Sports car has 30 units of fuel to start, uses 4 units if there is a heatwave, or 2 units when there is not. It moves 3 units each turn if there is a heat wave or not. 
		-Whichever car gets to the end of their track first wins. If both cars run out of fuel before this happens, it is a draw. If user quits early, it is a draw.
		-There is access to a cheat menu from eaith the SUV or Sports car turn, which can be used to maually manipulate the fuel or location of each car, or to manually invoke a blizzard or heatwave.
		-There a debug mode that can be turned on or off from the cheat menu, when on, it will display debugging messages throughout the program.
	Limitations:
		-Cars cannot move from one track to another.
		-Speed of cars cannot be manipulated by cheat, just fuel and current location.
		-Size of track cannot be manipulated.
	
	
    
*/

import java.util.Scanner;

public class GameController
{
	private Scanner in;
	
	private ArcticTrack arcTrack;
	private DesertTrack desTrack;
	private SUV anSUV;
	private Sports aSports;
	private Displayer aDisplayer;
	private GameEnder aGameEnder;
	
	
	private int cheatAffectingSports;
	private int cheatAffectingSUV;
	private int newFuelSports;
	private int newFuelSUV;
	
	
	//Constructor method for class, instantiates tracks and cars.
	public GameController()
    {
		in = new Scanner(System.in);
		
		arcTrack = new ArcticTrack();
        desTrack = new DesertTrack();
		anSUV = new SUV();
		aSports = new Sports();
		aDisplayer = new Displayer();
		aGameEnder = new GameEnder(anSUV, aSports, arcTrack, desTrack);
	}

	// Start: Begins loop for turns, ends loop if one or both cars win, or they both run out of fuel
	public void start()
	{
		aDisplayer.displayInitialInstructions();
		arcTrack.display();
		desTrack.display();
		
		while (true)
		{		
			System.out.println("NEW ROUND! \n");
			if (anSUV.isDead == false)
			{	
				if (anSUV.getFuel() <=0){
					System.out.println("\nSUV is out of fuel and cannot move.\n");
					anSUV.isDead = true;}
				else
					runTurnSUV();
			}
			if (aSports.isDead == false)
			{	
				if (aSports.getFuel() <=0){
					System.out.println("\nSports car is out of fuel and cannot move.\n");
					aSports.isDead = true;}
				else
					runTurnSports();
			}
			
			//Check if the game is over
			aGameEnder.checkIfOver();
			
			arcTrack.display();
			desTrack.display();
		}
	}

	//Run the SUV turn: display and process menu
	private void runTurnSUV()
	{
		char selection;

		selection = aDisplayer.displayMenuSUV();
		processMenuSUV(selection);
		System.out.println("");	
	}
	
	//Run the Sports car turn: display and process menu
	private void runTurnSports()
	{
		char selection;
		
		selection = aDisplayer.displayMenuSports();
		processMenuSports(selection);
		System.out.println("");
	}
	
	//SUV menu case 'd' or 'a': move SUV according to AWD selection, and if a cheat is being acted on the SUV or not
	private void SUVcaseAorD(boolean AWD)
	{
		if (cheatAffectingSUV != 5)
			arcTrack.decideRandBlizzard();
			
		if (cheatAffectingSUV == 4){
			System.out.println("\nSUV is moving FROM the location set by the cheat. ");
			arcTrack.move(AWD);
			cheatAffectingSUV = -1;}
			
		else if (cheatAffectingSUV == 2){
			System.out.println("\nSUV fuel has been set to " +newFuelSUV +", due to cheat.");
			arcTrack.move(AWD, newFuelSUV);
			cheatAffectingSUV = -1;}	
		
		else {
			arcTrack.move(AWD);}
			
		if (anSUV.getFuel() <=0){
			System.out.println("\nSUV is out of fuel and cannot move.\n");
			anSUV.isDead = true;}			
	}
	
	//Sports car menu case 'd': move sports car according to if a cheat is being acted on it or not
	private void SportsCaseD()
	{
		if (cheatAffectingSports != 6)
			desTrack.decideRandHeatwave();
			
		if (cheatAffectingSports == 3){
			System.out.println("\nSports car is moving FROM the location set by the cheat. ");
			desTrack.move();
			cheatAffectingSports = -1;}
			
		else if (cheatAffectingSports == 1){
			System.out.println("\nSports car fuel has been set to " +newFuelSports +", due to cheat. ");
			desTrack.move(newFuelSports);
			cheatAffectingSports = -1;}
		
		else{
			desTrack.move();}
			
		if (aSports.getFuel() <=0){
			System.out.println("\nSports car is out of fuel and cannot move.\n");
			aSports.isDead = true;}		
	}
	
	
	//Process SUV menu: Carry out actions for whichever SUV option user selected
	private void processMenuSUV(char selection)
	{	
		switch(selection)
		{
			case 'a':
				SUVcaseAorD(true);
				break;
	
			case 'd':				
				SUVcaseAorD(false);
				break;
	
			case 'q':
				aGameEnder.quitEarly();
				break;
			
			case 'c':
				invokeCheatMenu();
				break;	
	
			default:
				System.out.println("Please enter 'a', 'd' or 'q'");
		}
	}
	
	
	//Process Sports car menu: Carry out actions for whichever Sports car option user selected
	private void processMenuSports(char selection)
	{
		switch(selection)
		{
			case 'd':	
				SportsCaseD();		
				break;
	
			case 'q':
				aGameEnder.quitEarly();
				break;
			
			case 'c':
				invokeCheatMenu();
				break;	
		}
	}

	// When user invokes cheat menu: display and process menu, and loop until valid input is entered
	private void invokeCheatMenu()
	{
		int selection;
		selection = aDisplayer.displayMenuCheat();
		processMenuCheat(selection);
	}
	
	//Cheat menu OPTION 1
	private void cheatFuelSports()
	{
		System.out.print("Set new fuel value (non-negative value only): ");
		newFuelSports = in.nextInt();
		aSports.setFuel(newFuelSports);
	}
	//Cheat menu OPTION 2
	private void cheatFuelSUV()
	{
		System.out.print("Set new fuel value (non-negative value only): ");
		newFuelSUV = in.nextInt();
		anSUV.setFuel(newFuelSUV);	
	}
	//Cheat menu OPTION 3
	private void cheatDesertLocation()
	{
		System.out.print("Set new location (0 to 24): ");
		int newLocation = in.nextInt();
		desTrack.setLocation(newLocation);
		System.out.print("\nSports car is now at location at index " +desTrack.getLocation() +"\n");
	}
	//Cheat menu OPTION 4
	private void cheatArcticLocation()
	{
		System.out.print("Set new location (0 to 24): ");
		int newLocation = in.nextInt();
		arcTrack.setLocation(newLocation);
		System.out.print("\nSUV is now at location at index" +arcTrack.getLocation() +"\n");
	}
	//Cheat menu OPTION 5
	private void cheatBlizzard()
	{
		System.out.println("\nCausing a blizzard in the artic track");
		arcTrack.setBlizzard(true);
	}
	//Cheat menu OPTION 6
	private void cheatHeatwave()
	{
		System.out.println("\nCausing a heat wave in the desert track");
		desTrack.setHeatwave(true);
	}
	
	//Process cheat menu: Carry out actions for whichever cheat option user selected
	private void processMenuCheat(int selection)
	{
		switch(selection)
		{
			case 1:
				cheatFuelSports();
				cheatAffectingSports = selection;
				break;
				
			case 2:
				cheatFuelSUV();
				cheatAffectingSUV = selection;
				break;
				
			case 3:
				cheatDesertLocation();
				cheatAffectingSports = selection;
				break;
				
			case 4:
				cheatArcticLocation();
				cheatAffectingSUV = selection;
				break;
				
			case 5:
				cheatBlizzard();
				cheatAffectingSUV = selection;
				break;
				
			case 6:
				cheatHeatwave();
				cheatAffectingSports = selection;
				break;
		}
	}
}