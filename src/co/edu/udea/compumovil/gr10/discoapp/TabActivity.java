package co.edu.udea.compumovil.gr10.discoapp;

import android.app.Activity;
import android.app.ActivityGroup;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
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
		//Agregando la primera pestaña
		TabHost.TabSpec tabSpecMusic = myTabHost.newTabSpec("Music");
		tabSpecMusic.setIndicator("Canciones");
		Intent cancionesIntent= new Intent(this, MusicActivity.class);
		tabSpecMusic.setContent(cancionesIntent);
		//Agregando la segunda pestaña
		TabHost.TabSpec tabSpecOpinion = myTabHost.newTabSpec("Opinion");
		Intent opinionIntent = new Intent(this, OpinionActivity.class);
		tabSpecOpinion.setContent(opinionIntent);
		tabSpecOpinion.setIndicator("Opinión");
		//Agregando la tercera pestaña
		TabHost.TabSpec tabSpecEventos = myTabHost.newTabSpec("Eventos");
		Intent eventIntent = new Intent(this, EventActivity.class);
		tabSpecEventos.setContent(eventIntent);
		tabSpecEventos.setIndicator("Eventos");
		myTabHost.addTab(tabSpecMusic);
		myTabHost.addTab(tabSpecOpinion);
		myTabHost.addTab(tabSpecEventos);
		myTabHost.setCurrentTab(0);
		
		SharedPreferences pref = getSharedPreferences("MyPref", 0);
		if (pref.getString("usuario", "").equals("")){	
		Intent act = new Intent(getApplicationContext(),LoginActivity.class);
		startActivity(act);
		}
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
		/*
		switch (item.getItemId()) {
		case value:
			
			break;

		default:
			break;
		}
		*/
		
		return super.onOptionsItemSelected(item);
	}
}
