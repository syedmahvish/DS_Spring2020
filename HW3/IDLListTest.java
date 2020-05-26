package HW3;

import HW_1.IDLList;

import java.util.Scanner;

public class IDLListTest<E> {
    public static void main(String args[]) {
        IDLList dll =  new IDLList();
        int choice = 1;
        Scanner scan = new Scanner(System.in);

        try {
            while (choice < 13) {
                System.out.println("Following operations can be performed : " +
                        "\n 1- Add at index" +
                        "\n 2- Add at first" +
                        "\n 3- Add at last" +
                        "\n 4- Get element at index" +
                        "\n 5- Get head" +
                        "\n 6- Get last node or tail" +
                        "\n 7- Get size" +
                        "\n 8- Remove first element" +
                        "\n 9- Remove last element" +
                        "\n 10- Remove element at index" +
                        "\n 11- Remove specified element" +
                        "\n 12- Print IDLL" +
                        "\n 13- Exit");

                System.out.println("Enter your choice : ");
                choice = scan.nextInt();


                switch (choice) {
                    case 1: {
                        System.out.println("Enter index and data:");
                        int index = scan.nextInt();
                        String data = scan.next();
                        System.out.println("\nBefore add : " + dll.toString());
                        dll.add(index, data);
                        System.out.println("After add : " + dll.toString() + "\n");
                        break;
                    }
                    case 2: {
                        System.out.println("Enter data to add at head:");
                        String data = scan.next();
                        System.out.println("\nBefore add : " + dll.toString());
                        dll.add(data);
                        System.out.println("After add : " + dll.toString() + "\n");
                        break;
                    }

                    case 3: {
                        System.out.println("Enter data to add at tail:");
                        String data = scan.next();
                        System.out.println("\nBefore add : " + dll.toString());
                        dll.append(data);
                        System.out.println("After add : " + dll.toString() + "\n");
                        break;
                    }
                    case 4: {
                        System.out.println("Enter index to get data:");
                        int index = scan.nextInt();
                        System.out.println("\nDLL : " + dll.toString());
                        System.out.println("Data at index " + index + " : " + dll.get(index) + "\n");
                        break;
                    }
                    case 5: {
                        System.out.println("\nDLL : " + dll.toString());
                        System.out.println("Data at head :" + dll.getHead() + "\n");
                        break;
                    }
                    case 6: {
                        System.out.println("\nDLL : " + dll.toString());
                        System.out.println("Data at tail :" + dll.getLast() + "\n");
                        break;
                    }

                    case 7: {
                        System.out.println("\nDLL : " + dll.toString());
                        System.out.println("Size of dll :" + dll.size() + "\n");
                        break;
                    }
                    case 8: {
                        System.out.println("\nBefore remove : " + dll.toString());
                        System.out.println("\nData removed : " + dll.remove());
                        System.out.println("After remove at head : " + dll.toString() + "\n");
                        break;
                    }
                    case 9: {
                        System.out.println("\nBefore remove : " + dll.toString());
                        System.out.println("\nData removed at tail: " + dll.removeLast());
                        System.out.println("After remove at end : " + dll.toString() + "\n");
                        break;
                    }

                    case 10: {
                        System.out.println("Enter index to remove data:");
                        int index = scan.nextInt();
                        System.out.println("\nBefore remove : " + dll.toString());
                        System.out.println("\nData removed at index " + index + " : " + dll.removeAt(index));
                        System.out.println("After remove at index : " + dll.toString() + "\n");
                        break;
                    }
                    case 11: {
                        System.out.println("Enter data to remove :");
                        String data = scan.next();
                        System.out.println("\nBefore remove : " + dll.toString());
                        System.out.println("\nData removed " + dll.remove(data));
                        System.out.println("After remove : " + dll.toString() + "\n");
                        break;
                    }

                    case 12: {
                        System.out.println("\nDLL: " + dll.toString());
                        break;
                    }

                    default:
                        break;
                }
            }
        }catch (Exception e) {
            System.out.println(e);
        }
    }
}
