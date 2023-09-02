package unison;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class EditarInformacion {
    public static void main(String[] args) throws ParseException, FileNotFoundException {

        final String csvPath = "vendors.csv";

        Scanner sc = new Scanner(System.in);
        System.out.println("Escribe el numero de empleado");
        int empleadoPos = sc.nextInt();

        System.out.println("Que deseas cambiar? Escribe el numero adecuado \n" +
                " 1: Nombre \n 2: Fecha \n 3: Zona \n 4: Venta mensual");
        int eleccion = sc.nextInt();

        System.out.println("Estos son los valores anteriores:");
        VendorCSVFile csvFile = new VendorCSVFile(csvPath);
        Vendor values = csvFile.find(empleadoPos);
        System.out.println(values);

        System.out.println("Escribe el valor nuevo");

        sc.nextLine(); // quita problema del next int
        String lineAppend = String.valueOf(empleadoPos) + ",";
        String outputDate = null;
        SimpleDateFormat inputDateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.US);
        SimpleDateFormat outputDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        switch (eleccion){
            case 1: //nombre

                String nombre = sc.nextLine();


                try {
                    Date date = inputDateFormat.parse(String.valueOf(values.getFecha()));
                    outputDate = outputDateFormat.format(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                lineAppend += nombre + ","+ outputDate + ","+ values.getZona() + "," +  values.getMensual();


                break;
            case 2: //fecha
                System.out.println("Porfavor escriba el formato adecuado de dd/MM/aaaa");
                String fecha = sc.nextLine();

                lineAppend += values.getNombre() + ","+ fecha + ","+ values.getZona() + "," + values.getMensual() ;


                break;
            case 3: //zona
                String zona = sc.nextLine();

                try {
                    Date date = inputDateFormat.parse(String.valueOf(values.getFecha()));
                    outputDate = outputDateFormat.format(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                lineAppend += values.getNombre() + ","+ outputDate + ","+ zona + ","+ values.getMensual() ;


                break;
            case 4: //venta mensual
                int valorMensual = sc.nextInt();
                try {
                    Date date = inputDateFormat.parse(String.valueOf(values.getFecha()));
                    outputDate = outputDateFormat.format(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                lineAppend += values.getNombre() + ","+ outputDate + ","+ values.getZona() + ","+  valorMensual ;


                break;

        }
        lineAppend += "\n";

        // cambiar la informacion

        StringBuilder csvContent = new StringBuilder();


        try(BufferedReader br = new BufferedReader(new FileReader(csvPath))){
            String line;
            int lineStart = 1;
            boolean antierror = false;

            while ((line = br.readLine()) != null){

                if (lineStart == (empleadoPos+1)){

                    csvContent.append(lineAppend);
                    lineStart++;
                } else {

                    csvContent.append(line).append(System.lineSeparator());

                    lineStart++;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvPath))) {
            bw.write(csvContent.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Agregar al dat
        CopyCSV.main(new String[]{});

    }
}
