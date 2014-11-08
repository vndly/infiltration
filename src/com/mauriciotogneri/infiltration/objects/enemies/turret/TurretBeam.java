package com.mauriciotogneri.infiltration.objects.enemies.turret;

import com.mauriciotogneri.infiltration.utils.Resources;
import com.misty.kernel.Process;

public class TurretBeam extends Process
{
	private final Turret turret;
	private final float speed;
	private final int directionX;
	private final int directionY;
	
	public TurretBeam(Turret turret, float x, float y, float angle, float speed, int directionX, int directionY)
	{
		super(true, true);
		
		this.x = x;
		this.y = y;
		this.angle = angle;
		this.z = 2;
		
		this.speed = speed;
		this.directionX = directionX;
		this.directionY = directionY;
		
		this.turret = turret;
		
		setImage(Resources.Images.Enemies.Turret.BEAM);
	}
	
	@Override
	public void update(float delta)
	{
		this.x += (this.speed * delta) * this.directionX;
		this.y += (this.speed * delta) * this.directionY;
		
		this.turret.checkCollision(this);
	}
}