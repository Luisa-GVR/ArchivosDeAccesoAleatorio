package unison;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RandomFileDemo {
    public static void main(String[] args) {
        try {
            RandomAccessFile file = new RandomAccessFile("random.dat","rws");

            byte data[] = { 10,20,30,40,50 };
            String s = "HOLA";

            file.write(data);
            file.writeUTF(s);

            System.out.println("Tamanio del archivo: " +
                    file.length());


            // ir al inicio del archivo (byte 0)
            file.seek( 0 );

            // leer primer byte
            byte first = file.readByte();
            System.out.println(first);

            // ir byte 3, para leer cuarto byte
            file.seek( 3 );
            byte cuarto = file.readByte();

            System.out.println(cuarto);

            file.seek( 5 );
            System.out.println( file.readUTF() );
            file.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(RandomFileDemo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RandomFileDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
