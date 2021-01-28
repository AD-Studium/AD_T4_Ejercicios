/**
 * 
 */


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.matisse.MtDatabase;
import com.matisse.MtException;
import com.matisse.MtObjectIterator;
import com.matisse.MtPackageObjectFactory;

import biblioteca.Articulo;
import biblioteca.Autor;
import biblioteca.Libro;
import biblioteca.Obra;
/**
 * @author Alvca
 *
 */
public class Ejercicio1 {
	static Scanner teclado= new Scanner(System.in);
	static int menu;
	static String hostname ="localhost";
	static String dbname = "biblio";
	static String namespace = "biblioteca";
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		do {
			System.out.println("Elije que hacer:");
			System.out.println("1-Crear libro");
			System.out.println("2-Crear Articulo");
			System.out.println("3-Crear obra");
			System.out.println("4-Crear Autor");
			System.out.println("5-Relacionar Autor");
			System.out.println("6-Borrar todas las obras");
			System.out.println("7-Modificar Autor");
			System.out.println("8-Modificar Libro");
			System.out.println("9-Modificar Articulo");
			System.out.println("Inserte 0 para salir");
			menu=Integer.parseInt(teclado.nextLine());
			if(menu==1) {
				System.out.println("Introduce el titulo");
				String titulo=teclado.nextLine();
				System.out.println("Introduce la editorial");
				String editorial=teclado.nextLine();
				System.out.println("Introduce el número de páginas");
				Integer paginas=Integer.parseInt(teclado.nextLine());
				creaLibro(titulo, editorial, paginas);
			}else if(menu==2) {
				System.out.println("Introduce el titulo");
				String titulo=teclado.nextLine();
				System.out.println("Introduce la revista");
				String revista=teclado.nextLine();
				System.out.println("Introduce el número de páginas");
				Integer paginas=Integer.parseInt(teclado.nextLine());
				creaArticulo(titulo, revista, paginas);
			}else if(menu==3) {
				System.out.println("Introduce el titulo");
				String titulo=teclado.nextLine();
				System.out.println("Introduce el número de páginas");
				Integer paginas=Integer.parseInt(teclado.nextLine());
				creaObra(titulo, paginas);
			}else if(menu==4) {
				System.out.println("Introduce el nombre");
				String nombre=teclado.nextLine();
				System.out.println("Introduce los apellidos");
				String apellidos=teclado.nextLine();
				System.out.println("Introduce la edad");
				Integer edad=Integer.parseInt(teclado.nextLine());
				creaAutor(nombre, apellidos, edad);
			}else if(menu==5) {
				System.out.println("Introduce el nombre");
				String nombre=teclado.nextLine();
				System.out.println("Introduce los apellidos");
				String apellidos=teclado.nextLine();
				System.out.println("Introduce la edad");
				Integer edad=Integer.parseInt(teclado.nextLine());
				System.out.println("Introduce el titulo");
				String titulo=teclado.nextLine();
				System.out.println("Introduce la editorial");
				String editorial=teclado.nextLine();
				System.out.println("Introduce el número de páginas");
				Integer paginas=Integer.parseInt(teclado.nextLine());
				autorEscribeObra(nombre, apellidos, edad, titulo, editorial, paginas);
			}else if(menu==6){
				borrarTodos("localhost","biblio");
			}else if(menu==7){
				mostrar("Autor");
				System.out.println("Introduce el nombre");
				String nombre=teclado.nextLine();
				System.out.println("Introduce los apellidos");
				String apellidos=teclado.nextLine();
				modificaNombreAutor(nombre, apellidos);
			}else if(menu==8){
				mostrar("Libro");
				System.out.println("Introduce el titulo");
				String titulo=teclado.nextLine();
				System.out.println("Introduce la editorial");
				String editorial=teclado.nextLine();
				modificaNombreLibro(titulo, editorial);
			}else if(menu==9){
				mostrar("Articulo");
				System.out.println("Introduce el titulo");
				String titulo=teclado.nextLine();
				System.out.println("Introduce la revista");
				String revista=teclado.nextLine();
				modificaNombreArticulo(titulo, revista);
			}else
			{
				if (menu!=0) {
					System.out.println("Debe seleccionar una opcion valida");
				}
			}
		}while(menu!=0);
		System.out.println("Saliendo del programa");
		teclado.close();
	}
	public static Libro creaLibro(String titulo, String editorial, Integer paginas) {
		// Abre la base de datos con el Hostname (localhost), dbname (biblio) y el namespace "biblioteca".
		MtDatabase db = new MtDatabase(hostname, dbname, new MtPackageObjectFactory("", namespace));
		db.open();
		db.startTransaction();
		System.out.println("Conectado a la base de datos " +db.toString() + " de Matisse");

		// Crea un objeto Libro
		Libro libro = new Libro(db);
		libro.setTitulo(titulo);
		libro.setEditorial(editorial);
		libro.setPaginas(paginas);
		// Ejecuta un commit para materializar las peticiones.
		System.out.println("El libro " + libro.getTitulo() + " se ha credo correctamente");
		db.commit();
		// Cierra la base de datos.
		db.close();
		return libro;
	}
	public static void creaArticulo(String titulo, String revista, Integer paginas) {
		// Abre la base de datos con el Hostname (localhost), dbname (biblio) y el namespace "biblioteca".
		MtDatabase db = new MtDatabase(hostname, dbname, new MtPackageObjectFactory("", namespace));
		db.open();
		db.startTransaction();
		System.out.println("Conectado a la base de datos " +db.toString() + " de Matisse");
		// Crea un objeto Articulo
		Articulo articulo = new Articulo (db);
		articulo.setTitulo(titulo);
		articulo.setPaginas(paginas);
		articulo.setRevista(revista);
		System.out.println("El artículo " + articulo.getTitulo() + " se ha credo correctamente");
		// Ejecuta un commit para materializar las peticiones.
		db.commit();
		// Cierra la base de datos.
		db.close();

	}
	public static void creaObra(String titulo, Integer paginas) {
		// Abre la base de datos con el Hostname (localhost), dbname (biblio) y el namespace "biblioteca".
		MtDatabase db = new MtDatabase(hostname, dbname, new MtPackageObjectFactory("", namespace));
		db.open();
		db.startTransaction();
		System.out.println("Conectado a la base de datos " +db.toString() + " de Matisse");
		// Crea un objeto Articulo
		Obra obra = new Obra (db);
		obra.setTitulo(titulo);
		obra.setPaginas(paginas); 
		System.out.println("La obra " + obra.getTitulo() + " se ha credo correctamente");
		// Ejecuta un commit para materializar las peticiones.
		db.commit();
		// Cierra la base de datos.
		db.close();

	}
	public static Autor creaAutor(String nombre, String apellidos, Integer edad) {
		// Abre la base de datos con el Hostname (localhost), dbname (biblio) y el namespace "biblioteca".
		MtDatabase db = new MtDatabase(hostname, dbname, new MtPackageObjectFactory("", namespace));
		db.open();
		db.startTransaction();
		System.out.println("Conectado a la base de datos " +db.toString() + " de Matisse");
		// Crea un objeto Autor
		Autor autor = new Autor(db);
		autor.setNombre(nombre);
		autor.setApellidos(apellidos);
		autor.setEdad(edad);
		System.out.println("El autor " + autor.getNombre() + " se ha credo correctamente");
		// Ejecuta un commit para materializar las peticiones.
		db.commit();
		// Cierra la base de datos.
		db.close();
		return autor;
	}
	public static void autorEscribeObra(String nombre, String apellidos, Integer edad, String titulo, String editorial, Integer paginas) {
		try {
			// Abre la base de datos con el Hostname (localhost), dbname (biblio) y el namespace "biblioteca".
			MtDatabase db = new MtDatabase(hostname, dbname, new MtPackageObjectFactory("", namespace));
			db.open();
			db.startTransaction();
			System.out.println("Conectado a la base de datos " +db.toString() + " de Matisse");
			// Crea un objeto Autor
			Autor autor = creaAutor(nombre, apellidos, edad);
			// Crea un objeto Libro
			System.out.println("Autor "+ autor.getNombre() +" creado...");
			Libro libro = creaLibro(titulo, editorial, paginas);
			System.out.println("Libro "+ libro.getTitulo() +" creado...");
			// Crea un array de Obras para guardar los libros y hacer las relaciones
			Obra obra[] = new Obra[1];
			obra[0] = libro;
			// Guarda las relaciones del autor con los libros que ha escrito.
			autor.setEscribe(obra);
			// Ejecuta un commit para materializar las peticiones.
			db.commit();
			// Cierra la base de datos.
			db.close();
			System.out.println("\nHecho.");
		} catch (MtException mte) {
			System.out.println("MtException : " + mte.getMessage());
		}
	}
	//Borrar todos los objetos de una clase
	public static void borrarTodos(String hostname, String dbname) {
		try {
			MtDatabase db = new MtDatabase(hostname, dbname, new MtPackageObjectFactory("", namespace));
			db.open();
			db.startTransaction();
			// Lista todos los objetos Obra que hay en la base dedatos, con el método getInstanceNumber
			System.out.println("\n" + Obra.getInstanceNumber(db) + "Obra(s) en la DB.");

			//Borra todas las instancias de Obra
			Obra.getClass(db).removeAllInstances();

			// materializa los cambios y cierra la BD
			db.commit();
			db.close();
			System.out.println("\n Borrado.");
		} catch (MtException mte) {
			System.out.println("Error : " + mte.getMessage());
		}
	}
	public static void modificaNombreAutor(String nombre, String apellidos) {
		System.out.println("=========== Modifica nombre y apellidos del Autor ==========\n");
		int nAutores = 0;
		try {
			MtDatabase db = new MtDatabase(hostname, dbname, new MtPackageObjectFactory("", namespace));
			db.open();
			db.startTransaction();
			// Lista cuántos objetos Autor con el método
			//getInstanceNumber
			System.out.println("\n" + Autor.getInstanceNumber(db) + "Autores en la DB.");
			nAutores = (int) Autor.getInstanceNumber(db);
			// Crea un Iterador (propio de Java)
			MtObjectIterator<Autor> iter =
					Autor.<Autor>instanceIterator(db);
			System.out.println("recorro el iterador de uno en uno y cambio cuando encuentro "+nombre);
			while (iter.hasNext()) {
				Autor[] autores = iter.next(nAutores);
				for (int i = 0; i < autores.length; i++) {
					if (autores[i].getNombre().equals(nombre)) {
						autores[i].setApellidos(apellidos);
					} else {
					}
				}
				break;
			}
			iter.close();
			// materializa los cambios y cierra la BD
			db.commit();
			db.close();
			System.out.println("\nHEcho.");
		} catch (MtException mte) {
			System.out.println("MtException : " + mte.getMessage());
		}
	}
	public static void modificaNombreLibro(String titulo, String editorial) {
		System.out.println("=========== Modifica nombre y apellidos del Autor ==========\n");
		int nAutores = 0;
		try {
			MtDatabase db = new MtDatabase(hostname, dbname, new MtPackageObjectFactory("", namespace));
			db.open();
			db.startTransaction();
			// Lista cuántos objetos Libro con el método getInstanceNumber
			System.out.println("\n" + Libro.getInstanceNumber(db) + " Libros en la DB.");
			nAutores = (int) Libro.getInstanceNumber(db);
			// Crea un Iterador (propio de Java)
			MtObjectIterator<Libro> iter = Libro.<Libro>instanceIterator(db);
			System.out.println("recorro el iterador de uno en uno y cambio cuando encuentro "+titulo);
			while (iter.hasNext()) {
				Libro[] libros = iter.next(nAutores);
				for (int i = 0; i < libros.length; i++) {
					if
					(libros[i].getTitulo().equalsIgnoreCase(titulo)) {
						libros[i].setEditorial(editorial);
					} else {
					}
				}
				break;
			}
			iter.close();
			// materializa los cambios y cierra la BD
			db.commit();
			db.close();
			System.out.println("\nHEcho.");
		} catch (MtException mte) {
			System.out.println("MtException : " + mte.getMessage());
		}
	}
	public static void modificaNombreArticulo(String titulo, String revista) {
		System.out.println("=========== Modifica nombre y apellidos del Articulo ==========\n");
		int nArticulos = 0;
		try {
			MtDatabase db = new MtDatabase("localhost", "biblio", new MtPackageObjectFactory("", namespace));
			db.open();
			db.startTransaction();
			// Lista cuántos objetos Libro con el método getInstanceNumber
			System.out.println("\n" + Articulo.getInstanceNumber(db) +" Libros en la DB.");
			nArticulos = (int) Articulo.getInstanceNumber(db);
			// Crea un Iterador (propio de Java)
			MtObjectIterator<Articulo> iter = Articulo.<Articulo>instanceIterator(db);
			System.out.println("recorro el iterador de uno en uno y cambio cuando encuentro "+titulo);
			while (iter.hasNext()) {
				Articulo[] articulos = iter.next(nArticulos);
				for (int i = 0; i < articulos.length; i++) {
					if
					(articulos[i].getTitulo().equalsIgnoreCase(titulo)) {
						articulos[i].setRevista(revista);
					} else {
					}
				}
				break;
			}
			iter.close();
			// materializa los cambios y cierra la BD
			db.commit();
			db.close();
			System.out.println("\nHEcho.");
		} catch (MtException mte) {
			System.out.println("MtException : " + mte.getMessage());
		}
	}
	//Mostrar
	public static void mostrar(String tabla) {
		MtDatabase dbcon = new MtDatabase(hostname, dbname);
		// Abre una conexión a la base de datos
		dbcon.open();
		try {
			// Crea una instancia de Statement
			Statement stmt = dbcon.createStatement();
			// Asigna una consulta OQL. Esta consulta lo que hace es utilizar REF() para
			// obtener el objeto
			// directamente en vez de obtener valores concretos (que también podría ser).
			String commandText = "SELECT REF(a) from biblioteca."+tabla+" a;";
			// Ejecuta la consulta y obtiene un ResultSet
			ResultSet rset = stmt.executeQuery(commandText);
			Autor a1;
			// Lee rset uno a uno.
			while (rset.next()) {
				// Obtiene los objetos Autor.
				a1 = (Autor) rset.getObject(1);
				// Imprime los atributos de cada objeto con un formato determinado. 
				System.out.println("Autor: " + String.format("%16s", a1.getNombre()) + String.format("%16s", a1.getApellidos()) + " Spouse: " + String.format("%16s", a1.getEdad()));
			}
			// Cierra las conexiones
			rset.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		}
		//dbcon.close();
	}
}
