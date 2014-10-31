package com.mauriciotogneri.wakeupmax.objects;

import com.misty.kernel.Process;

public class Button extends Process
{
	public static final int SIZE = 64;

	public Button(int x, int y, String image)
	{
		super(false, false);

		this.x = x;
		this.y = y;
		this.z = 3;
		this.fixedPosition = true;

		setImage(image);
	}
}