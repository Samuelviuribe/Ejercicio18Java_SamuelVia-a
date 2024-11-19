package Domain.Model;

/**
 *
 * @author HP
 */
public class Donaciones {
    private int id;
    private float monto;
    private String metodo;
    private int numeroRecibo;
    private String fechaDonacion;
    private String cedula;
    private int usuarioId;


    public Donaciones(int id, float monto, String metodo, int numeroRecibo, String fechaDonacion, String cedula) {
        this.id = id;
        this.monto = monto;
        this.metodo = metodo;
        this.numeroRecibo = numeroRecibo;
        this.fechaDonacion = fechaDonacion;
        this.cedula = cedula; // Inicializar cédula
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public int getNumeroRecibo() {
        return numeroRecibo;
    }

    public void setNumeroRecibo(int numeroRecibo) {
        this.numeroRecibo = numeroRecibo;
    }

    public String getFechaDonacion() {
        return fechaDonacion;
    }

    public void setFechaDonacion(String fechaDonacion) {
        this.fechaDonacion = fechaDonacion;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
 
     

}