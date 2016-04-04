package game.UIElements;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JTextArea;

import game.util.GC;

public class CommandHistory extends UIElement {
	private int numCommand;
	private int curCommand;
	private String[] command;
	public CommandHistory(){
		x=0f;
		y=0.5f;
		width=0.5f;
		height=GC.hteos(y);
		name="Command History";
		clearCommandHistory();
		command = new String[9];
		fg=new Color(0,128,0);
		Font f=new Font("Andale Mono",Font.PLAIN,15);
		setFont(f);
		
	}
	public void addToCommandHistory(String s) {
		if (curCommand == command.length-1)
			curCommand = -1;
		if (numCommand < command.length)
			numCommand++;
		curCommand++;
		command[curCommand] = s;
	}

	public void clearCommandHistory() {
		numCommand = 0;
		curCommand = -1;
	}

	public void updateContents() {
		setText(null);
		String s=name;
		for(int i=numCommand-1;i>=0;i--)
			s+="\n\n"+command[i];
		setText(s);
	}

}
