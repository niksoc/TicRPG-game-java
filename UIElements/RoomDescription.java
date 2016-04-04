package game.UIElements;

import java.awt.Color;

public class RoomDescription extends UIElement {
	private String contents;
	public RoomDescription()
	{
		x=0.2f;
		y=.05f;
		width=0.6f;
		height=0.4f;
		name="Room Description";
		fg=Color.WHITE;
		setLineWrap(true);
	}
	public void setContents(String s){
		contents=s;
	}
	@Override
	public void updateContents() {
		setText(contents);
	}
	
}
