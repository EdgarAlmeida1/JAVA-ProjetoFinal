package Controle;

import Modelo.Comprador;
import Modelo.Corretor;
import Modelo.Visita;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

public class ControleVisita implements Serializable {

// Array do visita
    ArrayList<Visita> arrayvisita = new ArrayList<>();

// Cria nova visita
    void criavisita(Calendar data, Comprador comprador, Corretor corretor) {
        Visita visita = new Visita(data, comprador, corretor);
        arrayvisita.add(visita);
    }
}
