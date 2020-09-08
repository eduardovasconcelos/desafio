# desafio
Desafio Framework

Requisitos para execução:

1) JDK 8 ou superior.

2) Banco de Dados: Postgres Configurado

    2.1) O banco (desafio) deve ser criado (create database desafio) e as tabelas são criadas automaticamente quando a aplicação é executada.
 
    2.2) O arquivo application.properties contém os dados da conexão do banco.
  
        spring.datasource.url=jdbc:postgresql://localhost:5432/desafio
        spring.datasource.username=postgres (Alterar para o usuário do seu banco)    
        spring.datasource.password=eduardo (Alterar para a senha do seu banco)    
