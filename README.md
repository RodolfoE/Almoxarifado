1. Usando o Postgre, crie um banco de dados e um usuário e dê a ele todas as permissões.
    * O usuário e senha no postgres estão, respectivamente: postgres, postgres.

2. Crie as seguintes relações no banco de dados postgres:
    --Comando para trocar BD para Almoxarifado no pgAdmin
    --SET search_path TO Almoxarifado;

  create table Equipamento
  (
      Id SERIAL primary key,
      nome text NOT NULL
  );
  
  create table Ordem_Manutencao
	(
	    Id SERIAL primary key,
	    Id_Equipamento int NOT NULL,
	    Prev_Manutencao date not null,
	    FOREIGN KEY(Id_Equipamento) references Equipamento(Id)
	);  
  
3. Vá até a pasta complete/build/libs e execute o arquivo JAR.
    java -jar gs-rest-service-0.1.0.jar
   
4. Navegue até ao seu localhost, na porta 8080.
    http://localhost:8080/uri

5. Ou usando uma ferramenta de requisição HTTP, como o postman
  A. Listar o inventário com todos os equipamentos;
  * Obtem todas as tupla da relação Equipamento
  GET http://localhost:8080/obter_equipamento
  
  B. Consultar um equipamento específico através de seu id;
  * Obtem tupla da relação Equipamento por PK id
  GET http://localhost:8080/obter_equipamento/{id}
  
  C. Criar uma ordem de manutenção
  * Insere tupla na relação Ordem_Manutencao
  POST http://localhost:8080/post_ordem_manutencao
  {
    "Id_equipamento": 1,
    "prev_manutencao": "2019-10=04"
  }
  
  D. Alterar uma ordem de manutenção existente;
  * Altera a tupla de PK id na relação Ordem_Manutencao.
  PUT http://localhost:8080/alterar_Ordem_Manutencao/{id}
  {
    "Id_equipamento": 1,
    "prev_manutencao": "2019-10-05"
  }
  
  E. Consultar todas as ordens de manutenção existentes;
  * Obtem todas as tuplas da relação Ordem_Manutencao
  GET http://localhost:8080/obter_om
 
  F. Consultar uma ordem específica através de seu id.
  * Obtem tupla da relação Ordem_Manutencao por PK id
  GET http://localhost:8080/obter_om/{id}
  
  
