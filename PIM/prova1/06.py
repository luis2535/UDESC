import numpy as np
import cv2
import matplotlib.pyplot as plt

# função que organiza as cores da matriz seguindo o padrão
def gera_cores(tamanho):
    linha = tamanho[0]
    coluna = tamanho[1]
    for i in range(linha):
        for j in range(coluna):
            if(i%2 == 0 and j%2 == 0):
                matriz_cores[i][j] = 'R'
            elif(i%2 != 0 and j%2 != 0):
                matriz_cores[i][j] = 'B'
            else:
                matriz_cores[i][j] = 'G'

# calculos caso a cor na matriz seja vermelha
def red_color(i, j):
    R = matriz_total[i][j]
    G = soma_percorre('G', i, j)/4
    B = soma_percorre('B', i, j)/4

    # salva na posição i - 1 e j - 1 para voltar a matriz para o tamanho NxN
    matriz_vermelha[i-1][j-1] = R
    matriz_verde[i-1][j-1] = G
    matriz_azul[i-1][j-1] = B

# calculos caso a cor na matriz seja verde
def green_color(i, j):
    R = soma_percorre('R', i, j)/2
    G = matriz_total[i][j]
    B = soma_percorre('B', i, j)/2

    # salva na posição i - 1 e j - 1 para voltar a matriz para o tamanho NxN
    matriz_vermelha[i-1][j-1] = R
    matriz_verde[i-1][j-1] = G
    matriz_azul[i-1][j-1] = B

# calculos caso a cor na matriz seja azul
def blue_color(i, j):
    R = soma_percorre('R', i, j)/4
    G = soma_percorre('G', i, j)/4
    B = matriz_total[i][j]

    # salva na posição i - 1 e j - 1 para voltar a matriz para o tamanho NxN
    matriz_vermelha[i-1][j-1] = R
    matriz_verde[i-1][j-1] = G
    matriz_azul[i-1][j-1] = B

# percorre os valores que fazem fronteira com o pixel analisado e soma de acordo com as cores
def soma_percorre(cor, i, j):
    soma = 0
    if(matriz_cores[i - 1][j - 1] == cor):
        soma += matriz_total[i - 1][j - 1]

    if(matriz_cores[i - 1][j] == cor):
        soma += matriz_total[i - 1][j]

    if(matriz_cores[i - 1][j + 1] == cor):
        soma += matriz_total[i - 1][j + 1]

    if(matriz_cores[i][j - 1] == cor):
        soma += matriz_total[i][j - 1]

    if(matriz_cores[i][j + 1] == cor):
        soma += matriz_total[i][j + 1]

    if(matriz_cores[i + 1][j - 1] == cor):
        soma += matriz_total[i + 1][j - 1]

    if(matriz_cores[i + 1][j] == cor):
        soma += matriz_total[i + 1][j]

    if(matriz_cores[i + 1][j + 1] == cor):
        soma += matriz_total[i + 1][j + 1]

    return soma

# função utilizada para calcular o limiar com o algoritmo isodata
def limiar_isodata(imagem, valor_inicial, maxIter, delta):
    # valor inicial para o limiar para iniciar os testes
    limiar = valor_inicial
    iter = 0
    # numero maximo de iterações para evitar loop
    while iter < maxIter:
        # separa a imagem em 2 para testar o limiar
        img1 = imagem[imagem <= limiar]
        img2 = imagem[imagem > limiar]
        # medias de cada pixel
        media1 = np.mean(img1)
        media2 = np.mean(img2)

        novo_limiar = (media1 + media2)/2
        # verifica quanto o limiar mudou em relação ao antigo, se for menor que o delta definido, ele para a execução
        if abs(novo_limiar - limiar) < delta:
            break
        limiar = novo_limiar
        iter += 1
    return(limiar)

       



# matriz de entrada N por N
matriz_total = np.array([[10, 130, 15, 110, 15, 120],       
                  [215, 40, 250, 30, 250, 40],
                  [15, 255, 15, 255, 15, 230],
                  [210, 30, 255, 45, 250, 45],
                  [10, 115, 10, 110, 10, 115],
                  [110, 30, 110, 35, 115, 45]])
tamanho_inicial = matriz_total.shape
# gerando uma matriz de mesmo tamanho que a entrada para gravar as cores
matriz_cores = np.zeros(tamanho_inicial, dtype=np.str_)
gera_cores(tamanho_inicial)

# adicionando 0's nas bordas da matriz de entrada para evitar erros na hora dos calculos
matriz_total = np.pad(matriz_total, ((1, 1), (1, 1) ), mode = 'constant')
# adicionando 0's nas bordas da matriz de cores para as posições ficarem compativeis com a matriz de entrada
matriz_cores = np.pad(matriz_cores, ((1, 1), (1, 1) ), mode = 'constant')

# separando linha e coluna da matriz
shape = matriz_total.shape
linha = shape[0]
coluna = shape[1]

# criação de matrizes que irão conter os valores RGB de cada pixel
matriz_vermelha = np.zeros(tamanho_inicial)
matriz_verde = np.zeros(tamanho_inicial)
matriz_azul = np.zeros(tamanho_inicial)

for i in range(linha):
    for j in range(coluna):
        # para cada cor de pixel diferente será chamada uma função para fazer o cálculo do RGB desse pixel 
        if(matriz_cores[i][j] == 'R'): 
            red_color(i, j)
        elif(matriz_cores[i][j] == 'G'):
            green_color(i, j)
        elif(matriz_cores[i][j] == 'B'):
            blue_color(i, j)
        else: # quando for 0 apenas passa
            continue

# a partir daqui segue o código para a questão 6
# gera uma matriz com os valores cinza(RGB são iguais nessa matriz)
matriz_cinza = np.zeros(tamanho_inicial)

for i in range(tamanho_inicial[0]):
    for j in range(tamanho_inicial[1]):
        # utiliza a algoritmo abaixo para modificar pixel a pixel
        matriz_cinza[i][j] = 0.3 * matriz_vermelha[i][j] + 0.59 * matriz_verde[i][j] + 0.11 * matriz_azul[i][j]
#impressão da matriz tons de cinza
print('Matriz em tons de cinza')
print(matriz_cinza)

limiar = limiar_isodata(matriz_cinza, 128, 100, 1)

print('O limiar isodata calculado a partir da matriz de cinzas foi de {}'.format(limiar))

# gera uma matriz arrumando os valores de acordo com o limiar
matriz_limiar = np.zeros(tamanho_inicial)

for i in range(tamanho_inicial[0]):
    for j in range(tamanho_inicial[1]):
        # se for menor que o limiar vale 0 e se for maior vale 255
        if matriz_cinza[i][j] >= 0 and matriz_cinza[i][j] <= limiar:
            matriz_limiar[i][j] = 0
        else:
            matriz_limiar[i][j] = 255


# As linhas abaixo são para deixar as matrizes no formato da impressão da imagem, cada matriz gerada abaixo tera 3 valores por elemento sendo eles o valor do pixel de RGB

matriz_RGB = np.dstack((matriz_vermelha, matriz_verde, matriz_azul))
matriz_RGB = matriz_RGB.astype(np.uint8)

matriz_cinza = np.dstack((matriz_cinza, matriz_cinza, matriz_cinza))
matriz_cinza = matriz_cinza.astype(np.uint8)

matriz_limiar = np.dstack((matriz_limiar, matriz_limiar, matriz_limiar))
matriz_limiar = matriz_limiar.astype(np.uint8)


# Tentei imprimir usando cv2 porém as imagens estavam saindo um pouco estranhas e achei mais simples de entender utilizando a biblioteca matplotlib
fig, axs = plt.subplots(1, 3)
axs[0].set_title('Imagem de entrada')
axs[0].imshow(matriz_RGB)
axs[0].axis('off')

axs[1].set_title('Imagem em tons de cinza')
axs[1].imshow(matriz_cinza)
axs[1].axis('off')

axs[2].set_title('Imagem matriz limiar')
axs[2].imshow(matriz_limiar)
axs[2].axis('off')

plt.subplots_adjust(wspace = 1)

plt.show()



