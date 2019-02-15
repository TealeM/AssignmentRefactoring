import java.util.Random;

public class ArcticTrack extends Track
{
	private SUV anSUV;
	private int location;
	private boolean blizzard;
	
	
	//Constructor method for class, instantiates an SUV
	public ArcticTrack()
	{
		location = 0;
		anSUV = new SUV();	
	}
	
	//Display the arctic track
	public void display()
	{
		super.setLocation(anSUV, location);
		System.out.println("ARCTIC TRACK");
		super.display();
	}
	
	//Move car with only AWD parameter 
	public void move(boolean AWD)
	{
		if (location <=24)
			setToNull(location);
		int distanceMoved;
		distanceMoved = anSUV.move(AWD, blizzard);
		location = location + distanceMoved;
		blizzard = false;
	}
	
	//Move car with AWD parameter and fuel parameter (from cheat)
	public void move(boolean AWD, int fuel)
	{
		anSUV.setFuel(fuel);
		setToNull(location);
		int distanceMoved;
		distanceMoved = anSUV.move(AWD, blizzard, fuel);
		location = location + distanceMoved;
		blizzard = false;
	}
	
	//Create 10% chance for a blizzard`
	public void decideRandBlizzard()
	{
		Random generator = new Random();
		int num = generator.nextInt(10)+1;
		if (num == 1)
			blizzard = true;
		else
			blizzard = false;
	}
	
	//Mutator method for blizzard state
	public void setBlizzard(boolean mode)
	{
		blizzard = mode;
	}
	
	//Accessor method for location
	public int getLocation()
	{
		return (location);
	}
	//Mutator method for location
	public void setLocation(int index)
	{
		setToNull(location);
		if ((index >= 0) && (index < Track.SIZE))
			location = index;
		else
		{
			if ((index < 0) || (index >= Track.SIZE))
			location = Track.SIZE -1 ;	    
		}
    }
	
	//Accessor method for SUV fuel
	public int getSUVFuel()
	{
		return(anSUV.getFuel());
	}
	
	//Mutator method for SUV fuel
	public void setSUVFuel(int fuel)
	{
		anSUV.setFuel(fuel);
	}
}