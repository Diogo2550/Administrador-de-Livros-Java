# ADMINISTRADOR DE LIVROS
Um administrador de livros criado como um exercício para a matéria de Linguagens e Técnicas de Programação III do curso Técnico em Informática do IFRJ - *Campus* Arraial do Cabo.

O presente sistema pode ser entendido como um sistema para administrar livros (como o de uma biblioteca), possibilitando a adição, consulta, exclusão e atualização de livros, assim como, contas para usuários normais - que podem consultar os livros - e administradores - que possuem direito de adicionar/excluir/atualizar. Ele foi completamente escrito em Java e foi feito para demonstrar o uso da linguagem com a conexão ao banco de dados. 

<img src="https://i.imgur.com/Z1Mjebh.png">

Apesar de ser uma tarefa simples, eu tentei fazê-lo de uma forma relativamente boa, não tive tempo de criar um mini ORM que monta as queries automaticamente como acontece no <a href="https://github.com/Diogo2550/Sunday-Framework">Sunday Framework</a>, porém, foi criada uma função com *Reflection* que, a partir de um *ResultSet* (objeto que o Java retorna após uma consulta ao banco), era criado, automaticamente, uma *List* de um objeto qualquer.

Caso queiram testar o aplicativo, basta clonar este repositório e criar o banco de dados do projeto (o arquivo .sql para a criação do banco está na pasta "sql-db"), após isso, basta abrir o projeto com o Netbeans, alterar os dados para logar no banco no método *init()* do *App.java* e executar normalmente.

## Tecnologias e técnicas utilizadas:
- Java.
- MySQL.
- Reflection.

## Screenshots
<img src="https://i.imgur.com/8Oddt1H.png">
<img src="https://i.imgur.com/TdRe1UU.png">