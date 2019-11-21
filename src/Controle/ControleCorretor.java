package Controle;

import Modelo.Corretor;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ControleCorretor{
    //ArrayList do Corretor
    ArrayList<Corretor> arrayCorretor = new ArrayList<>();

    public ControleCorretor() throws Exception{
        desserializaCorretor();
        //testa();
    }
    
    public void testa(){
        System.out.println("**** CORRETOR ****");
        for(Corretor c: arrayCorretor){
            System.out.println(c.getNome());
        }
        System.out.println("*******************");
    }
    
    public void criaCorretor(String cpf, String nome, String email, String fone, String creci, double percCorretagem) {
        Corretor corretor = new Corretor(cpf, nome, email, fone, creci, percCorretagem);
        arrayCorretor.add(corretor);
    }
    
    private void desserializaCorretor() throws Exception {
        File objFile = new File("corretores.dat");
        if (objFile.exists()) {
            FileInputStream objFileIS = new FileInputStream("corretores.dat");
            ObjectInputStream objIS = new ObjectInputStream(objFileIS);
            arrayCorretor = (ArrayList<Corretor>) objIS.readObject();
            objIS.close();
        }
    }
    
    private void serializaCorretor() throws Exception {
        FileOutputStream objFileOS = new FileOutputStream("corretores.dat");
        ObjectOutputStream objOS = new ObjectOutputStream(objFileOS);
        objOS.writeObject(arrayCorretor);
        objOS.flush();
        objOS.close();
    }
    
    public void finalize() throws Exception{
        serializaCorretor();
    }
}
