package com.mauriciotogneri.wakeupmax.objects;

public class Cell
{
	public final int i;
	public final int j;
	
	public Cell(float x, float y)
	{
		this.i = (int)Math.floor(x / Level.BLOCK_SIZE);
		this.j = (int)Math.floor(y / Level.BLOCK_SIZE);
	}
	
	public boolean equals(Cell cell)
	{
		return ((cell.i == this.i) && (cell.j == this.j));
	}
}