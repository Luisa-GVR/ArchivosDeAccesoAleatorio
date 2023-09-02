package unison;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class AgregarVendedor {
    public static void main(String[] args) throws ParseException, IOException {
        Scanner sc = new Scanner(System.in);
        final String dataPath = "ArchivosDeAccesoAleatorio-master/src/vendors-data.dat";
        final String csvPath = "ArchivosDeAccesoAleatorio-master/src/vendors.csv";

        System.out.println("Inserta el nombre");
        String nombreNuevo = sc.nextLine();
        System.out.println("Inserta la fecha como dd/mm/aaaa");
        String fecha = sc.nextLine();
        System.out.println("Inserta el nombre de la zona");
        String ciudad = sc.nextLine();
        System.out.println("Inserta las ventas mensuales");
        int ventaMensual = sc.nextInt();
        sc.close();

        Vendor fechaCreator = new Vendor();

        fechaCreator.setFecha(fecha);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = dateFormat.parse(fecha);

        long fechaPasar = date.getTime(); // convertir fecha en long

        BufferedReader length = new BufferedReader(new FileReader(csvPath));
        int tamanio = 0;

        while (length.readLine() != null) {
            tamanio++;
        }
        length.close();
        int veCodven = tamanio;


        //escribir en el csv

        RandomAccessFile randomAccessFile = new RandomAccessFile(csvPath, "rw");
        randomAccessFile.seek(randomAccessFile.length());
        String dataToAppend = "\n" + veCodven + "," + nombreNuevo + "," + fecha + "," + ciudad + "," + ventaMensual;
        randomAccessFile.writeBytes(dataToAppend);
        randomAccessFile.close();


        //escribir en el dat

        CopyCSV.main(new String[]{});

    }
}
