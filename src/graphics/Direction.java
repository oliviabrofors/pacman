package graphics;

/**
 * @author sophiacederkvisthansen
 *Klass som håller koll på direction för de objekt som rör sig (pacman & ghost)
 *
 */

public class Direction {
	private double dirX;
	private double dirY;

	public Direction(double dirX, double dirY) {
		this.dirX = dirX;
		this.dirY = dirY;
	}

	public void setDirection(double newX, double newY) {
		dirX = newX;
		dirY = newY;
	}

	public double getDirX() {
		return dirX;
	}

	public double getDirY() {
		return dirY;
	}

}
