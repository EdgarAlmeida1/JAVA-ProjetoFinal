
package Limite;

import Controle.ControlePrincipal;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

class LimiteVendedor extends JPanel{
    private LimitePrincipal objLimPrincipal;
    private ControlePrincipal objCtrPrincipal;
    
    public LimiteVendedor(ControlePrincipal objCtrPrin, LimitePrincipal objLimPrin, int operacao) {
        objLimPrincipal = objLimPrin;
        objCtrPrincipal = objCtrPrin;
        this.setSize(720, 480);
        this.setLayout(new FlowLayout());
        
        switch(operacao){
            case 1:
                cadastrarVendedor();
                break;
            default:
                break;
        }
    }
    
    private void cadastrarVendedor(){
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
                objCtrPrincipal.getObjCtrVendedor().criaVendedor(txtCPF.getText(), txtNome.getText(), txtEmail.getText(), txtFone.getText(), (String) cmbContato.getSelectedItem());
                JOptionPane.showMessageDialog(null, "Vendedor cadastrado");
                
                
                JPanel cards = objLimPrincipal.cards;
                CardLayout principal = (CardLayout) (cards.getLayout());
                int opcao = JOptionPane.showConfirmDialog(null, "Deseja cadastrar um imovel para este vendedor? ");
                
                if(opcao == JOptionPane.YES_OPTION){
                    LimiteImovel cadastrarImovel = new LimiteImovel(objCtrPrincipal, objLimPrincipal, 1);
                    cadastrarImovel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                    cards.add(cadastrarImovel, "Cadastrar Imovel");
                    principal.show(cards, "Cadastrar Imovel");
                }
                else{
                    principal.show(cards, "Tela Principal");
                }
            }
        });
        jbVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                
                JPanel cards = objLimPrincipal.cards;
                CardLayout principal = (CardLayout) (cards.getLayout());
                principal.show(cards, "Tela Principal");
            }
        });
    }
}
