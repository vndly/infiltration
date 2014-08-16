package com.mauriciotogneri.wakeupmax.objects;

import com.mauriciotogneri.wakeupmax.R;
import com.misty.kernel.Process;

public class Sky extends Process
{
	public Sky()
	{
		super(false, false);
		
		this.x = 0;
		this.y = 96;
		this.z = 0;
		
		setSprite(R.raw.sky_1);
	}
}