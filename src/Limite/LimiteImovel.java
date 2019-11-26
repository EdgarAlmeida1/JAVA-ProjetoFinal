
package Limite;

import Controle.*;
import Modelo.Imovel;
import Modelo.Vendedor;
import Utilitario.Util;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;


class LimiteImovel extends JPanel {
    private LimitePrincipal objLimPrincipal;
    private ControlePrincipal objCtrPrincipal;
    File selectedFile, destination;
    ArrayList<Vendedor> vendedores = null;
    
    
    public LimiteImovel(ControlePrincipal objCtrPrin, LimitePrincipal objLimPrin, int operacao) {
        objLimPrincipal = objLimPrin;
        objCtrPrincipal = objCtrPrin;
        vendedores = objCtrPrincipal.getObjCtrVendedor().getArrayVendedor();
        this.setSize(720, 480);
        this.setLayout(new FlowLayout());
        
        switch(operacao){
            case 1:
                cadastrarImovel();
                break;
            case 2:
                catalogoImoveis();
                break;
            case 3:
                //propostasPendentes();
                break;
            case 4:
                //eventosPorImovel();
                break;
            default:
                break;
        }
    }
    
    //======================================================================================================
    
    //Case 1
    private void cadastrarImovel(){
        String vends[] = new String[vendedores.size()];
        for(int i=0;i<vendedores.size();i++) vends[i] = vendedores.get(i).getCpf();
        
        JLabel lblCodigo = new JLabel("Digite o código do imóvel: ");
        JTextField txtCodigo = new JTextField(15);
        
        JLabel lblTipo = new JLabel("Selecione o tipo do imóvel: ");
        JComboBox cmbTipo = new JComboBox(new String[]{Util.CASA, Util.LOTE, Util.APTO, Util.SALA, Util.RURAL});
        
        JLabel lblDescricao = new JLabel("Digite a descrição do imóvel: ");
        JTextField txtDescricao = new JTextField(50);
        
        JLabel lblArquivoFoto = new JLabel("Insira uma foto do imóvel: ");
        JButton jbUpar = new JButton("Inserir");
        
        JLabel lblPreco = new JLabel("Digite o preço do imóvel: ");
        JTextField txtPreco = new JTextField(10);
        
        JLabel lblComissao = new JLabel("Selecione a comissão: ");
        JComboBox cmbComissao = new JComboBox(new Double[]{0.01, 0.02, 0.03, 0.04, 0.05});
        
        JLabel lblVendedor = new JLabel("Vendedor responsável: ");
        JComboBox cmbVendedores = new JComboBox(vends);
        
        this.add(lblCodigo);
        this.add(txtCodigo);
        this.add(lblTipo);
        this.add(cmbTipo);
        this.add(lblDescricao);
        this.add(txtDescricao);
        this.add(lblArquivoFoto);
        this.add(jbUpar);
        this.add(lblPreco);
        this.add(txtPreco);
        this.add(lblComissao);
        this.add(cmbComissao);
        this.add(lblVendedor);
        this.add(cmbVendedores);
        
      
        JButton jbCadastrar = new JButton("Cadastrar");
        JButton jbVoltar = new JButton("Voltar");
        this.add(jbCadastrar);
        this.add(jbVoltar);
        
        jbUpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                int returnValue = jfc.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    selectedFile = jfc.getSelectedFile();
                }
            }
        });
        
        jbCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cpfVend = (String) cmbVendedores.getSelectedItem();
                Vendedor v = null;
                for(Vendedor vi:vendedores){
                    if(vi.getCpf().equals(cpfVend)){
                        v = vi; break;
                    }
                }
                objCtrPrincipal.getObjCtrImovel().criaImovel(Integer.parseInt(txtCodigo.getText()), (String) cmbTipo.getSelectedItem(), txtDescricao.getText(), 
                        txtCodigo.getText(), Double.parseDouble(txtPreco.getText()), (Double) cmbComissao.getSelectedItem(), Calendar.getInstance(), v);
                JOptionPane.showMessageDialog(null, "Imóvel cadastrado");
                
                destination = new File("images/"+txtCodigo.getText());
                try {
                    objCtrPrincipal.getObjCtrImovel().copiaArquivo(selectedFile, destination);
                } catch (IOException ex) {}
                
                JPanel cards = objLimPrincipal.cards;
                CardLayout principal = (CardLayout) (cards.getLayout());
                principal.show(cards, "Tela Principal");
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
    
    //======================================================================================================
    
    //Case 2
    private void catalogoImoveis(){
        JLabel lblTipo = new JLabel("Insira o tipo do imóvel que deseja ver: ");
        JComboBox cmbTipo = new JComboBox(new Object[]{Util.CASA, Util.LOTE, Util.APTO, Util.SALA, Util.RURAL});
        
        this.add(lblTipo);
        this.add(cmbTipo);
        
        JButton jbProcurar = new JButton("Procurar");
        JButton jbVoltar = new JButton("Voltar");
        this.add(jbProcurar);
        this.add(jbVoltar);
        
        DefaultListModel lm = new DefaultListModel();
        JList listaImovel = new JList(lm);
        
        //listaImovel.addListSelectionListener();
        
        jbProcurar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String itemSelecao = (String) cmbTipo.getSelectedItem();
                for(Imovel imov: objCtrPrincipal.getObjCtrImovel().getListaImovel()){
                    if(imov.getEstado().equals(Util.ATIVO) && imov.getTipo().equals(itemSelecao)) lm.addElement(imov.getCodigo());
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
    
    //======================================================================================================
    
    //Case 3
    private void propostasPendentes(){
        
    }
    
    //======================================================================================================
    
    //Case 4
    private void eventorPorImovel(){
        
    }
}
