package Model;

/** 
 * An abstract Thing that can move in the Warehouse
 */
public abstract class Thing {

	/**
	 * Field where the Thing is standing
	 */
	private Field f;
	
	/** 
	 * Thing constructor
	 * Sets the Field on which the Thing is standing
	 * 
	 * @param	f 		Field where the Thing is standing
	 */
	public Thing(Field f){
		
		this.f = f;
		
	}
	
	/** 
	 * Field getter method
	 * Returns the Field that the Thing is standing on
	 * 
	 * @return	f 		Field where the Thing is standing
	 */
	public Field GetField()
	{
		return f;
	}
	
	/** 
	 * Field setter method
	 * Sets the Field that the Thing is standing on
	 * 
	 * @param	nyu 		Field where the Thing is standing
	 */
	public void SetField(Field nyu)
	{
		f = nyu;
	}
	
	/** 
	 * Thing's move was accepted, it successfully stepped onto a new Field
	 * 
	 * @param 	new_home Field where the Thing stepped
	 * @return 	void
	 */
	public abstract void AcceptMove(Field new_home);
	
	/** 
	 * Thing collides with this Thing
	 * 
	 * @param	d		Direction in which the Thing wants to move
	 * @param 	t 		Thing that collides with this Thing
	 * @param 	str 	number of Things that can still be pushed
	 * @return 	int		modified number of Things that can still be pushed
	 */
	public abstract int CollideWith(Direction d, Thing t, int str);
	
	/** 
	 * Player pushes Thing in a given Direction
	 * 
	 * @param 	d 		Direction in which the Player wants to push the Thing
	 * @param 	p 		Player that wants to push the Thing
	 * @param 	str 		number of Things that can still be pushed
	 * @return 	int		modified number of Things that can still be pushed
	 */
	public abstract int PushedBy(Direction d, Player p, int str);
	
	/**
	 * The Thing is hit by a Player in a given Direction
	 *
	 * @param 	d 		Direction in which the Player wants to push the Thing
	 * @param 	p 		Player that hit the Thing
	 * @param 	str 		number of Things that can still be pushed
	 * @return 	int		modified number of Things that can still be pushed
	 */

	public abstract int HitBy(Direction d, Player p, int str);
	
	/**
	 * The Thing is hit by a Box in a given Direction
	 *
	 * @param 	d 		Direction in which the Box wants to push the Thing
	 * @param 	b 		Box that hit the Thing
	 * @param 	str 		number of Things that can still be pushed
	 * @return 	int		modified number of Things that can still be pushed
	 */
	public abstract int HitBy(Direction d, Box b, int str);
	
	/**
	 * The Thing is standing on a Field that has some Substance on it
	 * Calls the correct method that updates the number of Things that can still be pushed
	 * 
	 * @param 	s 		Substance that the Thing is standing on
	 * @param 	str 		number of Things that can still be pushed
	 * @return 	int		modified number of Things that can still be pushed
	 */
	public abstract int OnSubstance(Substance s, int str);
	
	/**
	 * Checks whether the Thing is still movable or not in the given Direction
	 * 
	 * @param 	dir 		Direction in which it is checking
	 * @return 	boolean	whether the Thing is movable or not
	 */
	public abstract boolean CheckIfMovable(Direction dir);
	
	public abstract boolean isBlocking();
	
}