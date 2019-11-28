package Controle;

import Modelo.*;
import Limite.LimiteVisita;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

public class ControleVisita{
    private ArrayList<Visita> arrayvisita = new ArrayList<>();
    private ControlePrincipal objCtr;
    
    public ControleVisita(ControlePrincipal objCtrPrincipal) throws Exception{
        desserializaVisita();
        objCtr = objCtrPrincipal;
        new LimiteVisita(objCtr);
    }

// Cria nova visita
    void criavisita(Calendar data, Comprador comprador, Corretor corretor) {
        Visita visita = new Visita(data, comprador, corretor);
        arrayvisita.add(visita);
    }
    
    private void desserializaVisita() throws Exception {
        File objFile = new File("visitas.dat");
        if (objFile.exists()) {
            FileInputStream objFileIS = new FileInputStream("visitas.dat");
            ObjectInputStream objIS = new ObjectInputStream(objFileIS);
            arrayvisita = (ArrayList<Visita>) objIS.readObject();
            objIS.close();
        }
    }
    
    private void serializaVisita() throws Exception {
        FileOutputStream objFileOS = new FileOutputStream("visitas.dat");
        ObjectOutputStream objOS = new ObjectOutputStream(objFileOS);
        objOS.writeObject(arrayvisita);
        objOS.flush();
        objOS.close();
    }
    
    public void finalize() throws Exception{
        serializaVisita();
    }
}
