#define echoPin 12 // Ultrasonic sensor 
#define trigPin 11 // Ultrasonic sensor
#define MotorR1 7 // IN1 on L298N
#define MotorR2 6 // IN2 on L298N
#define MotorRenable 9  // enA on L298N
#define MotorL1 5 // IN3 on L298N
#define MotorL2 4 // IN4 on L298N
#define MotorLenable 10 // enB on L298N

long distance, time;
unsigned long previousMillis = 0; // To manage non-blocking delays
const long interval = 200; // Time interval for distance checking

int xPos = 0;
int yPos = 0;

void setup() {
  pinMode(echoPin, INPUT);
  pinMode(trigPin, OUTPUT);

  pinMode(MotorL1, OUTPUT);
  pinMode(MotorL2, OUTPUT);
  pinMode(MotorLenable, OUTPUT); 
  pinMode(MotorR1, OUTPUT);
  pinMode(MotorR2, OUTPUT);
  pinMode(MotorRenable, OUTPUT);

  Serial.begin(9600);
}

void loop() {
  unsigned long currentMillis = millis();

  if (currentMillis - previousMillis >= interval) {
    previousMillis = currentMillis;

    distance = measureDistance();
    Serial.print("Distance: ");
    Serial.println(distance);

    if (distance < 15) {
      Serial.print("Obstacle at: ");
      Serial.print(xPos);
      Serial.print(",");
      Serial.println(yPos);
      
      moveBackward();
      delay(1000);
      turnRight();
      delay(1000);
    } else {
      moveForward();
      stopMotors();
    }
  }
}

long measureDistance() {
  digitalWrite(trigPin, LOW); 
  delayMicroseconds(5);
  digitalWrite(trigPin, HIGH); 
  delayMicroseconds(10);
  digitalWrite(trigPin, LOW); 

  time = pulseIn(echoPin, HIGH);
  return (time / 2) * 0.0343; // Speed of sound in air 0.0343 cm/μs
}

void moveForward() {
  digitalWrite(MotorR1, HIGH);
  digitalWrite(MotorR2, LOW);
  analogWrite(MotorRenable, 100);

  digitalWrite(MotorL1, HIGH);
  digitalWrite(MotorL2, LOW);
  analogWrite(MotorLenable, 100);
  delay(1000);

  yPos += 1;
}

void stopMotors() {
  digitalWrite(MotorR1, LOW);
  digitalWrite(MotorR2, LOW);
  analogWrite(MotorRenable, 0);

  digitalWrite(MotorL1, LOW);
  digitalWrite(MotorL2, LOW);
  analogWrite(MotorLenable, 0);

  delay(100);
}

void turnRight() {
  digitalWrite(MotorR1, HIGH);
  digitalWrite(MotorR2, LOW);
  analogWrite(MotorRenable, 0);

  digitalWrite(MotorL1, HIGH);
  digitalWrite(MotorL2, LOW);
  analogWrite(MotorLenable, 100);

  xPos += 1;
}

void turnLeft() {
  digitalWrite(MotorR1, HIGH);
  digitalWrite(MotorR2, LOW);
  analogWrite(MotorRenable, 100);

  digitalWrite(MotorL1, HIGH);
  digitalWrite(MotorL2, LOW);
  analogWrite(MotorLenable, 0);

  xPos -= 1;
}

void moveBackward() {
  digitalWrite(MotorR1, LOW);
  digitalWrite(MotorR2, HIGH);
  analogWrite(MotorRenable, 100);

  digitalWrite(MotorL1, LOW);
  digitalWrite(MotorL2, HIGH);
  analogWrite(MotorLenable, 100);

  yPos -= 1;
}
