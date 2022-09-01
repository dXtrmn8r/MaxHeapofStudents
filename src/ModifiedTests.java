import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


public class ModifiedTests {
    @Test
    public void oneStudent() {
        MaxHeap heap = new MaxHeap(10);
        heap.insert(new Student("Susan", 3.5, 60));
        assertEquals(3.5, heap.getMax().gpa(), .000001);
    }

    @Test
    public void deleteThatOneStudent() {
        MaxHeap heap = new MaxHeap(10);
        heap.insert(new Student("Susan", 3.5, 60));
        assertEquals(3.5, heap.extractMax().gpa(), .000001);
        assertEquals(0, heap.size());
    }

    @Test
    public void twoStudents() {
        MaxHeap heap = new MaxHeap(10);
        heap.insert(new Student("James", 3.71, 60));
        heap.insert(new Student("John", 2.3, 48));
        assertEquals(2, heap.size());
        assertEquals("James", heap.extractMax().getName());
        assertEquals(1, heap.size());
    }

    @Test
    public void twoStudentsInReverseOrder() {
        MaxHeap heap = new MaxHeap(10);
        heap.insert(new Student("John", 2.3, 48));
        heap.insert(new Student("James", 3.71, 60));
        assertEquals(2, heap.size());
        assertEquals("James", heap.extractMax().getName());
        assertEquals(1, heap.size());
    }

    @Test
    public void twoStudentswithIdenticalGPA() {
        MaxHeap heap = new MaxHeap(10);
        heap.insert(new Student("John", 2.3, 48));
        heap.insert(new Student("James", 2.3, 60));
        assertEquals(2, heap.size());
        assertEquals("John", heap.extractMax().getName());
        assertEquals(1, heap.size());
    }

    @Test
    public void threeStudents() {
        MaxHeap heap = new MaxHeap(10);
        heap.insert(new Student("John", 2.3, 48));
        heap.insert(new Student("David", 4.0, 55));
        heap.insert(new Student("James", 3.71, 60));
        assertEquals("David", heap.extractMax().getName());
        assertEquals("James", heap.extractMax().getName());
        assertEquals("John", heap.extractMax().getName());
        assertEquals(0, heap.size());
    }

    @Test
    public void aInsertAFewStudents() {
        MaxHeap heap = new MaxHeap(10);
        heap.insert(new Student("Susan", 3.5, 60));
        heap.insert(new Student("Ben", 3.4, 70));
        heap.insert(new Student("Reed", 4.0, 120));
        heap.insert(new Student("Johnny", 1.2, 50));
        assertEquals(4.0, heap.extractMax().gpa(), .000001);
        assertEquals(3.5, heap.extractMax().gpa(), .000001);
        heap.insert(new Student("Billy", 2.7, 20));
        assertEquals(3.4, heap.extractMax().gpa(), .000001);
        assertEquals(2.7, heap.extractMax().gpa(), .000001);
        assertEquals(1.2, heap.extractMax().gpa(), .000001);
    }

    @Test
    public void exceptionTest() {
        MaxHeap heap = new MaxHeap(10);
        heap.insert(new Student("Ben", 3.4, 70));
        assertEquals(3.4, heap.extractMax().gpa(), .000001);
        try {
            heap.extractMax();
            fail("You shouldn't reach this line, an IndexOutOfBoundsException should have been thrown.");
        } catch (IndexOutOfBoundsException except) {
            assertEquals(except.getMessage(), "No maximum value:  the heap is empty.");
        }

    }

    @Test
    public void changeKeyTest() {
        MaxHeap heap = new MaxHeap(10);
        Student susan = new Student("Susan", 3, 6);
        Student ben = new Student("Ben", 2.4, 10);
        Student reed = new Student("Reed", 3.3, 3);
        Student johnny = new Student("Johnny", 1, 4);

        // Added by student
        Student melvin = new Student("Melvin");
        Student dan = new Student("Dan", 2.7, 6);
        Student ken = new Student("Ken");
        heap.insert(susan);

        heap.insert(ben);
        heap.insert(johnny);
        heap.insert(reed);
        heap.insert(melvin);
        heap.insert(dan);
        heap.insert(ken);
        assertEquals(reed, heap.getMax());
        heap.addGrade(susan, 4, 3);  //should give her a 3.333333333 gpa
        assertEquals(susan, heap.getMax());
        assertEquals(3.33333333, heap.extractMax().gpa(), .000001);
        heap.addGrade(reed, .7, 3);  //should give him a 2.0
        heap.addGrade(johnny, 4, 4);  //should give him a 2.5
        Student tim = new Student("Tim", 2.5, 9);
        heap.insert(tim);
        assertEquals(2.7, heap.extractMax().gpa(), .000001); // added by student
        assertEquals(2.5, heap.extractMax().gpa(), .000001);
        assertEquals(2.5, heap.extractMax().gpa(), .000001); // added by student
        assertEquals(2.4, heap.extractMax().gpa(), .000001);
        assertEquals(2.0, heap.extractMax().gpa(), .000001);
    }

    @Test
    public void topStudentFallingTest() {
        MaxHeap heap = new MaxHeap(10);
        Student susan = new Student("Susan", 3, 6);
        heap.insert(susan);
        Student ben = new Student("Ben", 2.4, 10);
        heap.insert(ben);
        Student reed = new Student("Reed", 4, 3);
        heap.insert(reed);

        heap.addGrade(reed, 0, 3); // should give him 2.0
        assertEquals(3, heap.extractMax().gpa(), .000001);
        assertEquals(2.4, heap.extractMax().gpa(), .000001);
        assertEquals(2, heap.extractMax().gpa(), .000001);
    }

    @Test
    public void convertFromListTest() {
        List<Student> list = new LinkedList<>();

        for (int i = 0; i < 10; i++) {
            Student newStudent = new Student("Student " + i, Math.round(Math.random() * 400) / 100.0, 12);
            list.add(newStudent);
        }

        MaxHeap heap = new MaxHeap(list);
        int h_size = heap.size();
        for (int j = 0; j < h_size; ++j) {
            heap.extractMax();
            assertEquals(h_size - (j + 1), heap.size());
        }

        assertEquals(0, heap.size());
    }

}