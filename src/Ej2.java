import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Ej2 {
    public static void main(String[] args) {

        /*
        Como me encontraba en windows, el ejercicio realizado ha sido contar la cantidad de lineas que tiene la palabra hola
         */
        //Almaceno el comando a utilizar
        final String[] COMANDO = {"find","\"hola\"","/C"};

        //Creo el scanner con el cual recogere el nombre del archivo
        try(Scanner teclado = new Scanner(System.in);){
            System.out.println("Introduce el nombre del archivo a leer: ");
            File archivo = new File(teclado.nextLine());
            //Me aseguro de que el archivo exista
            if(!archivo.exists()){
                System.out.println("El archivo no existe!");
                System.exit(1);
            }
            //Preparo el process builder con su comando y sus redirecciones.
            ProcessBuilder findBuilder = new ProcessBuilder().command(COMANDO)
                    .redirectError(ProcessBuilder.Redirect.INHERIT).redirectOutput(ProcessBuilder.Redirect.INHERIT)
                    .redirectInput(archivo);
            //Ejecuto el comando
            try{
                Process find = findBuilder.start();
            } catch (IOException e) {
                System.out.println("No se ha podido crear el proceso");
                e.printStackTrace();
            }


        }

    }
}