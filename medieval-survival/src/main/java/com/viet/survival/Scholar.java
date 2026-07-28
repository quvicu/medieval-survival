package com.viet.survival;

import java.util.ArrayList;
import java.util.Scanner;

public class Scholar {

    private final Scanner scanner;

    public Scholar(Scanner scanner) {
        this.scanner = scanner;
    }

    public void start(){
        talkToScholar();
    }

    private static final String SCHOLAR_MENU =
            """
                    1. Talk to Scholar
                    2. Binary ->Decimal
                    3. Decimal -> Binary
                    4. Ask Scholar:
                    5. Leave""";

    private void talkToScholar() {
        do {
            System.out.println(SCHOLAR_MENU);
            int menuChoice = scanner.nextInt();
            switch (menuChoice) {
                case 1:
                    System.out.println("I am mute right now");
                    break;
                case 2:
                    System.out.println("Binary -> Decimal");
                    binaryToDecimal();
                    break;
                case 3:
                    System.out.println("Decimal -> Binary");
                    decimalToBinary();
                    break;
                case 4:
                    System.out.println("Ask Scholar:");
                    break;
                case 5:
                    return;
            }
        }while (true);
    }

    private void binaryToDecimal() {
        String input = scanner.next();
        int result = input.charAt(0) - '0';
        for(int i = 1; i < input.length(); i++){
            int inputNumber = (int)input.charAt(i) - '0';
            result = (result * 2  + inputNumber);
        }
        System.out.println(
                "Calculating..." +
                        "\nBinary: " + input +
                        "\nDecimal: " + result + "\n");
    }

    private void decimalToBinary() {
        int input = scanner.nextInt();
        int decimalInput = input;
        StringBuilder builder = new StringBuilder();
        ArrayList<Integer> binaries = new ArrayList<>();
        if(input == 0){
            System.out.println(
                    """
                            Calculating...
                            Binary: 0
                            Decimal: 0
                            """);
            return;
        }
        else {
            while (input != 0) {
                binaries.add(input % 2);
                input /= 2;
            }
        }
        for(int i = binaries.size(); i > 0; i--) {
            builder.append(binaries.get(i-1));
        }
        System.out.println(
                "Calculating...\n" +
                        "Decimal: " + decimalInput + "\n" +
                        "Binary: " + builder + "\n");
    }
}
