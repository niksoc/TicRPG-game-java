package game.util;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInput implements KeyListener {
	boolean[] key;
	int[] poll;

	public KeyboardInput() {
		key = new boolean[256];
		poll = new int[256];
	}

	public void poll() {
		for (int i = 0; i < key.length; i++) {
			if (key[i] == true)
				poll[i]++;
			else
				poll[i] = 0;

		}
	}

	public boolean isKeyDown(int keyCode) {
		return key[keyCode];
	}

	public boolean isKeyDownOnce(int keyCode) {
		return poll[keyCode] == 1;
	}

	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode >= 0 && keyCode <= key.length)
			key[keyCode] = true;
	}

	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode >= 0 && keyCode <= key.length)
			key[keyCode] = false;
	}

	public void keyTyped(KeyEvent e) {
		// not used
	}

}
