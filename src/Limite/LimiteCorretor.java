package Limite;

import Controle.ControlePrincipal;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

class LimiteCorretor extends JPanel {

    // Link para o limite principal e Controle principal
    private LimitePrincipal objLimPrincipal;
    private ControlePrincipal objCtrPrincipal;

    // Construtor
    public LimiteCorretor(ControlePrincipal objCtrPrin, LimitePrincipal objLimPrin, int operacao) throws ParseException, Exception {
        objLimPrincipal = objLimPrin;
        objCtrPrincipal = objCtrPrin;

        // Configuração Layout
        this.setSize(700, 400);

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
    private void ValorTotalCorretor() throws ParseException, Exception {

        // Botões, um para calcular o valor e outro para atualziar a lista de corretores
        JButton BtCalcular = new JButton("Calcular valor total");
        JButton BtAtualizar = new JButton("Atualizar lista de corretores");

        // Area de exibição de texto, um para a lista e outro para o valor final
        JTextArea area = new JTextArea();
        JTextArea total = new JTextArea();

        // JLabels para informar o campo para o usuario digitar 
        JLabel CRECI = new JLabel("Digite o CRECI do Corretor desejado: ");
        JLabel dataInicial = new JLabel("Digite a data inicial:");
        JLabel dataFinal = new JLabel("Digite a data Final:");

        // JText para digitar os campos informados pelo JLabels
        JTextField textCRECI = new JTextField(15);
        JTextField textDataInicial = new JTextField(4);
        JTextField textDataFinal = new JTextField(4);

        // Adicionando os botões na tela
        add(area);
        add(BtAtualizar);
        add(CRECI);
        add(textCRECI);
        add(dataInicial);
        add(textDataInicial);
        add(dataFinal);
        add(textDataFinal);
        add(BtCalcular);
        add(total);

        // Carregando a lista de corretores no sistema 
        area.setText(objCtrPrincipal.getObjCtrCorretor().TodosCorretores());

        // Definindo a ação do botão BtCalcular, que vai colher os dados e chamar a função para calcular o valor total
        BtCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //de STRING para CALENDAR
                try {
                    SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");

                    Calendar inicio = Calendar.getInstance();
                    Calendar fim = Calendar.getInstance();

                    inicio.setTime(formatoData.parse(textDataInicial.getText()));
                    fim.setTime(formatoData.parse(textDataFinal.getText()));

                    total.setText(objCtrPrincipal.getObjCtrCorretor().TotalFatCorretor(objCtrPrincipal.getObjCtrImovel().getListaImovel(), objCtrPrincipal.getObjCtrCorretor().BuscaCorretor(textCRECI.getText()), inicio, fim));
                } catch (Exception r) {
                }
            }
        });

        // Definindo a ação do botão BtAtualizar, que vai atualizar a lista se houver novos corretores
        BtAtualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    area.setText(objCtrPrincipal.getObjCtrCorretor().TodosCorretores());
                } catch (Exception ex) {
                    Logger.getLogger(LimiteCorretor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }

    private void VisitasCorretor() throws Exception {
        // Botões, um para calcular o valor e outro para atualziar a lista de corretores
        JButton BtVisitas = new JButton("Mostrar Visitas");
        JButton BtAtualizar = new JButton("Atualizar lista de corretores");

        // Area de exibição de texto, um para a lista e outro para o valor final
        JTextArea area = new JTextArea();
        JTextArea total = new JTextArea();

        // JLabels para informar o campo para o usuario digitar 
        JLabel CRECI = new JLabel("Digite o CRECI do Corretor desejado: ");
        JLabel dataInicial = new JLabel("Digite a data inicial:");
        JLabel dataFinal = new JLabel("Digite a data Final:");

        // JText para digitar os campos informados pelo JLabels
        JTextField textCRECI = new JTextField(15);
        JTextField textDataInicial = new JTextField(4);
        JTextField textDataFinal = new JTextField(4);

        // Adicionando os botões na tela
        add(area);
        add(BtAtualizar);
        add(CRECI);
        add(textCRECI);
        add(dataInicial);
        add(textDataInicial);
        add(dataFinal);
        add(textDataFinal);
        add(BtVisitas);
        add(total);

        // Carregando a lista de corretores no sistema 
        area.setText(objCtrPrincipal.getObjCtrCorretor().TodosCorretores());

        // Definindo a ação do botão BtVisitas, que vai colher os dados e chamar a função para mostrar as visitas
        BtVisitas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //de STRING para CALENDAR
                try {
                    SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");

                    Calendar inicio = Calendar.getInstance();
                    Calendar fim = Calendar.getInstance();

                    inicio.setTime(formatoData.parse(textDataInicial.getText()));
                    fim.setTime(formatoData.parse(textDataFinal.getText()));

                    total.setText(objCtrPrincipal.getObjCtrCorretor().VisitasCorretor(objCtrPrincipal.getObjCtrImovel().getListaImovel(), objCtrPrincipal.getObjCtrCorretor().BuscaCorretor(textCRECI.getText()), inicio, fim));
                } catch (Exception r) {
                }
            }
        });

        // Definindo a ação do botão BtAtualizar, que vai atualizar a lista se houver novos corretores
        BtAtualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    area.setText(objCtrPrincipal.getObjCtrCorretor().TodosCorretores());
                } catch (Exception ex) {
                    Logger.getLogger(LimiteCorretor.class.getName()).log(Level.SEVERE, null, ex);
                }
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

                txtCPF.setText("");
                txtCreci.setText("");
                txtEmail.setText("");
                txtFone.setText("");
                txtNome.setText("");
                txtCorretagem.setText("");

                JPanel cards = objLimPrincipal.cards;
                CardLayout cardCadComprador = (CardLayout) (cards.getLayout());
                cardCadComprador.show(cards, "Tela Principal");
            }
        });

        // Definindo a ação do botão jbVoltar, para voltar a tela inicial
        jbVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtCPF.setText("");
                txtCreci.setText("");
                txtEmail.setText("");
                txtFone.setText("");
                txtNome.setText("");
                txtCorretagem.setText("");

                JPanel cards = objLimPrincipal.cards;
                CardLayout cardCadComprador = (CardLayout) (cards.getLayout());
                cardCadComprador.show(cards, "Tela Principal");
            }
        });
    }
}
