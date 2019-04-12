package com.gellai.raspiwebled;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinPwmOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;

public class LedGpioController 
{
    private static final Pin RED_LED_PIN   = RaspiPin.GPIO_27; // Physical Pin 36 - Pi4J/WiringPi 27 - RED
    private static final Pin GREEN_LED_PIN = RaspiPin.GPIO_28; // Physical Pin 38 - Pi4J/WiringPi 28 - GREEN
    private static final Pin BLUE_LED_PIN  = RaspiPin.GPIO_29; // Physical Pin 40 - Pi4J/WiringPi 29 - BLUE

    private Map<String, GpioPinPwmOutput> pwmOutputPins;

    private static GpioController gpio;

    public LedGpioController() {
        pwmOutputPins = new HashMap<String, GpioPinPwmOutput>();

        gpio = GpioFactory.getInstance();

        pwmOutputPins.put(
                "redPwmPin",
                gpio.provisionSoftPwmOutputPin(RED_LED_PIN));

        pwmOutputPins.put(
                "greenPwmPin",
                gpio.provisionSoftPwmOutputPin(GREEN_LED_PIN));

        pwmOutputPins.put(
                "bluePwmPin",
                gpio.provisionSoftPwmOutputPin(BLUE_LED_PIN));

        // Set PWM range
        pwmOutputPins.get("redPwmPin").setPwmRange(100);
        pwmOutputPins.get("greenPwmPin").setPwmRange(100);
        pwmOutputPins.get("bluePwmPin").setPwmRange(100);

        // Set default PWM values
        pwmOutputPins.get("redPwmPin").setPwm(0);
        pwmOutputPins.get("greenPwmPin").setPwm(0);
        pwmOutputPins.get("bluePwmPin").setPwm(0);
    }

    public void setLedsPwm(String payload, WebSocketSession wss)
            throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        LedValueMapper ledValueMapper = objectMapper.readValue(payload, LedValueMapper.class);

        pwmOutputPins.get("redPwmPin").setPwm(
                Integer.parseInt(ledValueMapper.getRedValue()));

        pwmOutputPins.get("greenPwmPin").setPwm(
                Integer.parseInt(ledValueMapper.getGreenValue()));

        pwmOutputPins.get("bluePwmPin").setPwm(
                Integer.parseInt(ledValueMapper.getBlueValue()));

//      System.out.println("R:" + ledValueMapper.getRedValue());
//      System.out.println("G:" + ledValueMapper.getGreenValue());
//      System.out.println("B:" + ledValueMapper.getBlueValue());

        getLedHtmlSlider(wss);
    }

    public void getLedHtmlSlider(WebSocketSession wss)
            throws Exception {

        GpioPinPwmOutput redPwm   = pwmOutputPins.get("redPwmPin");
        GpioPinPwmOutput greenPwm = pwmOutputPins.get("greenPwmPin");
        GpioPinPwmOutput bluePwm  = pwmOutputPins.get("bluePwmPin");

        String message = 
                "{'redSlider' : '" + redPwm.getPwm() + "',"
                +"'greenSlider' : '" + greenPwm.getPwm() + "',"
                +"'blueSlider' : '" + bluePwm.getPwm() + "'}";

        wss.sendMessage(new TextMessage(message));
    }
}