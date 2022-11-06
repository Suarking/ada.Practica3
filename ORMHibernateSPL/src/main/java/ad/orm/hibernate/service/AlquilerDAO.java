package ad.orm.hibernate.service;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import ad.orm.hibernate.domain.Alquiler;
import ad.orm.hibernate.util.HibernateUtil;

public class AlquilerDAO {

	public void guardarAlquiler(Alquiler alquileres) {

		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// save the student object
			session.save(alquileres);
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public void borrarAlquiler(int id) {

		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// comenzamos transacción
			transaction = session.beginTransaction();

			// Obtenemos y borramos el alquiler (persistente)
			Alquiler alquiler = session.get(Alquiler.class, id);
			if (alquiler != null) {
				session.delete(alquiler);
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

	public void modAlquiler(int id, char devuelta) {

		Transaction transaction = null;
		Transaction transaction2 = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// Comenzamos transacción
			transaction = session.beginTransaction();

			// Cargamos entidad facilitando id
			Alquiler alquiler = session.load(Alquiler.class, id);
			// commit
			transaction.commit();
			// si no es nulo
			if (alquiler != null) {
				// modificamos el valor de devuelta
				alquiler.setDevuelta(devuelta);
				transaction2 = session.beginTransaction();
				session.update(alquiler);
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
	public void listAlquiler() {

		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// comenzar transacción
			transaction = session.beginTransaction();

			// listar alquileres, almacenamos en lista
			List list = session.createQuery("FROM Alquiler").list();
			// la recorremos
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Alquiler alquiler = (Alquiler) iterator.next();
				System.out.println(alquiler.toString());
			}
			transaction.commit();

		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}

	}

	// MÉTODOS ADICIONALES DE CONSULTA
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void listDetailAlquiler() {
		Transaction transaction = null;
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			// comenzar transacción
			transaction = session.beginTransaction();
			//ejecutamos la query en sql nativo que hemos nombrado en la clase alquiler como anotación
			Query query = session.getNamedQuery("getDetailedAlquiler");
			//la almacenamos en una lista de objetos
			List<Object[]> list = query.list();
			//obtenemos valores de los objetos y los mostramos
			for (Object[] objects : list) {
				int idalquiler = (int) objects[0];
				int idusuario = (int) objects[1];
				String nombre = (String) objects[2];
				String apellidos = (String) objects[3];
				int telefono = (int) objects[4];
				String titulo = (String) objects[5];
				char devuelta = (char) objects[6];
				System.out.println("ID de Alquiler: " + idalquiler + " [ID de Usuario: " + idusuario + "] [Nombre: "
						+ nombre + "] [Apellidos: " + apellidos + "] [Telefono: " + telefono + "] [Titulo: " + titulo
						+ "] [Devuelta: " + devuelta + "]");
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
