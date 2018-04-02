package com.glanexdev.blog.adsscript.parser;
import android.os.*;
import android.support.v7.app.*;
import android.support.v7.widget.*;
import android.view.*;
import android.widget.*;
import com.google.android.gms.ads.*;
import java.text.*;
import java.util.*;

import android.support.v7.widget.Toolbar;
/*
* © 02/03/2018 created by Hendriyawan
* Android app for adsense script parser
*
*/

public class AboutActivity extends AppCompatActivity
{
	private TextView copyright;
	private LinearLayout mLayoutViewAdView;
	private AdView mAdView;
	private AdRequest mAdRequestBanner;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about_layout);
		//toolbar
		setupToolbar(R.id.toolbar);
		
		//years build
		//copyrights
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		Date date = new Date();
		String years = sdf.format(date.getTime());
		
		copyright = (TextView)findViewById(R.id.copyright);
		copyright.setText(years.equals("2018") ? "\u00a9 2018 glanexdev" : "\u00a9 2018 - "+years+" "+"glanexdev");
		
		//admob ads banner
		mLayoutViewAdView = (LinearLayout)findViewById(R.id.layout_ads_banner);
		mAdView = new AdView(this);
		mAdView.setAdSize(AdSize.SMART_BANNER);
		mAdView.setAdUnitId(unitIdBanner());
		mAdView.loadAd(new AdRequest.Builder().build());
		mLayoutViewAdView.addView(mAdView);
		
	}
	
	/*setup toolbar
	*/
	private void setupToolbar(int id){
		Toolbar toolbar = (Toolbar)findViewById(id);
		setSupportActionBar(toolbar);
		getSupportActionBar().setTitle("About App");
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		//action back button / up button
		toolbar.setNavigationOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
				finish();
			}
		});
	}
	/* admob unit id banner*/
	public String unitIdBanner(){
		return UnitId.BANNER;
	}
}
