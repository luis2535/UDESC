with open('dados.txt', 'r') as file:
    dados = []
    for linha in file:
        if not linha.startswith('#'):
            div = linha.strip().split()
            if len(div) >= 2:
                x = float(div[0].replace(',', '.'))
                y = float(div[1].replace(',', '.'))

                dados.append((x,y))


for dado in dados:
    print(dado)

print(len(dados))