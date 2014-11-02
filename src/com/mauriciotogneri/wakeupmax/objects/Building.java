package com.mauriciotogneri.wakeupmax.objects;

import org.json.JSONArray;
import org.json.JSONObject;
import com.misty.utils.Assets;

public class Building
{
	private BuildingBlock[][] blocks;
	
	public Building(String path)
	{
		try
		{
			JSONObject json = Assets.getJsonObject(path);
			
			int width = json.getInt("width");
			int height = json.getInt("height");
			
			this.blocks = new BuildingBlock[width][height];
			
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
	
	private void addBlock(int i, int j)
	{
		BuildingBlock ground = new BuildingBlock(i * Level.BLOCK_SIZE, j * Level.BLOCK_SIZE);
		ground.start();
		
		this.blocks[i][j] = ground;
	}
	
	public void checkBottom(Max max)
	{
		Cell cellA = new Cell(max.x, (max.y + max.height) - 1);
		Cell cellB = new Cell((max.x + max.width) - 1, (max.y + max.height) - 1);
		
		if ((!checkCellBottom(max, cellA)) && (!cellA.equals(cellB)))
		{
			checkCellBottom(max, cellB);
		}
	}
	
	private boolean checkCellBottom(Max max, Cell cell)
	{
		boolean result = false;
		
		if (getBlock(cell.i, cell.j) == null)
		{
			BuildingBlock blockBottom = getBlock(cell.i, cell.j - 1);
			
			if ((blockBottom != null) && blocksIntersect(max, blockBottom))
			{
				max.y = blockBottom.y + blockBottom.height;
				max.touchGround();
				result = true;
			}
		}
		
		return result;
	}
	
	public void checkTop(Max max)
	{
		Cell cellA = new Cell(max.x, max.y);
		Cell cellB = new Cell(max.x, max.y);
		
		if ((!checkCellTop(max, cellA)) && (!cellA.equals(cellB)))
		{
			checkCellTop(max, cellB);
		}
	}
	
	private boolean checkCellTop(Max max, Cell cell)
	{
		boolean result = false;
		
		if (getBlock(cell.i, cell.j) == null)
		{
			BuildingBlock blockTop = getBlock(cell.i, cell.j + 1);
			
			if ((blockTop != null) && blocksIntersect(max, blockTop))
			{
				max.y = blockTop.y - max.height;
				max.touchCeiling();
				result = true;
			}
		}
		
		return result;
	}
	
	public void checkLeft(Max max)
	{
		Cell cellA = new Cell((max.x + max.width) - 1, (max.y + max.height) - 1);
		Cell cellB = new Cell((max.x + max.width) - 1, max.y);
		
		if ((!checkCellLeft(max, cellA)) && (!cellA.equals(cellB)))
		{
			checkCellLeft(max, cellB);
		}
	}
	
	private boolean checkCellLeft(Max max, Cell cell)
	{
		boolean result = false;
		
		if (getBlock(cell.i, cell.j) == null)
		{
			BuildingBlock blockLeft = getBlock(cell.i - 1, cell.j);
			
			if ((blockLeft != null) && blocksIntersect(max, blockLeft))
			{
				max.x = blockLeft.x + blockLeft.width;
				result = true;
			}
		}
		
		return result;
	}
	
	public void checkRight(Max max)
	{
		Cell cellA = new Cell(max.x, (max.y + max.height) - 1);
		Cell cellB = new Cell(max.x, max.y);
		
		if ((!checkCellRight(max, cellA)) && (!cellA.equals(cellB)))
		{
			checkCellRight(max, cellB);
		}
	}
	
	private boolean checkCellRight(Max max, Cell cell)
	{
		boolean result = false;
		
		if (getBlock(cell.i, cell.j) == null)
		{
			BuildingBlock blockRight = getBlock(cell.i + 1, cell.j);
			
			if ((blockRight != null) && blocksIntersect(max, blockRight))
			{
				max.x = blockRight.x - max.width;
				result = true;
			}
		}
		
		return result;
	}
	
	private boolean blocksIntersect(Max max, BuildingBlock block)
	{
		boolean condition1 = (max.x + max.width) <= block.x;
		boolean condition2 = (block.x + block.width) <= max.x;
		boolean condition3 = (max.y + max.height) <= block.y;
		boolean condition4 = (block.y + block.height) <= max.y;
		
		return ((!condition1) && (!condition2) && (!condition3) && (!condition4));
	}
	
	private BuildingBlock getBlock(int i, int j)
	{
		BuildingBlock result = null;
		
		try
		{
			result = this.blocks[i][j];
		}
		catch (Exception e)
		{
		}
		
		return result;
	}
}