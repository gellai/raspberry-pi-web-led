# Raspberry Pi Web LED

[![N|Gellai](https://www.gellai.com/wp-content/themes/gellai/images/Powered-By-Gellai.png)](https://gellai.com)

![Raspberry Pi Web LED](https://gellai.com/wp-content/uploads/2019/04/controlling-the-raspberry-pis-gpios-from-the-internet.jpg)

## What is this?
This Java application is to demonstrate how to control a tri-colour (RGB) LED from the internet. Tomcat servlet is running on a Raspberry Pi and the user can interfere with the Pi’s GPIO’s (General Purpose Input Output) from the web browser. 

## Hardware requirements
	- 1 x Raspberry Pi 2 Model B
	- 1 x 5mm RGB LED
	- 3 x Resistors (see the values later)
	- 1 x Solderless Breadboard
	- 4 x Male to Female Jumper Wires

## Required packages
	- Java JDK
	- WiringPi
	- Maven	
	
## Installation
The installation must be done on the Raspberry Pi.

#### Get The Repository
```
$ git clone git://github.com/gellai/raspberry-pi-web-led.git
```

### Compile source
```
$ mvn clean package
```

### Starting the web server
```
$ java -jar target/raspiwebled.war
```

[Find out more about this project and setup here.](https://gellai.com/raspberry-pi-web-server-rgb-led-control-over-the-internet)

