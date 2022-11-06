package ad.orm.hibernate.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

@Entity
@Table(name = "alquileres")
//declaración de consultas en sql nativo
@NamedNativeQueries(value = {@NamedNativeQuery(name = "getDetailedAlquiler", query = "SELECT a.idalquiler as IDALQUILER, u.idusuario as IDUSUARIO, u.nombre as NOMBREUSUARIO, u.apellidos as APELLIDOS, u.telefono as TELEFONO, p.titulo as 'PELICULA ALQUILADA', a.devuelta as DEVUELTA\r\n"
		+ "FROM usuarios as u\r\n"
		+ "JOIN alquileres as a\r\n"
		+ "ON u.idusuario = a.idusuario\r\n"
		+ "inner join peliculas as p\r\n"
		+ "on a.idpelicula = p.idpelicula\r\n"
		+ "order by a.IDALQUILER;"
		)})
public class Alquiler {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idAlquiler")
	private int idAlquiler;
	
	@Column(name="idUsuario")
	private int idUsuario;
	
	@Column(name="idPelicula")
	private int idPelicula;
	
	@Column(name="devuelta")
	private char devuelta;

	public Alquiler() {
	}

	public Alquiler(int idUsuario, int idPelicula, char devuelta) {
		this.idUsuario = idUsuario;
		this.idPelicula = idPelicula;
		this.devuelta = devuelta;
	}
	public int getIdAlquiler() {
		return idAlquiler;
	}

	public void setIdAlquiler(int idAlquiler) {
		this.idAlquiler = idAlquiler;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdPelicula() {
		return idPelicula;
	}

	public void setIdPelicula(int idPelicula) {
		this.idPelicula = idPelicula;
	}

	public char isDevuelta() {
		return devuelta;
	}

	public void setDevuelta(char devuelta) {
		this.devuelta = devuelta;
	}

	@Override
	public String toString() {
		return "Alquiler [ID de Alquiler: " + idAlquiler + "][ ID de Usuario: " + idUsuario + "][ ID de Película: " + idPelicula
				+ "][ devuelta: " + devuelta + "]";
	}
	
	
	
	

}
