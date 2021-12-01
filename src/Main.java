import java.util.Scanner;
import java.util.EnumSet;

public class Main {
	
	private static Scanner keyboard;
	private static Lote[] datos;
	
	Main() {
		keyboard = new Scanner(System.in);
		menuOptions();
	}

	private static void menuOptions() {		
		Boolean run = true;

		do {
			menu();

			while(!keyboard.hasNextInt()) {
				keyboard.next();
			}
			int menuItem = keyboard.nextInt();

			switch(menuItem) {
			case 1:
				System.out.print("\n Simulando datos...\n");
				datos = generarLotes(2000);
				System.out.print(" " + datos.length + " lotes generados.\n");
				break;
			case 2:
				System.out.print("\n  Analizando...\n");
				analizarDatos(datos);
				break;
			case 3:
				System.out.print("\n  Ranking...\n");
				break;
			case 4:
				run = false;
				break;
			default:
				System.out.println("  Selección erronea");
				break;
			}
		} while(run);
	}
	
	private static void menu() {
		System.out.println("-----------------------------------------");
		System.out.println(" Evaluación de proveedores\n");
		System.out.println("  Solo se aceptan números enteros.\n");
		System.out.println("  1. Simular datos");
		System.out.println("  2. Analizar datos");
		System.out.println("  3. Mostrar Ranking de proveedores");
		System.out.println("  4. Salir");
		System.out.println("-----------------------------------------");
	}
	
	public static void main (String args[]) {
		new Main();
	}
	
	private static Lote[] generarLotes(int lotesMax) {
		Lote[] lotes = new Lote[lotesMax];

		for (int i=0; i<lotesMax; i++) {
			lotes[i] = new Lote(i+1);
			lotes[i].evaluarRendimiento();
			System.out.println(lotes[i].toString());
		}
		
		return lotes;
	}
	
	private static void analizarDatos(Lote[] lotes) {
		Proveedor[] listaProveedores = new Proveedor[5];
		
		for (int i=0; i<5; i++) {
			listaProveedores[i] = new Proveedor(i+1);
		}
		
		for (int i=0; i<lotes.length; i++) {
			if (lotes[i].getPorcentajeRechazo() < 0.2) {
				listaProveedores[lotes[i].getProveedor()-1].incrementLotesRechazados();
			}
			else {
				listaProveedores[lotes[i].getProveedor()-1].incrementLotesAceptados();
			}
		}
		
		for (int i=0; i<5; i++) {
			System.out.println(listaProveedores[i].toString());
		}
//		Cantidad de proveedores: 5
//		Cantidad de Productos: 6
//		Tamaño de lote máximo: 1000
//		Tamaño de lote mínimo: 500
//		Cantidad máxima de productos defectuosos por lote: 200
//		Todos los proveedores pueden suministrar cualquiera de los 6 productos a evaluar.
		
	}
	
//	private enum Proveedores {
//		TERRABUSI(1,"Terrabusi"),
//		NESTLE(2,"Nestle"),
//		ARCOR(3,"Arcor"),
//		GRANIX(4,"Granix"),
//		GEORGALOS(5,"Georgalos");
//		
//		private int index;
//		private String name;
//
//	    Proveedores(int index, String name) {
//	        this.index = index;
//	        this.name = name;
//	    }
//	    
//	    public String getName() {
//	        return this.name;
//	    }
//	    
//	    public static EnumSet<Proveedores> getAll() {
//	    	return EnumSet.allOf(Proveedores.class);
//	    }
//	}
//	
//	private enum Productos {
//		TERRABUSI(1,"Galletitas"),
//		NESTLE(2,"Barras Cereal"),
//		ARCOR(3,"Dulce"),
//		GRANIX(4,"Cafe"),
//		GEORGALOS(5,"Alfajor"),
//		GEORGALOS(6,"Caramelos");
//		
//		private int index;
//		private String name;
//
//		Productos(int index, String name) {
//	        this.index = index;
//	        this.name = name;
//	    }
//	    
//	    public String getName() {
//	        return this.name;
//	    }
//	}
}
