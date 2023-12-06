from numpy import loadtxt
from xgboost import XGBClassifier
from sklearn.model_selection import train_test_split
from sklearn.metrics import accuracy_score
import json
import requests
import os
import time


def send_data():
    # Carregue os dados
    dataset = loadtxt('./datasets/pima-indians-diabetes.csv', delimiter=",")

    # Separe os dados em X e Y
    X = dataset[:, 0:8]
    Y = dataset[:, 8]

    # Separe os dados em conjuntos de treino e teste
    seed = 7
    test_size = 0.33
    X_train, X_test, y_train, y_test = train_test_split(X, Y, test_size=test_size, random_state=seed)

    # Ajuste o modelo nos dados de treino
    model = XGBClassifier()
    model.fit(X_train, y_train)

    # Salve o modelo em JSON
    model.save_model("model.json")

    metade_teste = len(X_test) // 2
    new_X_test = X_test[:metade_teste]
    new_y_test = y_test[:metade_teste]

    new_X_test1 = X_test[metade_teste:]
    new_y_test1 = y_test[metade_teste:]
    # Salve os dados de teste em JSON
    with open("X_test.json", "w") as f:
        json.dump(new_X_test1.tolist(), f)

    with open("y_test.json", "w") as f:
        json.dump(new_y_test1.tolist(), f)

    # Envie o modelo e os dados de teste para o servidor
    url = 'http://127.0.0.1:5000/upload'
    files = {
        'model_file': ('model.json', open('model.json', 'rb')),
        'X_test_file': ('X_test.json', open('X_test.json', 'rb')),
        'y_test_file': ('y_test.json', open('y_test.json', 'rb'))
    }

    response = requests.post(url, files=files)

    if response.status_code == 200:
        print(response.text)
    else:
        print("Erro ao enviar os arquivos para o servidor.")

    y_pred = model.predict(new_X_test)
    prediction = [round(value) for value in y_pred]

    return prediction, y_test
# Chame a função para enviar o modelo e os dados de teste
prediction, y_test = send_data()

directory_to_watch = '../server/uploads'
file_to_watch = 'predictions.json'
file_path = os.path.join(directory_to_watch, file_to_watch)

while not os.path.exists(file_path):
    time.sleep(1)

with open('../server/uploads/predictions.json', 'r') as f:
    pred_2 = json.load(f)


print(prediction)
prediction.extend(pred_2)

print(pred_2)

accuracy = accuracy_score(y_test, prediction)
end_time = time.time()
print("\nAccuracy: %.2f%%" % (accuracy * 100.0))

