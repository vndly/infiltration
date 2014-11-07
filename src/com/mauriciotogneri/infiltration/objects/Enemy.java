package com.mauriciotogneri.infiltration.objects;

import com.misty.kernel.Process;

public class Enemy extends Process
{
	public Enemy()
	{
		super(false, true);
		
		this.x = Level.BLOCK_SIZE * 23;
		this.y = Level.BLOCK_SIZE * 13;
		this.z = 2;
		
		setImage("images/debug/enemy.png");
	}
}