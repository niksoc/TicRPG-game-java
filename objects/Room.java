package game.objects;

import java.util.LinkedList;
import java.util.List;

import game.attributes.RoomAttribute;
import game.util.GC;
import game.util.GC.DIR;
import game.util.SF;

public class Room {
	protected static int roomCount;
	protected int roomNumber;
	protected Monster monster;
	protected boolean isMonster;
	protected List<RoomAttribute> attr;
	protected List<Item> items;
	protected Wall[] wall;
	protected String description;
	protected String mDescription;

	public Room() {
		wall = new Wall[4];
		for (int i = 0; i < 4; i++)
			wall[i] = new Wall();
		roomCount++;
		roomNumber = roomCount;
		GC.roomCount = roomCount;
		isMonster = false;
		attr = new LinkedList<RoomAttribute>();
		items = new LinkedList<Item>();
	}

	public static int getRoomCount() {
		return roomCount;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void add(Monster m) {
		monster = m;
		isMonster = true;
	}

	public boolean isMonster() {
		return isMonster;
	}

	public Monster getMonster() {
		return monster;
	}

	public void decorate() {

	}

	public void addDoor(DIR dir) {
		wall[dir.id].addDoor();
		addToDescription("There is a door to the " + SF.dirToS(dir));

	}

	public void generateDescription() {
		if (isMonster)
			mDescription = monster.getDescription();
		else
			mDescription = "";
	}

	public String getDescription() {
		generateDescription();
		return mDescription + "\n" + description;
	}

	public boolean isDoor(GC.DIR dir) {
		return wall[dir.id].isDoor();
	}

	public void populate() {
		double r = GC.random.nextDouble();
		if (r > 0.7d && roomNumber != 1) {
			add(Monster.generateMonster());
		}
	}

	public void addToDescription(String s) {
		if (description == null)
			description = s;
		else
			description += "\n" + s;
	}

	public static Room generateRoom() {
		Room room = new Room();
		room.populate();
		room.decorate();
		return room;

	}
}
