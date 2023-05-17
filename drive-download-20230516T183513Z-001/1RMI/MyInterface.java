import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MyInterface extends Remote {
    public void sendMessage(String message) throws RemoteException;
    public String receiveMessage() throws RemoteException;
}
