
#Desafio compasso - UOL
Desafio proposto da empresa Compasso UOL

Usado o banco de dados em memoria H2, não sera necessario nenhuma instalação para inicializar o microserviço.

Após inicializado o projeto é possivel testa-lo nos endpoint abaixos:

URL base: http:localhost:9999/products
````
- POST	      /products         Criação de um produto
- PUT	      /products/        Atualização de um produto
- GET	      /products/        Busca de um produto por ID
- GET	      /products	        Lista de produtos
- GET	      /products/search  Lista de produtos filtrados
- DELETE      /products/        Deleção de um produto
````
Para acessar o banco H2 utilizar a URL http://localhost:9999/h2 no navegador, colocar no campo JDBC URL - jdbc:h2:mem:uol (para banco em memória, que é apagado toda vez que a aplicação é derrubada) ou jdbc:h2:file:~/compasso/uol (para banco que persiste os dados mesmo com a aplicação derrubada. Descomentar a linha 10 do application.properties).No campo User Name e Password colocar uol.

Utilizei o design pattern **CHAIN OF RESPONSABILITY** por se tratar de uma aplicação simples e o design adotado atende bem com a sua principal função de evitar dependencia ente um objeto receptor e um objeto solicitante.

Utilizado a biblioteca mapper para conversão de objetos, facilitando a escrita do código e deixando-o mais clean.

Utilizei um padrão personalizado de exceptions para tratar erros e devolver mensagem padrão.

Criação de testes untarios para garantir a funcionalidade da aplicação, no desafio não foi solicitado mas acredito que seja interessante e considerado boa pratica e essencial.
