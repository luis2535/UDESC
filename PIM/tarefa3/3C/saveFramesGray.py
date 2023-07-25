import cv2

vidcap = cv2.VideoCapture('video/cachorro.mp4')
success, image = vidcap.read()
count = 0
while success:
    gray_image = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)  # Convertendo para escala de cinza
    cv2.imwrite('frames2/frame%d.jpg' % count, gray_image)
    success, image = vidcap.read()
    print('Read a new frame: ', success)
    count += 1
