
package libClases;
import java.util.Scanner;

public class Empresa implements Cloneable, Proceso{
    private Cliente[] clientes;
    private int n;
    private int nmax;

    private int buscar(Cliente c){
        for (int i=0; i<n;i++){
            if (clientes[i].equals(c)){
                return i;
            }
        }return -1;
    }
private int buscar(String nif){
    for (int i=0; i<n;i++){
        if (clientes[i].getNif().equals(nif)){
            return i;
        }
    }return -1;
}
public Empresa() {
    n=0;
    nmax=5;
    clientes=new Cliente[nmax];
}

public int getN() {
    return n;
}

public int getNmax() {
        return nmax;
}

public void alta(Cliente c){
    if (buscar(c)!=-1){
        return;
    }
    if(n==nmax){
        Cliente[] aux=clientes;
        nmax+=5;
        aux=new Cliente[nmax];
        for (int i=0; i<n; i++){
            aux[i] = clientes[i];
        }
    clientes = aux;

    }
    clientes[n]=c;
    n++;
}

    public void baja() {
        String nif, nombre, resp;
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduzca el nif de cliente para dar de baja: ");
        nif = sc.nextLine();
        int pos = buscar(nif);
        if (pos != -1) {
            clientes[pos].ver();
            System.out.println("¿Seguro que desea eliminarlo (s/n): ");
            resp = sc.nextLine();
            if (resp.equals("s")) {
                System.out.println("El cliente " +clientes[pos].getNombre() + " con nif " + nif + " ha sido eliminado. \n");
                baja(nif);
            } else {
                System.out.println("El cliente con nif " + clientes[pos].getNif() + " no se elimina. \n");
            }
        }
    }

    public void baja(String dni){
        int pos = buscar(dni);
        Cliente cli;
        char car;
        Scanner sc = new Scanner(System.in);
        if (pos == -1) {
           // System.out.println("No se ha encontrado el nif: ");
            return;
        }
        for (int i = pos; i < n - 1; i++) {
                clientes[i] = clientes[i + 1];
        }
        clientes[n - 1] = null;
        n--;
        if (n % 5 == 0 && nmax != 5) {
            Cliente[] aux = clientes;
            nmax -= 5;
            aux = new Cliente[nmax];
            for (int i = 0; i < n; i++) {
                aux[i] = clientes[i];
            }
            clientes = aux;

        }
    }


    public void alta () {
        String nif, nombre, nacionalidad;
        int tipo, pos;
        float minutos;
        float precio;
        Fecha fnac, falta, ffin;
        Cliente c;
        Scanner sc = new Scanner(System.in); // No cerrar este Scanner
        System.out.println("Dni: ");
        nif = sc.nextLine();
        pos = buscar(nif);
        if (pos != -1) {
            System.out.println("Ya existe un Cliente con ese dni ");
            clientes[pos].ver();
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
            if (tipo == 1) {
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


    public String toString () {
        String clien = "";
        for (int i = 0; i < n; i++) {
            clien += clientes[i].toString() + "\n";
        }
        return clien;
    }

    @Override
    public void ver () {
        System.out.println(toString());
    }

    public float factura () {
        float factura = 0;
        ClienteMovil cm;
        ClienteTarifaPlana ct;
        for (int i = 0; i < n; i++) {
            if (clientes[i].getClass() == ClienteMovil.class) {
                cm = (ClienteMovil) clientes[i];
                factura += cm.factura();
            } else {
                ct = (ClienteTarifaPlana) clientes[i];
                factura += ct.factura();
            }
        }
        return factura;
    }

    public Object clone () {
        Empresa emp= new Empresa();
        ClienteMovil cm;
        ClienteTarifaPlana ct;
        for (int i = 0; i < n; i++) {
            if(clientes[i].getClass()==ClienteMovil.class){
                cm = new ClienteMovil((ClienteMovil) clientes[i]);
                emp.alta(cm);
            }else{
                ct = new ClienteTarifaPlana((ClienteTarifaPlana) clientes[i]);
                emp.alta(ct);
            }

        }
        return emp;
    }

    public int nClienteMovil () {
        int nCM = 0;
        for (int i = 0; i < n; i++) {
            if (clientes[i].getClass() == ClienteMovil.class) {
                nCM++;
            }
        }
        return nCM;
    }

    public void descuento(int desc) {
        for (int i = 0; i < n; i++) {
            if (clientes[i].getClass() == ClienteMovil.class) { // Comparación directa de clases
                ClienteMovil cm = (ClienteMovil) clientes[i]; // Cast seguro
                // Aplicar el descuento directamente
                float precioConDescuento = cm.getPrecioMinuto() * (1 - desc / 100.0f);
                cm.setPrecioMinuto(precioConDescuento);
            }
        }
    }


}