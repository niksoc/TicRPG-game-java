package game.util;

import java.awt.Color;
import java.util.Random;

import game.exceptions.InvalidCommandE;

public class GC {
	public static final int SIZE_OF_MAP=500;
	public static Random random;
	public static final Color gameColor=Color.WHITE;
	public static final int SCREEN_WIDTH=1200;
	public static final int SCREEN_HEIGHT=700;
	public static int sw=SCREEN_WIDTH,sh=SCREEN_HEIGHT;
	public static int roomCount;
	public static enum DIR{
		NORTH(0),SOUTH(1),EAST(2),WEST(3);
		public final int id;
		DIR(int p){
			id=p;
		}
		public static DIR getDir(int p){
			switch(p){
			case 0:return DIR.NORTH;
			case 1:return DIR.SOUTH;
			case 2:return DIR.EAST;
			default:return DIR.WEST;
			}
		}
		public static DIR getOppDir(DIR dir){
			switch(dir){
			case SOUTH:return DIR.NORTH;
			case NORTH:return DIR.SOUTH;
			case WEST:return DIR.EAST;
			default:return DIR.WEST;
			}
		}
	}

	public static int th(float a){
		return (int) (sw*a);
	}
	public static int tv(float a){
		return (int) (sh*a)-((a==1)?1:0);
	}
	public static float wteos(float a){
		return (float)((sw-1)-th(a))/sw;
	}
	public static float hteos(float a){
		return (float)((sh-1)-tv(a))/sh;
	}
	static{
		random=new Random(System.currentTimeMillis());
	}
}
