from flask import Flask, request, send_from_directory, jsonify
import os
import json

app = Flask(__name__)

UPLOAD_FOLDER = 'uploads'
app.config['UPLOAD_FOLDER'] = UPLOAD_FOLDER

@app.route('/upload', methods=['POST'])
def upload_files():
    if 'model_file' not in request.files or 'X_test_file' not in request.files or 'y_test_file' not in request.files:
        return 'Nenhum arquivo recebido.'

    model_file = request.files['model_file']
    X_test_file = request.files['X_test_file']
    y_test_file = request.files['y_test_file']

    # Salvar o arquivo do modelo
    model_path = os.path.join(app.config['UPLOAD_FOLDER'], 'model.json')
    model_file.save(model_path)

    # Salvar o arquivo JSON com os dados de teste X
    X_test_path = os.path.join(app.config['UPLOAD_FOLDER'], 'X_test.json')
    X_test_file.save(X_test_path)

    # Salvar o arquivo JSON com os rótulos y
    y_test_path = os.path.join(app.config['UPLOAD_FOLDER'], 'y_test.json')
    y_test_file.save(y_test_path)

    return 'Arquivos recebidos com sucesso.'

@app.route('/download/model.json')
def download_model():
    return send_from_directory(app.config['UPLOAD_FOLDER'], 'model.json')

@app.route('/download/X_test.json')
def download_X_test():
    return send_from_directory(app.config['UPLOAD_FOLDER'], 'X_test.json')

@app.route('/download/y_test.json')
def download_y_test():
    return send_from_directory(app.config['UPLOAD_FOLDER'], 'y_test.json')
stored_predictions = []
# Rota para receber as previsões
@app.route('/predictions', methods=['POST'])
def receive_predictions():
    if 'predictions_file' not in request.files:
        return 'Nenhum arquivo de previsão recebido.'

    predictions_file = request.files['predictions_file']

    # Salvar o arquivo de previsões
    predictions_path = os.path.join(app.config['UPLOAD_FOLDER'], 'predictions.json')
    predictions_file.save(predictions_path)

    return 'Previsões recebidas com sucesso.'
if __name__ == '__main__':
    os.makedirs(app.config['UPLOAD_FOLDER'], exist_ok=True)
    app.run(debug=True, host='0.0.0.0', port=5000)
