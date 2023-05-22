import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;
import java.util.Scanner;

public class Server extends UnicastRemoteObject implements ServerInterface {
    private static final long serialVersionUID = 1L;
    private static int numClients = 0;

    public Server() throws RemoteException {
        super();
    }

    public String sayHello() throws RemoteException {
        return "Hello!";
    }

    public synchronized int getNumClients() throws RemoteException {
        return numClients;
    }

    public synchronized void addClient() throws RemoteException {
        numClients++;
    }

    public synchronized void removeClient() throws RemoteException {
        numClients--;
    }

    public String processMessage(String message) throws RemoteException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Received message: " + message);
        System.out.println("Enter a message to send to the client: ");
        String reply = scanner.nextLine();
        return reply;
    }

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            Server server = new Server();
            registry.rebind("Server", server);
            System.out.println("Server started!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/*Microsoft Windows [Version 10.0.19045.2965]
(c) Microsoft Corporation. All rights reserved.

C:\Users\saksh\IdeaProjects\DS\DS_1>cd src

C:\Users\saksh\IdeaProjects\DS\DS_1\src>javac ServerInterface.java

C:\Users\saksh\IdeaProjects\DS\DS_1\src>javac Server.java

C:\Users\saksh\IdeaProjects\DS\DS_1\src>javac Client.java

C:\Users\saksh\IdeaProjects\DS\DS_1\src>java Server
Server started!
Received message: Hii
Enter a message to send to the client:
hello

*/