CREATE TABLE servico_comentario (
  id INT PRIMARY KEY AUTO_INCREMENT,
  comentario VARCHAR(5000),
  orcamento DECIMAL(15, 2),
  comentario_orcamento VARCHAR(2000),
  servico_id INT NOT NULL,
  usuario_id INT NOT NULL,

  FOREIGN KEY (servico_id) REFERENCES servico(id) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (usuario_id) REFERENCES usuario(id) ON DELETE CASCADE ON UPDATE CASCADE
);