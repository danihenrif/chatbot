# Projeto Java

Este é um projeto Java que demonstra a implementação de um chatbot usando socket e rmi utilizando a biblioteca aiml.

## Pré-requisitos

Antes de executar o projeto, certifique-se de ter as seguintes ferramentas instaladas:

- Java Development Kit (JDK)
- Apache Maven

## Como executar

1. **[Clonar o repositório:](git clone https://github.com/danihenrif/chatbot/tree/main)**

2. Execute primeiro o lado do servidor

   1. Com socket: <br>
      1.1. Execute a classe:<br> 

      ```bash
      ServerSocketChatBot
      ```
      localizada dentro do servidor na pasta socket. <br><br>
      1.2. Execute a classe: <br>
      ```bash
      Main
      ```
      localizada dentro do lado cliente na pasta socket. <br>
   1. Com RMI: <br>
      1.1. Execute a classe:<br> 

      ```bash
      ServerRmiChatbot
      ```
      localizada dentro do servidor na pasta rmi. <br><br>
      1.2. Execute a classe: <br>
      ```bash
      ClientRmi
      ```
      localizada dentro do lado cliente na pasta rmi. <br>
      

3. Faça perguntas ao chatbot :) <br> Ex de interação: <br>
      ```bash
      Você: BEST SOCCER TEAM
      Chatbot: AH essa é fácil, Vasco da Gama