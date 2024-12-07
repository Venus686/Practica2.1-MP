package libClases;

public class ClienteTarifaPlana extends Cliente {
    private static float tarifa = 20;
    private static float limiteMinutos = 300;
    private float minutos;
    private String nacionalidad;

    public ClienteTarifaPlana(String nif, String nombre, Fecha fechaNac, Fecha fechaAlta, float minutos, String nacionalidad) {
        super(nif, nombre, fechaNac, fechaAlta);
        this.minutos = minutos;
        this.nacionalidad = nacionalidad;
    }

    public ClienteTarifaPlana(String nif, String nombre, Fecha fechaNac, float minutos, String nacionalidad) {
        super(nif, nombre, fechaNac);
        this.minutos = minutos;
        this.nacionalidad = nacionalidad;
    }
    protected ClienteTarifaPlana(ClienteTarifaPlana c) {
        super(c);
        this.minutos = c.minutos;
        this.nacionalidad = c.nacionalidad;
    }

    //getters
    public float getMinutos() {
        return minutos;
    }
    public String getNacionalidad() {
        return nacionalidad;
    }
    public static float getLimite() {
        return limiteMinutos;
    }

    public static float getTarifa() {
        return tarifa;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }
    //setters
    public void setMinutos(float minutos) {
        this.minutos = minutos;
    }

    public static void setTarifa(float limite, float nuevaTarifa) {
        limiteMinutos = limite;
        tarifa = nuevaTarifa;
    }


    public float factura() {
        float excesoMinutos = minutos - limiteMinutos;
        if (excesoMinutos < 0) {excesoMinutos =0;}
        return (tarifa + excesoMinutos*0.15f);
    }

    @Override
    public Object clone() {
        return new ClienteTarifaPlana(this);
    }

    @Override
    public String toString() {
        String s=super.toString();
        s=s+ " " +nacionalidad+" ["+limiteMinutos+ " por "+ tarifa+ " ] "+ minutos + " --> " + factura();
        return s;
    }
}

