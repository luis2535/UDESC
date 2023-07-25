import cv2

# Carregar a primeira imagem
im1 = cv2.imread('frames2/frame0.jpg')
num_frames = 445
# Definir as coordenadas da ROI
x = 400
y = 47
width = 202
height = 101

methods = [
    cv2.TM_CCOEFF
    # cv2.TM_CCOEFF_NORMED,
    # cv2.TM_CCORR,
    # cv2.TM_CCORR_NORMED,
    # cv2.TM_SQDIFF,
    # cv2.TM_SQDIFF_NORMED
]

# Obter o template a partir da ROI
template = im1[y:y+height, x:x+width]

detection_values = []
threshold = 0.1  # Definir o threshold para considerar uma correspondência válida

# Criação do objeto de rastreamento usando o método cv2.TM_SQDIFF_NORMED
for method in methods:
    # Carregar a primeira imagem
    im1 = cv2.imread('frames2/frame0.jpg')

    # Obter o template a partir da ROI
    template = im1[y:y+height, x:x+width]

    # Inicializar o gravador de vídeo para a identificação do objeto rastreado
    output_video_tracking = cv2.VideoWriter('tracking_cachorro.avi', cv2.VideoWriter_fourcc(*'MJPG'), 30, (im1.shape[1], im1.shape[0]))

    # Inicializar o gravador de vídeo para a imagem dentro do retângulo de matching
    output_video_matching = cv2.VideoWriter('matching_cachorro.avi', cv2.VideoWriter_fourcc(*'MJPG'), 30, (width, height))

    for frame_number in range(1, num_frames):
        # Carregar o próximo frame
        frame = cv2.imread('frames2/frame{}.jpg'.format(frame_number))

        # Aplicar o método de casamento de template atual
        result = cv2.matchTemplate(frame, template, method)

        # Encontrar a posição do melhor casamento
        min_val, max_val, minLoc, max_loc = cv2.minMaxLoc(result)
        # Adicionar o valor máximo à lista
        detection_values.append((min_val, max_val))


        if max_val > threshold:
            # Desenhar a caixa delimitadora no frame
            _, _, min_loc, max_loc = cv2.minMaxLoc(result)
            x, y = max_loc
            cv2.rectangle(frame, (x, y), (x + width, y + height), (0, 255, 0), 2)
            output_video_tracking.write(frame)



        # Extrair a região dentro do retângulo de matching
        matching_region = frame[y:y+height, x:x+width]
        output_video_matching.write(matching_region)

        # Mostrar o frame com a caixa delimitadora
        cv2.imshow('Tracking', frame)
        cv2.waitKey(1)
        # Liberar recursos
    output_video_tracking.release()
    output_video_matching.release()
# Imprimir a tabela com os valores máximo e mínimo detectados
print("Detecção de casamento:")
print("Frame\t\tValor Mínimo\t\tValor Máximo")
for i, (min_val, max_val) in enumerate(detection_values):
    print(f"Frame {i+1}\t{min_val}\t\t{max_val}")


cv2.destroyAllWindows()
