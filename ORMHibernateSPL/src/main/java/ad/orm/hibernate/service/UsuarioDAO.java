package ad.orm.hibernate.service;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ad.orm.hibernate.domain.Usuario;
import ad.orm.hibernate.util.HibernateUtil;

public class UsuarioDAO {
	// métodos de manipulación de entidades
	public void guardarUsuario(Usuario usuarios) {

		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// comenzamos transaccion
			transaction = session.beginTransaction();
			// save the student object
			session.save(usuarios);
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public void borrarUsuario(int id) {

		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// comenzamos transacción
			transaction = session.beginTransaction();

			// Obtenemos y borramos el usuario (persistente)
			Usuario usuario = session.get(Usuario.class, id);
			if (usuario != null) {
				session.delete(usuario);
				System.out.println("Película borrada correctamente");
				transaction.commit();
			}
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}

	}

	public void modUsuario(int id, String nombre, String apellido, int edad, String direccion, int telefono) {

		Transaction transaction = null;
		Transaction transaction2 = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// comenzamos transaccion
			transaction = session.beginTransaction();

			// Cargamos y modificamos entidad
			Usuario usuario = session.load(Usuario.class, id);
			transaction.commit();
			if (usuario != null) {
				usuario.setNombre(nombre);
				usuario.setApellidos(apellido);
				usuario.setEdad(edad);
				usuario.setDireccion(direccion);
				usuario.setTelefono(telefono);
				//Abrimos nueva sesión y actualizamos entidad
				transaction2 = session.beginTransaction();
				session.update(usuario);
				transaction2.commit();

			}
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}

	}

	@SuppressWarnings("rawtypes")
	public void listUsuario() {

		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// comenzamos transaccion
			transaction = session.beginTransaction();

			// listar usuarios con consulta HQL
			List list = session.createQuery("FROM Usuario").list();
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Usuario usuario = (Usuario) iterator.next();
				System.out.println(usuario.toString());

			}
			transaction.commit();

		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}

	}
	
}
