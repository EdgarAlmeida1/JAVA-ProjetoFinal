
package Limite;

import Controle.*;
import Modelo.*;
import Utilitario.Util;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileSystemView;
import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilCalendarModel;


class LimiteImovel extends JPanel implements ListSelectionListener{
    private LimitePrincipal objLimPrincipal;
    private ControlePrincipal objCtrPrincipal;
    File selectedFile = null, destination;
    ArrayList<Vendedor> vendedores = null;
    private JList listaImovel, listaImoveisSubmetidos, listaPropostas;
    private DefaultListModel lm = new DefaultListModel();
    private DefaultListModel lm2 = new DefaultListModel();
    private DefaultListModel lm3 = new DefaultListModel();
    Imovel selecionado = null;
    
    
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
                propostasPendentes();
                break;
            case 4:
                eventosPorImovel();
                break;
            case 5:
                //relatorioVendas();
                break;
            case 6:
                //valorTotal();
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
        JLabel lblPreco = new JLabel("Digite o preço do imóvel: ");
        JTextField txtPreco = new JTextField(10);
        JLabel lblComissao = new JLabel("Selecione a comissão: ");
        JComboBox cmbComissao = new JComboBox(new Double[]{0.01, 0.02, 0.03, 0.04, 0.05});
        JLabel lblVendedor = new JLabel("Vendedor responsável: ");
        JComboBox cmbVendedores = new JComboBox(vends);
      
        JButton jbUpar = new JButton("Enviar imagem do imagem (Opcional)");
        JButton jbCadastrar = new JButton("Cadastrar");
        JButton jbVoltar = new JButton("Voltar");
        
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        JPanel p1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel p2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel p3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel p4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel p5 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel p6 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel p7 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel p8 = new JPanel(new FlowLayout(FlowLayout.CENTER));
       
        p1.add(lblCodigo);
        p1.add(txtCodigo);
        p2.add(lblTipo);
        p2.add(cmbTipo);
        p3.add(lblDescricao);
        p3.add(txtDescricao);
        p4.add(lblPreco);
        p4.add(txtPreco);
        p5.add(lblComissao);
        p5.add(cmbComissao);
        p6.add(jbUpar);
        p7.add(lblVendedor);
        p7.add(cmbVendedores);
        p8.add(jbCadastrar);
        p8.add(jbVoltar);
        this.add(Box.createVerticalGlue());
        this.add(p1);
        this.add(p2);
        this.add(p3);
        this.add(p4);
        this.add(p5);
        this.add(p6);
        this.add(p7);
        this.add(p8);
        this.add(Box.createVerticalGlue());
        
        
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
                if(txtCodigo.getText().isEmpty() || txtDescricao.getText().isEmpty() || txtPreco.getText().isEmpty() || vendedores.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
                }
                else{
                    Vendedor v = null;
                    if(vendedores.size() > 0) v = vendedores.get(cmbVendedores.getSelectedIndex());
                    String nomeArq = "";
                    if(selectedFile != null) nomeArq = txtCodigo.getText();

                    objCtrPrincipal.getObjCtrImovel().criaImovel(Integer.parseInt(txtCodigo.getText()), (String) cmbTipo.getSelectedItem(), txtDescricao.getText(), 
                            nomeArq, Double.parseDouble(txtPreco.getText()), (Double) cmbComissao.getSelectedItem(), Calendar.getInstance(), v);
                    JOptionPane.showMessageDialog(null, "Imóvel cadastrado");

                    destination = new File("images/"+txtCodigo.getText());
                    try {
                        if(selectedFile != null) objCtrPrincipal.getObjCtrImovel().copiaArquivo(selectedFile, destination);
                    } catch (IOException ex) {}

                    JPanel cards = objLimPrincipal.cards;
                    CardLayout principal = (CardLayout) (cards.getLayout());
                    principal.show(cards, "Tela Principal");
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
    
    //Case 2
    private void catalogoImoveis(){
        JLabel lblTipo = new JLabel("Insira o tipo do imóvel que deseja ver: ");
        JComboBox cmbTipo = new JComboBox(new Object[]{Util.CASA, Util.LOTE, Util.APTO, Util.SALA, Util.RURAL});
        
        JButton jbProcurar = new JButton("Procurar");
        JButton jbVoltar = new JButton("Voltar");
        
        this.add(lblTipo);
        this.add(cmbTipo);
        
        this.add(jbProcurar);
        this.add(jbVoltar);
        
        listaImovel = new JList(lm);
        this.add(listaImovel);
        
        
        listaImovel.addListSelectionListener(this);
        
                
        jbProcurar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String itemSelecao = (String) cmbTipo.getSelectedItem();
                int index = 0;
                int quant = 0;
                lm.clear();
                for(Imovel imov: objCtrPrincipal.getObjCtrImovel().getListaImovel()){
                    if(imov.getEstado().equals(Util.ATIVO) && imov.getTipo().equals(itemSelecao)){
                        String itemJList = new String();
                        itemJList = "Imóvel: " + imov.getCodigo() + "\n Descrição: " + imov.getDescricao();
                        int cod = imov.getCodigo();
                        
                        lm.add(index, itemJList);
                        quant++;
                    }
                }
                if(quant == 0){
                    lm.clear();
                    lm.add(index, "Nenhum imóvel deste tipo foi cadastrado");
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
    private void propostasPendentes() {
        listaImoveisSubmetidos = new JList(lm2);
        
        listaPropostas = new JList(lm3);
        
        this.add(listaImoveisSubmetidos);
        this.add(listaPropostas);

        listaImoveisSubmetidos.addListSelectionListener(this);
        
        int index = 0;
        int quant = 0;
        
        lm2.clear();
        lm3.clear();
        for (Imovel imov : objCtrPrincipal.getObjCtrImovel().getListaImovel()) {
            if (imov.getEstado().equals(Util.ATIVO)){
                String itemJList = new String();
                itemJList = "Imóvel: " + imov.getCodigo() + "\n Descrição: " + imov.getDescricao();
                int cod = imov.getCodigo();

                lm2.add(index, itemJList);
                quant++;
            }
        }
        if (quant == 0) {
            lm2.clear();
            lm2.add(index, "Nenhum imóvel com propostas pendentes neste momento");
        }

    }
    
    //======================================================================================================
    
    //Case 4
    private void eventosPorImovel(){
        JButton BtBuscar = new JButton("Buscar");
        JButton BtVoltar = new JButton("Voltar");
       
        JLabel jlbCodigo = new JLabel("Digite o código do imóvel: ");
        JLabel dataInicial = new JLabel("Selecione a data inicial:");
        JLabel dataFinal = new JLabel("Selecione a data final:");

        JTextField cod = new JTextField(20);
       
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
        this.add(jlbCodigo);
        this.add(cod);
        this.add(dataInicial);
        this.add(datePickerInicial);
        this.add(dataFinal);
        this.add(datePickerFinal);
        this.add(BtBuscar);
        this.add(BtVoltar);
   
        BtBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Calendar inicio = (Calendar) datePickerInicial.getModel().getValue();
                Calendar fim = (Calendar) datePickerFinal.getModel().getValue();

                ArrayList<Imovel> arrayImovel = objCtrPrincipal.getObjCtrImovel().getListaImovel();
                String str = "";
                
                for(Imovel aux: arrayImovel){
                    ArrayList<Visita> arrayVisitas = aux.getListaVisitas();
                    for(Visita vis: arrayVisitas){
                        if(vis.getData().after(inicio) && vis.getData().before(fim)){
                            str += "Visita realizada no dia: " +vis.getData() + ", acompanhada pelo corretor " +vis.getCorretor() + " que acompanhou o comprador " + vis.getComprador();
                            
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Nenhuma visita neste período");
                        }
                        JOptionPane.showMessageDialog(null, str);
                    }
                }
               
               
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
    
    //======================================================================================================
    
    //Case 5:
    private void relatorioVendas(){
        
    }
    
    //======================================================================================================
    
    //Case 6
    private void valorTotal(){
        
    }
    
    //======================================================================================================

    public void valueChanged(ListSelectionEvent e){
        if(e.getSource() == listaImovel){
            if(e.getValueIsAdjusting() == false){
                if(listaImovel.getSelectedIndex() != -1){                
                    String index = (String) listaImovel.getSelectedValue();
                    String x;
                    for(int i = 8; ; i++){
                        if(index.charAt(i) == ' '){
                            x = index.substring(8, i-1); break;
                        }
                    }
                    int codigo = Integer.parseInt(x);
                    
                    ImageIcon icon = null;
                    for(Imovel imov: objCtrPrincipal.getObjCtrImovel().getListaImovel()){
                        if(imov.getCodigo() == codigo){
                            icon = new ImageIcon("images/" + codigo) {};
                            Image image = icon.getImage();
                            Image newimg = image.getScaledInstance(240, 240,  java.awt.Image.SCALE_SMOOTH);
                            icon = new ImageIcon(newimg);
                            selecionado = imov;
                            break;
                        }
                    }
                    JFrame telaImovel = new JFrame("Imovel: " + codigo);
                    telaImovel.setSize(600, 400);
                    telaImovel.setResizable(false);
                    telaImovel.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                    telaImovel.setLocationRelativeTo(null);
                    telaImovel.setVisible(true);
                    telaImovel.setLayout(new GridLayout(1, 2));
                    
                    
                    JPanel imagem = new JPanel(new BorderLayout());
                    imagem.add(new JLabel(icon), BorderLayout.CENTER);
                    JPanel desc = new JPanel();
                    
                    JLabel lblCodigo = new JLabel("Código do imóvel: ");
                    JTextArea txtCodigo = new JTextArea(""+selecionado.getCodigo());
                    JLabel lblDescricao = new JLabel("Descrição do imóvel: ");
                    JTextArea txtDescricao = new JTextArea(selecionado.getDescricao());
                    JLabel lblPreco = new JLabel("Preço do imóvel: ");
                    JTextArea txtPreco = new JTextArea(""+selecionado.getPreco());
                    //JLabel lblEndereco = new JLabel("Endereço do imóvel: ");
                    //JTextArea txtEndereco = new JTextArea(selecionado.getEndereco());
                    
                    JButton jbProposta = new JButton("Fazer proposta");
                    JButton jbVisita = new JButton("Agendar visita");

                    desc.setLayout(new BoxLayout(desc,BoxLayout.Y_AXIS));
                    JPanel p1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
                    JPanel p2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
                    JPanel p3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
                    //JPanel p4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
                    JPanel p5 = new JPanel(new FlowLayout(FlowLayout.CENTER));

                    p1.add(lblCodigo);
                    p1.add(txtCodigo);
                    p2.add(lblDescricao);
                    p2.add(txtDescricao);
                    p3.add(lblPreco);
                    p3.add(txtPreco);
                    //p4.add(lblEndereco);
                    //p4.add(txtEndereco);
                    p5.add(jbProposta);
                    p5.add(jbVisita);
                    desc.add(Box.createVerticalGlue());
                    desc.add(p1);
                    desc.add(p2);
                    desc.add(p3);
                    //this.add(p4);
                    desc.add(p5);
                    desc.add(Box.createVerticalGlue());
                    
                    telaImovel.add(imagem);
                    telaImovel.add(desc);
                    
                    jbProposta.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try{
                                objCtrPrincipal.criarControleProposta(selecionado);
                            } catch (Exception error){}
                        }
                    });
                    jbVisita.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try{
                                objCtrPrincipal.criarControleVisita(selecionado);
                            } catch (Exception error){}
                        }
                    });
                }
            }    
        }
        else if(e.getSource() == listaImoveisSubmetidos){
            if(e.getValueIsAdjusting() == false){
                if(listaImoveisSubmetidos.getSelectedIndex() != -1){
                    String index = (String) listaImovel.getSelectedValue();
                    String x;
                    for(int i = 8; ; i++){
                        if(index.charAt(i) == ' '){
                            x = index.substring(8, i-1);
                            break;
                        }
                    }
                    int codigo = Integer.parseInt(x);
                    
                    for(Imovel imov: objCtrPrincipal.getObjCtrImovel().getListaImovel()){
                        if(imov.getCodigo() == codigo){
                            System.out.println("Entrou aqui");
                            //Cria frame para exibir propostas
                        }
                    }
                    
                                      
                    JFrame framePropostas = new JFrame("" + codigo);
                    framePropostas.setSize(500, 500);
                    framePropostas.setResizable(false);
                    framePropostas.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                    framePropostas.setLocationRelativeTo(null);
                    framePropostas.setVisible(true);
                    framePropostas.setLayout(new GridLayout(1,1));
                    
                    JPanel propostas = new JPanel();
                    
                    
                    /*
                    for(Imovel imov: objCtrPrincipal.getObjCtrImovel().getListaImovel()){
                        ArrayList<Proposta> prop = imov.getListaPropostas();
                        for(Proposta aux: prop){
                            cmbPropostas.add(aux);
                        }
                    }*/
                    
                    
                    
                    
                }
            }
        }
        
    }
}   
    
