/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.unip.janelas;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 *
 * @author Grupo Aps
 */
public class JanelaPrincipal extends JFrame {

    private JMenu jogo;
    private JMenu ajuda;
    private JMenuBar menu;
    private JMenuItem sobre;
    private JMenuItem novoJogo;
    private JMenuItem dificuldade;
    private JMenuItem sair;
    private Container container;
    private JanelaJogo carregaJogo;
    private String nivel;
    private JPanel contadores;
    private JLabel txtMinas;
    private JLabel minas;
    
    

    public JanelaPrincipal() {

        criaTemplate();
        criaActionListeners();

        // Jogo é iniciado no nível fácil

        configuracoes();
        carregaJogo(nivel);


        /*
         * 
         * Criar Container para armazenar o Jogo
         * e depois arrumar o cria template.
         * 
         */
    }

    private void configuracoes() {
        nivel = "";
        
        container = getContentPane();
        container.setEnabled(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Campo Minado");
        this.setJMenuBar(menu);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }

    protected void carregaJogo(String nivel) {
        container.setVisible(false);
        container.removeAll();
        contadores.setVisible(true);
        contadores.add(txtMinas);
        contadores.add(minas);
        container.add(contadores, BorderLayout.NORTH);
        
        
        
        carregaJogo = new JanelaJogo(nivel, this);
        container.add(carregaJogo, BorderLayout.CENTER);
//        container.add(carregaJogo.getImagem());
        this.pack();
        container.setVisible(true);
        
    }

    private void janelaDificuldade() {
        /*
         * Não é necessário mas atribuí
         * o novo valor à uma variável
         */
        JanelaDificuldade janelaDificuldade = new JanelaDificuldade(this);
    }

    private void janelaSobre() {
        /*
         * Não é necessário mas atribuí
         * o novo valor à uma variável
         */

        JanelaSobre janelaSobre = new JanelaSobre(this);
    }

    private void criaTemplate() {
        menu = new JMenuBar();
        jogo = new JMenu("Jogo");
        ajuda = new JMenu("Ajuda");
        novoJogo = new JMenuItem("Novo Jogo");
        dificuldade = new JMenuItem("Dificuldade");
        sair = new JMenuItem("Sair");
        sobre = new JMenuItem("Sobre...");
        contadores = new JPanel();
        minas = new JLabel();
        txtMinas = new JLabel("Minas: ");
        menu.add(jogo);
        menu.add(ajuda);
        ajuda.add(sobre);
        jogo.add(novoJogo);
        jogo.add(dificuldade);
        jogo.add(sair);
    }

    public void setMinas(String bandeiras) {
        this.minas.setText(bandeiras);
    }

    private void criaActionListeners() {
        novoJogo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((JMenuItem) e.getSource()).updateUI();
                novoJogo();
            }
        });

        sair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });

        dificuldade.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                janelaDificuldade();
            }
        });
        sobre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                janelaSobre();
            }
        });
    }

    private void novoJogo() {
        container.removeAll();
        container = getContentPane();
        contadores.setVisible(true);
        contadores.add(txtMinas);
        contadores.add(minas);
        container.add(contadores, BorderLayout.NORTH);
        carregaJogo = new JanelaJogo(nivel, this);
        container.add(carregaJogo, BorderLayout.CENTER);
        container.setVisible(true);
        this.pack();
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }
}
