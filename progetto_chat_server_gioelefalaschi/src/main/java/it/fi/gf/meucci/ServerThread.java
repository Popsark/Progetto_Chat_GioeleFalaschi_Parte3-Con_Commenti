package it.fi.gf.meucci;
import java.net.Socket;
import javax.swing.event.SwingPropertyChangeSupport;

import java.net.*;
import java.io.*;

public class ServerThread extends Thread{
    ServerSocket server = null;
    Socket client = null;
    String stringaRicevuta= null;
    String stringaModificata = null;
    BufferedReader inDalClient;
    DataOutputStream OutVersoClient;

    public ServerThread (Socket socket){ //Costruttore che mi servirà nella classe "MultiServer" per poter inizializzare il thread
        this.client = socket;
    }

    public void comunica ()throws Exception{
        inDalClient = new BufferedReader(new InputStreamReader(client.getInputStream())); //Definisco il canale di input da cui riceveremo i messaggi del client
        OutVersoClient = new DataOutputStream(client.getOutputStream()); //Definisco il canale di output da cui manderemo i messaggi del server
        for(;;){
            stringaRicevuta = inDalClient.readLine(); //Leggo la stringa ricevuta dal client e la metto su una stringa creata prima

            if(stringaRicevuta == null || stringaRicevuta.equals("FINE")){ /*Questo controllo fa parte del passaggio 1.3 ed è in concomitanza 
                                                                            con il controllo definito nella classe ClientStr; in questo caso 
                                                                            vengono semplicemente stampate delle stringhe
                                                                            per far capire a testo cosa sta succedendo*/
                OutVersoClient.writeBytes(stringaRicevuta+ "(=>Server in chiusura...)" + '\n');
                System.out.println("Messaggio sul server in chiusura :"+ stringaRicevuta);
                break;
            }
            else{
                OutVersoClient.writeBytes(stringaRicevuta+ "(ricevuta e ritrasmessa)"+ '\n'); //se la stringa non è "FINE", semplicemente procedi normalmente 
                System.out.println("Messaggio sul server :"+ stringaRicevuta);
            }
        }
        OutVersoClient.close(); //Chiusura canale output
        inDalClient.close(); //Chiusura canale input
        System.out.println("Chiusura socket: "+ client);
        client.close(); //il socket viene terminato
    }
    
    public void run (){  //metodo per esecuzione del thread
        try{
            comunica();
        }
        catch(Exception e){
            e.printStackTrace(System.out);
        }
    }
}
