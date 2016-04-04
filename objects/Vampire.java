package game.objects;

public class Vampire extends Monster {

	public Vampire(int lvl) {
		super();
		attr.stats[1] = lvl;
		attr.stats[0] = lvl * 20;
		attr.stats[2] = lvl * 4;
		attr.stats[3] = lvl * 4;
		attr.hp = attr.stats[0];
		type = "Vampire";
		description = "Two people seem to be lying in the shadows. One of them seems to take notice of you, the shadow straightens "+
		"and approaches eerily. As it drifts into view, you notice with horror the blood dripping from the mouth of the pale"+
		" humanoid horror. It's a Vampire!";
	}

}
