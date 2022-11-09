import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Ej3 {

    public static void main(String[] args) {
        //Preparo el comando
        final String[] COMANDO = {"find",""};
        //Miro que se me haya enviado un argumento
        if(args.length != 1){
            System.out.println("Necesito un argumento que buscar");
            System.exit(1);
        }
        //Imprimo el argumento y lo formateo como lo necesito
        System.out.printf("El argumento es: %s%n",args[0]);
        //find requiere que lo que buscar este entre comillas
        String argumento = args[0];
        if(!argumento.startsWith("\""))
            argumento = "\"" + argumento;
        if(!argumento.endsWith("\""))
            argumento = argumento + "\"";
        COMANDO[1] = argumento;

        //Preparo el process builder
        ProcessBuilder processBuilder = new ProcessBuilder().command(COMANDO);
        String input;
        //Creo el scanner que leera de mi teclado
        Scanner teclado = new Scanner(System.in);
        do{
            System.out.println("Introduce linea: ");
            input = teclado.nextLine();
            //Si no es una linea vacia:
            if(!input.equals("")){
                //Creo el proceso
                try {
                    Process p = processBuilder.start();
                    //Escribo la linea al proceso
                    try(PrintWriter printWriter = new PrintWriter(p.getOutputStream(),true)){
                        printWriter.println(input); //No necesito un flush ya que puse autoFlush al crear el printWriter
                    }

                    //Espero a que el proceso termine su ejecución
                    try {
                        p.waitFor();
                    } catch (InterruptedException e) {
                        System.out.println("Error al esperar al proceso");
                    }

                    //Leo del proceso
                    try(Scanner inFind = new Scanner(p.getInputStream())){
                        //Si el proceso ha escrito algo, ha reescrito la linea porque tiene la expresión buscada
                        if(inFind.hasNextLine()){
                            System.out.printf("%s contiene %s%n",inFind.nextLine(),args[0]);
                        }else{ //si no, es que no la contenia
                            System.out.printf("%s no contiene %s%n",input,args[0]);
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Error al crear el proceso");
                }

            }
        //Hasta que sea linea vacia
        }while(!input.equals(""));

    }

}
