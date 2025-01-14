CREATE TABLE servico (
  id INT PRIMARY KEY AUTO_INCREMENT,
  descricao VARCHAR(10000) NOT NULL,
  valor DECIMAL(15, 2),
  valor_descricao VARCHAR(20),
  usuario_id INT,

  FOREIGN KEY (usuario_id) REFERENCES usuario(id) ON DELETE CASCADE ON UPDATE CASCADE
);