package game.UIElements;

import java.awt.Color;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BorderFactory;

import game.objects.LivingThingAttributes;
import game.objects.Monster;
import game.objects.Room;
import game.util.GC;

public class UI {
	private List<UIElement> UIElements;
	protected CommandHistory ch;
	private int sw, sh;

	public UI() {
		UIElements = new LinkedList<UIElement>();
		UIElements.add(new Titlebar());
		UIElements.add(new MonsterStatus());
		UIElements.add(new RoomDescription());
		UIElements.add(new PlayerStatus());
		UIElements.add(new StatusBox());
		UIElements.add(new CommandHistory());
		UIElements.add(new InventoryDisplay());
		Iterator<UIElement> i = getUIElements();
		while (i.hasNext()) {
			UIElement a = i.next();
			a.setBackground(Color.BLACK);
			a.updateForeground();
			a.setBorder(BorderFactory.createLineBorder(Color.WHITE));
			a.updateBounds();
			a.setEditable(false);
		}
		sw = GC.sw;
		sh = GC.sh;
	}

	public Iterator<UIElement> getUIElements() {
		return UIElements.iterator();
	}

	public UIElement getUIElements(int n) {
		return UIElements.get(n);
	}

	public void setRoom(Room room) {
		((RoomDescription) getUIElements(2)).setContents(room.getDescription());
		((Titlebar) getUIElements(0)).setRoomNumber(room.getRoomNumber());
		if (room.isMonster()) {
			Monster m = room.getMonster();
			((MonsterStatus) getUIElements(1)).setMonsterDetails(m.getAttr(), m.getType());
		}
		else ((MonsterStatus) getUIElements(1)).setMonsterFalse();

	}

	public void setPlayerStatus(LivingThingAttributes attr) {
		((PlayerStatus) getUIElements(3)).setPlayerAttr(attr);
	}

	public void addToCommandHistory(String s) {
		((CommandHistory) getUIElements(5)).addToCommandHistory(s);
	}

	public void addStatusMessage(String s) {
		((StatusBox) getUIElements(4)).addStatusMessage(s);
	}

	public void update() {
		Iterator<UIElement> i = getUIElements();
		boolean flag = false;
		while (i.hasNext()) {
			UIElement a = i.next();
			if (sw != GC.sw || sh != GC.sh) {
				a.updateBounds();
				flag = true;
			}
			a.updateContents();
		}
		if (flag) {
			sw = GC.sw;
			sh = GC.sh;
		}
	}

}
