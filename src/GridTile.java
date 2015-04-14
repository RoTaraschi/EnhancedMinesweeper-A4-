import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


class GridTile implements Serializable
{
	//----------------------------------------//Attributes of a single grid tile


	
	private boolean called;
	private int mines;
	private int count;
	private int shield;
	private boolean flagged;
	private int probe;
	private int bonusPoints;
	private int infiniteLives;
	

	//---------------------------------------//Constructors of a single grid tile
	public GridTile()
	{
		this.mines = 0;
		this.shield = 0;
		this.count = 0;
		this.called = false;
		this.flagged = false;
		this.probe = 0;
		this.bonusPoints = 0;
		this.infiniteLives = 0;
	}

	//---------------------------------------//Methods of a single grid tile
	
	public void setInfiniteLives(int newInfiniteLives)
	{
		this.infiniteLives = this.infiniteLives + newInfiniteLives;
	}
	
	public int getInfiniteLives()
	{
		return this.infiniteLives;
	}
	
	public void setBonusPoints(int newBonusPoints)
	{
		this.bonusPoints = newBonusPoints;
	}
	
	public int getBonusPoints()
	{
		return this.bonusPoints;
	}
	
	public void placeProbe() 
	{
		this.probe = 1;
	}
	
	public int getProbe()
	{
		return this.probe;
	}
	
	public int getMines()
	{
		return this.mines;
	}
	public void placeMine()
	{
		this.mines = this.mines + 1;;
	}
	
	public void placeExtraShield()
	{
		this.shield = 1;
	}
	
	public int getShield()
	{
		return this.shield;
	}
	
	public void setCount(int newCount)
	{
		this.count = newCount;
	}
	
	public int getCount()
	{
		if(this.mines > 0)
		{
			return 9;
		}
		else
		{
			return this.count;	
		}
	}
	
	public String getButtonPress()
	{
		if(this.getMines()<0){
			return "m"+this.getMines();
		}
		else
		{
			return this.count + "";	
		}
	}
	
	

	public boolean getCalled()
	{
		return this.called;
	}
	
	
	public void setTileCalledTrue()
	{
		this.called = true;
	}
	public void setTileCalledFalse()
	{
		this.called = false;
	}

	public void resetCall()
	{
		this.called = false;
	}

	public boolean getFlagged() 
	{
		return this.flagged;
	}
	public void changeFlagged()
	{
		this.flagged = !this.flagged;
	}

	public void setProbe(int i) {
		this.probe = i;
		
	}
	//Writes to Serialized File
	public void writeToSerializedFile(File file, GridTile[][] gridtile)
	{
		try 
		{
			ObjectOutputStream output = new ObjectOutputStream(
					new FileOutputStream(file));
			output.writeObject(gridtile);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	//Reads from serialized file
	public GridTile[][] readFromSerializedFile(File file) 
	{
		GridTile[][] Import = null;
		try 
		{
			ObjectInputStream input = new ObjectInputStream(
					new FileInputStream(file));
			Import = (GridTile[][]) input.readObject();    

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