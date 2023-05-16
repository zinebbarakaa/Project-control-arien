package doa;

public interface FuelConsommable {
	
	
	  public double getFuelLevel();
	  public double getFuelConsumptionPerSecond();
	  public void 	setFuelConsumptionPerSecond(double consumption);
	  public void 	alertFuelLow();
	  public void consumeFuel(double velocity);
//	public void refillFuel();
//	public  void calculateMaxDistance();
//	public  void scheduleRefuelingStop();
//	public void checkFuelLevel();
}
