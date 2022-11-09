import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MD5LinEntradaEstandar {

  public static void main(String[] args) {

    try (InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr)) {

      ProcessBuilder pb = new ProcessBuilder("md5sum");
      pb.redirectError(ProcessBuilder.Redirect.INHERIT);

      System.out.print("md5txt> ");
      String linea = br.readLine();

      while (linea != null && linea.length() > 0) {
        Process p = pb.start();

        try (OutputStream osp = p.getOutputStream();
                OutputStreamWriter oswp = new OutputStreamWriter(osp, "UTF-8");
                BufferedWriter bwp = new BufferedWriter(oswp)) {
          bwp.write(linea);
        }
        p.waitFor();

        try (InputStream isp = p.getInputStream();
                InputStreamReader isrp = new InputStreamReader(isp);
                BufferedReader brp = new BufferedReader(isrp)) {
          String lineaSalida = brp.readLine();
          System.out.println((lineaSalida.split(" +"))[0]);
        }

        System.out.print("md5txt> ");
        linea = br.readLine();
      }

    } catch (IOException ex) {
      System.err.println("Error de E/S");
      ex.printStackTrace();
    } catch (InterruptedException ex) {
      System.err.println("Proceso interrumpido");
    }

  }

}
