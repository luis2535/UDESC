import numpy as np
import cv2


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


print('Após os cálculos, temos que o valor RGB de cada pixel é:')
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

# prints das matrizes de saida
print('Matriz Vermelha:')
print(matriz_vermelha)
print('Matriz Verde:')
print(matriz_verde)
print('Matriz Azul:')
print(matriz_azul)    