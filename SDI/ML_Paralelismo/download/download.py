import requests
from numpy import loadtxt
from xgboost import XGBClassifier
from sklearn.metrics import accuracy_score
import json

def download_files():
    # Download do arquivo do modelo
    model_url = 'http://192.168.1.20:5000/download/model.json'
    model_response = requests.get(model_url)

    if model_response.status_code == 200:
        with open('model_received.json', 'wb') as model_file:
            model_file.write(model_response.content)
        print("Arquivo do modelo baixado com sucesso.")
    else:
        print("Erro ao baixar o arquivo do modelo.")

    # Download do arquivo de dados de teste X
    X_test_url = 'http://192.168.1.20:5000/download/X_test.json'
    X_test_response = requests.get(X_test_url)

    if X_test_response.status_code == 200:
        with open('X_test_received.json', 'wb') as X_test_file:
            X_test_file.write(X_test_response.content)
        print("Arquivo de dados de teste X baixado com sucesso.")
    else:
        print("Erro ao baixar o arquivo de dados de teste X.")

    # Download do arquivo de dados de teste y
    y_test_url = 'http://192.168.1.20:5000/download/y_test.json'
    y_test_response = requests.get(y_test_url)

    if y_test_response.status_code == 200:
        with open('y_test_received.json', 'wb') as y_test_file:
            y_test_file.write(y_test_response.content)
        print("Arquivo de dados de teste y baixado com sucesso.")
    else:
        print("Erro ao baixar o arquivo de dados de teste y.")

# Chame a função para fazer o download dos arquivos
download_files()


# Carregar o modelo
loaded_model = XGBClassifier()
loaded_model.load_model('model_received.json')

# Carregar os dados de teste X
with open('X_test_received.json', 'r') as X_test_file:
    X_test = json.load(X_test_file)

# Carregar os dados de teste y
with open('y_test_received.json', 'r') as y_test_file:
    y_test = json.load(y_test_file)


# Verificar a acurácia do modelo nos dados de teste
y_pred = loaded_model.predict(X_test)
predictions = [round(value) for value in y_pred]
with open("predictions.json", "w") as f:
    json.dump(predictions, f)

# Enviar as previsões para o servidor
url = 'http://192.168.1.20:5000/predictions'
file = {
    'predictions_file': ('predictions.json', open('predictions.json', 'rb'))
}

response = requests.post(url, files=file)

if response.status_code == 200:
    print("Previsões enviadas com sucesso.")
else:
    print("Erro ao enviar as previsões para o servidor.")
