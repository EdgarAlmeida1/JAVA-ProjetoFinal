/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Limite;

import Controle.*;
import Modelo.Imovel;
import Modelo.Vendedor;
import Utilitario.Util;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author rafae
 */
class LimiteImovel extends JPanel {
    private LimitePrincipal objLimPrincipal;
    private ControlePrincipal objCtrPrincipal;
    public ControleImovel objCtrImovel;
    
    DefaultListModel lm;
   // private JList<Imovel> lista = new JList(lm);
    Calendar calendario = Calendar.getInstance();
    //Vendedor vend = new Vendedor();
    
    //JComboBox cmbTipo = new JComboBox(new Object[]{Util.CASA, Util.LOTE, Util.APTO, Util.SALA, Util.RURAL});
    
    public LimiteImovel(ControlePrincipal objCtrPrin, LimitePrincipal objLimPrin, int operacao) {
        objLimPrincipal = objLimPrin;
        objCtrPrincipal = objCtrPrin;
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
        JLabel lblCodigo = new JLabel("Digite o código do imóvel: ");
        JTextField txtCodigo = new JTextField(15);
        
        JLabel lblTipo = new JLabel("Insira o tipo do imóvel: ");
        JComboBox cmbTipo = new JComboBox(new Object[]{Util.CASA, Util.LOTE, Util.APTO, Util.SALA, Util.RURAL});
        
        JLabel lblDescricao = new JLabel("Digite a descrição do imóvel: ");
        JTextField txtDescricao = new JTextField(50);
        
        JLabel lblArquivoFoto = new JLabel("Insira uma foto do imóvel: ");
        JTextField txtArquivoFoto = new JTextField(15);
        
        JLabel lblEstado = new JLabel("Insira o estado do imóvel: ");
        JComboBox cmbEstado = new JComboBox(new Object[]{Util.ATIVO, Util.INATIVO, Util.VENDIDO});
        
        JLabel lblPreco = new JLabel("Digite o preço do imóvel: ");
        JTextField txtPreco = new JTextField(10);
        
        JLabel lblComissao = new JLabel("Digite a comissão: ");
        JTextField txtComissao = new JTextField(2);
        
        JLabel lblDataInclusao = new JLabel("Data de inclusão: ");
        String strDataInclusao = calendario.get(Calendar.DAY_OF_MONTH) + "/" + calendario.get(Calendar.MONTH) + "/" + calendario.get(Calendar.YEAR);
        JTextField txtDataInclusao = new JTextField(strDataInclusao);
        txtDataInclusao.setEditable(false);
        JLabel lblVendedor = new JLabel("Vendedor responsável: ");
        JTextField txtVendedor = new JTextField(30);
        
        this.add(lblCodigo);
        this.add(txtCodigo);
        this.add(lblTipo);
        this.add(cmbTipo);
        this.add(lblDescricao);
        this.add(txtDescricao);
        this.add(lblArquivoFoto);
        this.add(txtArquivoFoto);
        this.add(lblEstado);
        this.add(cmbEstado);
        this.add(lblPreco);
        this.add(txtPreco);
        this.add(lblComissao);
        this.add(txtComissao);
        this.add(lblDataInclusao);
        this.add(txtDataInclusao);
        this.add(lblVendedor);
        this.add(txtVendedor);
        
      
        
        JButton jbCadastrar = new JButton("Cadastrar");
        JButton jbVoltar = new JButton("Voltar");
        this.add(jbCadastrar);
        this.add(jbVoltar);
        
        jbCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String strTipo = (String) cmbTipo.getSelectedItem();
                String strEstado = (String) cmbEstado.getSelectedItem();
                /*
                objCtrPrincipal.getObjCtrImovel().criaImovel(
                        Integer.parseInt(txtCodigo.getText()), //int codigo
                        strTipo,  //String tipo
                        txtDescricao.getText(), //String descricao
                        txtArquivoFoto.getText(),  //String arquivoFoto
                        strEstado, //String estado
                        Double.parseDouble(txtPreco.getText()), //Double preco
                        Double.parseDouble(txtComissao.getText()), //Double comissao 
                        calendario,  //Calendar dataInclusao
                        //txtVendedor.getText() 
                        vend); //Vendedor vendedor
                        */
                
                txtCodigo.setText("");
                //cmbTipo.setText("");
                txtDescricao.setText("");
                txtArquivoFoto.setText("");
                txtPreco.setText("");
                txtComissao.setText("");
                txtDataInclusao.setText("");
                txtVendedor.setText("");
            }
        });
        
        jbVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtCodigo.setText("");
                //cmbTipo.setText("");
                txtDescricao.setText("");
                txtArquivoFoto.setText("");
                txtPreco.setText("");
                txtComissao.setText("");
                txtDataInclusao.setText("");
                txtVendedor.setText("");
                
                
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
        
        this.add(jbProcurar);
        this.add(jbVoltar);
        //this.add(lista); //Conferir
        
        jbProcurar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cmbTipo.getSelectedItem() == Util.CASA){
                    for (Imovel imov: objCtrImovel.getListaImovel()){
                        if (imov.getTipo() == Util.CASA){
                            //lm.addElement(imov);
                        }
                    }
                }
                else if (cmbTipo.getSelectedItem() == Util.LOTE){
                    for (Imovel imov: objCtrImovel.getListaImovel()){
                        if (imov.getTipo() == Util.LOTE){
                            //lm.addElement(imov);
                        }
                    }
                }
                else if (cmbTipo.getSelectedItem() == Util.APTO){
                    for (Imovel imov: objCtrImovel.getListaImovel()){
                        if (imov.getTipo() == Util.APTO){
                            //lm.addElement(imov);
                        }
                    }
                }
                else if (cmbTipo.getSelectedItem() == Util.SALA){
                    for (Imovel imov: objCtrImovel.getListaImovel()){
                        if (imov.getTipo() == Util.SALA){
                            //lm.addElement(imov);
                        }
                    }
                }
                else if (cmbTipo.getSelectedItem() == Util.RURAL){
                    for (Imovel imov: objCtrImovel.getListaImovel()){
                        if (imov.getTipo() == Util.RURAL){
                            //lm.addElement(imov);
                        }
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
    
    //======================================================================================================
    
    //Case 3
    private void propostasPendentes(){
        
    }
    
    //======================================================================================================
    
    //Case 4
    private void eventorPorImovel(){
        
    }
}
