package Controle;

import Modelo.Corretor;
import Modelo.Imovel;
import Modelo.Proposta;
import Modelo.Visita;
import Utilitario.Util;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;

public class ControleCorretor {

    //ArrayList do Corretor
    ArrayList<Corretor> arrayCorretor = new ArrayList<>();

    //Ler o arquivo que foi serealizado
    public ControleCorretor() throws Exception {
        desserializaCorretor();
    }

    // Buscar um corretor no array
    public Corretor BuscaCorretor(String creci) {
        Corretor result = null;
        for (int i = 0; i < arrayCorretor.size(); i++) {
            if (arrayCorretor.get(i).getCreci() == creci) {
                result = arrayCorretor.get(i);
            }
        }
        return result;
    }

    // Listar todos os corretores cadastrados
    public String TodosCorretores() throws Exception {
        String resultado = ("");
        for (int i = 0; i < arrayCorretor.size(); i++) {
            resultado += ("\nNome: " + arrayCorretor.get(i).getNome() + " CRECI: " + arrayCorretor.get(i).getCreci());
        }
        return resultado;
    }

    // Função para calcular o totla faturado por corretor em um periodo 
    public String TotalFatCorretor(ArrayList<Imovel> imovel, Corretor corretor, Calendar inicio, Calendar fim) {
        double total = 0;

        for (int i = 0; i < imovel.size(); i++) {
            ArrayList<Proposta> proposta = imovel.get(i).getListaPropostas();
            for (int j = 0; j < proposta.size(); j++) {
                if (proposta.get(j).getCorretor() == corretor && proposta.get(j).getEstado() == Util.VENDIDO && proposta.get(j).getData().after(inicio) && proposta.get(j).getData().before(fim)) {
                    total += ((corretor.getPercCorretagem() / 100) * imovel.get(i).getComissao());
                }
            }
        }
        return (" Nome do corretor:" + corretor.getNome() + "\n Periodo: " + inicio + " até " + fim + "\n Valor total: " + total);
    }

    // Função para mostrar as visitas por corretor no periodo
    public String VisitasCorretor(ArrayList<Imovel> imovel, Corretor corretor, Calendar inicio, Calendar fim) {
        String resultado = ("Imoveis que o corretor " + corretor.getNome() + " visitou:");
        for (int i = 0; i < imovel.size(); i++) {
            ArrayList<Visita> visitas = imovel.get(i).getListaVisitas();
            for (int j = 0; j < visitas.size(); j++) {
                if (visitas.get(j).getCorretor() == corretor && visitas.get(j).getData().after(inicio) && visitas.get(j).getData().before(fim)) {
                    resultado += ("\nTipo do imovel :" + imovel.get(i).getTipo() + "Preço: " + imovel.get(i).getPreco() + "Data de Inclusão do imovel :" + imovel.get(i).getDataInclusao() + "Data de visita" + visitas.get(j).getData());
                }
            }
        }
        return resultado;
    }

    // Cria um novo corretor no sistema
    public void criaCorretor(String cpf, String nome, String email, String fone, String creci, double percCorretagem) {
        Corretor corretor = new Corretor(cpf, nome, email, fone, creci, percCorretagem);
        arrayCorretor.add(corretor);
    }

    // Faz a leitura do arquivo que contem os corretores
    private void desserializaCorretor() throws Exception {
        File objFile = new File("corretores.dat");
        if (objFile.exists()) {
            FileInputStream objFileIS = new FileInputStream("corretores.dat");
            ObjectInputStream objIS = new ObjectInputStream(objFileIS);
            arrayCorretor = (ArrayList<Corretor>) objIS.readObject();
            objIS.close();
        }
    }

    // Salva os corretores no arquivo 
    private void serializaCorretor() throws Exception {
        FileOutputStream objFileOS = new FileOutputStream("corretores.dat");
        ObjectOutputStream objOS = new ObjectOutputStream(objFileOS);
        objOS.writeObject(arrayCorretor);
        objOS.flush();
        objOS.close();
    }

    public void finalize() throws Exception {
        serializaCorretor();
    }
}
