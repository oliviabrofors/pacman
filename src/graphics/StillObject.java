/**
 * 
 */
package graphics;

import states.PlayState;

/**
 * @author sophiacederkvisthansen
 * Klass som hanterar stilla objekt
 */
public abstract class StillObject extends GraphicObject{

	public StillObject(double x, double y, PlayState playState) {
		super(x, y, playState);
	}
	
	public abstract StillObject getRemove();
	
	public abstract void setRemove();
	
	public abstract boolean intersectObject(MovableObject object);
}
