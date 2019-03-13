package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

/**
 * DrawField is an object that represents a Field entity's graphical elements to be drawn.
 * @author Arpad Fodor
 *
 */
public class DrawField extends JPanel {

	private static final long serialVersionUID = -1364038124023246234L;
	
	/** 
	 * The name of the Field
	 */
	String name;

	/** 
	 * JLayeredPane to put pictures onto each other in Z-order
	 */
	JLayeredPane layeredPane;
	
	/** 
	 * JLabels to display elements on the Field
	 */
    JLabel imageContainer_thing;
    JLabel imageContainer_substance;
    JLabel imageContainer_fieldtype;
    JLabel imageContainer_floor;
    
    /** 
	 * JPanel to display color data
	 */
    JPanel colourizer;

	/** 
	 * The buttons position on the game track
	 */
	GridBagConstraints gbc_field;

	/** 
	 * The size of the Field
	 */
	int size;

	/** 
	 * The Field's x position
	 */
	int position_x;

	/** 
	 * the Field's y position
	 */
	int position_y;

	/** 
	 * The color of the Field
	 */
	Color color;

	/** 
	 * Images that appears on the JLabels
	 */
	ImageIcon IconThing;
	ImageIcon IconSubstance;
	ImageIcon IconFieldType;
	ImageIcon IconFloor;

	/** 
	 * DrawField constructor
	 * Sets the name, size, x and y position, container, color and icon of the field
	 * 
	 * @param	name			the name of the Field
	 * @param	size 			the size of the Field
	 * @param	position_x		the x position of the Field
	 * @param	position_y		the y position of the Field
	 * @param 	cont			a container that contains the Fields
	 * @param	color			the color of the Field
	 * @param	IconThing		an icon that appears on the imageContainer_thing
	 * @param	IconSubstance	an icon that appears on the imageContainer_substance
	 * @param	IconFloor		an icon that appears on the imageContainer_floor
	 */
	public DrawField(String name, int size, int position_x, int position_y, Container cont, Color color, 
			ImageIcon IconThing, ImageIcon IconSubstance, ImageIcon IconFieldType, ImageIcon IconFloor) {
		
		super();
		
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		this.IconThing = IconThing;
		this.IconSubstance = IconSubstance;
		this.IconFieldType = IconFieldType;
		this.IconFloor = IconFloor;
		
	    imageContainer_thing = new JLabel(this.IconThing);
	    imageContainer_substance = new JLabel(this.IconSubstance);
	    imageContainer_fieldtype = new JLabel(this.IconFieldType);
	    imageContainer_floor = new JLabel(this.IconFloor);
		
		this.color = color; 
		this.name = name;
		this.size = size; 
		this.position_x = position_x; 
		this.position_y = position_y;
		
		this.colourizer = new JPanel();
		
		if(this.color == null) {
			
			this.colourizer.setBackground(new Color(0, 0, 0, 0));
			
		}
		
		else {
			
			this.colourizer.setBackground(new Color(this.color.getRed(), this.color.getGreen(), this.color.getBlue(), 160));
			
		}
		
		//JLayeredPane initialisation
        this.layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(this.size, this.size));
        
        //Z-order top
        layeredPane.add(imageContainer_thing, 0);
        //Z-order 2nd
        layeredPane.add(imageContainer_fieldtype, 1);
        //Z-order 3rd
        layeredPane.add(imageContainer_substance, 2);
        //Z-order 4th
        layeredPane.add(colourizer, 3);
        //Z-order bottom
        layeredPane.add(imageContainer_floor, 4);
        
        this.add(layeredPane, BorderLayout.CENTER);
		
		this.imageContainer_thing.setBounds(0, 0, this.size, this.size);
		this.imageContainer_fieldtype.setBounds(0, 0, this.size, this.size);
		this.imageContainer_substance.setBounds(0, 0, this.size, this.size);
		this.colourizer.setBounds(0, 0, this.size, this.size);
		this.imageContainer_floor.setBounds(0, 0, this.size, this.size);
		
		this.setPreferredSize(new Dimension(this.size, this.size));
		this.setMinimumSize(new Dimension(this.size, this.size));
		this.setMaximumSize(new Dimension(this.size, this.size));
		
		gbc_field = new GridBagConstraints();
		gbc_field.fill = GridBagConstraints.BOTH;
		gbc_field.gridx = this.position_x;
		gbc_field.gridy = this.position_y;
		
		cont.add(this, this.getGridBagConstraints());
		
	}
	

	/** 
	 * Returns with the name of the JPanel
	 * 
	 * @return 	String
	 */
	public String getName() {
		
		return this.name;
		
	}
	
	/** 
	 * Returns with the JPanel
	 * 
	 * @return	JPanel
	 */
	public JPanel getJPanel() {
		
		return this;
		
	}
	
	/** 
	 * Returns with the position of the JPanel
	 * 
	 * @return	GridBagConstraints
	 */
	public GridBagConstraints getGridBagConstraints() {
		
		return this.gbc_field;
		
	}
	
	/** 
	 * Returns with the color of the JPanel
	 * 
	 * @return	Color
	 */
	public Color getColor() {
		
		return this.color;
		
	}
	
	/** 
	 * Resets the field properties (the color and the images)
	 * 
 	 * @param	currentcolor	the current color of the field
	 * @param	IconThing		an icon that appears on the imageContainer_thing
	 * @param	IconSubstance	an icon that appears on the imageContainer_substance
	 * @param	IconFloor		an icon that appears on the imageContainer_floor
	 * @return	void
	 */
	public void reSet(Color currentcolor, ImageIcon IconThing, ImageIcon IconSubstance, ImageIcon IconFieldType, ImageIcon IconFloor) {
			
			this.color = currentcolor;
			
			if(this.color == null) {
				
				this.colourizer.setBackground(new Color(0, 0, 0, 0));
				
			}
			
			else {
				
				this.colourizer.setBackground(new Color(this.color.getRed(), this.color.getGreen(), this.color.getBlue(),150));
				
			}

			this.IconThing = IconThing;
			this.IconSubstance = IconSubstance;
			this.IconFieldType = IconFieldType;
			this.IconFloor = IconFloor;
			
			this.imageContainer_thing.setIcon(this.IconThing);
			this.imageContainer_substance.setIcon(this.IconSubstance);
			this.imageContainer_fieldtype.setIcon(this.IconFieldType);
			this.imageContainer_floor.setIcon(this.IconFloor);
			
	}
	
	/** 
	 * Returns with the x position of the JPanel
	 * 
	 * @return	int
	 */
	public int getPositionX() {
		
		return this.position_x;
		
	}
	
	/** 
	 * Returns with the y position of the JPanel
	 * 
	 * @return	int
	 */
	public int getPositionY() {
		
		return this.position_y;
		
	}
}