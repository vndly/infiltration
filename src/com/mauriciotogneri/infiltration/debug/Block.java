package com.mauriciotogneri.infiltration.debug;

import com.misty.kernel.Process;

public class Block extends Process
{
	
	public Block(float x, float y)
	{
		super(false, false);
		
		this.x = x;
		this.y = y;
		this.z = 5;
		
		setImage("images/debug/block.png");
	}
}