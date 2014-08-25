package com.mauriciotogneri.wakeupmax.objects;

import com.mauriciotogneri.wakeupmax.Resources;
import com.mauriciotogneri.wakeupmax.Resources.Images.Backgrounds;
import com.misty.graphics.Camera;
import com.misty.kernel.Process;

public class Background extends Process
{
	private final int initialX;
	private final int initialY;
	private final int h;
	private final int v;
	
	public Background(int x, int y, int z, int h, int v, String image)
	{
		super(false, false);
		
		this.initialX = x * World.BLOCK_SIZE;
		this.initialY = y * World.BLOCK_SIZE;
		
		this.x = this.initialX;
		this.y = this.initialY;
		this.z = z;
		
		this.h = h;
		this.v = v;
		
		setImage(Resources.getImage(Backgrounds.class, image));
	}
	
	public void update(Camera camera)
	{
		this.x = camera.x - (camera.x / this.h) + this.initialX;
		this.y = camera.y - (camera.y / this.v) + this.initialY;
	}
}