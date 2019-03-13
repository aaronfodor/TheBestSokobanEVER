package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import java.awt.GridBagConstraints;
import java.awt.Insets;

/**
 * DrawMainMenu draws the main menu screen onto a label.
 */
public class DrawMainMenu {

	//menu screen elements
	protected JLabel menu_screen;
	protected Container containerMenuButtons;
	protected Container containerMenuText;
	protected JButton buttonNewGame;
	protected JButton buttonCredits;
	protected JButton buttonExit;
	protected JTextField textareaMenuText;
	
	public DrawMainMenu(JLabel menu_screen, JButton buttonNewGame, JButton buttonCredits, JButton buttonExit) {
		
		this.menu_screen = menu_screen;
		
		//we need these buttons from the Draw object as it holds button listeners
		this.buttonNewGame = buttonNewGame;
		this.buttonExit = buttonExit;
		
		ImageIcon Wallpaper = new ImageIcon("src/visuals/background.jpg");
		
		//menu screen setting-up
		menu_screen.setIcon(Wallpaper);
		menu_screen.setBackground(UIManager.getColor("TextPane.selectionBackground"));
		menu_screen.setLayout(new BorderLayout(0, 0));
		
		//containerMenuButtons setting-up
		containerMenuButtons = new Container();
		//containerMenuText setting-up
		containerMenuText = new Container();
		
		//GridBagLayout building-up
		GridBagLayout gbl_Menu_Container = new GridBagLayout();
		gbl_Menu_Container.columnWidths = new int[] {};
		gbl_Menu_Container.rowHeights = new int[] {0, 100, 0, 100, 0};
		gbl_Menu_Container.columnWeights = new double[]{};
		gbl_Menu_Container.rowWeights = new double[]{0.0, 0.0, 0.0};
		//setting containerMenuButtons layout to GridBagLayout
		containerMenuButtons.setLayout(gbl_Menu_Container);
		
		//GridBagLayout building-up
		GridBagLayout Text_Container = new GridBagLayout();
		Text_Container.columnWidths = new int[] {};
		Text_Container.rowHeights = new int[] {0};
		Text_Container.columnWeights = new double[]{};
		Text_Container.rowWeights = new double[]{0.0, 0.0, 0.0};
		//setting containerMenuText layout to GridBagLayout
		containerMenuText.setLayout(Text_Container);
		
		textareaMenuText = new JTextField();
		textareaMenuText.setText("Main Menu");
		//setting the font
		textareaMenuText.setFont(new Font("Tahoma", Font.PLAIN, 50));
		//setting txtPlayer1 transparent
		textareaMenuText.setOpaque(false);
		//setting foreground to black
		textareaMenuText.setForeground(Color.white);
		//setting non-editable
		textareaMenuText.setEditable(false);
		//setting no border
		textareaMenuText.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		GridBagConstraints gbc_textareaMenuText = new GridBagConstraints();
		gbc_textareaMenuText.insets = new Insets(0, 0, 5, 5);
		gbc_textareaMenuText.gridx = 0;
		gbc_textareaMenuText.gridy = 0;
		containerMenuText.add(textareaMenuText, gbc_textareaMenuText);
		
		buttonNewGame.setText("New Game");
		buttonNewGame.setFont(new Font("Tahoma", Font.PLAIN, 20));
		buttonNewGame.setPreferredSize(new Dimension(300, 50));
		GridBagConstraints gbc_buttonNewGame = new GridBagConstraints();
		gbc_buttonNewGame.insets = new Insets(0, 0, 5, 5);
		gbc_buttonNewGame.gridx = 0;
		gbc_buttonNewGame.gridy = 0;
		containerMenuButtons.add(buttonNewGame, gbc_buttonNewGame);
		
		buttonCredits.setText("Credits");
		buttonCredits.setFont(new Font("Tahoma", Font.PLAIN, 20));
		buttonCredits.setPreferredSize(new Dimension(300, 50));
		GridBagConstraints gbc_buttonCredits = new GridBagConstraints();
		gbc_buttonCredits.insets = new Insets(0, 0, 0, 5);
		gbc_buttonCredits.gridx = 0;
		gbc_buttonCredits.gridy = 2;
		containerMenuButtons.add(buttonCredits, gbc_buttonCredits);
		
		buttonExit.setText("Exit");
		buttonExit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		buttonExit.setPreferredSize(new Dimension(300, 50));
		GridBagConstraints gbc_buttonExit = new GridBagConstraints();
		gbc_buttonExit.insets = new Insets(0, 0, 0, 5);
		gbc_buttonExit.gridx = 0;
		gbc_buttonExit.gridy = 4;
		containerMenuButtons.add(buttonExit, gbc_buttonExit);
		
		menu_screen.add(containerMenuText, BorderLayout.NORTH);
		menu_screen.add(containerMenuButtons, BorderLayout.CENTER);
		
	}
	
}
