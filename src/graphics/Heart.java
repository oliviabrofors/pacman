/**
 * 
 */
package graphics;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import states.PlayState;

/**
 * @author sophiacederkvisthansen Håller koll på grafiken för hjärtanen som
 *         representerar hur många liv spelaren har kvar
 *         Kommentar efter komplettering: Extendar nu stillobject
 */

public class Heart extends StillObject {

	private Image heart;

	public Heart(double x, double y, PlayState playState) {
		super(x, y, playState);

		try {
			heart = new Image(new FileInputStream("heart.png"));

		} catch (FileNotFoundException e) {
			System.out.println("Unable to find image-files!");
		}
	}

	@Override
	public void paintYourself(GraphicsContext gc, double w, double h) {
		gc.drawImage(heart, this.x, this.y, w, h);
	}

	@Override
	public void update() {
	}

	@Override
	public StillObject getRemove() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setRemove() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean intersectObject(MovableObject object) {
		// TODO Auto-generated method stub
		return false;
	}

}
