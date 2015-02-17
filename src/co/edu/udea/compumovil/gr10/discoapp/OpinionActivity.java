package co.edu.udea.compumovil.gr10.discoapp;

import java.io.UnsupportedEncodingException;

import org.apache.http.Header;

import co.edu.udea.compumovil.gr10.discoapp.webservicesclient.contract.WebServiceContract;
import co.edu.udea.compumovil.gr10.discoapp.webservicesclient.contract.WebServiceContract.ContractScore;
import co.edu.udea.compumovil.gr10.discoapp.webservicesclient.contract.WebServiceContract.ContractUser;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.Toast;

public class OpinionActivity extends Activity {
	private EditText opinion;
	private RatingBar opinionMusica;
	private RatingBar opinionServicio;
	private ProgressDialog progress;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_opinion);
		progress = new ProgressDialog(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.opinion, menu);
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
	
	public void darOpinion(View view){
		
		if (!ConexiónInternet.verificaConexion(getApplicationContext())) {
		    Toast.makeText(getBaseContext(),
		            "No tienes acceso a internet, Comprueba tu conexión y vuelve a intentarlo.", Toast.LENGTH_LONG)
		            .show();
		    return;
		}
		
		opinion = (EditText)findViewById(R.id.opinionText);
		opinionMusica = (RatingBar)findViewById(R.id.estrellasMusica);
		opinionServicio = (RatingBar)findViewById(R.id.EstrellasServicio);
		progress.setMessage("Enviando tu opinión, Porfavor espera");
		progress.setCancelable(false);
		progress.show();
		RequestParams requestParams = new RequestParams();
		requestParams.add(ContractScore.USER_SCORE_COMENT, opinion.getText().toString());
		requestParams.put(ContractScore.USER_SCORE_MUSIC, (int)opinionMusica.getRating()*2);
		requestParams.put(ContractScore.USER_SCORE_MUSIC, (int)opinionServicio.getRating()*2);
		requestParams.add(ContractUser.REGISTER_USER_R, "juanf.molina");
		
		AsyncHttpClient client = new AsyncHttpClient();
		client.get(WebServiceContract.ROOT_PATH+ContractScore.INSERT_SCORE, requestParams, new AsyncHttpResponseHandler() {
			
			@Override
			public void onSuccess(int arg0, Header[] arg1, byte[] bytes) {
				// TODO Auto-generated method stub
				try {
					progress.dismiss();
					String calificacion = new String(bytes, "UTF-8");
					Toast toast = Toast.makeText(getApplicationContext(), calificacion, Toast.LENGTH_LONG);
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
