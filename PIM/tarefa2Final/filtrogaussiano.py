import numpy as np
from PIL import Image

def calcular_nucleo_gaussiano(x, y, sigma):
    coeficiente = 1 / (2 * np.pi * sigma ** 2)
    exponente = -((x ** 2 + y ** 2) / (2 * sigma ** 2))
    return coeficiente * np.exp(exponente)

def gerar_nucleo_gaussiano(matriz1, matriz2, sigma):
    tamanho = 3
    nucleo = np.zeros((tamanho, tamanho))
    for i in range(tamanho):
        for j in range(tamanho):
            nucleo[i, j] = calcular_nucleo_gaussiano(matriz1[i][j], matriz2[i][j], sigma)
    return nucleo

def normalizar_nucleo(nucleo):
    return nucleo / np.sum(nucleo)

def aplicar_filtro_gaussiano(img, nucleo, tamanho):
    altura, largura = img.shape[:2]
    pads = int((tamanho - 1)/2)
    bordas = np.pad(img, ((pads, pads), (pads, pads)), mode = 'constant')
    imagem_filtrada = np.zeros_like(img)
    for i in range(pads, altura):
        for j in range(pads, largura):
            area = bordas[i - pads: i + pads + 1, j - pads: j + pads + 1]
            resultado = np.sum(area * nucleo)
            imagem_filtrada[i-pads, j-pads] = resultado
    return imagem_filtrada

imagem = np.array(Image.open('imgs/Lua1_gray.jpg').convert('L'))

matriz_bordas_horizontais = np.array([[-1, -1, -1],
                                      [ 0,  0,  0],
                                      [ 1,  1,  1]])

matriz_bordas_verticais = np.array([[-1,  0,  1],
                                    [-1,  0,  1],
                                    [-1,  0,  1]])

sigma = 0.6
nucleo = gerar_nucleo_gaussiano(matriz_bordas_verticais, matriz_bordas_horizontais, sigma)
nucleo_normalizado = normalizar_nucleo(nucleo)

imagem_filtrada_gaussiano = aplicar_filtro_gaussiano(imagem, nucleo_normalizado, 3)

imagem_filtrada = Image.fromarray(imagem_filtrada_gaussiano)
imagem_filtrada.save('questao1/Lua1_filtrada_gaussiano_sigma0.6.jpg')

sigma = 1.0
nucleo = gerar_nucleo_gaussiano(matriz_bordas_verticais, matriz_bordas_horizontais, sigma)
nucleo_normalizado = normalizar_nucleo(nucleo)

imagem_filtrada_gaussiano = aplicar_filtro_gaussiano(imagem, nucleo_normalizado, 3)

imagem_filtrada = Image.fromarray(imagem_filtrada_gaussiano)
imagem_filtrada.save('questao1/Lua1_filtrada_gaussiano_sigma1.0.jpg')

