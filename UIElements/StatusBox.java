package game.UIElements;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JTextArea;

import game.util.GC;

public class StatusBox extends UIElement {
	private int numMessage;
	private int curMessage;
	private String[] statusMessage;
	

	public StatusBox() {
		x = 0.5f;
		y = 0.45f;
		width = GC.wteos(x);
		height = .15f;
		numMessage = 0;
		curMessage = -1;
		statusMessage = new String[4];
		name="";
		fg=Color.CYAN;
		this.setLineWrap(true);
	}

	public void addStatusMessage(String s) {
		if (curMessage == 3)
			curMessage = -1;
		if (numMessage < 4)
			numMessage++;
		curMessage++;
		statusMessage[curMessage] = s;
	}

	public void clearStatus() {
		numMessage = 0;
		curMessage = -1;
	}

	@Override
	public void updateContents() {
		setText(null);
		String s=name;
		for(int i=0;i<numMessage;i++)
			s+="\n"+statusMessage[i];
		setText(s);
	}

}
