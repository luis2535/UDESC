from PIL import Image
import utils

IMGS = ["xadrez_lowCont.png", "marilyn.jpg","figuraClara.jpg", "figuraEscura.jpg"]

for imgpath in IMGS:
    # Abrir imagem
    print(f"IMAGEM: {imgpath}")
    img = Image.open(imgpath)
    img = img.convert("L")

    # Adicionar moldura à imagem
    img_padded = Image.new("L", (img.width + 2, img.height + 2))
    img_padded.paste(img, (1, 1))

    # Aplicar equalizacao utilizando janelas deslizantes
    eq_img = Image.new("L", (img.width, img.height))
    for x in range(1, img_padded.width - 1):
        for y in range(1, img_padded.height - 1):
            hist_dict = {}
            for i in range(-1, 2):
                for j in range(-1, 2):
                    pixel = img_padded.getpixel((x + i, y + j))
                    if not pixel in hist_dict:
                        hist_dict[pixel] = 0
                    hist_dict[pixel] += 1.0
            hist_list_items = list(hist_dict.items())
            hist_list_items.sort(key=lambda x: x[0])
            hist = [x[1] for x in hist_list_items]
            hist[0] /= 9.0
            for i in range(1, len(hist)):
                hist[i] = hist[i] / 9.0 + hist[i - 1]
            mapeamento = {}
            for i, t in enumerate(hist_list_items):
                mapeamento[t[0]] = round(hist[i] * 255.0)
            eq_img.putpixel((x - 1, y - 1), mapeamento[img_padded.getpixel((x, y))])

    eq_img.save("./imgQ2/" +imgpath + "-eq.png")

    # Mostrar histograma
    utils.save_histogram(img.histogram(), f"./imgQ2/{imgpath}-hist.png")
    utils.save_histogram(eq_img.histogram(), f"./imgQ2/{imgpath}-eq-hist.png")