CREATE TABLE servico_usuario (
  id INT PRIMARY KEY AUTO_INCREMENT,
  usuario_id INT NOT NULL,
  servico_id INT NOT NULL,
  valor DECIMAL(15, 2),

  FOREIGN KEY (usuario_id) REFERENCES usuario(id) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (servico_id) REFERENCES servico(id) ON DELETE CASCADE ON UPDATE CASCADE
);