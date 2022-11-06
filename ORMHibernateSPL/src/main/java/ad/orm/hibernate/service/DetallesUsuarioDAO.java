package ad.orm.hibernate.service;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import ad.orm.hibernate.domain.DetallesUsuario;
import ad.orm.hibernate.util.HibernateUtil;

public class DetallesUsuarioDAO {
	//Métodos detallesusuario, usando alternativa a saveorupdate()
	public void guardarDetallesUsuario(int id,String email,char newsletter) {

		Transaction transaction = null;
		Transaction transaction2 = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// comenzamos transaccion
			transaction = session.beginTransaction();
			DetallesUsuario detallesUsuario = session.get(DetallesUsuario.class, id);
			
			transaction.commit();
			if (detallesUsuario !=null) {
				transaction2 = session.beginTransaction();
				detallesUsuario.setIdusuario(id);
				detallesUsuario.setEmail(email);
				detallesUsuario.setNewsletter(newsletter);
				transaction2.commit();
			}else {
				transaction2 = session.beginTransaction();
				DetallesUsuario detallesUsuario2 = new DetallesUsuario(id,email,newsletter);
				session.save(detallesUsuario2);
				transaction2.commit();
				

			}
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void listDetallesUsuarios() {
		Transaction transaction = null;
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			// comenzar transacción
			transaction = session.beginTransaction();
			// ejecutamos la query en sql nativo que hemos nombrado en la clase película
			// como anotación
			Query query = session.createNativeQuery(" SELECT u.IDUSUARIO, u.NOMBRE, u.APELLIDOS, d.EMAIL, d.NEWSLETTER\r\n"
					+ " FROM USUARIOS u\r\n"
					+ " JOIN DETALLESUSUARIOS d ON u.IDUSUARIO = d.IDUSUARIO\r\n"
					+ " ORDER BY u.IDUSUARIO;");
			transaction.commit();
			// hacemos lista de objetos y almacenamos los resultados
			List<Object[]> detalles = query.getResultList();
			// recorremos los objetos mostrando resultado
			for (Object[] a : detalles) {
				System.out.println(
						"[ID de usuario: " + a[0] + "] [Nombre: " + a[1] + "] [Apellidos: " + a[2] + "] [Email: " + a[3] + "] [Suscrito a Newsletter: " + a[4] + "]");
			}
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();

			}
			e.printStackTrace();
		}
	}
}
