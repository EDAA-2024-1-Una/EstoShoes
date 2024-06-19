CREATE TABLE usuarios ( 
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR (64) NOT NULL,
    email VARCHAR (64) NOT NULL,
    senha VARCHAR (64) NOT NULL
);

CREATE TABLE clientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR (64) NOT NULL,
    email VARCHAR (64) NOT NULL,
    telefone VARCHAR (14) NOT NULL,
    rua VARCHAR (64) NOT NULL,
    numero VARCHAR (24) NOT NULL,
    bairro VARCHAR (64) NOT NULL,
    cidade VARCHAR (64) NOT NULL,
    cep VARCHAR (10) NOT NULL
);

CREATE TABLE produtos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR (64) NOT NULL,
    cor VARCHAR (30) NOT NULL,
    numeracao INT NOT NULL,
    codigo VARCHAR (30) NOT NULL,
    imagem_url VARCHAR (64) NOT NULL,
    valor DECIMAL(10, 2) NOT NULL,
    quantidade_estoque INT,
    quantidade_vendida INT
);

CREATE TABLE vendas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    id_cliente INT NOT NULL,
    valor_total DECIMAL(10, 2) NOT NULL,
    quantidade_produtos INT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id),
    FOREIGN KEY (id_cliente) REFERENCES clientes(id)
);

CREATE TABLE itens_venda (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_venda INT NOT NULL,
    id_produto INT NOT NULL,
    quantidade INT NOT NULL,
    preco_unitario DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (id_venda) REFERENCES vendas(id),
    FOREIGN KEY (id_produto) REFERENCES produtos(id)
);