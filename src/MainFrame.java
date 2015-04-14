import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;


public class MainFrame extends JFrame implements ActionListener, MouseListener, WindowListener,Serializable{

	//---------------Attributes

	private boolean gamecheck;
	private MenuPanel currentMainMenu;
	private GamePanel newGame;
	private InfoPanel infoPanel;
	private BottomPanel bottomPanel;
	private Player player;
	private HighScore highScore;
	private String newName;
	private MouseAdapter mouseListener = new MouseAdapter() 
	{
		public void mouseClicked(MouseEvent mEvent) 
		{
			for (int i = 0; i < newGame.getButtonsLength(); i++) 
			{
				for (int j = 0; j < newGame.getButtonsLength0(); j++) 
				{
					//this finds which button was clicked
					if(mEvent.getSource().equals(newGame.getPlayerMove(i, j)))
					{

						//handles flag event
						if(mEvent.getButton() == MouseEvent.BUTTON3)
						{
							if(newGame.getPlayerGrid(i, j).getCalled() == false)
							{
								newGame.getPlayerGrid(i, j).changeFlagged();
								if(newGame.getPlayerGrid(i, j).getFlagged() == true)
								{
									newGame.getPlayerMove(i,j).setIcon(new ImageIcon("flag.png")); 
									newGame.getPlayerMove(i, j).setEnabled(false);
									player.minusFlag();
									infoPanel.updateFlags(player.getFlags());
									checkforGameWin();
								}
								else
								{
									newGame.getPlayerMove(i,j).setIcon(new ImageIcon(""));
									newGame.getPlayerMove(i, j).setEnabled(true);
									player.addFlag();
									infoPanel.updateFlags(player.getFlags());

								}
							}
						} 
					}
				}
			}
		}
	};


	//---------------Constructors



	public MainFrame(HighScore highScore)
	{
		this.setTitle("Enhanced Minesweeper");

		//initialize all attributes
		currentMainMenu = new MenuPanel();


		//Defines the size and items for the Game
		newGame = new GamePanel(10, 25, 10, 10, 10, 1);
		infoPanel = new InfoPanel(newName, 0, 0, 0, 25);
		bottomPanel = new BottomPanel();
		this.player = new Player();
		this.highScore = highScore;
		this.gamecheck = false;



		//called once early to get labels before first game move.
		setupFrame();


		newName = JOptionPane.showInputDialog(this,"Enter your name","New Game",JOptionPane.WARNING_MESSAGE);


		player.setName(newName);
		infoPanel = new InfoPanel(newName, 0, 3, 0, 25);



		setupFrame();

		currentMainMenu.getNewGameButton().addActionListener(this);		//THE MAGIC!! Use methods!!
		currentMainMenu.getAboutButton().addActionListener(this);
		currentMainMenu.getSaveButton().addActionListener(this);
		currentMainMenu.getHighScoresButton().addActionListener(this);
		currentMainMenu.getscoreSystemButton().addActionListener(this);

		//adds listener for all game buttons.
		for (int x = 0; x < newGame.getButtonsLength(); x++) 
		{
			for (int y = 0; y < newGame.getButtonsLength0(); y++) 
			{
				newGame.getPlayerMove(x, y).addMouseListener(this);
				newGame.getPlayerMove(x,y).addActionListener(this);
			}
		}
	}

//this is for the serialized file that just gets sploped in here
	public MainFrame(HighScore highscore, GamePanel gamepanel, Player player){

		this.setTitle("Enhanced Minesweeper");

		//initialize all attributes
		currentMainMenu = new MenuPanel();


		//Defines the size and items for the called game
		newGame = gamepanel;
		infoPanel = new InfoPanel(player.getName(), player.getScore(), player.getLivesLeft(), player.getShield(), player.getFlags());
		bottomPanel = new BottomPanel();
		this.player = player;
		this.highScore = highscore;
		this.gamecheck = false;



		//called once early to get labels before first game move.
		setupFrame();

		currentMainMenu.getNewGameButton().addActionListener(this);		//THE MAGIC!! Use methods!!
		currentMainMenu.getAboutButton().addActionListener(this);
		currentMainMenu.getSaveButton().addActionListener(this);
		currentMainMenu.getHighScoresButton().addActionListener(this);
		currentMainMenu.getscoreSystemButton().addActionListener(this);

		//adds listener for all game buttons.
		for (int x = 0; x < newGame.getButtonsLength(); x++) 
		{
			for (int y = 0; y < newGame.getButtonsLength0(); y++) 
			{
				newGame.getPlayerMove(x, y).addMouseListener(this);
				newGame.getPlayerMove(x,y).addActionListener(this);
			}
		}
	}







	//-----------------------------------------Methods


	public JButton getnewGameButton()
	{
		return this.currentMainMenu.newGameButton;
	}

	public void setupFrame()
	{
		//BorderLayout Manager seemed fairly easy so I went with it. The other panels use a different layout manager.
		getContentPane().setLayout(new BorderLayout());
		this.setSize(870,675);

		//this adds each panel to the main frame.
		getContentPane().add(infoPanel, BorderLayout.WEST);
		getContentPane().add(currentMainMenu, BorderLayout.NORTH);
		getContentPane().add(bottomPanel, BorderLayout.SOUTH);	
		getContentPane().add(newGame, BorderLayout.EAST);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
	}



	public void checkforGameWin()
	{	
		if(!gamecheck){


			int looseChecker =0;
			int winChecker = 0;

			for (int x = 0; x < newGame.getButtonsLength(); x++) 
			{
				for (int y = 0; y < newGame.getButtonsLength0(); y++) 
				{
					if(newGame.getPlayerGrid(x,y).getCalled() == true && newGame.getPlayerGrid(x, y).getMines() >0){

						looseChecker += newGame.getPlayerGrid(x, y).getMines();


					}

				}
			}


			for (int x = 0; x < newGame.getButtonsLength(); x++) 
			{
				for (int y = 0; y < newGame.getButtonsLength0(); y++) 
				{

					if(newGame.getPlayerGrid(x, y).getFlagged() == true && newGame.getPlayerGrid(x,y).getMines() > 0){

						winChecker += newGame.getPlayerGrid(x, y).getMines();

					}			
				}

			}
			if(winChecker == 25) //all mines found
			{
				player.addToScore(winChecker*5);

				JOptionPane.showMessageDialog(this, "YOU WIN");
				gamecheck=true;
				highScore.addToHighScore(player);
				player.addFlag();
			}
			if(winChecker + looseChecker ==25){
				for (int x = 0; x < newGame.getButtonsLength(); x++) 
				{
					for (int y = 0; y < newGame.getButtonsLength0(); y++) 
					{
						newGame.getPlayerMove(x, y).setEnabled(false);
					}
				}
				player.addToScore(winChecker*5);
				JOptionPane.showMessageDialog(this, "You Lose");
				gamecheck=true;
				highScore.addToHighScore(player);
				player.addFlag();

			}
			if(player.getFlags() == 0 && player.getLivesLeft()>-1){
				for (int x = 0; x < newGame.getButtonsLength(); x++) 
				{
					for (int y = 0; y < newGame.getButtonsLength0(); y++) 
					{
						if(newGame.getPlayerMove(x, y).isEnabled())
						{
							if(newGame.getPlayerGrid(x, y).getMines() ==1)
							{
								newGame.getPlayerMove(x, y).setIcon(new ImageIcon("test3.png"));
							}
							if(newGame.getPlayerGrid(x, y).getMines() ==2)
							{
								newGame.getPlayerMove(x, y).setIcon(new ImageIcon("test2.png"));
							}
							newGame.getPlayerMove(x, y).setText(newGame.getPlayerGrid(x, y).getButtonPress());
							newGame.getPlayerGrid(x, y).setTileCalledTrue();
						}
					
					}
				}
				checkforGameWin();
			}
		}
	}

	public void hitMine(int xx, int yy)
	{	
		//Handles player losing one life, or minus one shield

		if( player.getShield() == 0)
		{
			player.minusLife(1);
			infoPanel.updateLife(player.getLivesLeft());
		}

		if(player.getShield() > 0)
		{
			player.minusShield(1);
			infoPanel.updateShield(player.getShield());
		}


		newGame.getPlayerGrid(xx, yy).setTileCalledTrue();

		if(newGame.getPlayerGrid(xx, yy).getMines() ==1)
		{
			newGame.getPlayerMove(xx, yy).setIcon(new ImageIcon("test3.png"));
		}
		if(newGame.getPlayerGrid(xx, yy).getMines() ==2)
		{
			newGame.getPlayerMove(xx, yy).setIcon(new ImageIcon("test2.png"));
		}

		//Handles Player's Death

		if(player.getLivesLeft() == 0 && player.getShield() == 0)
		{
			for (int x = 0; x < newGame.getButtonsLength(); x++) 
			{
				for (int y = 0; y < newGame.getButtonsLength0(); y++) 
				{
					if(newGame.getPlayerMove(x, y).isEnabled())
					{
						if(newGame.getPlayerGrid(x, y).getMines() ==1)
						{
							newGame.getPlayerMove(x, y).setIcon(new ImageIcon("test3.png"));
						}
						if(newGame.getPlayerGrid(x, y).getMines() ==2)
						{
							newGame.getPlayerMove(x, y).setIcon(new ImageIcon("test2.png"));
						}
						newGame.getPlayerMove(x, y).setText(newGame.getPlayerGrid(x, y).getButtonPress() + "");
						newGame.getPlayerGrid(x, y).setTileCalledTrue();
						checkforGameWin();
					}
				}
			}
		}

	}



	//-------------------------------ACTION HANDLING (All actions should be handled here)

	@Override
	public void actionPerformed(ActionEvent event) 
	{

		//Starts new game
		if(event.getSource().equals(currentMainMenu.getNewGameButton()))
		{
			this.dispose();

			MainFrame newMain = new MainFrame(highScore);
		}

		//Handles about button
		if(event.getSource().equals(currentMainMenu.getAboutButton()))
		{
			JOptionPane.showMessageDialog(this, "Mike Basdeo \n Roman Taraschi");
		}
		//Handles HighScore button
		if(event.getSource().equals(currentMainMenu.getHighScoresButton()))
		{
			HighScoreFrame hsFrame = new HighScoreFrame(highScore);
		}

		//Handles Save button
		if(event.getSource().equals(currentMainMenu.getSaveButton()))
		{
			//player.writeToSerializedFile(new File("PlayerSaveGamep.file"), player);
			//newGame.writeToSerializedFile(new File("PlayerSaveGamegp.file"), newGame);
			highScore.writeToSerializedFile(new File("HighScores.file"), highScore);
			System.exit(0);

		}

		//Handles how Score is Calculated
		if(event.getSource().equals(currentMainMenu.getscoreSystemButton())){

			JOptionPane.showMessageDialog(this, "Revealing square \t1 \n"
					+ "Bouns Score Crates \t25 \n"
					+ "Shield \t1 \n"
					+ "Probe \t1 \n");
		}



		checkforGameWin();

		//Handles gameplay
		for (int x = 0; x < newGame.getButtonsLength(); x++) 
		{
			for (int y = 0; y < newGame.getButtonsLength0(); y++) 
			{
				if(event.getSource().equals(newGame.getPlayerMove(x, y)))
				{

					//Probe using
					if(player.getProbe() == 1)
					{
						if(newGame.getPlayerGrid(x, y).getMines() > 0)
						{
							ImageIcon danger = new ImageIcon("danger.png");
							JOptionPane.showMessageDialog(null, "Danger! Mine.", "", JOptionPane.INFORMATION_MESSAGE, danger );
						}
						else
						{
							ImageIcon safe = new ImageIcon("safe.png");
							JOptionPane.showMessageDialog(null, "All Clear!", "", JOptionPane.INFORMATION_MESSAGE, safe );
						}
						player.minusProbe(1);

						break;
					}

					//Probe acquisition
					if(newGame.getPlayerGrid(x, y).getProbe() == 1)
					{

						ImageIcon probe = new ImageIcon("probe.png");
						JOptionPane.showMessageDialog(null, "PROBE! Choose a tile", "", JOptionPane.INFORMATION_MESSAGE, probe );
						player.setProbe(1);
						newGame.getPlayerGrid(x, y).setProbe(0);
					}

					//handles safe click  
					if(newGame.getPlayerGrid(x, y).getMines() == 0 && newGame.getPlayerGrid(x, y).getShield() == 0 && newGame.getPlayerGrid(x, y).getCalled() == false)
					{
						player.addToScore(1);
						newGame.getPlayerGrid(x, y).setTileCalledTrue();
						newGame.getPlayerMove(x, y).setText(newGame.getPlayerGrid(x, y).getButtonPress() + "");
						if(Integer.parseInt(newGame.getPlayerGrid(x, y).getButtonPress()) == 0){ 

							revealSurroundingZeroes(x,y);
						} 
						infoPanel.updateScore(player.getScore());	
					}

					//handles bonus click
					if(newGame.getPlayerGrid(x, y).getBonusPoints() > 0 && newGame.getPlayerGrid(x, y).getCalled() == false)
					{
						player.addToScore(newGame.getPlayerGrid(x, y).getBonusPoints());

						newGame.getPlayerGrid(x, y).setTileCalledTrue();
						newGame.getPlayerMove(x, y).setText(newGame.getPlayerGrid(x, y).getButtonPress() + ""); 
						infoPanel.updateScore(player.getScore());	

						ImageIcon bonus = new ImageIcon("bonus.png");
						JOptionPane opt = new JOptionPane("BONUS POINTS!", JOptionPane.WARNING_MESSAGE, JOptionPane.DEFAULT_OPTION, bonus, new Object[]{}); // no buttons
						final JDialog dlg = opt.createDialog("BONUS!");
						new Thread(new Runnable()
						{
							public void run()
							{
								try
								{
									Thread.sleep(1000);
									dlg.dispose();
								}
								catch ( Throwable th )
								{

								}
							}
						}).start();
						dlg.setVisible(true);
					}




					//handles infinite life  click
					if(newGame.getPlayerGrid(x, y).getInfiniteLives() > 0 && newGame.getPlayerGrid(x, y).getCalled() == false)
					{
						//player.setScore(1);
						player.setLivesInfinite();

						//interesting...
						player.addShield(3);
						newGame.getPlayerGrid(x, y).setTileCalledTrue();
						newGame.getPlayerMove(x, y).setText(newGame.getPlayerGrid(x, y).getButtonPress() + ""); 
						infoPanel.updateScore(player.getScore());	
						infoPanel.updateShield(player.getShield());
						infoPanel.updateLife(player.getLivesLeft());

						//not implemented. changes big picture to 1up.
						//infoPanel.changeBigPicture("1up.png");

						ImageIcon shield = new ImageIcon("shield.png");
						JOptionPane.showMessageDialog(null, "INFINITE LIVES", "", JOptionPane.INFORMATION_MESSAGE, shield );

					}

					//handles shield click
					if(newGame.getPlayerGrid(x, y).getMines() == 0 && newGame.getPlayerGrid(x, y).getShield() == 1 && newGame.getPlayerGrid(x, y).getCalled() == false)
					{
						player.addToScore(1);

						//interesting...
						player.addShield(3);
						newGame.getPlayerGrid(x, y).setTileCalledTrue();
						newGame.getPlayerMove(x, y).setText(newGame.getPlayerGrid(x, y).getButtonPress() + ""); 
						infoPanel.updateScore(player.getScore());	
						infoPanel.updateShield(player.getShield());
						infoPanel.updateLife(player.getLivesLeft());

						//not implemented. changes big picture to 1up.
						//infoPanel.changeBigPicture("1up.png");

						ImageIcon shield = new ImageIcon("shield.png");
						JOptionPane.showMessageDialog(null, "Shield Found", "", JOptionPane.INFORMATION_MESSAGE, shield );

					}


					//handles mine click
					if (newGame.getPlayerGrid(x, y).getMines() >= 1 && newGame.getPlayerGrid(x, y).getCalled() == false)
					{
						hitMine(x,y);
					}
				}				
			}
		}





	}


	//RECURSIVE ZEROS! -  MAGICAL BEAST OF CODE! 
	private void revealSurroundingZeroes(int x , int y){

		for(int itest = -1 ; itest < 2 ;itest++){
			for(int jtest = -1 ; jtest < 2 ;jtest++){

				if( (0<=(x+itest) && (x+itest)<newGame.getGameboardRowHeight() )  && ( 0<=(y+jtest) && (y+jtest)<newGame.getGameboardRowHeight()) ){

					if(Integer.parseInt(newGame.getPlayerGrid(x+itest, y+jtest).getButtonPress()) == 0){ 
						if(!(newGame.getPlayerGrid(x+itest, y+jtest).getCalled())){

							newGame.getPlayerGrid(x+itest, y+jtest).setTileCalledTrue();
							newGame.getPlayerMove(x+itest, y+jtest).setText(newGame.getPlayerGrid(x+itest, y+jtest).getButtonPress() + "");
							revealSurroundingZeroes(x+itest, y+jtest);

						}
					}
					else{
						newGame.getPlayerGrid(x+itest, y+jtest).setTileCalledTrue();
						newGame.getPlayerMove(x+itest, y+jtest).setText(newGame.getPlayerGrid(x+itest, y+jtest).getButtonPress() + "");
						player.addToScore(1);
					}
				}
			}
		}
	}
	//end of magical stuff

	//------------------------------------------------MOUSE LISTENERS	

	@Override
	public void mouseClicked(MouseEvent mEvent) 
	{
		
		//this is for the flag placement
		((JButton)mEvent.getSource()).addMouseListener(mouseListener);

	}



	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub


	}

	@Override
	public void windowClosing(WindowEvent e) {

		/*
		 * Saves as a null file so that a new game is started and not the previous one
		 */
		
		
		//player.writeToSerializedFile(new File("PlayerSaveGamep.file"), null);
		//newGame.writeToSerializedFile(new File("PlayerSaveGamegp.file"), null);
		highScore.writeToSerializedFile(new File("HighScores.file"), highScore);
		System.exit(0);
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}




}
