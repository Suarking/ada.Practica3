package ad.orm.hibernate.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "peliculas")
public class Peliculas {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idPelicula")
	private int idPelicula;
	
	@Column (name = "titulo")
	private String titulo;
	
	@Column(name = "genero")
	private String genero;
	

	public Peliculas() {
	
	}


	public Peliculas(String titulo, String genero) {
		this.titulo = titulo;
		this.genero = genero;
	}


	public int getIdPelicula() {
		return idPelicula;
	}


	public void setIdPelicula(int idPelicula) {
		this.idPelicula = idPelicula;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public String getGenero() {
		return genero;
	}


	public void setGenero(String genero) {
		this.genero = genero;
	}


	@Override
	public String toString() {
		return "Pelicula|| [ID de Pelicula: " + idPelicula + "][ Titulo: " + titulo + "][ Genero:" + genero + "]";
	}
	
		

}
