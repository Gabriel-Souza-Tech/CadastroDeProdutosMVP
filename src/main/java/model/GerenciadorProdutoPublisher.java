/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gabriel
 */
public abstract class GerenciadorProdutoPublisher {
    private final List<IProdutoSubscriber> subscribers; // Lista de observadores

    // Construtor para inicializar a lista de observadores
    public GerenciadorProdutoPublisher() {
        this.subscribers = new ArrayList<>();
    }

    // Adiciona um observador
    public void addObserver(IProdutoSubscriber observador) {
        if (!subscribers.contains(observador)) {
            subscribers.add(observador);
        }
    }

    // Remove um observador
    public void removeObserver(IProdutoSubscriber observador) {
        subscribers.remove(observador);
    }

    // Notifica todos os observadores sobre uma alteração
    public void notifyObservers(Object data) {
        for (IProdutoSubscriber observador : subscribers) {
            observador.update(data); // Chama o método de atualização no observador
        }
    }
}
