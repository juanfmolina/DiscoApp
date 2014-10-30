package co.edu.udea.compumovil.gr10.discoapp;

import android.app.Activity;
import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;

public class TabActivity extends ActivityGroup {

	private TabHost myTabHost;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tab);
		myTabHost = (TabHost) findViewById(android.R.id.tabhost);
		myTabHost.setup(this.getLocalActivityManager());
		TabHost.TabSpec tabSpecMusic = myTabHost.newTabSpec("Music");
		tabSpecMusic.setIndicator("Canciones");
		Intent cancionesIntent= new Intent(this, MusicActivity.class);
		tabSpecMusic.setContent(cancionesIntent);
		
		TabHost.TabSpec tabSpecOpinion = myTabHost.newTabSpec("Opinion");
		Intent opinionIntent = new Intent(this, OpinionActivity.class);
		tabSpecOpinion.setContent(opinionIntent);
		tabSpecOpinion.setIndicator("Opinión");
		myTabHost.addTab(tabSpecMusic);
		myTabHost.addTab(tabSpecOpinion);
		myTabHost.setCurrentTab(0);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tab, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
