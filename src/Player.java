import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


public class Player implements Serializable
{
	
//-----------------------Attributes
	
	
	private static final long serialVersionUID = 1L;
	private String name;
	private int score;
	private int livesLeft;
	private int shield;
	private int probe;
	private int flags;

//----------------------Constructors
	
	public Player()
	{
		this.name = null;
		this.score = 0;
		this.livesLeft = 3;
		this.shield = 0;
		this.probe = 0;
		this.flags = 25;
	}
	
	
	public Player(String name)
	{
		this.name = name;
		this.score = 0;
	}
	
	
	
	
//----------------------Methods All the get's and sets and minus1's
	
	public void setLivesInfinite()
	{
		this.livesLeft = -1;
	}

	public int getProbe()
	{
		return this.probe;
	}
	
	public void setProbe(int newProbe)
	{
		this.probe = 1;
	}
	
	public void minusProbe(int minusProbe)
	{
		this.probe = 0;
	}
	
	public void minusLife(int minusLife) 
	{
		this.livesLeft -= minusLife;
	}
	
	public void minusShield(int minusShield)
	{
		this.shield -= minusShield;
	}
	
	public void addShield(int addShield)
	{
		this.shield += addShield;
	}
	
	public int getShield()
	{
		return this.shield;
	}
	
	public void addFlag() {
		this.flags++;
	}
	
	public int getFlags() {
		return this.flags;
	}
	
	public void minusFlag(){
		this.flags--;
	}
	
	public int getLivesLeft()
	{
		return livesLeft;
	}
	
	public int getScore()
	{
		return score;
	}
	public void addToScore(int score) 
	{
		this.score += score;
	}
	
	public void setName(String newName)
	{
		this.name = newName;
	}
	
	public String getName()
	{
		return this.name;
	}

	public void writeToSerializedFile(File file, Player player)
	{
		try 
		{
			ObjectOutputStream output = new ObjectOutputStream(
					new FileOutputStream(file));
			output.writeObject(player);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	//Reads from serialized file
	public Player readFromSerializedFile(File file) 
	{
		Player Import = null;
		try 
		{
			ObjectInputStream input = new ObjectInputStream(
					new FileInputStream(file));
			Import = (Player) input.readObject();    

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
