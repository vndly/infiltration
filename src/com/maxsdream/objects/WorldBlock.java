package com.maxsdream.objects;

import com.misty.kernel.Process;

public class WorldBlock extends Process
{
	public WorldBlock(int x, int y, int sprite)
	{
		super(false, false);
		
		this.x = x;
		this.y = y;
		this.z = 1;
		
		setSprite(sprite);
	}
}