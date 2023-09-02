package unison;

import java.io.*;
import java.util.Scanner;

public class EliminarVendedor {
    public static void main(String[] args) {
        final String dataPath = "vendors-data.dat";
        String archivoTemp = "temp.dat";
        Scanner sc = new Scanner(System.in);
        System.out.println("Inserta el nombre");
        String nombreEliminar = sc.nextLine();

        try (RandomAccessFile entrada = new RandomAccessFile(dataPath, "rw");
             FileOutputStream salida = new FileOutputStream(archivoTemp)) {

            int longitudEntrada = 66;
            long longitudArchivo = entrada.length();
            byte[] buffer = new byte[longitudEntrada];
            long posicionLectura = 0;
            long posicionEscritura = 0;

            while (posicionLectura < longitudArchivo) {
                entrada.seek(posicionLectura);
                entrada.read(buffer);

                String nombre = new String(buffer, 0, 33, "UTF-8").trim();

                if (!nombre.equals(nombreEliminar)) {
                    salida.write(buffer);
                    posicionEscritura += longitudEntrada;
                }

                posicionLectura += longitudEntrada;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        File archivoOriginalObj = new File(dataPath);
        File archivoTemporalObj = new File(archivoTemp);

        if (archivoOriginalObj.delete() && archivoTemporalObj.renameTo(archivoOriginalObj)) {
            System.out.println("La entrada se eliminó con éxito.");
        } else {
            System.out.println("Error al eliminar la entrada.");
        }
    }
}