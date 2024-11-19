package Domain.Model;

/**
 *
 * @author HP
 */
public class Donaciones {
    
    private int id;
    private String monto;
    private String metodo;
    private String numero_recibo;
    private String fecha_donacion;
    private int usuario_id;

public Donaciones() {
}

public Donaciones(int id, String monto, String metodo, String numero_recibo, String fecha_donacion, int usuario_id ){
    this.id = id;
    this.monto=monto;
    this.metodo= metodo;
    this.numero_recibo = numero_recibo;
    this.fecha_donacion = fecha_donacion;
    this.usuario_id = usuario_id;
}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
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

    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }

}