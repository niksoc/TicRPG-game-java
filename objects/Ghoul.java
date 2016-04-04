package game.objects;

public class Ghoul extends Monster {
	public Ghoul(int lvl) {
		super();
		attr.stats[1] = lvl;
		attr.stats[0] = lvl * 10;
		attr.stats[2] = lvl * 3;
		attr.stats[3] = lvl * 3;
		attr.hp = attr.stats[0];
		type = "Ghoul";
		description = "First you notice the thuds which sound like somebody's banging his head on the wall."+
		" Then you notice somebody actually is and is also eating the pieces of his flesh that break off on the impact."+
		" Just as you realize from your previous observation that it's a someTHING, it takes notice of you and eagerly"+
		" follows the smell of living flesh. It's a Ghoul! ";
	}

}
