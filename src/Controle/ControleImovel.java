package Controle;

import Modelo.*;
import Utilitario.Util;
import java.io.*;
import java.nio.file.Files;
import java.util.*;
import javax.swing.JOptionPane;

public class ControleImovel {
// Array do IMOVEL

    private ArrayList<Imovel> arrayImovel = new ArrayList<>();

    public ControleImovel() throws Exception {
        desserializaImovel();
    }

    public void copiaArquivo(File a, File b) throws IOException {
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

    // Gera o relarotio de vendas no periodo 
    public String relatorioVenda(Calendar inicio, Calendar fim) {
        String vendas = ("");
        boolean status = false;
        for (int i = 0; i < arrayImovel.size(); i++) {
            if (arrayImovel.get(i).getEstado() == Util.VENDIDO) {
                status = true;
                vendas += ("\nCodigo:" + arrayImovel.get(i).getCodigo() + "\n"
                        + "Tipo:" + arrayImovel.get(i).getTipo() + "\n"
                        + "Preço pedido pelo imovel:" + arrayImovel.get(i).getPreco() + "\n"
                        + "Descrição:" + arrayImovel.get(i).getDescricao() + "\n"
                        + "Vendedor:" + arrayImovel.get(i).getVendedor() + "\n"
                        + "Valor de comissão:" + 0.6 * arrayImovel.get(i).getComissao() + "\n"
                        + "Preço final do imovel:" + arrayImovel.get(i).getPreco());

            }
        }
        if (arrayImovel.size() == 0) {
            return ("Não existe imoveis cadastrados no sistema");
        } else {
            if (status == true) {
                return vendas;
            } else {
                return ("Não teve vendas de imoveis nesse periodo!");
            }

        }
    }

    //Listagem de imoveis por vendedor
    public String RelatorioImoveisVendedor(String CPF) {
        String result = ("Imoveis associado ao CPF:  "+CPF);
        if (arrayImovel.size() == 0) {
            JOptionPane.showMessageDialog(null, "Não existe vendedor cadastrado no sistema, cadastre um vendedor!");
            return ("");
        } else {
            for (int i = 0; i < arrayImovel.size(); i++) {
                if (arrayImovel.get(i).getVendedor().getCpf().equals(CPF)) {
                    result += ("\n\nCodigo do imovel: " + getListaImovel().get(i).getCodigo() + ":\nTipo do imovel:" + getListaImovel().get(i).getTipo() + "\nEstado do imovel: " + getListaImovel().get(i).getEstado() + "\nDescrição do imovel: " + getListaImovel().get(i).getDescricao());
                } else {
                    JOptionPane.showMessageDialog(null, "Vendedor não encontrado. Por favor, digite um vendedor com CPF valido!");
                    return ("");
                }
            }
        }

        return result;
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
