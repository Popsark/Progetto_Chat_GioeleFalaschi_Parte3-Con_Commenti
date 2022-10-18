package it.fi.gf.meucci;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiServer {
    
    public void avvioServer(){  //Costruisco il metodo per avviare il server in modalità multi thread
        try{
            ServerSocket serverSocket = new ServerSocket(7777);  //apro la porta del server 
            for(;;){
                System.out.println("Il server è in attesa di un client...");
                Socket socket = serverSocket.accept();  //Blocco l'esecuzione fino a che non arriva un client
                System.out.println("Informazioni sul socket:  "+socket); 
                ServerThread serverThread = new ServerThread(socket); //affido la gestione del client arrivato ad un thread, così facendo, potrò gestire più client eventuali contemporaneamente
                serverThread.start(); //Avvio il thread appena creato
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza del server");
            System.exit(1);
        }
    }
}
