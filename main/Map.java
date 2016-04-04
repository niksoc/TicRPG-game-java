package game.main;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import game.exceptions.NoWayE;
import game.objects.Room;
import game.states.Battle;
import game.util.GC;
import game.util.GC.DIR;

public class Map {
	private int[][] map;
	private Room currentRoom;
	int curX, curY;
	List<Room> rooms;

	public Map() {
		map = new int[GC.SIZE_OF_MAP][GC.SIZE_OF_MAP];
		rooms = new LinkedList<Room>();
		curX = curY = GC.SIZE_OF_MAP / 2;
		generateRoom();
	}

	public Room roomToDir(DIR dir) {
		switch (dir) {
		case NORTH:
			return (map[curX][curY + 1] != 0) ? rooms.get(map[curX][curY + 1]-1) : null;
		case SOUTH:
			return (map[curX][curY - 1] != 0) ? rooms.get(map[curX][curY - 1]-1) : null;
		case EAST:
			return (map[curX + 1][curY] != 0) ? rooms.get(map[curX + 1][curY]-1) : null;
		default:
			return (map[curX - 1][curY] != 0) ? rooms.get(map[curX - 1][curY]-1) : null;
		}

	}
	public void generateRoom(){
		currentRoom = Room.generateRoom();
		int[] chk = new int[] { 1, 1, 1, 1 };
		for (int i = 0; i < 4; i++) {
			Room adj = roomToDir(DIR.getDir(i));
			if (adj != null) {
				if (adj.isDoor(DIR.getOppDir(DIR.getDir(i)))) {
					currentRoom.addDoor(DIR.getDir(i));
				}
				chk[i]=0;
			}
		}
		int r;
		do {
			r = (int) (GC.random.nextDouble() * 10 % 4);
		} while (chk[r]==0 && (chk[0]+chk[1]+chk[2]+chk[3])!=0);
		currentRoom.addDoor(GC.DIR.getDir(r));
		map[curX][curY] = currentRoom.getRoomNumber();
		rooms.add(currentRoom);
	}

	public void movement(GC.DIR dir) throws NoWayE {
		if (!currentRoom.isDoor(dir))
			throw new NoWayE();
		switch (dir) {
		case NORTH:
			curY++;
			break;
		case SOUTH:
			curY--;
			break;
		case EAST:
			curX++;
			break;
		case WEST:
			curX--;
			break;
		}
		if (map[curX][curY] == 0) {
		generateRoom();
		} else {
			currentRoom = rooms.get(map[curX][curY] - 1);
		}
		Random r=GC.random;
		if(currentRoom.isMonster()){
			if(!currentRoom.getMonster().isAlive()) 
				if(r.nextDouble()>0.7d && currentRoom.getMonster().getType()=="Ghoul")
					currentRoom.getMonster().resurrect();
			if(currentRoom.getMonster().isAlive()) 
			TicRPG.game.setBattle(currentRoom.getMonster());
		}
	}

	public Room getCurrentRoom() {
		return currentRoom;
	}
}
