
package Limite;

import java.awt.*;
import javax.swing.*;
import Controle.ControlePrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class LimiteComprador extends JPanel{
    private LimitePrincipal objLimPrincipal;
    private ControlePrincipal objCtrPrincipal;
    
    public LimiteComprador(ControlePrincipal objCtrPrin, LimitePrincipal objLimPrin, int operacao) {
        objLimPrincipal = objLimPrin;
        objCtrPrincipal = objCtrPrin;
        this.setSize(720, 480);
        this.setLayout(new FlowLayout());
        
        switch(operacao){
            case 1:
                cadastrarComprador();
                break;
            default:
                break;
        }
    }
    
    private void cadastrarComprador(){
        String str[] = {"Indiferente", "Telefone", "Email", "Mensagem Whatsapp"};
        JLabel lblCPF = new JLabel("Digite o CPF: ");
        JTextField txtCPF = new JTextField(15);
        JLabel lblNome = new JLabel("Digite o nome: ");
        JTextField txtNome = new JTextField(15);
        JLabel lblEmail = new JLabel("Digite o email: ");
        JTextField txtEmail = new JTextField(15);
        JLabel lblFone = new JLabel("Digite o telefone: ");
        JTextField txtFone = new JTextField(15);
        JLabel lblContato = new JLabel("Selecione o contato preferencial: ");
        JComboBox cmbContato = new JComboBox(str);
 
        this.add(lblCPF);
        this.add(txtCPF);
        this.add(lblNome);
        this.add(txtNome);
        this.add(lblEmail);
        this.add(txtEmail);
        this.add(lblFone);
        this.add(txtFone);
        this.add(lblContato);
        this.add(cmbContato);
        
        JButton jbCadastrar = new JButton("Cadastrar");
        JButton jbVoltar = new JButton("Voltar");
        this.add(jbCadastrar);
        this.add(jbVoltar);
        
        jbCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                objCtrPrincipal.getObjCtrComprador().criaComprador(txtCPF.getText(), txtNome.getText(), txtEmail.getText(), txtFone.getText(), (String) cmbContato.getSelectedItem());
                JOptionPane.showMessageDialog(null, "Comprador cadastrado");
            
                
                JPanel cards = objLimPrincipal.cards;
                CardLayout principal = (CardLayout) (cards.getLayout());
                principal.show(cards, "Tela Principal");
            }
        });
        jbVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                JPanel cards = objLimPrincipal.cards;
                CardLayout principal = (CardLayout) (cards.getLayout());
                principal.show(cards, "Tela Principal");
            }
        });
    } 
}
