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
 * DrawMapSelection draws the map selection screen onto a label.
 * @author Arpad Fodor
 *
 */
public class DrawMapSelection {

	//map selection screen elements
	protected JLabel mapselection_screen;
	protected Container containerMapButtons;
	protected Container containerMapText;
	protected JButton buttonNext;
	protected JButton buttonBack;
	protected JTextField textareaMapSelectText;
	protected JTextField textareaSelectMap;
	protected JComboBox<String> LoadMap;
	protected JLabel LoadedMapIcon;
	
	public DrawMapSelection(JLabel mapselection_screen, JButton buttonNext, JButton buttonBack, JComboBox<String> LoadMap, JLabel LoadedMapIcon) {
		
		this.mapselection_screen = mapselection_screen;
		this.LoadMap = LoadMap;
		this.LoadedMapIcon = LoadedMapIcon;
		
		//we need these buttons from the Draw object as it holds button listeners
		this.buttonNext = buttonNext;
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
		gbl_Start_Container.columnWidths = new int[] {};
		gbl_Start_Container.rowHeights = new int[] {0, 0, 50, 300, 50, 0, 50, 0};
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
		
		textareaMapSelectText = new JTextField();
		textareaMapSelectText.setText("Map selection");
		//setting the font
		textareaMapSelectText.setFont(new Font("Tahoma", Font.PLAIN, 50));
		//setting textareaStartText transparent
		textareaMapSelectText.setOpaque(false);
		//setting foreground to black
		textareaMapSelectText.setForeground(Color.white);
		//setting non-editable
		textareaMapSelectText.setEditable(false);
		//setting no border
		textareaMapSelectText.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		GridBagConstraints gbc_textareaMapSelectText = new GridBagConstraints();
		gbc_textareaMapSelectText.insets = new Insets(0, 0, 5, 5);
		gbc_textareaMapSelectText.gridx = 0;
		gbc_textareaMapSelectText.gridy = 0;
		containerMapText.add(textareaMapSelectText, gbc_textareaMapSelectText);
		
		textareaSelectMap = new JTextField();
		textareaSelectMap.setText("Select map");
		//setting the font
		textareaSelectMap.setFont(new Font("Tahoma", Font.PLAIN, 16));
		//setting textareaSelectMap transparent
		textareaSelectMap.setOpaque(false);
		//setting foreground to black
		textareaSelectMap.setForeground(Color.white);
		//setting non-editable
		textareaSelectMap.setEditable(false);
		//setting no border
		textareaSelectMap.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		GridBagConstraints gbc_textareaSelectMap = new GridBagConstraints();
		gbc_textareaSelectMap.insets = new Insets(0, 0, 5, 5);
		gbc_textareaSelectMap.gridx = 0;
		gbc_textareaSelectMap.gridy = 0;
		containerMapButtons.add(textareaSelectMap, gbc_textareaSelectMap);
		
        //LoadMap creation, setting text, setting desired font style & colour, setting field only readable, setting not visible border
		LoadMap.setFont(new Font("Tahoma", Font.PLAIN, 16));
		LoadMap.setOpaque(true);
		LoadMap.setForeground(Color.BLACK);
		LoadMap.setEditable(false);
		LoadMap.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		LoadMap.setPreferredSize(new Dimension(200, 30));
		GridBagConstraints gbc_LoadMap = new GridBagConstraints();
		gbc_LoadMap.insets = new Insets(0, 0, 5, 5);
		gbc_LoadMap.gridx = 0;
		gbc_LoadMap.gridy = 1;
		containerMapButtons.add(LoadMap, gbc_LoadMap);
		
		//LoadedMapIcon creation
		LoadedMapIcon.setOpaque(false);
		GridBagConstraints gbc_LoadedMapIcon = new GridBagConstraints();
		gbc_LoadedMapIcon.insets = new Insets(0, 0, 5, 5);
		gbc_LoadedMapIcon.gridx = 0;
		gbc_LoadedMapIcon.gridy = 3;
		containerMapButtons.add(LoadedMapIcon, gbc_LoadedMapIcon);
		
		buttonNext.setText("Next");
		buttonNext.setFont(new Font("Tahoma", Font.PLAIN, 20));
		buttonNext.setPreferredSize(new Dimension(300, 50));
		GridBagConstraints gbc_buttonStartGame = new GridBagConstraints();
		gbc_buttonStartGame.insets = new Insets(0, 0, 5, 5);
		gbc_buttonStartGame.gridx = 0;
		gbc_buttonStartGame.gridy = 5;
		containerMapButtons.add(buttonNext, gbc_buttonStartGame);
		
		buttonBack.setText("Back");
		buttonBack.setFont(new Font("Tahoma", Font.PLAIN, 20));
		buttonBack.setPreferredSize(new Dimension(300, 50));
		GridBagConstraints gbc_buttonBack = new GridBagConstraints();
		gbc_buttonBack.insets = new Insets(0, 0, 5, 5);
		gbc_buttonBack.gridx = 0;
		gbc_buttonBack.gridy = 7;
		containerMapButtons.add(buttonBack, gbc_buttonBack);
		
		mapselection_screen.add(containerMapText, BorderLayout.NORTH);
		mapselection_screen.add(containerMapButtons, BorderLayout.CENTER);
		
	}
	
	//to empty the map list easily
	public void LoadMapReset(){
		
		int itemCount = LoadMap.getItemCount();

	    for(int i = 0; i < itemCount; i++){
	    	
	    	LoadMap.removeItemAt(0);
	    	
	     }
		
	}
	
	public void LoadMapAdd(int j){
		
		if(j == 2){
			
			LoadMap.addItem("Warehouse (hazardous)");
			LoadMap.addItem("Warehouse (crowded)");
			LoadMap.addItem("Warehouse (columned)");
			LoadMap.addItem("Warehouse (labyrinth)");
			LoadMap.addItem("Storehouse (huge)");
			LoadMap.addItem("Storehouse (medium)");
			LoadMap.addItem("Storehouse (small)");
			LoadMap.addItem("Stockroom (huge)");
			LoadMap.addItem("Stockroom (small)");
			LoadMap.addItem("Storeroom (huge)");
			LoadMap.addItem("Storeroom (small)");
			LoadMap.addItem("Raging");
			
		}
		
		else if(j == 3){
			
			LoadMap.addItem("Warehouse (hazardous)");
			LoadMap.addItem("Warehouse (crowded)");
			LoadMap.addItem("Warehouse (columned)");
			LoadMap.addItem("Warehouse (labyrinth)");
			LoadMap.addItem("Storehouse (huge)");
			LoadMap.addItem("Storehouse (medium)");
			LoadMap.addItem("Storehouse (small)");
			LoadMap.addItem("Stockroom (huge)");
			LoadMap.addItem("Stockroom (small)");
			LoadMap.addItem("Storeroom (huge)");
			LoadMap.addItem("Storeroom (small)");
			LoadMap.addItem("Raging");
		    
		}
		
		else if(j == 4){
			
			LoadMap.addItem("Warehouse (hazardous)");
			LoadMap.addItem("Warehouse (crowded)");
			LoadMap.addItem("Warehouse (columned)");
			LoadMap.addItem("Warehouse (labyrinth)");
			LoadMap.addItem("Storehouse (huge)");
			LoadMap.addItem("Storehouse (medium)");
			LoadMap.addItem("Storehouse (small)");
			LoadMap.addItem("Stockroom (huge)");
			LoadMap.addItem("Stockroom (small)");
			LoadMap.addItem("Storeroom (huge)");
			LoadMap.addItem("Storeroom (small)");
			LoadMap.addItem("Raging");
		    
		}
	}
	
	public Integer GetMapID() {
		
		return LoadMap.getSelectedIndex();
		
	}
	
	public String GetMapName(){
		
		return (String)LoadMap.getSelectedItem();
		
	}
	
}
