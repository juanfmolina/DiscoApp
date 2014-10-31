package co.edu.udea.compumovil.gr10.discoapp;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter {

	Context context;
	List<Evento> listaOpciones;

	public CustomAdapter(Context context, List<Evento> opciones) {
		this.context = context;
		this.listaOpciones = opciones;
	}

	private class viewHolder {
		ImageView ivImagen;
		TextView txtTitulo;
		TextView txtFecha;
		TextView txtDescripcion;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
	
		viewHolder holder = null;

		LayoutInflater mInflater = (LayoutInflater) context
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		if (convertView == null) {

			convertView = mInflater.inflate(R.layout.event_layout, null);

			holder = new viewHolder();
			holder.ivImagen = (ImageView) convertView
					.findViewById(R.id.imageView1);
			holder.txtTitulo = (TextView) convertView
					.findViewById(R.id.text_titulo);
			holder.txtFecha = (TextView) convertView
					.findViewById(R.id.text_fecha);
			holder.txtDescripcion = (TextView) convertView
					.findViewById(R.id.text_descripcion);
			convertView.setTag(holder);

		} else {
			holder = (viewHolder) convertView.getTag();
		}
		Evento evento = (Evento) getItem(position);
		holder.ivImagen.setImageBitmap(evento.getImagen());
		holder.txtTitulo.setText(evento.getTitulo());
		holder.txtFecha.setText(evento.getFecha());
		holder.txtDescripcion.setText(evento.getDescripcion());

		return convertView;
	}

	@Override
	public int getCount() {
		return listaOpciones.size();
	}

	@Override
	public Object getItem(int position) {
		return listaOpciones.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return listaOpciones.indexOf(getItem(position));
	}

}
