/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presenter;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import model.IProdutoSubscriber;
import model.Produto;
import model.ProdutoCollection;
import view.ListagemProdutoView;

/**
 *
 * @author Gabriel
 */
public class ListagemProdutoPresenter implements IProdutoSubscriber {
    private final ListagemProdutoView listagemView;
    private final ProdutoCollection produtos;
    
    public ListagemProdutoPresenter(ProdutoCollection produtos) {
        this.listagemView = new ListagemProdutoView();
        this.produtos = produtos;
        
        this.produtos.addObserver(this);
        
        configureView();
        this.listagemView.setVisible(true);
    }
    
    public void configureView() {
        DefaultTableModel tableModel = new DefaultTableModel(
            new Object[][]{}, 
            new String[]{"Nome", "Lucro(%)", "Preço Custo", "Preço Venda"} 
        );

        listarProdutos(tableModel);

        listagemView.getTabelaProdutos().setModel(tableModel);
        
        listagemView.getTabelaProdutos().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
            }
        });

        listagemView.getBtnExcluir().addActionListener(e -> {
            int selectedRow = listagemView.getTabelaProdutos().getSelectedRow();
            if (selectedRow != -1) {
                excluirProduto(selectedRow);
            } else {
                JOptionPane.showMessageDialog(listagemView, "Selecione um produto para excluir.");
            }
        });
    }
    
    public void listarProdutos(DefaultTableModel tableModel) {
        // Limpar dados existentes
        tableModel.setRowCount(0);

        for (var produto : produtos.getProdutos()) {
            tableModel.addRow(new Object[]{
                produto.getNome(),
                produto.getPercentualLucro(),
                produto.getPrecoCusto(),
                produto.getPrecoVenda()
            });
        }
    }
    
    private void excluirProduto(int selectedRow) {
        Produto produto = produtos.getProdutos().get(selectedRow);
        int response = JOptionPane.showConfirmDialog(
            listagemView, 
            "Tem certeza que deseja excluir o produto " + produto.getNome() + "?",
            "Confirmar Exclusão", 
            JOptionPane.YES_NO_OPTION
        );

        if (response == JOptionPane.YES_OPTION) {
            produtos.excluir(produto);
            JOptionPane.showMessageDialog(listagemView, "Produto excluído com sucesso!");
        }
    }

    @Override
    public void update(Object data) {
        DefaultTableModel tableModel = (DefaultTableModel) listagemView.getTabelaProdutos().getModel();
        listarProdutos(tableModel);
    }
}
