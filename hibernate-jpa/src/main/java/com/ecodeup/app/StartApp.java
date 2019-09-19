package com.ecodeup.app;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.ecodeup.model.Mascota;

public class StartApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int opcion = 0;
		Scanner scanner = new Scanner(System.in);
		Mascota mimascota;

		EntityManager entity = JPAutil.getEntityManagerFactory().createEntityManager();
		while (opcion!=5) {
			System.out.println("1. Crear Mascota");
			System.out.println("2. Buscar Mascota");
			System.out.println("3. Actualizar Mascota");
			System.out.println("4. Eliminar Mascota");
			System.out.println("5. Salir");
			System.out.println("Elija una opción:");

			opcion = scanner.nextInt();
			switch (opcion) {
			case 1:
				System.out.println("Digite el nombre del producto:");
				mimascota = new Mascota();
				mimascota.setId(null);
				scanner.nextLine();
				mimascota.setNombre(scanner.nextLine());

				System.out.println("Digite el precio del producto:");
				mimascota.setVacunas(scanner.toString());
				System.out.println(mimascota);
				
				entity.getTransaction().begin();
				entity.persist(mimascota);
				entity.getTransaction().commit();
				
				System.out.println("Producto registrado..");
				System.out.println();
				break;

			case 2:
				System.out.println("Digite el id del producto a buscar:");
				mimascota = new Mascota();
				mimascota = entity.find(Mascota.class, scanner.nextLong());
				if (mimascota != null) {
					System.out.println(mimascota);
					System.out.println();
				} else {
					System.out.println();
					System.out.println("Producto no encontrado... Lista de productos completa");
					
					List<Mascota> listamascota= new ArrayList<>();
					
					Query query=entity.createQuery("SELECT p FROM Producto p");
					listamascota=query.getResultList();
					for (Mascota p : listamascota) {
						System.out.println(p);
					}
					
					System.out.println();
				}

				break;
			case 3:
				System.out.println("Digite el id del de mascota a actualizar:");
				mimascota = new Mascota();

				mimascota = entity.find(Mascota.class, scanner.nextLong());
				if (mimascota != null) {
					System.out.println(mimascota);
					System.out.println("Digite el nombre de la mascota:");
					scanner.nextLine();
					mimascota.setNombre(scanner.nextLine());
					System.out.println("Digite la vacuna que requiere:");
					mimascota.setVacunas(scanner.toString());
					
					entity.getTransaction().begin();
					entity.merge(mimascota);
					entity.getTransaction().commit();
					
					System.out.println("Mascota actualizado..");
					System.out.println();
				} else {
					System.out.println("Mascota no encontrado....");
					System.out.println();
				}
				break;
			case 4:
				System.out.println("Digite el id del producto a eliminar:");
				mimascota = new Mascota();

				mimascota = entity.find(Mascota.class, scanner.nextLong());
				if (mimascota != null) {
					System.out.println(mimascota);
					
					entity.getTransaction().begin();
					entity.remove(mimascota);
					entity.getTransaction().commit();
					
					System.out.println("Mascota eliminado...");
				} else {
					System.out.println("Producto no encontrado...");
				}
				break;
			case 5:entity.close();JPAutil.shutdown();
			break;

			default:
				System.out.println("Opción no válida\n");
				break;
			}
		}
	}

}
