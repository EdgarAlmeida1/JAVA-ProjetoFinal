package Controle;

import Modelo.Proposta;
import Modelo.Comprador;
import Modelo.Corretor;
import Limite.LimiteProposta;
import java.io.*;
import java.util.*;

public class ControleProposta{
    private ArrayList<Proposta> arrayProposta = new ArrayList<>();
    private ControlePrincipal objCtr;
    
    public ControleProposta(ControlePrincipal objCtrPrincipal) throws Exception{
        desserializaProposta();
        objCtr = objCtrPrincipal;
        new LimiteProposta(objCtr);
    }

// Cria proposta e insere no array
    public void criaProposta(Calendar data, Comprador comprador, Corretor corretor, double valor) {
        Proposta proposta = new Proposta(data, comprador, corretor, valor);
        arrayProposta.add(proposta);
    }
    
    private void desserializaProposta() throws Exception {
        File objFile = new File("propostas.dat");
        if (objFile.exists()) {
            FileInputStream objFileIS = new FileInputStream("propostas.dat");
            ObjectInputStream objIS = new ObjectInputStream(objFileIS);
            arrayProposta = (ArrayList<Proposta>) objIS.readObject();
            objIS.close();
        }
    }
    
    private void serializaProposta() throws Exception {
        FileOutputStream objFileOS = new FileOutputStream("propostas.dat");
        ObjectOutputStream objOS = new ObjectOutputStream(objFileOS);
        objOS.writeObject(arrayProposta);
        objOS.flush();
        objOS.close();
    }
    
    public void finalize() throws Exception{
        serializaProposta();
    }
}
