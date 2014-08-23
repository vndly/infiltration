package com.mauriciotogneri.wakeupmax.objects;

import com.misty.kernel.Process;

public class Button extends Process
{
	public final int initialX;
	public final int initialY;
	public static final int SIZE = 64;

	public Button(int x, int y, String image)
	{
		super(false, false);

		this.initialX = x;
		this.initialY = y;
		this.z = 3;

		setImage(image);
	}
}