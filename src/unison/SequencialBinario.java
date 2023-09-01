package unison;

import java.util.Hashtable;

public class SequencialBinario {
    public static void main(String[] args) {

        final String dataPath = "vendors-data.dat";

        RandomVendorFile randomFile = new RandomVendorFile(dataPath);
        Vendor vendor;

        long t1 = System.currentTimeMillis();
        for(int i=1; i<= 100; i++) {
            int pos = (i * Vendor.RECORD_LEN) - Vendor.RECORD_LEN;
            vendor = randomFile.read(pos);
            System.out.println( vendor.getNombre() + ", " + vendor.getZona() );
        }
        long t2 = System.currentTimeMillis();

        long rt1 = t2 - t1;

        Vendor vendorArray[] = new Vendor[100];

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
