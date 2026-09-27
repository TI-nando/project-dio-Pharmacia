package com.farmacia;

import com.farmacia.behavioral.observer.NotificadorCompras;
import com.farmacia.behavioral.observer.NotificadorFarmaceutico;
import com.farmacia.creational.builder.PedidoBuilder;
import com.farmacia.creational.factory.MedicamentoFactory;
import com.farmacia.creational.singleton.Estoque;
import com.farmacia.model.Cliente;
import com.farmacia.model.Medicamento;
import com.farmacia.model.Pedido;
import com.farmacia.model.PedidoInvalidoException;
import com.farmacia.model.TipoMedicamento;

public class Main {

    public static void main(String[] args) {

        // ---- Singleton + Observer: preparar o estoque e seus observadores ----
        Estoque estoque = Estoque.getInstance();
        estoque.registrarObserver(new NotificadorFarmaceutico());
        estoque.registrarObserver(new NotificadorCompras());

        // ---- Factory Method: criar os medicamentos ----
        Medicamento dipirona = MedicamentoFactory.criar(
                "Dipirona 500mg", "Dipirona Sódica", 9.90, TipoMedicamento.GENERICO);
        Medicamento losartana = MedicamentoFactory.criar(
                "Losartana 50mg", "Losartana Potássica", 24.50, TipoMedicamento.SIMILAR);
        Medicamento rivotril = MedicamentoFactory.criar(
                "Rivotril 2mg", "Clonazepam", 32.00, TipoMedicamento.CONTROLADO);

        estoque.repor(dipirona, 20);
        estoque.repor(losartana, 8);
        estoque.repor(rivotril, 6);

        ProcessadorPedido processador = new ProcessadorPedido();

        // ---- Builder: montar o pedido de um cliente com convênio ----
        Cliente maria = new Cliente("Maria Souza", 45, true, false);
        Pedido pedidoMaria = new PedidoBuilder()
                .paraCliente(maria)
                .adicionarItem(dipirona, 2)
                .adicionarItem(losartana, 3)
                .build();

        executar(processador, pedidoMaria);

        // ---- Cliente idoso comprando remédio controlado com receita ----
        Cliente joao = new Cliente("João Pereira", 67, false, false);
        Pedido pedidoJoao = new PedidoBuilder()
                .paraCliente(joao)
                .adicionarItem(rivotril, 4) // vai deixar o estoque abaixo do limite -> dispara Observer
                .comReceitaApresentada(true)
                .build();

        executar(processador, pedidoJoao);

        // ---- Cliente jovem tentando comprar controlado sem receita (deve falhar) ----
        Cliente pedro = new Cliente("Pedro Lima", 22, false, true);
        Pedido pedidoPedro = new PedidoBuilder()
                .paraCliente(pedro)
                .adicionarItem(rivotril, 1)
                .comReceitaApresentada(false)
                .build();

        executar(processador, pedidoPedro);
    }

    private static void executar(ProcessadorPedido processador, Pedido pedido) {
        System.out.println("\n--- Novo pedido: " + pedido.getCliente().getNome() + " ---");
        try {
            processador.processar(pedido);
            System.out.printf("Valor bruto: R$ %.2f | Desconto: R$ %.2f | Total: R$ %.2f%n",
                    pedido.getValorBruto(), pedido.getDescontoAplicado(), pedido.getValorFinal());
        } catch (PedidoInvalidoException e) {
            System.out.println("Pedido rejeitado: " + e.getMessage());
        }
    }
}
