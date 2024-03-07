package states;

//Superklass för levelone och leveltwo

public abstract class Level {
	private int[] map;

	public Level(int[] map) {
		this.map = map;
	}

	public int[] getMap() {
		return map;
	}

}