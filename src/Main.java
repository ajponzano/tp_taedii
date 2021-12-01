import java.util.Scanner;

public class Main {

	private static Scanner keyboard;
	private static Lote[] lotes;
	private static AVL ranking = new AVL();

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
				lotes = generarLotes(2000);
				System.out.print(" " + lotes.length + " lotes generados.\n");
				break;
			case 2:
				System.out.print("\n  Analizando...\n");
				analizarDatos(lotes);
				break;
			case 3:
				System.out.print("\n  Ranking de proveedores (% aprobados sobre total de lotes)\n");
				ranking.mostrar();
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
			//System.out.println(lotes[i].toString());
		}
		
		return lotes;
	}

	private static void analizarDatos(Lote[] lotes) {
		Proveedor[] proveedores = new Proveedor[5];

		// Inicializar lista de proveedores
		for (int i=0; i<5; i++) {
			proveedores[i] = new Proveedor(i+1);
		}

		// Contar lotes según proveedor
		for (int i=0; i<lotes.length; i++) {
			if (lotes[i].getPorcentajeRechazo() < 0.2) {
				proveedores[lotes[i].getProveedor()-1].incrementLotesRechazados();
			}
			else {
				proveedores[lotes[i].getProveedor()-1].incrementLotesAceptados();
			}
		}

		// Rankear
		for (int i=0; i<5; i++) {
			//System.out.print(proveedores[i].toString());
			//System.out.println(" " + proveedores[i].getNombre() + " " + String.format("%.2f", proveedores[i].getPrcAceptados()));
			ranking.insertar(proveedores[i].getPrcAceptados(), proveedores[i].getNombre());
		}
	}
}
