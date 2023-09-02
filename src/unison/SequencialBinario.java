package unison;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;

public class SequencialBinario {
    public static void main(String[] args) throws IOException {

        final String dataPath = "vendors-data.dat";
        final String csvPath = "vendors.csv";

        RandomVendorFile randomFile = new RandomVendorFile(dataPath);
        Vendor vendor;

        BufferedReader length = new BufferedReader(new FileReader(csvPath));
        int tamanio =0;

        while(length.readLine() != null){
            tamanio++;
        }


        long t1 = System.currentTimeMillis();
        for(int i=1; i< tamanio-1; i++) {
            int pos = i * Vendor.RECORD_LEN;
            vendor = randomFile.read(pos);
            System.out.println( vendor.getNombre() + ", " + vendor.getZona() + ", " + vendor.getMensual());
        }
        long t2 = System.currentTimeMillis();

        long rt1 = t2 - t1;

        Vendor vendorArray[] = new Vendor[tamanio-1];

        t1 = System.currentTimeMillis();

        randomFile.read(vendorArray);
        Hashtable<String,Integer> contadores = new Hashtable<>();
        for (Vendor v: vendorArray) {
            System.out.println(v);
        }
        t2 = System.currentTimeMillis();

        long rt2 = t2 - t1;
        System.out.printf("%nTiempos de ejecución:%nT1: %d T2: %d%n",rt1,rt2);
    }
}
