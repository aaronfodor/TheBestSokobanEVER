package Controller;
import Model.Direction;
import Model.Field;
import Model.Player;
import Model.Warehouse;
import View.DrawingManager;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//Draw manages every drawings -> Game needs only this class to communicate with view.DrawingManager

/** 
 * The class that controls the whole Game
 * 
 * @author Arpad Fodor
 *
 */
public class Game implements KeyListener{
	
	/**
	 * Warehouse in the Game
	 */
	private Warehouse warehouse;
	
	private DrawingManager GraphicalDisplay;
	
	/**
	 * List of Players in the Game
	 */
	private List<Player> players = new ArrayList<Player>();
	
	/**
	 * Maximum possible points that a Player can reach
	 */
	private int maxpoints;
	
	/**
	 * String for displaying text on the console
	 */
	private String additionaltext = null;
	
	/**
	 * flag to represent whether the current game has finished or not
	 */
	private boolean GamePlayFinished = false;
	
	
	/**
	 * Game constructor
	 * Creates the Warehouse
	 */
	public Game(DrawingManager d){
		
		warehouse = new Warehouse(this);
		GraphicalDisplay = d;
		
	}
	
	/**
	 * Creates the map of the Warehouse
	 * Adds the players to the Game
	 * Starts the Game
	 * 
	 * @param 	s 		String that contains the map for the Warehouse
	 */
	public void StartGame(List<String> s) {
		
		this.GamePlayFinished = false;
		players.clear();
		
		warehouse.MakePlayingField(s);
		List<Player> tmpplayers = warehouse.GetPlayers();
		for(int i = 0; i < tmpplayers.size(); i++)
		{
			players.add(tmpplayers.get(i));
		}
		
		System.out.println("\n[Gameplay started]");
		
	}
	
	public void ReadMap(String mapname) throws IOException{
		
		try {
			
			String path = mapname;
			FileReader fr = new FileReader(path);
			BufferedReader br = new BufferedReader(fr);
			String[] sp = {""};
			String s = "";
			List<String> palya = new ArrayList<String>();
			while(!sp[0].equals("exit"))
			{
				String tmp = br.readLine(); 
				if(tmp != null)
				{
					sp = tmp.split(" ");
					switch(sp[0])
					{
						case "createmap": 
							{
								palya.add(sp[1]);
								palya.add(sp[2]);
								palya.add(sp[3]);
								palya.add(sp[4]);
								palya.add("");
								String str = "";
								while(!s.equals("endcreate") && s != null)
								{
									s = br.readLine();
									if(s.startsWith("setstrength"))
									{
										String[] spi = s.split(" ");
										str = str.concat(spi[1] + " ");
										str = str.concat(spi[2] + " ");
									}
									else if(!s.equals("endcreate")) palya.add(s);
								}
								palya.remove(4);
								palya.add(4, str);
							} break;
						case "startgame":
							{
								StartGame(palya);
							}break;
						default: break;
					}
				}
			}
			
			br.close();
			
		}
		
		catch(Exception ex){
			
		    ex.printStackTrace();
		    
		}
		
	}
	
	/**
	* Moves a given Player in a given Direction
	* Writes the map of the Warehouse onto the console
	* 
	* @param 	player_string 	The	name of the Player that should be moved
	* @param 	d				Direction in which the Player should move
	*/
	public void MovePlayer(String player_string, Direction d){
		
		List<Player> plas = warehouse.GetPlayers();
		if(player_string.length() != 2 || player_string.charAt(1)-48 > players.size() || player_string.charAt(1)-48 < 1) 
			{
				return;
			}
		Player pla = players.get(player_string.charAt(1)-49);
		for(int o = 0; o < plas.size(); o++)
		{
			if(plas.get(o) == pla) plas.get(o).Move(d);
		}
		warehouse.WriteMap();
		if(additionaltext != null) 
			{
				System.out.println(additionaltext); 
				additionaltext = null;
			}
		
	}
	
	/**
	 * Sets any additional text that should be displayed on the console
	 * 
	 * @param 	o 		Object whose name is needed
	 * @param 	s		Additional text
	 */
	public void setAddtext(Object o, String s){
		
		additionaltext = warehouse.GetMapItem(o) + s;
		
	}
	
	/**
	 * Sets the substance of the player's field
	 * Writes the map of the Warehouse onto the console
	 * 
	 * @param 	pl 		The player's name who puts down the substance
	 * @param 	st		The name of the substance
	 */
	public void place(String pl, String st){
		
		if(pl.length() != 2 || pl.charAt(1)-48 > players.size() || pl.charAt(1)-48 < 1) 
		{
			return;
		}
		Player player = players.get(pl.charAt(1)-49);
		if(player.GetField() != null)
		{
			if(st.equals("honey")) 
				{
					Object o = player.PutHoney();
					if(o != null) 
						{
							warehouse.SetMapItem(o, "sh");
							warehouse.WriteMap();
							GraphicalDisplay.UpdatePlayerInfo(pl, player.GetPoints(), player.GetHoney(), player.GetOil());
						}
					else System.out.println("Ran out of honey.");
				}
			else if(st.equals("oil"))
				{
					Object o = players.get(pl.charAt(1)-49).PutOil();
					if(o != null) 
						{
							warehouse.SetMapItem(o, "so");
							warehouse.WriteMap();
							GraphicalDisplay.UpdatePlayerInfo(pl, player.GetPoints(), player.GetHoney(), player.GetOil());
						}
					else System.out.println("Ran out of oil.");
				}
			
		}
		
	}
	
	/**
	 * Warehouse getter method
	 * Returns the Warehouse in the Game
	 * 
	 * @return 	Warehouse	Warehouse in the Game	
	 */
	public Warehouse GetWarehouse(){
		
		return warehouse;
		
	}
	
	/**
	 * Warehouse notifies Game that field has been changed
	 * Game passes the received Field to Draw class
	 * 
	 * @param	f		Field that has been changed
	 * @param	x		Warehouse array x position
	 * @param	y		Warehouse array y position
	 */
	public void WarehouseChanged(Field f, int x, int y){
		
		GraphicalDisplay.RefreshDraw(f, x, y);
		
	}
	
	/**
	 * Given player wins the game
	 * End of the Game
	 * 
	 * @param 	p 		Player that won
	 */
	public void Win(Player p){
		
		warehouse.WriteMap();
		if(additionaltext != null) 
		{
			System.out.println(additionaltext); 
			additionaltext = null;
		}
		System.out.println("[Gameplay finished. Winner: " + warehouse.GetMapItem(p) + "]");
		GraphicalDisplay.SetWin(warehouse.GetMapItem(p));
		this.GamePlayFinished = true;
		
	}
	
	/**
	 * Called if all the Players died, or if there are no more movable Boxes
	 * Checks which Player won
	 * End of the Game
	 */
	public void CheckResult(){
		
		if(players.size() > 1) {
			//sorting into decreasing order
			for(int i=0; i<players.size(); i++) {
				for(int j = i; j < players.size(); j++) {
					if(players.get(i).GetPoints() < players.get(j).GetPoints()) {
						Player p = (players.get(i));
						players.set(i, players.get(j));
						players.set(j, p);
					}
				}
				
			}
			//if draw
			if(players.get(0).GetPoints() == players.get(1).GetPoints()) {
				int max = players.get(0).GetPoints();
				List<Player> whplayers = new ArrayList<Player>();
				//checking who is still alive
				for(int k=0; k < warehouse.GetPlayers().size(); k++) {
					if(warehouse.GetPlayers().get(k).GetPoints() == max) {
						whplayers.add(warehouse.GetPlayers().get(k));
					}
				}
				//ha egy el, az nyer
				if(whplayers.size() == 1)
					Win(whplayers.get(0));
				//ha mindenki halott
				else if(whplayers.size() == 0) {
					List<Player> gplayers = new ArrayList<Player>();
						//megnezi hogy a gameben kinek van annyi pontja, es becseszi a g listaba
						for(int k=0; k < players.size(); k++) {
							if(players.get(k).GetPoints() == max) {
								gplayers.add(players.get(k));
							}
						}
						Draw(gplayers);
					}
				//ha tobben elnek
				else Draw(whplayers);
				}
			else Win(players.get(0));
			}
			//ha eleve csak egy jatekos volt
			else Win(players.get(0));
		
	}
	
	/**
	 * Called if there is a draw
	 * End of the Game
	 * 
	 * @param 	pls 		list of Player that the draw is between
	 */
	public void Draw(List<Player> pls) {
		warehouse.WriteMap();
		System.out.print("[Gameplay finished. Draw: ");
		for(int i=0; i < pls.size(); i++) {
			System.out.print(warehouse.GetMapItem(pls.get(i)) + " ");
			GraphicalDisplay.SetDraw(warehouse.GetMapItem(pls.get(i)));
		}
		System.out.print("have equal points.]\n");
		this.GamePlayFinished = true;
	}
	
	/**
	 * Returns the maximum points a Player can get in the Game
	 * 
	 * @return 	int		maximum points a Player can have
	 */
	public int GetMaxPoints(){
		
		return maxpoints;
		
	}
	
	/**
	 * Sets the maximum points a Player can get in the Game
	 * 
	 * @param 	mp		maximum points a Player can have
	 */
	public void SetMaxPoints(int mp){
		
		maxpoints = mp;
		
	}
	
	/** 
	 * transmitter method -> calls Draw method
	 */
	public void UpdatePlayerInfo(String pl, int points, int honey, int oil){
		
		GraphicalDisplay.UpdatePlayerInfo(pl, points, honey, oil);
		
	}
	
	/** 
	 * transmitter method -> calls Warehouse method
	 */
	public int GetHeight(){
		
		return warehouse.GetHeight();
		
	}
	
	/** 
	 * transmitter method -> calls Warehouse method
	 */
	public int GetWidth(){
		
		return warehouse.GetWidth();
		
	}
	
	/** 
	 * transmitter method -> calls Warehouse method
	 */
	public String GetMapItem(Object o){
		
		return warehouse.GetMapItem(o);
		
	}
	
	/** 
	 * transmitter method -> calls Warehouse method
	 */
	public void WriteMap(){
		
		warehouse.WriteMap();
		
	}
	
	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		int r = arg0.getKeyCode();
		
		//if the game has not finished, process KeyEvents
		if(!GamePlayFinished) {
			
			switch(r)
			{
				case KeyEvent.VK_RIGHT: MovePlayer("p1", Direction.Right);break;
				case KeyEvent.VK_LEFT: MovePlayer("p1", Direction.Left);break;
				case KeyEvent.VK_UP: MovePlayer("p1", Direction.Up);break;
				case KeyEvent.VK_DOWN: MovePlayer("p1", Direction.Down);break;
				case KeyEvent.VK_PAGE_UP: place("p1", "honey");break;
				case KeyEvent.VK_PAGE_DOWN: place("p1", "oil");break;
				case KeyEvent.VK_D : MovePlayer("p2", Direction.Right);break;
				case KeyEvent.VK_A: MovePlayer("p2", Direction.Left);break;
				case KeyEvent.VK_W: MovePlayer("p2", Direction.Up);break;
				case KeyEvent.VK_S: MovePlayer("p2", Direction.Down);break;
				case KeyEvent.VK_Q: place("p2", "honey");break;
				case KeyEvent.VK_E: place("p2", "oil");break;
				case KeyEvent.VK_H: MovePlayer("p3", Direction.Right);break;
				case KeyEvent.VK_F: MovePlayer("p3", Direction.Left);break;
				case KeyEvent.VK_T: MovePlayer("p3", Direction.Up);break;
				case KeyEvent.VK_G: MovePlayer("p3", Direction.Down);break;
				case KeyEvent.VK_R: place("p3", "honey");break;
				case KeyEvent.VK_Z: place("p3", "oil");break;
				case KeyEvent.VK_L: MovePlayer("p4", Direction.Right);break;
				case KeyEvent.VK_J: MovePlayer("p4", Direction.Left);break;
				case KeyEvent.VK_I: MovePlayer("p4", Direction.Up);break;
				case KeyEvent.VK_K: MovePlayer("p4", Direction.Down);break;
				case KeyEvent.VK_U: place("p4", "honey");break;
				case KeyEvent.VK_O: place("p4", "oil");break;
			}
			
		}
		
	}
	
}
