package com.example.swing_async_test.main;

import com.example.swing_async_test.components.MainButton;
import com.example.swing_async_test.components.MainPanel;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

@Component
@RequiredArgsConstructor
public class MainFrame extends JFrame {

    private final MainButton mainButton;

    private final MainPanel mainPanel;

    @PostConstruct
    void pc(){
        onStart();
    }

    private void onStart(){
        SwingUtilities.invokeLater(this::onStartRunner);
    }

    private void onStartRunner(){
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setLayout(new BorderLayout());
        this.add(mainButton, BorderLayout.SOUTH);
        this.add(mainPanel, BorderLayout.CENTER);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
