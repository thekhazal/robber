package com.anmark.robberlanguage;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TranslateToActivity extends Activity {

	private String robberLanguage = "";

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		// Save translated text if activity restarted or destroyed
		outState.putString("message", robberLanguage);
	}

	@Override
	protected void onRestoreInstanceState (Bundle savedInstanceState){
		TextView output = (TextView) findViewById(R.id.translateTo_output);	
		output.setText(savedInstanceState.getString("message"));
		System.out.println(savedInstanceState.getString("message"));
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_translate_to);
		// Show the Up button in the action bar.
		setupActionBar();
	}


	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.translate_to, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}


	// Translate input text to Robber Language
	public String translateTo(String input){

		String output = "";

		char con[] = "bcdfghjklmnpqrstvwxz".toCharArray();
		char robber[] = input.toCharArray();	

		for(int i = 0; i < robber.length; i++){
			String add = "";
			for(int y = 0; y < con.length; y++){
				if(robber[i] == con[y]){
					add = "o" + Character.toString(robber[i]); 
					break;
				}
			}
			output = output + Character.toString(robber[i]) + add;
		}

		return output;
	}

	/** Called when the user clicks the Translate button */
	public void TranslateToPressed(View view){

		// Get input text
		EditText input = (EditText) findViewById(R.id.translateTo_input);
		String textToTranslate = input.getText().toString();

		// Clear input
		input.setText("");

		// Display short toast if input is empty
		if(textToTranslate.isEmpty()){
			Context context = getApplicationContext();
			CharSequence text = "There is nothing to translate!";
			int duration = Toast.LENGTH_SHORT;
			Toast toast = Toast.makeText(context, text, duration);
			toast.show();
		}
		// Do translation and display it
		else{
			robberLanguage = translateTo(textToTranslate) + " ";
			TextView output = (TextView) findViewById(R.id.translateTo_output);	
			output.append(robberLanguage);
		}
	}



}
