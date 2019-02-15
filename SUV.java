public class SUV extends Car
{
	
	private final int STARTING_FUEL = 50;
	private final int CONSUMPTION_RATE = 3;

	private int distanceMoved; 
	private int fuel = STARTING_FUEL;
	
	//Constructor method for class, sets appearance 
	public SUV()
	{
		setAppearance ('V');
	}
	
	//Subtract the amount of fuel consumed from SUV's fuel
	public void consumeFuel(int amount)
    {
		if (amount < 0)
			fuel = 0;
		else
			fuel = fuel - amount;
    }
	
	//Mutator method for fuel
	public void setFuel(int newFuel)
	{
		fuel = newFuel;
	}
	//Accessor method for fuel
	public int getFuel()
	{
		return(fuel);
	}
	
	//Move SUV with AWD and blizzard parameters
	public int move(boolean AWD, boolean blizzard)
	{
		consumeFuel(CONSUMPTION_RATE);
		if (blizzard == true)
		{
			System.out.print ("Blizzard hits ");
			if (AWD == true)
			{
				System.out.println ("but SUV moves slowly but surely in AWD mode.");
				distanceMoved = 1;
			}
			else
			{
				System.out.println ("and SUV spins its wheels.");
				distanceMoved = 0;
			}
		}
		else
		{
			if (AWD == true)
				distanceMoved = 1;
			else
				distanceMoved = 2;
		}
		System.out.println("Current fuel: " + fuel);
		System.out.println("Fuel use: " + CONSUMPTION_RATE);
		System.out.println("Distance SUV moved: " + distanceMoved);
		return (distanceMoved);
	}
	
	//Move SUV with AWD, blizzard, and fuel parameters (fuel from cheat)
	public int move(boolean AWD, boolean blizzard, int fuel)
	{
		if (blizzard == true)
		{
			System.out.print ("Blizzard hits ");
			if (AWD == true)
			{
				System.out.println ("but SUV moves slowly but surely in AWD mode.");
				distanceMoved = 1;
			}
			else
			{
				System.out.println ("and SUV spins its wheels.");
				distanceMoved = 0;
			}
		}
		else
		{
			if (AWD == true)
				distanceMoved = 1;
			else
				distanceMoved = 2;
		}
		System.out.println("Current fuel: " + fuel);
		System.out.println("Fuel use: Overridden by cheat");
		System.out.println("Distance SUV moved: " + distanceMoved);
		return (distanceMoved);
	}
	
	
}