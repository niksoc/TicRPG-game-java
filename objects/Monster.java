package game.objects;

import game.main.TicRPG;
import game.util.GC;

public abstract class Monster {
	protected LivingThingAttributes attr;
	protected String type;
	protected String description;
	
	private boolean alive;
	public Monster(){
		attr=new LivingThingAttributes();
		attr.stats=new int[4];
		alive=true;
	}
	public boolean isAlive(){
		return alive;
	}
	public void setDead(){
		alive=false;
		attr.hp=0;
		TicRPG.game.getGui().addStatusMessage("You have slayed the "+type+"!");
		description="The carcass of a slain "+type+" rots away on the floor";
	}
	public void resurrect(){
		alive=true;
		attr.setHp((int)(0.5*attr.stats[0]));
		TicRPG.game.getGui().addStatusMessage("A "+type+" has resurrected!");
		description="The resurrected "+type+" claws it's way to you, fuelled by the anger of twice death.";
	}
	public static Monster generateMonster(){
		double r=GC.random.nextDouble();
		int rc=Room.getRoomCount();
		int lvl=(int) Math.round(Math.ceil(r*rc/3));
		r=GC.random.nextDouble();
		if (r>0.7d)
		return new Vampire(lvl);
		else return new Ghoul(lvl);
		
	}
	public String getType() {
		return type;
	}
	public String getDescription() {
		return description;
	}
	public LivingThingAttributes getAttr(){
		return attr;
	}
}
