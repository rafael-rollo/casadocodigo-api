-- schema definition ------------------------------------------------

create table author (
	id integer generated by default as identity,
	bio varchar(255),
	first_name varchar(255),
	last_name varchar(255),
	profile_picture_path varchar(255),
	primary key (id)
);

create table author_technologiesshe_writes_about (
	author_id integer not null,
 	technologiesshe_writes_about varchar(255)
);

create table book (
	id integer generated by default as identity,
	cover_image_path varchar(255),
	description clob,
	isbn varchar(255),
	number_of_pages integer not null check (number_of_pages>=30),
	publication_date date not null,
	subtitle varchar(255),
	title varchar(255),
	author_id integer not null,
	primary key (id)
);

create table book_prices (
	book_id integer not null,
	book_type varchar(255),
	value decimal(19,2)
);

alter table author_technologiesshe_writes_about
	add constraint technologies_of_an_author
	foreign key (author_id)
	references author;


alter table book
	add constraint author_of_a_book
	foreign key (author_id)
	references author;


alter table book_prices
	add constraint book_for_prices
	foreign key (book_id)
	references book;

-- initial data -----------------------------------------------------

-- rollo
insert into author (first_name, last_name, bio, profile_picture_path)
	values ('Rafael', 'Rollo', 'Um dev que adora o que faz tanto quanto o futebol, a música ou qualquer outra manifestação artística através da qual pessoas apaixonadas expressam sua verdade.', 'https://user-images.githubusercontent.com/13206745/114415754-5c378f00-9b86-11eb-996d-4d42858ad3c1.jpeg');

insert into author_technologiesshe_writes_about (author_id, technologiesshe_writes_about)
    values (1, 'Java');
insert into author_technologiesshe_writes_about (author_id, technologiesshe_writes_about)
    values (1, 'JavaScript');
insert into author_technologiesshe_writes_about (author_id, technologiesshe_writes_about)
    values (1, 'ReactNative');

insert into book (title, subtitle, cover_image_path, description, number_of_pages, isbn, publication_date, author_id)
    values ('Orientação a Objetos', 'Aprenda seus conceitos e suas aplicabilidades de forma efetiva', 'https://cdn.shopify.com/s/files/1/0155/7645/products/eBook-Orientacao-a-Objetos-atualizado_large.jpg', 'Conforme a demanda por novos paradigmas que correspondessem às necessidades do dia a dia dos programadores, surgia a Orientação a Objetos, com a missão de cobrir as insuficiências do modelo estrutural. O Paradigma Orientado a Objeto tem como principal característica uma melhor e maior expressividade das nossas demandas e possibilita criar unidades de código mais próximas da forma como pensamos e agimos, facilitando o processo de transformação das necessidades diárias para uma linguagem orientada a objetos. Neste livro, Thiago Leite demonstrará todos os seus conceitos para você utilizá-la da forma mais eficiente. Por meio de exemplos, você será iniciado nas boas práticas do uso da Orientação a Objetos para alcançar uma alta qualidade nos seus programas e tornar o processo de desenvolvimento mais produtivo e de mais fácil manutenção.', 377, '978-85-5519-213-5', '2020-10-10', 1);
insert into book (title, subtitle, cover_image_path, description, number_of_pages, isbn, publication_date, author_id)
    values ('ECMAScript 6', 'Entre de cabeça no futuro do JavaScript', 'https://cdn.shopify.com/s/files/1/0155/7645/products/yPVDxju4tCeqY45tdQtvOZo6bdCztD7A1gUZHRoZ5wU_large.jpg', 'Nos últimos anos, a linguagem JavaScript cresceu bastante na comunidade de desenvolvedores, tornando-se mais robusta, poderosa e sendo usada em uma infinidade de aplicações de alto nível, tanto no front-end quanto no back-end das aplicações. Ela é a tecnologia por trás de ferramentas, frameworks e bibliotecas consagradas no mercado, tais como: Angular, Ember, React, Backbone, jQuery, Grunt. A lista é enorme. Atualmente, é essencial que um profissional de TI tenha domínio desta tecnologia, tanto para aplicações web, aplicativos mobile e/ou desktop. É exatamente para isto que este livro foi escrito. Para que você seja capaz de entender todas as mudanças que vieram com o ECMAScript 6, aprimorar suas habilidades como desenvolvedor e se destacar no mercado de trabalho que hoje é tão concorrido. Neste livro, Diego Pinho aborda as principais mudanças que a nova versão da especificação trouxe para a linguagem. Você vai aprender não somente o conceito, mas como aplicá-lo na prática em situações reais.', 227, '978-85-5519-258-6', '2020-10-10', 1);
insert into book (title, subtitle, cover_image_path, description, number_of_pages, isbn, publication_date, author_id)
    values ('React Native', 'Desenvolvimento de aplicativos mobile com React', 'https://cdn.shopify.com/s/files/1/0155/7645/products/cover_3a3f6c84-715f-4214-b5bc-6873470a63cc_large.jpg', 'Neste livro, Bruna Escudelario e Diego Pinho vão desde os primeiros passos com React Native até o desenvolvimento de aplicações que consomem serviços na internet (APIs). No meio desde percurso, você vai aproveitar e estudar o funcionamento da biblioteca React para web, afinal, todos os conceitos trabalhados nela também são usados aqui. Você verá o que é o JSX, o que é um e como criar um componente das mais diversas formas, quais são as principais dependências, o que são propriedades e estados, estilos por meio do Flexbox e CSS, navegação de telas, quais são as principais funções do ciclo de vida de um componente, como passar informações de um componente para outro, consumir serviços da internet e atualizar os componentes com as respostas, até chegar à última grande novidade do React 16, os Hooks. Este livro foi estruturado de modo que os tópicos se complementem e se tornem gradativamente mais complexos ao decorrer da leitura, mas sempre apoiados por códigos contextualizados em casos de usos reais, seguindo as boas práticas adotadas pelo mercado e pela comunidade desenvolvedora.', 185, '978-65-86110-09-8', '2020-10-10', 1);

insert into book_prices (book_id, book_type, value)
    values (1, 'EBOOK', 29.90);
insert into book_prices (book_id, book_type, value)
    values (1, 'HARDCOVER', 49.90);
insert into book_prices (book_id, book_type, value)
    values (1, 'COMBO', 59.90);

insert into book_prices (book_id, book_type, value)
    values (2, 'EBOOK', 29.90);
insert into book_prices (book_id, book_type, value)
    values (2, 'HARDCOVER', 49.90);
insert into book_prices (book_id, book_type, value)
    values (2, 'COMBO', 59.90);

insert into book_prices (book_id, book_type, value)
    values (3, 'EBOOK', 29.90);
insert into book_prices (book_id, book_type, value)
    values (3, 'HARDCOVER', 49.90);
insert into book_prices (book_id, book_type, value)
    values (3, 'COMBO', 59.90);

-- alberto

insert into author (first_name, last_name, bio, profile_picture_path)
	values ('Alberto', 'Souza', 'Eu amo ficar com minha família, adoro esportes e atividade física no geral, estudar e trabalhar =).', 'https://user-images.githubusercontent.com/13206745/114415776-622d7000-9b86-11eb-9920-92e27e199fd7.jpeg');

insert into author_technologiesshe_writes_about (author_id, technologiesshe_writes_about)
    values (2, 'Java');
insert into author_technologiesshe_writes_about (author_id, technologiesshe_writes_about)
    values (2, 'Spring');
insert into author_technologiesshe_writes_about (author_id, technologiesshe_writes_about)
    values (2, 'BlockChain');

insert into book (title, subtitle, cover_image_path, description, number_of_pages, isbn, publication_date, author_id)
    values ('Orientação a Objetos e SOLID para Ninjas', 'Projetando classes flexíveis', 'https://cdn.shopify.com/s/files/1/0155/7645/products/oo-solid-sumario-featured_large.png', 'Neste livro, Maurício Aniche discute como a implementação e o foco no projeto de classes é importante para criar um software de qualidade. Sempre mantendo a característica de escrever um livro com uma didática voltada para o mundo real, você vai entender como moldar e dominar as peças da Orientação a Objetos usando os princípios de SOLID e escrevendo um código mais elegante e fácil de manter. Saia do básico e vire um ninja da Orientação a Objetos aprendendo de forma prática e eficaz as boas práticas do assunto. Os exemplos ao longo do livro estão escritos em Java, porém são facilmente compreendidas por qualquer programador. As discussões fazem sentido para todas as linguagens OO.', 155, '978-85-5519-037-7', '2020-10-10', 2);
insert into book (title, subtitle, cover_image_path, description, number_of_pages, isbn, publication_date, author_id)
    values ('Spring MVC', 'Domine o principal framework web Java', 'https://m.media-amazon.com/images/I/41MdyrEql0L.jpg', 'O Spring é o principal concorrente da especificação JavaEE. Com uma plataforma muito estável e com integração fina entre todas as suas extensões, fornece um ambiente muito propício para que o programador foque nas regras de negócio e esqueça dos problemas de infraestrutura. Durante o livro, será construída uma aplicação baseada na loja da Casa do Código e você terá a chance de utilizar diversas das funcionalidades e integrações providas pelo framework. Usaremos o Spring MVC como alicerce da nossa aplicação web e para implementar todas as funcionalidades, utilizaremos diversas integrações, como: Spring JPA, para facilitar o acesso ao banco de dados; Spring Security, para segurança da aplicação; diferenciação de ambientes com a parte de Profiles; respostas assíncronas para melhorarmos a escalabilidade; e ainda detalhes, como cache e suporte às requisições, seguindo o estilo REST. Tudo isso sem uma linha de XML, todas configurações serão feitas baseadas em anotações e código Java.', 260, '978-85-5519-020-9', '2015-04-10', 2);
insert into book (title, subtitle, cover_image_path, description, number_of_pages, isbn, publication_date, author_id)
    values ('Play Framework', 'Java para web sem Servlets e com diversão', 'https://cdn.shopify.com/s/files/1/0155/7645/products/play-framework-java-featured_large.png', 'Desenvolver sistemas web em Java nunca foi tão divertido! Se você está cansado de escrever longas Servlets, de ficar brigando com seu framework o tempo inteiro para realizar tarefas simples e de escrever mais código que o necessário para desenvolver qualquer funcionalidade, o Play Framework é o que você precisa. Nesse livro, Fernando Boaglio ensina como criar uma aplicação do começo ao fim utilizando o Play Framework na versão Java, passando por situações comuns do dia a dia, indo desde o tradicional cadastro, até funcionalidades mais avançadas como habilitação de HTTPS, login integrado com redes sociais e integração com serviços REST. Você vai aprender como o Play Framework pode o tornar extremamente produtivo.', 147, '978-85-66250-42-8', '2020-10-10', 2);

insert into book_prices (book_id, book_type, value)
    values (4, 'EBOOK', 29.90);
insert into book_prices (book_id, book_type, value)
    values (4, 'HARDCOVER', 49.90);
insert into book_prices (book_id, book_type, value)
    values (4, 'COMBO', 59.90);

insert into book_prices (book_id, book_type, value)
    values (5, 'EBOOK', 29.90);
insert into book_prices (book_id, book_type, value)
    values (5, 'HARDCOVER', 49.90);
insert into book_prices (book_id, book_type, value)
    values (5, 'COMBO', 59.90);

insert into book_prices (book_id, book_type, value)
    values (6, 'EBOOK', 29.90);
insert into book_prices (book_id, book_type, value)
    values (6, 'HARDCOVER', 49.90);
insert into book_prices (book_id, book_type, value)
    values (6, 'COMBO', 59.90);

-- matheus

insert into author (first_name, last_name, bio, profile_picture_path)
	values ('Matheus', 'Brandino', 'Eu sou apenas um rapaz latino-americano, sem dinheiro no banco, sem parentes importantes, e vindo do interior. Mas trago de cabeça uma canção do rádio em que um antigo compositor baiano me dizia: Tudo é divino tudo é maravilhoso!', 'https://user-images.githubusercontent.com/13206745/114415801-678aba80-9b86-11eb-9ad0-1986b12a42e6.jpeg');

insert into author_technologiesshe_writes_about (author_id, technologiesshe_writes_about)
    values (3, 'Kotlin');
insert into author_technologiesshe_writes_about (author_id, technologiesshe_writes_about)
    values (3, 'Android');
insert into author_technologiesshe_writes_about (author_id, technologiesshe_writes_about)
    values (3, 'Flutter');

insert into book (title, subtitle, cover_image_path, description, number_of_pages, isbn, publication_date, author_id)
    values ('Kotlin com Android', 'Crie aplicativos de maneira fácil e divertida', 'https://cdn.shopify.com/s/files/1/0155/7645/products/p_4d784e4f-769e-4f00-82d9-c1c81628055a_large.jpg', 'A linguagem Kotlin vem ganhando cada vez mais destaque no cenário do desenvolvimento de aplicativos. Projetada para ter uma interoperabilidade total com código Java, Kotlin tem sido a primeira escolha na criação de aplicativos Android, sendo multiparadigma e multiplataforma. Trata-se de uma linguagem moderna, concisa e poderosa, oferecendo segurança, clareza e suporte a ferramentas de alta produtividade. Neste livro, Kassiano Resende apresenta a linguagem Kotlin tanto para quem já conhece Java ou não, com uma didática prática e divertida. Você verá todos os passos necessários para construir e publicar na Play Store aplicações mobile em Kotlin, no ambiente de desenvolvimento Android Studio. O escopo das aplicações apresentadas no livro vai além das funcionalidades da linguagem, com a utilização de banco de dados, acesso a APIs REST e funcionalidades dos smartphones, como notificações, acesso à galeria de imagens, câmera e localização através de GPS.', 346, '978-85-94188-75-5', '2020-10-10', 3);
insert into book (title, subtitle, cover_image_path, description, number_of_pages, isbn, publication_date, author_id)
    values ('Entrega contínua em Android', 'Como automatizar a distribuição de apps', 'https://cdn.shopify.com/s/files/1/0155/7645/products/33JmvoJMSmMmf2E7Qns5gMEO7R7PjA1GmyHAQP4RFj0_large.jpg', 'Independente de ser um desenvolvedor freelancer ou um time de desenvolvimento de software já maduro, um item especial ao qual o responsável pelo desenvolvimento de um novo software deve dar especial atenção é a entrega do software ao cliente. Como será realizada a entrega? Com qual frequência? Será adotado algum processo automatizado ou a entrega do software será manual? Como o software é validado antes da entrega? Embora já tenha-se discutido sobre essas questões, um vácuo literário permanece quando pensamos em entrega contínua de aplicações mobile. Neste livro, Roger Silva vem preencher esta lacuna, explicando como automatizar a distribuição do app para o Google Play, como executar testes automatizados sobre as suas funcionalidades e disponibilizá-lo para a equipe de testes em vários dispositivos. Você verá como tratar todos esses requisitos para que, quando um desenvolvedor comitar seu código-fonte para um repositório de código remoto, uma bateria de testes automatizados seja executada, acompanhada por uma análise de cobertura de código-fonte, verificações de regras de negócios e, por fim, chegando até a etapa de distribuição automatizada do app para o Google Play.', 190, '978-85-5519-219-7', '2020-10-10', 3);
insert into book (title, subtitle, cover_image_path, description, number_of_pages, isbn, publication_date, author_id)
    values ('Iniciando com Flutter Framework', 'Desenvolva aplicações móveis no Dart Side!', 'https://cdn.shopify.com/s/files/1/0155/7645/products/Amazon-Flutter_large.jpg', 'No desenvolvimento de aplicativos para dispositivos móveis, precisamos nos preocupar muito mais com a usabilidade, interface gráfica, desempenho e praticidade. A escolha das ferramentas corretas impacta diretamente na qualidade do que estamos construindo quando produto final. Um mundo ideal seria poder obter o mesmo processamento e renderização de uma aplicação nativa em uma aplicação híbrida que possibilitasse escrever apenas um código e executar para todas as plataformas. Flutter veio com uma proposta totalmente diferente de seus antecessores e promete ser um framework que realmente divida águas no meio tecnológico. Neste livro, Leonardo Marinho mostra quais dores o Flutter veio para curar, com a criação de aplicativos verdadeiramente performáticos, atrativos e multiplataforma. Você conhecerá desde a arquitetura de software fantástica por baixo dele e as razões pelas quais Dart foi a linguagem escolhida para programar nele, verá que tudo será organizado com Widgets e poderá aproveitá-los ao máximo. Pelo caminho, colocará em prática as principais etapas da criação de um aplicativo, como gerenciamento de dependências, requisições http, banco de dados local, testes automatizados, estilização e muito mais!', 173, '978-65-86110-26-5', '2020-10-10', 3);

insert into book_prices (book_id, book_type, value)
    values (7, 'EBOOK', 29.90);
insert into book_prices (book_id, book_type, value)
    values (7, 'HARDCOVER', 49.90);
insert into book_prices (book_id, book_type, value)
    values (7, 'COMBO', 59.90);

insert into book_prices (book_id, book_type, value)
    values (8, 'EBOOK', 29.90);
insert into book_prices (book_id, book_type, value)
    values (8, 'HARDCOVER', 49.90);
insert into book_prices (book_id, book_type, value)
    values (8, 'COMBO', 59.90);

insert into book_prices (book_id, book_type, value)
    values (9, 'EBOOK', 29.90);
insert into book_prices (book_id, book_type, value)
    values (9, 'HARDCOVER', 49.90);
insert into book_prices (book_id, book_type, value)
    values (9, 'COMBO', 59.90);