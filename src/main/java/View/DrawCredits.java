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
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

/**
 * DrawCredits draws the credits screen.
 */
public class DrawCredits {

	//credits screen elements
	protected JLabel credits_screen;
	protected Container containerCreditsButtons;
	protected Container containerCreditsText;
	protected JButton buttonCreditsBack;
	protected JTextField textareaCreditsText;
	protected JTextArea textareaCredits;
	
	public DrawCredits(JLabel credits_screen, JButton buttonCreditsBack) {
		
		this.credits_screen = credits_screen;
		
		//we need the button from the Draw object as it holds button listeners
		this.buttonCreditsBack = buttonCreditsBack;
		
		ImageIcon Wallpaper = new ImageIcon("src/visuals/background.jpg");
		
		//start screen setting-up
		this.credits_screen.setIcon(Wallpaper);
		credits_screen.setBackground(UIManager.getColor("TextPane.selectionBackground"));
		credits_screen.setLayout(new BorderLayout(0, 0));
		
		//containerStartButtons setting-up
		containerCreditsButtons = new Container();
		//containerStartText setting-up
		containerCreditsText = new Container();
		
		//GridBagLayout building-up
		GridBagLayout gbl_containerCreditsButtons = new GridBagLayout();
		gbl_containerCreditsButtons.columnWidths = new int[] {};
		gbl_containerCreditsButtons.rowHeights = new int[] {0, 100, 0};
		gbl_containerCreditsButtons.columnWeights = new double[]{};
		gbl_containerCreditsButtons.rowWeights = new double[]{0.0, 0.0, 0.0};
		//setting containerCreditsButtons layout to GridBagLayout
		containerCreditsButtons.setLayout(gbl_containerCreditsButtons);
		
		//GridBagLayout building-up
		GridBagLayout gbl_containerCreditsText = new GridBagLayout();
		gbl_containerCreditsText.columnWidths = new int[] {};
		gbl_containerCreditsText.rowHeights = new int[] {0};
		gbl_containerCreditsText.columnWeights = new double[]{};
		gbl_containerCreditsText.rowWeights = new double[]{0.0, 0.0, 0.0};
		//setting containerCreditsText layout to GridBagLayout
		containerCreditsText.setLayout(gbl_containerCreditsText);
		
		textareaCreditsText = new JTextField();
		textareaCreditsText.setText("Credits");
		//setting the font
		textareaCreditsText.setFont(new Font("Tahoma", Font.PLAIN, 50));
		//setting textareaStartText transparent
		textareaCreditsText.setOpaque(false);
		//setting foreground to black
		textareaCreditsText.setForeground(Color.white);
		//setting non-editable
		textareaCreditsText.setEditable(false);
		//setting no border
		textareaCreditsText.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		GridBagConstraints gbc_textareaMapSelectText = new GridBagConstraints();
		gbc_textareaMapSelectText.insets = new Insets(0, 0, 5, 5);
		gbc_textareaMapSelectText.gridx = 0;
		gbc_textareaMapSelectText.gridy = 0;
		containerCreditsText.add(textareaCreditsText, gbc_textareaMapSelectText);
		
		textareaCredits = new JTextArea();
		textareaCredits.setText(	"Creators:\n\n"
									+ "Arpad Fodor\n"
									+ "Adam Szaloky\n"
									+ "Hanna Pleesz\n"
									+ "Klaudia Kruppa");
		//setting the font
		textareaCredits.setFont(new Font("Tahoma", Font.PLAIN, 16));
		//setting textareaStartText transparent
		textareaCredits.setOpaque(false);
		//setting foreground to black
		textareaCredits.setForeground(Color.white);
		//setting non-editable
		textareaCredits.setEditable(false);
		//setting no border
		textareaCredits.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		GridBagConstraints gbc_textareaCredits = new GridBagConstraints();
		gbc_textareaCredits.insets = new Insets(0, 0, 5, 5);
		gbc_textareaCredits.gridx = 0;
		gbc_textareaCredits.gridy = 0;
		containerCreditsButtons.add(textareaCredits, gbc_textareaCredits);
		
		buttonCreditsBack.setText("Back");
		buttonCreditsBack.setFont(new Font("Tahoma", Font.PLAIN, 20));
		buttonCreditsBack.setPreferredSize(new Dimension(300, 50));
		GridBagConstraints gbc_buttonCreditsBack = new GridBagConstraints();
		gbc_buttonCreditsBack.insets = new Insets(0, 0, 0, 5);
		gbc_buttonCreditsBack.gridx = 0;
		gbc_buttonCreditsBack.gridy = 2;
		containerCreditsButtons.add(buttonCreditsBack, gbc_buttonCreditsBack);
		
		credits_screen.add(containerCreditsText, BorderLayout.NORTH);
		credits_screen.add(containerCreditsButtons, BorderLayout.CENTER);
		
	}
	
}