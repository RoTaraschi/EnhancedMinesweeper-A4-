import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import java.awt.Color;


public class AboutFrame extends JFrame implements ActionListener{
	
//---------------Attributes


	
	
//---------------Constructors
	
	private static final long serialVersionUID = 1L;


	public AboutFrame()
	{
		getContentPane().setBackground(Color.GREEN);
	
		setupFrame();
		//currentMainMenu.getNewGameButton().addActionListener(this);		//THE MAGIC!! add more here
		
	}
	
//---------------Methods
	

	

	
	public void setupFrame()
	{
		
		//BorderLayout Manager seemed fairly easy so I went with it.
		//getContentPane().setLayout(new BorderLayout());
		
		this.setSize(200,200);//
		
		this.setLocationRelativeTo(null);
		//These were causing trouble so I took them out. 
		//this.setContentPane(currentMainMenu);
		//this.setContentPane(newGame);
		
		
		//this adds each panel to the main frame.
	
	
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    this.setVisible(true);
	}
	
	
//------------------------ACTION HANDLING

	@Override
	public void actionPerformed(ActionEvent event) {		
		
	}




}
