/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * A simple empty queue exception.
 * 
 * @author Ed Grzyb
 */
public class EmptyQueueException extends Exception {
  /**
   * Just provide the default constructor. We don't want
   * to allow the user to choose the message associated
   * with the exception.
   */
  public EmptyQueueException() {
    super("Queue is empty.");
  }
}