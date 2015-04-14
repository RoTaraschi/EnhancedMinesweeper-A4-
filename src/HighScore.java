import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;



public class HighScore implements Serializable {

	private static final long serialVersionUID = 1389773029334593814L;

	int[] top10Score = new int [10];// keeps the #
	String[] top10Name = new String [top10Score.length] ; //keeps the Name


	public void addToHighScore(Player p1){//oh look a player is the parameter

		// since the last person is going to get booted out just check to see if you better than the #10th
		if(top10Score[top10Score.length-1] < p1.getScore() ){ 

			top10Score[top10Score.length-1] = p1.getScore();
			top10Name[top10Score.length-1] = p1.getName();

			//this sorts from bottom to top ie i starts at 10 and works its way to 2 since 2 will make it switch with the #1 person
		for(int i = top10Score.length-1; 0 < i ; i--){

			if(top10Score[i]>top10Score[i-1]){

				int tempScore = Integer.valueOf(top10Score[i-1]);
				String tempName = top10Name[i-1].toString();

				top10Score[i-1] = Integer.valueOf(top10Score[i]);
				top10Name[i-1] = top10Name[i].toString();

				top10Score[i] = tempScore;
				top10Name[i] = tempName;
			}
		}
		}
		
	}
//this is a method that makes it be displayed to a JTable
	public Object[][] display(){
		
		Object[][] toTable = new String [top10Name.length][2];
		
		for(int i =0 ; i < top10Name.length ; i++)
		{
			
			toTable[i][0] = top10Name[i].toString();
			toTable[i][1] = Integer.valueOf(top10Score[i]).toString();
			
			
			
		}
		
		return toTable;
		
	}
	//write to serialized file
	public void writeToSerializedFile(File file, HighScore highScore)
	 {
		    try 
		    {
		        ObjectOutputStream output = new ObjectOutputStream(
		                                    new FileOutputStream(file));
		        output.writeObject(highScore);
		        
		    } catch (FileNotFoundException e) {
		        e.printStackTrace();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
	 }
	
	 
	 //Reads from serialized file
	 public HighScore readFromSerializedFile(File file) 
	 {
		    HighScore HImport = null;
		    try 
		    {
		        ObjectInputStream input = new ObjectInputStream(new FileInputStream(file));
		      HImport = (HighScore) input.readObject();    
		      
		    } catch (FileNotFoundException e) {
		        e.printStackTrace();
		    } catch (IOException e) {
		        e.printStackTrace();
		    } catch (ClassNotFoundException e) {
		       e.printStackTrace();
		    }
		    return HImport;
	}

}

