package Limite;

import Controle.ControlePrincipal;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.Calendar;
import java.util.Properties;
import javax.swing.*;
import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.UtilCalendarModel;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.JDatePanelImpl;

class LimiteCorretor extends JPanel {

    // Link para o limite principal e Controle principal
    private LimitePrincipal objLimPrincipal;
    private ControlePrincipal objCtrPrincipal;

    // Construtor
    public LimiteCorretor(ControlePrincipal objCtrPrin, LimitePrincipal objLimPrin, int operacao){
        objLimPrincipal = objLimPrin;
        objCtrPrincipal = objCtrPrin;

        // Configuração Layout
        this.setSize(700, 400);
        this.setLayout(new FlowLayout());

        // Chamar as funções de acordo com a função que chama o cosntrutor
        switch (operacao) {
            case 1:
                cadastrarCorretor();
                break;
            case 2:
                ValorTotalCorretor();
                break;
            case 3:
                VisitasCorretor();
                break;
            default:
                break;
        }
    }

    //Função que calcula o valor total que cada corretor vai receber de comissão
    private void ValorTotalCorretor(){

        // Botões, um para calcular o valor e outro para atualziar a lista de corretores
        JButton BtCalcular = new JButton("Calcular valor total");
        JButton BtVoltar = new JButton("Voltar");

        // Area de exibição de texto, um para a lista e outro para o valor final
        JTextArea area = new JTextArea();
        JTextArea total = new JTextArea();

        // JLabels para informar o campo para o usuario digitar 
        JLabel CRECI = new JLabel("Digite o CRECI do Corretor desejado: ");
        JLabel dataInicial = new JLabel("Selecione a data inicial:");
        JLabel dataFinal = new JLabel("Selecione a data final:");

        // JText para digitar os campos informados pelo JLabels
        JTextField textCRECI = new JTextField(15);
       
        UtilCalendarModel modelI = new UtilCalendarModel();
        Properties pI = new Properties();
        pI.put("text.today", "Today");
        pI.put("text.month", "Month");
        pI.put("text.year", "Year");
        UtilCalendarModel modelF = new UtilCalendarModel();
        Properties pF = new Properties();
        pF.put("text.today", "Today");
        pF.put("text.month", "Month");
        pF.put("text.year", "Year");
        JDatePanelImpl datePanelInicial = new JDatePanelImpl(modelI, pI);
        JDatePanelImpl datePanelFinal = new JDatePanelImpl(modelF, pF);
        
        JDatePickerImpl datePickerInicial = new JDatePickerImpl(datePanelInicial, new DateComponentFormatter());
        JDatePickerImpl datePickerFinal = new JDatePickerImpl(datePanelFinal, new DateComponentFormatter());
 
        
        // Adicionando os botões na tela
        add(area);
        add(CRECI);
        add(textCRECI);
        add(dataInicial);
        add(datePickerInicial);
        add(dataFinal);
        add(datePickerFinal);
        add(BtCalcular);
        add(total);
        add(BtVoltar);

        // Carregando a lista de corretores no sistema 
        area.setText(objCtrPrincipal.getObjCtrCorretor().TodosCorretores());

        // Definindo a ação do botão BtCalcular, que vai colher os dados e chamar a função para calcular o valor total
        BtCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Calendar inicio = (Calendar) datePickerInicial.getModel().getValue();
                Calendar fim = (Calendar) datePickerFinal.getModel().getValue();
                
                total.setText(objCtrPrincipal.getObjCtrCorretor().TotalFatCorretor(objCtrPrincipal.getObjCtrImovel().getListaImovel(), 
                        objCtrPrincipal.getObjCtrCorretor().BuscaCorretor(textCRECI.getText()), 
                        inicio, fim));
            }
        });
        BtVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                JPanel cards = objLimPrincipal.cards;
                CardLayout principal = (CardLayout) (cards.getLayout());
                principal.show(cards, "Tela Principal");
            }
        });

    }

    private void VisitasCorretor() {
        // Botões, um para calcular o valor e outro para atualziar a lista de corretores
        JButton BtVisitas = new JButton("Mostrar Visitas");
        JButton BtVoltar = new JButton("Voltar");

        // Area de exibição de texto, um para a lista e outro para o valor final
        JTextArea area = new JTextArea();
        JTextArea total = new JTextArea();

        // JLabels para informar o campo para o usuario digitar 
        JLabel CRECI = new JLabel("Digite o CRECI do Corretor desejado: ");
        JLabel dataInicial = new JLabel("Selecione a data inicial:");
        JLabel dataFinal = new JLabel("Selecione a data final:");

        // JText para digitar os campos informados pelo JLabels
        JTextField textCRECI = new JTextField(15);
        
        UtilCalendarModel modelI = new UtilCalendarModel();
        Properties pI = new Properties();
        pI.put("text.today", "Today");
        pI.put("text.month", "Month");
        pI.put("text.year", "Year");
        UtilCalendarModel modelF = new UtilCalendarModel();
        Properties pF = new Properties();
        pF.put("text.today", "Today");
        pF.put("text.month", "Month");
        pF.put("text.year", "Year");
        JDatePanelImpl datePanelInicial = new JDatePanelImpl(modelI, pI);
        JDatePanelImpl datePanelFinal = new JDatePanelImpl(modelF, pF);
        
        JDatePickerImpl datePickerInicial = new JDatePickerImpl(datePanelInicial, new DateComponentFormatter());
        JDatePickerImpl datePickerFinal = new JDatePickerImpl(datePanelFinal, new DateComponentFormatter());

        // Adicionando os botões na tela
        add(area);
        add(CRECI);
        add(textCRECI);
        add(dataInicial);
        add(datePickerInicial);
        add(dataFinal);
        add(datePickerFinal);
        add(BtVisitas);
        add(total);
        add(BtVoltar);

        // Carregando a lista de corretores no sistema 
        area.setText(objCtrPrincipal.getObjCtrCorretor().TodosCorretores());

        // Definindo a ação do botão BtVisitas, que vai colher os dados e chamar a função para mostrar as visitas
        BtVisitas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Calendar inicio = (Calendar) datePickerInicial.getModel().getValue();
                Calendar fim = (Calendar) datePickerFinal.getModel().getValue();

                total.setText(objCtrPrincipal.getObjCtrCorretor().VisitasCorretor(objCtrPrincipal.getObjCtrImovel().getListaImovel(),
                        objCtrPrincipal.getObjCtrCorretor().BuscaCorretor(textCRECI.getText()), inicio, fim));
                
            }
        });
        BtVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                JPanel cards = objLimPrincipal.cards;
                CardLayout principal = (CardLayout) (cards.getLayout());
                principal.show(cards, "Tela Principal");
            }
        });

    }

    // Função para cadastrar os corretores no sistema
    private void cadastrarCorretor() {

        // JLabels para informar o significado do campo JText 
        JLabel lblCPF = new JLabel("Digite o CPF: ");
        JTextField txtCPF = new JTextField(15);
        JLabel lblNome = new JLabel("Digite o nome: ");
        JTextField txtNome = new JTextField(15);
        JLabel lblEmail = new JLabel("Digite o email: ");
        JTextField txtEmail = new JTextField(15);
        JLabel lblFone = new JLabel("Digite o telefone: ");
        JTextField txtFone = new JTextField(15);
        JLabel lblCreci = new JLabel("Digite o CRECI: ");
        JTextField txtCreci = new JTextField(15);
        JLabel lblCorretagem = new JLabel("Digite a porcentagem de corretagem: ");
        JTextField txtCorretagem = new JTextField(15);

        // Botões 
        JButton jbCadastrar = new JButton("Cadastrar");
        JButton jbVoltar = new JButton("Voltar");

        // Adicionando os botões na tela
        add(lblCPF);
        add(txtCPF);
        add(lblNome);
        add(txtNome);
        add(lblEmail);
        add(txtEmail);
        add(lblFone);
        add(txtFone);
        add(lblCreci);
        add(txtCreci);
        add(lblCorretagem);
        add(txtCorretagem);
        add(jbCadastrar);
        add(jbVoltar);

        // Definindo a ação do botão jbCadastrar, que vai cadastrar novos Corretores
        jbCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                objCtrPrincipal.getObjCtrCorretor().criaCorretor(txtCPF.getText(), txtNome.getText(),
                        txtEmail.getText(), txtFone.getText(), txtCreci.getText(), Double.parseDouble(txtCorretagem.getText()));
                JOptionPane.showMessageDialog(null, "Corretor cadastrado");

                JPanel cards = objLimPrincipal.cards;
                CardLayout cardCadComprador = (CardLayout) (cards.getLayout());
                cardCadComprador.show(cards, "Tela Principal");
            }
        });

        // Definindo a ação do botão jbVoltar, para voltar a tela inicial
        jbVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
                JPanel cards = objLimPrincipal.cards;
                CardLayout cardCadComprador = (CardLayout) (cards.getLayout());
                cardCadComprador.show(cards, "Tela Principal");
            }
        });
    }
}
