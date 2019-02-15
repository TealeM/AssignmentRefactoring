
public class GameEnder
{
	SUV anSUV;
	Sports aSports;
	ArcticTrack arcTrack;
	DesertTrack desTrack;
	
	public GameEnder(SUV establishedSUV, Sports establishedSports, ArcticTrack establishedArcTrack, DesertTrack establishedDesTrack)
	{
		anSUV = establishedSUV;
		aSports = establishedSports;
		arcTrack = establishedArcTrack;
		desTrack = establishedDesTrack;
	}
	
	public void checkIfOver()
	{
		if (anSUV.isDead == true && aSports.isDead == true)	
			bothOutofFuel();
		if (arcTrack.getLocation() >= 24 && desTrack.getLocation() >= 24)
			winBoth();
		if (arcTrack.getLocation() >= 24)
			winSUV();
		if (desTrack.getLocation() >= 24)
			winSports();
	}

	//When both cars run out of fuel: print message, display tracks, end program
	private void bothOutofFuel()
	{
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
	public void quitEarly()
	{
		System.out.println("Quitting game before end: simulation is a draw.");
		System.exit(0);
	}
}
