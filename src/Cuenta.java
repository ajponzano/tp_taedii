public class Cuenta {

    private int id;
    private int lotesAceptados;
    private int lotesRechazados;

    public Cuenta(int ID){
        this.id = ID;
        this.lotesAceptados = 0;
        this.lotesRechazados = 0;
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
        return String.format("[Proveedor] {ID: %s, Lotes Totales: %s, Lotes Aceptados: %s, Lotes Rechazados: %s}",
                this.id,
                this.getLotesTotales(),
                this.lotesAceptados,
        		this.lotesRechazados
        );
    }
}