/**
        * Queue Interface
        * CS 108 Spring 202
        * @author Anthony Norderhaug
 */

public interface QueueInterface {

    /**
     * The enQueue method inserts an element
     * in the Queue and return true, if successful
     * The element is always entered at the end
     * of the queue.
     *
     * @param Integer value to be inserted
     * @return boolean true, if successful, false otherwise
     */
    public boolean enQueue(Integer input);

    /**
     * The deQueue deletes an element in the
     * Queue, and returns the element.
     * The element is always removed from the
     * front of the queue.
     *
     * @return Integer value of the removed element
     */
    public Integer deQueue();

    /**
     * The peek method returns the head (or the
     * first element) of the Queue
     *
     * @return Integer value of the first element
     */
    public Integer peek();

    /**
     * The isEmpty() method returns true if the
     * Queue is empty, false otherwise
     *
     * @return boolean true, if empty, false otherwise
     */
    public boolean isEmpty();
}