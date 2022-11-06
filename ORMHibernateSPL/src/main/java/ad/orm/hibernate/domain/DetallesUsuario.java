package ad.orm.hibernate.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "detallesusuarios")
public class DetallesUsuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "iddetallesusuario")
	private int iddetallesusuario;
	
	@Column(name = "idusuario")
	private int idusuario;

	@Column(name = "email")
	private String email;

	@Column(name = "newsletter")
	private char newsletter;

	public DetallesUsuario(int idusuario, String email, char newsletter) {
		super();
		this.idusuario = idusuario;
		this.email = email;
		this.newsletter = newsletter;
	}

	public DetallesUsuario() {
		super();
	}

	public int getIddetallesusuario() {
		return iddetallesusuario;
	}

	public void setIddetallesusuario(int iddetallesusuario) {
		this.iddetallesusuario = iddetallesusuario;
	}

	public int getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public char getNewsletter() {
		return newsletter;
	}

	public void setNewsletter(char newsletter) {
		this.newsletter = newsletter;
	}

	@Override
	public String toString() {
		return "DetallesUsuario [iddetallesusuario=" + iddetallesusuario + ", idusuario=" + idusuario + ", email="
				+ email + ", newsletter=" + newsletter + "]";
	}

	
}
