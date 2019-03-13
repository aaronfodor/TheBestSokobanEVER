package Model;

import java.util.EnumMap;

/** 
 * A Field in the Warehouse
 * The playing field is made up of these Fields
 * The Boxes and Players move on them
 */
public class Field {
	
	/**
	 * Thing on the Field
	 */
	private Thing t;
	
	/**
	 * Warehouse that contains this Field
	 */
	private Warehouse wh;
	
	/**
	 * Neighbours of the Field in every Direction
	 */
	private EnumMap<Direction, Field> neighbours;
	
	/**
	 * Substance on this Field
	 */
	private Substance s;
	
	/** 
	 * Field constructor
	 * Sets the Field to know its container Warehouse,
	 * Creates the Map containing the Field's neighbours
	 * 
	 * @param	wh 		Warehouse that contains the Field
	 */
	public Field(Warehouse wh){
		
		this.wh = wh;
		this.neighbours = new EnumMap<Direction, Field>(Direction.class);
		
	}
	
	/** 
	 * Thing setter method
	 * Sets the Thing standing on the Field.
	 * 
	 * @param 	t		Thing on the Field
	 * @return 	void
	 */
	public void SetThing(Thing t) {
		
		this.t = t;
		
	}
	
	/** 
	 * Thing getter method
	 * Returns the Thing standing on the Field.
	 * 
	 * @return 	Thing	Thing on the Field
	 */
	public Thing GetThing()
	{
		return t;
	}
	
	/** 
	 * Neighbour setter method
	 * Sets the received Field as a Neighbour in the received Direction
	 * 
	 * @param 	f		neighbouring Field
	 * @param 	d 		Direction of the received neighbour
	 * @return 	void
	 */
	public void SetNeighbour(Field f, Direction d) {
		
	    neighbours.put(d, f);
	    
	}
	
	/** 
	 * Thing wants to move away from the Field in the given Direction
	 * This Field tells the neighbouring Field that the Thing moves towards it
	 * 
	 * @param	d 		Direction in which the Thing wants to move
	 * @param 	str 	number of Things that can still be pushed
	 * @return 	void
	 */
	public void TryMove(Direction d, int str){
		
		int str_new = str;
		
		if(this.s != null) {
			str_new = this.t.OnSubstance(s, str);
		}
		
		neighbours.get(d).TryMove(d, t, str_new);
		
	}
	
	/** 
	 * Thing wants to move to this Field from the given Direction
	 * If the Field is empty, the Thing can step onto the Field
	 * If the Field is not Empty, this method manages the collision
	 * Asks Warehouse to check whether there still are movable boxes
	 * 
	 * @param 	d 		Direction in which the Thing wants to move
	 * @param 	rec 	Thing stepping onto the Field
	 * @param 	str 	number of Things that can still be pushed
	 * @return 	void
	 */
	public void TryMove(Direction d, Thing rec, int str){
		if(str > 0)
			{
			if(this.t != null) {
				rec.CollideWith(d, this.t, str);
			}
			
			if(this.t == null){
				rec.AcceptMove(this);
			}
		}
	}
	
	/** 
	 * Player steps onto the Field, and the Field accepts it
	 * 
	 * @param 	t 		Player stepping onto the Field
	 * @return 	void
	 */
	public void Accept(Player t){

		this.t = t;
		
	}
	
	/** 
	 * Box steps onto the Field, and the Field accepts it
	 * 
	 * @param 	t 		Box stepping onto the Field
	 * @return 	void
	 */
	public void Accept(Box t){
		
		this.t = t;
		
	}	
	
	/** 
	 * Player leaves the Field
	 * 
	 * @param 	t 		Player leaving the Field
	 * @return 	void
	 */
	public void Remove(Player t){
		
		if(this.t == t){
			this.t = null;
		}
		
	}
	
	/** 
	 * Box leaves the Field
	 * 
	 * @param 	t 		Box leaving the Field
	 * @return 	void
	 */
	public void Remove(Box t){
		
		if(this.t == t){
			this.t = null;
		}
		
	}
	
	/** 
	 * Thing leaves the Field
	 * 
	 * @param 	t 		Thing leaving the Field
	 * @return 	void
	 */
	public void Remove(Thing t){
		if(this.t == t){
			this.t = null;
		}
	}

	
	/** 
	 * Player tells the Field that it was pushed onto the Field in a given Direction
	 * 
	 * @param 	d 		Direction in which the Player was pushed
	 * @param 	str 	number of Things that can still be pushed
	 * @return 	void
	 */
	public void Pushed(Direction d, int str){

		int str_new = str;
		
		if(this.s != null) {
			str_new = this.t.OnSubstance(s, str);
		}
		
		neighbours.get(d).Pushed(d, (Player)this.t, str_new);
		
	}
	
	/** 
	 * A neighbouring Field tells this Field that a Player was pushed towards it
	 * If the Field is empty, no problem
	 * If the Field is not empty, the Player tries to push the Thing
	 * If the Player can't do this, this method tells the Warehouse to kill that Player
	 * 
	 * @param 	d 		Direction in which the Player was pushed
	 * @param 	p 		Player stepping onto the Field
	 * @param 	str 	number of Things that can still be pushed
	 * @return 	void
	 */
	public void Pushed(Direction d, Player p, int str){
		if(str > 0)
		{
			if(this.t != null) {
				str = p.Pushing(d, this.t, str);
			}
			
			if(this.t == null) {
				p.AcceptMove(this);
			}
			
			else if(str > 0){
				neighbours.get(d.GetOpposite()).Remove(p);
				wh.Delete(p);
			}
		}		
	}
	
	/**
	 * Substance setter method
	 * 
	 * @param 	s 		Substance put onto the Field
	 * @return 	void
	 */
	public void SetSubstance(Substance s) {
		this.s = s;
	}
	
	/**
	 * Substance getter method
	 * 
	 * @return 	Substance	Substance on the Field
	 */
	public Substance GetSubstance(){
		return s;
	}
	
	/**
	 * Returns the Field's neighbour in a given Direction
	 * 
	 * @param 	d 		given Direction
	 * @return 	Field	Field in the given Direction
	 */
	public Field GetNeighbour(Direction d) {
	    return neighbours.get(d);
	}
	
	/**
	 * Returns the Warehouse that contains the Field
	 * 
	 * @return 	Warehouse	Warehouse that the Field is in
	 */
	public Warehouse GetWarehouse() {
		return wh;
	}
	
	/**
	 * Checks whether a box could move in a given direction to this Field
	 * 
	 * @param 	d 		the Direction in which the box moves
	 * @return 	boolean	whether the box can move here or not
	 */
	public boolean Check(Direction d) {
		
		if(t==null)
			return true;
		else
			return !t.isBlocking();
		
	}
	
}