/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.unip.janelas;

import br.com.unip.componentes.ComponenteBotao;
import br.com.unip.eventos.EventoBotao;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

/**
 *
 * @author Hr
 */
public class JanelaJogo extends JPanel {

    private int linha;
    private int coluna;
    private int mina;
    private ComponenteBotao[][] mapa;
    private List<Integer> bombas;
    private ComponenteBotao toAdd;
    private JanelaPrincipal janelaPrincipal;
    private EventoBotao evento;

    public JanelaJogo(String nivel, JanelaPrincipal janelaPrincipal) {
        this.janelaPrincipal = janelaPrincipal;
        verificaNivel(nivel);
        criaBomba();
        criaNivel();
    }

    private void verificaNivel(String nivel) {
        switch (nivel) {
            case "Fácil":
                linha = 10;
                coluna = 10;
                mina = 10;
                janelaPrincipal.setSize(400, 400);
                break;
            case "Médio":
                linha = 17;
                coluna = 17;
                mina = 40;
                janelaPrincipal.setSize(new Dimension(400, 400));
                break;
            case "Difícil":
                linha = 17;
                coluna = 31;
                mina = 99;
                janelaPrincipal.setSize(new Dimension(400, 400));
                break;
            default:
                linha = 10;
                coluna = 10;
                mina = 10;
                janelaPrincipal.setSize(400, 400);
                break;
        }
    }

    private void criaNivel() {
        this.setLayout(new GridLayout(linha, coluna));
        mapa = new ComponenteBotao[linha][coluna];
        evento = new EventoBotao();
        boolean minaSN = false;
        int posicao = 0;
        for (int i = 0; i < linha; i++) {
            for (int j = 0; j < coluna; j++) {

                if (bombas.contains(posicao)) {
                    minaSN = true;
                }

                toAdd = new ComponenteBotao(i, j, posicao, minaSN);
                if (toAdd.isMina()) {
//                    toAdd.setText("b");
                }
                toAdd.setBorder(new LineBorder(Color.getHSBColor(0.010f, 0.010f, 0.5f)));
                toAdd.setPreferredSize(new Dimension(25, 25));
                toAdd.setMargin(new Insets(1, 1, 1, 1));
                toAdd.addActionListener(evento);
                toAdd.setClicado(false);
                toAdd.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        ComponenteBotao botao = ((ComponenteBotao) e.getSource());
                        if (SwingUtilities.isRightMouseButton(e)) {
                            if (!botao.isClicado()) {
                                if (botao.getIcon() == null) {
                                    botao.setIcon(new ImageIcon(getClass().getResource("resources/redFlag.gif")));
                                    mina--;
                                    janelaPrincipal.setMinas("" + mina);
                                } else {
                                    botao.setIcon(null);
                                    mina++;
                                    janelaPrincipal.setMinas("" + mina);
                                }
                            }
                        }
                    }
                });
                this.add(toAdd);
                mapa[i][j] = toAdd;

                minaSN = false;
                posicao++;
            }
            janelaPrincipal.setMinas("" + mina);
        }
        evento.setMapa(mapa);
        evento.setLinhaMaxima(linha - 1);
        evento.setColunaMaxima(coluna - 1);
        verificaAoLado();
    }

    public JPanel getImagem() {
        JPanel painelImagem = new JPanel();
        /*
         * 
         * Adicionar Smiley Campo Minado
         * 
         */
        return painelImagem;
    }

    public void criaBomba() {
        Random randomNumero = new Random();
        bombas = new ArrayList<>();
        for (int i = 0; i < mina; i++) {
            int r = randomNumero.nextInt(linha * coluna);

            if (bombas.contains(r)) {
                i--;

            } else {
                bombas.add(r);
            }
        }
    }

    private void verificaAoLado() {
        for (int i = 0; i < linha; i++) {
            for (int j = 0; j < coluna; j++) {
                int cont = 0;
                // linha acima do botão clicado
                cont = calculaPorLinha((i - 1), j, cont, false);
                // A mesma linha do botão clicado
                cont = calculaPorLinha(i, j, cont, true);
                // linha abaixo do botão clicado
                cont = calculaPorLinha((i + 1), j, cont, false);
                System.out.println("Posição:" + mapa[i][j].getPosicao() + "\t Em volta:"+ cont);
                mapa[i][j].setValor(cont);
            }
        }
    }

    private int calculaPorLinha(int linhaAtual, int colunaBotaoAtual, int cont, boolean mesmoBotao) {

        if (linhaAtual >= 0 && linhaAtual < linha) {
            int colunaAtual = colunaBotaoAtual;
            //coluna =
            if (mapa[linhaAtual][colunaAtual].isMina() && !mesmoBotao) {
                cont++;
            }

            //coluna -1
            colunaAtual = colunaBotaoAtual - 1;
            if (colunaAtual >= 0) {
                if (mapa[linhaAtual][colunaAtual].isMina()) {
                    cont++;
                }
            }

            //coluna +1
            colunaAtual = colunaBotaoAtual + 1;
            if (colunaAtual < coluna) {
                if (mapa[linhaAtual][colunaAtual].isMina()) {
                    cont++;
                }
            }
        }
        return cont;
    }
}
