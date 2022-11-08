package org.example;
import java.rmi.Naming;

public class RMIClient {
        public static void main(String[] args) {
            String hostName = "localhost";
            int port = 8080;
            String RMI_HOSTNAME = "java.rmi.server.hostname";
            String SERVICE_PATH = "//" + hostName + ":" + port + "/Service";

            try {
                System.setProperty(RMI_HOSTNAME, hostName);
                Service service = (Service) Naming.lookup(SERVICE_PATH);

                while(true){
                    Integer num = service.pollElem();
                    if (num == null) {
                        System.out.println("Received none!");
                        break;
                    } else {
                        System.out.println("Received: " + num);
                        service.Calculate(findEvenPositive(num));
                    }
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        public static int findEvenPositive(int num){
            int count = 0;
            for(int i=1;i<num;i++){
                if(isEvenPositive(i)){
                    count++;
                }
            }
            return count;
        }

        private static boolean isEvenPositive(int num){
            boolean flag=false;
            for(int i=0;i<=num;i++){
                if(num%2==0){
                    flag=true;
                    break;
                }
            }
            if(!flag){
                return true;
            }else {
                return false;
            }
        }
}