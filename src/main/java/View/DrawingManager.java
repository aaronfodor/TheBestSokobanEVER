package View;

import Controller.Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * DrawingManager is an object that manages drawings
 * 
 */
public class DrawingManager implements ActionListener{

	//the game controller object
	private Game game;
	
	//main frame
	public JFrame frame;
	
	//buttons -> we need them here as we want to keep their event listeners in this class to control display events from here
	private JButton buttonNewGame = new JButton();
	private JButton buttonExit = new JButton();
	private JButton buttonNextPlayerSelection = new JButton();
	private JButton buttonBackPlayerSelection = new JButton();
	private JButton buttonNextMapSelection = new JButton();
	private JButton buttonBackMapSelection = new JButton();
	private JButton buttonStartGame = new JButton();
	private JButton buttonBackMapProperties = new JButton();
	private JButton buttonGameExit = new JButton();
	private JButton buttonCredits = new JButton();
	private JButton buttonCreditsBack = new JButton();
	
	private JLabel menu_screen = new JLabel();
	private JLabel playerselection_screen = new JLabel();
	private JLabel mapselection_screen = new JLabel();
	private JLabel mapproperties_screen = new JLabel();
	private JPanel game_screen = new JPanel();
	private JLabel credits_screen = new JLabel();
	
	protected JComboBox<Integer> LoadPlayerNumber = new JComboBox<Integer>();
	protected JComboBox<String> LoadMap = new JComboBox<String>();
	protected JComboBox<String> LoadFloor = new JComboBox<String>();
	protected JComboBox<String> LoadWall = new JComboBox<String>();
	protected JComboBox<String> LoadBox = new JComboBox<String>();
	protected JLabel LoadedMapIcon = new JLabel();
	protected JLabel LoadedFloorIcon = new JLabel();
	protected JLabel LoadedWallIcon = new JLabel();
	protected JLabel LoadedBoxIcon = new JLabel();
	protected JTextArea textareaPlayersControl = new JTextArea();

	//screens to be displayed
	private DrawMainMenu MainMenu  = new DrawMainMenu(menu_screen, buttonNewGame, buttonCredits, buttonExit);
	private DrawPlayerSelection PlayerSelectionMenu = new DrawPlayerSelection(playerselection_screen, buttonNextPlayerSelection, buttonBackPlayerSelection, LoadPlayerNumber, textareaPlayersControl);
    private DrawMapSelection MapSelectionMenu = new DrawMapSelection(mapselection_screen, buttonNextMapSelection, buttonBackMapSelection, LoadMap, LoadedMapIcon);
    private DrawMapProperties MapPropertiesMenu = new DrawMapProperties(mapproperties_screen, buttonStartGame, buttonBackMapProperties, LoadFloor, LoadWall, LoadBox, LoadedFloorIcon, LoadedWallIcon, LoadedBoxIcon);
    private DrawGame Gameplay = new DrawGame(this, game_screen, buttonGameExit);
    private DrawCredits Credits = new DrawCredits(credits_screen, buttonCreditsBack);
	
    /**
     * DrawingManager constructor
     * 
     */
	public DrawingManager() {
		
		if(MainMenu != null && PlayerSelectionMenu != null && MapSelectionMenu != null && Gameplay != null && Credits != null) {
			
			System.out.println("GUI initialized.");
			
		}
		
    	//setting up title
        frame = new JFrame("The best Sokoban EVER");
        //to close window when clicking on exit
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setting up frame size & visibility
        frame.setSize(1000, 800);
        //frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //frame.setUndecorated(true); 
        frame.setResizable(false);
        //make the frame visible
        frame.setVisible(true);
        
        game_screen.setEnabled(false);
		
        //action listener pairings
		buttonGameExit.addActionListener(this);
		buttonNewGame.addActionListener(this);
		buttonExit.addActionListener(this);
		buttonStartGame.addActionListener(this);
		buttonNextPlayerSelection.addActionListener(this);
		buttonBackPlayerSelection.addActionListener(this);
		buttonNextMapSelection.addActionListener(this);
		buttonBackMapSelection.addActionListener(this);
		buttonBackMapProperties.addActionListener(this);
		buttonCredits.addActionListener(this);
		buttonCreditsBack.addActionListener(this);
		
		LoadPlayerNumber.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                    	
                        Integer selected_number = (Integer)LoadPlayerNumber.getSelectedItem();
                        
                        if(selected_number == 2) {
                        	
                        	textareaPlayersControl.setText(	"Key order: up, down, left, right, honey, oil\n\n" +
									"Player 1:        \u2191       \u2193       \u2190      \u2192      PgUp  PgDn\n" +
									"Player 2:       W       S       A       D       Q       E");
                        	
                        }
                        
                        else if(selected_number == 3) {
                        	
                        	textareaPlayersControl.setText(	"Key order: up, down, left, right, honey, oil\n\n" +
									"Player 1:        \u2191       \u2193       \u2190      \u2192      PgUp  PgDn\n" +
									"Player 2:       W       S       A       D       Q       E\n" +
									"Player 3:       T        G       F       H       R       Z");
                        	
                        }
                        
                        else if(selected_number == 4) {

                        	textareaPlayersControl.setText(	"Key order: up, down, left, right, honey, oil\n\n" +
									"Player 1:        \u2191       \u2193       \u2190      \u2192      PgUp  PgDn\n" +
									"Player 2:       W       S       A       D       Q       E\n" +
									"Player 3:       T        G       F       H       R       Z\n" +
									"Player 4:        I        K       J        L       U       O");

                        }
                        
                        else {
                        	
                        	textareaPlayersControl.setText(	"Player number has not been selected.");
                        	
                        }
                        
                        //refresh displayed things of JFrame
                   		frame.validate();
                        frame.repaint();
                        
                    }
                }            
        );
		
		LoadMap.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                    	
                        String text = (String)LoadMap.getSelectedItem();
                        LoadedMapIcon.setIcon(new ImageIcon("src/maps/" + text + " [" + LoadPlayerNumber.getSelectedItem().toString() + " players].png"));
                        
                        //refresh displayed things of JFrame
                   		frame.validate();
                        frame.repaint();
                        
                    }
                }            
        );
		
		LoadFloor.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                    	
            		LoadedFloorIcon.setIcon(new ImageIcon("src/visuals/floor/" + MapPropertiesMenu.GetFloorName().toLowerCase() + ".png"));
                    
            		//refresh displayed things of JFrame
               		frame.validate();
                    frame.repaint();
                    		
                    }
                }            
        );
		
		LoadWall.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                    	
            		LoadedWallIcon.setIcon(new ImageIcon("src/visuals/wall/" + MapPropertiesMenu.GetWallName().toLowerCase() + ".png"));
                    
            		//refresh displayed things of JFrame
               		frame.validate();
                    frame.repaint();
                    		
                    }
                }            
        );
		
		LoadBox.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                    	
            		LoadedBoxIcon.setIcon(new ImageIcon("src/visuals/box/box_" + MapPropertiesMenu.GetBoxName().toLowerCase() + ".png"));
                    
            		//refresh displayed things of JFrame
               		frame.validate();
                    frame.repaint();
                    		
                    }
                }            
        );
        
        //draw menu screen
        frame.setContentPane(menu_screen);
        
        MapPropertiesMenu.LoadFloorAdd();
        MapPropertiesMenu.LoadWallAdd();
        MapPropertiesMenu.LoadBoxAdd();
        
	}
	
	/**
	 * Game notifies DrawGame that field has been changed, transmitter method -> calls DrawGame method
	 * Draw passes the received Field to DrawGame class
	 * 
	 * @param	f		Field that has been changed
	 * @param	x		Warehouse array x position
	 * @param	y		Warehouse array y position
	 */
	public void RefreshDraw(Object f, int x, int y){
		
		Gameplay.RefreshDrawGame(f, x, y);
		
	}
	
	/** 
	 * transmitter method -> calls DrawGame method
	 */
	public void UpdatePlayerInfo(String pl, int points, int honey, int oil){
		
		Gameplay.UpdatePlayerInfo(pl, points, honey, oil);
		
	}
	
	/** 
	 * transmitter method -> calls DrawGame method
	 */
	public void SetWin(String pl){
		
		Gameplay.SetWin(pl);
		
	}
	
	/** 
	 * transmitter method -> calls DrawGame method
	 */
	public void SetDraw(String pl){
		
		Gameplay.SetDraw(pl);
		
	}
	
	/** 
	 * transmitter method -> calls Game method
	 */
	public int GetHeight(){
		
		return game.GetHeight();
		
	}
	
	/** 
	 * transmitter method -> calls Game method
	 */
	public int GetWidth(){
		
		return game.GetWidth();
		
	}
	
	/** 
	 * transmitter method -> calls Game method
	 */
	public void WriteMap(){
		
		game.WriteMap();
		
	}
	
	/** 
	 * transmitter method -> calls Game method
	 */
	public String GetMapItem(Object o){
		
		return game.GetMapItem(o);
		
	}

	/**
	 * event handler method
	 */
    public void actionPerformed(ActionEvent ae) {
    	
    	//temporary button to model the button which got action and needs to handle it
        JButton button = (JButton) ae.getSource();

        //if buttonGameExit clicked
        if (button == buttonGameExit) {
    	   	
    	    frame.removeKeyListener(game);
   			frame.remove(game_screen);
   			frame.setContentPane(menu_screen);
   			System.out.println("[Gameplay closed]");
           
       	}
       
       	//if buttonNewGame clicked
      	else if (button == buttonNewGame) {
           frame.remove(menu_screen);
    	   frame.setContentPane(playerselection_screen);
           
       	}
       
       	//if buttonStartGame clicked
       	else if (button == buttonStartGame) {   
    	   
    	   frame.remove(mapselection_screen);
    	   try {   
        	   game = new Game(this);
    		   game.ReadMap("src/maps/" + MapSelectionMenu.GetMapName() + " [" + LoadPlayerNumber.getSelectedItem().toString() + " players].txt");
    		   frame.addKeyListener(game);
    		   game_screen.addKeyListener(game);
    		   frame.setFocusable(true);
    		   
    	   } catch (IOException e) {
    		   e.printStackTrace();
    	   }
    	   
    	   Gameplay.StartGame(PlayerSelectionMenu.GetPlayerNum(), MapSelectionMenu.GetMapID(),
    			   //loading the selected floor, wall, box icons
    			   new ImageIcon("src/visuals/floor/" + MapPropertiesMenu.GetFloorName().toLowerCase() + ".png"),
    			   new ImageIcon("src/visuals/wall/" + MapPropertiesMenu.GetWallName().toLowerCase() + ".png"),
    			   new ImageIcon("src/visuals/box/box_" + MapPropertiesMenu.GetBoxName().toLowerCase() + ".png")
    			   );
    	   frame.setContentPane(game_screen);
    	   
       	}
       
       	//if buttonBackPlayerSelection clicked
       	else if (button == buttonBackPlayerSelection) {   
    	   
    	   	frame.remove(playerselection_screen);
    	   	frame.setContentPane(menu_screen);
    	   
       	}
       
       	//if buttonBackMapSelection clicked
       	else if (button == buttonBackMapSelection) {   
    	   
    	   	frame.remove(mapselection_screen);
    	   	frame.setContentPane(playerselection_screen);
    	   
       	}
       
       	//if buttonNextPlayerSelection clicked
       	else if (button == buttonNextPlayerSelection) {   
    	   
    	   	//we need to remove previously loaded items from the map selection list
    	   	MapSelectionMenu.LoadMapReset();
    	   	MapSelectionMenu.LoadMapAdd(PlayerSelectionMenu.GetPlayerNum());
    	   	frame.remove(playerselection_screen);
    	   	frame.setContentPane(mapselection_screen);
    	   	
       	}
       
       	//if buttonExit clicked
       	else if (button  == buttonExit) {
    	   
       		//this will remove the main_screen and closes the Frame, exit
       		frame.remove(menu_screen);
       		frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        }
       
       	//if buttonCredits clicked
       	else if (button == buttonCredits) {   
    	   
       		frame.remove(menu_screen);
       		frame.setContentPane(credits_screen);
    	   
       	}
           
       //if buttonCreditsBack clicked
       else if (button == buttonCreditsBack) {   
    	   
    	   	frame.remove(credits_screen);
    	   	frame.setContentPane(menu_screen);
    	   
       	}
        
      //if buttonBackMapProperties clicked
       else if (button == buttonBackMapProperties) {   
    	   
    	   	frame.remove(mapproperties_screen);
    	   	frame.setContentPane(mapselection_screen);
    	   
       	}
        
      //if buttonCreditsBack clicked
       else if (button == buttonNextMapSelection) {   
    	   
    	   	frame.remove(mapselection_screen);
    	   	frame.setContentPane(mapproperties_screen);
    	   
       	}
           
        //refresh displayed things of JFrame
   		frame.validate();
        frame.repaint();
           
    }
    
}
