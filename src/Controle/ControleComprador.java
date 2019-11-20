package Controle;

import Modelo.Comprador;
import java.io.Serializable;
import java.util.ArrayList;

public class ControleComprador implements Serializable {
    // Array do comprador

    ArrayList<Comprador> arrayComprador = new ArrayList<>();
    // Cria novo comprador e colcoa no array

    void criaComprador(String cpf, String nome, String email, String fone, String contatoPref) {
        Comprador comprador = new Comprador(cpf, nome, email, fone, contatoPref);
        arrayComprador.add(comprador);
    }

}
