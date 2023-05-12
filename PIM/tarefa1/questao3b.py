from PIL import Image
from skimage import color


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
            pix_y = int(color.rgb2yiq(pix_norm)[0] * 255)
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
            pix_yiq = color.rgb2yiq(pix_norm)
            pix_y = int(pix_yiq[0] * 255)
            pix_y_eq = mapeamento[pix_y]
            pix_yiq_eq = (pix_y_eq / 255, pix_yiq[1], pix_yiq[2])
            pix_rgb_eq = color.yiq2rgb(pix_yiq_eq)
            pix_rgb_eq_norm = norm(pix_rgb_eq, 1)
            pix_rgb_eq_int = (
                int(pix_rgb_eq_norm[0] * 255),
                int(pix_rgb_eq_norm[1] * 255),
                int(pix_rgb_eq_norm[2] * 255)
            )
            img_eq.putpixel((x, y), pix_rgb_eq_int)

    img_eq.save("./imgQ3/2/" + imgpath + "-eq-yiq.png")
