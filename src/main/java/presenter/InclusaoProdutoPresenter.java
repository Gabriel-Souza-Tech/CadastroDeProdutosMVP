package presenter;

import model.ProdutoCollection;
import model.Produto;
import view.InclusaoProdutoView;
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

    public InclusaoProdutoPresenter(ProdutoCollection produtos) {
        this.produtos = produtos;

        this.view = new InclusaoProdutoView(); 
        this.view.setVisible(true); 

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
        limparCampos();
    }
    
    public void limparCampos() {
        view.getTxtNome().setText("");
        view.getTxtPrecoCusto().setText("");
        view.getTxtPercentualLucro().setText("");
        view.getTxtPrecoVenda().setText("");
    }

    private void cancelar() {
        view.dispose(); // Fecha a janela corretamente
    }
}
