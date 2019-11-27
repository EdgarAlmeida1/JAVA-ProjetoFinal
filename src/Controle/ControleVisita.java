package Controle;

import Modelo.*;
import java.io.Serializable;
import java.util.*;

public class ControleVisita implements Serializable {

// Array do visita
    ArrayList<Visita> arrayvisita = new ArrayList<>();

// Cria nova visita
    void criavisita(Calendar data, Comprador comprador, Corretor corretor) {
        Visita visita = new Visita(data, comprador, corretor);
        arrayvisita.add(visita);
    }
}
