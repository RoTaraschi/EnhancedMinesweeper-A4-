import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

import javax.swing.*;


public class Minesweeper
{

/*
 * Developed by Mike Basdeo & Roman Taraschi
 * 
 * This is an enhanced minesweeper with muli-bombs, shield, infinite lives, bonus points, and probes.
 * 
 * We tried to get the file serialization to work however, there's a java Object library that makes it currently unSerializable
 * I spent 5 hours trying to find out what makes it not serialized and I gave up and assume that it's one of the Java awt objects that's in there
 * EVEN after getting rid of the mouse pointer which is a know issue
 * 
 * 
 * 
 * 
 */



	public static void main(String[] args) 
	{
		HighScore highScore = new HighScore();
//		GamePanel gamepanel = null;
//		Player player = null;
//
		highScore = new HighScore().readFromSerializedFile(new File("HighScores.file"));
//		gamepanel.readFromSerializedFile(new File("PlayerSaveGamegp.file"));
//		player.readFromSerializedFile(new File("PlayerSaveGamep.file"));
//
//		if(player == null || gamepanel == null){

			//This opens a new main Frame.
			MainFrame newMain = new MainFrame(highScore);

//		}else{
//
//			MainFrame oldMain = new MainFrame(highScore , gamepanel, player);
//
//		}












	}
}
