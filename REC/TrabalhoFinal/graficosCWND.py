import matplotlib.pyplot as plt

def carregar_arquivo(nome_arquivo):
    valores = []
    with open(nome_arquivo, 'r') as arquivo:
        for linha in arquivo:
            valor = linha.split(':')[1].strip()
            valores.append(int(valor))
    return valores

def plotar_grafico(valores_cubic, valores_reno, titulo):
    plt.clf()
    eixo_x_cubic = range(1, len(valores_cubic) + 1)
    eixo_x_reno = range(1, len(valores_reno) + 1)
    plt.plot(eixo_x_cubic, valores_cubic, label='TCP Cubic')
    plt.plot(eixo_x_reno, valores_reno, label='TCP Reno')
    plt.xlabel('Tempo (segundos)')
    plt.ylabel('cwnd')
    plt.title(titulo)
    plt.legend()
    nome_arquivo = titulo.lower().replace(' ', '_').replace('(', '').replace(')', '').replace(':', '')

    plt.savefig(f'graficoCWND/{nome_arquivo}.png')


def calcular_media_por_grupo(valores, tamanho):
    media_por_grupo = []
    grupo = []
    contador = 0

    for valor in valores:
        grupo.append(valor)
        contador += 1

        if contador == tamanho:
            media_grupo = sum(grupo) / len(grupo)
            media_por_grupo.append(media_grupo)
            grupo = []
            contador = 0

    return media_por_grupo



############################################## Latência 20ms #######################################################

arquivo_cubic = 'TF/relatorioCubic1.txt'
valores_cubic = carregar_arquivo(arquivo_cubic)

arquivo_reno = 'TF/relatorioReno1.txt'
valores_reno = carregar_arquivo(arquivo_reno)

titulo = 'TCP Cubic e TCP Reno 1 Processo Latência 20ms'
plotar_grafico(valores_cubic, valores_reno, titulo)

arquivo_cubic = 'TF/relatorioCubic5lat75.txt'
valores_cubic = carregar_arquivo(arquivo_cubic)
array_cubic = calcular_media_por_grupo(valores_cubic, 5)

arquivo_reno = 'TF/relatorioReno5.txt'
valores_reno = carregar_arquivo(arquivo_reno)
array_reno = calcular_media_por_grupo(valores_reno, 5)

titulo = 'TCP Cubic e TCP Reno 5 Processos Latência 20ms'
plotar_grafico(array_cubic, array_reno, titulo)


arquivo_cubic = 'TF/relatorioCubic10.txt'
valores_cubic = carregar_arquivo(arquivo_cubic)
array_cubic = calcular_media_por_grupo(valores_cubic, 10)

arquivo_reno = 'TF/relatorioReno10.txt'
valores_reno = carregar_arquivo(arquivo_reno)
array_reno = calcular_media_por_grupo(valores_reno, 10)

titulo = 'TCP Cubic e TCP Reno 10 Processos Latência 20ms'
plotar_grafico(array_cubic, array_reno, titulo)



############################################## Latência 220ms #######################################################


arquivo_cubic = 'TF/relatorioCubic1lat200.txt'
valores_cubic = carregar_arquivo(arquivo_cubic)

arquivo_reno = 'TF/relatorioReno1lat200.txt'
valores_reno = carregar_arquivo(arquivo_reno)

titulo = 'TCP Cubic e TCP Reno 1 Processo Latência 220ms'
plotar_grafico(valores_cubic, valores_reno, titulo)

arquivo_cubic = 'TF/relatorioCubic5lat200.txt'
valores_cubic = carregar_arquivo(arquivo_cubic)
array_cubic = calcular_media_por_grupo(valores_cubic, 5)

arquivo_reno = 'TF/relatorioReno5lat200.txt'
valores_reno = carregar_arquivo(arquivo_reno)
array_reno = calcular_media_por_grupo(valores_reno, 5)

titulo = 'TCP Cubic e TCP Reno 5 Processos Latência 220ms'
plotar_grafico(array_cubic, array_reno, titulo)


arquivo_cubic = 'TF/relatorioCubic10lat200.txt'
valores_cubic = carregar_arquivo(arquivo_cubic)
array_cubic = calcular_media_por_grupo(valores_cubic, 10)

arquivo_reno = 'TF/relatorioReno10lat200.txt'
valores_reno = carregar_arquivo(arquivo_reno)
array_reno = calcular_media_por_grupo(valores_reno, 10)

titulo = 'TCP Cubic e TCP Reno 10 Processos Latência 220ms'
plotar_grafico(array_cubic, array_reno, titulo)


