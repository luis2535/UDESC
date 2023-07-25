import cv2
import numpy as np

# Carregar a primeira imagem
im1 = cv2.imread('frames/frame0.jpg')
num_frames = 258
# Definir as coordenadas da ROI
x = 10
y = 135
width = 76
height = 65

methods = [
    # cv2.TM_CCOEFF,
    # cv2.TM_CCOEFF_NORMED,
    # cv2.TM_CCORR,
    # cv2.TM_CCORR_NORMED,
    # cv2.TM_SQDIFF,
    cv2.TM_SQDIFF_NORMED
]

# Obter o template a partir da ROI
template = im1[y:y+height, x:x+width]

detection_values = []
# Criação do objeto de rastreamento usando o método cv2.TM_SQDIFF_NORMED
for method in methods:
    # Carregar a primeira imagem
    im1 = cv2.imread('frames/frame0.jpg')

    # Obter o template a partir da ROI
    template = im1[y:y+height, x:x+width]

    # Inicializar o gravador de vídeo para a identificação do objeto rastreado
    output_video_tracking = cv2.VideoWriter('output_tracking.avi', cv2.VideoWriter_fourcc(*'MJPG'), 30, (im1.shape[1], im1.shape[0]))

    # Inicializar o gravador de vídeo para a imagem dentro do retângulo de matching
    output_video_matching = cv2.VideoWriter('output_matching.avi', cv2.VideoWriter_fourcc(*'MJPG'), 30, (width, height))

    for frame_number in range(1, num_frames):
        # Carregar o próximo frame
        frame = cv2.imread('frames/frame{}.jpg'.format(frame_number))

        # Aplicar o método de casamento de template atual
        result = cv2.matchTemplate(frame, template, method)

        # Encontrar a posição do melhor casamento
        min_val, max_val, minLoc, max_loc = cv2.minMaxLoc(result)
        # Adicionar os valores máximo e mínimo à lista
        detection_values.append((min_val, max_val))

        # Desenhar a caixa delimitadora no frame
        x, y = minLoc
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

# Fechar as janelas abertas
cv2.destroyAllWindows()
