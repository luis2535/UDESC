CREATE TABLE servico_especialidade (
  id INT PRIMARY KEY AUTO_INCREMENT,
  servico_id INT NOT NULL,
  especialidade_id INT NOT NULL,

  FOREIGN KEY (servico_id) REFERENCES servico(id) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (especialidade_id) REFERENCES especialidade(id) ON DELETE CASCADE ON UPDATE CASCADE
);