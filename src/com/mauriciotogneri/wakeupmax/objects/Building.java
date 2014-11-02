package com.mauriciotogneri.wakeupmax.objects;

import org.json.JSONArray;
import org.json.JSONObject;
import com.misty.kernel.Process;
import com.misty.utils.Assets;

public class Building
{
	private Block[][] blocks;
	
	public Building(String path)
	{
		try
		{
			JSONObject json = Assets.getJsonObject(path);
			
			int width = json.getInt("width");
			int height = json.getInt("height");
			
			this.blocks = new Block[width][height];
			
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
		this.blocks[x][y] = new Block(x * Level.BLOCK_SIZE, y * Level.BLOCK_SIZE);
	}
	
	public void checkBottom(Protagonist protagonist)
	{
		Block blockA = getBlock(protagonist.x, protagonist.y - 1);
		Block blockB = getBlock(protagonist.x + protagonist.width - 1, protagonist.y - 1);
		
		if ((!checkBlockBottom(protagonist, blockA)) && (blockA != blockB))
		{
			checkBlockBottom(protagonist, blockB);
		}
	}
	
	private boolean checkBlockBottom(Protagonist protagonist, Block block)
	{
		boolean result = false;
		
		if ((block != null) && blocksIntersect(protagonist, block))
		{
			protagonist.y = block.y + block.height;
			protagonist.touchGround();
			result = true;
		}
		
		return result;
	}
	
	public void checkTop(Protagonist protagonist)
	{
		Block blockA = getBlock(protagonist.x, protagonist.y + protagonist.height);
		Block blockB = getBlock(protagonist.x + protagonist.width - 1, protagonist.y + protagonist.height);
		
		if ((!checkBlockTop(protagonist, blockA)) && (blockA != blockB))
		{
			checkBlockTop(protagonist, blockB);
		}
	}
	
	private boolean checkBlockTop(Protagonist protagonist, Block block)
	{
		boolean result = false;
		
		if ((block != null) && blocksIntersect(protagonist, block))
		{
			protagonist.y = block.y - protagonist.height;
			protagonist.touchCeiling();
			result = true;
		}
		
		return result;
	}
	
	public void checkLeft(Protagonist protagonist)
	{
		Block blockA = getBlock(protagonist.x - 1, protagonist.y);
		Block blockB = getBlock(protagonist.x - 1, protagonist.y + protagonist.height - 1);
		
		if ((!checkBlockLeft(protagonist, blockA)) && (blockA != blockB))
		{
			checkBlockLeft(protagonist, blockB);
		}
	}
	
	private boolean checkBlockLeft(Protagonist protagonist, Block block)
	{
		boolean result = false;
		
		if ((block != null) && blocksIntersect(protagonist, block))
		{
			protagonist.x = block.x + block.width;
			result = true;
		}
		
		return result;
	}
	
	public void checkRight(Protagonist protagonist)
	{
		Block blockA = getBlock(protagonist.x + protagonist.width, protagonist.y);
		Block blockB = getBlock(protagonist.x + protagonist.width, protagonist.y + protagonist.height - 1);
		
		if ((!checkBlockRight(protagonist, blockA)) && (blockA != blockB))
		{
			checkBlockRight(protagonist, blockB);
		}
	}
	
	private boolean checkBlockRight(Protagonist protagonist, Block block)
	{
		boolean result = false;
		
		if ((block != null) && blocksIntersect(protagonist, block))
		{
			protagonist.x = block.x - protagonist.width;
			result = true;
		}
		
		return result;
	}
	
	private boolean blocksIntersect(Process process, Block block)
	{
		boolean condition1 = (process.x + process.width) <= block.x;
		boolean condition2 = (block.x + block.width) <= process.x;
		boolean condition3 = (process.y + process.height) <= block.y;
		boolean condition4 = (block.y + block.height) <= process.y;
		
		// block.intersects(process.getBounds());
		
		return ((!condition1) && (!condition2) && (!condition3) && (!condition4));
	}
	
	private Block getBlock(float x, float y)
	{
		Block result = null;
		
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