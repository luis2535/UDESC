import numpy as np
from PIL import Image

# carregar a imagem
img = np.array(Image.open('imgs/11_test.png').convert('L'))

# definir as máscaras de filtragem
mascara_a = np.array([[0, 1, 0], [1, -4, 1], [0, 1, 0]])
mascara_b = np.array([[1, 1, 1], [1, -8, 1], [1, 1, 1]])

# ajustar para controlar a intensidade do filtro
c = -1

# criar matrizes de 0 com as mesmas dimensões e tipo de dados da img
gradiente_a = np.zeros_like(img, dtype=np.float32)
gradiente_b = np.zeros_like(img, dtype=np.float32)

# calcular o operador Laplaciano com a máscara para cada pixel da imagem
for i in range(1, img.shape[0] - 1):
    for j in range(1, img.shape[1] - 1):
        regiao = img[i - 1:i + 2, j - 1:j + 2]
        gradiente_a[i, j] = np.sum(regiao * mascara_a)
        gradiente_b[i, j] = np.sum(regiao * mascara_b)

# aplicar o filtro de aguçamento
filtro_agucamento_a = img + c * gradiente_a
filtro_agucamento_b = img + c * gradiente_b

# ajustar os valores para ficarem dentro da faixa desejada
filtro_agucamento_a = np.clip(filtro_agucamento_a, 0, 255)
filtro_agucamento_b = np.clip(filtro_agucamento_b, 0, 255)

# converter para o tipo de dado de 8 bits
filtro_agucamento_a = filtro_agucamento_a.astype(np.uint8)
filtro_agucamento_b = filtro_agucamento_b.astype(np.uint8)

# salvar imagens filtradas
imagem_agucamento_a = Image.fromarray(filtro_agucamento_a, mode="L")
imagem_agucamento_a.save('questao2/11_test_a.jpg')
imagem_agucamento_b = Image.fromarray(filtro_agucamento_b, mode="L")
imagem_agucamento_b.save('questao2/11_test_b.jpg')

# Ao comparar esteticamente os resultados com as diferentes máscaras de filtragem
# (mascara_a e mascara_b), podemos observar que a máscara_a produz um aguçamento mais suave,
# realçando as bordas e detalhes de forma sutil. Por outro lado, a máscara_b proporciona um
# aguçamento mais intenso, resultando em bordas mais definidas e um contraste mais acentuado.
# A escolha entre as máscaras depende do efeito desejado, sendo a máscara_a adequada para um
# aguçamento mais discreto e a máscara_b mais indicada para um aguçamento mais perceptível.