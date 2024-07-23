# Arduino Car Obstacle Mapping Project

## Overview
This project involves an Arduino-powered car equipped with an ultrasonic sensor to detect obstacles and map their locations. The car sends the coordinates of detected obstacles to a Java-based application via serial communication. The Java application visualizes the mapped obstacles on a GUI.

## Features
- **Arduino Car Control**: The car moves forward, backward, and turns based on obstacle detection.
- **Obstacle Detection**: Uses an ultrasonic sensor to detect obstacles within 15 cm.
- **Serial Communication**: Sends obstacle coordinates to the Java application.
- **Java GUI**: Visualizes the mapped obstacles in real-time.

## Components
### Hardware
- Arduino board
- Ultrasonic sensor (HC-SR04)
- Motor driver (L298N)
- Motors and wheels
- Bluetooth or WiFi module (HC-06 or ESP8266)
- Power supply

### Software
- Arduino IDE
- Java Development Kit (JDK)
- jSerialComm library for serial communication
- Java Swing for GUI

## Arduino Code
The Arduino code controls the car's movement and detects obstacles. When an obstacle is detected, it sends the coordinates to the serial port.

### Arduino Connections
```plaintext
#define echoPin 12 // Ultrasonic sensor
#define trigPin 11 // Ultrasonic sensor
#define MotorR1 7 // IN1 on L298N
#define MotorR2 6 // IN2 on L298N
#define MotorRenable 9  // enA on L298N
#define MotorL1 5 // IN3 on L298N
#define MotorL2 4 // IN4 on L298N
#define MotorLenable 10 // enB on L298N
