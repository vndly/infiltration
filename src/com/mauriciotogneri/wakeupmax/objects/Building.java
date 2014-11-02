package com.mauriciotogneri.wakeupmax.objects;

import org.json.JSONArray;
import org.json.JSONObject;
import com.misty.kernel.Process;
import com.misty.math.Rectangle;
import com.misty.utils.Assets;

public class Building extends Process
{
	private Rectangle[][] blocks;
	
	public Building(String path, int z, String image)
	{
		super(false, false);
		
		this.z = z;
		
		setImage(image);
		
		try
		{
			JSONObject json = Assets.getJsonObject(path);
			
			int width = json.getInt("width");
			int height = json.getInt("height");
			
			this.blocks = new Rectangle[width][height];
			
			JSONArray blocks = json.getJSONArray("blocks");
			
			for (int i = 0; i < blocks.length(); i++)
			{
				JSONObject block = blocks.getJSONObject(i);
				
				int x = block.getInt("x");
				int y = block.getInt("y");
				
				addBlock(x, y);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private void addBlock(int x, int y)
	{
		this.blocks[x][y] = new Rectangle(x * Level.BLOCK_SIZE, y * Level.BLOCK_SIZE, Level.BLOCK_SIZE, Level.BLOCK_SIZE);
	}
	
	public void checkBottom(Protagonist protagonist)
	{
		Rectangle blockA = getBlock(protagonist.x, protagonist.y - 1);
		Rectangle blockB = getBlock(protagonist.x + protagonist.width - 1, protagonist.y - 1);
		
		if ((!checkBlockBottom(protagonist, blockA)) && (blockA != blockB))
		{
			checkBlockBottom(protagonist, blockB);
		}
	}
	
	private boolean checkBlockBottom(Protagonist protagonist, Rectangle block)
	{
		boolean result = false;
		
		if ((block != null) && block.intersects(protagonist.getBounds()))
		{
			protagonist.y = block.y + block.height;
			protagonist.touchGround();
			result = true;
		}
		
		return result;
	}
	
	public void checkTop(Protagonist protagonist)
	{
		Rectangle blockA = getBlock(protagonist.x, protagonist.y + protagonist.height);
		Rectangle blockB = getBlock(protagonist.x + protagonist.width - 1, protagonist.y + protagonist.height);
		
		if ((!checkBlockTop(protagonist, blockA)) && (blockA != blockB))
		{
			checkBlockTop(protagonist, blockB);
		}
	}
	
	private boolean checkBlockTop(Protagonist protagonist, Rectangle block)
	{
		boolean result = false;
		
		if ((block != null) && block.intersects(protagonist.getBounds()))
		{
			protagonist.y = block.y - protagonist.height;
			protagonist.touchCeiling();
			result = true;
		}
		
		return result;
	}
	
	public void checkLeft(Protagonist protagonist)
	{
		Rectangle blockA = getBlock(protagonist.x - 1, protagonist.y);
		Rectangle blockB = getBlock(protagonist.x - 1, protagonist.y + protagonist.height - 1);
		
		if ((!checkBlockLeft(protagonist, blockA)) && (blockA != blockB))
		{
			checkBlockLeft(protagonist, blockB);
		}
	}
	
	private boolean checkBlockLeft(Protagonist protagonist, Rectangle block)
	{
		boolean result = false;
		
		if ((block != null) && block.intersects(protagonist.getBounds()))
		{
			protagonist.x = block.x + block.width;
			result = true;
		}
		
		return result;
	}
	
	public void checkRight(Protagonist protagonist)
	{
		Rectangle blockA = getBlock(protagonist.x + protagonist.width, protagonist.y);
		Rectangle blockB = getBlock(protagonist.x + protagonist.width, protagonist.y + protagonist.height - 1);
		
		if ((!checkBlockRight(protagonist, blockA)) && (blockA != blockB))
		{
			checkBlockRight(protagonist, blockB);
		}
	}
	
	private boolean checkBlockRight(Protagonist protagonist, Rectangle block)
	{
		boolean result = false;
		
		if ((block != null) && block.intersects(protagonist.getBounds()))
		{
			protagonist.x = block.x - protagonist.width;
			result = true;
		}
		
		return result;
	}
	
	private Rectangle getBlock(float x, float y)
	{
		Rectangle result = null;
		
		try
		{
			int i = (int)Math.floor(x / Level.BLOCK_SIZE);
			int j = (int)Math.floor(y / Level.BLOCK_SIZE);
			
			result = this.blocks[i][j];
		}
		catch (Exception e)
		{
		}
		
		return result;
	}
}