import numpy as np
from skimage.morphology import binary_erosion, binary_dilation
from PIL import Image

# Definir a imagem original W
W = np.array([[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
              [0, 0, 1, 0, 1, 0, 1, 1, 1, 0, 0],
              [0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0],
              [0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0],
              [0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0],
              [0, 0, 0, 0, 1, 0, 1, 1, 1, 0, 0],
              [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
              [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]], dtype=np.uint8)

# Definir o elemento estruturante B
B = np.array([[0, 1, 0],
              [1, 1, 0],
              [0, 1, 0]], dtype=np.uint8)

# Realizar a erosão de W usando o elemento estruturante B para obter X0
X0 =  binary_erosion(W, B)
# Inicializar Xk como X0
Xk = X0
print('X0 erosion:')
print(Xk.astype(np.uint8))
count = 1

# Realizar a operação iterativa até que não haja mudanças
while True:

    # Aplicar a dilatação de Xk usando o elemento estruturante B
    Xk_dilated = binary_dilation(Xk, B)
    # Realizar a interseção (AND) de Xk_dilated com W
    Xk_new = np.logical_and(Xk_dilated, W).astype(np.uint8)
    print(f'X{count} after dilation and intersection:')
    print(Xk_new.astype(np.uint8))
    count = count + 1
    # Verificar se houve mudanças entre Xk e Xk_new
    if np.array_equal(Xk_new, Xk):
        break
    
    # Atualizar Xk com Xk_new
    Xk = Xk_new

# Converter a matriz Xk em binário e inverter os valores
Xk_binary = 1 - Xk.astype(np.uint8)

# Criar uma imagem PIL com base na matriz Xk_binary
image = Image.fromarray(Xk_binary * 255, 'L')

# Salvar a imagem resultante em um arquivo
image.save('3B/resultado.png')

# Criar uma imagem PIL com base na matriz W

# Inverter os valores da matriz W
W_inverted = 1 - W.astype(np.uint8)

# Criar uma imagem PIL com base na matriz W_inverted
image_w_inverted = Image.fromarray(W_inverted * 255, 'L')

# Salvar a imagem original invertida em um arquivo
image_w_inverted.save('3B/original.png')
