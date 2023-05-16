package doa;

import java.util.List;

public interface Flayable {

	
	  public void ajouterAvionPiste(Avion avion);

	  public void takeOff(Avion a,int i,List<Coordinate> path);
	  public void fly();
	  public void land(Vol a,List<Coordinate> path ,int i);
	  public  void ascend(List<Coordinate> path,int i);
	  public void descend(List<Coordinate> path,int i);
}
