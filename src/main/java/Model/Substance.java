package Model;

/** 
 * An abstract Substance that can be placed on a Field in the Warehouse
 * A Substance changes the friction between the Field and the Player or Box on it
 */
public abstract class Substance {

	/**
	 * A Player is standing on some Substance
	 * Calls the appropriate Substance's method
	 * 
	 * @param 	str 		number of Things that can still be pushed
	 * @return 	int		modified number of Things that can still be pushed
	 */
	public abstract int SubstanceByPlayer(int str);
	
	/**
	 * A Box is standing on some Substance
	 * Calls the appropriate Substance's method
	 * 
	 * @param 	str 		number of Things that can still be pushed
	 * @return 	int		modified number of Things that can still be pushed
	 */
	public abstract int SubstanceByBox(int str);
	
}