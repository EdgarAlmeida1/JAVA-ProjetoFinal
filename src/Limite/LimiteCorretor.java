
package Limite;

import Controle.ControlePrincipal;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

class LimiteCorretor extends JPanel{
    private LimitePrincipal objLimPrincipal;
    private ControlePrincipal objCtrPrincipal;
    
    public LimiteCorretor(ControlePrincipal objCtrPrin, LimitePrincipal objLimPrin, int operacao) {
        objLimPrincipal = objLimPrin;
        objCtrPrincipal = objCtrPrin;
        this.setSize(720, 480);
        this.setLayout(new FlowLayout());
        
        switch(operacao){
            case 1:
                cadastrarCorretor();
                break;
            default:
                break;
        }
    }
    
    private void cadastrarCorretor(){
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
 
        this.add(lblCPF);
        this.add(txtCPF);
        this.add(lblNome);
        this.add(txtNome);
        this.add(lblEmail);
        this.add(txtEmail);
        this.add(lblFone);
        this.add(txtFone);
        this.add(lblCreci);
        this.add(txtCreci);
        this.add(lblCorretagem);
        this.add(txtCorretagem);
        
        JButton jbCadastrar = new JButton("Cadastrar");
        JButton jbVoltar = new JButton("Voltar");
        this.add(jbCadastrar);
        this.add(jbVoltar);
        
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
