package com.mauriciotogneri.wakeupmax.controls;

import com.mauriciotogneri.wakeupmax.activities.Main;
import com.mauriciotogneri.wakeupmax.utils.Resources;

public class Controls
{
	private final Button buttonLeft;
	private final Button buttonRight;
	private final Button buttonUp;

	private final Input input = new Input();
	
	public Controls()
	{
		this.buttonLeft = new Button(10, 10, Resources.Images.Controls.ARROW_LEFT);
		this.buttonLeft.start();
		
		this.buttonRight = new Button(Button.SIZE + 40, 10, Resources.Images.Controls.ARROW_RIGHT);
		this.buttonRight.start();
		
		this.buttonUp = new Button(Main.RESOLUTION_X - Button.SIZE - 15, 10, Resources.Images.Controls.ARROW_UP);
		this.buttonUp.start();
	}

	public Input getInput()
	{
		this.input.left = this.buttonLeft.isPressed();
		this.input.right = this.buttonRight.isPressed();
		this.input.up = this.buttonUp.isPressed();
		
		return this.input;
	}
}