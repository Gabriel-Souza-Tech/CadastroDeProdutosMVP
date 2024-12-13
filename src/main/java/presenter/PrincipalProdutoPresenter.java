/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presenter;

import javax.swing.JOptionPane;
import model.IProdutoSubscriber;
import model.ProdutoCollection;
import view.PrincipalProdutoView;


/**
 *
 * @author Gabriel
 */
public class PrincipalProdutoPresenter implements IProdutoSubscriber {
    private final PrincipalProdutoView principalView;
    private final ProdutoCollection produtoCollection;
    
    public PrincipalProdutoPresenter(ProdutoCollection produtoCollection) {
        this.principalView = new PrincipalProdutoView();
        this.produtoCollection = produtoCollection;
        this.produtoCollection.addObserver(this);
        configureView();
        this.principalView.setVisible(true);
    }
    
    private void configureView() {
        principalView.getJmiIncluirProduto().addActionListener(event -> { // Adiciona Action Listener ao item de menu
            try {
                new InclusaoProdutoPresenter(produtoCollection); // Cria um novo presenter para inclusão
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE); // Exibe mensagem de erro
            }
        });
        
        principalView.getJmiListagemProduto().addActionListener(event -> {
            try {
                // new ListagemProdutoPresenter(produtoCollectio);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
    
    @Override
    public void update(Object data) {
      int contador = produtoCollection.getProdutos().size();
      System.out.println(produtoCollection.getProdutos().size());
      principalView.getContador().setText(String.valueOf(contador));
    };
}

    