import java.io.*;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import com.sun.management.UnixOperatingSystemMXBean;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.Set;

class Procesos {
   public static void main(String[] arg) throws IOException, InterruptedException {

      //LeerArchivo();
      //NumArchivosDescriptivos();
      //VariablesEntorno();
      CrearProceso();

   }

   public static void LeerArchivo() {
      File archivo = null;
      FileReader fr = null;
      BufferedReader br = null;

      try {

         archivo = new File("/home/muterk/Documentos/Tarea 3/cominicacion-entre-procesos-1-jarturogl05/Texto");
         fr = new FileReader(archivo);
         br = new BufferedReader(fr);

         String linea;
         while ((linea = br.readLine()) != null)
            System.out.println(linea);
      } catch (IOException e) {
         e.printStackTrace();
      } finally {
         try {
            if (null != fr) {
               fr.close();
            }
         } catch (IOException e2) {
            e2.printStackTrace();
         }
      }
   }

   


   public static void NumArchivosDescriptivos() {
      OperatingSystemMXBean os = ManagementFactory.getOperatingSystemMXBean();
      if (os instanceof UnixOperatingSystemMXBean) {
         System.out.println("El número máximo de archivos descriptores es: "
               + ((UnixOperatingSystemMXBean) os).getMaxFileDescriptorCount());
      }
   }

   public static void VariablesEntorno() {
      Map<String, String> env = System.getenv();
      Set<String> keys = env.keySet();
      for (String key : keys) {
         System.out.println(key + " = " + env.get(key));
      }
   }

   public static void CrearProceso() throws IOException, InterruptedException {
      ProcessBuilder processBuilder = new ProcessBuilder("/opt/google/chrome/chrome");
      Process process1 = processBuilder.start();
      long pid1 = process1.pid();
      System.out.println(pid1);

      Process process2 = Runtime.getRuntime().exec("/opt/google/chrome/chrome");
      long pid2 = process2.pid();
      System.out.println(pid2);

      
      process2.destroy();
      process2.waitFor();

   }

}