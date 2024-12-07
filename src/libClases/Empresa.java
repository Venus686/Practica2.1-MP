
package libClases;
import java.util.Scanner;

public class Empresa implements Cloneable, Proceso{
    private Cliente[] t;
    private int n;
    private int nmax;

    private int buscar(Cliente c, int ini){
        for (int i=ini; i<n;i++){
            if (t[i].equals(c)){
                return i;
            }
        }return -1;
    }
private int buscar(String nif, int ini){
    for (int i=ini; i<n;i++){
        if (t[i].getNif().equals(nif)){
            return i;
        }
    }return -1;
}
public Empresa() {
    n=0;
    nmax=5;
    t=new Cliente[nmax];
}

public int getN() {
    return n;
}

public int getNmax() {
        return nmax;
}

public void alta(Cliente c){
    if (buscar(c,0)!=-1){
        return;
    }
    if(n==nmax){
        Cliente[] aux=t;
        nmax+=5;
        aux=new Cliente[nmax];
        for (int i=0; i<n; i++){
            aux[i] = t[i];
        }
    t = aux;

    }
    t[n]=c;
    n++;
}

public void baja(){
        int pos;
        String nif, resp;
        Scanner sc = new Scanner(System.in);
        System.out.println("Introducza el nif del cliente a dar de baja: ");
        nif = sc.nextLine();
        baja(nif);
        sc.close();
}

public void baja(String nif){
        int pos = buscar(nif,0);
        Cliente cli;
        char car;
        Scanner sc = new Scanner(System.in);
        if(pos!=-1){
            System.out.println("No se ha encontrado el nif: ");
            return;
        }
        Cliente c;
        cli = t[pos];
        cli.ver();
         System.out.println("¿Seguro que desea eliminarlo (s/n)? ");
        car = sc.next().charAt(0);
        if(car=='s'){
            for(int i = pos; i<n -1; i++){
                t[i] = t[i+1];
            }
            t[n-1]=null;
            n--;
        }
        if(n % 5 == 0 && nmax !=5){
            Cliente[] aux=t;
            nmax-=5;
            aux=new Cliente[nmax];
            for (int i=0; i<n; i++){
                aux[i] = t[i];
            }
            t = aux;

        }
        sc.close();

}



    public void alta(){
        String nif, nombre, nacionalidad;
        int tipo, pos;
        float minutos;
        float precio;
        Fecha fnac, falta, ffin;
        Cliente c;
        Scanner sc = new Scanner(System.in); // No cerrar este Scanner
        System.out.println("Dni: ");
        nif = sc.nextLine();
        pos = buscar(nif, 0);
        if (pos != -1){
            System.out.println("Ya existe un Cliente con ese dni ");
        } else {
            System.out.println("Nombre: ");
            nombre = sc.nextLine();
            System.out.println("Fecha de nacimiento: ");
            fnac = Fecha.pedirFecha(); // Usa pedirFecha sin cerrar Scanner
            System.out.println("Fecha de Alta: ");
            falta = Fecha.pedirFecha();
            System.out.println("Minutos que habla al mes: ");
            minutos = sc.nextFloat();
            System.out.println("Indique tipo de cliente (1-Movil, 2-TarifaPlana): ");
            tipo = sc.nextInt();
            if(tipo == 1){
                System.out.println("Precio por minuto: ");
                precio = sc.nextFloat();
                System.out.println("Fecha fin de permanencia: ");
                ffin = Fecha.pedirFecha();
                c = new ClienteMovil(nif, nombre, fnac, falta, ffin, minutos, precio);
            } else {
                System.out.println("Nacionalidad: ");
                nacionalidad = sc.next();
                c = new ClienteTarifaPlana(nif, nombre, fnac, falta, minutos, nacionalidad);
            }
            alta(c);
        }
        // No cerrar Scanner aquí
    }


    public String toString() {
        String clien ="";
        for(int i=0 ; i<n ; i++){
            clien += t[i].toString()+"\n";
        }
        return clien;
        }

    @Override
    public void ver() {
        System.out.println(toString());
    }

    public float factura(){
        float factura=0;
        ClienteMovil cm;
        ClienteTarifaPlana ct;
        for(int i = 0 ; i<n ; i++){
            if(t[i].getClass() == ClienteMovil.class){
                cm=(ClienteMovil)t[i];
                factura += cm.factura();
            }else{
                ct=(ClienteTarifaPlana)t[i];
                factura += ct.factura();
            }
        }
        return factura;
    }

    public Object clone(){
        Empresa e = new Empresa();
        for(int i=0 ; i<n ; i++){
            e.alta(t[i]);
        }
        return e;
    }

    public int nClienteMovil(){
        int nCM = 0;
        for(int i=0 ; i<n ; i++){
            if(t[i].getClass() == ClienteMovil.class){
                nCM++;
            }
        }
        return nCM;
    }

    public void descuento(int desc){
        ClienteMovil cm;
        for(int i=0 ; i<n ; i++){
            if(t[i].getClass() == ClienteMovil.class){
                cm = (ClienteMovil)t[i];
                cm.setPrecioMinuto(cm.getPrecioMinuto()*(100-desc)/100);
            }
        }
    }


}