public class MyArray<E> {

    private int size;
    private E[] elements;

    private static final int DEFAULT_CAPACITY = 10; // 初始容量
    private static final int ELEMENT_NOT_FOUND = -1;


    public MyArray() {
        this(DEFAULT_CAPACITY);
    }

    public MyArray(int capacity) {
        capacity =  (capacity < DEFAULT_CAPACITY) ? DEFAULT_CAPACITY : capacity;
        elements = (E[]) new Object[capacity];
    }

    int size(){
        return size;
    }


    public boolean isEmpty(){
        return size==0;
    }
    public boolean contains (E element){
        return indexOf(element) != ELEMENT_NOT_FOUND;

    }


     public void add (E element){

        add(size,element);
    }

    E get (int index){
        return elements[index];
    }

    E set (int index, E element){
        E old = elements[index];
        elements[index] = element;
        return  old;

    }

    public void add (int index, E element){

        rangeCheckForAdd(index); // 检查下标越界
        ensureCapacity(size + 1); // 确保容量够大
        for(int i = size -1;i > index ;i--){
            elements[i+1] = elements[i];
        }
        elements[index] = element;
        size++;



    }


    public E remove (int index){
        E old = elements[index];

        for (int i = index; i < size -1  ; i++) {
            elements[i] = elements[i+1];
        }
        elements[--size] = null;
        return  old;


    }

    public int indexOf (E element){
        if (element ==null){
            for (int i = 0; i < size; i++) {
                if (elements[i]==null)
                return i;
            }
        }else {
            for (int i = 0; i < size ; i++) {
                if (elements[i].equals(element))
                    return i;
            }
        }
  return ELEMENT_NOT_FOUND;

    }

    void clear (){
        for (int i = 0; i < size; i++) {
            elements[i] =null;
        }
        size = 0;
    }


    private void ensureCapacity(int capacity){
        int oldCapacity = elements.length;
        if(oldCapacity >= capacity) return;
        // 新容量为旧容量的1.5倍
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        E[] newElements = (E[])new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i]; // 拷贝原数组元素到新数组
        }
        elements = newElements;
        System.out.println("size="+oldCapacity+", 扩容到了"+newCapacity);
    }


    private void outOfBounds(int index) {
        throw new IndexOutOfBoundsException("Index:" + index + ", Size:" + size);
    }
    // 检查add()的下标越界(可以在size位置添加元素)
    private void rangeCheckForAdd(int index) {
        if (index < 0 || index > size) {
            outOfBounds(index);
        }
    }

    public String toString() {
        // 打印形式为: size=5, [99, 88, 77, 66, 55]
        StringBuilder string = new StringBuilder();
        string.append("size=").append(size).append(", [");
        for (int i = 0; i < size; i++) {
            if(0 != i) string.append(", ");
            string.append(elements[i]);
        }
        string.append("]");
        return string.toString();
    }

}
