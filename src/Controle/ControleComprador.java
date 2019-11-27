package Controle;

import Modelo.Comprador;
import java.io.*;
import java.util.*;

public class ControleComprador{
    // Array do comprador
    private ArrayList<Comprador> arrayComprador = new ArrayList<>();

    public ControleComprador() throws Exception{
        desserializaComprador();
        //testa();
    }
    
    public void testa(){ 
        System.out.println("**** COMPRADOR ****");
        for(Comprador c: arrayComprador){
            System.out.println(c.getNome());
        }
        System.out.println("*******************");
    }
    
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
