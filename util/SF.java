package game.util;

import game.exceptions.InvalidCommandE;
import game.util.GC.DIR;

public class SF {
	public static String outOf(int a,int max){
		return a+"/"+max;
	}
	public static DIR sToDir(String s) throws InvalidCommandE{
		switch(s){
		case "north":return DIR.NORTH;
		case "south":return DIR.SOUTH;
		case "east":return DIR.EAST;
		case "west":return DIR.WEST;
		}
		throw new InvalidCommandE();
	}
	public static String dirToS(DIR dir){
		switch(dir){
		case NORTH:return "north";
		case SOUTH:return "south";
		case EAST:return "east";
		default:return "west";
		}
	}
}
