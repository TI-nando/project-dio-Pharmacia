package com.farmacia;

import com.farmacia.behavioral.chain.ValidadorEstoqueHandler;
import com.farmacia.behavioral.chain.ValidadorIdadeHandler;
import com.farmacia.behavioral.chain.ValidadorPedido;
import com.farmacia.behavioral.chain.ValidadorReceitaHandler;
import com.farmacia.behavioral.strategy.DescontoStrategy;
import com.farmacia.behavioral.strategy.SeletorDescontoStrategy;
import com.farmacia.creational.singleton.Estoque;
import com.farmacia.model.ItemPedido;
import com.farmacia.model.Pedido;

/**
 * Orquestra o fluxo completo de processamento de um pedido:
 * 1) valida (Chain of Responsibility)
 * 2) calcula desconto (Strategy)
 * 3) baixa estoque (Singleton, que dispara o Observer se necessário)
 */
public class ProcessadorPedido {

    private final ValidadorPedido cadeiaDeValidacao;
    private final SeletorDescontoStrategy seletorDesconto = new SeletorDescontoStrategy();

    public ProcessadorPedido() {
        ValidadorPedido validadorEstoque = new ValidadorEstoqueHandler();
        ValidadorPedido validadorReceita = new ValidadorReceitaHandler();
        ValidadorPedido validadorIdade = new ValidadorIdadeHandler();

        validadorEstoque.encadear(validadorReceita).encadear(validadorIdade);
        this.cadeiaDeValidacao = validadorEstoque;
    }

    public void processar(Pedido pedido) {
        cadeiaDeValidacao.validar(pedido);

        DescontoStrategy estrategia = seletorDesconto.selecionar(pedido.getCliente());
        double desconto = estrategia.calcular(pedido.getValorBruto());
        pedido.setDescontoAplicado(desconto);

        Estoque estoque = Estoque.getInstance();
        for (ItemPedido item : pedido.getItens()) {
            estoque.baixarEstoque(item.getMedicamento(), item.getQuantidade());
        }

        System.out.println("Pedido aprovado para " + pedido.getCliente().getNome()
                + " | Estratégia aplicada: " + estrategia.getDescricao());
    }
}
