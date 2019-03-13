package Model;

/** 
 * A movable Player in the Warehouse
 * The Players are the what can directly be controlled
 */
public class Player extends Thing{

	/**
	 * Player's points
	 */
	private int points;
	
	/**
	 * Game that the Player is in
	 */
	private Warehouse w;
	
	/**
	 * Number of Honey that the player can place in the Warehouse
	 */
	int honey;
	
	/**
	 * Number of Oil that the player can place in the Warehouse
	 */
	int oil;
	
	/**
	 * Player's strength
	 * The number of Things it can move at once
	 */
	int str;
	
	/** 
	 * Player constructor
	 * Sets the Player's initial points
	 * Sets the Field on which the Player is standing
	 * Sets the Game that the Player is in
	 * Sets the number of Oil and Honey the Player can place in the Warehouse
	 * 
	 * @param	p 		Player's initial points
	 * @param	f 		Field where the Player is standing
	 * @param	w	 	Warehouse that the player is in
	 * @param	o 		number of Oil that the Player can place in the Warehouse
	 * @param	h 		number of Honey that the Player can place in the Warehouse
	 */
	public Player(int p, Field f, Warehouse w, int o, int h) {
		
		super(f);
		this.points = p;
		this.w = w;
		this.oil = o;
		this.honey = h;
		
	}
	
	/** 
	 * Sets the Player's strength
	 * 
	 * @param	str_new 	Player's new strength
	 * @return	void
	 */
	public void SetStrength(int str_new) {
		str = str_new;
	}
	
	/** 
	 * Player moves in a given direction
	 * If the Player reaches the maximum points after a move, they win the game
	 * 
	 * @param	d		Direction in which the Player moves
	 * @return	void
	 */
	public void Move(Direction d)
	{
		GetField().TryMove(d, str);
		if(points == w.GetMaxPoints()) {
			w.Win(this);
		}
		GetField().GetWarehouse().Empty();
	}
	
	/** 
	 * Adds a point for the Player
	 * 
	 * @return	void
	 */
	public void AddPoints() {
		points += 1;
		w.UpdatePlayerInfo(w.GetMapItem(this), points, honey, oil);
	}
	
	/** 
	 * Takes a point away from the Player
	 * 
	 * @return	void
	 */
	public void MinusPoints() {
		points -= 1;
		w.UpdatePlayerInfo(w.GetMapItem(this), points, honey, oil);
	}
	
	/** 
	 * Points getter method
	 * Returns the Player's points
	 * 
	 * @return	int		Player's points
	 */
	public int GetPoints() {
		return points;
	}
	
	/**
	 * The Player is hit by a Player in a given Direction
	 *
	 * @param 	d 		Direction in which the Player wants to push the Player
	 * @param 	p 		Player that hit the Player
	 * @param 	str 		number of Things that can still be pushed
	 * @return 	int		modified number of Things that can still be pushed
	 */
	public int HitBy(Direction d, Player p, int str) {
		str -=1;
		return str;
	}

	/**
	 * The Player is hit by a Box in a given Direction
	 *
	 * @param 	d 		Direction in which the Box wants to push the Player
	 * @param 	b 		Box that hit the Player
	 * @param 	str 	number of Things that can still be pushed
	 * @return 	int		modified number of Things that can still be pushed
	 */
	public int HitBy(Direction d, Box b, int str) {
		str -=1;
		GetField().Pushed(d, str);
		return str;
	}
	
	/** 
	 * Player pushes Player in a given Direction
	 * 
	 * @param 	d 		Direction in which the Player wants to push the Player
	 * @param 	p 		Player that wants to push the Player
	 * @param 	str 		number of Things that can still be pushed
	 * @return 	int		modified number of Things that can still be pushed
	 */
	public int PushedBy(Direction d,Player p, int str) {
		str -= 1;
		GetField().Pushed(d, str);
		return str;
	}

	/** 
	 * Player pushing a Thing in a given Direction
	 * 
	 * @param	d		Direction in which the Player wants to move
	 * @param 	t 		Thing that the Player is pushing
	 * @param 	str 		number of things that can still be pushed
	 * @return 	int		modified number of things that can still be pushed
	 */
	public int Pushing(Direction d,Thing t, int str) {
		str = t.PushedBy(d, this, str);
		return str;
	}
	
	/** 
	 * Thing collides with this Player
	 * 
	 * @param	d		Direction in which the Thing wants to move
	 * @param 	t 		Thing that collides with this Player
	 * @param 	str 	number of Things that can still be pushed
	 * @return 	int		modified number of Things that can still be pushed
	 */
	public int CollideWith(Direction d, Thing t, int str){
		str = t.HitBy(d, this, str);
		return str;
	}
	
	/** 
	 * Player's move was accepted, it successfully stepped onto a new Field
	 * 
	 * @param 	new_home Field where the Player stepped
	 * @return 	void
	 */
	public void AcceptMove(Field new_home){
		GetField().Remove(this);
		new_home.Accept(this);
		SetField(new_home);
	}
	
	/** 
	 * Player places Oil on a Field
	 * 
	 * @return	Oil		Oil that was placed
	 */
	public Oil PutOil() {
		if(oil>0) {
			oil--;
			Oil o = new Oil();
			GetField().SetSubstance(o);
			return o;
		}
		else return null;
	}
	
	/** 
	 * Player places Honey on a Field
	 * 
	 * @return	Honey		Honey that was placed
	 */
	public Honey PutHoney() {
		if(honey>0) {
			honey--;
			Honey h = new Honey();
			GetField().SetSubstance(h);
			return h;
		}
		else return null;
	}
	
	/**
	 * The Player is standing on a Field that has some Substance on it
	 * Calls the correct method that updates the number of Things that can still be pushed
	 * 
	 * @param 	s 		Substance that the Player is standing on
	 * @param 	str 		number of Things that can still be pushed
	 * @return 	int		modified number of Things that can still be pushed
	 */
	public int OnSubstance (Substance s, int str){
		int new_str = s.SubstanceByPlayer(str);
		return new_str;
	}
	
	/**
	 * Checks whether the Player is still movable or not in the given Direction
	 * 
	 * @param 	dir 		Direction in which it is checking
	 * @return 	boolean	whether the Player is movable or not
	 */
	public boolean CheckIfMovable(Direction dir) {
		return true;
	}
	
	public int GetHoney()
	{
		return honey;
	}
	
	public int GetOil()
	{
		return oil;
	}
	
	@Override
	public boolean isBlocking() {
		
		return false;
		
	}
	
}