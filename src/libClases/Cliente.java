package libClases;
import libClases.*;

import java.io.Serializable;

public class Cliente implements Cloneable,Proceso{
    private final String nif; //dni del cliente (letra incluida) (NO puede cambiar)
    private final int codCliente;//codigo único (y fijo) generado por la aplicación
    private String nombre; //nombre completo del cliente (SI se puede modificar)
    private final Fecha fechaNac; //fecha nacimiento del cliente (NO se puede cambiar)
    private final Fecha fechaAlta; //fecha de alta del cliente (SI se puede modificar)
    private static int contCliente = 1;
    private static final Fecha FechaAltaPorDefecto=new Fecha(1,1,2018);

    public Cliente(String NIF, String nom, Fecha fNac, Fecha fAlta) {
        this.nif = NIF;
        this.codCliente = contCliente++;
        this.nombre = nom;
        this.fechaNac=new Fecha(fNac);
        this.fechaAlta= new Fecha(fAlta);
    }

    public Cliente(String NIF, String nom, Fecha fNac) {
        this.nif = NIF;
        this.nombre = nom;
        this.codCliente = contCliente++;
        this.fechaNac= new Fecha(fNac);
        this.fechaAlta= new Fecha(FechaAltaPorDefecto);
    }

    public Cliente(Cliente c) {
        this.nif = c.getNif();
        this.codCliente = contCliente++;
        this.nombre = c.getNombre();
        this.fechaNac = new Fecha(c.getFechaNac());
        this.fechaAlta=new Fecha(c.getFechaAlta());

    }

    public void setFechaAlta(Fecha f) {
        fechaAlta.setFecha(f.getDia(),f.getMes(),f.getAnio());

    }
    public static Fecha getFechaPorDefecto() {
        Fecha f=new Fecha(FechaAltaPorDefecto);
        return f;
    }

    //getters
    public String getNif() {
        return nif;
    }

    public int getCodCliente() {
        return codCliente;
    }

    public String getNombre() {
        return nombre;
    }
    public Fecha getFechaNac() {
        Fecha f=new Fecha(fechaNac);
        return f;
    }

    public Fecha getFechaAlta() {
        Fecha f=new Fecha(fechaAlta);
        return f;
    }

    //setters
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
   public void setFechaAlta(int dia, int mes,int anio){
        this.fechaAlta.setFecha(dia,mes,anio);
   }
  public static void setFechaPorDefecto(Fecha f){
       FechaAltaPorDefecto.setFecha(f.getDia(),f.getMes(),f.getAnio());
    }

    public String toString() {
        String s = nif + " " + fechaNac + ": " + nombre + " (" + codCliente + " - " + fechaAlta + ")";
        return s;
    }
    public void ver() {
        System.out.println(this.toString());
    }

    @Override
    public Object clone() {
            Fecha fechanaci= new Fecha(this.fechaNac.getDia(),this.fechaNac.getMes(),this.fechaNac.getAnio());
            Fecha fechaal =new Fecha(this.fechaAlta.getDia(),this.fechaAlta.getMes(),this.fechaAlta.getAnio());
            return new Cliente(this.nif,this.nombre,fechanaci,fechaal);
    }

    @Override
    public boolean equals(Object obj) { //true sin son iguales
        if (this == obj) return true; //si apuntan al mismo sitio son iguales
        if (obj == null) return false;
        if (getClass() != obj.getClass())//if (!(obj instanceof Cliente))
            return false; // si los 2 no son de la misma clase no son iguales
        Cliente c = (Cliente) obj;
        return (nif.equals(c.nif));
        //return (nif == c.nif); se compara las referencias de los objetos
    }

}