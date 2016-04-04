package game.UIElements;

import java.awt.Color;

import javax.swing.JTextArea;

import game.util.GC;

public abstract class UIElement extends JTextArea {
	protected float x, y;
	protected float width, height;
	protected String name;
	protected Color fg;
	public abstract void updateContents();
	public void updateForeground(){
		setForeground(fg);
	}
	
	public void updateBounds() {
		setBounds(GC.th(x), GC.tv(y), GC.th(width), GC.tv(height));

	}
}
