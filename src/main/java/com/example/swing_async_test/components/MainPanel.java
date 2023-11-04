package com.example.swing_async_test.components;

import com.example.swing_async_test.dto.CustomClickButtonEvent;
import com.example.swing_async_test.dto.CustomReloadedBackGroundEvent;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.util.Random;


@Component
@Slf4j
@RequiredArgsConstructor
public class MainPanel extends JPanel implements SwingComponents {

    private Random random;

    private final ApplicationEventPublisher applicationEventPublisher;

    private final MainLoader mainLoader;

    @PostConstruct
    void pc(){
        onStart();
        random = new Random();
    }


    @EventListener(classes = CustomClickButtonEvent.class)
    public void listener(){
        log.info("Accepted event for change background color");
        showSpinner();
        Color color = customLogic();
        SwingUtilities.invokeLater(() -> logic(color));
    }

    private void onStart(){
        SwingUtilities.invokeLater(this::onStartRunner);
    }

    private void onStartRunner(){
        this.setBackground(Color.RED);
        this.setLayout(new BorderLayout());
        this.add(mainLoader, BorderLayout.CENTER);
        mainLoader.setVisible(false);
    }

    @SneakyThrows
    private void logic(Color color){
        log.info("Inside event for change background color");
        disableSpinner();
        this.setBackground(color);
        applicationEventPublisher.publishEvent(new CustomReloadedBackGroundEvent());
    }

    private void showSpinner(){
        SwingUtilities.invokeLater(() -> mainLoader.setVisible(true));
    }

    private void disableSpinner(){
        SwingUtilities.invokeLater(() -> mainLoader.setVisible(false));
    }

    @SneakyThrows
    private Color customLogic(){
        Thread.sleep(1000);
        return new Color(
                random.nextInt(256),
                random.nextInt(256),
                random.nextInt(256));
    }
}
