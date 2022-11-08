package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

public class RMIServer {
    public static void main(String[] args) {
        String hostName = "localhost";
        int port = 8080;
        ArrayList<Integer> numbers = new ArrayList<>();
        String RMI_HOSTNAME = "java.rmi.server.hostname";
        try {
            System.setProperty(RMI_HOSTNAME, hostName);

            // Create service for RMI
            Service service = new ServiceImpl();
            // Init service
//            service.addElem("Hello, world");

            BufferedReader file = new BufferedReader(new FileReader("src/main/resources/1-100.txt"));
            String line = file.readLine();
            while(line != null){
                String[] tokens = line.split(" ");
                numbers.add(Integer.parseInt(tokens[0]));
                line = file.readLine();
            }
            for(int num: numbers){
                service.addElem(num);
            }
            String serviceName = "Service";

            System.out.println("Initializing " + serviceName);

            Registry registry = LocateRegistry.createRegistry(port);
            // Make service available for RMI
            registry.rebind(serviceName, service);

            System.out.println("Start " + serviceName);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
