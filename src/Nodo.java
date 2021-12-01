public class Nodo {
	
	Nodo izquierda;
    Nodo derecha;
    double valor;
	int altura;
	String datos;
 
    Nodo(double valor, String datos) {
        this.valor = valor;
        this.datos = datos;
        altura = 1;
    }

}
