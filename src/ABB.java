public class ABB {
	
	Nodo raiz;
	
	public ABB () {
		this.raiz = null;
	}
	
	///---------------------INSERTAR-------------------///
	
	public void insertar(double valor, String datos) {
        raiz = insertarR(this.raiz, valor, datos);
    }
	
	protected Nodo insertarR(Nodo nodoActual, double valor, String datos) {
		
		if (nodoActual == null) {
            return (new Nodo(valor, datos));
        }
        
        if (valor < nodoActual.valor) {
            nodoActual.izquierda = insertarR(nodoActual.izquierda, valor, datos);
        }else if (valor > nodoActual.valor) {
            nodoActual.derecha = insertarR(nodoActual.derecha, valor, datos);
        }else {		// Si el valor esta duplicado retorna el mismo nodo encontrado
            return nodoActual;
        }
        
        return nodoActual;
         
    }
	
	///---------------------ELIMINAR-------------------///
	
	public void eliminar(double valor) {
        raiz = eliminarR(this.raiz, valor);
    }
	
	protected Nodo eliminarR(Nodo nodoActual, double valor) {
		if (nodoActual == null) {
			return nodoActual;
		}
		
        if (valor < nodoActual.valor){
            nodoActual.izquierda = eliminarR(nodoActual.izquierda, valor);
        }else if (valor > nodoActual.valor){
            nodoActual.derecha = eliminarR(nodoActual.derecha, valor);
        }else {
            //El nodo es igual al valor, se elimina
            //Nodo con un unico hijo o es hoja
            if ((nodoActual.izquierda == null) || (nodoActual.derecha == null)) {
                Nodo temp = null;
                if (temp == nodoActual.izquierda) {
                    temp = nodoActual.derecha;
                }else {
                    temp = nodoActual.izquierda;
                }
 
                ///CASO NODO SIN HIJOS
                if (temp == null) {
                    nodoActual = null;		//Se elimina dejandolo en null
                }else{
                ///CASO NODO CON UN HIJO
                    nodoActual = temp;		//Elimina el valor actual reemplazandolo por su hijo
                }
            }else {
                ///CASO NODO CON 2 HIJOS
                Nodo temp = getNodoConValorMaximo(nodoActual.izquierda);
                
                ///Se copia el dato del predecesor
                nodoActual.valor = temp.valor;
 
                ///Se elimina el predecesor
                nodoActual.izquierda = eliminarR(nodoActual.izquierda, temp.valor);
            }
        }
 
        //Si solo tiene un nodo
        if (nodoActual == null) {
        	return nodoActual;
        }
 
 
        return nodoActual;
		
	}

	public Nodo getNodoConValorMaximo(Nodo nodo) {
        Nodo current = nodo;
        
        while (current.derecha != null){
           current = current.derecha;
        }
        
        return current;
    }
    
	///---------------------MOSTRAR-------------------///
	
	public void mostrarArbol() {
		System.out.println("");
        System.out.println("Arbol:");
        mostrarArbolR(raiz, 0);
    }

    protected void mostrarArbolR(Nodo nodo, int profundidad) {
        if (nodo.izquierda != null) {
        	mostrarArbolR(nodo.izquierda, profundidad + 1);
        }
        System.out.print(String.format("%1$s,", nodo.valor));
        if (nodo.derecha != null) {
        	mostrarArbolR(nodo.derecha, profundidad + 1);
        }
    }
	
}
