package Model;

/** 
 * A Base in the Warehouse
 * The point of the Game is for the Players to push Boxes onto the Bases they are assigned
 * Players receive a point if there is a Box standing on their Base Field
 */
public class Base extends Field {
	
	/** 
	 * Player that the Base belongs to
	 */
	private Player p;
	
	/** 
	 * Base constructor
	 * Sets the Base so that it knows its container Warehouse,
	 * Sets the Player that the Base belongs to
	 * 
	 * @param	wh		Warehouse that contains the Base
	 * @param 	p		Player that the Base belongs to
	 */
	Base(Warehouse wh, Player p){
		super(wh);
		this.p = p;
	}
	
	/** 
	 * Accepts a Box and increases the points of the Player that the Base belongs to
	 * 
	 * @param	b		Box stepping onto the Base
	 * @return 	void
	 */
	public void Accept(Box b){
		SetThing(b);
		p.AddPoints();	
	}
	
	/** 
	 * Removes a Box from the Base
	 * Decreases the points of the Player that the Base belongs to
	 * 
	 * @param	b		Box leaving the Base
	 * @return 	void
	 */
	public void Remove(Box b){
		
		p.MinusPoints();
		
		if(GetThing().equals(b)){
			
			SetThing(null);
			
		}
		
	}

}