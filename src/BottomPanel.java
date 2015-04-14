import javax.swing.JButton;
import javax.swing.JFrame;
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
import net.miginfocom.swing.MigLayout;


public class BottomPanel extends JPanel implements ActionListener{

	

	//we can add things here if we want to
	
	
	
//-----------------------------------Constructor	
	public BottomPanel() 
	{
		setupPanel();	
	}
	
//------------------------------------Methods

	
	
	public void setupPanel()
	{
		
		//trying different layout managers for fun
		this.setBackground(Color.black);
		setLayout(new MigLayout("", "[]", "[]"));
		this.setVisible(true);
	}
	
	


	//All actions in MainFrame.
	@Override
	public void actionPerformed(ActionEvent event) 
	{

	}
}
