public class Sports extends Car
{
	
	private final int STARTING_FUEL = 30;
	private final int DISTANCE_MOVED = 3; 
	
	private int consumptionRate;
	private int fuel = STARTING_FUEL;
	
	//Constructor method for class, sets appearance 
	public Sports()
	{
		setAppearance ('P');
	}
	
	//Subtract the amount of fuel consumed from Sports car's fuel
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
	
	//Move Sports car with heatwave parameter
	public int move(boolean heatwave)
	{
		if (heatwave == true)
		{
			System.out.println("A heatwave hammers the desert track.");
			consumptionRate = 4;
		}
		else
			consumptionRate = 2;
	
		consumeFuel(consumptionRate);
		
		System.out.println("Current fuel: " + fuel);
		System.out.println("Fuel use: " + consumptionRate);
		System.out.println("Distance Sports car moved: " + DISTANCE_MOVED);
		return (DISTANCE_MOVED);
	}
	
	//Move Sports car with heatwave, and fuel parameters (fuel from cheat)
	public int move(boolean heatwave, int fuel)
	{
		if (heatwave == true)
		{
			System.out.println("A heatwave hammers the desert track.");
			consumptionRate = 4;
		}
		else
			consumptionRate = 2;
		
		System.out.println("Current fuel: " + fuel);
		System.out.println("Fuel use: Overridden by cheat");
		System.out.println("Distance Sports car moved: " + DISTANCE_MOVED);
		return (DISTANCE_MOVED);
	}
}