package it.fi.gf.meucci;

public class App 
{
    public static void main( String[] args )
    {
        MultiServer tcpServer = new MultiServer(); //Inizializza il server
        tcpServer.avvioServer(); //eseguo il metodo per avviare il server 
    }
}
