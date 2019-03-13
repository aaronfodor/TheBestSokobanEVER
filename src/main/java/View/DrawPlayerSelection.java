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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

/**
 * DrawPlayerSelection draws the player selection screen onto a label.
 * @author Arpad Fodor
 *
 */
public class DrawPlayerSelection {

	//map selection screen elements
		protected JLabel playerselection_screen;
		protected Container containerPlayerButtons;
		protected Container containerPlayerText;
		protected JButton buttonNext;
		protected JButton buttonBackToPalyers;
		protected JTextField textareaStartText;
		protected JTextField textareaSelectPlayers;
		protected JTextArea textareaPlayersControl;
		protected JComboBox<Integer> LoadPlayerNumber;
		
		public DrawPlayerSelection(JLabel playerselection_screen, JButton buttonNext, JButton buttonBackToPalyers, JComboBox<Integer> LoadPlayerNumber, JTextArea textareaPlayersControl) {
			
			this.playerselection_screen = playerselection_screen;
			this.LoadPlayerNumber = LoadPlayerNumber;
			this.textareaPlayersControl = textareaPlayersControl;
			
			//we need these buttons from the Draw object as it holds button listeners
			this.buttonNext = buttonNext;
			this.buttonBackToPalyers = buttonBackToPalyers;
			
			ImageIcon Wallpaper = new ImageIcon("src/visuals/background.jpg");
			
			//start screen setting-up
			this.playerselection_screen.setIcon(Wallpaper);
			playerselection_screen.setBackground(UIManager.getColor("TextPane.selectionBackground"));
			playerselection_screen.setLayout(new BorderLayout(0, 0));
			
			//containerStartButtons setting-up
			containerPlayerButtons = new Container();
			//containerStartText setting-up
			containerPlayerText = new Container();
			
			//GridBagLayout building-up
			GridBagLayout gbl_Start_Container = new GridBagLayout();
			gbl_Start_Container.columnWidths = new int[] {};
			gbl_Start_Container.rowHeights = new int[] {0, 0, 50, 300, 50, 0, 50, 0};
			gbl_Start_Container.columnWeights = new double[]{};
			gbl_Start_Container.rowWeights = new double[]{0.0, 0.0, 0.0};
			//setting containerStartButtons layout to GridBagLayout
			containerPlayerButtons.setLayout(gbl_Start_Container);
			
			//GridBagLayout building-up
			GridBagLayout Text_Container = new GridBagLayout();
			Text_Container.columnWidths = new int[] {};
			Text_Container.rowHeights = new int[] {0};
			Text_Container.columnWeights = new double[]{};
			Text_Container.rowWeights = new double[]{0.0, 0.0, 0.0};
			//setting containerStartText layout to GridBagLayout
			containerPlayerText.setLayout(Text_Container);
			
			textareaStartText = new JTextField();
			textareaStartText.setText("Player selection");
			//setting the font
			textareaStartText.setFont(new Font("Tahoma", Font.PLAIN, 50));
			//setting textareaStartText transparent
			textareaStartText.setOpaque(false);
			//setting foreground to black
			textareaStartText.setForeground(Color.white);
			//setting non-editable
			textareaStartText.setEditable(false);
			//setting no border
			textareaStartText.setBorder(javax.swing.BorderFactory.createEmptyBorder());
			GridBagConstraints gbc_textareaStartText = new GridBagConstraints();
			gbc_textareaStartText.insets = new Insets(0, 0, 5, 5);
			gbc_textareaStartText.gridx = 0;
			gbc_textareaStartText.gridy = 0;
			containerPlayerText.add(textareaStartText, gbc_textareaStartText);
			
			textareaSelectPlayers = new JTextField();
			textareaSelectPlayers.setText("Select the number of Players");
			//setting the font
			textareaSelectPlayers.setFont(new Font("Tahoma", Font.PLAIN, 16));
			//setting textareaStartText transparent
			textareaSelectPlayers.setOpaque(false);
			//setting foreground to black
			textareaSelectPlayers.setForeground(Color.white);
			//setting non-editable
			textareaSelectPlayers.setEditable(false);
			//setting no border
			textareaSelectPlayers.setBorder(javax.swing.BorderFactory.createEmptyBorder());
			GridBagConstraints gbc_textareaSelectPlayers = new GridBagConstraints();
			gbc_textareaSelectPlayers.insets = new Insets(0, 0, 5, 5);
			gbc_textareaSelectPlayers.gridx = 0;
			gbc_textareaSelectPlayers.gridy = 0;
			containerPlayerButtons.add(textareaSelectPlayers, gbc_textareaSelectPlayers);
			
	        //LoadPlayerNumber creation, setting text, setting desired font style & colour, setting field only readable, setting not visible border
			LoadPlayerNumber.addItem(2);
			LoadPlayerNumber.addItem(3);
			LoadPlayerNumber.addItem(4);
			LoadPlayerNumber.setFont(new Font("Tahoma", Font.PLAIN, 16));
			LoadPlayerNumber.setOpaque(true);
			LoadPlayerNumber.setForeground(Color.BLACK);
			LoadPlayerNumber.setEditable(false);
			LoadPlayerNumber.setBorder(javax.swing.BorderFactory.createEmptyBorder());
			LoadPlayerNumber.setPreferredSize(new Dimension(50, 30));
			GridBagConstraints gbc_LoadPlayerNumber = new GridBagConstraints();
			gbc_LoadPlayerNumber.insets = new Insets(0, 0, 5, 5);
			gbc_LoadPlayerNumber.gridx = 0;
			gbc_LoadPlayerNumber.gridy = 1;
			containerPlayerButtons.add(LoadPlayerNumber, gbc_LoadPlayerNumber);
			
        	textareaPlayersControl.setText(	"Key order: up, down, left, right, honey, oil\n\n" +
					"Player 1:        \u2191       \u2193       \u2190      \u2192      PgUp  PgDn\n" +
					"Player 2:       W       S       A       D       Q       E");
        	
			//setting the font
			textareaPlayersControl.setFont(new Font("Tahoma", Font.PLAIN, 16));
			//setting textareaStartText transparent
			textareaPlayersControl.setOpaque(false);
			//setting foreground to black
			textareaPlayersControl.setForeground(Color.white);
			//setting non-editable
			textareaPlayersControl.setEditable(false);
			//setting no border
			textareaPlayersControl.setBorder(javax.swing.BorderFactory.createEmptyBorder());
			GridBagConstraints gbc_textareaPlayersControl = new GridBagConstraints();
			gbc_textareaPlayersControl.insets = new Insets(0, 0, 5, 5);
			gbc_textareaPlayersControl.gridx = 0;
			gbc_textareaPlayersControl.gridy = 3;
			containerPlayerButtons.add(textareaPlayersControl, gbc_textareaPlayersControl);
			
			buttonNext.setText("Next");
			buttonNext.setFont(new Font("Tahoma", Font.PLAIN, 20));
			buttonNext.setPreferredSize(new Dimension(300, 50));
			GridBagConstraints gbc_buttonStartGame = new GridBagConstraints();
			gbc_buttonStartGame.insets = new Insets(0, 0, 5, 5);
			gbc_buttonStartGame.gridx = 0;
			gbc_buttonStartGame.gridy = 5;
			containerPlayerButtons.add(buttonNext, gbc_buttonStartGame);
			
			buttonBackToPalyers.setText("Back");
			buttonBackToPalyers.setFont(new Font("Tahoma", Font.PLAIN, 20));
			buttonBackToPalyers.setPreferredSize(new Dimension(300, 50));
			GridBagConstraints gbc_buttonBack = new GridBagConstraints();
			gbc_buttonBack.insets = new Insets(0, 0, 0, 5);
			gbc_buttonBack.gridx = 0;
			gbc_buttonBack.gridy = 7;
			containerPlayerButtons.add(buttonBackToPalyers, gbc_buttonBack);
			
			playerselection_screen.add(containerPlayerText, BorderLayout.NORTH);
			playerselection_screen.add(containerPlayerButtons, BorderLayout.CENTER);
			
		}
		
		public Integer GetPlayerNum() {
			
			return (Integer) LoadPlayerNumber.getSelectedItem();
			
		}
	
}
