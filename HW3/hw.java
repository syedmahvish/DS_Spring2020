package HW3;

import java.util.ArrayList;

public class hw<E> {
    private static class Node<E> {
        E data;
        Node<E> next;
        Node<E> prev;

        Node (E elem) {
            data = elem;
            prev = next = null;
        }

        Node (E elem, Node<E> prev, Node<E> next) {
            data = elem;
            this.prev = prev;
            prev.next = this;
            this.next = next;
            next.prev = this;
        }
    }


    private Node<E> head ;
    private Node<E> tail;
    private int size = 0;
    private ArrayList<Node<E>> indices;

    public hw (){
        indices = new ArrayList<>();
    }

    /***
     * that adds elem at position index.
     * It uses the index for fast access.
     * @param index specify index to add element in DLL.
     * @param elem node data to add.
     * @return true if node is added. else false.
     * @throws IndexOutOfBoundsException if index is invalid, throw exception.
     */
    public boolean add (int index, E elem) throws  IndexOutOfBoundsException {

        if(index < 0 || index > size()){
            throw new IndexOutOfBoundsException();
        }

        if(index == 0) {
            add(elem);
        } else{
            Node temp = indices.get(index);
            Node<E> newNode = new Node(elem , temp.prev , temp.prev.next);
            indices.add(index, newNode);
            size++;
        }

        return true;
    }

    /***
     * adds elem at the head
     * (i.e. it becomes the first element of the list).
     * @param elem to add at first node or head.
     * @return true if add node at head, else false.
     */
    public boolean add (E elem) {
        if(head == null) {
            head = tail =  new Node(elem);
            size++;
        } else{
            Node newNode = new Node(elem);
            newNode.prev = null;
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
            size++;
        }
        indices.add(0,head);
        return true;
    }

    /***
     * adds elem as the new last element of the list
     * (i.e. at the tail).
     * @param elem to add at last or tail.
     * @return true if add node at tail, else false.
     */
    public boolean append (E elem) {
        if(head == null) {
            add(elem);
        } else{
            Node<E> newNode = new Node(elem);
            newNode.next = null;
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
            indices.add(size() , tail);
            size++;
        }

        return true;
    }

    /***
     * that returns the object at position index from the head. It uses the index for fast access.
     * Indexing starts from 0.
     * @param index specify index of node.
     * @return node data of specified index.
     * @throws IndexOutOfBoundsException if index is invalid, throw exception.
     */
    public E get (int index) throws  IndexOutOfBoundsException{
        if(index < 0 || index > size()){
            throw new IndexOutOfBoundsException();
        }
        //to do
        return null;
    }

    /***
     * returns the object at the head.
     * @return node data at the head.
     * @throws NullPointerException if DLL is empty, throw exception.
     */
    public E getHead () throws NullPointerException {
        if(head == null) {
            throw  new NullPointerException();
        }
        //to do
        return null;
    }

    /***
     * returns the object at the tail.
     * @return node data at the tail
     * @throws NullPointerException if DLL is empty, throw exception.
     */
    public E getLast () throws NullPointerException {
        if(head == null || tail == null) {
            throw  new NullPointerException();
        }
        //to do
        return null;
    }

    /***
     * Size of DLL.
     * Index start from 0.
     * @return int value of size.
     */
    public int size() {
        return indices.size();
    }

    /***
     * removes and returns the element at the head.
     * @return old node data at the head.
     * @throws NullPointerException if DLL is empty, throw exception.
     */
    public E remove() throws NullPointerException {
        if(head == null) {
            throw  new NullPointerException();
        }
       //to do

        return null;
    }

    /***
     * removes and returns the element at the tail
     * @return the element at the tail.
     * @throws NullPointerException if DLL is empty throw exception.
     */
    public E removeLast () throws NullPointerException {
        if(head == null || tail == null) {
            throw  new NullPointerException();
        }

        //to do

        return null;
    }

    /***
     * removes and returns the element at the index index.
     * Use the index for fast access
     * @param index specify the index of element to remove.
     * @return data store by the element at the index index
     */
    public E removeAt (int index) throws IndexOutOfBoundsException {
        if(index < 0 || index > size()){
            throw new IndexOutOfBoundsException();
        }

        Node node = indices.get(index);
        E temp = (E) node.data;
        indices.remove(node);

        //to do

        //link of node logic

        return null;
    }

    /***
     * removes the first occurrence of elem in the list and
     * returns true.
     * Return false if elem was not in the list.
     * @param elem specify the element to remove.
     * @return returns true if element found and removed.
     *         Return false if elem was not in the list.
     */
    public boolean remove (E elem) throws NullPointerException {
        if(head == null || tail == null) {
            throw  new NullPointerException();
        }

        int i = 0;
        boolean flag = false;
        String inputData = (String) elem;

            if(i == 0) { remove(); return true;}
            if(i == size() - 1) { removeLast(); return true;}
            removeAt(i);
            return true;
    }

    /***
     * presents a string representation of the list
     * @return string representation of DLL.
     */
    @Override
    public String toString() {
        String tempString = "";
        if (head == null) {
            System.out.println("DLL is Emmpty");
        } else {
            Node temp = head;


            for (Node node : indices) {
                tempString += node.data ;
            }
            tempString += "null";
        }
        return tempString;
    }
}
