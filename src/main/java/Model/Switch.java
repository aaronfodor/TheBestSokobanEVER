package Model;

/** 
 * A Switch in the Warehouse
 * A Switch can be turned on and off by pushing a Box on or off the Switch
 * All Switches have a Hole that belongs to them
 * When a Switch is turned on, the belonging Hole opens
 * When a Switch is turned off, the belonging Hole closes and behaves as a regular Field
 */
public class Switch extends Field {
	
	/** 
	 * Hole that belongs to the Switch
	 */
	protected Hole h;
	

	/** 
	 * Switch constructor
	 * Sets the Switch so that it knows its container Warehouse
	 * Sets the Hole that belongs to the Switch
	 * 
	 * @param 	wh		Warehouse that contains the Switch
	 * @param	h		Hole that belongs to the Switch
	 */
	Switch(Warehouse wh, Hole h){
		super(wh);
		this.h = h;
		h.SetActive(false);
	}
	
	/** 
	 * Accepts a Box and sets the Hole's state to active
	 * 
	 * @param	b 		Box stepping onto the Switch
	 * @return 	void
	 */
	public void Accept(Box b){
		SetThing(b);
		h.SetActive(true);
	}
	
	/** 
	 * Removes a box and sets the Hole's state to inactive
	 * 
	 * @param	b		Box leaving the Switch
	 * @return 	void
	 */
	public void Remove(Box b){
		
		h.SetActive(false);
		
		if(GetThing() == b){
			
			SetThing(null);
			
		}
		
	}
}
