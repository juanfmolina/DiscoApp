package co.edu.udea.compumovil.gr10.discoapp;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class EventActivity extends Activity {

	private ListView lvLista;
	private List<Evento> listaEventos;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event);
		init();
		new EventoTask().execute();
		CustomAdapter customAdapter = new CustomAdapter(
				getApplicationContext(), listaEventos);
		lvLista = (ListView) findViewById(R.id.listEvents);
		lvLista.setAdapter(customAdapter);
		
	
	}

	private void init() {

		listaEventos = new ArrayList<Evento>();
		Evento evento = new Evento();
		evento.setTitulo("Concierto Yelsid");
		evento.setFecha("1 de noviembre");
		evento.setDescripcion("El mejor show en tu Discoteca");
		evento.setImagen(BitmapFactory.decodeResource(getResources(),
				R.drawable.evento1));
		listaEventos.add(evento);

		evento = new Evento();
		evento.setTitulo("Fiesta de Halloween");
		evento.setFecha("31 de octubre");
		evento.setDescripcion("Se premiaran el mejor disfraz de la noche");
		evento.setImagen(BitmapFactory.decodeResource(getResources(),
				R.drawable.evento2));
		listaEventos.add(evento);

		evento = new Evento();
		evento.setTitulo("Streapers");
		evento.setFecha("8 de noviembre");
		evento.setDescripcion("Las bailarinas mas sexies");
		evento.setImagen(BitmapFactory.decodeResource(getResources(),
				R.drawable.evento3));
		listaEventos.add(evento);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.event, menu);
		getMenuInflater().inflate(R.menu.menuprincipal, menu);
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
		switch (item.getItemId()) {
		case R.id.cerrarSesion:
			SharedPreferences pref = getSharedPreferences("MyPref", 0);
		    final SharedPreferences.Editor edit = pref.edit();
		    edit.putString("usuario", "");
		    edit.commit();
		    Intent act = new Intent(getApplicationContext(), LoginActivity.class);
		    startActivity(act);
			break;

		default:
			break;
		}
		
		return super.onOptionsItemSelected(item);
	}

	private class EventoTask extends AsyncTask<Void, Void, Void> {
		
		
		
		private static final String TAG = "EventTask";
		private String Content;
		private String Error = null;
		private ProgressDialog Dialog = new ProgressDialog(EventActivity.this);

		String data = "";
		
		@Override
		protected void onPreExecute() {
			// super.onPreExecute();
			// Start Progress Dialog (Message)
			Dialog.setMessage("Please wait..");
			Dialog.show();
		}

		@Override
		protected Void doInBackground(Void... params) {
			
			if (!ConexiónInternet.verificaConexion(getApplicationContext())) {
			    Toast.makeText(getBaseContext(),
			            "No tienes acceso a internet, Comprueba tu conexión y vuelve a intentarlo.", Toast.LENGTH_LONG)
			            .show();
			    
			}
			
			try {
				data = new EventoHttpCliente().getEventosData();
				Log.v(TAG, "Info:" + data);
			} catch (Exception ex) {
				Error = ex.getMessage();
			}
			
			return null;
			
		}
		
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if (Dialog.isShowing())
				Dialog.dismiss();
		}
		
		
		
	}
}
