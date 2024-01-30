CREATE TABLE filmes (
    id             BIGINT(20) AUTO_INCREMENT PRIMARY KEY,
    duracao        VARCHAR(10) NOT NULL,
    genero         VARCHAR(50) NOT NULL,
    sinopse        VARCHAR(300) NOT NULL,
    ano_lancamento INT(11) NOT NULL,
    faixa_etaria   VARCHAR(100) NOT NULL,
    nome           VARCHAR(300) NOT NULL
);
