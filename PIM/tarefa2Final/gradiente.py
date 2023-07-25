import numpy as np
from PIL import Image
import os



def filtro_mediana(image, tamanho):
    altura, largura = image.shape
    pad = tamanho // 2
    padded_image = np.pad(image, pad, mode='constant')
    imagem_filtrada = np.zeros_like(image)

    for i in range(pad, altura + pad):
        for j in range(pad, largura + pad):
            vizinhanca = padded_image[i-pad:i+pad+1, j-pad:j+pad+1]
            mediana = np.median(vizinhanca)
            imagem_filtrada[i-pad, j-pad] = mediana

    return imagem_filtrada

def convolucao(imagem, mascara):
    altura, largura = imagem.shape
    m, n = mascara.shape
    pad = (m - 1) // 2

    # Adiciona padding na imagem
    imagem_padded = np.pad(imagem, pad, mode='constant')

    # Aplica a convolução
    resultado = np.zeros_like(imagem)
    for i in range(altura):
        for j in range(largura):
            resultado[i, j] = np.sum(imagem_padded[i:i+m, j:j+n] * mascara)

    return resultado


def calculo_magnitude(gx, gy):
    return np.sqrt(gx ** 2 + gy ** 2)


def selecao_maximos_locais(direcao, magnitude):
    altura, largura = direcao.shape
    max_locais = np.zeros_like(magnitude)

    for i in range(1, altura - 1):
        for j in range(1, largura - 1):
            angulo = direcao[i, j]

            if (22.5 < angulo <= 67.5):
                Y = magnitude[i - 1, j + 1]
                X = magnitude[i + 1, j - 1]
            elif (67.5 < angulo <= 112.5):
                Y = magnitude[i - 1, j]
                X = magnitude[i + 1, j]
            elif (112.5 < angulo <= 157.5):
                Y = magnitude[i - 1, j - 1]
                X = magnitude[i + 1, j + 1]
            elif (-22.5 < angulo <= 22.5):
                Y = magnitude[i, j - 1]
                X = magnitude[i, j + 1]
            elif (-67.5 < angulo <= -22.5):
                Y = magnitude[i - 1, j - 1]
                X = magnitude[i + 1, j + 1]
            else:
                Y = magnitude[i - 1, j - 1]
                X = magnitude[i + 1, j + 1]

            if magnitude[i, j] >= Y and magnitude[i, j] >= X:
                max_locais[i, j] = magnitude[i, j]

    return max_locais


def calculo_direcao(gx, gy):
    direcao = np.arctan2(gy, gx)
    return direcao

def aplicar_filtros(imagem, nome_arquivo):
    # Normalizar a imagem
    imagem_normalizada = (imagem - np.min(imagem)) / (np.max(imagem) - np.min(imagem)) * 255

    # Filtro passa-baixa (blur) usando um filtro de média ponderada
    imagem_filtrada = filtro_mediana(imagem, 3)
    # Operador de Sobel
    sobel_x = np.array([[-1, 0, 1],
                        [-2, 0, 2],
                        [-1, 0, 1]])

    sobel_y = np.array([[-1, -2, -1],
                        [0, 0, 0],
                        [1, 2, 1]])

    # Operador de Prewitt
    prewitt_x = np.array([[-1, 0, 1],
                          [-1, 0, 1],
                          [-1, 0, 1]])

    prewitt_y = np.array([[-1, -1, -1],
                          [0, 0, 0],
                          [1, 1, 1]])

    # Operador de Scharr
    scharr_x = np.array([[-3, 0, 3],
                         [-10, 0, 10],
                         [-3, 0, 3]])

    scharr_y = np.array([[-3, -10, -3],
                         [0, 0, 0],
                         [3, 10, 3]])

    # Aplicar convolução e cálculo de magnitude e direção para cada filtro
    gx_sobel = convolucao(imagem_filtrada, sobel_x)
    gy_sobel = convolucao(imagem_filtrada, sobel_y)
    magnitude_sobel = calculo_magnitude(gx_sobel, gy_sobel)
    direcao_sobel = calculo_direcao(gx_sobel, gy_sobel)

    gx_prewitt = convolucao(imagem_filtrada, prewitt_x)
    gy_prewitt = convolucao(imagem_filtrada, prewitt_y)
    magnitude_prewitt = calculo_magnitude(gx_prewitt, gy_prewitt)
    direcao_prewitt = calculo_direcao(gx_prewitt, gy_prewitt)

    gx_scharr = convolucao(imagem_filtrada, scharr_x)
    gy_scharr = convolucao(imagem_filtrada, scharr_y)
    magnitude_scharr = calculo_magnitude(gx_scharr, gy_scharr)
    direcao_scharr = calculo_direcao(gx_scharr, gy_scharr)

    # Seleção de máximos locais
    img_sobel = selecao_maximos_locais(direcao_sobel, magnitude_sobel)
    img_prewitt = selecao_maximos_locais(direcao_prewitt, magnitude_prewitt)
    img_scharr = selecao_maximos_locais(direcao_scharr, magnitude_scharr)

    # Conversão para tipo de dados uint8
    img_sobel = img_sobel.astype(np.uint8)
    img_prewitt = img_prewitt.astype(np.uint8)
    img_scharr = img_scharr.astype(np.uint8)

    # Salvar as imagens resultantes
    Image.fromarray(img_sobel).save(f'questao3/{nome_arquivo}_sobel.png')
    Image.fromarray(img_prewitt).save(f'questao3/{nome_arquivo}_prewitt.png')
    Image.fromarray(img_scharr).save(f'questao3/{nome_arquivo}_scharr.png')



# Carregar as imagens em tons de cinza
imagem1 = Image.open('imgs/11_test.png').convert('L')
imagem2 = Image.open('imgs/chessboard_inv.png').convert('L')
imagem3 = Image.open('imgs/Lua1_gray.jpg').convert('L')

# Converter as imagens para arrays numpy
imagem1_array = np.array(imagem1, dtype=np.float32)
imagem2_array = np.array(imagem2, dtype=np.float32)
imagem3_array = np.array(imagem3, dtype=np.float32)

# Aplicar os filtros e salvar as imagens resultantes
aplicar_filtros(imagem1_array, '11')
aplicar_filtros(imagem2_array, 'chessboard_inv')
aplicar_filtros(imagem3_array, 'Lua1_gray')
