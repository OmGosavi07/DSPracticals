import java.rmi.*;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            ServerInterface server = (ServerInterface) Naming.lookup("rmi://localhost/Server");

            int clientId = server.getNumClients();
            server.addClient();
            System.out.println("Client " + clientId + " connected to server.");

            while (true) {
                System.out.println("Enter a message to send to the server: ");
                String message = scanner.nextLine();
                String response = server.processMessage(message);
                System.out.println("Server responded with: " + response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/*Microsoft Windows [Version 10.0.19045.2965]
(c) Microsoft Corporation. All rights reserved.

C:\Users\saksh\IdeaProjects\DS\DS_1>cd src

C:\Users\saksh\IdeaProjects\DS\DS_1\src>java Client
Client 0 connected to server.
Enter a message to send to the server:
Hii
Server responded with: hello
*/