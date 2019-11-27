//imóveis por vendedor -> lista vendedores e seus imóveis

package Limite;

import Controle.ControlePrincipal;
import Modelo.Imovel;
import Modelo.Vendedor;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import javax.swing.*;

class LimiteVendedor extends JPanel{
    private LimitePrincipal objLimPrincipal;
    private ControlePrincipal objCtrPrincipal;
    File selectedFile = null, destination;
    ArrayList<Vendedor> vendedores = null;
    
    public LimiteVendedor(ControlePrincipal objCtrPrin, LimitePrincipal objLimPrin, int operacao) {
        objLimPrincipal = objLimPrin;
        objCtrPrincipal = objCtrPrin;
        vendedores=objCtrPrincipal.getObjCtrVendedor().getArrayVendedor();
        this.setSize(720, 480);
        
        switch(operacao){
            case 1:
                cadastrarVendedor();
                break;
            case 2:
                Imoveisporvendedor();
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
        JButton jbCadastrar = new JButton("Cadastrar");
        JButton jbVoltar = new JButton("Voltar");
        
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        JPanel p1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel p2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel p3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel p4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel p5 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel p6 = new JPanel(new FlowLayout(FlowLayout.CENTER));
       
        p1.add(lblCPF);
        p1.add(txtCPF);
        p2.add(lblNome);
        p2.add(txtNome);
        p3.add(lblEmail);
        p3.add(txtEmail);
        p4.add(lblFone);
        p4.add(txtFone);
        p5.add(lblContato);
        p5.add(cmbContato);
        p6.add(jbCadastrar);
        p6.add(jbVoltar);
        this.add(Box.createVerticalGlue());
        this.add(p1);
        this.add(p2);
        this.add(p3);
        this.add(p4);
        this.add(p5);
        this.add(p6);
        this.add(Box.createVerticalGlue());
        
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
    
    
    private void Imoveisporvendedor(){
        String vends[] = new String[vendedores.size()];
        for(int i=0;i<vendedores.size();i++) vends[i] = vendedores.get(i).getCpf();

        JLabel lblVendedor = new JLabel("Cpf do Vendedor: ");
        JComboBox cmbVendedores = new JComboBox(vends);        
      
        JButton jbBuscar = new JButton("Buscar");
        JButton jbVoltar = new JButton("Voltar");
        JTextArea imoveis = new JTextArea("");
        this.add(lblVendedor);
        this.add(cmbVendedores);
        this.add(jbBuscar);
        this.add(jbVoltar);
        this.add(imoveis);
        
    
        
        jbBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                Vendedor VendedorSelec = vendedores.get(cmbVendedores.getSelectedIndex());
                ArrayList<Imovel> imov = objCtrPrincipal.getObjCtrImovel().getListaImovel();
                
                for(Imovel aux: imov){
                    if(aux.getVendedor().getCpf().equals(VendedorSelec.getCpf())){
                        imoveis.setText(imoveis.getText()+aux.getCodigo()+" - "+aux.getEstado()+"\n");
                        
                    }
                }
                
                
                
            }
        });
        
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
