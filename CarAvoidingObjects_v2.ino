#define echoPin 12 // Ultrasonic sensor 
#define trigPin 11 // Ultrasonic sensor
#define MotorR1 7 // IN1 on L298N
#define MotorR2 6 // IN2 on L298N
#define MotorRenable 9  // enA on L298N
#define MotorL1 5 // IN3 on L298N
#define MotorL2 4 // IN4 on L298N
#define MotorLenable 10 // enB on L298N

long distance, time;

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
  digitalWrite(trigPin, LOW); //sensor set to pasive
  delayMicroseconds(5);
  digitalWrite(trigPin, HIGH); //sensor set to active
  delayMicroseconds(10);
  digitalWrite(trigPin, LOW); //sensor set to pasive

  //d=v*t
  time = pulseIn(echoPin, HIGH);
  distance = (time / 2)*0.0343; //speed of sound in air 0.0343 cm/μs

  Serial.print("Distance: ");
  Serial.println(distance);

  if (distance < 15) {
    moveBackward();
    delay(1000);
    turnRight();
    delay(800);
  }
  else {
    moveForward();
  } 
}

void moveForward() {
  digitalWrite(MotorR1, HIGH);
  digitalWrite(MotorR2, LOW);
  analogWrite(MotorRenable, 115);

  digitalWrite(MotorL1, HIGH);
  digitalWrite(MotorL2, LOW);
  analogWrite(MotorLenable, 100);
}
/*
void stop() {
  digitalWrite(MotorR1, HIGH);
  digitalWrite(MotorR2, LOW);
  analogWrite(MotorRenable, 0);

  digitalWrite(MotorL1, HIGH);
  digitalWrite(MotorL2, LOW);
  analogWrite(MotorLenable, 0);

  delay(4000);
}*/

void turnRight() {
  digitalWrite(MotorR1, LOW);
  digitalWrite(MotorR2, HIGH);
  analogWrite(MotorRenable, 100);

  digitalWrite(MotorL1, HIGH);
  digitalWrite(MotorL2, LOW);
  analogWrite(MotorLenable, 100);
}
/*
void turnLeft() {
  digitalWrite(MotorR1, HIGH);
  digitalWrite(MotorR2, LOW);
  analogWrite(MotorRenable, 100);

  digitalWrite(MotorL1, HIGH);
  digitalWrite(MotorL2, LOW);
  analogWrite(MotorLenable, 0);
} */

void moveBackward() {
  digitalWrite(MotorR1, LOW);
  digitalWrite(MotorR2, HIGH);
  analogWrite(MotorRenable, 100);

  digitalWrite(MotorL1, LOW);
  digitalWrite(MotorL2, HIGH);
  analogWrite(MotorLenable, 100);
}

