public class Car
{
    public static final int STARTING_FUEL = 40;
    public static final int STANDARD_DISTANCE = 2;
    public static final int CONSUMPTION_RATE = 2;

    private char appearance;
    private int fuel;

    public boolean isDead;
    
    public Car()
    {
	    setAppearance('c');
	    fuel = STARTING_FUEL;
    }

    // As the car moves, this class or child classes can specify for that type of car
    // how much fuel to consume.
    protected void consumeFuel(int amount)
    {
	   if (amount < 0)
	       fuel = 0;
	   else
	       fuel = fuel - amount;
    }

    public int getFuel()
    {
	   return fuel;
    }

    // Allows external queries of the fuel state of a particular car.
    public boolean isEmpty()
    {
  	   if (fuel <= 0)
	       return true;
	   else
	       return false;
    }

    public void setAppearance (char anAppearance)
    {
 	   appearance = anAppearance;
    }

    public void setFuel (int newFuel)
    {
	   if (newFuel < 0)
	       System.out.println("Fuel cannot be set to a negative value");
	   else
	       fuel = newFuel;
    }

    public String toString() 
    {
  	   String s = "";
	   s = s + appearance;
	   return s;
    }
}