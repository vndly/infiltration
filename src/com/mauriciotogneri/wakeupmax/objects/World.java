package com.mauriciotogneri.wakeupmax.objects;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import com.mauriciotogneri.wakeupmax.utils.Resources;
import com.misty.utils.Assets;

public class World
{
	public static int BLOCK_SIZE = 32;
	private static int WORLD_SIZE_X = 30;
	private static int WORLD_SIZE_Y = 10;
	private final WorldBlock[][] blocks;
	
	public World()
	{
		this.blocks = new WorldBlock[World.WORLD_SIZE_X][World.WORLD_SIZE_Y];
	}
	
	public void create(String path)
	{
		InputStream inputstream = null;
		
		try
		{
			inputstream = Assets.getInputStream(path);
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputstream));
			String line = "";
			
			while ((line = reader.readLine()) != null)
			{
				String[] values = line.split(",");
				
				int i = Integer.parseInt(values[0]);
				int j = Integer.parseInt(values[1]);
				
				addBlock(i, j, Resources.Images.Blocks.STONE);
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
		
		// for (int i = 0; i < 32; i++)
		// {
		// for (int j = 0; j < 2; j++)
		// {
		// addBlock(i, j, Resources.Images.Levels.GROUND_1);
		// }
		// }
		//
		// for (int i = 0; i < 32; i++)
		// {
		// addBlock(i, 2, Resources.Images.Levels.GROUND_2);
		// }
		
		// addBlock(6, 3, Resources.Images.Levels.GROUND_1);
		// addBlock(7, 3, Resources.Images.Levels.GROUND_1);
		// addBlock(8, 3, Resources.Images.Levels.GROUND_1);
		// addBlock(6, 4, Resources.Images.Levels.GROUND_2);
		// addBlock(7, 4, Resources.Images.Levels.GROUND_2);
		// addBlock(8, 4, Resources.Images.Levels.GROUND_2);
		//
		// addBlock(23, 3, Resources.Images.Levels.GROUND_2);
		// addBlock(24, 4, Resources.Images.Levels.GROUND_2);
		// addBlock(25, 3, Resources.Images.Levels.GROUND_2);
		// addBlock(24, 3, Resources.Images.Levels.GROUND_1);
		//
		// addBlock(14, 3, Resources.Images.Levels.GROUND_2);
		//
		// addBlock(15, 3, Resources.Images.Levels.GROUND_1);
		// addBlock(15, 4, Resources.Images.Levels.GROUND_2);
		//
		// addBlock(16, 3, Resources.Images.Levels.GROUND_1);
		// addBlock(16, 4, Resources.Images.Levels.GROUND_1);
		// addBlock(16, 5, Resources.Images.Levels.GROUND_2);
		//
		// addBlock(17, 3, Resources.Images.Levels.GROUND_1);
		// addBlock(17, 4, Resources.Images.Levels.GROUND_1);
		// addBlock(17, 5, Resources.Images.Levels.GROUND_1);
		// addBlock(17, 6, Resources.Images.Levels.GROUND_2);
		//
		// addBlock(18, 3, Resources.Images.Levels.GROUND_1);
		// addBlock(18, 4, Resources.Images.Levels.GROUND_1);
		// addBlock(18, 5, Resources.Images.Levels.GROUND_1);
		// addBlock(18, 6, Resources.Images.Levels.GROUND_1);
		// addBlock(18, 7, Resources.Images.Levels.GROUND_2);
		//
		// addBlock(19, 3, Resources.Images.Levels.GROUND_1);
		// addBlock(19, 4, Resources.Images.Levels.GROUND_1);
		// addBlock(19, 5, Resources.Images.Levels.GROUND_2);
		//
		// addBlock(20, 3, Resources.Images.Levels.GROUND_2);
	}
	
	private void addBlock(int i, int j, String sprite)
	{
		WorldBlock ground = new WorldBlock(i * World.BLOCK_SIZE, j * World.BLOCK_SIZE, sprite);
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
			WorldBlock blockBottom = getBlock(cell.i, cell.j - 1);
			
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
			WorldBlock blockTop = getBlock(cell.i, cell.j + 1);
			
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
			WorldBlock blockLeft = getBlock(cell.i - 1, cell.j);
			
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
			WorldBlock blockRight = getBlock(cell.i + 1, cell.j);
			
			if ((blockRight != null) && blocksIntersect(max, blockRight))
			{
				max.x = blockRight.x - max.width;
				result = true;
			}
		}
		
		return result;
	}
	
	private boolean blocksIntersect(Max max, WorldBlock block)
	{
		boolean condition1 = (max.x + max.width) <= block.x;
		boolean condition2 = (block.x + block.width) <= max.x;
		boolean condition3 = (max.y + max.height) <= block.y;
		boolean condition4 = (block.y + block.height) <= max.y;
		
		return ((!condition1) && (!condition2) && (!condition3) && (!condition4));
	}
	
	private WorldBlock getBlock(int i, int j)
	{
		WorldBlock result = null;
		
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