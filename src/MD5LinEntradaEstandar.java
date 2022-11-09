import java.io.*;
import java.util.Scanner;

public class MD5LinEntradaEstandar {

  public static void main(String[] args) {

    try (Scanner teclado = new Scanner(System.in)) {

      ProcessBuilder pb = new ProcessBuilder("find","\"hola\"","/C");
      //ProcessBuilder pb = new ProcessBuilder("md5sum");

      pb.redirectError(ProcessBuilder.Redirect.INHERIT);

      System.out.print("md5txt> ");
      String linea = teclado.nextLine();

      while (linea.length() > 0) {
        Process p = pb.start();

        try (PrintWriter printWriter = new PrintWriter(p.getOutputStream(),true)){
          printWriter.println(linea);
        }
        p.waitFor();

        try (Scanner in = new Scanner(p.getInputStream())){
          if(in.hasNextLine()) {
            String lineaSalida = in.nextLine();
            System.out.println((lineaSalida.split(" +"))[0]);
          }else{
            System.out.println("No se ha recibido nada del proceso.");
          }
        }

        System.out.print("md5txt> ");
        linea = teclado.nextLine();
      }

    } catch (IOException ex) {
      System.err.println("Error de E/S");
      ex.printStackTrace();
    } catch (InterruptedException ex) {
      System.err.println("Proceso interrumpido");
    }

  }

}
