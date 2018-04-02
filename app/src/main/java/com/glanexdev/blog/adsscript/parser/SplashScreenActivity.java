package com.glanexdev.blog.adsscript.parser;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.*;
import android.view.*;
/*
* © 02/03/2018 created by Hendriyawan
* Android app for adsense script parser parser
*
* SpashScreenActivity.java
*/
public class SplashScreenActivity extends AppCompatActivity
{
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_screen_layout);
		
		new Handler().postDelayed(new Runnable(){
			@Override
			public void run(){
				startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
				finish();
			}
		}, 3000);
	}
}
