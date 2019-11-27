package Limite;

import Controle.*;
import Modelo.Comprador;
import Modelo.Corretor;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.*;

public class LimiteProposta{

    ControlePrincipal objControlePrincipal;

    public LimiteProposta(ControlePrincipal objCtrPrin) {
        JFrame proposta = new JFrame("Proposta");
        proposta.setSize(720, 480);
        proposta.setResizable(false);
        proposta.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        proposta.setLocationRelativeTo(null);
        proposta.setVisible(true);
        proposta.setLayout(new FlowLayout());
        objControlePrincipal = objCtrPrin;

        // Passando o array do controle
        ArrayList<Comprador> arraycom = objControlePrincipal.getObjCtrComprador().getComprador();
        ArrayList<Corretor> arrayco = objControlePrincipal.getObjCtrCorretor().getarrayCorretor();

        // Tela ---------------------------------------------------------------
        // Campo para pegar texto na interface     
        JTextField textValor = new JTextField(20);

        // Combox corretor e comprador
        String str1[] = new String[arrayco.size()];
        String str2[] = new String[arraycom.size()];

        for (int i = 0; i < arraycom.size(); i++) {
            str1[i] = ("\nNome:" + arrayco.get(i).getNome() + "- CRECI:" + arrayco.get(i).getCreci());
        }

        for (int i = 0; i < arraycom.size(); i++) {
            str2[i] = ("\nNome:" + arraycom.get(i).getNome() + "- CPF:" + arraycom.get(i).getCpf());
        }

        JComboBox combo1 = new JComboBox(str1);
        JComboBox combo2 = new JComboBox(str2);

        //Texto definido para aparecer na interface 
        JLabel Valor = new JLabel("Digite o nome do CPF do Corretor:");
        JLabel Comprador = new JLabel("Selecione um comprador");
        JLabel Corretor = new JLabel("Selecione um corretor");
        JButton Registrar = new JButton("Registrar Proposta");

        proposta.add(Comprador);
        proposta.add(combo1);
        proposta.add(Corretor);
        proposta.add(combo2);
        proposta.add(Valor);
        proposta.add(Registrar);

        // Atividade do botão
        Registrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                objCtrPrin.getObjCtrProposta().criaProposta(Calendar.getInstance(), arraycom.get(combo1.getSelectedIndex()), arrayco.get(combo2.getSelectedIndex()), Double.parseDouble(textValor.getText()));

            }
        });
    }
}
