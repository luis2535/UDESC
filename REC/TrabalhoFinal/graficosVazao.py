import re

import matplotlib.pyplot as plt

def extrair_transfer(arquivo):
    # Usando expressões regulares para extrair os valores de Transfer
    with open(arquivo, 'r') as file:
        data = file.read()
    pattern = r'\d+\.\d+\-\d+\.\d+ sec\s+(\d+\.\d+)\s+MBytes'
    matches = re.findall(pattern, data)
    valores = [float(value) for value in matches]
    return valores


def extrair_transfer_sum(arquivo, tamanho):
    with open(arquivo, 'r') as file:
        data = file.read()
    pattern = r'\[\s*SUM\s*\]\s+\d+\.\d+\-\d+\.\d+ sec\s+(\d+\.\d+)\s+MBytes'
    matches = re.findall(pattern, data)
    valores = [float(value)/tamanho for value in matches]
    return valores



def plotar_grafico(valores_cubic, valores_reno, titulo):
    plt.clf() 
    eixo_x_cubic = range(1, len(valores_cubic) + 1)
    eixo_x_reno = range(1, len(valores_reno) + 1)
    plt.plot(eixo_x_cubic, valores_cubic, label='TCP Cubic')
    plt.plot(eixo_x_reno, valores_reno, label='TCP Reno')
    plt.xlabel('Tempo (segundos)')
    plt.ylabel('Vazão Real')
    plt.title(titulo)
    plt.legend()
    nome_arquivo = titulo.lower().replace(' ', '_').replace('(', '').replace(')', '').replace(':', '')

    plt.savefig(f'graficoVazao/{nome_arquivo}.png')




############################################## Latência 20ms #######################################################

arquivo_cubic = 'TF/clienteCubic.txt'
valores_cubic = extrair_transfer(arquivo_cubic)

arquivo_reno = 'TF/clienteReno.txt'
valores_reno = extrair_transfer(arquivo_reno)

titulo = 'TCP Cubic e TCP Reno 1 Processo Latência 20ms Vazão real'
plotar_grafico(valores_cubic, valores_reno, titulo)

arquivo_cubic = 'TF/clienteCubic5.txt'
valores_cubic = extrair_transfer_sum(arquivo_cubic, 5)

arquivo_reno = 'TF/clienteReno5.txt'
valores_reno = extrair_transfer_sum(arquivo_reno, 5)

titulo = 'TCP Cubic e TCP Reno 5 Processos Latência 20ms Vazão real'
plotar_grafico(valores_cubic, valores_reno, titulo)


arquivo_cubic = 'TF/clienteCubic10.txt'
valores_cubic = extrair_transfer_sum(arquivo_cubic, 10)

arquivo_reno = 'TF/clienteReno10.txt'
valores_reno = extrair_transfer_sum(arquivo_reno, 10)

titulo = 'TCP Cubic e TCP Reno 10 Processos Latência 20ms Vazão real'
plotar_grafico(valores_cubic, valores_reno, titulo)


############################################## Latência 220ms #######################################################

arquivo_cubic = 'TF/clienteCubiclat200.txt'
valores_cubic = extrair_transfer(arquivo_cubic)

arquivo_reno = 'TF/clienteRenolat200.txt'
valores_reno = extrair_transfer(arquivo_reno)

titulo = 'TCP Cubic e TCP Reno 1 Processo Latência 220ms Vazão real'
plotar_grafico(valores_cubic, valores_reno, titulo)

arquivo_cubic = 'TF/clienteCubic5lat200.txt'
valores_cubic = extrair_transfer_sum(arquivo_cubic, 5)

arquivo_reno = 'TF/clienteReno5lat200.txt'
valores_reno = extrair_transfer_sum(arquivo_reno, 5)

titulo = 'TCP Cubic e TCP Reno 5 Processos Latência 220ms Vazão real'
plotar_grafico(valores_cubic, valores_reno, titulo)


arquivo_cubic = 'TF/clienteCubic10lat200.txt'
valores_cubic = extrair_transfer_sum(arquivo_cubic, 10)

arquivo_reno = 'TF/clienteReno10lat200.txt'
valores_reno = extrair_transfer_sum(arquivo_reno, 10)

titulo = 'TCP Cubic e TCP Reno 10 Processos Latência 220ms Vazão real'
plotar_grafico(valores_cubic, valores_reno, titulo)
