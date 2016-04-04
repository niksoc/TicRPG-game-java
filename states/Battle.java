package game.states;

import java.util.Random;

import game.main.TicRPG;
import game.objects.LivingThingAttributes;
import game.objects.Monster;
import game.objects.Player;
import game.util.GC;

public class Battle {
	private Player player;
	private Monster monster;
	private boolean playerDefend;

	public Battle(Player p, Monster m) {
		player = p;
		monster = m;
	}

	private int sumAttr(LivingThingAttributes l) {
		int sum = 0;
		for (int i : l.getStats())
			sum += i;
		return sum;

	}

	private boolean isRunSuccess() {
		Random r = GC.random;
		int p = 0, m = 0;
		p += sumAttr(player.getAttr());
		m += sumAttr(monster.getAttr()) + monster.getAttr().getHp() * 5;
		if (p > (m + 20) || r.nextDouble() > 0.8d)
			return true;
		else
			return false;
	}

	public void monsterAttack() {
		if(!monster.isAlive()) return;
		int damage;
		Random r = GC.random;
		damage = monster.getAttr().getStats()[2] * ((int) (r.nextDouble() * 10) % 7) / player.getAttr().getStats()[3];
		if (damage == 0)
			damage = monster.getAttr().getStats()[2];
		if (playerDefend)
			damage /= 2;
		if (r.nextDouble() < .2d) {
			damage = 0;
			TicRPG.game.getGui().addStatusMessage("The monster's attack missed!");
		} else
			TicRPG.game.getGui().addStatusMessage("The monster attacks and inflicts " + damage + " damage");
		player.getAttr().setHp(-damage);
		if (player.getAttr().getHp() <= 0) {
			TicRPG.game.gameOver();
		}
	}

	public void playerAttack() {
		int damage;
		Random r = GC.random;
		damage = player.getAttr().getStats()[2] * ((int) (r.nextDouble() * 10) % 5) / monster.getAttr().getStats()[3];
		if (damage == 0)
			damage = player.getAttr().getStats()[2];
		if (r.nextDouble() < .2d) {
			damage = 0;
			TicRPG.game.getGui().addStatusMessage("Your attack missed!");
		} else
			TicRPG.game.getGui().addStatusMessage("You inflicted " + damage + " damage");
		monster.getAttr().setHp(-damage);
		if (monster.getAttr().getHp() <= 0) {
			monster.setDead();
			TicRPG.game.getGui().addStatusMessage(+damage + " damage");
			TicRPG.game.setBattle(null);
		}
	}

	public boolean processInput(StringBuilder commandS) {
		boolean battleCommand = true;
		playerDefend = false;
		String[] c = commandS.toString().split(" ");
		switch (c[0]) {
		case "attack":
			playerAttack();
			monsterAttack();
			break;
		case "run":
			if (isRunSuccess()) {
				c[0] = "run";
				battleCommand = false;
				
			} else
				TicRPG.game.getGui().addStatusMessage("Run attempt unsuccessful");
			break;
		case "defend":
			playerDefend = true;
			monsterAttack();
			break;
		default:
			monsterAttack();
			battleCommand = false;
		}
		commandS.delete(0, commandS.length());
		commandS.append(String.join(" ", c));
		return battleCommand;
	}

}
