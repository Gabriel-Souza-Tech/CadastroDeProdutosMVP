/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Gabriel
 */
public class ProdutoCollection extends GerenciadorProdutoPublisher {
    private List<Produto> produtos;
    
    public ProdutoCollection() {
        produtos = new ArrayList<>();
    }
    
    public void incluir(Produto produto) {
        if(produto == null) {
            throw new IllegalArgumentException("Informe um produto valido!");
        } 
        produtos.add(produto);
        
        notifyObservers(produto);
    }
    
    public List<Produto> getProdutos() {
        return produtos;
    }
    
    public Optional<Produto> findProdutoByNome(String nome) {
        for (Produto produto : produtos) {
            if (produto.getNome().equalsIgnoreCase(nome)) {
                return Optional.of(produto);
            }
        }
        return Optional.empty();
    }

}
