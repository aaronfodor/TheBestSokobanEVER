package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;

/**
 * DrawMapProperties draws the map selection screen onto a label.
 * @author Arpad Fodor
 *
 */
public class DrawMapProperties {

	//map selection screen elements
	protected JLabel mapselection_screen;
	protected Container containerMapButtons;
	protected Container containerMapText;
	protected JButton buttonStartGame;
	protected JButton buttonBack;
	protected JTextField textareaMapPropertyText;
	protected JTextField textareaSelectFloor;
	protected JTextField textareaSelectWall;
	protected JTextField textareaSelectBox;
	protected JComboBox<String> LoadFloor;
	protected JLabel LoadedFloorIcon;
	protected JComboBox<String> LoadWall;
	protected JLabel LoadedWallIcon;
	protected JComboBox<String> LoadBox;
	protected JLabel LoadedBoxIcon;
	
	public DrawMapProperties(JLabel mapselection_screen, JButton buttonStartGame, JButton buttonBack, JComboBox<String> LoadFloor, JComboBox<String> LoadWall, JComboBox<String> LoadBox, JLabel LoadedFloorIcon, JLabel LoadedWallIcon, JLabel LoadedBoxIcon) {
		
		this.mapselection_screen = mapselection_screen;
		this.LoadFloor = LoadFloor;
		this.LoadedFloorIcon = LoadedFloorIcon;
		this.LoadWall = LoadWall;
		this.LoadedWallIcon = LoadedWallIcon;
		this.LoadBox = LoadBox;
		this.LoadedBoxIcon = LoadedBoxIcon;
		
		//we need these buttons from the Draw object as it holds button listeners
		this.buttonStartGame = buttonStartGame;
		this.buttonBack = buttonBack;
		
		ImageIcon Wallpaper = new ImageIcon("src/visuals/background.jpg");
		
		//start screen setting-up
		this.mapselection_screen.setIcon(Wallpaper);
		mapselection_screen.setBackground(UIManager.getColor("TextPane.selectionBackground"));
		mapselection_screen.setLayout(new BorderLayout(0, 0));
		
		//containerStartButtons setting-up
		containerMapButtons = new Container();
		//containerStartText setting-up
		containerMapText = new Container();
		
		//GridBagLayout building-up
		GridBagLayout gbl_Start_Container = new GridBagLayout();
		gbl_Start_Container.columnWidths = new int[] {200, 30, 200, 30, 200};
		//{200, 0, 200};
		gbl_Start_Container.rowHeights = new int[] {0, 0, 50, 305, 50, 0, 50, 0};
		gbl_Start_Container.columnWeights = new double[]{};
		gbl_Start_Container.rowWeights = new double[]{0.0, 0.0, 0.0};
		//setting containerStartButtons layout to GridBagLayout
		containerMapButtons.setLayout(gbl_Start_Container);
		
		//GridBagLayout building-up
		GridBagLayout Text_Container = new GridBagLayout();
		Text_Container.columnWidths = new int[] {};
		Text_Container.rowHeights = new int[] {0};
		Text_Container.columnWeights = new double[]{};
		Text_Container.rowWeights = new double[]{0.0, 0.0, 0.0};
		//setting containerStartText layout to GridBagLayout
		containerMapText.setLayout(Text_Container);
		
		textareaMapPropertyText = new JTextField();
		textareaMapPropertyText.setText("Map properties");
		//setting the font
		textareaMapPropertyText.setFont(new Font("Tahoma", Font.PLAIN, 50));
		//setting textareaMapPropertyText transparent
		textareaMapPropertyText.setOpaque(false);
		//setting foreground to black
		textareaMapPropertyText.setForeground(Color.white);
		//setting non-editable
		textareaMapPropertyText.setEditable(false);
		//setting no border
		textareaMapPropertyText.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		GridBagConstraints gbc_textareaMapPropertyText = new GridBagConstraints();
		gbc_textareaMapPropertyText.insets = new Insets(0, 0, 5, 5);
		gbc_textareaMapPropertyText.gridx = 0;
		gbc_textareaMapPropertyText.gridy = 0;
		containerMapText.add(textareaMapPropertyText, gbc_textareaMapPropertyText);
		
		textareaSelectFloor = new JTextField();
		textareaSelectFloor.setText("Select floor");
		//setting the font
		textareaSelectFloor.setFont(new Font("Tahoma", Font.PLAIN, 16));
		//setting textareaSelectMap transparent
		textareaSelectFloor.setOpaque(false);
		//setting foreground to black
		textareaSelectFloor.setForeground(Color.white);
		//setting non-editable
		textareaSelectFloor.setEditable(false);
		//setting no border
		textareaSelectFloor.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		GridBagConstraints gbc_textareaSelectFloor = new GridBagConstraints();
		gbc_textareaSelectFloor.insets = new Insets(0, 0, 5, 5);
		gbc_textareaSelectFloor.gridx = 0;
		gbc_textareaSelectFloor.gridy = 0;
		containerMapButtons.add(textareaSelectFloor, gbc_textareaSelectFloor);
		
        //LoadFloor creation, setting text, setting desired font style & colour, setting field only readable, setting not visible border
		LoadFloor.setFont(new Font("Tahoma", Font.PLAIN, 16));
		LoadFloor.setOpaque(true);
		LoadFloor.setForeground(Color.BLACK);
		LoadFloor.setEditable(false);
		LoadFloor.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		LoadFloor.setPreferredSize(new Dimension(150, 30));
		GridBagConstraints gbc_LoadFloor = new GridBagConstraints();
		gbc_LoadFloor.insets = new Insets(0, 0, 5, 5);
		gbc_LoadFloor.gridx = 0;
		gbc_LoadFloor.gridy = 1;
		containerMapButtons.add(LoadFloor, gbc_LoadFloor);
		
		//LoadedFloorIcon creation
		LoadedFloorIcon.setOpaque(false);
		GridBagConstraints gbc_LoadedFloorIcon = new GridBagConstraints();
		gbc_LoadedFloorIcon.insets = new Insets(0, 0, 5, 5);
		gbc_LoadedFloorIcon.gridx = 0;
		gbc_LoadedFloorIcon.gridy = 3;
		containerMapButtons.add(LoadedFloorIcon, gbc_LoadedFloorIcon);
		
		textareaSelectWall = new JTextField();
		textareaSelectWall.setText("Select wall");
		//setting the font
		textareaSelectWall.setFont(new Font("Tahoma", Font.PLAIN, 16));
		//setting textareaSelectMap transparent
		textareaSelectWall.setOpaque(false);
		//setting foreground to black
		textareaSelectWall.setForeground(Color.white);
		//setting non-editable
		textareaSelectWall.setEditable(false);
		//setting no border
		textareaSelectWall.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		GridBagConstraints gbc_textareaSelectWall = new GridBagConstraints();
		gbc_textareaSelectWall.insets = new Insets(0, 0, 5, 5);
		gbc_textareaSelectWall.gridx = 4;
		gbc_textareaSelectWall.gridy = 0;
		containerMapButtons.add(textareaSelectWall, gbc_textareaSelectWall);
		
        //LoadWall creation, setting text, setting desired font style & colour, setting field only readable, setting not visible border
		LoadWall.setFont(new Font("Tahoma", Font.PLAIN, 16));
		LoadWall.setOpaque(true);
		LoadWall.setForeground(Color.BLACK);
		LoadWall.setEditable(false);
		LoadWall.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		LoadWall.setPreferredSize(new Dimension(90, 30));
		GridBagConstraints gbc_LoadWall = new GridBagConstraints();
		gbc_LoadWall.insets = new Insets(0, 0, 5, 5);
		gbc_LoadWall.gridx = 4;
		gbc_LoadWall.gridy = 1;
		containerMapButtons.add(LoadWall, gbc_LoadWall);
		
		//LoadedWallIcon creation
		LoadedWallIcon.setOpaque(false);
		GridBagConstraints gbc_LoadedWallIcon = new GridBagConstraints();
		gbc_LoadedWallIcon.insets = new Insets(0, 0, 5, 5);
		gbc_LoadedWallIcon.gridx = 4;
		gbc_LoadedWallIcon.gridy = 3;
		containerMapButtons.add(LoadedWallIcon, gbc_LoadedWallIcon);
		
		textareaSelectBox = new JTextField();
		textareaSelectBox.setText("Select box");
		//setting the font
		textareaSelectBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		//setting textareaSelectBox transparent
		textareaSelectBox.setOpaque(false);
		//setting foreground to black
		textareaSelectBox.setForeground(Color.white);
		//setting non-editable
		textareaSelectBox.setEditable(false);
		//setting no border
		textareaSelectBox.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		GridBagConstraints gbc_textareaSelectBox = new GridBagConstraints();
		gbc_textareaSelectBox.insets = new Insets(0, 0, 5, 5);
		gbc_textareaSelectBox.gridx = 2;
		gbc_textareaSelectBox.gridy = 0;
		containerMapButtons.add(textareaSelectBox, gbc_textareaSelectBox);
		
        //LoadBox creation, setting text, setting desired font style & colour, setting field only readable, setting not visible border
		LoadBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		LoadBox.setOpaque(true);
		LoadBox.setForeground(Color.BLACK);
		LoadBox.setEditable(false);
		LoadBox.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		LoadBox.setPreferredSize(new Dimension(120, 30));
		GridBagConstraints gbc_LoadBox = new GridBagConstraints();
		gbc_LoadBox.insets = new Insets(0, 0, 5, 5);
		gbc_LoadBox.gridx = 2;
		gbc_LoadBox.gridy = 1;
		containerMapButtons.add(LoadBox, gbc_LoadBox);
		
		//LoadedBoxIcon creation
		LoadedBoxIcon.setOpaque(false);
		GridBagConstraints gbc_LoadedBoxIcon = new GridBagConstraints();
		gbc_LoadedBoxIcon.insets = new Insets(0, 0, 5, 5);
		gbc_LoadedBoxIcon.gridx = 2;
		gbc_LoadedBoxIcon.gridy = 3;
		containerMapButtons.add(LoadedBoxIcon, gbc_LoadedBoxIcon);
		
		buttonStartGame.setText("Start Game");
		buttonStartGame.setFont(new Font("Tahoma", Font.PLAIN, 20));
		buttonStartGame.setPreferredSize(new Dimension(300, 50));
		GridBagConstraints gbc_buttonStartGame = new GridBagConstraints();
		gbc_buttonStartGame.insets = new Insets(0, 0, 5, 5);
		gbc_buttonStartGame.gridx = 2;
		gbc_buttonStartGame.gridy = 5;
		containerMapButtons.add(buttonStartGame, gbc_buttonStartGame);
		
		buttonBack.setText("Back");
		buttonBack.setFont(new Font("Tahoma", Font.PLAIN, 20));
		buttonBack.setPreferredSize(new Dimension(300, 50));
		GridBagConstraints gbc_buttonBack = new GridBagConstraints();
		gbc_buttonBack.insets = new Insets(0, 0, 5, 5);
		gbc_buttonBack.gridx = 2;
		gbc_buttonBack.gridy = 7;
		containerMapButtons.add(buttonBack, gbc_buttonBack);
		
		mapselection_screen.add(containerMapText, BorderLayout.NORTH);
		mapselection_screen.add(containerMapButtons, BorderLayout.CENTER);
		
	}
	
	//to empty the floor list easily
	public void LoadFloorReset(){
		
		int itemCount = LoadFloor.getItemCount();

	    for(int i = 0; i < itemCount; i++){
	    	
	    	LoadFloor.removeItemAt(0);
	    	
	     }
		
	}
	
	//to empty the box list easily
	public void LoadWallReset(){
		
		int itemCount = LoadWall.getItemCount();

	    for(int i = 0; i < itemCount; i++){
	    	
	    	LoadWall.removeItemAt(0);
	    	
	     }
		
	}
	
	//to empty the box list easily
	public void LoadBoxReset(){
		
		int itemCount = LoadBox.getItemCount();

	    for(int i = 0; i < itemCount; i++){
	    	
	    	LoadBox.removeItemAt(0);
	    	
	     }
		
	}
	
	public void LoadFloorAdd(){
		
		LoadFloor.addItem("Asphalt");
		LoadFloor.addItem("Concrete");
		LoadFloor.addItem("Grass");
		LoadFloor.addItem("Metal");
		LoadFloor.addItem("Mosaic");
		LoadFloor.addItem("Wood");
		
	}
	
	public void LoadWallAdd(){
		
		LoadWall.addItem("Grey");
		LoadWall.addItem("Brown");
		
	}
	
	public void LoadBoxAdd(){
		
		LoadBox.addItem("Normal");
		LoadBox.addItem("Almdudler");
		LoadBox.addItem("Bank");
		LoadBox.addItem("Biohazard");
		LoadBox.addItem("Coke");
		LoadBox.addItem("H&M");
		LoadBox.addItem("IKEA");
		LoadBox.addItem("JYSK");
		LoadBox.addItem("Kofola");
		LoadBox.addItem("Manner");
		LoadBox.addItem("Marlenka");
		LoadBox.addItem("Nutella");
		LoadBox.addItem("OBI");
		LoadBox.addItem("Oreo");
		LoadBox.addItem("Present");
		LoadBox.addItem("Rotax");
		LoadBox.addItem("Scitec");
		
	}
	
	public String GetFloorName(){

		return (String)LoadFloor.getSelectedItem();
		
	}
	
	public String GetWallName(){

		return (String)LoadWall.getSelectedItem();
		
	}
	
	public String GetBoxName(){

		return (String)LoadBox.getSelectedItem();
		
	}
	
}

