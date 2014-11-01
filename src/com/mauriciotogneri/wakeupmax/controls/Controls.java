package com.mauriciotogneri.wakeupmax.controls;

import com.mauriciotogneri.wakeupmax.activities.Main;
import com.mauriciotogneri.wakeupmax.objects.Button;
import com.mauriciotogneri.wakeupmax.utils.Resources;
import com.misty.kernel.Process;

public class Controls extends Process
{
	private final Button buttonLeft;
	private final Button buttonRight;
	private final Button buttonUp;

	private final Input input = new Input();
	
	public Controls()
	{
		super(false, false);
		
		this.buttonLeft = new Button(10, 10, Resources.Images.Controls.ARROW_LEFT);
		this.buttonLeft.start();
		
		this.buttonRight = new Button(Button.SIZE + 40, 10, Resources.Images.Controls.ARROW_RIGHT);
		this.buttonRight.start();
		
		this.buttonUp = new Button(Main.RESOLUTION_X - Button.SIZE - 15, 10, Resources.Images.Controls.ARROW_UP);
		this.buttonUp.start();
	}

	public Input getInput()
	{
		this.input.left = isPressed(this.buttonLeft.x, this.buttonLeft.x + Button.SIZE, this.buttonLeft.y, this.buttonLeft.y + Button.SIZE);
		this.input.right = isPressed(this.buttonRight.x, this.buttonRight.x + Button.SIZE, this.buttonRight.y, this.buttonRight.y + Button.SIZE);
		this.input.up = isPressed(this.buttonUp.x, this.buttonUp.x + Button.SIZE, this.buttonUp.y, this.buttonUp.y + Button.SIZE);
		
		return this.input;
	}
}