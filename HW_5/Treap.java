package HW_5;
/***
 * @author : Syed Mahvish (CWID : 10456845)
 * This program perform Add, delete and find operation on HW_5.Treap.
 */

import java.util.Random;
import java.util.Stack;

public class Treap<E extends Comparable<E>> {
    //static inner class Node to store data, priority and reference to left and right child.
    private static class Node<E extends Comparable<E>> implements Comparable<Node<E>> {
        public E data;
        public int priority;
        public Node <E> left;
        public Node <E> right;

        /***
         * The constructor to create node with given data and priority
         * @param data value of node in treap.
         * @param priority priority of node in treap;
         * @throws NullPointerException if provided data is null throws NullPointerException
         */
        public Node (E data , int priority ) throws NullPointerException {
            if(data == null)
                throw new NullPointerException();
            this.data = data;
            this.priority = priority;
            this.left = null;
            this.right = null;
        }

        /***
         * PreCondition : tree is given with reference node to rotate right.
         * PostCondition : tree is rotate right with respect to refernce node maintaining property of heap.
         * Nodes are placed on basis of priority and data.
         * @return root node of Right rotate tree.
         */
        Node <E> rotateRight () {
            Node<E> rotateNode = new Node<>(this.data, this.priority);
            if(this.left != null) {

                if(this.right != null) {
                    rotateNode.right = this.right;
                }

                if(this.left.right != null) {
                    rotateNode.left = this.left.right;
                }

                this.data = this.left.data;
                this.priority = this.left.priority;
                this.right = rotateNode;

                if(this.left.left != null)
                    this.left = this.left.left;
                else
                    this.left = null;

            }

            return rotateNode;
        }

        /***
         * PreCondition : tree is given with reference node to rotate left.
         * PostCondition : tree is rotate left with respect to refernce node maintaining property of heap.
         * Nodes are placed on basis of priority and data.
         * @return root node of left rotate tree.
         */
        Node <E> rotateLeft () {
            Node<E> rotateNode = new Node<>(this.data, this.priority);
            if(this.right != null) {

                if(this.left != null) {
                    rotateNode.left = this.left;
                }


                if(this.right.left != null) {
                    rotateNode.right = this.right.left;
                }

                this.data = this.right.data;
                this.priority = this.right.priority;
                this.left = rotateNode;

                if(this.right.right != null)
                    this.right = this.right.right;
                else
                    this.right = null;

            }
            return rotateNode;
        }

        /***
         * Compare the data of two node objects.
         * @param o node object whose data need to compare with current node.
         * @return -1 , 1, 0 if current node is less than , greater than or equal to given node respectively.
         */
        @Override
        public int compareTo(Node<E> o) {
            return this.data.compareTo(o.data);
        }
    }


    private Random priorityGenerator ;
    private Node <E> root ;


    /***
     * Default constructor to create null treap and initialize random Generator(which will assign priority).
     */
    public Treap () {
        root = null;
        priorityGenerator = new Random();
    }

    /***
     * constructor to create null treap and initialize random Generator with given seed value.
     * @param seed to initialize random Generator with given seed value.
     */
    public Treap (long seed) {
        root = null;
        priorityGenerator = new Random(seed);
    }

    /***
     * Insert new node in treap.
     * @param key data to be entered in treap.
     * @return true if data added successfully, else false.
     */
    boolean add (E key ) {
        return add(key , priorityGenerator.nextInt());
    }

    /***
     * This is basically a helper method to insert new node in treap.
     * If treap is not yet initialize it will create root and return true;
     * If data is already present in treap, it will return false.
     * Else it will check where to store data and keep track of path node in stack.
     * Once reached to leaf node and found place, it insert data jst like BST.
     * Then it calls reheap() method to maintain priority of heap and fulfill heap property in treap.
     * @param key data to be insert in treap.
     * @param priority priority of node.
     * @return true if inserted successfully else false.
     */
     boolean add (E key , int priority)  {
         if(root == null) {
             root = new Node<>(key, priority);
             return true;
         }

         if(root.data.compareTo(key) == 0) {
             return false;
         }

         Stack<Node> stack = new Stack<>();
         Node temp = root;
         Node newNode = new Node(key, priority);

         while (temp != null) {
             if(temp.data.compareTo(key) == 0) {
                 return false;
             }

             if(temp.data.compareTo(key) > 0) {
                 stack.push(temp);
                 temp = temp.left;
             }else{
                 stack.push(temp);
                 temp = temp.right;
             }
         }

         if(stack.peek().data.compareTo(key) > 0){
             stack.peek().left = newNode;
         }else{
             stack.peek().right = newNode;
         }

         reHeap(newNode , stack);

         return true;
     }

    /***
     * This is helper method to maintain priority of node and fulfill heap property in treap.
     * It checks if node priority is greater than stack data priority rotate left else right.
     * It performs rotation untill stack is empty or in other words node is inserted from leaf to appropriate place.
     * @param node which need to insert inserted from leaf to appropriate place.
     * @param stack stack to maintain node path from root to given node.
     */
    private void reHeap(Node<E> node, Stack<Node> stack) {
        while (!stack.isEmpty()) {
            Node temp = stack.pop();
            if(node.priority > temp.priority) {
                if(temp.data.compareTo(node.data) > 0)
                    temp.rotateRight();
                else
                    temp.rotateLeft();
            }
        }
    }

    /***
     * This method deletes given node in treap.
     * It uses findNode method to find node in treap.
     * If key is not found delete it return false.
     * Once key is found, it moves that node towards leaf maintainig priorities of other node using deleteReheap method.
     * It calls deleteHelper method to delete given node.
     * @param key data which need to be delete from treap.
     * @return true if deleted else false.
     */
     boolean delete (E key ){
        Node<E> deleteNode = findNode(root, key);
        if(deleteNode == null) {
            return false;
        }

        deleteReheap(deleteNode);
        root = deleteHelper(root, key);

        return true;
     }


    /***
     * It moves given node towards leaf maintainig priorities of other node.
     * @param node which need to be delete from treap and move to leaf.
     */
     private void deleteReheap(Node<E> node) {
        while (node.left != null || node.right != null){
            if(node.left != null && node.right == null) {
                node  = node.rotateRight();
            }else if(node.right != null && node.left == null) {
                node = node.rotateLeft();
            }else{
                if(node.left.priority > node.right.priority) {
                   node = node.rotateRight();
                }else{
                   node = node.rotateLeft();
                }
            }
        }
     }


    /***
     * This method works recursively to delete node from treap.
     * @param node root node of treap.
     * @param key data to be deleted from treap.
     * @return  node after delete.
     */
    private Node<E> deleteHelper(Node<E> node, E key) {
        if(node == null)
            return null;

        if(node.right == null && node.left == null && node.data.compareTo(key) == 0) {
            return null;
        }

        if(node.data.compareTo(key) > 0)
            node.left = deleteHelper(node.left, key);
        else
            node.right = deleteHelper(node.right, key);
        return node;
    }

    /***
     * This is helper method to find a data from treap.
     * It works recursively.
     * @param root root node of treap.
     * @param key data to be find.
     * @return node whose data to be find.
     */
    private Node<E> findNode(Node <E> root , E key) {
        if(root == null)
            return null;

        if(root.data.compareTo(key) == 0)
            return root;

        if(root.data.compareTo(key) > 0){
            root = root.left;
        }else{
            root = root.right;
        }
        return findNode(root, key);
    }


    /***
     * This is helper method to find a data from treap.
     * it calls findNode to find node in treap.
     * @param root root node fo treap.
     * @param key data to be find in treap.
     * @return true if data found else false.
     */
      private boolean find (Node <E> root , E key ) {
        return !(findNode(root , key) == null);
     }

    /***
     * This method finds a given data from treap.
     * It calls helper method to perform search.
     * @param key  data to be find in treap.
     * @return true if data found else false.
     */
    public boolean find (E key) {
        return find(root, key);
    }


    /***
     * Display string format of treap.
     * It calls helper method toString to perform operation.
     * If no treap is initialize or root is null then it returns null.
      * @return string format of treap.
     */
   public String toString () {
       if(root == null)
           return null;
       StringBuilder str = new StringBuilder();
       toString(root, 1 , str);
       return str.toString();
   }

    /***
     * It is helper method to display string format of treap.
     * It display each level with space.
     * Calls recursively to perform operation.
     * @param node root node of treap and recursively pass left and right node of treap.
     * @param depth indicate each level of tree.
     * @param str string format of treap.
     */
  private  void toString(Node node, int depth, StringBuilder str) {
        for(int i = 0 ; i < depth ; i++) {
            str.append(" ");
        }

        if(node == null){
            str.append("null\n");
        }else{
            String temp = "(key = " + node.data + ", Priority = " + node.priority + ")\n";
            str.append(temp);
            toString(node.left, depth + 1, str);
            toString(node.right, depth + 1 , str);
        }
  }
}
