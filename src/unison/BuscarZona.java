package unison;

import java.util.Scanner;

public class BuscarZona {
    public static void main(String[] args) {

        final String csvPath = "vendors.csv";
        Scanner sc = new Scanner(System.in);

        System.out.println("Escribe la zona que desees buscar: ");
        String zona = sc.nextLine();


        VendorCSVFile prueba = new VendorCSVFile(csvPath);
        System.out.println(prueba.findZone(zona));


    }
}
