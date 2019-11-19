package Controle;

import Modelo.Corretor;
import java.io.Serializable;
import java.util.ArrayList;

public class ControleCorretor implements Serializable {
//ArrayList do Corretor

    ArrayList<Corretor> arrayCorretor = new ArrayList<>();
// Cria um novo corretor e coloca no Array
    void criaCorretor(String cpf, String nome, String email, String fone, String creci, double percCorretagem) {
        Corretor corretor = new Corretor(cpf, nome, email, fone, creci, percCorretagem);
        arrayCorretor.add(corretor);
    }
}
