package game.objects;

import game.attributes.Door;
import game.attributes.WallAttribute;
import game.util.GC;

public class Wall {
	private Door door;
	private WallAttribute attr;
	private boolean isDoor;
	public void addDoor(){
		door=new Door();
		isDoor=true;
	}
	public boolean isDoor(){
		return isDoor;
	}
}
