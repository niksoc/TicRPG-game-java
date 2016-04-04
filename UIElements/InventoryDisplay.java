package game.UIElements;

import java.awt.Color;

import javax.swing.JTextArea;

import game.util.GC;

public class InventoryDisplay extends UIElement {
	
	public InventoryDisplay(){
		x=0.5f;
		y=0.6f;
		width=GC.wteos(x);
		height=GC.hteos(y);
		name="Inventory Display";
		fg=Color.lightGray;
		
	}
	
	@Override
	public void updateContents() {
		
	}

}