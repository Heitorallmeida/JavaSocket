# Projeto Redes de Computadores - Implementação Sockets
## Utilizando Sockets com java em aplicação de *Loja de construição*

![image](https://user-images.githubusercontent.com/38872475/134444781-61b0a0bb-0f49-4f9f-9500-8fd991b1e606.png)

## Conteúdos
   * [Resumo](#nome-do-tópico)
   * [Inicialização](#nome-do-tópico)
     * [Pré requisitos](#nome-do-tópico)
   * [Execução](#nome-do-tópico)

## Resumo
O projeto trata-se de um sistema de uma loja de construção, a IC Ink. O intuito de tal sistema é possibilitar que clientes possam interagir com a empresa a distância, realizando seus pedidos e conferindo orçamentos sem necessariamente terem que ir até a loja. Os clientes podem ver os materiais disponíveis, adicioná-los à seus respectivos carrinhos e verem o status do pedido quando quiserem, até que julguem completa a compra,  posteriormente, então, estes podem encerrá-la. Qualquer cliente pode acessar o sistema enquanto o mesmo está sendo usado por outros clientes, tendo seu estoque atualizado em tempo real, assim como o carrinho de compras é particular para cada um.

## Inicialização
Itens necessários à execução do projeto.

### Pré requisitos
A execução pode ser feita em uma IDE ou diretamente no terminal, basta conter em sua máquina os seguintes pontos:
   * Compilador java
     * [Windows](https://www.oracle.com/java/technologies/downloads/#java16)
     * [Mac](https://www.oracle.com/java/technologies/downloads/#java16)
     * Linux: 
     ```
     sudo apt install default-jdk
     ```
   * IDE
     * IntelliJ  
 
## Execução
 * Terminal
     * Servidor 
      ```
      java Servidor.jar -jar
      ``` 
      Insira a porta de conexão que deseja ultilizar.
     * Cliente
     ```
     java Servidor.jar -jar
     ``` 
     Insira o IP do servidor que deseja conectar-se, logo após será solicitada a porta a qual deseja conectar, insira a mesma do servidor.
     
 * IDE
     * Execute a classe "Servidor.java" e insira a porta de conexão que deseja ultilizar.
     * Execute a classe "Cliente.java" e insira o IP do servidor que deseja conectar-se, logo após será solicitada a porta a qual deseja conectar, insira a mesma do servidor.
 
 

