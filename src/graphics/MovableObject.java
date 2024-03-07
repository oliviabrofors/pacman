/**
 * 
 */
package graphics;

import states.PlayState;

/**
 * @author sophiacederkvisthansen
 *Kommentar efter komplettering: Klass som hanterar direction och kordinater för rörliga objekt
 */

public abstract class MovableObject extends GraphicObject{
	private Point position; // protected eftersom underklasserna ghost & pacman använder sig av dem!
	private Direction direction;

	public class Point{
		double x;
		double y;

		public Point(double x, double y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public MovableObject(double x, double y, double dirX, double dirY, PlayState playState) {
		super(x, y, playState);
		position = new Point(x,y);
		direction = new Direction(dirX, dirY);
	}

	public double getX() {
		return position.x;
	}

	public double getY() {
		return position.y;
	}
	
	public void setPosition(double x, double y) {
		position.x = x;
		position.y = y;
	}
	
	public Direction getDirection() {
		return direction;
	}

}
