
package Limite;

import java.awt.*;
import javax.swing.*;


class LimiteComprador extends JPanel{

    public LimiteComprador(int operacao) {
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
        JLabel lblCPF = new JLabel("Digite o CPF: ");
        JTextField txtCPF = new JTextField(20);
        
        this.add(lblCPF);
        this.add(txtCPF);
        
        // CADASTRAR O COMPRADOR (ADICIONAR OS CAMPOS)
    }
    
}
