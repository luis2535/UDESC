import numpy as np

# Plano de imagem
d = 5

# Coordenadas 3D dos pontos identificados no objeto (Figura).
pontos3D = np.array([[650.7, 2000, 1500, 1],
                     [653.5, 2000, 1500, 1],
                     [650.7, 1990, 1500, 1],
                     [645.3, 500.3, 1500, 1],
                     [645.0, 500.3, 1500, 1],
                     [645.3, 500.0, 1500, 1]
                     ])


# Coordenadas do centro do sensor
Oxy = 1024

# Dimensões de pixel
Sxy = 0.0075

p1 = np.array([[650.7],
              [2000],
              [1500],
              [1]])
# matriz projeção
matriz_projecao = np.array([[1,0,0,0],
                   [0,1,0,0],
                   [0,0,1/d,0]])

# M'
matriz_linha = np.array([[1/Sxy,0,Oxy/d,0],
                   [0,-1/Sxy,Oxy/d,0],
                   [0,0,1/d,0]])

p1 = np.dot(matriz_linha, pontos3D[0])
p2 = np.dot(matriz_linha, pontos3D[1])
p3 = np.dot(matriz_linha, pontos3D[2])
p4 = np.dot(matriz_linha, pontos3D[3])
p5 = np.dot(matriz_linha, pontos3D[4])
p6 = np.dot(matriz_linha, pontos3D[5])

Qh = np.array([[p1],
                       [p2],
                       [p3],
                       [p4],
                       [p5],
                       [p6]])
print('Valores dos pontos Qh:')
print(Qh)

qp1 = [p1[0] * d/p1[2], p1[1] * d/p1[2], 1]
qp2 = [p2[0] * d/p2[2], p2[1] * d/p2[2], 1]
qp3 = [p3[0] * d/p3[2], p3[1] * d/p3[2], 1]
qp4 = [p4[0] * d/p4[2], p4[1] * d/p4[2], 1]
qp5 = [p5[0] * d/p5[2], p5[1] * d/p5[2], 1]
qp6 = [p6[0] * d/p6[2], p6[1] * d/p6[2], 1]

Qp = np.array([[qp1],
               [qp2],
               [qp3],
               [qp4],
               [qp5],
               [qp6]
               ])
print('Valores dos pontos Qp:')
print(Qp)