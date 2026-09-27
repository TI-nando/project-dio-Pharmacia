# 💊 Pharma Design Patterns

Sistema simples de **pedidos de uma farmácia**, em Java puro, criado para
consolidar os principais Padrões de Projeto (Design Patterns) estudados no
bootcamp da DIO. Não usa nenhum framework — só Java (JDK 17+).

## O cenário

Uma farmácia processa pedidos de clientes. Cada pedido precisa:

- ter estoque suficiente dos medicamentos escolhidos;
- apresentar receita médica, se algum item for controlado;
- respeitar a idade mínima para medicamentos controlados;
- receber o desconto correto conforme o perfil do cliente (convênio, idoso
  ou programa de fidelidade);
- disparar um alerta automático quando o estoque de algum item ficar baixo.

## Padrões aplicados e onde encontrá-los

| Padrão | Categoria | Onde está | Por que foi usado |
|---|---|---|---|
| **Factory Method** | Criacional | `creational/factory/MedicamentoFactory.java` | Centraliza a regra "medicamento controlado exige receita" na criação do objeto. |
| **Builder** | Criacional | `creational/builder/PedidoBuilder.java` | Monta um `Pedido` passo a passo (cliente → itens → receita) com API fluente. |
| **Singleton** | Criacional | `creational/singleton/Estoque.java` | Garante um único ponto de controle do estoque em todo o sistema. |
| **Chain of Responsibility** | Comportamental | `behavioral/chain/*` | Cada regra de validação (estoque, receita, idade) é um elo independente da corrente. |
| **Strategy** | Comportamental | `behavioral/strategy/*` | Regras de desconto (convênio, idoso, fidelidade) são intercambiáveis em tempo de execução. |
| **Observer** | Comportamental | `behavioral/observer/*` | O `Estoque` (Subject) avisa automaticamente o farmacêutico e o setor de compras quando algum item fica com estoque baixo. |

## Estrutura

```
src/main/java/com/farmacia/
├── Main.java                     # demonstração ponta a ponta
├── ProcessadorPedido.java        # orquestra Chain + Strategy + Singleton
├── model/                        # entidades de domínio
├── creational/
│   ├── factory/                  # Factory Method
│   ├── builder/                  # Builder
│   └── singleton/                # Singleton
└── behavioral/
    ├── chain/                    # Chain of Responsibility
    ├── strategy/                 # Strategy
    └── observer/                 # Observer
```

## Como rodar

**Sem Maven** (só precisa do JDK instalado):
```bash
chmod +x run.sh
./run.sh
```

**Com Maven:**
```bash
mvn compile exec:java -Dexec.mainClass=com.farmacia.Main
# ou, para gerar o jar:
mvn package
java -jar target/pharma-design-patterns.jar
```

## Saída esperada (resumo)

O `Main` simula três pedidos:

1. Cliente com convênio comprando itens sem restrição → aprovado com 20% de desconto.
2. Cliente idoso comprando um controlado com receita apresentada → aprovado com
   15% de desconto e **dispara o alerta de estoque baixo** (Observer).
3. Cliente jovem tentando comprar um controlado sem receita → **pedido rejeitado**
   pela cadeia de validação (Chain of Responsibility).

## Ideias para evoluir

- Trocar o `Estoque` em memória por persistência real (arquivo, banco H2, etc.).
- Adicionar um padrão **Decorator** para embalagens especiais (ex.: "embalagem discreta").
- Expor tudo como API REST com Spring Boot, reaproveitando as mesmas classes de domínio.
