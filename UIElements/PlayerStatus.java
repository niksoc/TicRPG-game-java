package game.UIElements;

import java.awt.Color;
import java.awt.Font;

import game.objects.LivingThingAttributes;
import game.util.GC;
import game.util.SF;

public class PlayerStatus extends UIElement {
	private LivingThingAttributes attr;

	public PlayerStatus() {
		x = 0.8f;
		y = 0.05f;
		width = GC.wteos(x);
		height = 0.4f;
		name = "Player Status";
		fg = Color.GREEN;
		Font f=new Font("Times New Roman",Font.PLAIN,20);
		this.setFont(f);
	}

	public void setPlayerAttr(LivingThingAttributes a) {
		attr = a;
	}

	public void updateContents() {
		String contents = new String();
		contents = "\n";
		contents += "              YOU\n";
		contents += "\n        HP " + SF.outOf(attr.getHp(), attr.getStats()[0]) + "\n\n        MP "
				+ SF.outOf(attr.getMp(), attr.getStats()[1]) + "\n\n        Attack " + attr.getStats()[2] + "\n\n        Defense "
				+ attr.getStats()[3];
		setText(contents);
	}

}
