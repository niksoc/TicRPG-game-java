package game.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import game.UIElements.UI;
import game.UIElements.UIElement;
import game.exceptions.InvalidCommandE;
import game.objects.Inventory;
import game.objects.Monster;
import game.objects.Player;
import game.states.Battle;
import game.util.GC;
import game.util.SF;

public class TicRPG extends JFrame {
	private static final long serialVersionUID = 2444282906446005439L;
	private static UI gui;
	private JTextField cl;
	private Player player;
	private Inventory inventory;
	private Map map;
	private static boolean battleState;
	private Battle battle;
	public static final TicRPG game;

	static {
		game = new TicRPG();
	}

	public TicRPG() {
		player = new Player();
		inventory = new Inventory();
		map = new Map();
		battleState = false;
	}

	public UI getGui() {
		return gui;
	}

	public void setBattle(Monster m) {
		if(m!=null){
		battle = new Battle(player, m);
		battleState=true;
		}
		else battleState=false;
	}

	public void gameOver() {
		gui.addStatusMessage("Your valiant journey ends here, your body will forever rest in room "
				+ map.getCurrentRoom().getRoomNumber() + ". Or, of course, it might join the ranks of the undead.");
		gui.update();
		repaint();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {}
		System.exit(0);
	}

	private class GamePanel extends JPanel {
		public GamePanel() {
			setPreferredSize(new Dimension(GC.SCREEN_WIDTH, GC.SCREEN_HEIGHT));
			setVisible(true);
			setBackground(Color.BLACK);
			setLayout(null);
			cl = new JTextField(">>");
			cl.setBackground(Color.BLACK);
			cl.setForeground(Color.GREEN);
			add(cl);
			update();
			gui.update();
			cl.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						processInput(cl.getText());
						cl.setText("");
					}
				}
			});
			Iterator<UIElement> i = gui.getUIElements();
			while (i.hasNext()) {
				add(i.next());
			}
		}

		public void processInput(String com) {
			boolean flag = false;
			boolean genCommand = true;
			StringBuilder commandS = new StringBuilder();
			commandS.append(com);
			try {
				if (battleState) {
					genCommand = !battle.processInput(commandS);
				}
				String command = commandS.toString();
				if (genCommand) {
					String[] c = command.split(" ");
					switch (c[0]) {
					case "move":
						if(!battleState)
						map.movement(SF.sToDir(c[1]));
						else{
							gui.addStatusMessage("Your movement is blocked by the monster");
							throw new InvalidCommandE();
						}
						break;
					case "run":
						map.movement(SF.sToDir(c[1]));
						game.getGui().addStatusMessage("You have successfully run away");
						game.setBattle(null);
						break;
					case "cast":
								if(c[1].contentEquals("heal"))
								if(player.getAttr().getMp()>5){
								int old=player.getAttr().getHp();
								player.getAttr().setMp(-5);
								player.getAttr().setHp(10);
								int delta=player.getAttr().getHp()-old;
								game.getGui().addStatusMessage("You have healed "+delta+" points");
								}
								break;
					default:
						flag = true;
					}
				}
				if (flag) {
					throw new InvalidCommandE();
				} else
					gui.addToCommandHistory(command);
				update();
				gui.update();
			} catch (Exception e) {
				gui.addStatusMessage(e.toString());
				gui.update();
				e.printStackTrace();
			}
		}

		public void update() {
			gui.setRoom(map.getCurrentRoom());
			gui.setPlayerStatus(player.getAttr());
		}

		public void paint(Graphics g) {
			super.paint(g);
			onPaint(g);
		}

		public void onPaint(Graphics g) {
			g.setColor(Color.WHITE);
			GC.sw = getWidth();
			GC.sh = getHeight();
			cl.setBounds(0, GC.tv(0.45f), GC.th(0.5f), GC.tv(0.05f));
			gui.update();
		}
	}

	protected void createAndShowGUI() {
		gui = new UI();
		GamePanel panel = new GamePanel();
		setTitle("TicRPG");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().add(panel);
		pack();
		setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				game.createAndShowGUI();
			}
		});
	}

}
