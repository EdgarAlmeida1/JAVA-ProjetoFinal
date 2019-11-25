package Controle;

import Modelo.Imovel;
import Modelo.Vendedor;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

public class ControleImovel implements Serializable {
// Array do IMOVEL

    private ArrayList<Imovel> arrayImovel = new ArrayList<>();

// Cria um novo imovel e insere no aray
    void criaImovel(int codigo, String tipo, String descricao, String arquivoFoto, String estado,
            double preco, double comissao, Calendar dataInclusao, Vendedor vendedor) {
        Imovel imovel = new Imovel(codigo, tipo, descricao, arquivoFoto, estado, preco, comissao, dataInclusao, vendedor);
        arrayImovel.add(imovel);
    }

    public ArrayList<Imovel> getListaImovel() {
        return arrayImovel;
    }
}