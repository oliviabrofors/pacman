/**
 * 
 */
package graphics;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import states.PlayState;

/**
 * @author sophiacederkvisthansen Klass som hanterar grafiken för poison, en
 *         enemy. konverterar bilden och ritar ut sig själv Kommentar efter
 *         komplettering: Wall håller koll på om ghost eller pacman intersectar
 *         med dem
 */
public class Poison extends StillObject {

	private Image poison;
	private Poison myPoison;

	public Poison(double x, double y, PlayState playState) {
		super(x, y, playState);
		try {
			poison = new Image(new FileInputStream("poison.png"));

		} catch (FileNotFoundException e) {
			System.out.println("Unable to find image-files!");
		}
	}

	@Override
	public void paintYourself(GraphicsContext gc, double w, double h) {
		gc.drawImage(poison, this.x, this.y, w, h);
	}

	@Override
	public void update() {
		if (intersectObject(myState.getMyPacman()) == true) {
			myState.setBgColor(Color.PALEGREEN);
			myState.getMyPacman().setPoints(50);
		}
	}

	@Override
	public boolean intersectObject(MovableObject object) {
		Rectangle2D nextRect = new Rectangle2D(object.getX(), object.getY(), 50, 50);
		Rectangle2D thisRect = new Rectangle2D(this.x, this.y, 50, 50);
		if (nextRect.intersects(thisRect)) {
			setRemove();
			return true;
		}
		return false;
	}

	@Override
	public void setRemove() {
		myPoison = this;
	}

	@Override
	public Poison getRemove() {
		return myPoison;
	}	
	
}
