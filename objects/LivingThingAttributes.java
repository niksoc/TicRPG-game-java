package game.objects;

public class LivingThingAttributes {
	protected int[] stats;
	protected int hp;
	protected int mp;
	protected int lvl;
	public int[] getStats() {
		return stats;
	}
	public int getHp() {
		return hp;
	}
	public int getMp() {
		return mp;
	}
	public int getLvl() {
		return lvl;
	}
	public void setStats(int[] delta) {
		for(int i=0;i<stats.length;i++){
			stats[i]+=delta[i];
		}
	}
	public void setHp(int delta) {
		hp += delta;
		if(hp>stats[0]) hp=stats[0];
	}
	public void setMp(int delta) {
		mp +=delta;
	}
	public void setLvl(int delta) {
		lvl += lvl;
	}

}
