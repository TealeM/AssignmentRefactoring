import java.util.Random;

public class DesertTrack extends Track
{
	private Sports aSports;
	private int location;
	private boolean heatwave;
	
	//Constructor method for class, instantiates a Sports car
	public DesertTrack()
	{
		location = 0;
		aSports = new Sports();	
	}
	//Display the desert track
	public void display()
	{
		super.setLocation(aSports, location);
		System.out.println("DESERT TRACK");
		super.display();
	}
	
	//Move car with no parameters 
	public void move()
	{
		if (location <=24)
			setToNull(location);
		int distanceMoved = aSports.move(heatwave);
		location = location + distanceMoved;
		heatwave = false;
	}
	//Move car with fuel parameter (from cheat)
	public void move(int fuel)
	{	
		aSports.setFuel(fuel);
		setToNull(location);
		int distanceMoved = aSports.move(heatwave, fuel);
		location = location + distanceMoved;
		heatwave = false;
	}
	
	//Create 10% chance for a heatwave`
	public void decideRandHeatwave()
	{
		Random generator = new Random();
		int num = generator.nextInt(10)+1;
		if (num == 1)
			heatwave = true;
		else
			heatwave = false;
	}
	
	//Mutator method for heatwave state
	public void setHeatwave(boolean mode)
	{
		heatwave = mode;
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
}