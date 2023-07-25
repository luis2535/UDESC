import numpy as np
import matplotlib.pyplot as plt
from skimage.io import imread
import cv2
from skimage.color import rgb2gray
from skimage import exposure
from skimage.metrics import structural_similarity as ssim
from skimage.metrics import mean_squared_error

dark_image_grey = imread('imgs/folhas1_Reticulada.png')
dark_image_grey_fourier = np.fft.fftshift(np.fft.fft2(dark_image_grey))
plt.figure(num=None, figsize=(8, 6), dpi=80)
plt.imshow(np.log(abs(dark_image_grey_fourier)), cmap='gray')

def fourier_masker(image):
    f_size = 15
    dark_image_grey_fourier = np.fft.fftshift(np.fft.fft2(image))
    center_x = dark_image_grey.shape[1] // 2
    center_y = dark_image_grey.shape[0] // 2
    
    # Remoção do reticulado vertical
    mask_ver = np.ones_like(dark_image_grey_fourier)
    mask_ver[center_y - 1:center_y + 1, :] = 0
    mask_ver[center_y - 20:center_y + 20, center_x-40:center_x+40] = 1
    
    # Remoção do reticulado horizontal
    mask_hor = np.ones_like(dark_image_grey_fourier)
    mask_hor[:, center_x - 1:center_x + 1] = 0
    mask_hor[center_y-40:center_y+40, center_x-40:center_x+40] = 1
    
    # Aplicar as máscaras
    dark_image_grey_fourier *= mask_ver
    dark_image_grey_fourier *= mask_hor
    
    fig, ax = plt.subplots(1, 4, figsize=(15, 15))
    ax[0].imshow(np.log(np.abs(dark_image_grey_fourier)), cmap='gray')
    ax[0].set_title('Masked Fourier', fontsize=f_size)
    ax[1].imshow(image, cmap='gray')
    ax[1].set_title('Greyscale Image', fontsize=f_size)
    ax[2].imshow(np.abs(np.fft.ifft2(dark_image_grey_fourier)), cmap='gray')
    ax[2].set_title('Transformed Greyscale Image', fontsize=f_size)
    
    # Inversão da transformada de Fourier
    image_restored = np.fft.ifft2(np.fft.ifftshift(dark_image_grey_fourier)).real
    ax[3].imshow(image_restored, cmap='gray')
    ax[3].set_title('Imagem Restaurada', fontsize=f_size)
    
    return image_restored


img_back = fourier_masker(dark_image_grey)

img_back_normalized = exposure.rescale_intensity(img_back, out_range=(0, 255)).astype(np.uint8)

cv2.imwrite('img_recuperada_horizontal_vertical.png', img_back_normalized)

img_original = cv2.imread('imgs/folhas1.jpg', 0)

# Calcular a similaridade estrutural (SSIM)
ssim_score = ssim(img_original, img_back_normalized, data_range=img_original.max() - img_original.min())

# Calcular o erro médio quadrado (MSE)
mse_score = mean_squared_error(img_original, img_back_normalized)

# Exibir as métricas
print(f"Similaridade Estrutural (SSIM) - Horizontal e Vertical: {ssim_score}")
print(f"Erro Médio Quadrado (MSE) - Horizontal e Vertical: {mse_score}")

plt.show()
