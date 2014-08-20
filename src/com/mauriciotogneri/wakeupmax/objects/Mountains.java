package com.mauriciotogneri.wakeupmax.objects;

import com.mauriciotogneri.wakeupmax.R;
import com.misty.graphics.Camera;
import com.misty.kernel.Process;

public class Mountains extends Process
{
	public Mountains()
	{
		super(false, false);

		this.x = 0;
		this.y = 96;
		this.z = 1;

		setSprite(R.raw.mountains_1);
	}

	public void update(Camera camera)
	{
		this.x = camera.x - (camera.x / 2);
	}
}