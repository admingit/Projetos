/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.unip.eventos;

import br.com.unip.componentes.ComponenteBotao;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

/**
 * s
 *
 * @author Grupo APS
 */
public class EventoBotao implements ActionListener {

    private ComponenteBotao[][] mapa;
    private ComponenteBotao botao;
    private int linhaMaxima;
    private int colunaMaxima;
    private boolean primeiroClique;

    public EventoBotao() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        botao = ((ComponenteBotao) e.getSource());
        for (int i = 0; i <= linhaMaxima; i++) {
            for (int j = 0; j <= colunaMaxima; j++) {
//                mapa[i][j].setText(mapa[i][j].getValor() + "");
                if (mapa[i][j].isMina()) {
                    mapa[i][j].setBackground(Color.YELLOW);
                }
            }
        }
        if (primeiroClique) {

            primeiroClique = false;
        }
        if (!botao.isClicado()) {
            if (botao.getIcon() != null) {
                botao.setIcon(null);
            }
            if (botao.isMina()) {

                botao.setBackground(Color.red);
                botao.setIcon(new ImageIcon(getClass().getResource("resources/bomb.png")));

                for (int i = 0; i <= linhaMaxima; i++) {
                    for (int j = 0; j <= colunaMaxima; j++) {
                        mapa[i][j].setClicado(true);
                        mapa[i][j].setFocusable(false);
//                        mapa[i][j].setBorderPainted(false);
//                        mapa[i][j].setBorder(null);

                        if (mapa[i][j].isMina()) {

                            mapa[i][j].setIcon(new ImageIcon(getClass().getResource("resources/bomb.png")));
                        }
                    }
                }
            } else if (botao.getValor() > 0) {
                botao.setClicado(true);
                botao.setFocusable(false);
                botao.setOpaque(false);
                botao.setContentAreaFilled(false);
                botao.setText(botao.getValor() + "");
            } else {
                int linhaBotaoAtual = botao.getLinha();
                int colunaBotaoAtual = botao.getColuna();
                /*se não for mina, verifica os que estão em volta
                 * Verificar se a linha- ou coluna - são <= 0
                 * Verificar se a linha+ é maior que linhaMaxima
                 * Verificar se a coluna+ é maior que colunaMaxima
                 * 
                 * 
                 * linha - 1, coluna =  | OK |
                 * linha -1, coluna +1  | OK |
                 * linha = , coluna +1  | OK |
                 * linha +1, coluna +1  | OK |
                 * linha +1, coluna =   | OK |
                 * linha +1, coluna -1  | OK |
                 * linha = , coluna -1  | OK |
                 * linha -1, coluna -1  | OK |
                 * 
                 * 
                 * 
                 */

                // linha acima do botão clicado
                abreAoLado((linhaBotaoAtual - 1), colunaBotaoAtual);
                // A mesma linha do botão clicado
                abreAoLado(linhaBotaoAtual, colunaBotaoAtual);
                // linha abaixo do botão clicado
                abreAoLado((linhaBotaoAtual + 1), colunaBotaoAtual);
            }
        }
    }

    private void abreAoLado(int linhaAtual, int colunaBotaoAtual) {

        if (linhaAtual >= 0 && linhaAtual <= linhaMaxima) {
            int colunaAtual = colunaBotaoAtual;
            //coluna =
            mapa[linhaAtual][colunaAtual].abreBotao();

            //coluna -1
            colunaAtual = colunaBotaoAtual - 1;
            if (colunaAtual >= 0) {
                mapa[linhaAtual][colunaAtual].abreBotao();
            }

            //coluna +1
            colunaAtual = colunaBotaoAtual + 1;
            if (colunaAtual <= colunaMaxima) {
                mapa[linhaAtual][colunaAtual].abreBotao();
            }
        }
    }

    public void setMapa(ComponenteBotao[][] mapa) {
        this.mapa = mapa;
    }

    public void setLinhaMaxima(int linhaMaxima) {
        this.linhaMaxima = linhaMaxima;
    }

    public void setColunaMaxima(int colunaMaxima) {
        this.colunaMaxima = colunaMaxima;
    }
}
