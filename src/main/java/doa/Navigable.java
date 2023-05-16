package doa;

import java.util.ArrayList;

public interface Navigable {

		  public void setDestination(Aeroport destination);
		  public ArrayList<Aeroport>  calculateRoute(String Ad,String Aa);
//		  public void followRoute();
//		  public void updatePosition();
//		  public void requestClearance();
//		  public void checkWeather();
}
