package unison;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VendorCSVFile {
    private String fileName;

    public VendorCSVFile(String fileName) {
        this.fileName = fileName;
    }

    public void write(Vendor v) {
        try {
            PrintWriter out = new PrintWriter(new FileWriter(fileName, true), true);
            out.println( v.toString() );
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(VendorCSVFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Vendor find(int codigo) {
        String lookFor = String.valueOf(codigo);
        String record = null;
        Vendor x = null;
        try {
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            while ((record = in.readLine()) != null) {
                if (record.startsWith(lookFor)) {
                    x = parseRecord(record);
                    break;
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(VendorCSVFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(VendorCSVFile.class.getName()).log(Level.SEVERE, null, ex);
        }

        return x;
    }

    private Date parseDOB(String d) throws ParseException {
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

    private Vendor parseRecord(String record) {
        StringTokenizer st1 = new StringTokenizer(record, ",");

        Vendor v = new Vendor();

        v.setCodigo( Integer.parseInt(st1.nextToken()) );
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

    public static void main(String[] args) {
        final String fileName = "vendors.csv";
        //final String fileName = "D:\\data\\vendors-data.csv";

        VendorCSVFile csvFile = new VendorCSVFile(fileName);

        Scanner input = new Scanner(System.in);

        System.out.println("Numero de empleado:");

        int codigoEmpleado = input.nextInt();
        long t1 = System.currentTimeMillis();
        Vendor p = csvFile.find( codigoEmpleado );
        long t2 = System.currentTimeMillis();
        System.out.println(p);
        System.out.println(t2-t1);
    }


}
