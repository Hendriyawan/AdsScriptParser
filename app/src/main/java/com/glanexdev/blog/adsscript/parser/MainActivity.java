package com.glanexdev.blog.adsscript.parser;
import android.content.*;
import android.os.*;
import android.support.v7.app.*;
import android.support.v7.widget.*;
import android.view.*;
import android.widget.*;
import com.google.android.gms.ads.*;

import android.support.v7.widget.Toolbar;
/*
* © 02/03/2018 created by Hendriyawan
* Android app for adsense script parser
*
*/
public class MainActivity extends AppCompatActivity 
{
	private AppCompatButton mButtonParse;
	private AppCompatEditText mEditCodeParser;
	private AppCompatEditText mEditParsedCode;
	
	private ScriptParser mParser;
	private LinearLayout mLayoutViewAdView;
	private AdView mAdView;
	private InterstitialAd mInterstitialAd;
	private AdRequest mAdRequestInterstitial;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		
		//android toolbar
		setupToolbar(R.id.toolbar);
		
		/** ADMOB
		*/
		MobileAds.initialize(this, UnitId.APP_UNIT_ID);
		//AdView admob / ads banner
		mLayoutViewAdView = (LinearLayout)findViewById(R.id.layout_ads_banner);
		mAdView = new AdView(this);
		mAdView.setAdSize(AdSize.SMART_BANNER);
		mAdView.setAdUnitId(unitIdBanner());
		mAdView.loadAd(new AdRequest.Builder().build());
		mLayoutViewAdView.addView(mAdView);
		
		//script parser
		mParser = new ScriptParser();
		
		//edit code
		mEditCodeParser = (AppCompatEditText)findViewById(R.id.edit_code_to_parse);
		
		//button parser
		mButtonParse = (AppCompatButton)findViewById(R.id.button_do_parse);
		mButtonParse.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
				//check if code to parse is empty
				if(mEditCodeParser.getText().toString().isEmpty()){
					Toast.makeText(MainActivity.this, "Enter code to parse !", Toast.LENGTH_LONG).show();
				}
				else {
					//admob interstitial
					mInterstitialAd = new InterstitialAd(MainActivity.this);
					mInterstitialAd.setAdUnitId(unitIdIntersttitial());
					mAdRequestInterstitial = new AdRequest.Builder().build();
					mInterstitialAd.loadAd(mAdRequestInterstitial);
					mInterstitialAd.setAdListener(new AdListener(){
							@Override
							public void onAdLoaded(){
								mInterstitialAd.show();
							}
						});
					
					mParser.doParser(mEditCodeParser.getText().toString());
					//show dialog result parsed code
					showDialogParsedCode();
					
				}
			}
		});
    }
	
	/* Android Options Menu*/
	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		getMenuInflater().inflate(R.menu.menu, menu);
		return super.onCreateOptionsMenu(menu);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch(item.getItemId()){
			//open about activity
			case R.id.about_app:
				startActivity(new Intent(MainActivity.this, AboutActivity.class));
				break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	/*setup Toolbar
	*/
	
	private void setupToolbar(int id){
		Toolbar toolbar = (Toolbar)findViewById(id);
		setSupportActionBar(toolbar);
		//set title app name
		getSupportActionBar().setTitle(" Ads Script Parser");
		getSupportActionBar().setIcon(R.drawable.ic_markup);
	}
	
	
	//dialog show parsed code
	private void showDialogParsedCode(){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Parsed Code");
		
		View view = getLayoutInflater().inflate(R.layout.dialog_parsed_code_layout, null);
		mEditParsedCode = (AppCompatEditText)view.findViewById(R.id.edit_parsed_code);
		builder.setView(view);
		mEditParsedCode.setText(mParser.getParsedCode());
		
		//set positive button / copy
		builder.setPositiveButton("COPY", new DialogInterface.OnClickListener(){
			@Override
			public void onClick(DialogInterface dialog, int id){
				setToClipboard(mEditParsedCode.getText().toString());
			}
		});
		
		//set negative button / cancel
		builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener(){
			@Override
			public void onClick(DialogInterface dialog, int id){
				dialog.dismiss();
			}
		});
		
		AlertDialog dialog = builder.create();
		dialog.show();
	}
	
	//method copy to clipboard
	public void setToClipboard(String content){
		ClipboardManager clipboard = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
		ClipData clip = ClipData.newPlainText("Parsed Code", content);
		clipboard.setPrimaryClip(clip);
		Toast.makeText(this, "Copied to Clipboard", Toast.LENGTH_LONG).show();
	}
	
	
	//admob unit id banner
	public String unitIdBanner(){
		return UnitId.BANNER;
	}
	//admob unit id interstitial
	public String unitIdIntersttitial(){
		return UnitId.INTERSTITIAL;
	}
}
