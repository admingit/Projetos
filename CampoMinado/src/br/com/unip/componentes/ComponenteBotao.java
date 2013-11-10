/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.unip.componentes;

import javax.swing.JButton;

/**
 *
 * @author Grupo APS
 */
public class ComponenteBotao extends JButton {

    private boolean mina;
    private int posicao;
    private int linha;
    private int coluna;
    private boolean clicado;
    private boolean passado = false;
    private int valor;

    public ComponenteBotao(int linha, int coluna, int posicao, boolean mina) {
        this.linha = linha;
        this.coluna = coluna;
        this.posicao = posicao;
        this.mina = mina;
    }

    public boolean isMina() {
        return mina;
    }

    public int getPosicao() {
        return posicao;
    }

    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }

    public boolean isClicado() {
        return clicado;
    }

    public void setClicado(boolean clicado) {
        this.clicado = clicado;
    }

    public boolean isPassado() {
        return passado;
    }

    public void setPassado(boolean varrido) {
        this.passado = varrido;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public void abreBotao() {
        setClicado(true);
        setFocusable(false);
        setOpaque(false);
        setContentAreaFilled(false);
        if (this.valor > 0) {
            setText(this.valor + "");
        }
    }
}
