package com.example.swing_async_test.components;

import com.example.swing_async_test.dto.CustomClickButtonEvent;
import com.example.swing_async_test.dto.CustomReloadedBackGroundEvent;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

@Component
@RequiredArgsConstructor
public class MainButton extends JButton implements SwingComponents {

    private final ApplicationEventPublisher eventPublisher;

    @PostConstruct
    void pc() {
        onStart();
    }

    private ActionListener eventActionListener() {
        return e -> {
            eventPublisher.publishEvent(new CustomClickButtonEvent());
//            this.setEnabled(false);
        };
    }

    @EventListener(classes = CustomReloadedBackGroundEvent.class)
    public void onEvent(){
//        this.setEnabled(true);
    }

    private void onStart() {
        SwingUtilities.invokeLater(this::onStartRunner);
    }

    private void onStartRunner() {
        this.setBackground(Color.cyan);
        this.setForeground(Color.RED);
        this.setText("PRESS ME");
        this.addActionListener(eventActionListener());
    }
}
