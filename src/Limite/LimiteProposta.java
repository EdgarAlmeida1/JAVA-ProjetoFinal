package Limite;

import Controle.ControlePrincipal;
import Modelo.Comprador;
import Modelo.Corretor;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

class LimiteProposta extends JFrame {

    // Layout
    FlowLayout layout = new FlowLayout();
    Container container = getContentPane();

    public LimiteProposta(ControlePrincipal objCtrPrin, LimitePrincipal objLimPrin, int operacao) {

        // Configura Layout
        setLayout(layout);

        // Passando o arrey do controle
        ArrayList<Comprador> arraycom = objCtrPrin.getObjCtrComprador().getComprdor();
        ArrayList<Corretor> arrayco = objCtrPrin.getObjCtrCorretor().getarrayComprador();

        // Tela ---------------------------------------------------------------
        // Campo para pegar texto na interface     
        JTextField textValor = new JTextField(20);

        // Combox corretor e comprador
        String str1[] = null;
        String str2[] = null;

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

        this.add(Comprador);
        this.add(combo1);
        this.add(Corretor);
        this.add(combo2);
        this.add(Valor);
        this.add(Registrar);

        // Atividade do botão
        Registrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                layout.setAlignment(FlowLayout.CENTER);

                // FALTA POR A DATA 
                objCtrPrin.getObjCtrProposta().criaProposta(Calendar.getInstance(), arraycom.get(combo1.getSelectedIndex()), arrayco.get(combo2.getSelectedIndex()), Double.parseDouble(textValor.getText()));

                //REALINHA OS COMPONENTES ANEXADOS
                layout.layoutContainer(container);
            }
        });
    }
}
