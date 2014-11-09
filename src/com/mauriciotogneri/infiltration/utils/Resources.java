package com.mauriciotogneri.infiltration.utils;

public class Resources
{
	public static class Images
	{
		private static final String ROOT_IMAGES = "images/";
		
		public static class Protagonist
		{
			private static final String ROOT_PROTAGONIST = Images.ROOT_IMAGES + "protagonist/";
			
			public static final String CHARACTER_IDLE = Protagonist.ROOT_PROTAGONIST + "idle.png";
			public static final String CHARACTER_JUMPING = Protagonist.ROOT_PROTAGONIST + "jumping.png";
			public static final String CHARACTER_FALLING = Protagonist.ROOT_PROTAGONIST + "falling.png";
			public static final String CHARACTER_RUNNING_1 = Protagonist.ROOT_PROTAGONIST + "running_1.png";
			public static final String CHARACTER_RUNNING_2 = Protagonist.ROOT_PROTAGONIST + "running_2.png";
			public static final String CHARACTER_RUNNING_3 = Protagonist.ROOT_PROTAGONIST + "running_3.png";
			public static final String CHARACTER_RUNNING_4 = Protagonist.ROOT_PROTAGONIST + "running_4.png";
			public static final String CHARACTER_RUNNING_5 = Protagonist.ROOT_PROTAGONIST + "running_5.png";
			public static final String CHARACTER_RUNNING_6 = Protagonist.ROOT_PROTAGONIST + "running_6.png";
		}
		
		public static class Enemies
		{
			private static final String ROOT_ENEMIES = Images.ROOT_IMAGES + "enemies/";
			
			public static class Laser
			{
				private static final String ROOT_LASER = Enemies.ROOT_ENEMIES + "laser/";
				
				public static final String BASE = Laser.ROOT_LASER + "laser_base.png";
				public static final String BEAM = Laser.ROOT_LASER + "laser_beam.png";
			}
			
			public static class Turret
			{
				private static final String ROOT_TURRET = Enemies.ROOT_ENEMIES + "turret/";
				
				public static final String BASE = Turret.ROOT_TURRET + "turret_base.png";
				public static final String BEAM = Turret.ROOT_TURRET + "turret_beam.png";
			}
		}
		
		public static class Levels
		{
			private static final String ROOT_LEVELS = Images.ROOT_IMAGES + "levels/";
			
			public static class Buildings
			{
				private static final String ROOT_BUILDINGS = Levels.ROOT_LEVELS + "buildings/";
				
				public static final String BUILDING_1 = Buildings.ROOT_BUILDINGS + "building_1.png";
			}
			
			public static class Door
			{
				private static final String ROOT_DOOR = Levels.ROOT_LEVELS + "door/";
				
				public static final String DOOR_CLOSED = Door.ROOT_DOOR + "door_closed.png";
				public static final String DOOR_OPENING_1 = Door.ROOT_DOOR + "door_opening_1.png";
				public static final String DOOR_OPENING_2 = Door.ROOT_DOOR + "door_opening_2.png";
				public static final String DOOR_OPENING_3 = Door.ROOT_DOOR + "door_opening_3.png";
			}
			
			public static class Switch
			{
				private static final String ROOT_SWITCH = Levels.ROOT_LEVELS + "switch/";
				
				public static final String SWITCH_ON = Switch.ROOT_SWITCH + "switch_on.png";
				public static final String SWITCH_OFF = Switch.ROOT_SWITCH + "switch_off.png";
			}
		}
		
		public static class Controls
		{
			private static final String ROOT_CONTROLS = Images.ROOT_IMAGES + "controls/";
			
			public static final String ARROW = Controls.ROOT_CONTROLS + "arrow.png";
		}
	}
	
	public static class Levels
	{
		private static final String ROOT_LEVELS = "levels/";
		
		public static final String LEVEL_1 = Levels.ROOT_LEVELS + "level_1.txt";
	}
	
	public static class Audio
	{
		private static final String ROOT_AUDIO = "audio/";
		
		public static class Music
		{
			private static final String ROOT_MUSIC = Audio.ROOT_AUDIO + "music/";
			
			public static final String MUSIC = Music.ROOT_MUSIC + "music.ogg";
		}
		
		public static class Sound
		{
			private static final String ROOT_SOUND = Audio.ROOT_AUDIO + "sounds/";
			
			public static final String JUMP = Sound.ROOT_SOUND + "jump.ogg";
			
			public static class Enemies
			{
				private static final String ROOT_ENEMIES = Sound.ROOT_SOUND + "enemies/";
				
				public static final String TURRET_BEAM = Enemies.ROOT_ENEMIES + "turret_beam.ogg";
			}
		}
	}
}