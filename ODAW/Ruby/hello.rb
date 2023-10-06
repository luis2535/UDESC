class OlaMundo
 	def initialize nome #construtor que inicializa a variável nome
		# Armazena o parâmetro em uma variável de instância.
		@nome = nome
	end
 
	# Método que imprime a mensagem junto com o nome.
	def ola
		puts "Olá, #{@nome}!"
	end
end
 
# Instancia a classe enviando o nome como argumento para o construtor.
ola_mundo= OlaMundo.new "Luís"
 
# Invoca o método ola, que imprimirá a mensagem.
ola_mundo.ola
