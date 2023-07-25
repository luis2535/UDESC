import numpy as np
from PIL import Image
import os

def filtro_media(img, tamanho):
    altura, largura = img.shape[:2]
    #definindo tamanho correto para os pads
    pads = int((tamanho - 1)/2)
    bordas = np.pad(img, ((pads, pads), (pads, pads)), mode = 'constant')
    img_filtro = np.zeros_like(img, dtype = np.uint8)
    #criação de matriz para calculos
    kernel = np.ones((tamanho, tamanho)) / (tamanho * tamanho)
    
    #janela deslizantes
    for i in range(pads, altura):
        for j in range(pads, largura):
            #isolar a area para fazer o somatorio
            area = bordas[i - pads: i + pads + 1, j - pads: j + pads + 1]            
            resultado = np.sum(area * kernel)
            img_filtro[i-pads, j-pads] = resultado
    return img_filtro




img = np.array(Image.open('imgs/Lua1_gray.jpg').convert('L'))

img_filtro3 = filtro_media(img, 3)
img_filtro5 = filtro_media(img, 5)

imagem_filtrada3 = Image.fromarray(img_filtro3)

imagem_filtrada5 = Image.fromarray(img_filtro5)

diretorio_destino = "questao1"


imagem_filtrada3.save(os.path.join(diretorio_destino, "img_filtrada3.jpg"))
imagem_filtrada5.save(os.path.join(diretorio_destino, "img_filtrada5.jpg"))

print("Imagens salvas")