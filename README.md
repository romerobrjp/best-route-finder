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

## Tencologias e ferramentas utilizadas:
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

### Motivação
Apesar de trabalhar diariamente com tecnologias como JSF, EJB, Glassfish, Jersey REST API, escolhi trabalhar as utilizadas neste projeto devido à  simplicidade e "light weight" para construir e executar uma aplicação web. Apesar de nunca ter desenvolvido aplicações com Spring Web nem Jetty, esta escolha me permitiu nova aquisição de conhecimentos extremamente importantes onde as pessoas buscam cada vez menos reinventar a roda e utilizar-se de tecnologias cada vez mais inteligentes e simples ao mesmo tempo, refletindo assim na produtividade do desenvolvimento.


### Como executar a aplicação:
- Antes de tudo é necessário que ter instalado e configurado as seguintes ferramentas: git, uma conta no GitHub, Maven 3m JDK 7.
- Utilizando sua IDE favorita ou através de linha de comando, faça um clone do respositótio Git através da url https://github.com/romeromfm/walmart-logistics.git
- Após baixar o projeto, mande o Maven baixar e instalar as dependências e plugins para o projeto
- Se necessário, modifique a versão do java que será usado na compilação através no parâmetro ${java-version} no pom.xml
- Instale o PorstreSQL (se não tiver ainda) e crie uma banco chamado "walmart-logistics"
- Vamos agora iniciar nosso servidor de aplicações. Vá até a pasta do projeto e execute o comando do plugin Jetty para maven "mvn jetty:run", ou se preferir pela sua IDE. Não é necessário baixar o Jetty, o plugin do Maven se encarrega de tudo sozinho.
- Com o servidor rodando, acesse a url localhost:8080/jsondoc-ui.html para acessar a documentação da API REST
- É possível realizar as requisições aos recursos REST a partir da página citada no tópico anterior, mas você também pode utilizar o cliente REST que preferir
- Primeiramente será necessário popular o banco dados, para isso, utilize o recurso /delivery com o método POST
- Após popular o banco, você poderá realizar pesquisas para encontrar as melhores rota através do recurso /delivery com o método GET
- Dúvidas de como realizar interação com os serviços REST, basta seguir as orientações da documentação citada anteriormente.