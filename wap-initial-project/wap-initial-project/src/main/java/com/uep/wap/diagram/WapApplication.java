package com.uep.wap.diagram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@SpringBootApplication
public class WapApplication {

    // Default port for the web application
    private static final int DEFAULT_PORT = 8080;
    // Default host for the web application
    private static final String DEFAULT_HOST = "localhost";

    /**
     * The main method that serves as the entry point for the application.
     * It creates instances of your classes and orchestrates their interactions.
     *
     * @param args Command line arguments passed to the application
     */

    public static void main(String[] args) {
        SpringApplication.run(WapApplication.class, args);
        User user = new User();
        int port = 8080;

//        // Parse command line arguments for custom port
//        if (args.length > 0) {
//            try {
//                port = Integer.parseInt(args[0]);
//            } catch (NumberFormatException e) {
//                System.out.println("Invalid port number. Using default port " + DEFAULT_PORT);
//            }
//        }




    }

    /**
     * Opens the default web browser and navigates to the application URL.
     *
     * @param host The hostname where the server is running
     * @param port The port number where the server is listening
     */
    private static void openBrowser(String host, int port) {
        String url = "http://" + host + ":" + port;

        System.out.println("Attempting to open browser at: " + url);

        // Check if Desktop is supported
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            try {
                Desktop.getDesktop().browse(new URI(url));
                System.out.println("Browser opened successfully.");
            } catch (IOException | URISyntaxException e) {
                System.err.println("Failed to open browser: " + e.getMessage());
                System.out.println("Please manually open your browser and navigate to: " + url);
            }
        } else {
            System.out.println("Desktop browsing not supported on this platform.");
            System.out.println("Please manually open your browser and navigate to: " + url);
        }
    }


}
