import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JTable;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.CardLayout;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;


public class HighScoreFrame extends JFrame implements ActionListener{
	private JTable table;
	private JButton btnClose;

	public HighScoreFrame(HighScore highscore){
		getContentPane().setBackground(Color.GRAY);
		getContentPane().setLayout(null);
		
		
		//ohh look a table that's convinient to put a list that's 2 x 10
		table = new JTable();
		table.setModel(new DefaultTableModel(
			highscore.display(),
			new String[] {
				"Player Name", "Score"
			}
		));
		
		
		table.setBounds(0, 0, 184, 160);
		getContentPane().add(table);
		
		btnClose = new JButton("Close");
		btnClose.addActionListener(this);
		btnClose.setBounds(50, 171, 89, 23);
		getContentPane().add(btnClose);
		setupFrame();
	}

	private void setupFrame() {

		this.setSize(200, 247);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource().equals(btnClose)){
			this.dispose();
		}
		
	}
	
	
}
