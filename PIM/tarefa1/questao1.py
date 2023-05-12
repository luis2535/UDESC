from PIL import Image
import math
import utils

IMGS = ["figuraClara.jpg", "figuraEscura.jpg", "lena.pgm"]

for imgpath in IMGS:
    # Abrir imagem
    print(f"IMAGEM: {imgpath}")
    img = Image.open(imgpath)
    img = img.convert("L")

    # Calcular média
    n = img.width * img.height
    avg = 0
    for x in range(img.width):
        for y in range(img.height):
            avg += img.getpixel((x, y))
    avg /= n
    print(f"\tMÉDIA = {avg}")

    # Calcular variância
    var = 0
    for x in range(img.width):
        for y in range(img.height):
            var += (img.getpixel((x, y)) - avg) ** 2
    var /= n
    print(f"\tVARIÂNCIA = {var}")

    # Calcular entropia
    hist = img.histogram()
    ent = 0
    for i in range(256):
        pi = hist[i] / n
        if pi > 0:
            ent += pi * math.log2(pi)
    ent = -ent
    print(f"\tENTROPIA = {ent}")

    # Mostrar histograma
    utils.save_histogram(hist, f"./imgQ1/{imgpath}-hist.png")