package mongoDB;

import java.util.Scanner;

import org.bson.Document;

import com.mongodb.client.*;
import com.mongodb.client.MongoClient;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;


public class consultasMongoDB {

	public static void main(String[] args) {

		// Establecemos la conexión con la base de datos MongoDB
		MongoClient conexion = MongoClients.create("mongodb://localhost:27017");
		MongoDatabase database = conexion.getDatabase("harry-potter");
		MongoCollection<Document> coleccionHarry = database.getCollection("collection_harry-potter");

		Scanner sc = new Scanner(System.in);
		int opcion = 0;

		do {
			System.out.println("Elija una opción");
			System.out.println("1. Mostrar todos los personajes cuyo atributo -species- tenga como valor -human-");
			System.out.println("2. Mostrar todos los personajes cuyo atributo -yearOfBirth- sea anterior a 1979");
			System.out.println("3. Mostrar todos los personajes cuyo atributo -wood- de la propiedad -wand- sea -holly-");
			System.out.println("4. Mostrar todos los personajes que estén vivos (propiedad -alive- igual a true) y además sean estudiantes (propiedad -hogwartsStudent- igual a true)");
			System.out.println("5. Finalizar el programa ");

			opcion = sc.nextInt();
			switch (opcion) {
			case 1:{
				consulta1(coleccionHarry);
				break;
			}
			case 2:{
				consulta2(coleccionHarry);
				break;
			}
			case 3:{
				consulta3(coleccionHarry);
				break;
			}
			case 4:{
				consulta4(coleccionHarry);
				break;
			}
			case 5:{
				break;
			}
			}

		}while(opcion !=5);
	}


	// Método para la consulta1
	public static void consulta1(MongoCollection<Document> coleccionHarry) {

		// Mostrar todos los personajes cuyo atributo "species" tenga como valor "human"
		FindIterable<Document> humanos = coleccionHarry.find(eq("species", "human"));
		for(Document coleccion : humanos) {
			System.out.println(coleccion.toJson());
		}
	}

	// Método para la consulta2
	public static void consulta2(MongoCollection<Document> coleccionHarry) {

		// Mostrar todos los personajes cuyo atributo -yearOfBirth- sea anterior a 1979
		FindIterable<Document> humanos = coleccionHarry.find(lte("yearOfBirth", 1979));
		for(Document coleccion : humanos) {
			System.out.println(coleccion.toJson());
		}
	}
	
	// Método para la consulta3
	public static void consulta3(MongoCollection<Document> coleccionHarry) {

		// Mostrar todos los personajes cuyo atributo -wood- de la propiedad -wand- sea -holly-
		FindIterable<Document> humanos = coleccionHarry.find(eq("wand.wood", "holly"));
		for(Document coleccion : humanos) {
			System.out.println(coleccion.toJson());
		}
	}
	
	// Método para la consulta4
	public static void consulta4(MongoCollection<Document> coleccionHarry) {

		// Mostrar todos los personajes que estén vivos (propiedad -alive- igual a true) y además sean estudiantes (propiedad -hogwartsStudent- igual a true)"
		FindIterable<Document> humanos = coleccionHarry.find(and(eq("alive", true), eq("hogwartsStudent", true)));
		for(Document coleccion : humanos) {
			System.out.println(coleccion.toJson());
		}
	}

}
