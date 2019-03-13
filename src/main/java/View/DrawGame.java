package View;

import Model.Field;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

//DrawGame needs to know model.Field to be able to transform it to a printable DrawField

/**
 * DrawGame draws the game screen onto a label.
 * @author Arpad Fodor
 *
 */
public class DrawGame {
	
	private DrawingManager draw;
	
	//size of a Field
	protected int Field_size = 50;
	//game screen
	protected JPanel game_screen;
	//contains the game field
	protected Container Map_Container;
	//top menu bar
	protected JMenuBar top_menu;
	//buttons of the menu bar
	protected JButton Exit;
	//the button used to show whose turn is the current one
	protected JButton Player1Show, Player2Show, Player3Show, Player4Show;
	//information JTextFields
	protected JTextArea txtSeparator, txtPlayer1, txtPlayer2, txtPlayer3, txtPlayer4;
	//list of Fields
	protected ArrayList<DrawField> Warehouse;
	int Field_ID;
	//icons to display on the Fields
	ImageIcon IconExit;
    ImageIcon IconWall;
    ImageIcon IconPlayer1;
    ImageIcon IconPlayer2;
    ImageIcon IconPlayer3;
    ImageIcon IconPlayer4;
    ImageIcon IconBox;
    ImageIcon IconHole;
    ImageIcon IconSwitch;
    ImageIcon IconOil;
    ImageIcon IconHoney;
    ImageIcon IconFloor;
    
    //basic colours
	Color backgroundColor = null;
	Color switchColor = Color.gray;
	Color player1Color = new Color(255, 89, 89);//Color.red;
	Color player2Color = new Color(114, 187, 255);//Color.blue;
	Color player3Color = new Color(149, 239, 100);//Color.green;
	Color player4Color = new Color(237, 218, 113);//Color.orange;
	
	public DrawGame(DrawingManager d, JPanel game_screen, JButton Exit) {
		
		draw = d;
		this.game_screen = game_screen;
		//we need this button from the Draw object as it holds button listeners
		this.Exit = Exit;
		
		//loading the icons
		IconExit = new ImageIcon("src/visuals/cross.png");
		IconPlayer1 = new ImageIcon("src/visuals/worker_red.png");
		IconPlayer2 = new ImageIcon("src/visuals/worker_blue.png");
		IconPlayer3 = new ImageIcon("src/visuals/worker_green.png");
		IconPlayer4 = new ImageIcon("src/visuals/worker_orange.png");
		IconHole = new ImageIcon("src/visuals/hole.png");
		IconSwitch = new ImageIcon("src/visuals/switch.png");
		IconOil = new ImageIcon("src/visuals/oil.png");
		IconHoney = new ImageIcon("src/visuals/honey.png");
		IconFloor = new ImageIcon();
		IconWall = new ImageIcon();
		IconBox = new ImageIcon();
		
        game_screen.setBackground(UIManager.getColor("TextPane.selectionBackground"));
		game_screen.setLayout(new BorderLayout(0, 0));
		
        //ArrayList to store game player colours
		Warehouse = new ArrayList<DrawField>();
		
		//top menu bar
		top_menu = new JMenuBar();
		
		//Map_Container setting-up
		Map_Container = new Container();
		
		//GridBagLayout building-up
		GridBagLayout gbl_Map_Container = new GridBagLayout();
		gbl_Map_Container.columnWidths = new int[] {};
		gbl_Map_Container.rowHeights = new int[] {};
		gbl_Map_Container.columnWeights = new double[]{};
		gbl_Map_Container.rowWeights = new double[]{};
		//setting Map_Container layout to GridBagLayout
		Map_Container.setLayout(gbl_Map_Container);
	}
	
	public void StartGame(Integer PlayerNum, Integer MapID, ImageIcon Floor, ImageIcon Wall, ImageIcon Box){
		
		//if there were previous games, remove their components to add new game elements instead
		Warehouse.removeAll(Warehouse);
		top_menu.removeAll();
		Map_Container.removeAll();
		
		//setting previously loaded floor, wall, box icons
		IconFloor = Floor;
		IconWall = Wall;
		IconBox = Box;
		
		//Exit button setting-up
		//setting Exit button placement
		Exit.setHorizontalAlignment(SwingConstants.CENTER);
		//setting Exit button icon
		Exit.setIcon(IconExit);
		//adding the button to the top_menu bar
		top_menu.add(Exit);
		
		//txtSeparator JTextArea setting-up
		txtSeparator = new JTextArea();
		//setting the text
		txtSeparator.setText("");
		//setting the font
		txtSeparator.setFont(new Font("Tahoma", Font.PLAIN, 16));
		//setting txtSeparator transparent
		txtSeparator.setOpaque(false);
		//setting foreground to black
		txtSeparator.setForeground(Color.black);
		//setting non-editable
		txtSeparator.setEditable(false);
		//setting no border
		txtSeparator.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		//setting 5 columns
		txtSeparator.setColumns(3);
    	//adding txtWarOfEmpires to top_menu bar 
		top_menu.add(txtSeparator);
		
		//Player1Show button setting-up
		Player1Show = new JButton();
		//setting MainShow placement
		Player1Show.setHorizontalAlignment(SwingConstants.CENTER);
		//setting the font
		Player1Show.setFont(new Font("Tahoma", Font.PLAIN, 16));
		//setting the text
		Player1Show.setText("Player 1");
		//text colour
		Player1Show.setForeground(Color.black);
		//setting the text alignment
		Player1Show.setHorizontalAlignment(JTextField.CENTER);
		//setting MainShow non-transparent
		Player1Show.setOpaque(true);
		//setting the background colour
		Player1Show.setBackground(player1Color);
		//enabling it
		Player1Show.setEnabled(true);
		//setting no border
		Player1Show.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		
		//adding TurnShow to top_menu bar 
		top_menu.add(Player1Show);
		
		//txtPlayer1 JTextArea setting-up
		txtPlayer1 = new JTextArea();
		//setting the text
		txtPlayer1.setText("    Score: 0\n"
				+ "    Oil: 3 Honey: 3");
		//setting the font
		txtPlayer1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		//setting txtPlayer1 transparent
		txtPlayer1.setOpaque(false);
		//setting foreground to black
		txtPlayer1.setForeground(Color.black);
		//setting non-editable
		txtPlayer1.setEditable(false);
		//setting no border
		txtPlayer1.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		//setting 5 columns
		txtPlayer1.setColumns(5);
    	//adding txtWarOfEmpires to top_menu bar 
		top_menu.add(txtPlayer1);
		
		//Player2Show button setting-up
		Player2Show = new JButton();
		//setting MainShow placement
		Player2Show.setHorizontalAlignment(SwingConstants.CENTER);
		//setting the font
		Player2Show.setFont(new Font("Tahoma", Font.PLAIN, 16));
		//setting the text
		Player2Show.setText("Player 2");
		//text colour
		Player2Show.setForeground(Color.black);
		//setting the text alignment
		Player2Show.setHorizontalAlignment(JTextField.CENTER);
		//setting MainShow non-transparent
		Player2Show.setOpaque(true);
		//setting the background colour
		Player2Show.setBackground(player2Color);
		//enabling it
		Player2Show.setEnabled(true);
		//setting no border
		Player2Show.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		//adding TurnShow to top_menu bar 
		
		top_menu.add(Player2Show);
		
		//txtPlayer2 JTextArea setting-up
		txtPlayer2 = new JTextArea();
		//setting the text
		txtPlayer2.setText("    Score: 0\n"
				+ "    Oil: 3 Honey: 3");
		//setting the font
		txtPlayer2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		//setting txtPlayer2 transparent
		txtPlayer2.setOpaque(false);
		//setting foreground to black
		txtPlayer2.setForeground(Color.black);
		//setting non-editable
		txtPlayer2.setEditable(false);
		//setting no border
		txtPlayer2.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		//setting 5 columns
		txtPlayer2.setColumns(5);
		//adding txtEventInformer to top_menu bar 
		top_menu.add(txtPlayer2);
		
		if(PlayerNum >= 3) {
			
			//Player3Show button setting-up
			Player3Show = new JButton();
			//setting MainShow placement
			Player3Show.setHorizontalAlignment(SwingConstants.CENTER);
			//setting the font
			Player3Show.setFont(new Font("Tahoma", Font.PLAIN, 16));
			//setting the text
			Player3Show.setText("Player 3");
			//text colour
			Player3Show.setForeground(Color.black);
			//setting the text alignment
			Player3Show.setHorizontalAlignment(JTextField.CENTER);
			//setting MainShow non-transparent
			Player3Show.setOpaque(true);
			//setting the background colour
			Player3Show.setBackground(player3Color);
			//enabling it
			Player3Show.setEnabled(true);
			//setting no border
			Player3Show.setBorder(javax.swing.BorderFactory.createEmptyBorder());
			//adding TurnShow to top_menu bar 
			top_menu.add(Player3Show);
			
			//txtPlayer3 JTextArea setting-up
			txtPlayer3 = new JTextArea();
			//setting the text
			txtPlayer3.setText("    Score: 0\n"
					+ "    Oil: 3 Honey: 3");
			//setting the font
			txtPlayer3.setFont(new Font("Tahoma", Font.PLAIN, 16));
			//setting txtPlayer3 transparent
			txtPlayer3.setOpaque(false);
			//setting foreground to black
			txtPlayer3.setForeground(Color.black);
			//setting non-editable
			txtPlayer3.setEditable(false);
			//setting no border
			txtPlayer3.setBorder(javax.swing.BorderFactory.createEmptyBorder());
			//setting 5 columns
			txtPlayer3.setColumns(5);
			//adding txtEventInformer to top_menu bar 
			top_menu.add(txtPlayer3);
			
		}
		
		if(PlayerNum >= 4) {
			
			//Player4Show button setting-up
			Player4Show = new JButton();
			//setting MainShow placement
			Player4Show.setHorizontalAlignment(SwingConstants.CENTER);
			//setting the font
			Player4Show.setFont(new Font("Tahoma", Font.PLAIN, 16));
			//setting the text
			Player4Show.setText("Player 4");
			//text colour
			Player4Show.setForeground(Color.black);
			//setting the text alignment
			Player4Show.setHorizontalAlignment(JTextField.CENTER);
			//setting MainShow non-transparent
			Player4Show.setOpaque(true);
			//setting the background colour
			Player4Show.setBackground(player4Color);
			//enabling it
			Player4Show.setEnabled(true);
			//setting no border
			Player4Show.setBorder(javax.swing.BorderFactory.createEmptyBorder());
			//adding TurnShow to top_menu bar 
			top_menu.add(Player4Show);
			
			//txtPlayer4 JTextArea setting-up
			txtPlayer4 = new JTextArea();
			//setting the text
			txtPlayer4.setText("    Score: 0\n"
					+ "    Oil: 3 Honey: 3");
			//setting the font
			txtPlayer4.setFont(new Font("Tahoma", Font.PLAIN, 16));
			//setting txtPlayer4 transparent
			txtPlayer4.setOpaque(false);
			//setting foreground to black
			txtPlayer4.setForeground(Color.black);
			//setting non-editable
			txtPlayer4.setEditable(false);
			//setting no border
			txtPlayer4.setBorder(javax.swing.BorderFactory.createEmptyBorder());
			//setting 5 columns
			txtPlayer4.setColumns(5);
			//adding txtEventInformer to top_menu bar 
			top_menu.add(txtPlayer4);
			
		}
		
		//adding the top_menu bar to the game_screen
		game_screen.add(top_menu, BorderLayout.NORTH);
		
		initmap();
		game_screen.add(Map_Container, BorderLayout.CENTER);
		
	}
	
	public void UpdatePlayerInfo(String player, int point, int honey, int oil)
	{
		switch(player)
		{
			case "p1": {
				txtPlayer1.setText("    Score: " + point + "\n"
					+ "    Oil: " + oil + " Honey: " + honey);
			}break;
			case "p2": {
				txtPlayer2.setText("    Score: " + point + "\n"
						+ "    Oil: " + oil + " Honey: " + honey);
			}break;
			case "p3": {
				txtPlayer3.setText("    Score: " + point + "\n"
						+ "    Oil: " + oil + " Honey: " + honey);
			}break;
			case "p4": {
				txtPlayer4.setText("    Score: " + point + "\n"
						+ "    Oil: " + oil + " Honey: " + honey);
			}break;
		}
	}
	
	public void SetDraw(String pl)
	{
		switch(pl)
		{
			case "p1": Player1Show.setText("DRAW!");break;
			case "p2": Player2Show.setText("DRAW!");break;
			case "p3": Player3Show.setText("DRAW!");break;
			case "p4": Player4Show.setText("DRAW!");break;
		}
		
	}
	
	public void SetWin(String pl){
		
		switch(pl)
		{
			case "p1": Player1Show.setText("WINNER!");break;
			case "p2": Player2Show.setText("WINNER!");break;
			case "p3": Player3Show.setText("WINNER!");break;
			case "p4": Player4Show.setText("WINNER!");break;
		}
		
	}
	
	/**
	 * DrawGame receives notification that field has been changed
	 * Change the DrawField which belongs to the Field which has been sent
	 * 
	 * @param	o		Field that has been changed
	 * @param	x		Warehouse x position
	 * @param	y		Warehouse y position
	 * 
	 * @return 	void	
	 */
	public void RefreshDrawGame(Object o, int x, int y){
		
		//we need to cast back the Object to Field as the transmitter Draw class does not know the Field class
		Field f = (Field)o;
		
		if(f.GetThing() != null && f.GetSubstance() != null)
		{
			int pos = (draw.GetHeight()*x) + y;
			switch(draw.GetMapItem(f.GetThing()))
			{
				case("p1"): {
					switch(draw.GetMapItem(f.GetSubstance()))
					{
						case("so"): {
							switch(draw.GetMapItem(f))
							{
								case("ss"): Warehouse.get(pos).reSet(switchColor, IconPlayer1, IconOil, IconSwitch, IconFloor); break;
								case("c1"): Warehouse.get(pos).reSet(player1Color, IconPlayer1, IconOil, null, IconFloor); break;
								case("c2"): Warehouse.get(pos).reSet(player2Color, IconPlayer1, IconOil, null, IconFloor); break;
								case("c3"): Warehouse.get(pos).reSet(player3Color, IconPlayer1, IconOil, null, IconFloor); break;
								case("c4"): Warehouse.get(pos).reSet(player4Color, IconPlayer1, IconOil, null, IconFloor); break;
								default: Warehouse.get(pos).reSet(backgroundColor, IconPlayer1, IconOil, null, IconFloor); break;
							}
						} break;
						case("sh"): {
							switch(draw.GetMapItem(f))
							{
								case("ss"): Warehouse.get(pos).reSet(switchColor, IconPlayer1, IconHoney, IconSwitch, IconFloor); break;
								case("c1"): Warehouse.get(pos).reSet(player1Color, IconPlayer1, IconHoney, null, IconFloor); break;
								case("c2"): Warehouse.get(pos).reSet(player2Color, IconPlayer1, IconHoney, null, IconFloor); break;
								case("c3"): Warehouse.get(pos).reSet(player3Color, IconPlayer1, IconHoney, null, IconFloor); break;
								case("c4"): Warehouse.get(pos).reSet(player4Color, IconPlayer1, IconHoney, null, IconFloor); break;
								default: Warehouse.get(pos).reSet(backgroundColor, IconPlayer1, IconHoney, null, IconFloor); break;
							}
						} break;
					}
				} break;
				case("p2"): {
					switch(draw.GetMapItem(f.GetSubstance()))
					{
						case("so"): {
							switch(draw.GetMapItem(f))
							{
								case("ss"): Warehouse.get(pos).reSet(switchColor, IconPlayer2, IconOil, IconSwitch, IconFloor); break;
								case("c1"): Warehouse.get(pos).reSet(player1Color, IconPlayer2, IconOil, null, IconFloor); break;
								case("c2"): Warehouse.get(pos).reSet(player2Color, IconPlayer2, IconOil, null, IconFloor); break;
								case("c3"): Warehouse.get(pos).reSet(player3Color, IconPlayer2, IconOil, null, IconFloor); break;
								case("c4"): Warehouse.get(pos).reSet(player4Color, IconPlayer2, IconOil, null, IconFloor); break;
								default: Warehouse.get(pos).reSet(backgroundColor, IconPlayer2, IconOil, null, IconFloor); break;
							}
						} break;
						case("sh"): {
							switch(draw.GetMapItem(f))
							{
								case("ss"): Warehouse.get(pos).reSet(switchColor, IconPlayer2, IconHoney, IconSwitch, IconFloor); break;
								case("c1"): Warehouse.get(pos).reSet(player1Color, IconPlayer2, IconHoney, null, IconFloor); break;
								case("c2"): Warehouse.get(pos).reSet(player2Color, IconPlayer2, IconHoney, null, IconFloor); break;
								case("c3"): Warehouse.get(pos).reSet(player3Color, IconPlayer2, IconHoney, null, IconFloor); break;
								case("c4"): Warehouse.get(pos).reSet(player4Color, IconPlayer2, IconHoney, null, IconFloor); break;
								default: Warehouse.get(pos).reSet(backgroundColor, IconPlayer2, IconHoney, null, IconFloor); break;
							}
						} break;
					}
				} break;
				case("p3"): {
					switch(draw.GetMapItem(f.GetSubstance()))
					{
						case("so"): {
							switch(draw.GetMapItem(f))
							{
								case("ss"): Warehouse.get(pos).reSet(switchColor, IconPlayer3, IconOil, IconSwitch, IconFloor); break;
								case("c1"): Warehouse.get(pos).reSet(player1Color, IconPlayer3, IconOil, null, IconFloor); break;
								case("c2"): Warehouse.get(pos).reSet(player2Color, IconPlayer3, IconOil, null, IconFloor); break;
								case("c3"): Warehouse.get(pos).reSet(player3Color, IconPlayer3, IconOil, null, IconFloor); break;
								case("c4"): Warehouse.get(pos).reSet(player4Color, IconPlayer3, IconOil, null, IconFloor); break;
								default: Warehouse.get(pos).reSet(backgroundColor, IconPlayer3, IconOil, null, IconFloor); break;
							}
						} break;
						case("sh"): {
							switch(draw.GetMapItem(f))
							{
								case("ss"): Warehouse.get(pos).reSet(switchColor, IconPlayer3, IconHoney, IconSwitch, IconFloor); break;
								case("c1"): Warehouse.get(pos).reSet(player1Color, IconPlayer3, IconHoney, null, IconFloor); break;
								case("c2"): Warehouse.get(pos).reSet(player2Color, IconPlayer3, IconHoney, null, IconFloor); break;
								case("c3"): Warehouse.get(pos).reSet(player3Color, IconPlayer3, IconHoney, null, IconFloor); break;
								case("c4"): Warehouse.get(pos).reSet(player4Color, IconPlayer3, IconHoney, null, IconFloor); break;
								default: Warehouse.get(pos).reSet(backgroundColor, IconPlayer3, IconHoney, null, IconFloor); break;
							}
						} break;
					}
				} break;
				case("p4"): {
					switch(draw.GetMapItem(f.GetSubstance()))
					{
						case("so"): {
							switch(draw.GetMapItem(f))
							{
								case("ss"): Warehouse.get(pos).reSet(switchColor, IconPlayer4, IconOil, IconSwitch, IconFloor); break;
								case("c1"): Warehouse.get(pos).reSet(player1Color, IconPlayer4, IconOil, null, IconFloor); break;
								case("c2"): Warehouse.get(pos).reSet(player2Color, IconPlayer4, IconOil, null, IconFloor); break;
								case("c3"): Warehouse.get(pos).reSet(player3Color, IconPlayer4, IconOil, null, IconFloor); break;
								case("c4"): Warehouse.get(pos).reSet(player4Color, IconPlayer4, IconOil, null, IconFloor); break;
								default: Warehouse.get(pos).reSet(backgroundColor, IconPlayer4, IconOil, null, IconFloor); break;
							}
						} break;
						case("sh"): {
							switch(draw.GetMapItem(f))
							{
								case("ss"): Warehouse.get(pos).reSet(switchColor, IconPlayer4, IconHoney, IconSwitch, IconFloor); break;
								case("c1"): Warehouse.get(pos).reSet(player1Color, IconPlayer4, IconHoney, null, IconFloor); break;
								case("c2"): Warehouse.get(pos).reSet(player2Color, IconPlayer4, IconHoney, null, IconFloor); break;
								case("c3"): Warehouse.get(pos).reSet(player3Color, IconPlayer4, IconHoney, null, IconFloor); break;
								case("c4"): Warehouse.get(pos).reSet(player4Color, IconPlayer4, IconHoney, null, IconFloor); break;
								default: Warehouse.get(pos).reSet(backgroundColor, IconPlayer4, IconHoney, null, IconFloor); break;
							}
						} break;
					}
				} break;
				case("bb"):{
					switch(draw.GetMapItem(f.GetSubstance()))
					{
						case("so"): {
							switch(draw.GetMapItem(f))
							{
								case("ss"): Warehouse.get(pos).reSet(switchColor, IconBox, IconOil, IconSwitch, IconFloor); break;
								case("c1"): Warehouse.get(pos).reSet(player1Color, IconBox, IconOil, null, IconFloor); break;
								case("c2"): Warehouse.get(pos).reSet(player2Color, IconBox, IconOil, null, IconFloor); break;
								case("c3"): Warehouse.get(pos).reSet(player3Color, IconBox, IconOil, null, IconFloor); break;
								case("c4"): Warehouse.get(pos).reSet(player4Color, IconBox, IconOil, null, IconFloor); break;
								default: Warehouse.get(pos).reSet(backgroundColor, IconBox, IconOil, null, IconFloor); break;
							}
						} break;
						case("sh"): {
							switch(draw.GetMapItem(f))
							{
								case("ss"): Warehouse.get(pos).reSet(switchColor, IconBox, IconHoney, IconSwitch, IconFloor); break;
								case("c1"): Warehouse.get(pos).reSet(player1Color, IconBox, IconHoney, null, IconFloor); break;
								case("c2"): Warehouse.get(pos).reSet(player2Color, IconBox, IconHoney, null, IconFloor); break;
								case("c3"): Warehouse.get(pos).reSet(player3Color, IconBox, IconHoney, null, IconFloor); break;
								case("c4"): Warehouse.get(pos).reSet(player4Color, IconBox, IconHoney, null, IconFloor); break;
								default: Warehouse.get(pos).reSet(backgroundColor, IconBox, IconHoney, null, IconFloor); break;
							}
						} break;
					}
				} break;
			}
		}
		else if(f.GetThing() != null)
		{
			int pos = (draw.GetHeight()*x) + y;
			switch(draw.GetMapItem(f.GetThing()))
			{
				case("p1"): {
					switch(draw.GetMapItem(f))
					{
						case("ss"): Warehouse.get(pos).reSet(switchColor, IconPlayer1, null, IconSwitch, IconFloor); break;
						case("c1"): Warehouse.get(pos).reSet(player1Color, IconPlayer1, null, null, IconFloor); break;
						case("c2"): Warehouse.get(pos).reSet(player2Color, IconPlayer1, null, null, IconFloor); break;
						case("c3"): Warehouse.get(pos).reSet(player3Color, IconPlayer1, null, null, IconFloor); break;
						case("c4"): Warehouse.get(pos).reSet(player4Color, IconPlayer1, null, null, IconFloor); break;
						default: Warehouse.get(pos).reSet(backgroundColor, IconPlayer1, null, null, IconFloor); break;
					}
				} break;
				case("p2"): {
					switch(draw.GetMapItem(f))
					{
						case("ss"): Warehouse.get(pos).reSet(switchColor, IconPlayer2, null, IconSwitch, IconFloor); break;
						case("c1"): Warehouse.get(pos).reSet(player1Color, IconPlayer2, null, null, IconFloor); break;
						case("c2"): Warehouse.get(pos).reSet(player2Color, IconPlayer2, null, null, IconFloor); break;
						case("c3"): Warehouse.get(pos).reSet(player3Color, IconPlayer2, null, null, IconFloor); break;
						case("c4"): Warehouse.get(pos).reSet(player4Color, IconPlayer2, null, null, IconFloor); break;
						default: Warehouse.get(pos).reSet(backgroundColor, IconPlayer2, null, null, IconFloor); break;
					}
				} break;
				case("p3"): {
					switch(draw.GetMapItem(f))
					{
						case("ss"): Warehouse.get(pos).reSet(switchColor, IconPlayer3, null, IconSwitch, IconFloor); break;
						case("c1"): Warehouse.get(pos).reSet(player1Color, IconPlayer3, null, null, IconFloor); break;
						case("c2"): Warehouse.get(pos).reSet(player2Color, IconPlayer3, null, null, IconFloor); break;
						case("c3"): Warehouse.get(pos).reSet(player3Color, IconPlayer3, null, null, IconFloor); break;
						case("c4"): Warehouse.get(pos).reSet(player4Color, IconPlayer3, null, null, IconFloor); break;
						default: Warehouse.get(pos).reSet(backgroundColor, IconPlayer3, null, null, IconFloor); break;
					}
				} break;
				case("p4"): {
					switch(draw.GetMapItem(f))
					{
						case("ss"): Warehouse.get(pos).reSet(switchColor, IconPlayer4, null, IconSwitch, IconFloor); break;
						case("c1"): Warehouse.get(pos).reSet(player1Color, IconPlayer4, null, null, IconFloor); break;
						case("c2"): Warehouse.get(pos).reSet(player2Color, IconPlayer4, null, null, IconFloor); break;
						case("c3"): Warehouse.get(pos).reSet(player3Color, IconPlayer4, null, null, IconFloor); break;
						case("c4"): Warehouse.get(pos).reSet(player4Color, IconPlayer4, null, null, IconFloor); break;
						default: Warehouse.get(pos).reSet(backgroundColor, IconPlayer4, null, null, IconFloor); break;
					}
				} break;
				case("bb"):{
					switch(draw.GetMapItem(f))
					{
						case("ss"): Warehouse.get(pos).reSet(switchColor, IconBox, null, IconSwitch, IconFloor); break;
						case("c1"): Warehouse.get(pos).reSet(player1Color, IconBox, null, null, IconFloor); break;
						case("c2"): Warehouse.get(pos).reSet(player2Color, IconBox, null, null, IconFloor); break;
						case("c3"): Warehouse.get(pos).reSet(player3Color, IconBox, null, null, IconFloor); break;
						case("c4"): Warehouse.get(pos).reSet(player4Color, IconBox, null, null, IconFloor); break;
						default: Warehouse.get(pos).reSet(backgroundColor, IconBox, null, null, IconFloor); break;
					}
				} break;
			}
		}
		else if(f.GetSubstance() != null)
		{
			int pos = (draw.GetHeight()*x) + y;
			switch(draw.GetMapItem(f.GetSubstance()))
			{
				case("so"): {
					switch(draw.GetMapItem(f))
					{
						case("ss"): Warehouse.get(pos).reSet(switchColor, null, IconOil, IconSwitch, IconFloor); break;
						case("hh"): Warehouse.get(pos).reSet(backgroundColor, null, null, IconHole, IconFloor); break;
						case("c1"): Warehouse.get(pos).reSet(player1Color, null, IconOil, null, IconFloor); break;
						case("c2"): Warehouse.get(pos).reSet(player2Color, null, IconOil, null, IconFloor); break;
						case("c3"): Warehouse.get(pos).reSet(player3Color, null, IconOil, null, IconFloor); break;
						case("c4"): Warehouse.get(pos).reSet(player4Color, null, IconOil, null, IconFloor); break;
						default: Warehouse.get(pos).reSet(backgroundColor, null, IconOil, null, IconFloor); break;
					}
				} break;
				case("sh"): {
					switch(draw.GetMapItem(f))
					{
						case("ss"): Warehouse.get(pos).reSet(switchColor, null, IconHoney, IconSwitch, IconFloor); break;
						case("hh"): Warehouse.get(pos).reSet(backgroundColor, null, null, IconHole, IconFloor); break;
						case("c1"): Warehouse.get(pos).reSet(player1Color, null, IconHoney, null, IconFloor); break;
						case("c2"): Warehouse.get(pos).reSet(player2Color, null, IconHoney, null, IconFloor); break;
						case("c3"): Warehouse.get(pos).reSet(player3Color, null, IconHoney, null, IconFloor); break;
						case("c4"): Warehouse.get(pos).reSet(player4Color, null, IconHoney, null, IconFloor); break;
						default: Warehouse.get(pos).reSet(backgroundColor, null, IconHoney, null, IconFloor); break;
					}
				} break;
			}
		}
		else if(f != null)
		{
			int pos = (draw.GetHeight()*x) + y;
			switch(draw.GetMapItem(f))
			{
				case("ww"): Warehouse.get(pos).reSet(backgroundColor, null, null, IconWall, IconFloor); break;
				case("hh"): Warehouse.get(pos).reSet(backgroundColor, null, null, IconHole, IconFloor); break;
				case("ss"): Warehouse.get(pos).reSet(switchColor, null, null, IconSwitch, IconFloor); break;
				case("c1"): Warehouse.get(pos).reSet(player1Color, null, null, null, IconFloor); break;
				case("c2"): Warehouse.get(pos).reSet(player2Color, null, null, null, IconFloor); break;
				case("c3"): Warehouse.get(pos).reSet(player3Color, null, null, null, IconFloor); break;
				case("c4"): Warehouse.get(pos).reSet(player4Color, null, null, null, IconFloor); break;
				default: Warehouse.get(pos).reSet(backgroundColor, null, null, null, IconFloor); break;
			}
			
		}
		
	}
	
	/**
	 * Initialises the map at the beginning of a new game
	 * 
	 * @return 	void	
	 */
	public void initmap(){
		
				//set field ID to 0
				Field_ID = 0;
				//two-level loop to build up the fields
				for(int i = 0; i < draw.GetWidth(); i++) {
					
					for(int j = 0; j < draw.GetHeight(); j++) {
						
						//field ID increment
						Field_ID++;
						Warehouse.add(new DrawField("Field " + Integer.valueOf(Field_ID).toString(), Field_size, i, j, Map_Container, null, IconFloor, IconFloor, IconFloor, IconFloor));
						
					}
					
				}
				
				draw.WriteMap();
				
	}
	
}
