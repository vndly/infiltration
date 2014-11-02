package com.mauriciotogneri.wakeupmax.objects;

import com.misty.math.Rectangle;

public class Block extends Rectangle
{
	// public final float x;
	// public final float y;
	// public final int width = Level.BLOCK_SIZE;
	// public final int height = Level.BLOCK_SIZE;
	
	public Block(float x, float y)
	{
		super(x, y, Level.BLOCK_SIZE, Level.BLOCK_SIZE);
		
		// this.x = (int)Math.floor(x / Level.BLOCK_SIZE);
		// this.y = (int)Math.floor(y / Level.BLOCK_SIZE);
		
		// this.x = x;
		// this.y = y;
	}
	
	// public boolean equals(Cell cell)
	// {
	// return ((cell.x == this.x) && (cell.y == this.y));
	// }
}