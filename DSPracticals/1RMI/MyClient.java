import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MyClient implements Runnable {
    private String name;
    private MyInterface server;

    public MyClient(String name) {
        this.name = name;
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 5000);
            server = (MyInterface) registry.lookup("myInterface");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        for (int i = 1; i <= 5; i++) {
            try {
                server.sendMessage("Message from " + name + ": " + i);
                String response = server.receiveMessage();
                System.out.println(response);
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new MyClient("Client 1")).start();
        new Thread(new MyClient("Client 2")).start();
    }
}
