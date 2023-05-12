from PIL import Image


def rgb2yiq(rgb):
    return (
        rgb[0] * 0.299 + rgb[1] * 0.587 + rgb[2] * 0.114,
        rgb[0] * 0.59590059 - rgb[1] * 0.27455667 - rgb[2] * 0.32134392,
        rgb[0] * 0.21153661 - rgb[1] * 0.52273617 + rgb[2] * 0.31119955,
    )

def yiq2rgb(yiq):
    return (
        yiq[0] * 1 + yiq[1] * 0.956 - yiq[2] * 0.621,
        yiq[0] * 1 - yiq[1] * 0.272 - yiq[2] * 0.647,
        yiq[0] * 1 - yiq[1] * 1.106 + yiq[2] * 1.703,
    )


def norm(xyz, max):
    return (xyz[0] / max, xyz[1] / max, xyz[2] / max)


IMGS = ["outono_LC.png", "predios.jpeg"]

for imgpath in IMGS:
    # Abrir imagem
    print(f"IMAGEM: {imgpath}")
    img = Image.open(imgpath)

    # Adicionar moldura à imagem
    img_padded = Image.new("RGB", (img.width + 2, img.height + 2))
    img_padded.paste(img, (1, 1))

    # Aplicar equalizacao utilizando janelas deslizantes
    img_eq = Image.new("RGB", (img.width, img.height))
    hist = [0.0] * 256
    for x in range(1, img_padded.width - 1):
        for y in range(1, img_padded.height - 1):
            pix = img_padded.getpixel((x, y))
            pix_norm = norm(pix, 255)
            pix_y = int(rgb2yiq(pix_norm)[0] * 255)
            hist[pix_y] += 1.0

    # Ajustar valores do histograma para [0,1]
    hist = [h/(img.width*img.height) for h in hist]
    for i in range(1, 256):
        hist[i] = hist[i] + hist[i - 1]
    mapeamento = [round(h*255) for h in hist]

    for x in range(img.width):
        for y in range(img.height):
            pix = img.getpixel((x, y))
            pix_norm = norm(pix, 255)
            pix_y = int(rgb2yiq(pix_norm)[0] * 255)
            pix_y_eq = mapeamento[pix_y]
            pix_yiq_eq = (pix_y_eq / 255, rgb2yiq(pix_norm)[1], rgb2yiq(pix_norm)[2])
            pix_rgb_eq = yiq2rgb(pix_yiq_eq)
            pix_rgb_eq_norm = norm(pix_rgb_eq, 1)
            pix_rgb_eq_int = (
                int(pix_rgb_eq_norm[0] * 255),
                int(pix_rgb_eq_norm[1] * 255),
                int(pix_rgb_eq_norm[2] * 255)
            )
            img_eq.putpixel((x, y), pix_rgb_eq_int)

    img_eq.save("./imgQ3/3/" + imgpath[:-4] + "-eq-yiq.png") #remove a extensão do arquivo da string
