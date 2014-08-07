package com.maxsdream.objects;

import com.maxsdream.R;

public class World
{
	public static int BLOCK_SIZE = 32;
	private static int WORLD_SIZE_X = 100;
	private static int WORLD_SIZE_Y = 20;
	private final WorldBlock[][] blocks;
	
	public World()
	{
		this.blocks = new WorldBlock[World.WORLD_SIZE_X][World.WORLD_SIZE_Y];
	}
	
	public void create()
	{
		for (int i = 0; i < 32; i++)
		{
			for (int j = 0; j < 2; j++)
			{
				addBlock(i, j, R.raw.ground_1);
			}
		}
		
		for (int i = 0; i < 32; i++)
		{
			addBlock(i, 2, R.raw.ground_2);
		}
		
		addBlock(6, 3, R.raw.ground_1);
		addBlock(7, 3, R.raw.ground_1);
		addBlock(8, 3, R.raw.ground_1);
		addBlock(6, 4, R.raw.ground_2);
		addBlock(7, 4, R.raw.ground_2);
		addBlock(8, 4, R.raw.ground_2);
		
		addBlock(23, 3, R.raw.ground_2);
		addBlock(24, 4, R.raw.ground_2);
		addBlock(25, 3, R.raw.ground_2);
		addBlock(24, 3, R.raw.ground_1);
		
		addBlock(14, 3, R.raw.ground_2);
		
		addBlock(15, 3, R.raw.ground_1);
		addBlock(15, 4, R.raw.ground_2);
		
		addBlock(16, 3, R.raw.ground_1);
		addBlock(16, 4, R.raw.ground_1);
		addBlock(16, 5, R.raw.ground_2);
		
		addBlock(17, 3, R.raw.ground_1);
		addBlock(17, 4, R.raw.ground_1);
		addBlock(17, 5, R.raw.ground_1);
		addBlock(17, 6, R.raw.ground_2);
		
		addBlock(18, 3, R.raw.ground_1);
		addBlock(18, 4, R.raw.ground_1);
		addBlock(18, 5, R.raw.ground_1);
		addBlock(18, 6, R.raw.ground_1);
		addBlock(18, 7, R.raw.ground_2);
		
		addBlock(19, 3, R.raw.ground_1);
		addBlock(19, 4, R.raw.ground_1);
		addBlock(19, 5, R.raw.ground_2);
		
		addBlock(20, 3, R.raw.ground_2);
	}
	
	private void addBlock(int i, int j, int sprite)
	{
		WorldBlock ground = new WorldBlock(i * World.BLOCK_SIZE, j * World.BLOCK_SIZE, sprite);
		ground.start();
		this.blocks[i][j] = ground;
	}
	
	public void checkBottom(Mario mario)
	{
		Cell cellA = new Cell(mario.x, (mario.y + mario.height) - 1);
		Cell cellB = new Cell((mario.x + mario.width) - 1, (mario.y + mario.height) - 1);
		
		if ((!checkCellBottom(mario, cellA)) && (!cellA.equals(cellB)))
		{
			checkCellBottom(mario, cellB);
		}
	}
	
	private boolean checkCellBottom(Mario mario, Cell cell)
	{
		boolean result = false;
		
		if (getBlock(cell.i, cell.j) == null)
		{
			WorldBlock blockBottom = getBlock(cell.i, cell.j - 1);
			
			if ((blockBottom != null) && blocksIntersect(mario, blockBottom))
			{
				mario.y = blockBottom.y + blockBottom.height;
				mario.setJumping(false);
				result = true;
			}
		}
		
		return result;
	}
	
	public void checkUp(Mario mario)
	{
		Cell cellA = new Cell(mario.x, mario.y);
		Cell cellB = new Cell(mario.x, mario.y);
		
		if ((!checkCellUp(mario, cellA)) && (!cellA.equals(cellB)))
		{
			checkCellUp(mario, cellB);
		}
	}
	
	private boolean checkCellUp(Mario mario, Cell cell)
	{
		boolean result = false;
		
		if (getBlock(cell.i, cell.j) == null)
		{
			WorldBlock blockUp = getBlock(cell.i, cell.j + 1);
			
			if ((blockUp != null) && blocksIntersect(mario, blockUp))
			{
				mario.y = blockUp.y - mario.height;
				result = true;
			}
		}
		
		return result;
	}
	
	public void checkLeft(Mario mario)
	{
		Cell cellA = new Cell((mario.x + mario.width) - 1, (mario.y + mario.height) - 1);
		Cell cellB = new Cell((mario.x + mario.width) - 1, mario.y);
		
		if ((!checkCellLeft(mario, cellA)) && (!cellA.equals(cellB)))
		{
			checkCellLeft(mario, cellB);
		}
	}
	
	private boolean checkCellLeft(Mario mario, Cell cell)
	{
		boolean result = false;
		
		if (getBlock(cell.i, cell.j) == null)
		{
			WorldBlock blockLeft = getBlock(cell.i - 1, cell.j);
			
			if ((blockLeft != null) && blocksIntersect(mario, blockLeft))
			{
				mario.x = blockLeft.x + blockLeft.width;
				result = true;
			}
		}
		
		return result;
	}
	
	public void checkRight(Mario mario)
	{
		Cell cellA = new Cell(mario.x, (mario.y + mario.height) - 1);
		Cell cellB = new Cell(mario.x, mario.y);
		
		if ((!checkCellRight(mario, cellA)) && (!cellA.equals(cellB)))
		{
			checkCellRight(mario, cellB);
		}
	}
	
	private boolean checkCellRight(Mario mario, Cell cell)
	{
		boolean result = false;
		
		if (getBlock(cell.i, cell.j) == null)
		{
			WorldBlock blockRight = getBlock(cell.i + 1, cell.j);
			
			if ((blockRight != null) && blocksIntersect(mario, blockRight))
			{
				mario.x = blockRight.x - mario.width;
				result = true;
			}
		}
		
		return result;
	}
	
	private boolean blocksIntersect(Mario mario, WorldBlock block)
	{
		boolean condition1 = (mario.x + mario.width) <= block.x;
		boolean condition2 = (block.x + block.width) <= mario.x;
		boolean condition3 = (mario.y + mario.height) <= block.y;
		boolean condition4 = (block.y + block.height) <= mario.y;
		
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