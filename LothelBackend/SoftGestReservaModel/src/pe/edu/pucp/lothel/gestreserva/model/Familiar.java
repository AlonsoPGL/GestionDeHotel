package pe.edu.pucp.lothel.gestreserva.model;

/**
 *
 * @author efeproceres
 */
public class Familiar extends Habitacion{
    private boolean cocheraPropia;

    public Familiar() {
    }

    public Familiar(boolean cocheraPropia, int piso, int numeroDeCamas, double precio, boolean reservado,byte[] imagen,
            String titulo,String descripcion,int cantHuespedes,int stock) {
        super(piso, numeroDeCamas, precio, reservado,imagen,titulo,descripcion,cantHuespedes,stock);
        this.cocheraPropia = cocheraPropia;
    }

    public boolean getCocheraPropia() {
        return cocheraPropia;
    }

    public void setCocheraPropia(boolean cocheraPropia) {
        this.cocheraPropia = cocheraPropia;
    }
}
