CREATE TABLE usuario_especialidade (
  id INT PRIMARY KEY AUTO_INCREMENT,
  usuario_id INT,
  especialidade_id INT,

  FOREIGN KEY (usuario_id) REFERENCES usuario(id) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (especialidade_id) REFERENCES especialidade(id) ON DELETE CASCADE ON UPDATE CASCADE
);