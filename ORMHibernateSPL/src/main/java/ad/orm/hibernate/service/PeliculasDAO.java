package ad.orm.hibernate.service;

import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import ad.orm.hibernate.domain.Peliculas;
import ad.orm.hibernate.util.HibernateUtil;

public class PeliculasDAO {

	public void guardarPelicula(Peliculas peliculas) {

		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// save the student object
			session.save(peliculas);
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public void borrarPelicula(int id) {

		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// comenzamos transacción
			transaction = session.beginTransaction();

			// Obtenemos y borramos el usuario (persistente)
			Peliculas peliculas = session.get(Peliculas.class, id);
			if (peliculas != null) {
				session.delete(peliculas);
				System.out.println("Película borrada correctamente");
				transaction.commit();
			}
		} catch (HibernateException e) {
			if (transaction != null) {
				//rollback si no hay transacción
				transaction.rollback();
			}
			e.printStackTrace();
		}

	}

	public void modPelicula(int id, String titulo, String genero) {

		Transaction transaction = null;
		Transaction transaction2 = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			// Cargamos entidad facilitando id
			Peliculas peliculas = session.load(Peliculas.class, id);
			transaction.commit();
			if (peliculas != null) {
				peliculas.setTitulo(titulo);
				peliculas.setGenero(genero);
				// Abrimos nueva sesión y actualizamos entidad
				transaction2 = session.beginTransaction();
				session.update(peliculas);
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
	public void listPelicula() {

		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			// listar usuarios
			List list = session.createQuery("FROM Peliculas").list();
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Peliculas peliculas = (Peliculas) iterator.next();
				System.out.println(peliculas.toString());

			}
			transaction.commit();

		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}

	}

	// MÉTODOS ADICIONALES DE CONSULTAS
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void listMasAlquilada() {
		Transaction transaction = null;
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			// comenzar transacción
			transaction = session.beginTransaction();
			// ejecutamos la query en sql nativo que hemos nombrado en la clase película
			// como anotación
			Query query = session.createNativeQuery("SELECT a.IDPELICULA , COUNT(*) AS 'VECES ALQUILADA' , p.TITULO \r\n"
					+ "FROM ALQUILERES a, PELICULAS p\r\n"
					+ "where a.idpelicula = p.idpelicula\r\n"
					+ "GROUP BY IDPELICULA\r\n"
					+ "order by COUNT(*) DESC");
			// hacemos lista de objetos y almacenamos los resultados
			
			List<Object[]> peliculas = query.getResultList();
			// recorremos los objetos mostrando resultado
			for (Object[] a : peliculas) {
				System.out.println(
						"[ID de película: " + a[0] + "] [Veces alquilada: " + a[1] + "] [Título: " + a[2] + "]");
			}
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();

			}
			e.printStackTrace();
		}
	}
}
