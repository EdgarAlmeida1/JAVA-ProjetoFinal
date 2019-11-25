package Limite;

import Controle.*;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import javax.swing.*;

public class LimitePrincipal extends JFrame {

    private ControlePrincipal objCtrPrincipal;
    public JPanel cards;

    public LimitePrincipal(ControlePrincipal objCtrPrin) throws ParseException, Exception {
        // Criação da JFrame
        super("IMOBILIARIA ItaHouse");
        this.setSize(900, 400);
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setLayout(new GridLayout(2, 4, 2, 2));
        objCtrPrincipal = objCtrPrin;

        // Listener para fechar e serializar os dados
        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
                objCtrPrincipal.finalize();
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent e) {
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });

        // Criação do Menu ***************************************************************************************************
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
        //  ***************************************************************************************************

        // Cards de ações
        cards = new JPanel(new CardLayout());
        this.getContentPane().add(cards, BorderLayout.CENTER);

        JPanel inicial = new JPanel(new BorderLayout());
        cards.add(inicial, "Tela Principal");

        // Cadastrar comprador
        LimiteComprador cadastrarComprador = new LimiteComprador(objCtrPrincipal, this, 1);
        cadastrarComprador.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        cards.add(cadastrarComprador, "Cadastrar Comprador");

        // Cadastrar corretor 
        LimiteCorretor cadastrarCorretor = new LimiteCorretor(objCtrPrincipal, this, 1);
        cadastrarCorretor.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        cards.add(cadastrarCorretor, "Cadastrar Corretor");

        // Calcular valor total faturado
        LimiteCorretor totalcorretor = new LimiteCorretor(objCtrPrincipal, this, 2);
        totalcorretor.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        cards.add(totalcorretor, "Valor total faturado por corretor");

        // Exibir imoveis visitados por corretor
        LimiteCorretor visitCorretor = new LimiteCorretor(objCtrPrincipal, this, 3);
        visitCorretor.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        cards.add(visitCorretor, "Visitas por corretor");

        // Ações dos menus
        valorTotalCorretor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout valorTotalCorretor = (CardLayout) (cards.getLayout());
                valorTotalCorretor.show(cards, "Valor total faturado por corretor");
            }
        });

        visitasCorretor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout visitasCorretor = (CardLayout) (cards.getLayout());
                visitasCorretor.show(cards, "Visitas por corretor");
            }
        });

        cadComprador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cardCadComprador = (CardLayout) (cards.getLayout());
                cardCadComprador.show(cards, "Cadastrar Comprador");
            }
        });
        cadCorretor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cardCadComprador = (CardLayout) (cards.getLayout());
                cardCadComprador.show(cards, "Cadastrar Corretor");
            }
        });
    }

}
