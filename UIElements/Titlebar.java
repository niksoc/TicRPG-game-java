package game.UIElements;

import java.awt.Color;
import java.awt.Font;

import game.util.GC;

public class Titlebar extends UIElement {
	private int roomNumber;
	public Titlebar() {
		x=0f;
		y=0f;
		width=GC.wteos(x);
		height=.05f;
		name="Titlebar";
		fg=new Color(101,66,138);//purple
		Font f=new Font("dejavu serif",Font.PLAIN , 30);
		setFont(f);
	}
	
	public void setRoomNumber(int no) {
		roomNumber=no;
	}
	public void updateContents() {
		setText("----------------------------------------------------------Room "+roomNumber+" ------------------------------------------------------------------");
		
	}

}
