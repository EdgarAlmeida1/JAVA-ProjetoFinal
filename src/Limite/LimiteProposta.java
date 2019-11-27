package Limite;

import Controle.ControlePrincipal;
import Modelo.Comprador;
import Modelo.Corretor;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

class LimiteProposta extends JFrame {
    // Layout
    FlowLayout layout = new FlowLayout();
    Container container = getContentPane();

    public LimiteProposta(ControlePrincipal objCtrPrin, LimitePrincipal objLimPrin, int operacao) {

        // Configura Layout
        setLayout(layout);

        // Tela
        // Campo para pegar texto na interface 
        JTextField textnome = new JTextField(20);
        JTextField textcpf = new JTextField(20);
        JTextField textnomeCo = new JTextField(20);
        JTextField textCRECICo = new JTextField(20);
        JTextField textValor = new JTextField(20);
        
        //Texto definido para aparecer na interface 
        JLabel nome = new JLabel("Digite o nome do comprador:");
        JLabel cpf = new JLabel("Digite o nome do CPF do comprador:");
        JLabel nomeCorrtor = new JLabel("Digite o nome do Corretor:");
        JLabel CRECICorretor = new JLabel("Digite o nome do CPF do Corretor:");
        JLabel Valor = new JLabel("Digite o nome do CPF do Corretor:");
        JButton Registrar = new JButton("Registrar Proposta");

        this.add(nome);
        this.add(textnome);
        this.add(cpf);
        this.add(textcpf);
        this.add(textnomeCo);
        this.add(nomeCorrtor);
        this.add(textCRECICo);
        this.add(CRECICorretor);
        this.add(textValor);
        this.add(Valor);
        this.add(Registrar);

        // Atividade do botão
        Registrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                layout.setAlignment(FlowLayout.CENTER);
                if (objCtrPrin.getObjCtrComprador().StatusComprador(textcpf.getText()) == true) {
                    
                    // Busca o objeto comprador e corretor
                    Comprador buscacomprador = objCtrPrin.getObjCtrComprador().BuscaComprador(textcpf.getText());
                    Corretor buscaCorretor = objCtrPrin.getObjCtrCorretor().BuscaCorretor(textCRECICo.getText());

                    // FALTA POR A DATA 
                    //objCtrPrin.getObjCtrProposta().criaProposta(/*data*/,buscacomprador, buscaCorretor, Double.parseDouble(textValor));
                }

                //REALINHA OS COMPONENTES ANEXADOS
                layout.layoutContainer(container);
            }
        });
    }
}
