package com.gellai.raspiwebled;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class RpiWebSocketHandler extends TextWebSocketHandler 
{
    private static LedGpioController ledGpioController = new LedGpioController();

    @Override
    public void afterConnectionEstablished(WebSocketSession session)
            throws Exception {

//      System.out.println("Session ID: " + session.getId());
        ledGpioController.getLedHtmlSlider(session);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) 
            throws Exception {

        ledGpioController.setLedsPwm(message.getPayload(), session);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception)
            throws Exception {

        session.close(CloseStatus.SERVER_ERROR);
        System.out.println("Connection error with session '" + session.getId() + "': " + exception);
    }
}