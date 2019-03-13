package Model;

import Controller.Game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 
 * The Warehouse is the playing field in the Game
 * The collection of Fields, Boxes, Players and Substances form the Warehouse
 */
public class Warehouse {
	
	/** 
	 * Game that the Warehouse is a part of
	 */
	private Game game;
	
	/** 
	 * List of Players still in the Warehouse
	 */
	private List<Player> players = new ArrayList<Player>();
	
	/** 
	 * List of Boxes still in the Warehouse
	 */
	private List<Box> boxes;
	
	/** 
	 * Matrix of Fields in the Warehouse
	 */
	private Field[][] fields;
	
	/** 
	 * Width of the Warehouse
	 */
	private int x;
	
	/** 
	 * Height of the Warehouse
	 */
	private int y;
	
	/** 
	 * Map containing the objects in the Warehouse with their given names
	 */
	private Map<Object, String> map = new HashMap<Object, String>();
	
	
	/** 
	 * Warehouse constructor
	 * Sets the Game that the Warehouse is in
	 * 
	 * @param	g		Game that the Warehouse is in
	 */
	public Warehouse(Game g)
	{
		game = g;
		
	}
	
	/** 
	 * Creates the playing field
	 * Adds the players, boxes, switches etc from the input in the given list of strings
	 * 
	 * @param	s		list of strings that form the input instructions
	 */
	public void MakePlayingField(List<String> s)
	{
		x = Integer.parseInt(s.get(0));
		y = Integer.parseInt(s.get(1));
		int playernumber = Integer.parseInt((s.get(2)));
		for(int t = 0; t < playernumber; t++)
		{
			players.add(null);
		}
		Hole[] holes = new Hole[Integer.parseInt(s.get(3))];
		int[] switches = new int[Integer.parseInt(s.get(3))*2];
		List<String> bases = new ArrayList<String>();
		fields = new Field[x][y];
		String[] putin;
		for(int k = 5; k < s.size(); k++)
		{
				putin = s.get(k).split(",");
				for(int l = 0; l < x; l++)
				{
					switch(putin[l].charAt(0))
					{
						case 'w':
						{
							Wall w = new Wall(this);
							fields[l][k-5] = w;
							map.put(w, "ww");
						} break;
						case 'h': 
						{
							Hole lik = new Hole(this, true);
							if(putin[l].length() > 1) holes[putin[l].charAt(1)-49] = lik; 
							fields[l][k-5] = lik;
							map.put(lik, "hh");
						} break;
						case 's':
						{
							if(Integer.parseInt(s.get(3)) > 0 && putin[l].charAt(1)-48 > 0 && putin[l].charAt(1)-49 < Integer.parseInt(s.get(3)))
							{
								switches[(putin[l].charAt(1)-49)*2] = l;
								switches[((putin[l].charAt(1)-49)*2)+1] = k-5;
							}
							else
							{
								Field f =  new Field(this);
								if(putin[l].equals("sh"))
								{
									Honey h = new Honey();
									f.SetSubstance(h);
									map.put(h, "sh");
								}
								else if(putin[l].equals("so"))
								{
									Oil o = new Oil();
									f.SetSubstance(o);
									map.put(o, "so");
								}
								else;
								fields[l][k-5] = f;
								map.put(f, "  ");
							}
						} break;
						case 'c':
						{
							bases.add(putin[l].charAt(1) + "," + l + "," + (k-5));
						}break;
						case 'p':
						{
							Field f =  new Field(this);
							fields[l][k-5] = f;
							map.put(f, "  ");
							players.remove(putin[l].charAt(1) - 49);
							Player p = new Player(0, f, this, 3, 3);
							players.add((putin[l].charAt(1) - 49), p);
							fields[l][k-5].SetThing(players.get(putin[l].charAt(1) - 49));
							map.put(p, putin[l]);
						} break;
						case 'b':
						{
							if(boxes == null) boxes = new ArrayList<Box>();
							Field f =  new Field(this);
							fields[l][k-5] = f;
							map.put(f, "  ");
							Box b = new Box(f);
							boxes.add(b);
							map.put(b, "bb");
							fields[l][k-5].SetThing(boxes.get(boxes.size() - 1));
						} break;
						default :
						{
							Field f =  new Field(this);
							fields[l][k-5] = f;
							map.put(f, "  ");
						}break;
					}
				}
		}
		
		for(int i = 0; i < switches.length/2; i++)
		{
			Switch swi =  new Switch(this, holes[i]);
			fields[switches[i*2]][switches[(i*2)+1]] = swi;
			map.put(swi, "ss");
		}
		
		if(bases.size() == 0) game.SetMaxPoints(1);
		else game.SetMaxPoints(bases.size()/2);
		if(bases.size() % 2 == 1) game.SetMaxPoints(game.GetMaxPoints() + 1);
		for(int i = 0; i < bases.size(); i++)
		{
			String[] sb = bases.get(i).split(",");
			Base b = new Base(this, players.get(Integer.parseInt(sb[0])-1));
			fields[Integer.parseInt(sb[1])][Integer.parseInt(sb[2])] = b;
			map.put(b, "c" + sb[0]);
		}
		
		String[] splitted = s.get(4).split(" ");
		int[] ero = new int[splitted.length/2];
		for(int r = 0; r < splitted.length/2; r++)
		{
			ero[splitted[r*2].charAt(1)-49] = Integer.parseInt(splitted[(r*2) + 1]);
		}
		for(int e = 0; e < players.size(); e++)
		{
			players.get(e).SetStrength(ero[e]+1);
		}
		
		for(int k = 0; k < x; k++)
		{
			for(int l = 0; l < y; l++)
			{
				if(k > 0) fields[k][l].SetNeighbour(fields[k-1][l], Direction.Left);
				if(k < x-1) fields[k][l].SetNeighbour(fields[k+1][l], Direction.Right);
				if(l > 0) fields[k][l].SetNeighbour(fields[k][l-1], Direction.Up);
				if(l < y-1) fields[k][l].SetNeighbour(fields[k][l+1], Direction.Down);
			}
		}
		
	}
	
	/** 
	 * Players getter method
	 * Returns the list of Players in the Warehouse
	 * 
	 * @return List<Player>	list of Players in the Warehouse
	 */
	public List<Player> GetPlayers()
	{
		return players;
	}
	
	/** 
	 * Field getter method
	 * Returns the Field at the given coordinates
	 * 
	 * @param	horizontal	x coordinate of the Field
	 * @param	vertical	y coordinate of the Field
	 * @return 	Field		Field at the given coordinates
	 */
	public Field GetField(int horizontal, int vertical)
	{
		return  fields[horizontal][vertical];
	}
	
	/** 
	 * Deletes a Thing from the Warehouse
	 * 
	 * @param	t			Thing that should be deleted from the Warehouse
	 */
	public void Delete(Thing t)
	{
		
	}
	
	/** 
	 * Deletes a Player from the Warehouse
	 * 
	 * @param	p			Player that should be deleted from the Warehouse
	 */
	public void Delete(Player p)
	{
		p.SetField(null);
		players.remove(p);
		
	}
	
	/** 
	 * Deletes a Box from the Warehouse
	 * 
	 * @param	b			Box that should be deleted from the Warehouse
	 */
	public void Delete(Box b)
	{
		b.SetField(null);
		boxes.remove(b);
		
	}
	
	/** 
	 * Checks the result of the game
	 * If there are no more Players or Boxes left in the Warehouse
	 */
	public void Empty()
	{
		if(boxes != null)
		{
			if(players.isEmpty() || boxes.isEmpty()) game.CheckResult();
			CheckBoxes();
		}
	}
	
	/** 
	 * Checks whether there still are movable Boxes in the Warehouse or not
	 */
	public void CheckBoxes()
	{
			for(int i = 0; i < boxes.size(); i++)
			{
				boolean b = boxes.get(i).CheckIfMovable(null);
				if(b == true)
					return;
			}
				game.CheckResult();
	}
	
	/** 
	 * Draws the appropriate symbol for a Hole on the console
	 * Depends on the state of the Hole
	 * 
	 * @param	b		Hole's state (active or inactive)
	 * @param	h		Hole that is being drawn
	 */
	public void HolePrintset(boolean b, Hole h){
		
		if(b == false) map.replace(h, "  ");
		else map.replace(h, "hh");
		
	}
	
	/** 
	 * Passes the fields of the Warehouse to the Game to be painted
	 */
	public void WriteMap(){
		
		for(int i = 0; i < x; i++){
			
			for(int j = 0;j < y; j++){
				
				Field f = (fields[i][j]);
				game.WarehouseChanged(f, i, j);
				
			}
			
		}
		
	}
	
	public int GetWidth(){
		
		return x;
		
	}
	
	public int GetHeight(){
		
		return y;
		
	}
	
	/** 
	 * Returns the name of a given object from the map
	 * 
	 * @param	o		object whose name is needed
	 * @return	String	name of the object
	 */
	public String GetMapItem(Object o){
		
		return map.get(o);
		
	}
	
	/** 
	 * Sets the name of a given object in the map
	 * 
	 * @param	o		object whose name is set
	 * @return	String	name of the object
	 */
	public void SetMapItem(Object o, String s){
		
		map.put(o, s);
		
	}
	
	/** 
	 * transmitter method -> calls Game method
	 */
	public int GetMaxPoints(){
		
		return game.GetMaxPoints();
		
	}
	
	/** 
	 * transmitter method -> calls Game method
	 */
	public void Win(Player p){
		
		game.Win(p);
		
	}
	
	/** 
	 * transmitter method -> calls Game method
	 */
	public void setAddtext(Object o, String s){
		
		game.setAddtext(o, s);
		
	}
	
	/** 
	 * transmitter method -> calls Game method
	 */
	public void UpdatePlayerInfo(String pl, int points, int honey, int oil){
		
		game.UpdatePlayerInfo(pl, points, honey, oil);
		
	}
	
}
