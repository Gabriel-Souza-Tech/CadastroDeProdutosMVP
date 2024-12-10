package presenter;

import model.ProdutoCollection;
import model.Produto;
import view.InclusaoProdutoView;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Gabriel
 */
public class InclusaoProdutoPresenter {
    private Produto produto;
    private final InclusaoProdutoView view;
    private final ProdutoCollection produtos;
    private final JFrame frame; // Janela que conterá o JPanel

    public InclusaoProdutoPresenter(ProdutoCollection produtos) {
        this.produtos = produtos;

        // Criando o frame para conter o JPanel
        this.frame = new JFrame("Inclusão de Produto");
        this.view = new InclusaoProdutoView();

        // Configurações do JFrame
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setContentPane(view); // Adiciona o JPanel ao JFrame
        frame.pack(); // Ajusta o tamanho da janela ao conteúdo
        frame.setLocationRelativeTo(null); // Centraliza a janela na tela
        frame.setVisible(true); // Torna a janela visível

        configuraView();
    }

    private void configuraView() {
        view.getBtnIncluir().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Botão Incluir clicado!");
                try {
                    salvar();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });

        view.getBtnCancelar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelar();
            }
        });
    }

    private void salvar() {
        String nome = view.getTxtNome().getText();
        System.out.println("Nome: " + nome);  // Verifica se o nome foi obtido
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("Nome do produto é obrigatório");
        }
        double precoCusto = Double.parseDouble(view.getTxtPrecoCusto().getText());
        System.out.println("Preço de Custo: " + precoCusto);  // Verifica o valor do preço de custo
        if (precoCusto <= 0) {
            throw new IllegalArgumentException("Preço de custo deve ser maior que zero");
        }
        double percentualLucro = Double.parseDouble(view.getTxtPercentualLucro().getText());
        System.out.println("Percentual de Lucro: " + percentualLucro);  // Verifica o valor do percentual de lucro
        if (percentualLucro <= 0) {
            throw new IllegalArgumentException("Percentual de lucro deve ser maior que zero");
        }
        produto = new Produto(nome, precoCusto, percentualLucro);
        produtos.incluir(produto);

        view.getTxtPrecoVenda().setText(String.format("%.2f", produto.getPrecoVenda()));

        System.out.println(produto);

        JOptionPane.showMessageDialog(view, "Produto incluído com sucesso!");
    }

    private void cancelar() {
        frame.dispose(); // Fecha a janela corretamente
    }
}
