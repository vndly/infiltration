package com.mauriciotogneri.wakeupmax.objects;

import com.mauriciotogneri.wakeupmax.Resources;
import com.misty.graphics.Camera;
import com.misty.kernel.Process;

public class Mountains extends Process
{
	public Mountains()
	{
		super(false, false);
		
		this.x = 1;
		this.y = 4 * World.BLOCK_SIZE;
		this.z = 1;
		
		setImage(Resources.Images.Levels.MOUNTAINS);
	}
	
	public void update(Camera camera)
	{
		this.x = camera.x - (camera.x / 2);
	}
}