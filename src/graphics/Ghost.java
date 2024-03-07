package graphics;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import states.PlayState;
import static constants.Constants.objectSize;

/**
 * @author sophiacederkvisthansen Klass som hanterar grafiken för ghost (fiende
 *         i spelet). konverterar bilden och ritar ut sig själv håller koll på
 *         sin position & direction
 *         Kommentar efter komplettering: Håller koll på om pacman intersectar med den
 */
public class Ghost extends MovableObject {

	private Image ghost;
	private double dirX, dirY;

	public Ghost(double x, double y, double dirX, double dirY, PlayState playState) {
		super(x, y, dirX, dirY, playState);

		try {
			ghost = new Image(new FileInputStream("ghost.png"));

		} catch (FileNotFoundException e) {
			System.out.println("Unable to find image-files!");
		}
	}

	@Override
	public void paintYourself(GraphicsContext gc, double w, double h) {
		gc.drawImage(ghost, getX(), getY(), w, h);
	}

	@Override
	public void update() {
		dirX = getDirection().getDirX();
		dirY = getDirection().getDirY();
		setPosition(getX()+dirX, getY()+dirY);

		if (intersectObject(myState.getMyPacman()) == true) {
			if (myState.getMyPacman().canEatGhost() == true) {
				myState.getMyPacman().setGhost(this);
				myState.setBgColor(Color.LIGHTBLUE);
				System.out.println("You now ate a ghost and have used your star power.");
				myState.getMyPacman().setCanEatGhost(false);
			} else {
				myState.getMyPacman().removeLife();
				myState.getHearts().remove(0);
			}
		}
	}

	public boolean intersectObject(MovableObject object) {
		Rectangle2D nextRect = new Rectangle2D(object.getX(), object.getY(), objectSize, objectSize);
		Rectangle2D thisRect = new Rectangle2D(this.getX(), this.getY(), objectSize, objectSize);
		if (nextRect.intersects(thisRect)) {
			return true;

		}
		return false;
	}
	
	public void changeDirection() {
		this.getDirection().setDirection(dirX*-1, dirY*-1);
	}

}
