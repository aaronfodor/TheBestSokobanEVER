package Model;

/** 
 * A Wall in the Warehouse
 * Players and Boxes cannot pass through Walls
 */
public class Wall extends Field {
	
	/** 
	 * Wall constructor
	 * Sets the Wall so that it knows its container Warehouse
	 * 
	 * @param 	wh		Warehouse that contains the Wall
	 */
	Wall(Warehouse wh){
		super(wh);
	}
	
	/** 
	 * Thing wants to step into this Wall from the given Direction
	 * NoThing happens, because it is impossible to step into the Wall,
	 * (when the Thing tries to move we do not know that this is a Wall,
	 * that is why we have to call this method)	
	 * Therefore, the Thing that wanted to step into the Wall stays on the original Field 
	 * 
	 * @param	d 		Direction in which the Thing wants to step
	 * @param 	t 		Thing that wants to step into the Wall
	 * @param 	str 		number of Things that can still be pushed
	 * @return 	void
	 */
	public void TryMove(Direction d, Thing t, int str){
		return;
	}
	
	/** 
	 * A Player is pushed into the Wall
	 * The pushed Player dies
	 * The dead Player is removed from the Warehouse
	 * 
	 * @param	d 		Direction in which the Player was pushed
	 * @param 	p 		Player that was pushed
	 * @param 	str 	number of Things that can still be pushed
	 * @return 	void
	 */
	public void Pushed(Direction d, Player p, int str){
		
		GetNeighbour(d.GetOpposite()).Remove(p);
		GetWarehouse().Delete(p);
	}
	
	/**
	 * Checks whether a box could move in a given direction to this Wall
	 * 
	 * @param 	d 		the Direction in which the box moves
	 * @return 	boolean	whether the box can move here or not, always false
	 */
	public boolean Check(Direction d) {
		return false;
	}
	
}