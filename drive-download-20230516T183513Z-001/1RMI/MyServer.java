import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MyServer extends UnicastRemoteObject implements MyInterface {
    private String message = null;
    public MyServer() throws RemoteException {}

    public void sendMessage(String message) throws RemoteException {
        this.message = message;
    }

    public String receiveMessage() throws RemoteException {
        return message;
    }

    public static void main(String args[]) throws Exception {
        java.rmi.registry.LocateRegistry.createRegistry(5000);
        System.out.println("Server started...");
        while (true) {
            MyInterface stub = new MyServer();
            java.rmi.Naming.rebind("rmi://localhost:5000/myInterface", stub);
            Thread.sleep(1000);
        }
    }
}
