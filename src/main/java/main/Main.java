/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import model.ProdutoCollection;
import presenter.InclusaoProdutoPresenter;
import presenter.PrincipalProdutoPresenter;


/**
 *
 * @author Gabriel
 */
public class Main {
    public static void main(String[] args) {
        ProdutoCollection produtos = new ProdutoCollection();
        //new InclusaoProdutoPresenter(produtos);
        new PrincipalProdutoPresenter(produtos);
    }
}
