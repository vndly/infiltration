package com.mauriciotogneri.wakeupmax.objects;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import com.misty.utils.Assets;

public class Building
{
	public static int BLOCK_SIZE = 32;
	private static int BUILDING_SIZE_X = 21;
	private static int BUILDING_SIZE_Y = 16;
	private final BuildingBlock[][] blocks;
	
	public Building(String path)
	{
		this.blocks = new BuildingBlock[Building.BUILDING_SIZE_X][Building.BUILDING_SIZE_Y];
		
		fillBlocks(path);
	}
	
	private void fillBlocks(String path)
	{
		InputStream inputstream = null;
		
		try
		{
			inputstream = Assets.getInputStream(path);
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputstream));
			String line = "";
			
			while ((line = reader.readLine()) != null)
			{
				if (!line.isEmpty())
				{
					String[] values = line.split(",");
					
					int i = Integer.parseInt(values[0]);
					int j = Integer.parseInt(values[1]);
					
					addBlock(i, j);
				}
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			Assets.close(inputstream);
		}
	}
	
	private void addBlock(int i, int j)
	{
		BuildingBlock ground = new BuildingBlock(i * Building.BLOCK_SIZE, j * Building.BLOCK_SIZE);
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