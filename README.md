# Walmart Logistics Challange 

## Entregando mercadorias

O Walmart esta desenvolvendo um novo sistema de logistica e sua ajuda é muito importante neste momento. 
Sua tarefa será desenvolver o novo sistema de entregas *visando sempre o menor custo*. 
Para popular sua base de dados o sistema precisa expor um *Webservices* que aceite o formato de malha logística 
(exemplo abaixo), nesta mesma requisição *o requisitante deverá informar um nome para este mapa*. 
*É importante que os mapas sejam persistidos* para evitar que a cada novo deploy todas as informações desapareçam. 
O formato de malha logística é bastante simples, cada linha mostra uma rota: ponto de origem, ponto de destino e 
distância entre os pontos em quilômetros.

- A B 10 
- B D 15
- A C 20
- C D 30
- B E 50
- D E 30

Com os mapas carregados o requisitante irá procurar o menor valor de entrega e seu caminho, para isso 
ele passará o *nome do ponto de origem, nome do ponto de destino, autonomia do caminhão (km/l) e o valor do litro 
do combustivel*, agora sua tarefa é criar este Webservices. 
Um exemplo de entrada seria, origem A, destino D, autonomia 10, valor do litro 2,50; a resposta seria a rota A B D com custo de 6,25.

--------------------------------------------------------------------------------------------------------------

## Tecnologias e ferramentas utilizadas:
- Java EE Web API 7
- Spring Context
- Spring Web
- Spring Data JPA
- Spring Test
- Maven
- JPA
- Hibernate
- PostgreSQL
- Git
- JSONdoc
- jQuery

### Como executar a aplicação:
- Antes de tudo é necessário que ter instalado e configurado as seguintes ferramentas: Git, Maven 3, JDK 7 ou superior.
- Utilizando sua IDE favorita ou através de linha de comando, faça um clone do respositótio Git através da url https://github.com/romeromfm/walmart-logistics.git
- Se necessário, modifique a versão do java que será usado na compilação através no parâmetro ${java-version} no pom.xml
- [Opcional] Após baixar o projeto, dentro da pasta, execute "mvn compile" para compilar as classes e baixar as dependências necessárias
- [Opcional] Para executar os testes execute "mvn test"
- Por padrão a banco utilizado é o PostgreSQL (por ser robusto, grátis e rápido), porém se quiser utilizar um banco mais leve e embarcado é possível utilizar o H2, basta seguir as instruções na seção [Utilizando H2 Embedded]
- Instale o PostgresSQL (se não tiver ainda) e crie uma banco chamado "walmart-logistics". Usuário e senha 'postgres', como definido no database.properties
- Vamos agora iniciar o servidor de aplicações. Vá até a pasta do projeto e execute o comando do plugin Jetty para maven "mvn jetty:run" o qual irá baixar dependências necessárias, compilar o projeto e executar os testes, ou se preferir pela sua IDE. Não é necessário baixar o Jetty, pois o plugin do Maven se encarrega de tudo sozinho.
- Com o servidor rodando, acesse a url localhost:8080 para acessar a documentação da API REST
- É possível realizar as requisições aos recursos REST a partir da página citada no tópico anterior, mas você também pode utilizar o cliente REST que preferir
- Primeiramente será necessário popular o banco dados, para isso, utilize o recurso /delivery com o método POST
- Após popular o banco, você poderá realizar pesquisas para encontrar as melhores rota através do recurso /delivery com o método GET
- Dúvidas de como realizar interação com os serviços REST, basta seguir as orientações da documentação citada anteriormente.

### Extras

# Utilizando o banco H2 no modo embedded
- No database.properties comente as configurações PostgreSQL e descomente as relacionadas ao H2
- Altere jdbc.username para "sa" e jdbc.password para vazio
- No arquivo hibernate-config.properties modifique o dialeto para H2
- Você pode visualizar o banco de dados através de um cliente H2. Para isso baixe, extraia o .zip e execute o arquivo .jar que se encontra na pasta /h2/bin. Se preferir um cliente diferente, no link a seguir, na seção "Database Frontends / ToolsDatabase Frontends / Tools" estão listados diversos aplicativos suportados pela H2: http://h2database.com/html/links.html#tools
- Após executar o .jar, será aberta uma página no browser. Basta apertar para conectar na url padrão mesmo que é a jdbc:h2:~/test