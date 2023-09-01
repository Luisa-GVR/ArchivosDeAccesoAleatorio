package unison;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CopyCSV {
    public static void main(String args[]) {

        final String dataPath = "vendors-data.dat";
        final String csvPath = "vendors.csv";
        // final String csvPath =  "D:\\data\\vendorsata.csv";
        //final String dataPath = "D:\\data\\vendors-data.dat";
        BufferedReader csvFile = null;
        RandomAccessFile datFile = null;
        try {
            csvFile = new BufferedReader(new FileReader(csvPath));

            // archivo binario
            datFile = new RandomAccessFile(dataPath, "rws");

            Vendor registroVendedor = null;
            String record = null;
            byte buffer[] = null;

            csvFile.readLine();

            long time = System.currentTimeMillis();

            while ((record = csvFile.readLine()) != null) {

                registroVendedor = parseRecord(record);

                datFile.writeInt( registroVendedor.getCodigo() );

                buffer = registroVendedor.getNombre().getBytes();
                datFile.write(buffer);

                long dob = registroVendedor.getFecha().getTime();
                datFile.writeLong(dob);

                buffer = registroVendedor.getZona().getBytes();
                datFile.write(buffer);

            }
            System.out.printf("Done in %d miliseconds\n", System.currentTimeMillis() - time );
        } catch (FileNotFoundException ex) {
            Logger.getLogger(VendorCSVFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(VendorCSVFile.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (csvFile != null) {
                try {
                    csvFile.close();
                } catch (IOException ex) {
                    Logger.getLogger(CopyCSV.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (datFile != null) {
                try {
                    datFile.close();
                } catch (IOException ex) {
                    Logger.getLogger(CopyCSV.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static Vendor parseRecord(String record) {
        StringTokenizer st1 = new StringTokenizer(record, ",");

        Vendor v = new Vendor();

        v.setCodigo(Integer.parseInt(st1.nextToken()));
        v.setNombre(st1.nextToken());

        String fecha = st1.nextToken();

        Date dob = null;
        try {
            dob = parseDOB(fecha);

        } catch (ParseException e) {
            System.out.printf(e.getMessage());
        }
        v.setFecha(dob);
        v.setZona(st1.nextToken());
        return v;
    }

    public static Date parseDOB(String d) throws ParseException {
        int len = d.length();
        Date date = null;
        if(len == 8) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
            date = dateFormat.parse(d);
        }
        if(len == 10) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            date = dateFormat.parse(d);
        }
        return date;
    }


}
