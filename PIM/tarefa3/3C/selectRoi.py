import cv2

# Carregar a primeira imagem
im1 = cv2.imread('frames2/frame0.jpg')

# Exibir a primeira imagem e permitir a seleção da ROI
bbox = cv2.selectROI(im1)

# Extrair as coordenadas da ROI
x, y, width, height = bbox

# Imprimir as coordenadas da ROI
print('Coordenadas da ROI: x={}, y={}, width={}, height={}'.format(x, y, width, height))
