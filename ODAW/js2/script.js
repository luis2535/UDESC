// Função para validar o formulario
function validarFormulario() {
    var tipo = document.querySelector('input[name="tipo"]:checked');
    var nome = document.querySelector('input[name="nome"]').value;
    var email = document.querySelector('input[name="email"]').value;
    var titulo = document.querySelector('input[name="titulo"]').value;
  
    if (!tipo) {
      alert("Por favor, selecione o tipo (filme ou série).");
      return false;
    }
  
    if (nome === "") {
      alert("Por favor, preencha seu nome.");
      return false;
    }
  
    if (email === "") {
      alert("Por favor, preencha seu email.");
      return false;
    }
  
    if (titulo === "") {
      alert("Por favor, preencha o título da sugestão.");
      return false;
    }
  
    return true;
  }


  // Melhora o scroll da pagina
  
document.addEventListener('DOMContentLoaded', function () {
    var links = document.querySelectorAll('a[href^="#"]');
    for (var i = 0; i < links.length; i++) {
    links[i].addEventListener('click', scrollToSection);
    }
});

function scrollToSection(e) {
e.preventDefault();
    var targetId = this.getAttribute('href').substring(1);
    var targetElement = document.getElementById(targetId);
    if (targetElement) {
        window.scrollTo({
        top: targetElement.offsetTop - 50,
        behavior: 'smooth'
        });
    }
}
      

// 3 - Função com contagem regressiva
function contagemRegressiva(dataAlvo) {
    const agora = new Date();
    const diferenca = dataAlvo - agora;
    
    const dias = Math.floor(diferenca / (1000 * 60 * 60 * 24));
    const horas = Math.floor((diferenca % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
    const minutos = Math.floor((diferenca % (1000 * 60 * 60)) / (1000 * 60));
    const segundos = Math.floor((diferenca % (1000 * 60)) / 1000);
    
    document.getElementById('dias').textContent = dias;
    document.getElementById('horas').textContent = horas;
    document.getElementById('minutos').textContent = minutos;
    document.getElementById('segundos').textContent = segundos;
  }
    
    
    const dataAlvo = new Date('2023-10-13T00:00:00');
    
    setInterval(() => contagemRegressiva(dataAlvo), 1000);


// 4 - recolher e expandir texto


function expandirSinopse(sinopseId) {
    var sinopse = document.getElementById(sinopseId);
    sinopse.style.display = 'block';
  }
  

  function recolherSinopse(sinopseId) {
    var sinopse = document.getElementById(sinopseId);
    sinopse.style.display = 'none';
  }


  // 5 - Modo claro e modo escuro

  function alternarTema() {
    const elementoRaiz = document.documentElement;

    if (elementoRaiz.classList.contains('modo-claro')) {
        elementoRaiz.classList.remove('modo-claro');
        elementoRaiz.classList.add('modo-escuro');
    } else {
        elementoRaiz.classList.remove('modo-escuro');
        elementoRaiz.classList.add('modo-claro');
    }
}