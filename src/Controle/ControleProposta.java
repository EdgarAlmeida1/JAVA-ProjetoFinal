package Controle;

import Modelo.Comprador;
import Modelo.Corretor;
import Modelo.Proposta;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

public class ControleProposta implements Serializable {
//array da proposta

    ArrayList<Proposta> arrayproposta = new ArrayList<>();
// Cria proposta e insere no array
    void criaProposta(Calendar data, Comprador comprador, Corretor corretor, double valor) {
        Proposta proposta = new Proposta(data, comprador, corretor, valor);
        arrayproposta.add(proposta);
    }
    
    
}
