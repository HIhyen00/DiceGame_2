package utility;

import java.util.Scanner;

public class KeyboardInput {
    private static Scanner scanner = new Scanner(System.in);

    private KeyboardInput() {}

    public static String getStringInput (String prompt){
        System.out.print(prompt);
        return scanner.nextLine();
    }

}

