package Controle;

import Modelo.Comprador;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class ControleComprador{
    // Array do comprador
    private ArrayList<Comprador> arrayComprador = new ArrayList<Comprador>();

    public ControleComprador() throws Exception{
        desserializaComprador();
    }
    
    public void teste(){
        for(Comprador c :arrayComprador){
            System.out.println(c.getNome());
        }
    }
    // Cria novo comprador e colcoa no array
    public void criaComprador(String cpf, String nome, String email, String fone, String contatoPref) {
        Comprador comprador = new Comprador(cpf, nome, email, fone, contatoPref);
        arrayComprador.add(comprador); 
    }
    
    private void desserializaComprador() throws Exception {
        File objFile = new File("compradores.dat");
        if (objFile.exists()) {
            FileInputStream objFileIS = new FileInputStream("compradores.dat");
            ObjectInputStream objIS = new ObjectInputStream(objFileIS);
            arrayComprador = (ArrayList<Comprador>) objIS.readObject();
            objIS.close();
        }
    }
    
    private void serializaComprador() throws Exception {
        FileOutputStream objFileOS = new FileOutputStream("compradores.dat");
        ObjectOutputStream objOS = new ObjectOutputStream(objFileOS);
        objOS.writeObject(arrayComprador);
        objOS.flush();
        objOS.close();
    }
    
    public void finalize() throws Exception{
        serializaComprador();
    }
}
