package Controle;

import Modelo.*;
import java.io.*;
import java.nio.file.Files;
import java.util.*;


public class ControleImovel {
// Array do IMOVEL

    private ArrayList<Imovel> arrayImovel = new ArrayList<>();

    public ControleImovel() throws Exception{
        desserializaImovel();
    }
    
    public void copiaArquivo(File a, File b) throws IOException{
        Files.copy(a.toPath(), b.toPath());
    }

// Cria um novo imovel e insere no aray
    public void criaImovel(int codigo, String tipo, String descricao, String arquivoFoto,
            double preco, double comissao, Calendar dataInclusao, Vendedor vendedor) {
        Imovel imovel = new Imovel(codigo, tipo, descricao, arquivoFoto, preco, comissao, dataInclusao, vendedor);
        arrayImovel.add(imovel);
    }

    public ArrayList<Imovel> getListaImovel() {
        return arrayImovel;
    }
    
    // Faz a leitura do arquivo que contem os corretores
    private void desserializaImovel() throws Exception {
        File objFile = new File("imoveis.dat");
        if (objFile.exists()) {
            FileInputStream objFileIS = new FileInputStream("imoveis.dat");
            ObjectInputStream objIS = new ObjectInputStream(objFileIS);
            arrayImovel = (ArrayList<Imovel>) objIS.readObject();
            objIS.close();
        }
    }

    // Salva os corretores no arquivo 
    private void serializaImovel() throws Exception {
        FileOutputStream objFileOS = new FileOutputStream("imoveis.dat");
        ObjectOutputStream objOS = new ObjectOutputStream(objFileOS);
        objOS.writeObject(arrayImovel);
        objOS.flush();
        objOS.close();
    }

    public void finalize() throws Exception {
        serializaImovel();
    }
}