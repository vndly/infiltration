package com.mauriciotogneri.wakeupmax.objects;

import com.mauriciotogneri.wakeupmax.Main;
import com.mauriciotogneri.wakeupmax.R;
import com.misty.graphics.Animation;
import com.misty.kernel.Process;

public class Mario extends Process
{
	private boolean jumping = false;

	private float accelerationX = 0;
	private float accelerationY = 0;

	private float speedX = 0;
	private float speedY = 0;

	private final World world;

	private final Button buttonLeft;
	private final Button buttonRight;
	private final Button buttonUp;

	private final Sky sky;
	private final Mountains mountains;

	private final Animation animationRight;
	private final Animation animationLeft;

	private boolean facingRight = true;

	private static final int MAX_SPEED_Y = 10;
	private static final int MAX_ACCELERATION_Y_DOWN = -300;
	private static final int GRAVITY = -1100;

	public Mario(World world)
	{
		super(true, true);

		this.world = world;

		this.x = 0;
		this.y = (World.BLOCK_SIZE * 3);
		this.z = 2;

		setSprite(R.raw.mario_running_right_1);

		this.animationRight = new Animation(0.08f, R.raw.mario_running_right_1, R.raw.mario_running_right_2, R.raw.mario_running_right_3);
		this.animationLeft = new Animation(0.08f, R.raw.mario_running_left_1, R.raw.mario_running_left_2, R.raw.mario_running_left_3);

		this.buttonLeft = new Button(5, 5, R.raw.arrow_left);
		this.buttonLeft.start();

		this.buttonRight = new Button(Button.SIZE + 15, 5, R.raw.arrow_right);
		this.buttonRight.start();

		this.buttonUp = new Button(Main.RESOLUTION_X - Button.SIZE - 5, 5, R.raw.arrow_up);
		this.buttonUp.start();

		this.sky = new Sky();
		this.sky.start();

		this.mountains = new Mountains();
		this.mountains.start();

		setJumping(false);
		updateButtons();
		playMusic(R.raw.music);
	}

	@Override
	public void update(float delta)
	{
		processInput(delta);
		updatePosition(delta);
		updateCamera();
		updateButtons();
		
		this.sky.update(this.camera);
		this.mountains.update(this.camera);
	}

	private void updatePosition(float delta)
	{
		float newX = updateX(delta);
		float newY = updateY(delta);

		if (newX != this.x)
		{
			this.x = newX;

			if (this.speedX < 0)
			{
				this.world.checkLeft(this);
			}

			if (this.speedX > 0)
			{
				this.world.checkRight(this);
			}
		}

		if (newY != this.y)
		{
			this.y = newY;

			if (this.speedY < 0)
			{
				this.world.checkBottom(this);
			}

			if (this.speedY > 0)
			{
				this.world.checkUp(this);
			}
		}
	}

	private float updateX(float delta)
	{
		float result = this.x;

		if (this.accelerationX > 0)
		{
			this.accelerationX -= 20;
		}
		else if (this.accelerationX < 0)
		{
			this.accelerationX += 20;
		}

		this.speedX = this.accelerationX * delta;
		result = this.x + this.speedX;

		if (result < 0)
		{
			result = 0;
		}

		int limitX = 1024 - this.width;

		if (result > limitX)
		{
			result = limitX;
		}

		return result;
	}

	private float updateY(float delta)
	{
		float result = this.y;

		applyGravity(delta);

		this.speedY = this.accelerationY * delta;

		if (Math.abs(this.speedY) > Mario.MAX_SPEED_Y)
		{
			this.speedY = Mario.MAX_SPEED_Y * Math.signum(this.speedY);
		}

		result = this.y + this.speedY;

		return result;
	}

	private void applyGravity(float delta)
	{
		if (this.jumping)
		{
			this.accelerationY += Mario.GRAVITY * delta;
		}

		if (this.accelerationY < Mario.MAX_ACCELERATION_Y_DOWN)
		{
			this.accelerationY = Mario.MAX_ACCELERATION_Y_DOWN;
		}
	}

	private void updateCamera()
	{
		this.camera.x = (this.x - (Main.RESOLUTION_X / 2)) + this.width;
		this.camera.y = 0; // this.y - (World.BLOCK_SIZE * 3);

		if (this.camera.x < 0)
		{
			this.camera.x = 0;
		}

		int limitX = 1024 - Main.RESOLUTION_X;

		if (this.camera.x > limitX)
		{
			this.camera.x = limitX;
		}
	}

	private void updateButtons()
	{
		this.buttonLeft.x = this.camera.x + this.buttonLeft.initialX;
		this.buttonLeft.y = this.camera.y + this.buttonLeft.initialY;

		this.buttonRight.x = this.camera.x + this.buttonRight.initialX;
		this.buttonRight.y = this.camera.y + this.buttonRight.initialY;

		this.buttonUp.x = this.camera.x + this.buttonUp.initialX;
		this.buttonUp.y = this.camera.y + this.buttonUp.initialY;
	}

	private void processInput(float delta)
	{
		if (isPressed(this.buttonLeft.initialX, this.buttonLeft.initialX + Button.SIZE, this.buttonLeft.initialY, this.buttonLeft.initialY + Button.SIZE))
		{
			this.accelerationX = -160;
			this.facingRight = false;

			if (!this.jumping)
			{
				setSprite(this.animationLeft.getSprite(delta));
			}
			else
			{
				setSprite(R.raw.mario_jumping_left_1);
			}
		}

		if (isPressed(this.buttonRight.initialX, this.buttonRight.initialX + Button.SIZE, this.buttonRight.initialY, this.buttonRight.initialY + Button.SIZE))
		{
			this.accelerationX = 160;
			this.facingRight = true;

			if (!this.jumping)
			{
				setSprite(this.animationRight.getSprite(delta));
			}
			else
			{
				setSprite(R.raw.mario_jumping_right_1);
			}
		}

		if (isPressed(this.buttonUp.initialX, this.buttonUp.initialX + Button.SIZE, this.buttonUp.initialY, this.buttonUp.initialY + Button.SIZE) && (!this.jumping))
		{
			setJumping(true);

			if (this.facingRight)
			{
				setSprite(R.raw.mario_jumping_right_1);
			}
			else
			{
				setSprite(R.raw.mario_jumping_left_1);
			}
		}

		if ((this.accelerationX == 0.0) && (!this.jumping))
		{
			if (this.facingRight)
			{
				setSprite(R.raw.mario_running_right_1);
			}
			else
			{
				setSprite(R.raw.mario_running_left_1);
			}
		}
	}

	public void setJumping(boolean value)
	{
		this.jumping = value;

		if (this.jumping)
		{
			playSound(R.raw.snd_jump);
			this.accelerationY = 410;
		}
		else
		{
			this.accelerationY = Mario.MAX_ACCELERATION_Y_DOWN;
		}
	}
}