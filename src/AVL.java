public class AVL extends ABB{

	public AVL() {
		super();
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
        }else {		///Si el valor esta duplicado retorna el mismo nodo encontrado
            return nodoActual;
        }
        ///ACTUALIZACION DE LA ALTURA
        nodoActual.altura = 1 + max(getAltura(nodoActual.izquierda), getAltura(nodoActual.derecha));
 
        ///Se obtiene el Factor de equilibrio para determinar posibles rotaciones
        int fe = getFactorEquilibrio(nodoActual);

        ///CASO ROTACION SIMPLE DERECHA
        if (fe > 1 && valor < nodoActual.izquierda.valor) {
            return rotarD(nodoActual);
        }
 
        ///CASO ROTACION SIMPLE IZQUIERDA
        if (fe < -1 && valor > nodoActual.derecha.valor) {
            return rotarI(nodoActual);
        }
        ///CASO ROTACION DOBLE DEERECHA
        if (fe > 1 && valor > nodoActual.izquierda.valor) {
            nodoActual.izquierda = rotarI(nodoActual.izquierda);
            return rotarD(nodoActual);
        }
        ///CASO ROTACION DOBLE IZQUIERDA
        if (fe < -1 && valor < nodoActual.derecha.valor) {
            nodoActual.derecha = rotarD(nodoActual.derecha);
            return rotarI(nodoActual);
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
                    nodoActual = null;//Se elimina dejandolo en null
                }else{
                ///CASO NODO CON UN HIJOS
                    nodoActual = temp;//Elimina el valor actual reemplazandolo por su hijo
                }
            }else {
            	///CASO NODO CON 2 HIJOS
                Nodo temp = getNodoConValorMaximo(nodoActual.izquierda);
                
                //Se copia el dato del predecesor
                nodoActual.valor = temp.valor;
 
                //Se elimina el predecesor
                nodoActual.izquierda = eliminarR(nodoActual.izquierda, temp.valor);
            }
        }
 
        //Si solo tiene un nodo
        if (nodoActual == null) {
        	return nodoActual;
        }
        
        ///ACTUALIZACION DE LA ALTURA
        nodoActual.altura = max(getAltura(nodoActual.izquierda), getAltura(nodoActual.derecha)) + 1;
 
        ///Se obtiene el Factor de equilibrio para determinar posibles rotaciones
        int fe = getFactorEquilibrio(nodoActual);
 
        ///CASO ROTACION SIMPLE DERECHA
        if (fe > 1 && getFactorEquilibrio(nodoActual.izquierda) >= 0) {
            return rotarD(nodoActual);
        }
 
        ///CASO ROTACION SIMPLE IZQUIERDA
        if (fe < -1 && getFactorEquilibrio(nodoActual.derecha) <= 0) {
            return rotarI(nodoActual);
        }
 
        ///CASO ROTACION DOBLE DEERECHA
        if (fe > 1 && getFactorEquilibrio(nodoActual.izquierda) < 0) {
            nodoActual.izquierda = rotarI(nodoActual.izquierda);
            return rotarD(nodoActual);
        }
        
        ///CASO ROTACION DOBLE IZQUIERDA
        if (fe < -1 && getFactorEquilibrio(nodoActual.derecha) > 0) {
            nodoActual.derecha = rotarD(nodoActual.derecha);
            return rotarI(nodoActual);
        }
 
        return nodoActual;
		
	}
	
	public Nodo getNodoConValorMaximo(Nodo node) {
        Nodo current = node;
        
        while (current.derecha != null){
           current = current.derecha;
        }
        
        return current;
    }
	
	///---------------------MOSTRAR-------------------///
	
	public void mostrarArbol() {
		System.out.println("");
        System.out.println("Arbol AVL:");
        mostrarArbolR(raiz, 0);
    }

    protected void mostrarArbolR(Nodo nodo, int profundidad) {
        if (nodo.izquierda != null) {
        	mostrarArbolR(nodo.izquierda, profundidad + 1);
        }
        System.out.print(String.format("%1$s(%2$s),", nodo.valor, getFactorEquilibrio(nodo)));
        if (nodo.derecha != null) {
        	mostrarArbolR(nodo.derecha, profundidad + 1);
        }
    }
    
    public void mostrar() {
    	mostrarR(raiz, 0);
    }
    
    protected void mostrarR(Nodo nodo, int profundidad) {
        if (nodo.izquierda != null) {
        	mostrarR(nodo.izquierda, profundidad + 1);
        }
        System.out.print("    " + nodo.datos + "\t");
        System.out.println(String.format("%.2f", nodo.valor));
        if (nodo.derecha != null) {
        	mostrarR(nodo.derecha, profundidad + 1);
        }
    }

    ///---------------------ROTACIONES-------------------///
    
	///ROTACION SIMPLE DERECHA
    private Nodo rotarD(Nodo nodoActual) {
        Nodo nuevaRaiz = nodoActual.izquierda;
        Nodo temp = nuevaRaiz.derecha;
 
        /// Se realiza la rotacion
        nuevaRaiz.derecha = nodoActual;
        nodoActual.izquierda = temp;
 
        /// Se actualizan las alturas
        nodoActual.altura = max(getAltura(nodoActual.izquierda), getAltura(nodoActual.derecha)) + 1;
        nuevaRaiz.altura = max(getAltura(nuevaRaiz.izquierda), getAltura(nuevaRaiz.derecha)) + 1;
 
        return nuevaRaiz;
    }
 
    ///ROTACION SIMPLE IZQUIERDA
    private Nodo rotarI(Nodo nodoActual) {
        Nodo nuevaRaiz = nodoActual.derecha;
        Nodo temp = nuevaRaiz.izquierda;
 
        /// Se realiza la rotacion
        nuevaRaiz.izquierda = nodoActual;
        nodoActual.derecha = temp;
 
        /// Se actualizan las alturas
        nodoActual.altura = max(getAltura(nodoActual.izquierda), getAltura(nodoActual.derecha)) + 1;
        nuevaRaiz.altura = max(getAltura(nuevaRaiz.izquierda), getAltura(nuevaRaiz.derecha)) + 1;
        
        return nuevaRaiz;
    }
	
    ///---------------------ALTURA Y FE-------------------///

    protected int getAltura(Nodo nodoActual) {
        if (nodoActual == null) {
            return 0;
        }
 
        return nodoActual.altura;
    }
    
    protected int getFactorEquilibrio(Nodo nodoActual) {
        if (nodoActual == null) {
            return 0;
        }
 
        return getAltura(nodoActual.izquierda) - getAltura(nodoActual.derecha);
    }
    
    
    
    protected int max(int a, int b) {
        return (a > b) ? a : b;
    }
   
}
