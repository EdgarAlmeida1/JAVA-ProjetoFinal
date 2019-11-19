package Controle;

import Modelo.Vendedor;
import java.io.Serializable;
import java.util.ArrayList;

public class ControleVendedor implements Serializable {
//ArrayList do vendedor

    ArrayList<Vendedor> arrayVendedor = new ArrayList<>();

// Cria novo vendedor na ItaHouse
    void criavendedor(String cpf, String nome, String email, String fone, String contatoPref) {
        Vendedor vendedor = new Vendedor(cpf, nome, email, fone, contatoPref);
        arrayVendedor.add(vendedor);
    }

}
