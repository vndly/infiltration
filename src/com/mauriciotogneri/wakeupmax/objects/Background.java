package com.mauriciotogneri.wakeupmax.objects;

import com.misty.kernel.Process;

public class Background extends Process
{
	public Background(int z, String image)
	{
		super(false, false);
		
		this.z = z;
		
		setImage(image);
	}
}