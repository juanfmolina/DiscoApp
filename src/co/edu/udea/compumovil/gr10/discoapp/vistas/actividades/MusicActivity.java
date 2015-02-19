package co.edu.udea.compumovil.gr10.discoapp.vistas.actividades;

import java.io.UnsupportedEncodingException;

import org.apache.http.Header;

import co.edu.udea.compumovil.gr10.discoapp.ConexionInternet;
import co.edu.udea.compumovil.gr10.discoapp.R;
import co.edu.udea.compumovil.gr10.discoapp.R.id;
import co.edu.udea.compumovil.gr10.discoapp.R.layout;
import co.edu.udea.compumovil.gr10.discoapp.R.menu;
import co.edu.udea.compumovil.gr10.discoapp.webservicesclient.contract.WebServiceContract;
import co.edu.udea.compumovil.gr10.discoapp.webservicesclient.contract.WebServiceContract.ContractRequest;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MusicActivity extends Activity {
	private EditText cancion;
	private ListView listView;
	private ProgressDialog progress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_music);
		listView = (ListView) findViewById(R.id.songListView);
		String[] songs = new String[] { "Animals-Martin Garrix",
				"& a.m.- J Balvin ft. Farruco", "Tu vicio- Eddy Herrera" };

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, songs);
		listView.setAdapter(adapter);
		progress = new ProgressDialog(this);
		
		SharedPreferences pref = getSharedPreferences("MyPref", 0);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.music, menu);
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
	public void solicitarCancion(View view){
		
		if (!ConexionInternet.verificaConexion(getApplicationContext())) {
		    Toast.makeText(getBaseContext(),
		            "No tienes acceso a internet, Comprueba tu conexión y vuelve a intentarlo.", Toast.LENGTH_LONG)
		            .show();
		    return;
		}
		
		cancion = (EditText)findViewById(R.id.request_song);
		progress.setMessage("Solicitando canción, profavor espere");
		progress.setCancelable(false);
		progress.show();
		RequestParams requestParams = new RequestParams();
		requestParams.add(ContractRequest.USER_ID, "juanf.molina");
		requestParams.add(ContractRequest.USER_REQUEST, cancion.getText().toString());
		AsyncHttpClient client = new AsyncHttpClient();
		 client.get(WebServiceContract.ROOT_PATH+ContractRequest.REQUEST_SONG, requestParams, new AsyncHttpResponseHandler() {
			
			@Override
			public void onSuccess(int arg0, Header[] arg1, byte[] bytes) {
				// TODO Auto-generated method stub
				String solicitud;
				try {
					solicitud = new String(bytes, "UTF-8");
					progress.dismiss();
						Toast toast=  Toast.makeText(getApplicationContext(), solicitud, Toast.LENGTH_LONG);
						toast.show();
									
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			@Override
			public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
	}
	
}
