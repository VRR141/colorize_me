package com.example.swing_async_test.components;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Component
@RequiredArgsConstructor
public class MainLoader extends JLabel implements SwingComponents {

    private final ResourceLoader resourceLoader;

    @PostConstruct
    void pc(){
        onStart();
    }

    private void onStart(){
        SwingUtilities.invokeLater(this::onStartRunner);
    }

    @SneakyThrows
    private void onStartRunner() {
        this.setIcon(new ImageIcon(
                resourceLoader
                        .getResource("amalie-steiness.gif")
                        .getURL()));
        this.setHorizontalAlignment(CENTER);
        this.setVerticalAlignment(CENTER);
    }
}
