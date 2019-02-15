import java.util.Scanner;

public class Displayer {
	
	private Scanner in;
	
	//Constructor
	public Displayer()
	{
		in = new Scanner(System.in);
	}
	
	//Print out the instructions for the whole simulation
	public void displayInitialInstructions()
	{
		System.out.println("\nINSTRUCTIONS: \n\nIn this racing game, there is an SUV in an arctic track and a Sports car in a desert track. \n\nEach turn you can choose if the SUV is to drive in AWD mode \nor normal drive mode, and you can make the Sports car drive too. \n\nEach turn there is a 10% chance of a blizzard hitting the arctic track \nor a heatwave hitting the desert track. \nThese occurances impede the driving of the SUV or the Sports car. \n\nWhichever gets to the end of their track first wins. \nIf both cars run out of fuel before this happens, it is a draw. \nIf you quit early, it is a draw. \n\n");
	}
	
	//Display SUV menu: print options, and read and return user's selection
	public char displayMenuSUV()
	{
		String line;
		char selection;
		
		System.out.println("SUV driving options");
		System.out.println("(a)ll wheel drive mode");
		System.out.println("(d)rive normally");
		System.out.println("(q)uit simulation");
		System.out.print("Enter selection: ");
		line = in.nextLine();
		if (line.length() == 0)
			line = in.nextLine();
		
		selection = line.charAt(0);
		
		//Error checking for bad input
		while (selection != 'a' && selection!= 'A' && selection != 'd' && selection!= 'D' && selection!= 'c' && selection!= 'C' && selection!= 'q'&& selection!= 'Q')
		{
			System.out.println("Please enter 'a', 'd' or 'q'");
			line = in.nextLine();
			if (line.length() == 0)
				line = in.nextLine();
			
			selection = line.charAt(0);
		}
		selection = Character.toLowerCase(selection);
		return selection;
	}
	
	//Display Sports car menu: print options, and read and return user's selection
	public char displayMenuSports()
	{
		String line;
		char selection;

		System.out.println("Sportscar driving options");
		System.out.println("(d)rive normally");
		System.out.println("(q)uit simulation");
		System.out.print("Enter selection: ");
		line = in.nextLine();
		if (line.length() == 0)
			line = in.nextLine();
		selection = line.charAt(0);
		
		//Error checking for bad input
		while (selection != 'd' && selection!= 'D' && selection!= 'c' && selection!= 'C' && selection!= 'q'&& selection!= 'Q')
		{
			System.out.println("Please enter 'a', 'd' or 'q'");
			line = in.nextLine();
			if (line.length() == 0)
				line = in.nextLine();
			
			selection = line.charAt(0);
		}
		selection = Character.toLowerCase(selection);
		return selection;
	}
	
	//Display Cheat menu: print options for cheat menu
	public int displayMenuCheat()
	{
		int selection;
		System.out.println("\nCHEAT MENU SELECTION");
		System.out.println("(1) Change fuel of sports car");
		System.out.println("(2) Change fuel of SUV car");
		System.out.println("(3) Change location of sports car");
		System.out.println("(4) Change location of suv car");
		System.out.println("(5) Make a blizzard in the artic track");
		System.out.println("(6) Make a heat wave in the desert track");
		System.out.print("Enter selection: ");
		
		selection = in.nextInt();
		
		while (selection<0 || selection>6)
		{
			System.out.println("Please select a number from 1 to 6");
			selection = in.nextInt();
		}
		
		return selection;
	}
	
	
}
