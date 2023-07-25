import numpy as np
from PIL import Image

def calcular_nucleo_gaussiano(x, y, sigma):
    coeficiente = 1 / (2 * np.pi * sigma ** 2)
    exponente = -((x ** 2 + y ** 2) / (2 * sigma ** 2))
    return coeficiente * np.exp(exponente)

def gerar_nucleo_gaussiano(matriz1, matriz2, sigma, tamanho):
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
    for i in range(pads, altura+pads):  # Ajuste na dimensão da iteração
        for j in range(pads, largura+pads):  # Ajuste na dimensão da iteração
            area = bordas[i - pads: i + pads + 1, j - pads: j + pads + 1]
            resultado = np.sum(area * nucleo)
            imagem_filtrada[i-pads, j-pads] = resultado
    return imagem_filtrada


imagem = np.array(Image.open('imgs/Lua1_gray.jpg').convert('L'))

# Matriz de bordas horizontais
tamanho = int(input("tamanho: "))
matriz_bordas_horizontais = np.ones((tamanho, tamanho))
matriz_bordas_horizontais[:, :int(tamanho/2)] = -1
matriz_bordas_horizontais[:, int(tamanho/2):] = 0

# Matriz de bordas verticais
matriz_bordas_verticais = np.ones((tamanho, tamanho))
matriz_bordas_verticais[:int(tamanho/2), :] = -1
matriz_bordas_verticais[int(tamanho/2):, :] = 0

sigma = 120
nucleo = gerar_nucleo_gaussiano(matriz_bordas_verticais, matriz_bordas_horizontais, sigma, tamanho - 1)

nucleo_normalizado = normalizar_nucleo(nucleo)

print("\nGerando imagem 1\n")
imagem_filtrada_gaussiano = aplicar_filtro_gaussiano(imagem, nucleo_normalizado, tamanho)
imagem_filtrada06 = Image.fromarray(imagem_filtrada_gaussiano)
imagem_filtrada06.save('teste/Lua1_filtrada_gaussiano_sigma0.6.jpg')

sigma = 255
nucleo = gerar_nucleo_gaussiano(matriz_bordas_verticais, matriz_bordas_horizontais, sigma, tamanho - 1)

nucleo_normalizado = normalizar_nucleo(nucleo)
print("\nGerando imagem 2\n")
imagem_filtrada_gaussiano = aplicar_filtro_gaussiano(imagem, nucleo_normalizado, tamanho)

imagem_filtrada1 = Image.fromarray(imagem_filtrada_gaussiano)
imagem_filtrada1.save('teste/Lua1_filtrada_gaussiano_sigma1.0.jpg')

imagem_filtrada06.show()
imagem_filtrada1.show()
print("\nFinalizado!\n")
