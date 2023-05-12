import numpy as np

# inicializando os pontos para serem usados como parametros, a primeira linha são os pontos x, a segunda os pontos y e a terceira, no caso do 3d, os pontos z
ponto3d = np.array([[0.0, 600.0, 1000.0, 1000.0, 1000.0, 1000.0],
                     [1000.0, 1000.0, 800.0, 400.0, 1000.0, 800.0],
                     [200.0, 400.0, 400.0, 600.0, 600.0, 0.0]])
ponto2d = np.array([[5, 3, 2, 1, 2, 2],
                     [3, 4, 4, 2, 3, 5]])
# numero de pontos
tamanho = 6



matriz_a = np.zeros((2 * 6, 12))


for i in range(tamanho):
    matriz_a[2 * i] = np.array([ponto3d[0][i], ponto3d[1][i], ponto3d[2][i], 1, 0, 0, 0, 0, -ponto2d[0][i] * ponto3d[0][i], -ponto2d[0][i] * ponto3d[1][i], -ponto2d[0][i] * ponto3d[2][i], -ponto2d[0][i]])
    matriz_a[2 * i + 1] = np.array([0, 0, 0, 0, ponto3d[0][i], ponto3d[1][i], ponto3d[2][i], 1, -ponto2d[1][i] * ponto3d[0][i], -ponto2d[1][i] * ponto3d[1][i], -ponto2d[1][i] * ponto3d[2][i], -ponto2d[1][i]])

# a função a seguir é utilizada par conseguir a matriz transposta de A
_, _, transposta = np.linalg.svd(matriz_a)
# nessa linha a ultima linha da matriz transposta é dividida pelo seu ultimo valor
matriz_temp = transposta[-1, :] / transposta[-1, -1]
# nessa linha os 12 elementos iniciais da matriz P são extraidos, restando apenas uma matriz 3x4 que será a matriz de calibração
matriz_calibracao = matriz_temp[:12].reshape(3, 4)

# as linhas abaixo apresentam o calculo da acurácia
erro_total = 0
for i in range(tamanho):
    ponto_3d_homogeneo = np.array([ponto3d[0][i], ponto3d[1][i], ponto3d[2][i], 1])
    ponto_2d_estimado_homogeneo = matriz_calibracao @ ponto_3d_homogeneo
    ponto_2d_estimado = ponto_2d_estimado_homogeneo[:2] / ponto_2d_estimado_homogeneo[2]
    erro = np.linalg.norm([ponto2d[0][i], ponto2d[1][i]] - ponto_2d_estimado)
    erro_total += erro

acuracia = erro_total / tamanho
print("Matriz de Calibração:")
print(matriz_calibracao)
print("Acurácia da Matriz de Calibração:")
print(acuracia)
