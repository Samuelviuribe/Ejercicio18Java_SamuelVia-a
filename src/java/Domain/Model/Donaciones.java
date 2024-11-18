package Domain.Model;

/**
 *
 * @author HP
 */
public class Donaciones {
    
    private String id;
    private String monto;
    private String medoto;
    private String numero_recibo;
    private String fecha_donacion;
    private String usuario_id;

public Donaciones() {
}

public Donaciones(String id, String monto, String metodo, String numero_recibo, String fecha_donacion, String usuario_id ){
    this.id = id;
    this.monto=monto;
    this.medoto= metodo;
    this.numero_recibo = numero_recibo;
    this.fecha_donacion = fecha_donacion;
    this.usuario_id = usuario_id;
}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getMedoto() {
        return medoto;
    }

    public void setMedoto(String medoto) {
        this.medoto = medoto;
    }

    public String getNumero_recibo() {
        return numero_recibo;
    }

    public void setNumero_recibo(String numero_recibo) {
        this.numero_recibo = numero_recibo;
    }

    public String getFecha_donacion() {
        return fecha_donacion;
    }

    public void setFecha_donacion(String fecha_donacion) {
        this.fecha_donacion = fecha_donacion;
    }

    public String getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(String usuario_id) {
        this.usuario_id = usuario_id;
    }

}