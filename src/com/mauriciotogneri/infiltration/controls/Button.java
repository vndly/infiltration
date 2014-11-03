package com.mauriciotogneri.infiltration.controls;

import com.misty.kernel.Process;

public class Button extends Process
{
	public static final int SIZE = 64;
	
	public Button(float x, float y, float angle, String image)
	{
		super(false, false);
		
		this.x = x;
		this.y = y;
		this.z = 3;
		
		this.angle = angle;
		this.fixedPosition = true;
		
		setImage(image);
	}
}