package ad.orm.hibernate.app;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.hibernate.HibernateException;

import ad.orm.hibernate.domain.Alquiler;
import ad.orm.hibernate.domain.Peliculas;
import ad.orm.hibernate.domain.Usuario;
import ad.orm.hibernate.service.AlquilerDAO;
import ad.orm.hibernate.service.DetallesUsuarioDAO;
import ad.orm.hibernate.service.PeliculasDAO;
import ad.orm.hibernate.service.UsuarioDAO;

public class App {
	static Scanner sc = new Scanner(System.in);
	static int opcion;
	static boolean salir = false;

	public static void mostrarmenu() {
		System.out.println("*****************************************");
		System.out.println("*--NEW-HIBERNATE-VIDEOCLUB-LUVANE-2022--*");
		System.out.println("*****************************************");

		do { // Do while con booleana para salir
			System.out.println("1. GESTIÓN DE USUARIOS");
			System.out.println("2. GESTIÓN PELÍCULAS");
			System.out.println("3. GESTIÓN DE ALQUILERES");
			System.out.println("4. CONSULTAS");
			System.out.println("5. SALIR");
			try { // Bloque Try/Catch para Captura de excepciones
				System.out.println("Selecciona una de las opciones anteriores");
				opcion = sc.nextInt();
				switch (opcion) {
				case 1:
					System.out.println("****MENÚ USUARIOS****\n");
					System.out.println("1. AÑADIR USUARIO");
					System.out.println("2. MODIFICAR USUARIO");
					System.out.println("3. ELIMINAR USUARIO");
					System.out.println("4. MOSTRAR USUARIOS");
					System.out.println("5. ATRÁS");
					System.out.println("Selecciona una de las opciones anteriores");
					int sub1 = sc.nextInt();

					switch (sub1) {
					case 1:
						System.out.println("1. AÑADIR USUARIO");
						addUsuario();
						break;
					case 2:
						System.out.println("2. MODIFICAR USUARIO");
						modUsuario();
						break;
					case 3:
						System.out.println("3. ELIMINAR USUARIO");
						delUsuario();
						break;
					case 4:
						System.out.println("4. MOSTRAR USUARIOS");
						listUsuarios();
						break;
					case 5:
						mostrarmenu();
						break;
					default:
						System.out.println("Utiliza solo números entre 1 y 5");
						break;
					}
					break;
				case 2:
					System.out.println("****MENÚ PELÍCULAS****\n");
					System.out.println("1. AÑADIR PELÍCULA");
					System.out.println("2. MODIFICAR PELÍCULA");
					System.out.println("3. ELIMINAR PELÍCULA");
					System.out.println("4. MOSTRAR PELÍCULAS");
					System.out.println("5. ATRÁS");
					System.out.println("Selecciona una de las opciones anteriores");

					int sub2 = sc.nextInt();
					switch (sub2) {
					case 1:
						System.out.println("1. AÑADIR PELÍCULA");
						addPelicula();
						break;
					case 2:
						System.out.println("2. MODIFICAR PELÍCULA");
						modPelicula();
						break;
					case 3:
						System.out.println("3. ELIMINAR PELÍCULA");
						delPelicula();
						break;
					case 4:
						System.out.println("4. MOSTRAR PELÍCULAS");
						listPeliculas();
						break;
					case 5:
						mostrarmenu();
						break;
					default:
						System.out.println("Utiliza solo números entre 1 y 5");
						break;
					}
					break;
				case 3:
					System.out.println("****MENÚ ALQUILERES****\n");
					System.out.println("1. AÑADIR ALQUILER");
					System.out.println("2. MODIFICAR ALQUILER");
					System.out.println("3. ELIMINAR ALQUILER");
					System.out.println("4. MOSTRAR ALQUILERES (simple)");
					System.out.println("5. ATRÁS");
					System.out.println("Selecciona una de las opciones anteriores");
					int sub3 = sc.nextInt();
					switch (sub3) {
					case 1:
						System.out.println("1. AÑADIR ALQUILER");
						addAlquiler();
						break;
					case 2:
						System.out.println("2. MODIFICAR ALQUILER (MARCAR COMO DEVUELTA/NO DEVUELTA)");
						listAlquileres();
						modAlquiler();
						break;
					case 3:
						System.out.println("3. ELIMINAR REGISTRO DE ALQUILER");
						delAlquiler();
						break;
					case 4:
						System.out.println("4. MOSTRAR ALQUILERES");
						listAlquileresSimple();
						break;
					case 5:
						mostrarmenu();
						break;
					default:
						System.out.println("Utiliza solo números entre 1 y 5");
						break;
					}
					break;
				case 4:
					System.out.println("****MENÚ CONSULTAS/OTROS****\n");
					System.out.println("1. INFORME DETALLADO DE ALQUILERES");
					System.out.println("2. RANKING DE PELÍCULAS ALQUILADAS");
					System.out.println("3. COMUNICACION ONLINE (Email/Newsletter");
					System.out.println("4. ATRÁS");
					System.out.println("Selecciona una de las opciones anteriores");

					int sub4 = sc.nextInt();
					switch (sub4) {
					case 1:
						System.out.println("1. INFORME DETALLADO DE ALQUILERES");
						listAlquileres();
						break;
					case 2:
						System.out.println("2. RANKING DE PELÍCULAS ALQUILADAS");
						PeliculasDAO peliculasDAO = new PeliculasDAO();
						peliculasDAO.listMasAlquilada();
						break;
					case 3:
						System.out.println("****COMUNICACION ONLINE****");
						System.out.println("1. VER USUARIOS SUSCRITOS AL BOLETÍN");
						System.out.println("2. CAMBIAR EMAIL/SUSCRIPCIÓN AL BOLETÍN");
						System.out.println("3. ATRÁS");
						System.out.println("Selecciona una de las opciones anteriores");

						int sub4a = sc.nextInt();
						switch (sub4a) {
						case 1:
							System.out.println("1. VER USUARIOS SUSCRITOS AL BOLETÍN");
							listDetallesUsuarios();
							break;
						case 2:
							// Usando saveorupdate() de hibernate;
							System.out.println("2. CAMBIAR/AÑADIR EMAIL/SUSCRIPCIÓN AL BOLETÍN");
							listDetallesUsuarios();
							addDetallesUsuario();
							break;
						case 3:
							mostrarmenu();
							break;
						default:
							System.out.println("Utiliza solo números entre 1 y 3");
							break;
						}
						break;
					case 4:
						mostrarmenu();
						break;
					default:
						System.out.println("Utiliza solo números entre 1 y 4");
						break;
					}
					break;
				case 5:

					salir = true;
					System.out.println("Aplicación terminada, hasta pronto!!");

					break;
				default:
					System.out.println("Utiliza solo números entre 1 y 5");
				}
			} catch (InputMismatchException e) {
				e.printStackTrace();
				System.out.println("El tipo de dato introducido no se corresponde con el solicitado");
				sc.next();
			} catch (HibernateException e) {
				e.printStackTrace();
				System.out.println("No ha sido posible realizar la operación solicitada");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} while (!salir);
	}

	// ------MÉTODOS MENÚ USUARIOS------
	public static void addUsuario() {
		sc.nextLine();
		System.out.println("Introduce el nombre del nuevo usuario: ");
		String nombre = sc.nextLine();
		System.out.println("Introduce el apellido del nuevo usuario: ");
		String apellidos = sc.nextLine();

		System.out.println("Introduce la edad del nuevo usuario: ");
		int edad = sc.nextInt();
		sc.nextLine();
		System.out.println("Introduce la dirección del nuevo usuario: ");
		String direccion = sc.nextLine();
		System.out.println("Introduce el teléfono del nuevo usuario: ");
		int telefono = sc.nextInt();

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = new Usuario(nombre, apellidos, edad, direccion, telefono);

		usuarioDAO.guardarUsuario(usuario);
		listUsuarios();
		System.out.println("El usuario " + usuario.getNombre().toString() + " " + usuario.getApellidos().toString()
				+ " ha sido agragedo correctamente");
	}

	public static void delUsuario() {
		// mostramos la lista de usuarios para poder elegir la ID
		listUsuarios();
		sc.nextLine();
		// pedimos datos
		System.out.println("Introduce el ID del usuario a borrar: ");
		int idUsuario = sc.nextInt();
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		// llamámos al método facilitando id para que lo borre
		usuarioDAO.borrarUsuario(idUsuario);
		// volvemos a listar para comprobar que se ha borrado correctamente
		listUsuarios();
		System.out.println("\nEl usuario ha sido eliminado correctamente\n");
	}

	public static void modUsuario() {
		listUsuarios();
		sc.nextLine();
		// pedimos dato de ID
		System.out.println("\nIntroduce el ID del usuario a modificar: ");
		int idUsuario = sc.nextInt();
		// Introducimos nuevos datos de usuario
		sc.nextLine();
		System.out.println("Introduce el nuevo nombre del usuario: ");
		String nombre = sc.nextLine();
		System.out.println("Introduce el nuevo apellido del usuario: ");
		String apellidos = sc.nextLine();

		System.out.println("Introduce la nueva edad del usuario: ");
		int edad = sc.nextInt();
		sc.nextLine();
		System.out.println("Introduce la nueva dirección del usuario: ");
		String direccion = sc.nextLine();
		System.out.println("Introduce el nuevo teléfono del usuario: ");
		int telefono = sc.nextInt();

		UsuarioDAO usuarioDAO = new UsuarioDAO();

		usuarioDAO.modUsuario(idUsuario, nombre, apellidos, edad, direccion, telefono);
		listUsuarios();

		System.out.println("Los datos del usuario han sido actualizados correctamente");
	}

	public static void listUsuarios() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.listUsuario();
		System.out.println("\n");
	}

	// ------MÉTODOS MENÚ PELÍCULAS------
	public static void addPelicula() {
		// limpiamos buffer
		sc.nextLine();
		// pedimos datos
		System.out.println("Introduce el nombre de la nueva película: ");
		String titulo = sc.nextLine();
		System.out.println("Introduce el género de la nueva película: ");
		String genero = sc.nextLine();
		// Creamos objeto DAO y objeto con los valores anteriores

		PeliculasDAO peliculasDAO = new PeliculasDAO();

		Peliculas peliculas = new Peliculas(titulo, genero);

		// llamamos al método correcpondiente y almacenamos la película
		peliculasDAO.guardarPelicula(peliculas);
		listPeliculas();

		System.out.println("La película " + peliculas.getTitulo().toString() + " ha sido añadida con éxito. ");

	}

	public static void modPelicula() {
		try {
			listPeliculas();
			sc.nextLine();
			// pedimos dato de ID
			System.out.println("\nIntroduce el ID de la película a modificar: ");
			int idPelicula = sc.nextInt();
			// Introducimos nuevos datos de usuario
			sc.nextLine();
			System.out.println("Introduce el nuevo título de la película: ");
			String titulo = sc.nextLine();
			System.out.println("Introduce el nuevo género de la película: ");
			String genero = sc.nextLine();
			PeliculasDAO peliculasDAO = new PeliculasDAO();

			peliculasDAO.modPelicula(idPelicula, titulo, genero);
			listPeliculas();
			System.out.println("Los datos de la película han sido modificados correctamente");
		} catch (Exception e) {
			System.out.println("Error en la operación de modificado");
		}

	}

	public static void delPelicula() {
		listPeliculas();
		sc.nextLine();
		// pedimos datos
		System.out.println("Introduce el ID de la película que deseas borrar: ");
		int idpelicula = sc.nextInt();

		PeliculasDAO peliculasDAO = new PeliculasDAO();

		peliculasDAO.borrarPelicula(idpelicula);
		listPeliculas();
		System.out.println("Película borrada correctamente");

	}

	public static void listPeliculas() {
		PeliculasDAO peliculasDAO = new PeliculasDAO();

		peliculasDAO.listPelicula();
		;
		System.out.println("\n");
	}

	// ------MÉTODOS MENÚ ALQUILERES------
	public static void addAlquiler() {
		listUsuarios();

		System.out.println("Introduce el ID del usuario que alquila la película: ");
		int idusuario = sc.nextInt();
		listPeliculas();
		System.out.println("Introduce el ID de la película alquilada ");
		int idpelicula = sc.nextInt();

		AlquilerDAO alquilerDAO = new AlquilerDAO();

		Alquiler alquiler = new Alquiler(idusuario, idpelicula, 'N');

		alquilerDAO.guardarAlquiler(alquiler);

		System.out.println("El alquiler ha sido registrado con éxito. ");

	}

	public static void delAlquiler() {
		listAlquileresSimple();
		sc.nextLine();
		// pedimos datos
		System.out.println("Introduce el ID del registro de alquiler a borrar: ");
		int idAlquiler = sc.nextInt();

		AlquilerDAO alquilerDAO = new AlquilerDAO();

		alquilerDAO.borrarAlquiler(idAlquiler);
		System.out.println("El registro ha sido eliminado correctamente");
	}

	public static void modAlquiler() {
		try {

			sc.nextLine();
			listAlquileres();
			// pedimos dato de ID
			System.out.println("\nIntroduce el ID del alquiler al que quieres modificar estado de devolución: ");
			int idalquiler = sc.nextInt();
			sc.nextLine();
			// Introducimos nuevos datos de usuario
			System.out.println("\nIntroduce el estado de devolución (Introduce únicamente S o N): ");
			String stringdevuelto = sc.nextLine().toUpperCase();
			char devuelta = stringdevuelto.charAt(0);
			AlquilerDAO alquilerDAO = new AlquilerDAO();
			alquilerDAO.modAlquiler(idalquiler, devuelta);
			listAlquileres();
			System.out.println("Los datos del estado de alquiler han sido modificados correctamente");
		} catch (HibernateException e) {
			System.out.println("Error en la operación de modificado");
		}

	}

	public static void listAlquileresSimple() {
		try {
			AlquilerDAO alquilerDAO = new AlquilerDAO();

			alquilerDAO.listAlquiler();
			System.out.println("\n");
		} catch (HibernateException e) {

			e.printStackTrace();
		}

	}

	// ------MÉTODOS MENÚ CONSULTAS------
	public static void addDetallesUsuario() {
		sc.nextLine();

		System.out.println("Introduce el ID del usuario al que añadir datos de comunicación online: ");
		int idusuario = sc.nextInt();
		sc.nextLine();
		System.out.println("Introduce el correo electrónico del usuario: ");
		String correo = sc.nextLine();
		System.out.println("Desea suscripción a newsletter? (Introduzca solo S o N)");
		char newsletter = sc.nextLine().toUpperCase().charAt(0);

		DetallesUsuarioDAO detallesUsuarioDAO = new DetallesUsuarioDAO();


		detallesUsuarioDAO.guardarDetallesUsuario(idusuario, correo, newsletter);

	}

	public static void listAlquileres() {
		try {
			AlquilerDAO alquilerDAO = new AlquilerDAO();

			alquilerDAO.listDetailAlquiler();
			System.out.println("\n");
		} catch (HibernateException e) {

			e.printStackTrace();
		}

	}

	public static void listDetallesUsuarios() {
		try {
			DetallesUsuarioDAO detallesUsuarioDAO = new DetallesUsuarioDAO();

			detallesUsuarioDAO.listDetallesUsuarios();
			System.out.println("\n");
		} catch (HibernateException e) {

			e.printStackTrace();
		}

	}

	
	
	public static void main(String[] args) {
		mostrarmenu();

	}

}
