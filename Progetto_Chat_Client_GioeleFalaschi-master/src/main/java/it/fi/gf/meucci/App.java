package it.fi.gf.meucci;


public class App 
{
    public static void main( String[] args )
    {
        ClientStr client = new ClientStr();
        client.connetti(); 
        client.comunica();
        //Esegue i metodi creati nella classe del client per effettuare la comunicazione col server
    }
}
