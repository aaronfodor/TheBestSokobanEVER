package Model;

/** 
 * Possible Directions in which the Players can move
 */
public enum Direction {

	/**
	 * The four possible Directions
	 */
	Down, Left, Up, Right;
	
	/**
	 * Returns the opposite of a Direction
	 * 
	 * @return 	Direction	Opposite Direction
	 */
	Direction GetOpposite() {
		switch(this) {
	    case Down:
	        return Up;
	    case Left:
	        return Right;
	    case Up:
	        return Down;
	    case Right:
	        return Left;
	    default:
	    	return this;
		}
	}
	
}