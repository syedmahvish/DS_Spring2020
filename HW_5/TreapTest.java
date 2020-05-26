package HW_5;

public class TreapTest {
    public static void main(String args[]) {
        Treap testTree = new Treap< Integer >();
        System.out.println("HW_5.Treap after find all data from given HW pdf");
        testTree . add (4 ,19);
        testTree . add (2 ,31);
        testTree . add (6 ,70);
        testTree . add (1 ,84);
        testTree . add (3 ,12);
        testTree . add (5 ,83);
        testTree . add (7 ,26);
        System.out.println("----------------------------------------------");
        System.out.println(testTree.toString());

        System.out.println("HW_5.Treap find data from testing");
        System.out.println("find 1 = " +  testTree.find(1));
        System.out.println("find 7 = " + testTree.find(7));
        System.out.println("find 0 = " + testTree.find(0));
        System.out.println("find 8 = " + testTree.find(8));
        System.out.println("find 2 = " + testTree.find(2));
        System.out.println("find 5= " +  testTree.find(5));


        System.out.println("\nDelete node 2,31 = " +  testTree.delete(2));
        System.out.println("HW_5.Treap after deleting node with key 2");
        System.out.println("------------------------------------");
        System.out.println(testTree.toString());
        System.out.println("\nDelete node 3,21 = " +  testTree.delete(3));
        System.out.println("HW_5.Treap after deleting node with key 3");
        System.out.println("------------------------------------");
        System.out.println(testTree.toString());
        System.out.println("\nNot included node delete node 8,21 = " +  testTree.delete(3));
        System.out.println("HW_5.Treap after deleting node with key 8");
        System.out.println("------------------------------------");
        System.out.println(testTree.toString());



        Treap test = new Treap<Character>();
        test.add('p',99);
        test.add('r',99);
        test.add('u',99);
        test.add('z',99);
        test.add('a',99);
        test.add('g',99);
        test.add('j',99);
        test.add('v',99);
        test.add('w',99);
        test.add('x',99);

        System.out.println("HW_5.Treap after find  data with same priority");
        System.out.println("----------------------------------------------");
        System.out.println(test.toString());

        System.out.println("HW_5.Treap find  data with same priority");
        System.out.println("find j=" + test.find('j'));
        System.out.println("find a=" +test.find('a'));
        System.out.println("find x=" +test.find('x'));
        System.out.println("find b=" +test.find('b'));
        System.out.println("find p=" +test.find('p'));
        System.out.println("find c=" +test.find('c'));


        System.out.println("\nadd m,100 = "  + test.add('m',100));
        System.out.println("\nadd same data with diff priority add a,100 = "  + test.add('a',100));
        System.out.println("HW_5.Treap after adding node with key a and priority 93");
        System.out.println("------------------------------------");
        System.out.println(test.toString());

        System.out.println(test.delete('z'));
        System.out.println("HW_5.Treap after deleting node with key z");
        System.out.println("------------------------------------");
        System.out.println(test.toString());

    }
}
