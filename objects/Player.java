package game.objects;

public class Player{
	private LivingThingAttributes attr;
	private int[] bonus;
	public Player(){
		attr=new LivingThingAttributes();
		attr.stats=new int[4];
		attr.stats[0]=30;
		attr.stats[1]=30;
		attr.stats[2]=5;
		attr.stats[3]=5;
		attr.hp=attr.stats[0];
		attr.mp=attr.stats[1];
		bonus =new int[3];
	}
	public void equipItem(Item i){
		
	}
	public void addStatsBonus(int[] b){
		for(int i=0;i<4;i++)
			bonus[i]+=b[i];
	}
//	public LivingThingAttributes updateAttr(){
//		LivingThingAttributes l=new LivingThingAttributes();
//		for(int i=0;i<4;i++)
//			l.setStats(bonus);
//		return l;
//		
//	}
	public LivingThingAttributes getAttr(){
		return attr;
	}
	
}
