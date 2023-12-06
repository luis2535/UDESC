// #include <NewPing.h>
// #include <WiFi.h>
// #include <WiFiClientSecure.h>
// #include <PubSubClient.h>

// #define TRIGGER_PIN 14 // Pino de Trigger conectado à GPIO 14 (D14)
// #define ECHO_PIN 27    // Pino de Echo conectado à GPIO 27 (D27)
// #define LED_PIN1 26     // Pino do primeiro LED conectado à GPIO 26 (D26)
// #define LED_PIN2 25     // Pino do segundo LED conectado à GPIO 25 (D25)

// NewPing sonar(TRIGGER_PIN, ECHO_PIN);

// const char* ssid = "Elizabeth 5G";
// const char* password = "B3rt3@@i";


// const char* mqtt_server = "b814cbcae3cd4041972b71aa4f136620.s1.eu.hivemq.cloud";
// const char* mqtt_username = "luis2535";
// const char* mqtt_password = "Luis1999@@";
// const int mqtt_port = 8883;

// WiFiClient espClient;
// PubSubClient client(espClient);

// unsigned long lastMsg = 0;
// #define MSG_BUFFER_SIZE (50)
// char msg[MSG_BUFFER_SIZE];


// void setup_wifi() {
//   delay(10);
//   Serial.print("\nConnecting to");
//   Serial.println(ssid);

//   WiFi.mode(WIFI_STA);
//   WiFi.begin(ssid, password);

//   while(WiFi.status() != WL_CONNECTED) {
//     delay(500);
//     Serial.print(".");
//   }

//   randomSeed(micros());
//   Serial.print("\nWiFi connected\nIP address:");
//   Serial.println(WiFi.localIP());
// }

// void reconnect(){
//   while(!client.connected()){
//     Serial.print("Attempting MQTT connection...");
//     String clientId = "ESP32 Client";
//     clientId += String(random(0xfffff), HEX);

//     if(client.connect(clientId.c_str(), mqtt_username, mqtt_password)) {
//       Serial.print("connected");

//       client.subscribe("led_state");
//     }else{
//       Serial.print("failed, rc=");
//       Serial.print(client.state());
//       Serial.println("Try again in 5 seconds");
//       delay(5000);
//     }
//   }
// }

// void setup() {
//   Serial.begin(115200);
//   pinMode(LED_PIN1, OUTPUT);
//   pinMode(LED_PIN2, OUTPUT);
//   digitalWrite(LED_PIN1, LOW); // Desligue o primeiro LED no início
//   digitalWrite(LED_PIN2, LOW); // Desligue o segundo LED no início
// }

// void loop() {
//   unsigned int distance = sonar.ping_cm();

//   if (distance >= 0 && distance <= 8) { // Ligue o primeiro LED se a distância estiver entre 0 e 5 cm
//     digitalWrite(LED_PIN1, HIGH); // Acenda o primeiro LED
//     digitalWrite(LED_PIN2, LOW);  // Desligue o segundo LED
//   } else {
//     digitalWrite(LED_PIN1, LOW);  // Desligue o primeiro LED
//     digitalWrite(LED_PIN2, HIGH); // Acenda o segundo LED
//   }

//   Serial.print("Distância: ");
//   Serial.print(distance);
//   Serial.println(" cm");

//   delay(50); // Aguarde 50 milissegundos antes de fazer outra leitura
// }
