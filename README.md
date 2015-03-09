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
- H2
- Git
- JSONdoc
- jQuery
- IntelliJ IDEA

### Motivação
Apesar de trabalhar diariamente com tecnologias bem robustas (até demais) como JSF, EJB, Glassfish, Jersey REST API, escolhi trabalhar com as citadas no tópico anterior neste projeto devido à simplicidade e "lightweight" das mesmas. Portanto, mesmo sem nunca ter desenvolvido aplicações com Spring Web ou Jetty, esta experiência me permitiu experimentar o que de mais atual e fino há hoje no mundo/mercado de TI (nesse caso Java) para desenvolver aplicações Web limpas, leves e objetivas com bastante produtividade. Além disso, a aquisição e consolidação de novos conhecimentos foi totalmente válida e proveitosa.


### Como executar a aplicação:
- Antes de tudo é necessário ter instalado e configurado as seguintes ferramentas: Git, Maven 3, JDK 7 ou superior.
- Utilizando sua IDE favorita ou através de linha de comando, faça um clone do repositório Git através da url https://github.com/romeromfm/walmart-logistics.git
- Se necessário, modifique a versão do Java que será usado na compilação através no parâmetro ${java-version} no pom.xml
- [Opcional] Após baixar o projeto, dentro da pasta, execute "mvn compile" para compilar as classes e baixar as dependências necessárias
- [Opcional] Para executar os testes execute "mvn test"
- Por padrão a banco utilizado é o PostgreSQL (por ser robusto, grátis e rápido), porém se quiser utilizar um banco mais leve e embarcado é possível utilizar o H2, basta seguir as instruções na seção [Utilizando o banco H2 no modo embedded]
- Instale o PostgreSQL (se não tiver ainda) e crie um banco chamado "walmart-logistics". Usuário e senha 'postgres', como definido no database.properties
- Vamos agora iniciar o servidor de aplicações. Vá até a pasta do projeto e execute o comando do plugin Jetty para maven "mvn jetty:run" o qual irá baixar as dependências necessárias, compilar o projeto e executar os testes. Você pode fazer isso pela IDE também. Não é necessário baixar o Jetty, pois o plugin do Maven se encarrega de tudo sozinho.
- Com o servidor rodando, abra o browser e navegue para a url localhost:8080. Uma página com a documentação da API REST irá carregar.
- É possível realizar as requisições aos recursos REST a partir da página citada no tópico anterior, mas você também pode utilizar o cliente REST que preferir
- Primeiramente, será necessário popular o banco de dados, para isso, utilize o recurso /delivery com o método POST e cadastre quantos Mapas (LogisticsMap) forem necessários.
- Após popular o banco, você poderá realizar pesquisas para encontrar as melhores rotas através do recurso /delivery com o método GET
- Informações sobre os serviços REST podem ser encontradas na documentação citada anteriormente.

## Extras

### Utilizando o banco H2 no modo embedded
- No database.properties comente as configurações PostgreSQL e descomente as relacionadas ao H2
- Altere jdbc.username para "sa" e jdbc.password para vazio
- No arquivo hibernate-config.properties modifique o dialeto para H2
- Você pode visualizar o banco de dados através de um cliente H2. Para isso baixe, extraia o .zip e execute o arquivo .jar que se encontra na pasta /h2/bin. Se preferir um cliente diferente, no link a seguir, na seção "Database Frontends / ToolsDatabase Frontends / Tools", estão listados diversos aplicativos suportados pela H2: http://h2database.com/html/links.html#tools
- Após executar o .jar, será aberta uma página no browser. Basta conectar na url padrão, que é a jdbc:h2:~/test
