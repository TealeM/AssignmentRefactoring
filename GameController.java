
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
	
	private String lineSUV;
	private String lineSports;
	private char selectionSUV;
	private char selectionSports;
	private int selectionCheat;
	private int selectionCheatforSports;
	private int selectionCheatforSUV;
	private int newFuelSports;
	private int newFuelSUV;

	private boolean end = false;
		
	
	//Constructor method for class, instantiates tracks and cars.
	public GameController()
    {
		in = new Scanner(System.in);
		
		arcTrack = new ArcticTrack();
        desTrack = new DesertTrack();
		anSUV = new SUV();
		aSports = new Sports();
	}
	
	//Print the instructions for the simulations
	private void displayInstructions()
	{
		System.out.println("\nINSTRUCTIONS: \n\nIn this racing game, there is an SUV in an arctic track and a Sports car in a desert track. \n\nEach turn you can choose if the SUV is to drive in AWD mode \nor normal drive mode, and you can make the Sports car drive too. \n\nEach turn there is a 10% chance of a blizzard hitting the arctic track \nor a heatwave hitting the desert track. \nThese occurances impede the driving of the SUV or the Sports car. \n\nWhichever gets to the end of their track first wins. \nIf both cars run out of fuel before this happens, it is a draw. \nIf you quit early, it is a draw. \n\nBEGIN!\n");
	}
	
	// Start: Begins loop for turns, ends loop if one or both cars win, or they both run out of fuel
	public void start()
	{
		displayInstructions();
		arcTrack.display();
		desTrack.display();
		
		while (end == false)
		{		
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
			if (anSUV.isDead == true && aSports.isDead == true)	
				bothOutofFuel();
			if (arcTrack.getLocation() >= 24 && desTrack.getLocation() >= 24)
				winBoth();
			if (arcTrack.getLocation() >= 24)
				winSUV();
			if (desTrack.getLocation() >= 24)
				winSports();
			
			System.out.println("NEXT ROUND! \n");
			arcTrack.display();
			desTrack.display();
		}
	}
	//When both cars run out of fuel: print message, display tracks, end program
	private void bothOutofFuel()
	{
		end = true;
		System.out.println("Both cars are out of fuel, simulation is a draw.");
		arcTrack.display();
		desTrack.display();
		System.exit(0);
	}
	//When both cars reach the end together: print message, display tracks, end program
	private void winBoth()
	{
		System.out.println("Both cars have reached the end in the same round, it's a tie!");
		arcTrack.display();
		desTrack.display();
		System.exit(0);
	}
	//When SUV reaches the end: print message, display tracks, end program
	private void winSUV()
	{
		System.out.println("SUV has reached the end first!");
		arcTrack.display();
		desTrack.display();
		System.exit(0);
	}
	//When Sports car reaches the end: print message, display tracks, end program
	private void winSports()
	{
		System.out.println("Sports car has reached the end first!");
		arcTrack.display();
		desTrack.display();
		System.exit(0);
	}
	//When user quits early: print message, display tracks, end program
	private void quitEarly()
	{
		System.out.println("Quitting game before end: simulation is a draw.");
		arcTrack.display();
		desTrack.display();
		System.exit(0);
	}
	//Run the SUV turn: display and process menu, and loop until valid input is entered
	private void runTurnSUV()
	{
		do
		{
			displayMenuSUV();
			processMenuSUV(selectionSUV);
			System.out.println("");
		}
		while (selectionSUV != 'a' && selectionSUV != 'A' && selectionSUV != 'd' && selectionSUV != 'D' && selectionSUV != 'c' && selectionSUV != 'C' && selectionSUV != 'q' && selectionSUV != 'Q');
	
	}
	//Run the Sports car turn: display and process menu, and loop until valid input is entered
	private void runTurnSports()
	{
		do
		{
			displayMenuSports();
			processMenuSports(selectionSports);
			System.out.println("");
		}
		while (selectionSports != 'd' && selectionSports != 'D' && selectionSports != 'c' && selectionSports != 'C' && selectionSports != 'q'&& selectionSports != 'Q');
	
	}
	
	//Display SUV menu: print options, and read input
	private void displayMenuSUV()
	{
		System.out.println("SUV driving options");
		System.out.println("(a)ll wheel drive mode");
		System.out.println("(d)rive normally");
		System.out.println("(q)uit simulation");
		System.out.print("Enter selection: ");
		lineSUV = in.nextLine();
		if (lineSUV.length() == 0)
			lineSUV = in.nextLine();
		selectionSUV = lineSUV.charAt(0);
	}
	//Display Sports car menu: print options, and read input
	private void displayMenuSports()
	{
		System.out.println("Sportscar driving options");
		System.out.println("(d)rive normally");
		System.out.println("(q)uit simulation");
		System.out.print("Enter selection: ");
		lineSports = in.nextLine();
		if (lineSports.length() == 0)
			lineSports = in.nextLine();
		selectionSports = lineSports.charAt(0);
	}
	
	//SUV menu case 'd' or 'a': move SUV according to AWD selection, and if a cheat is being acted on the SUV or not
	private void SUVcaseAorD(boolean AWD)
	{
		if (selectionCheatforSUV != 5)
			arcTrack.decideRandBlizzard();
			
		if (selectionCheatforSUV == 4){
			System.out.println("\nSUV is moving FROM the location set by the cheat. ");
			arcTrack.move(AWD);
			selectionCheatforSUV = -1;}
			
		else if (selectionCheatforSUV == 2){
			System.out.println("\nSUV fuel has been set to " +newFuelSUV +", due to cheat.");
			arcTrack.move(AWD, newFuelSUV);
			selectionCheatforSUV = -1;}	
		
		else {
			arcTrack.move(AWD);}
			
		if (anSUV.getFuel() <=0){
			System.out.println("\nSUV is out of fuel and cannot move.\n");
			anSUV.isDead = true;}			
	}
	
	//Sports car menu case 'd': move sports car according to if a cheat is being acted on it or not
	private void SportsCaseD()
	{
		if (selectionCheatforSports != 6)
			desTrack.decideRandHeatwave();
			
		if (selectionCheatforSports == 3){
			System.out.println("\nSports car is moving FROM the location set by the cheat. ");
			desTrack.move();
			selectionCheatforSports = -1;}
			
		else if (selectionCheatforSports == 1){
			System.out.println("\nSports car fuel has been set to " +newFuelSports +", due to cheat. ");
			desTrack.move(newFuelSports);
			selectionCheatforSports = -1;}
		
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
			case 'A':
			case 'a':
				SUVcaseAorD(true);
				break;
	
			case 'D':
			case 'd':				
				SUVcaseAorD(false);
				break;
	
			case 'Q':
			case 'q':
				quitEarly();
				break;
			
			case 'C':
			case 'c':
				invokeCheatMenu();
				break;	
	
			default:
				System.out.println("Please enter 'a', 'd' or 'q'");
		}
	}
	
	
	//Process Sports car menu: Carry out actions for whichever Sportcs car option user selected
	private void processMenuSports(char selection)
	{
		switch(selection)
		{
			case 'D':
			case 'd':	
				SportsCaseD();		
				break;
	
			case 'Q':
			case 'q':
				quitEarly();
				break;
			
			case 'C':
			case 'c':
				invokeCheatMenu();
				break;	
	
			default:
				System.out.println("Please enter 'd' or 'q'");
		}
	}
	
	
	
	// When user invokes cheat menu: display and process menu, and loop until valid input is entered
	private void invokeCheatMenu()
	{
		do
		{
			displayMenuCheat();
			selectionCheat = in.nextInt();
			processMenuCheat(selectionCheat);
		}
		while (selectionCheat<0 || selectionCheat>6);
	}
	
	//Display Cheat menu: print options for cheat menu
	public void displayMenuCheat()
	{
		System.out.println("\nCHEAT MENU SELECTION");
		System.out.println("(0) Toggle debugging messages on/off");
		System.out.println("(1) Change fuel of sports car");
		System.out.println("(2) Change fuel of SUV car");
		System.out.println("(3) Change location of sports car");
		System.out.println("(4) Change location of suv car");
		System.out.println("(5) Make a blizzard in the artic track");
		System.out.println("(6) Make a heat wave in the desert track");
		System.out.print("Enter selection: ");
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
			case 0:
				if (Debug.on == false)
					Debug.on = true;
				else 
					Debug.on = false;
				break;
				
			case 1:
				cheatFuelSports();
				selectionCheatforSports = selection;
				break;
				
			case 2:
				cheatFuelSUV();
				selectionCheatforSUV = selection;
				break;
				
			case 3:
				cheatDesertLocation();
				selectionCheatforSports = selection;
				break;
				
			case 4:
				cheatArcticLocation();
				selectionCheatforSUV = selection;
				break;
				
			case 5:
				cheatBlizzard();
				selectionCheatforSUV = selection;
				break;
				
			case 6:
				cheatHeatwave();
				selectionCheatforSports = selection;
				break;
				
			default:
				System.out.println("Please select a number from 0 to 6");
		}
	}
}