package com.mauriciotogneri.infiltration.objects.levels;

import com.misty.kernel.Process;

public class ViewPoint extends Process
{
	private final int maxX;
	
	public ViewPoint(int maxX)
	{
		super(false, false);
		
		this.maxX = maxX;
	}
	
	public void update(Process proces)
	{
		this.camera.x = (proces.x - (getResolutionX() / 2)) + proces.width;
		this.camera.y = (proces.y - (getResolutionY() / 2)) + proces.height;
		
		if (this.camera.x < 0)
		{
			this.camera.x = 0;
		}
		
		int limitX = this.maxX - this.camera.width;
		
		if (this.camera.x > limitX)
		{
			this.camera.x = limitX;
		}
		
		if (this.camera.y < 0)
		{
			this.camera.y = 0;
		}
	}
}