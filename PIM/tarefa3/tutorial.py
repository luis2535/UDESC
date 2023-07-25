import numpy as np
import matplotlib.pyplot as plt
from skimage.io import imread, imshow
import cv2
from skimage.color import rgb2hsv, rgb2gray, rgb2yuv
from skimage import color, exposure, transform
from skimage.exposure import equalize_hist
from skimage.metrics import structural_similarity as ssim
from skimage.metrics import mean_squared_error

dark_image_grey = imread('imgs/folhas1_Reticulada.png')

# plt.figure(num=None, figsize=(8, 6), dpi=80)
# plt.imshow(dark_image_grey, cmap='gray')

# Pré-processamento da imagem em escala de cinza
dark_image_grey = exposure.equalize_hist(dark_image_grey)

dark_image_grey_fourier = np.fft.fftshift(np.fft.fft2(dark_image_grey))
# plt.figure(num=None, figsize=(8, 6), dpi=80)
# plt.imshow(np.log(abs(dark_image_grey_fourier)), cmap='gray')

def fourier_masker_ver(image, i):
    f_size = 15
    dark_image_grey_fourier = np.fft.fftshift(np.fft.fft2(image))    
    center_y = dark_image_grey.shape[0] // 2
    
    # Remoção do reticulado vertical
    mask = np.ones_like(dark_image_grey_fourier)
    mask[center_y - 2:center_y + 3, :] = 0
    dark_image_grey_fourier *= mask
    
    fig, ax = plt.subplots(1, 4, figsize=(15, 15))
    ax[0].imshow(np.log(abs(dark_image_grey_fourier)), cmap='gray')
    ax[0].set_title('Masked Fourier', fontsize=f_size)
    ax[1].imshow(image, cmap='gray')
    ax[1].set_title('Greyscale Image', fontsize=f_size)
    ax[2].imshow(abs(np.fft.ifft2(dark_image_grey_fourier)), cmap='gray')
    ax[2].set_title('Transformed Greyscale Image', fontsize=f_size)
    
    # Inversão da transformada de Fourier
    image_restored = np.fft.ifft2(np.fft.ifftshift(dark_image_grey_fourier)).real
    ax[3].imshow(image_restored, cmap='gray')
    ax[3].set_title('Imagem Restaurada', fontsize=f_size)
    return image_restored
img_back = fourier_masker_ver(dark_image_grey, 1)

img_back_normalized = (img_back - img_back.min()) / (img_back.max() - img_back.min())
img_back_normalized = (img_back_normalized * 255).astype(np.uint8)

cv2.imwrite('img_recuperada1.png', img_back_normalized)

plt.show()

img_original = cv2.imread('imgs/folhas1.jpg', 0)

# Calcular a similaridade estrutural (SSIM)
ssim_score = ssim(img_original, img_back_normalized, data_range=img_original.max() - img_original.min())

# Calcular o erro médio quadrado (MSE)
mse_score = mean_squared_error(img_original, img_back_normalized)

# Exibir as métricas
print(f"Similaridade Estrutural (SSIM): {ssim_score}")
print(f"Erro Médio Quadrado (MSE): {mse_score}")
