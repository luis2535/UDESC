#include <Arduino.h>
#include <NewPing.h>
#include <WiFi.h>
#include <PubSubClient.h>

#define TRIGGER_PIN 14
#define ECHO_PIN 27
#define LED_PIN1 26
#define LED_PIN2 25

NewPing sonar(TRIGGER_PIN, ECHO_PIN);

const char* ssid = "Elizabeth 2G";
const char* password = "B3rt3@@i";

const char* mqtt_server = "192.168.1.20";
const int mqtt_port = 1883;
const char* mqtt_user = "tht";
const char* mqtt_password = "senha123";

WiFiClient espClient;
PubSubClient client(espClient);

void callback(char* topic, byte* payload, unsigned int length) {
  Serial.print("Message received on topic: ");
  Serial.println(topic);

  Serial.print("Payload: ");
  for (int i = 0; i < length; i++) {
    Serial.print((char)payload[i]);
  }
  Serial.println();
}

void reconnect() {
  // Loop until we're reconnected
  while (!client.connected()) {
    Serial.println("Attempting MQTT connection...");
    // Attempt to connect
    if (client.connect("esp32_cli1")) {
      Serial.println("Connected to MQTT broker");
      // Subscribe to topics here if needed
    } else {
      Serial.print("Failed, rc=");
      Serial.print(client.state());
      Serial.println(" Retrying in 5 seconds...");
      // Wait 5 seconds before retrying
      delay(5000);
    }
  }
}


void sendSensorDataToMosquitto(float distance) {
  String payload = String(distance);
  client.publish("topico_79384", payload.c_str());
}



void setup() {
  Serial.begin(9600);
  pinMode(LED_PIN1, OUTPUT);
  pinMode(LED_PIN2, OUTPUT);
  digitalWrite(LED_PIN1, LOW);
  digitalWrite(LED_PIN2, LOW);
  WiFi.mode(WIFI_STA);

  WiFi.begin(ssid, password);

while(WiFi.status() != WL_CONNECTED) {
  delay(500);
  Serial.print(".");
}

Serial.println("Connected to WiFi");


  client.setServer(mqtt_server, mqtt_port);
  client.setCallback(callback);

  while (!client.connected()) {
  Serial.println("Attempting MQTT connection...");
  if (client.connect("esp32_cli1")) {
    Serial.println("Connected to MQTT broker");
  } else {
    Serial.print("Failed, rc=");
    Serial.print(client.state());
    Serial.println(" Retrying in 5 seconds...");
    delay(5000);
  }
  }
}

int init1 = 0;
uint32_t lastPublishTime = 0;

void loop() {
  if (!client.connected()) {
    reconnect();
  }

  if (init1 == 0) {
    client.publish("init", "init1");
    client.subscribe("topico_79384");
    init1++;
  }

  unsigned int distance = sonar.ping_cm();

  if (distance >= 0 && distance <= 8) {
    digitalWrite(LED_PIN1, HIGH);
    digitalWrite(LED_PIN2, LOW);
  } else {
    digitalWrite(LED_PIN1, LOW);
    digitalWrite(LED_PIN2, HIGH);
  }

  client.loop();

  if (millis() - lastPublishTime > 1000) {
    Serial.println("publish?");
    sendSensorDataToMosquitto(distance);
    lastPublishTime = millis();
  }

  delay(100);
}
