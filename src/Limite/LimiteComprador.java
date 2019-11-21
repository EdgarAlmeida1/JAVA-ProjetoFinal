
package Limite;

import java.awt.*;
import javax.swing.*;
import Controle.ControleComprador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class LimiteComprador extends JPanel{
    private ControleComprador objCtrComprador;
    
    public LimiteComprador(ControleComprador objCtrCom, int operacao) {
        objCtrComprador = objCtrCom;
        this.setSize(720, 480);
        this.setLayout(new FlowLayout());
        objCtrComprador.teste();
        switch(operacao){
            case 1:
                cadastrarComprador();
                break;
            default:
                break;
        }
    }
    
    private void cadastrarComprador(){
        JLabel lblCPF = new JLabel("Digite o CPF: ");
        JTextField txtCPF = new JTextField(15);
        JLabel lblNome = new JLabel("Digite o nome: ");
        JTextField txtNome = new JTextField(15);
        JLabel lblEmail = new JLabel("Digite o email: ");
        JTextField txtEmail = new JTextField(15);
        JLabel lblFone = new JLabel("Digite o telefone: ");
        JTextField txtFone = new JTextField(15);
        JLabel lblContato = new JLabel("Digite o contato preferencial: ");
        JTextField txtContato = new JTextField(15);
 
        this.add(lblCPF);
        this.add(txtCPF);
        this.add(lblNome);
        this.add(txtNome);
        this.add(lblEmail);
        this.add(txtEmail);
        this.add(lblFone);
        this.add(txtFone);
        this.add(lblContato);
        this.add(txtContato);
        
        JButton jb = new JButton("Cadastrar");
        this.add(jb);
        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                objCtrComprador.criaComprador(txtCPF.getText(), txtNome.getText(), txtEmail.getText(), txtFone.getText(), txtContato.getText());
            }
        });
    }
}
