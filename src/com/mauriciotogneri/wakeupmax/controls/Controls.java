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
		this.input.left = isPressed(this.buttonLeft.x, this.buttonLeft.x + this.buttonLeft.width, this.buttonLeft.y, this.buttonLeft.y + this.buttonLeft.height);
		this.input.right = isPressed(this.buttonRight.x, this.buttonRight.x + this.buttonRight.width, this.buttonRight.y, this.buttonRight.y + this.buttonRight.height);
		this.input.up = isPressed(this.buttonUp.x, this.buttonUp.x + this.buttonUp.width, this.buttonUp.y, this.buttonUp.y + this.buttonUp.height);

		return this.input;
	}
}