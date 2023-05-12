class Usuario {
    constructor(cpf, pnome, unome, email, senha) {
      this.cpf = cpf;
      this.pnome = pnome;
      this.unome = unome;
      this.email = email;
      this.senha = senha;
    }
  
    get cpf() {
      return this.cpf;
    }
  
    set cpf(value) {
      this.cpf = value;
    }
  
    get pnome() {
      return this.pnome;
    }
  
    set pnome(value) {
      this.pnome = value;
    }
  
    get unome() {
      return this.unome;
    }
  
    set unome(value) {
      this.unome = value;
    }
  
    get email() {
      return this.email;
    }
  
    set email(value) {
      this.email = value;
    }
  
    get senha() {
      return this.senha;
    }
  
    set senha(value) {
      this.senha = value;
    }
  
    // Métodos estáticos e de instância aqui
  }
  