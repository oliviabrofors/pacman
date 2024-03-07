/**
 * 
 */
/**
 * 
 */
package graphics;


import states.PlayState;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * @author sophiacederkvisthansen Klass som hanterar grafiken för banan (poängen
 *         i spelet). konverterar bilden och ritar ut sig själv
 *         Kommentar efter komplettering: Banana håller nu koll på om pacman intersectar med den
 */
public class Banana extends GraphicObject {

	private Image banana;
	private Banana myBanana = null;

	public Banana(double x, double y, PlayState playState) {
		super(x, y, playState);
		try {
			banana = new Image(new FileInputStream("h-banana.png"));

		} catch (FileNotFoundException e) {
			System.out.println("Unable to find image-files!");
		}
	}

	public void paintYourself(GraphicsContext gc, double w, double h) {
		gc.drawImage(banana, this.x, this.y, w, h);
	}

	public Image getImage() {
		return banana;
	}

	@Override
	public void update() {
		if (intersectObject(myState.getMyPacman()) == true) {
			myState.getMyPacman().addBananaPoints();
		}
	}

	public boolean intersectObject(Pacman pacman) {
		Pacman myPacman = pacman;
		Rectangle2D nextRect = new Rectangle2D(myPacman.getX(), myPacman.getY(), 50, 50);
		Rectangle2D thisRect = new Rectangle2D(this.x, this.y, 50, 50);
		if (nextRect.intersects(thisRect)) {
			setRemove();
			return true;
		}
		return false;
	}

	public void setRemove() {
		myBanana = this;
	}

	public Banana getRemove() {
		return myBanana;
	}

}