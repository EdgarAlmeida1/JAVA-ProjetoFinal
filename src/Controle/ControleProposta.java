package Controle;

import Modelo.*;
import java.io.*;
import java.util.*;

public class ControleProposta implements Serializable {
//array da proposta

    ArrayList<Proposta> arrayproposta = new ArrayList<>();
// Cria proposta e insere no array
    void criaProposta(Calendar data, Comprador comprador, Corretor corretor, double valor) {
        Proposta proposta = new Proposta(data, comprador, corretor, valor);
        arrayproposta.add(proposta);
    }
    
    
}
