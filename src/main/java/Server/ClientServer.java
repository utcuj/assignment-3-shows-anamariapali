package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public
class ClientServer  {

    private int portNumber;

    public ClientServer(int portNumber) {
        this.portNumber = portNumber;
        connect();
    }

    public void connect() {
        System.out.println("Server port " + portNumber + " try to connect");
        try (
                ServerSocket serverSocket = new ServerSocket(portNumber);
                Socket clientSocket = serverSocket.accept();
                ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream())
        ){

            Text outputLine = null, inputLine;

            System.out.println("Server port " + portNumber + " connected");

            ClientProtocol kkp = new ClientProtocol();

            // get the command and data from the client
            inputLine = read(in);

            while (inputLine != null) {

                outputLine = kkp.processInput(inputLine);

                System.out.println("Server: write data to client");
                out.writeObject(outputLine);

                // get the next command and data from the client
                inputLine = read(in);
                System.out.println(inputLine);

            }

            // wait until the client has done the reading from the socket
            //  System.out.println("Server: wait for client");
            //  while((inputLine = read(in)) != null) { }

            in.close();
            out.close();
            serverSocket.close();

            System.out.println("Server closed");

        } catch (IOException e1) {
            e1.printStackTrace();
            System.out.println(e1.getMessage());
        }

        System.out.println("Server port " + portNumber + " closed");
    }


    private Text read(ObjectInputStream in) {
        Text inputLine = null;
        try {
            inputLine = (Text) in.readObject();
           // System.out.println(inputLine.methodName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return inputLine;
    }



    public static void main(String[] args) {

        ClientServer cs = new ClientServer(1342);
    }

	
	

	
	
		
	

}
