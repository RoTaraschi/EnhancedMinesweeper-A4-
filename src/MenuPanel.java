import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import java.awt.Font;

import net.miginfocom.swing.MigLayout;

import javax.swing.SwingConstants;


public class MenuPanel extends JPanel implements ActionListener{
	
	public JButton newGameButton;
	private JButton aboutButton;
	private JButton saveButton;
	private JButton highScoresButton;
	private JButton scoreSystemButton;
	private JLabel title;
	

	//private JTextArea testText;
	
	
	
//-----------------------------------Constructor	
	public MenuPanel() 
	{
		setLayout(new MigLayout("", "[110px][110px][82px][][164px]", "[29px][21px][]"));
		
		newGameButton = new JButton("New Game");
		this.add (newGameButton, "cell 0 0,alignx left,aligny top");
		this.newGameButton.addActionListener(this);
		
		highScoresButton = new JButton("High Scores");
		this.add(highScoresButton, "cell 1 0,alignx left,aligny top");
		this.highScoresButton.addActionListener(this);
		
		aboutButton = new JButton("About");
		this.add (aboutButton, "cell 2 0,alignx left,aligny top");
		this.aboutButton.addActionListener(this);
		
		saveButton = new JButton("Save");
		this.add(saveButton, "cell 3 0,alignx left,aligny top");
		this.saveButton.addActionListener(this);
		
		scoreSystemButton = new JButton("How Score is Calculated");
		this.add(scoreSystemButton, "cell 1 1,alignx center aligny top");
		this.scoreSystemButton.addActionListener(this);
		
		
		
		title = new JLabel("   ENHANCED MINESWEEPER", SwingConstants.RIGHT);
		title.setFont(new Font("Stencil", Font.BOLD, 25));
		title.setForeground(Color.white);
		this.add(title, "cell 4 1,alignx left,aligny top");
		setupPanel();	
	}
	

	
	

//------------------------------------Methods
	
	public JButton getNewGameButton()
	{
		return this.newGameButton;
	}
	public JButton getAboutButton()
	{
		return this.aboutButton;
	}
	public JButton getSaveButton()
	{
		return this.saveButton;
	}
	public JButton getHighScoresButton()
	{
		return this.highScoresButton;
	}
	
	public void setupPanel()
	{
		this.setBackground(Color.black);
		this.setVisible(true);
	}
	public JButton getscoreSystemButton(){
		return this.scoreSystemButton;
	}


	//All actions in MainFrame.
	@Override
	public void actionPerformed(ActionEvent event) 
	{

	}



}
