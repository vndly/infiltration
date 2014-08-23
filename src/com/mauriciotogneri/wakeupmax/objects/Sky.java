package com.mauriciotogneri.wakeupmax.objects;

import com.mauriciotogneri.wakeupmax.Resources;
import com.misty.graphics.Camera;
import com.misty.kernel.Process;

public class Sky extends Process
{
	public Sky()
	{
		super(false, false);
		
		this.x = 0;
		this.y = 96;
		this.z = 0;
		
		setImage(Resources.Images.Levels.SKY);
	}
	
	public void update(Camera camera)
	{
		this.x = camera.x - (camera.x / 10);
	}
}