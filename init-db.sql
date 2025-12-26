-- Create database schema for ERP System
USE erp_db;

-- Create Produto table
CREATE TABLE IF NOT EXISTS produto (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    descricao TEXT,
    preco DECIMAL(10, 2) NOT NULL,
    sku VARCHAR(100) UNIQUE NOT NULL,
    ativo BOOLEAN DEFAULT TRUE,
    quantidadeEstoque INT DEFAULT 0,
    dataAdicao VARCHAR(10),
    horaAdicao VARCHAR(8),
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Create Estoque table
CREATE TABLE IF NOT EXISTS estoque (
    id INT PRIMARY KEY AUTO_INCREMENT,
    produto_id INT NOT NULL,
    quantidade INT NOT NULL DEFAULT 0,
    quantidade_minima INT NOT NULL DEFAULT 10,
    armazem VARCHAR(100),
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (produto_id) REFERENCES produto(id) ON DELETE CASCADE
);

-- Create Venda table
CREATE TABLE IF NOT EXISTS venda (
    id INT PRIMARY KEY AUTO_INCREMENT,
    produto_id INT NOT NULL,
    quantidade INT NOT NULL,
    preco_unitario DECIMAL(10, 2) NOT NULL,
    total DECIMAL(12, 2) NOT NULL,
    cliente_nome VARCHAR(255) NOT NULL,
    dataVendaFormatada VARCHAR(10),
    horaVenda VARCHAR(8),
    data_venda TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(50) DEFAULT 'PENDENTE',
    FOREIGN KEY (produto_id) REFERENCES produto(id) ON DELETE RESTRICT
);

-- Create indexes for better performance
CREATE INDEX idx_produto_sku ON produto(sku);
CREATE INDEX idx_estoque_produto_id ON estoque(produto_id);
CREATE INDEX idx_venda_produto_id ON venda(produto_id);
CREATE INDEX idx_venda_data ON venda(data_venda);

-- Insert sample data
INSERT INTO produto (nome, descricao, preco, sku) VALUES
('Notebook Dell', 'Notebook Dell Inspiron 15', 3500.00, 'DELL-INS-15'),
('Mouse Logitech', 'Mouse sem fio Logitech MX Master', 250.00, 'LOG-MX-MASTER'),
('Teclado Mecânico', 'Teclado mecânico RGB', 450.00, 'MECH-RGB-001');

INSERT INTO estoque (produto_id, quantidade, quantidade_minima, armazem) VALUES
(1, 50, 10, 'Armazém Principal'),
(2, 150, 20, 'Armazém Principal'),
(3, 75, 15, 'Armazém Principal');
