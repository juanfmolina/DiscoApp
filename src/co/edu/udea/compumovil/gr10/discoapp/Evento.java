package co.edu.udea.compumovil.gr10.discoapp;

import android.graphics.Bitmap;

public class Evento {
	private String Titulo;
	private String Descripcion;
	private String Fecha;
	private Bitmap Imagen;
	
	
	
	public String getTitulo() {
		return Titulo;
	}
	public void setTitulo(String titulo) {
		Titulo = titulo;
	}
	public String getDescripcion() {
		return Descripcion;
	}
	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}
	public String getFecha() {
		return Fecha;
	}
	public void setFecha(String fecha) {
		Fecha = fecha;
	}
	public Bitmap getImagen() {
		return Imagen;
	}
	public void setImagen(Bitmap imagen) {
		Imagen = imagen;
	}
	
	
	
}
