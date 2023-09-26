    drop database if exists Locadora;

    create database Locadora;

    use Locadora;

    create table Produtora(id bigint not null auto_increment, cnpj varchar(18) not null, nome varchar(256) not null, primary key (id));

    create table Filme(id bigint not null auto_increment, titulo varchar(256) not null, diretor varchar(256) not null, ano integer not null, preco float not null, produtora_id bigint not null, primary key (id), foreign key (produtora_id) references Produtora(id));

    -- Inserir produtora
    INSERT INTO Produtora(cnpj, nome) VALUES ('15.374.630/0001-95', 'Universal Pictures');

    -- Inserir filme
    INSERT INTO Filme(titulo, diretor, ano, preco, produtora_id) VALUES ('Jurassic Park', 'Steven Spielberg', 1993,55.5, 1);

    -- Inserir produtora
    INSERT INTO Produtora(cnpj, nome) VALUES ('15.374.630/0001-95', 'Paramount Pictures ');

    -- Inserir filme
    INSERT INTO Filme(titulo, diretor, ano, preco, produtora_id) VALUES ('The Godfather', 'Francis Ford Coppola', 1972, 51.1, 2);
    -- Inserir produtora
    INSERT INTO Produtora(cnpj, nome) VALUES ('15.374.630/0001-95', '20th Century Fox');

    -- Inserir filme
    INSERT INTO Filme(titulo, diretor, ano, preco, produtora_id) VALUES ('Avatar', 'James Cameron', 2009, 22.5, 3);
