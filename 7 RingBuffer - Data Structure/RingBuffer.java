/**
 * Program 7 - RingBuffer
 * Implementation of a queue using an Integer array
 * @author Anthony Norderhaug
 * @date 4/19/2020
 */
public class RingBuffer implements QueueInterface{
    Integer[] buffer;
    int size = 0;
    int front = -1;
    int rear = -1;

    /**
     * default Constructor, allocates 10 spaces for Integer array
     */
    public RingBuffer() {
        buffer = new Integer[10];
    }

    /**
     * parameterized Constructor, allocates array space based on input
     *
     * @param input                 int specifying array space allocation
     */
    public RingBuffer(int input) {
        buffer = new Integer[input];
    }

    /**
     * isEmpty() returns t/f based on if array is empty
     *
     * @return                      boolean specifying if empty or not
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * enQueue() takes Integer parameter, assigns 1 space ahead of rear, and increases size. If rear is last index, rear
     * is initialized to 0 and parameter is assigned there. Special case of initializing front to 0 for first enQueue()
     * call. Returns t/f based on if enQueue() is successful
     *
     * @param element               Integer to be added to queue
     * @return                      boolean indicating if method is successful
     */
    public boolean enQueue(Integer element) {
        if (front == -1) {
            front = 0;
        }

        if (!(isFull())) {
            if (rear == getCapacity() - 1) {
                rear = 0;
                buffer[rear] = element;
            } else {
                rear++;
                buffer[rear] = element;
            }
            size++;
            return true;
        }
        return false;
    }

    /**
     * deQueue() identifies element to be removed, adjusts front appropriately, and decreases size. If front is last
     * index, front is initialized to 0. Returns specific element removed or null if queue was initially empty
     *
     * @return                      Integer representing element removed, or null
     */
    public Integer deQueue() {
        Integer elementRemoved;

        if (!(isEmpty())) {
            elementRemoved = buffer[front];
            if (front == getCapacity() - 1) {
                front = 0;
            }
            else {
                front++;
            }
            size--;
            return elementRemoved;
        }
        return null;
    }

    /**
     * peek() returns null if queue is empty, returns otherwise queue's front element
     *
     * @return                      Integer representing queue's front element, or null
     */
    public Integer peek() {
        if (isEmpty()) {
            return null;
        }
        return buffer[front];
    }

    /**
     * isFull() returns boolean evaluation of whether queue is full
     *
     * @return                      boolean indicating if queue is full
     */
    public boolean isFull() {
        return size == buffer.length;
    }

    /**
     * last() returns queue's last element, located at rear
     *
     * @return                      Integer representing queue's rear element
     */
    public Integer last() {
        return buffer[rear];
    }

    /**
     * getArray() returns Integer array used for queue's implementation
     *
     * @return                      Integer array for queue's implementation
     */
    public Integer[] getArray() {
        return buffer;
    }

    /**
     * getSize() returns int size representing number of elements in queue
     *
     * @return                      int representing number of elements
     */
    public int getSize() {
        return size;
    }

    /**
     * getCapacity() returns int representing space allocated in Integer array
     *
     * @return                      int representing array space allocated
     */
    public int getCapacity() {
        return buffer.length;
    }

    /**
     * toString() (overloaded) identifies space between front and rear indices, concatenates elements between to string
     * outputString, and returns
     *
     * @return                      String containing all elements between front and rear indices
     */
    public String toString() {
        String outputString = "";
        int currIndex = front;

        while (currIndex != rear) {
            outputString += buffer[currIndex] + ", ";
            if (currIndex == getCapacity() - 1) {
                currIndex = 0;
            } else {
                currIndex++;
            }
        }
        outputString += buffer[currIndex];

        return outputString;
    }

    /**
     * getIdentificationString() returns student info in String
     *
     * @return                  "Program 7, Anthony Norderhaug"
     */
    public static String getIdentificationString() {
        return "Program 7, Anthony Norderhaug";
    }
}
