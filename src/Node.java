/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * A generic node for chaining.
 *
 * @param <T> the object stored in the list.
 * @author Ed Grzyb
 */
public class Node<T> {
    private T item;          // The nodes data.
    private Node<T> next;    // The next reference.
    /**
     * The default constructor. It sets the next reference to null.
     */
    public Node() {
        this.next = null;
    }
    /**
     * This is the overloaded constructor that sets the item to itm and the next
     * reference to null.
     *
     * @param itm the item stored in the node.
     */
    public Node(T itm) {
        this.item = itm;
        this.next = null;
    }
    /**
     * This is the overloaded constructor that sets the item to itm and the next
     * reference to nxt.
     *
     * @param item the item to be stored in the node.
     * @param next the reference to the next node in the chain.
     */
    public Node(T item, Node<T> next) {
        this.item = item;
        this.next = next;
    }
    /**
     * Set the item field of the node to item.
     *
     * @param item the item to store in the node.
     */
    public void setItem(T item) {
        this.item = item;
    }
    /**
     * Get the value stored in the item field of the node.
     *
     * @return the item stored in the node.
     */
    public T getItem() {
        return item;
    }
    /**
     * Set the value of the next reference.
     *
     * @param next the reference to the next node in the chain.
     */
    public void setNext(Node<T> next) {
        this.next = next;
    }
    /**
     * Return the reference to the next node in the chain.
     *
     * @return the reference to the next node in the chain.
     */
    public Node<T> getNext() {
        return this.next;
    }  
    /**
     * Set the value of the previous reference.
     *
     * @param previous the reference to the next node in the chain.
     */
}
