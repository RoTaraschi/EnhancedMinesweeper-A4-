


import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JPanel;




class GamePanel extends JPanel implements Serializable
{

	//--------------------------------------------------ATTRIBUTES

	private static final long serialVersionUID = -1439988311049113060L;

	JPanel panel = new JPanel();

	//array of buttons that coresponds to the minesweeper grid
	JButton[][] buttons;
	GridTile[][] gridTiles;
	private int gameboardRowHeight;





	//(GUI)creates a container and we can define the layout
	Container grid = new Container();

	//--------------------------------------------------CONSTRUCTORS


	public GamePanel(int size, int mines , int shields, int probes, int bounsPoints, int infiniteLives)
	{
		gameboardRowHeight=size;

		buttons = new JButton[gameboardRowHeight][gameboardRowHeight];
		gridTiles = new GridTile[gameboardRowHeight][gameboardRowHeight];

		// defines Container Layout to be a grid
		grid.setLayout(new GridLayout(gameboardRowHeight,gameboardRowHeight));


		//Fill Button array with JButtons
		for (int i = 0; i < buttons.length; i++) 
		{			
			for (int j = 0; j < buttons[0].length; j++) 
			{
				buttons[i][j] = new JButton();
				buttons[i][j].setFocusable(false);
				buttons[i][j].setPreferredSize(new Dimension(50,50));
				grid.add(buttons[i][j]);
			}
		}


		//Populate Grid Tiles with numbers
		for (int i = 0; i < gameboardRowHeight; i++) 
		{
			for (int j = 0; j < gameboardRowHeight; j++) 
			{
				gridTiles[i][j] = new GridTile();
			}	
		}

		//Create random mines
		for (int i = 0; i < mines; i++) 
		{
			gridTiles[((int)(Math.random() * gameboardRowHeight))][((int)(Math.random() * gameboardRowHeight))].placeMine();
		}

		//Create shields
		for (int j = 0; j < shields; j++) 
		{
			gridTiles[((int)(Math.random() * gameboardRowHeight))][((int)(Math.random() * gameboardRowHeight))].placeExtraShield();
		}


		//create probes
		for (int i = 0; i < probes; i++) 
		{
			gridTiles[((int)(Math.random() * gameboardRowHeight))][((int)(Math.random() * gameboardRowHeight))].placeProbe();
		}

		//create bonus points.
		for (int i = 0; i < bounsPoints; i++) 
		{
			gridTiles[((int)(Math.random() * gameboardRowHeight))][((int)(Math.random() * gameboardRowHeight))].setBonusPoints(25);
		}

		//create infinite life
		for (int i = 0; i < infiniteLives; i++) 
		{
			gridTiles[((int)(Math.random() * gameboardRowHeight))][((int)(Math.random() * gameboardRowHeight))].setInfiniteLives(1);
		}




		/*
		 * 
		 * ---------------------NEIGHBORCOUNT
		 * 
		 * This is a funny algorithm, it the concept of it is that it itterates through the array of butons
		 * 
		 * then it iterates from -1 to 1 ALWAYS
		 * then it add that -1 or 0 or 1 to the corresponding tile that it's checking(this makes it a surrounding tile
		 * then it checks to see if it's in the bounds of the gameboard
		 * 
		 * then it adds it to a counter
		 * 
		 * then once it's done itterating
		 * 
		 * it will add the count and subtract whatever the current tile is since it iterated on 0 ,0 (which is itself so it counded itself
		 *
		 *
		 */

		for(int i = 0 ; i < gameboardRowHeight ; i++){
			for(int j = 0; j < gameboardRowHeight; j++){

				int countplacer = 0;

				for(int itest = -1 ; itest < 2 ;itest++){
					for(int jtest = -1 ; jtest < 2 ;jtest++){//and line above itterates from -1, 0 , 1

						//checks to see if it's in the bounds
						if( (0<=(i+itest) && (i+itest)<gameboardRowHeight )  && ( 0<=(j+jtest) && (j+jtest)<gameboardRowHeight) ){

							countplacer += gridTiles[i+itest][j+jtest].getMines(); //the thing that checks the count
						}
					}
				}

				gridTiles[i][j].setCount(countplacer-gridTiles[i][j].getMines());// the thing that set's the count

			}		
		}//done neighbor count

		setupPanel();

	}


	public void setupPanel()
	{

		this.add(grid);
		this.setBackground(Color.GRAY);
		this.setVisible(true);
	}


	//--------------------------METHODS

	public int getGameboardRowHeight() {
		return gameboardRowHeight;
	}

	public JButton getPlayerMove(int x, int y)
	{
		return buttons[x][y];
	}

	public GridTile getPlayerGrid(int x, int y)
	{
		return this.gridTiles[x][y];
	}

	public int getButtonsLength()
	{
		return buttons.length;
	}

	public int getButtonsLength0()
	{
		return buttons[0].length;
	}
	
	//Writes from Serialized File
	public void writeToSerializedFile(File file, GamePanel gamePanel)
	{
		try 
		{
			ObjectOutputStream output = new ObjectOutputStream(
					new FileOutputStream(file));
			output.writeObject(gamePanel);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	//Reads from serialized file
	public GamePanel readFromSerializedFile(File file) 
	{
		GamePanel Import = null;
		try 
		{
			ObjectInputStream input = new ObjectInputStream(
					new FileInputStream(file));
			Import = (GamePanel) input.readObject();    

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return Import;
	}



}
