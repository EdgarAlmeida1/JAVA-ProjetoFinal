package Limite;

import Controle.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LimitePrincipal extends JFrame{
// Limite das outras classes

    private LimiteComprador objLimComprador;
    private LimiteCorretor objLimCorretor;
    private LimiteImovel objLimImovel;
    private LimiteProposta objLimProposta;
    private LimiteVendedor objLimVendedor;
    private LimiteVisita objLimVisita;

    public LimitePrincipal() {
        // Criação da JFrame
        super("IMOBILIARIA ItaHouse");
        this.setSize(720, 480);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        
        // Criação do Menu
        JMenuBar menu = new JMenuBar();
        this.setJMenuBar(menu);
        
        JMenu compradorMenu = new JMenu("Comprador");
        menu.add(compradorMenu);
        JMenuItem cadComprador = new JMenuItem("Cadastrar");
        compradorMenu.add(cadComprador);
        
        JMenu corretorMenu = new JMenu("Corretor");
        menu.add(corretorMenu);
        JMenuItem cadCorretor = new JMenuItem("Cadastrar");
        JMenuItem valorTotalCorretor = new JMenuItem("Valor total faturado por corretor");
        JMenuItem visitasCorretor = new JMenuItem("Visitas por corretor");
        corretorMenu.add(cadCorretor);
        corretorMenu.add(valorTotalCorretor);
        corretorMenu.add(visitasCorretor);
        
        JMenu vendedorMenu = new JMenu("Vendedor");
        menu.add(vendedorMenu);
        JMenuItem cadVendedor = new JMenuItem("Cadastrar");
        JMenuItem imoveisVendedor = new JMenuItem("Imoveis por vendedor");
        vendedorMenu.add(cadVendedor);
        vendedorMenu.add(imoveisVendedor);
        
        JMenu imovelMenu = new JMenu("Imovel");
        menu.add(imovelMenu);
        JMenuItem cadImovel = new JMenuItem("Cadastrar");
        JMenuItem catImovel = new JMenuItem("Catálogo");
        JMenuItem propImovel = new JMenuItem("Propostas pendentes");
        JMenuItem eventosImovel = new JMenuItem("Eventos por imóvel");
        imovelMenu.add(cadImovel);
        imovelMenu.add(catImovel);
        imovelMenu.add(propImovel);
        imovelMenu.add(eventosImovel);
        
        JMenu relatorioMenu = new JMenu("Relatorios");
        menu.add(relatorioMenu);
        JMenuItem valorTotal = new JMenuItem("Valor total faturado");
        JMenuItem relatorioVendas = new JMenuItem("Vendas");
        relatorioMenu.add(valorTotal);
        relatorioMenu.add(relatorioVendas);
        
        // Cards de ações
        JPanel cards = new JPanel(new CardLayout());
        this.getContentPane().add(cards, BorderLayout.CENTER);

        JPanel inicial = new JPanel(new BorderLayout());
        cards.add(inicial, "Tela Principal");
        
        LimiteComprador cadastrarComprador = new LimiteComprador(1);
        cadastrarComprador.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        cards.add(cadastrarComprador, "Cadastrar Comprador");
        
        
        
        
        // Ações dos menus
        cadComprador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cardCadComprador = (CardLayout) (cards.getLayout());
                cardCadComprador.show(cards, "Cadastrar Comprador");
            }
        });
        
    }
    
}
