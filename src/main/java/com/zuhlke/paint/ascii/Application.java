package com.zuhlke.paint.ascii;

import com.zuhlke.paint.ascii.command.Command;
import com.zuhlke.paint.ascii.command.CommandFactory;
import com.zuhlke.paint.ascii.command.CommandFactoryImpl;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * The {@code Application} class represents the ascii-paint application providing the main command loop.
 * It reads input from the given {@code InputStream} and triggers execution of commands.
 */
public class Application {

    private CommandFactory commandFactory;
    private ApplicationContext applicationContext;
    private InputStream inputStream;

    private Application(InputStream inputStream) {
        this.inputStream = inputStream;
        applicationContext = ApplicationContextImpl.create(System.out::println);
        commandFactory = CommandFactoryImpl.create(applicationContext);
    }

    private void processInput(String input) {
        String [] arguments = input.split("\\s");

        try {
            Command command = commandFactory.createCommand(Arrays.asList(arguments));
            command.execute();
        } catch (AsciiPaintException e) {
            System.out.println(e.getLocalizedMessage());
        }

    }

    public void start() {
        applicationContext.setExecuting(true);
        System.out.println("ASCII-PAINT");

        try (Scanner scanner = new Scanner(this.inputStream)) {
            do {
                System.out.println();
                System.out.print("enter command: ");
                String input = scanner.nextLine();
                System.out.println();
                processInput(input.trim());
            } while (applicationContext.isExecuting());
        }

    }

    public static Application create(InputStream inputStream) {
        return new Application(inputStream);
    }

    public static void main(String[] args) {
        Application application = Application.create(System.in);
        application.start();
    }

}
