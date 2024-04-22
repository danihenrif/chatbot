# Chatbot com Socket e RMI em Java

Este é um projeto Java que demonstra a implementação de um chatbot usando socket e RMI utilizando a biblioteca AIML.

## Pré-requisitos

Antes de executar o projeto, certifique-se de ter as seguintes ferramentas instaladas:

- Java Development Kit (JDK)
- Apache Maven

## Como Executar

1. **Clonar o Repositório:**

   Você pode clonar o repositório usando o seguinte comando:

   ```bash
   git clone https://github.com/danihenrif/chatbot.git
   ```
2. Execute primeiro o lado do servidor

    1. Execute a classe <br>
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

    3. Com socket baseado em middleware: <br>
    1.1. Execute a classe:<br> 

        ```bash
        ServerSocketChatbot
        ```
        
        localizada dentro do servidor na pasta socket. <br><br>
        1.2. Execute a classe: <br>

        ```bash
        NameServiceSocket
        &
        Main
        ```
        
        localizada dentro do lado cliente nas pastas name_service e chatbot. <br>

    4. Faça perguntas ao chatbot :) <br> Ex de interação: <br>
        ```bash
        Você: BEST SOCCER TEAM
        Chatbot: AH essa é fácil, Vasco da Gama
        ```