package it.fi.gf.meucci;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientStr {
    String nomeServer ="localhost"; //il client è sullo stesso dispositivo in cui viene inizializzato il server
    int portaServer = 7777;
    Socket miosocket; //Inizializzo il socket del client
    BufferedReader tastiera;
    String stringaUtente;
    String stringaRicevutaDalServer;
    DataOutputStream outVersoServer;
    BufferedReader inDalServer;

    public Socket connetti(){
        try{
            miosocket = new Socket(nomeServer, portaServer); //Definisco il socket del client
            tastiera = new BufferedReader(new InputStreamReader(System.in));  //Definisco il metodo di input, in questo caso la tastiera
            outVersoServer = new DataOutputStream((miosocket.getOutputStream())); //Definisco il canale di output da cui manderemo i messaggi
            inDalServer = new BufferedReader(new InputStreamReader(miosocket.getInputStream())); //Definisco il canale di input da cui riceveremo i messaggi inviati dal server
            System.out.println("CLIENT partito in esecuzione");
        }
        catch(UnknownHostException e ){
            System.err.println("Host sconosciuto"); 
        }
            catch(Exception e){
                System.out.println(e.getMessage());
                System.out.println("Errore durante la connessione");
                System.exit(1);
            }
            return miosocket;
        }

    public void comunica(){
        for(;;)
        try{
            System.out.println("inserisci la stringa da trasmettere al server " + '\n');
            stringaUtente = tastiera.readLine();  //Viene riempita la stringa da mandare al server
            System.out.println("invio la stringa al server e attendo");
            outVersoServer.writeBytes(stringaUtente+ '\n');  //Quando la stringa viene inviata attraverso il canale di output,la stringa viene trasformata in byte
            stringaRicevutaDalServer = inDalServer.readLine(); //Legge la stringa che il server invia al client come risposta attraverso il canale di input
            System.out.println("risposta dal server "+'\n'+ stringaRicevutaDalServer);
            if(stringaUtente.equals("FINE")){  //Definisco il metodo del passo 1.3 per fare chiudere UN SOLO SOCKET scrivendo la stringa "FINE"
                System.out.println("CLIENT: termina elaborazione e chiude connessione");
                miosocket.close();  //chiude il socket, terminando la comunicazione
                break; //interrompe il ciclo
            }
            
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Errore durante la comunicazione col server");
            System.exit(1); //Esce il programma corrente terminando la Java Virtual Machine 
        }
    }
    }

    

