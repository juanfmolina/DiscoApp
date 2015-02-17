package co.edu.udea.compumovil.gr10.discoapp;

import java.io.UnsupportedEncodingException;

import org.apache.http.Header;

import co.edu.udea.compumovil.gr10.discoapp.webservicesclient.contract.WebServiceContract;
import co.edu.udea.compumovil.gr10.discoapp.webservicesclient.contract.WebServiceContract.ContractRequest;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.app.Activity;
import android.app.ProgressDialog;
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
	private String cancionExitosa;
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
		
		Toast.makeText(getBaseContext(),
	            pref.getString("usuario", ""), Toast.LENGTH_LONG)
	            .show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.music, menu);
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
	public void solicitarCancion(View view){
		
		if (!ConexiónInternet.verificaConexion(getApplicationContext())) {
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
						Toast toast=  Toast.makeText(getApplicationContext(), "Solicitud Exitosa", Toast.LENGTH_LONG);
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
