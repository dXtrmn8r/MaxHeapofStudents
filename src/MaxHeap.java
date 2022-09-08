import java.util.ArrayList;
import java.util.Collection;

public class MaxHeap {
    private ArrayList<Student> students;

    public MaxHeap(int capacity) {
        students = new ArrayList<Student>(capacity);
    }

    public MaxHeap(Collection<Student> collection) {
        students = new ArrayList<Student>(collection);
        for (int i = size() / 2 - 1; i >= 0; i--) {
            maxHeapify(i);
        }
    }

    public Student getMax() {
        if (size() < 1) {
            throw new IndexOutOfBoundsException("No maximum value:  the heap is empty.");
        }
        return students.get(0);
    }

    public Student extractMax() {
        Student value = getMax();
        int lastIndex = size() - 1;
        Student tempStudent = students.get(lastIndex);
        tempStudent.setIndex(0);
        students.set(0, tempStudent);
        students.remove(size() - 1);
        maxHeapify(0);
        return value;
    }

    public int size() {
        return students.size();
    }

    public void insert(Student elt) {
        //Please write me.  I should add the given student into the heap,
        //following the insert algorithm from the videos.

        students.add(elt); // adds the student to the end of the ArrayList
        /*  Returns the heap into one that satisfies the Max-Heap (CLRS) property
            Using the bottom-up method from R. Sedgewick (2011)
         */
        int index = students.size() - 1;
        elt.setIndex(index);
        riseUp(index);

    }

    public void addGrade(Student elt, double gradePointsPerUnit, int units) {
        //Please write me.  I should change the student's gpa (using a method
        //from the student class), and then adjust the heap as needed using
        //the changeKey algorithm from the videos.

        double oldGPA = elt.gpa();
        // Adds a new grade for {@code elt}
        elt.addGrade(gradePointsPerUnit, units);

        int location = getIndex(elt);
        if (location != -1) {
            if (elt.gpa() > oldGPA) riseUp(location);
            else maxHeapify(location);
        }
    }

    public int getIndex(Student elt) {
        return elt.getIndex();
    }

    private int parent(int index) {
        return (index - 1) / 2;
    }

    private int left(int index) {
        return 2 * index + 1;
    }

    private int right(int index) {
        return 2 * index + 2;
    }

    private void swap(int from, int to) {
        Student oldStu = students.get(from);
        Student newStu = students.get(to);

        oldStu.setIndex(to);
        newStu.setIndex(from);

        students.set(from, newStu);
        students.set(to, oldStu);

        // newStu.setIndex(from);
        // oldStu.setIndex(to);
    }

    private void maxHeapify(int index) {
        int left = left(index);
        int right = right(index);
        int largest = index;
        if (left < size() && students.get(left).compareTo(students.get(largest)) > 0) {
            largest = left;
        }
        if (right < size() && students.get(right).compareTo(students.get(largest)) > 0) {
            largest = right;
        }
        if (largest != index) {
            swap(index, largest);
            maxHeapify(largest);
        }
    }

    /**
     * Restores the heap condition from the bottom up (R. Sedgewick, 2011).
     *
     * @param k index to compare with its parent
     */
    private void riseUp(int k) {
        while (k > 0 && students.get(k).compareTo(students.get(parent(k))) > 0) {
            swap(k, parent(k));
            k = parent(k);
        }
    }
}