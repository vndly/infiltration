package com.mauriciotogneri.infiltration.activities;

import android.app.Application;
import android.os.StrictMode;

public class Infiltration extends Application
{
	@Override
	public void onCreate()
	{
		super.onCreate();
		
		StrictMode.ThreadPolicy.Builder threadBuilder = new StrictMode.ThreadPolicy.Builder();
		threadBuilder.detectAll();
		threadBuilder.penaltyLog();
		StrictMode.setThreadPolicy(threadBuilder.build());
		
		StrictMode.VmPolicy.Builder vmBuilder = new StrictMode.VmPolicy.Builder();
		vmBuilder.detectAll();
		vmBuilder.penaltyLog();
		StrictMode.setVmPolicy(vmBuilder.build());
	}
}