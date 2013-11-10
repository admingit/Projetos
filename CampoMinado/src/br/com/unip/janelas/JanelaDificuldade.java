/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.unip.janelas;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 *
 * @author Grupo Aps
 */
public class JanelaDificuldade extends JDialog {

    private Checkbox facil;
    private Checkbox medio;
    private Checkbox dificil;
    private CheckboxGroup agrupar;
    private Container container;
    private JButton botaoOk;
    private Panel panelBotao;
    private Panel panelCheckBox;
    private JanelaPrincipal janelaPrincipal;
    private String txtFacil;
    private String txtMedio;
    private String txtDificil;

    JanelaDificuldade(JanelaPrincipal janelaPrincipal) {
        super(janelaPrincipal, "Dificuldade", true);
        this.janelaPrincipal = janelaPrincipal;
        txtFacil = "Fácil";
        txtMedio = "Médio";
        txtDificil = "Difícil";
        opcoesJogo();
        criaActionListeners();
        configuracoes();

    }

    private void configuracoes() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(300, 100);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void opcoesJogo() {

//        Jogo inicia = new Jogo();
        container = getContentPane();
        container.setLayout(new GridLayout(2, 1));
        panelCheckBox = new Panel(new GridLayout(1, 3));
        panelBotao = new Panel(new FlowLayout());

        facil = new Checkbox(txtFacil);
        medio = new Checkbox(txtMedio);
        dificil = new Checkbox(txtDificil);
        botaoOk = new JButton("Confirmar");
        agrupar = new CheckboxGroup();

        facil.setCheckboxGroup(agrupar);
        medio.setCheckboxGroup(agrupar);
        dificil.setCheckboxGroup(agrupar);

        if (janelaPrincipal.getNivel().isEmpty()
                || janelaPrincipal.getNivel().equals(txtFacil)) {
            facil.setState(true);
        } else if (janelaPrincipal.getNivel()
                .equals(txtMedio)) {
            medio.setState(true);
        } else if (janelaPrincipal.getNivel()
                .equals(txtDificil)) {
            dificil.setState(true);
        }
//
//        inicia.setNivel(inicia.getNivel());
//
//        if (inicia.getNivel() == null) {
//            inicia.setNivel("Fácil");
//        }
//
//        if (inicia.getNivel().equals("Fácil")) {
//            facil.setState(true);
//        } else if (inicia.getNivel().equals("Médio")) {
//            medio.setState(true);
//        } else {
//            dificil.setState(true);
//        }

        panelCheckBox.add(facil);

        panelCheckBox.add(medio);

        panelCheckBox.add(dificil);

        panelBotao.add(botaoOk);

        container.add(panelCheckBox);

        container.add(panelBotao);
    }

    private void criaActionListeners() {
        botaoOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nivel = getStatusAtivo();
                janelaPrincipal.setNivel(nivel);
                janelaPrincipal.carregaJogo(nivel);
                fechaDialog();
            }
        });
    }

    private String getStatusAtivo() {
        String retorna;
        retorna = this.agrupar.getSelectedCheckbox().getLabel();
        return retorna;
    }

    private void fechaDialog() {
        this.dispose();
    }
}
