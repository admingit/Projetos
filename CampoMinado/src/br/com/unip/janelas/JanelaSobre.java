/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.unip.janelas;

import java.awt.GridLayout;
import java.awt.Label;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 *
 * @author Hr
 */
public class JanelaSobre extends JDialog {
    
    private Label criadoPor;
    private Label ano;
    private Label universidade;
    
    
    JanelaSobre(JanelaPrincipal janelaPrincipal) {
        super(janelaPrincipal, "Sobre", true);

        criadoPor = new Label ("Henrique Rocha(MC NARIGA) e Leandro Piroupo(MC PIROCA)");
        universidade = new Label ("Universidade Paulista");
        ano = new Label ("2013");
        GridLayout janela = new GridLayout(3,1);
        this.setLayout(janela);
        this.add(criadoPor);
        this.add(universidade);
        this.add(ano);
        
        
        configuracoes();
        
    }

    private void configuracoes() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
