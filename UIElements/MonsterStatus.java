package game.UIElements;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextArea;

import game.objects.LivingThingAttributes;
import game.util.GC;
import game.util.SF;

public class MonsterStatus extends UIElement {
	private LivingThingAttributes attr;
	private String type;
	private boolean isMonster;

	public MonsterStatus() {
		x = 0f;
		y = 0.05f;
		width = 0.2f;
		height = 0.4f;
		name = "Monster Status";
		fg = Color.RED;
		Font f = new Font("Times New Roman", Font.PLAIN, 20);
		this.setFont(f);
		isMonster = false;

	}

	public void setMonsterDetails(LivingThingAttributes a, String s) {
		attr = a;
		type = s;
		isMonster = true;
	}

	public void setMonsterFalse() {
		isMonster = false;
	}

	public void updateContents() {
		if (isMonster) {
			String contents = new String();
			contents = "\n";
			contents += "              " + type + "\n";
			if(attr.getHp()==0){
				contents+="             carcass";
			}
			else{
			contents += "\n        lvl " + attr.getStats()[1] + "\n\n        HP "
					+ SF.outOf(attr.getHp(), attr.getStats()[0]) + "\n\n        Attack " + attr.getStats()[2]
					+ "\n\n        Defense " + attr.getStats()[3];
			}
			setText(contents);
		} else
			setText(null);
	}

}