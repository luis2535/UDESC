const express = require('express');
const http = require('http');
const socketIO = require('socket.io');
const mqtt = require('mqtt');

const app = express();
const server = http.createServer(app);
const io = socketIO(server);

const mqttClient = mqtt.connect('mqtt://192.168.1.20');

mqttClient.on('connect', () => {
  mqttClient.subscribe('topico_79384', (err) => {
    if (!err) {
      console.log('Conectado ao MQTT e inscrito em "topico_79384"');
    }
  });
});

mqttClient.on('message', (topic, message) => {
  console.log('Mensagem MQTT Recebida:', message.toString());

  const distance = parseFloat(message.toString());

  let status = 'Desconhecido';
  if (!isNaN(distance)) {
    status = distance < 9 ? 'Ocupado' : 'Livre';
  }

  io.emit('mqttMessage', { distance, status });
});

app.get('/', (req, res) => {
  res.sendFile(__dirname + '/index.html');
});

// Inicialização do servidor
const PORT = process.env.PORT || 3000;
server.listen(PORT, () => {
  console.log(`Servidor web rodando na porta ${PORT}`);
});
