package com.mauriciotogneri.wakeupmax.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class Resources
{
	public static class Images
	{
		private static final String ROOT_IMAGES = "images/";

		public static class Sprites
		{
			private static final String ROOT = Images.ROOT_IMAGES + "sprites/";
			
			public static final String CHARACTER_JUMPING_LEFT = Sprites.ROOT + "jumping_left.png";
			public static final String CHARACTER_JUMPING_RIGHT = Sprites.ROOT + "jumping_right.png";
			
			public static final String CHARACTER_RUNNING_LEFT_1 = Sprites.ROOT + "running_left_1.png";
			public static final String CHARACTER_RUNNING_LEFT_2 = Sprites.ROOT + "running_left_2.png";
			public static final String CHARACTER_RUNNING_LEFT_3 = Sprites.ROOT + "running_left_3.png";
			public static final String CHARACTER_RUNNING_LEFT_4 = Sprites.ROOT + "running_left_4.png";
			
			public static final String CHARACTER_RUNNING_RIGHT_1 = Sprites.ROOT + "running_right_1.png";
			public static final String CHARACTER_RUNNING_RIGHT_2 = Sprites.ROOT + "running_right_2.png";
			public static final String CHARACTER_RUNNING_RIGHT_3 = Sprites.ROOT + "running_right_3.png";
			public static final String CHARACTER_RUNNING_RIGHT_4 = Sprites.ROOT + "running_right_4.png";
		}
		
		public static class Backgrounds
		{
			private static final String ROOT = Images.ROOT_IMAGES + "backgrounds/";
			
			public static final String WALL = Backgrounds.ROOT + "wall.png";
		}
		
		public static class Blocks
		{
			private static final String ROOT = Images.ROOT_IMAGES + "blocks/";
			
			public static final String STONE = Blocks.ROOT + "stone.png";
		}

		public static class Controls
		{
			private static final String ROOT = Images.ROOT_IMAGES + "controls/";

			public static final String ARROW_LEFT = Controls.ROOT + "arrow_left.png";
			public static final String ARROW_RIGHT = Controls.ROOT + "arrow_right.png";
			public static final String ARROW_UP = Controls.ROOT + "arrow_up.png";
		}
	}

	public static class Music
	{
		private static final String ROOT = "audio/music/";
		
		public static final String MUSIC = Music.ROOT + "music.ogg";
	}

	public static class Sound
	{
		private static final String ROOT = "audio/sounds/";
		
		public static final String JUMP = Sound.ROOT + "jump.ogg";
	}

	public static String getImage(Class<?> clazz, String name)
	{
		String result = "";
		
		for (Field field : clazz.getDeclaredFields())
		{
			if ((Modifier.isStatic(field.getModifiers())) && (field.getName().equals(name)))
			{
				try
				{
					result = field.get(null).toString();
					break;
				}
				catch (Exception e)
				{
				}
			}
		}
		
		return result;
	}
}