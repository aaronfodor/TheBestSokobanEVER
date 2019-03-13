package Model;

/** 
 * A Box in the Warehouse that can be pushed by Players
 */
public class Box extends Thing{

	/** 
	 * Box constructor
	 * Sets the Field on which the Box is standing
	 * 
	 * @param	f 		Field where the Box is standing
	 */
	public Box(Field f){
		super(f);
	}
	
	/**
	 * The Box is hit by a Player in a given Direction
	 *
	 * @param 	d 		Direction in which the Player wants to push the Box
	 * @param 	p 		Player that hit the Box
	 * @param 	str 		number of Things that can still be pushed
	 * @return 	int		modified number of Things that can still be pushed
	 */
	public int HitBy(Direction d, Player p, int str) {
		str -= 1;
		GetField().TryMove(d, str);
		return str;
	}
	
	/**
	 * The Box is hit by a Box in a given Direction
	 *
	 * @param 	d 		Direction in which the Box wants to push the Box
	 * @param 	b 		Box that hit the Box
	 * @param 	str 		number of Things that can still be pushed
	 * @return 	int		modified number of Things that can still be pushed
	 */
	public int HitBy(Direction d, Box b, int str) {
		str -=1;
		GetField().TryMove(d, str);
		return str;
	}
	
	/** 
	 * Player pushes Box in a given Direction
	 * 
	 * @param 	d 		Direction in which the Player wants to push the Box
	 * @param 	p 		Player that wants to push the Box
	 * @param 	str 		number of Things that can still be pushed
	 * @return 	int		modified number of Things that can still be pushed
	 */
	public int PushedBy(Direction d, Player p, int str) {
		str -= 1;
		GetField().TryMove(d, str);
		return str;
	}
	
	/** 
	 * Thing collides with this Box
	 * 
	 * @param	d		Direction in which the Thing wants to move
	 * @param 	t 		Thing that collides with this Box
	 * @param 	str 		number of Things that can still be pushed
	 * @return 	int		modified number of Things that can still be pushed
	 */
	public int CollideWith(Direction d, Thing t, int str){
		str = t.HitBy(d, this, str);
		return str;
	}

	/** 
	 * Box's move was accepted, it successfully stepped onto a new Field
	 * 
	 * @param 	new_home Field where the Box stepped
	 * @return 	void
	 */
	public void AcceptMove(Field new_home){
		GetField().Remove(this);
		new_home.Accept(this);
		SetField(new_home);
	}
	
	/**
	 * The Box is standing on a Field that has some Substance on it
	 * Calls the correct method that updates the number of Things that can still be pushed
	 * 
	 * @param 	s 		Substance that the Box is standing on
	 * @param 	str 		number of Things that can still be pushed
	 * @return 	int		modified number of Things that can still be pushed
	 */
	public int OnSubstance(Substance s, int str) {
		int new_str = s.SubstanceByBox(str);
		return new_str;
	}
	
	/**
	 * Checks whether the Box is still movable or not in the given Direction
	 * 
	 * @param 	dir 		Direction in which it is checking
	 * @return 	boolean	whether the Box is movable or not
	 */
	public boolean CheckIfMovable(Direction dir) {
		boolean r = true,l = true,u = true,d= true;
		if(dir != Direction.Left) r = GetField().GetNeighbour(Direction.Right).Check(Direction.Right);
		if(dir != Direction.Right) l = GetField().GetNeighbour(Direction.Left).Check(Direction.Left);
		if(dir != Direction.Down) u = GetField().GetNeighbour(Direction.Up).Check(Direction.Up);
		if(dir != Direction.Up) d = GetField().GetNeighbour(Direction.Down).Check(Direction.Down);
        return (r != false || u != false) && (l != false || u != false) && (r != false || d != false) && (l != false || d != false);
	}
	
	@Override
	public boolean isBlocking() {
		
		return true;
		
	}
	
}