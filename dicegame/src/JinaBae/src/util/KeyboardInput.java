package util;

import java.util.Scanner;

public class KeyboardInput {
    private static Scanner sc = new Scanner(System.in);

    //키보드 생성자

    public KeyboardInput() {}

    public static String getStringInput(String prompt) {
        System.out.print(prompt);
        return sc.nextLine();
    }
}
