package Model;

/** 
 * A Hole in the Warehouse
 * Some Holes are always active
 * Other Holes only act as Holes if there is a Box standing on a Switch controlling this Hole
 * If a Player or Box steps on a Hole while it is active, they are removed from the Game
 */
public class Hole extends Field {
	
	/** 
	 * Stores the state of the Hole
	 * If the Hole is active (active = true) the Hole is visible
	 * Otherwise the Hole acts as a simple field
	 */
	private Boolean active = false;
	
	/** 
	 * Hole constructor
	 * Sets the Hole so that it knows its container Warehouse
	 * Sets its initial state (active or inactive)
	 * 
	 * @param 	wh		the Warehouse that contains the Hole
	 * @param 	z 	the state of the Hole (true if active, false if inactive)
	 */
	Hole(Warehouse wh, boolean z){
		super(wh);
		active = z;
	}
	
	/** 
	 * Accepts a Box and removes it from the Warehouse (if the Hole is active)
	 * 
	 * @param 	t		Box stepping onto the Hole
	 * @return 	void
	 */
	public void Accept(Box t){
		if (active == true) {
			GetWarehouse().Delete(t);
		}
		else SetThing(t);
	}
	
	/** 
	 * Accepts a Player and removes it from the Warehouse (if the Hole is active)
	 * 
	 * @param 	t		Player stepping onto the Hole
	 * @return 	void
	 */
	public void Accept(Player t){
		if (active == true) {
			GetWarehouse().Delete(t);
		}
		else SetThing(t);
	}
	
	/** 
	 * Sets the Hole to active or inactive
	 * If there is a Thing on the Hole when the Hole becomes active, 
	 * it deletes the Thing from the Warehouse
	 * 
	 * @param 	b		the new state of the Hole
	 * @return 	void
	 */	
	public void SetActive(boolean b){
		
		if(b == true){
			GetWarehouse().HolePrintset(true, this);
			SetSubstance(null);
		}
		else GetWarehouse().HolePrintset(false, this);
		
		active = b;
	
		if(GetThing() != null && active == true) {
			GetWarehouse().Delete(GetThing());
			Remove(GetThing());
		}
		
	}

}