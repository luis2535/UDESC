from PIL import Image

IMGS = ["outono_LC.png", "predios.jpeg"]

for imgpath in IMGS:
    # Abrir imagem
    print(f"IMAGEM: {imgpath}")
    img = Image.open(imgpath)

    # Adicionar moldura à imagem
    img_padded = Image.new("RGB", (img.width + 2, img.height + 2))
    img_padded.paste(img, (1, 1))

    # Aplicar equalizacao utilizando janelas deslizantes
    eq_img = Image.new("RGB", (img.width, img.height))
    for channel_i, canal in enumerate(img_padded.split()):
        hist = [0.0] * 256
        for x in range(1, img_padded.width - 1):
            for y in range(1, img_padded.height - 1):
                pixel = canal.getpixel((x, y))
                hist[pixel] += 1.0
        # Ajustar valores do histograma para [0,1]
        hist = [h/(img.width*img.height) for h in hist]
        for i in range(1, 256):
            hist[i] = hist[i] + hist[i - 1]
        mapeamento = [round(h*255) for h in hist]
        for x in range(img.width):
            for y in range(img.height):
                cor = list(eq_img.getpixel((x, y)))
                cor[channel_i] = mapeamento[canal.getpixel((x+1, y+1))]
                eq_img.putpixel((x, y), tuple(cor))

    eq_img.save("./imgQ3/1/" + imgpath + "-eq-rgb.png")
