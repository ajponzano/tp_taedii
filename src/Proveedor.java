public class Proveedor {

    private String nombre;
    private int id;
    private int lotesAceptados;
    private int lotesRechazados;

    public Proveedor(int ID){
        this.id = ID;
        this.lotesAceptados = 0;
        this.lotesRechazados = 0;

        switch (this.id){
            case 1:
                this.nombre = "Terrabusi";
                break;
            case 2:
                this.nombre = "Nestle";
                break;
            case 3:
                this.nombre = "Arcor";
                break;
            case 4:
                this.nombre = "Granix";
                break;
            case 5:
                this.nombre = "Georgalos";
                break;
        }
    }

    public String getNombre() {
        return nombre;
    }
    
    public int getId() {
        return id;
    }
    
    public void incrementLotesAceptados() {
    	this.lotesAceptados += 1;
    }
    
    public void incrementLotesRechazados() {
    	this.lotesRechazados += 1;
    }
    
    public int getLotesAceptados() {
    	return lotesAceptados;
    }
    
    public int getLotesRechazados() {
    	return lotesRechazados;
    }
    
    public int getLotesTotales() {
    	return lotesAceptados + lotesRechazados;
    }
    
    public double getPrcAceptados() {
    	return (double)lotesAceptados / (double)getLotesTotales() * 100;
    }
    
    public double getPrcRechazados() {
    	return (double)lotesRechazados / (double)getLotesTotales() * 100;
    }

    public String toString(){
        return String.format("[Proveedor] {ID: %s, Nombre: %s, Lotes Totales: %s, Lotes Aceptados: %s, Lotes Rechazados: %s}",
                this.id,
                this.nombre,
                this.getLotesTotales(),
                this.lotesAceptados,
        		this.lotesRechazados
        );
    }
}