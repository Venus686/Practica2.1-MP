package libClases;

public class ClienteMovil extends Cliente implements Proceso, Cloneable {
    private Fecha fPermanencia;
    private float precioMinuto;
    private float minutos;

    public ClienteMovil(String nif, String nombre, Fecha fechaNac, Fecha fechaAlta, Fecha fPer, float m, float pM) {
        super(nif, nombre, fechaNac, fechaAlta);
        fPermanencia= (Fecha)fPer.clone();
        precioMinuto = pM;
        minutos = m;
    }

    public ClienteMovil(String nif, String nombre, Fecha fechaNac, float min, float pM) {
        super(nif, nombre, fechaNac);
        Fecha f=Cliente.getFechaPorDefecto();
        fPermanencia = new Fecha(f.getDia(), f.getMes(),f.getAnio() + 1);
        precioMinuto = pM;
        minutos = min;
    }

    public ClienteMovil(ClienteMovil cm) {
        super(cm);
        minutos=cm.minutos;
        precioMinuto=cm.precioMinuto;
        fPermanencia=new Fecha(cm.fPermanencia);
    }

    public float getMinutos() {
        return minutos;
    }
    public float getPrecioMinuto() {
        return precioMinuto;
    }

    public void setMinutos(float m) {
        minutos=m;
    }
    public void setPrecioMinuto(float P) {
        precioMinuto=P;
    }
    public Fecha getFPermanencia() {
        return new Fecha(fPermanencia);
    }

    public void setFPermanencia(Fecha fPermanencia) {
        this.fPermanencia = new Fecha(fPermanencia);
    }

    public float factura() {
        return precioMinuto * minutos;
    }

    public Object clone() {
        return new ClienteMovil(super.getNif(), super.getNombre(), super.getFechaNac(), super.getFechaAlta(), fPermanencia, minutos, precioMinuto);
    }

    public String toString() {
        return super.toString() + " " + fPermanencia + " " + minutos + " x " + precioMinuto + " --> " + factura();
    }

}

