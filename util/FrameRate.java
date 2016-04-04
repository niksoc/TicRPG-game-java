package game.util;

public class FrameRate {
	private long lastTime;
	private long delta;
	private int frames;
	private int frameRate;

	public void initialize() {
		frames = 0;
		delta = 0;
		frameRate=0;
		lastTime = System.currentTimeMillis();
	}

	public void calculate() {
		long currentTime = System.currentTimeMillis();
		delta += currentTime - lastTime;
		lastTime = currentTime;
		frames++;
		if (delta >= 1000) {
			delta -= 1000;
			frameRate=frames;
			frames = 0;
		}
	}

	public int getFrameRate() {
		return frameRate;
	}

	public String toString() {
		return "FPS "+frameRate;
	}

	public static void main(String[] args) {
		FrameRate fps=new FrameRate();
		fps.initialize();
		while(true){
			fps.calculate();
			System.out.println(fps);
		}
	}

}
