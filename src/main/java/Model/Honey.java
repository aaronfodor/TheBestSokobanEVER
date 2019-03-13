package Model;

/** 
 * Honey that can be placed on a Field by a Player
 * Honey changes the friction between the Field and the Player or Box on it
 */
public class Honey extends Substance {
	
	/**
	 * A Player is standing on Honey
	 * Adds 1 to the number of Things that can still be pushed
	 * 
	 * @param 	str 		number of Things that can still be pushed
	 * @return 	int		modified number of Things that can still be pushed
	 */
	public int SubstanceByPlayer(int str) {
		return str+1;
	}
	
	/**
	 * A Box is standing on Honey
	 * Takes 1 away from the number of Things that can still be pushed
	 * 
	 * @param 	str 		number of Things that can still be pushed
	 * @return 	int		modified number of Things that can still be pushed
	 */
	public int SubstanceByBox(int str) {
		return str-1;
	}
}