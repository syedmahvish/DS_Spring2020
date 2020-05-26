package HW_1;

import HW_1.BinaryNumber;

public class Tester {
    public static void main(String args[]) {

        int choice = 1;

        BinaryNumber b1 = new BinaryNumber(-2);

        BinaryNumber b2 = new BinaryNumber("111");
        System.out.println("b2 = " + b2.toString());

        System.out.println("\nFollowing operations you can perform:" +
                "\n1- Get Length of binary number." +
                "\n2- Get Binary digit at given index." +
                "\n3- Shift Binary to right. " +
                "\n4- Convert binary number to decimal number" +
                "\n5- Add two binary number." +
                "\n6- Exit.");

        while (choice < 6) {


            switch (choice) {
                case 1:
                    System.out.println("Length of binary number is: " + b2.getLength());
                    break;

                case 2:
                    System.out.println("Digit at index: " + b2.getDigit(2));
                    break;

                case 3:
                    b2.shiftR(3);
                    break;

                case 4:
                    System.out.println("Decimal number is: " + b2.toDecimal());
                    break;

                case 5:
                    BinaryNumber b3 = new BinaryNumber("11100");
                    System.out.println("b3 = " + b3.toDecimal());
                    BinaryNumber b4 = new BinaryNumber("00012");
                    System.out.println("b4 = " + b4.toDecimal());
                    b3.add(b4);
                    System.out.println("b4 = " + b4.toString());
                    System.out.println("b4 = " + b4.toDecimal());
                    break;

                default:
                    return;
            }
            choice++;

        }
    }
}
