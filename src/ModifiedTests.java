import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


public class ModifiedTests {
    /**
     * Tests inserting one student into the heap.
     */
    @Test
    public void oneStudent() {
        MaxHeap heap = new MaxHeap(10);
        heap.insert(new Student("Susan", 3.5, 60));
        assertEquals(3.5, heap.getMax().gpa(), .000001);
    }

    /**
     * Tests inserting one student into the heap, and then deleting that student.
     */
    @Test
    public void deleteThatOneStudent() {
        MaxHeap heap = new MaxHeap(10);
        heap.insert(new Student("Susan", 3.5, 60));
        assertEquals(3.5, heap.extractMax().gpa(), .000001);
        assertEquals(0, heap.size());
    }

    /**
     * Tests inserting two students in the heap.
     */
    @Test
    public void twoStudents() {
        MaxHeap heap = new MaxHeap(10);
        heap.insert(new Student("James", 3.71, 60));
        heap.insert(new Student("John", 2.3, 48));
        assertEquals(2, heap.size());
        assertEquals("James", heap.extractMax().getName());
        assertEquals(1, heap.size());
    }

    /**
     * Tests inserting two students into the heap with the second student having a higher
     * GPA than the first.
     */
    @Test
    public void twoStudentsInReverseOrder() {
        MaxHeap heap = new MaxHeap(10);
        heap.insert(new Student("John", 2.3, 48));
        heap.insert(new Student("James", 3.71, 60));
        assertEquals(2, heap.size());
        assertEquals("James", heap.extractMax().getName());
        assertEquals(1, heap.size());
    }

    /**
     * Tests inserting two students with identical GPAs.
     */
    @Test
    public void twoStudentswithIdenticalGPA() {
        MaxHeap heap = new MaxHeap(10);
        heap.insert(new Student("John", 2.3, 48));
        heap.insert(new Student("James", 2.3, 60));
        assertEquals(2, heap.size());
        assertEquals("John", heap.extractMax().getName());
        assertEquals(1, heap.size());
    }

    /**
     * Tests inserting three {@code Students} into the heap.
     */
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
    public void threeStudentsIndexTest() {
        MaxHeap heap = new MaxHeap(10);
        heap.insert(new Student("John", 2.3, 48));
        assertEquals(0, heap.getMax().getIndex());
        heap.insert(new Student("David", 4.0, 55));
        assertEquals(0, heap.getMax().getIndex());
        heap.insert(new Student("James", 3.71, 60));
        assertEquals("David", heap.extractMax().getName());
        assertEquals(0, heap.getMax().getIndex());
        assertEquals("James", heap.extractMax().getName());
        assertEquals("John", heap.extractMax().getName());
        assertEquals(0, heap.size());
    }

    @Test
    public void addingGradeForUnenrolledStudent() {
        MaxHeap heap = new MaxHeap(10);
        heap.insert(new Student("John", 2.3, 48));
        heap.insert(new Student("David", 4.0, 55));
        heap.insert(new Student("James", 3.71, 60));
        Student daniel = new Student("Daniel");
        heap.addGrade(daniel, 3, 3);
        assertEquals(3, heap.size());
    }

    /**
     * Tests adding a couple of students into the heap before deleting all of them in order.
     */
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

    /**
     * Testing for exceptions. Unmodified from Dr. David Scot Taylor's code.
     */
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

    /**
     * Testing {@code changeKey} found in {@code addGrade}.
     * Modified by student from Dr. David Scot Taylor's code.
     */
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
        assertEquals(3, ken.getIndex());
        assertEquals(3, heap.getIndex(ken));
        assertEquals(6, tim.getIndex());
        assertEquals(6, heap.getIndex(tim));
        assertEquals(0, heap.getMax().getIndex());
        assertEquals(2.7, heap.extractMax().gpa(), .000001); // added by student
        assertEquals(0, heap.getMax().getIndex());
        assertEquals(2.5, heap.extractMax().gpa(), .000001);
        assertEquals(0, heap.getMax().getIndex());
        assertEquals(2.5, heap.extractMax().gpa(), .000001); // added by student
        assertEquals(0, heap.getMax().getIndex());
        assertEquals(2.4, heap.extractMax().gpa(), .000001);
        assertEquals(0, heap.getMax().getIndex());
        assertEquals(2.0, heap.extractMax().gpa(), .000001);
    }

    /**
     * Tests changeKey on a student who lowered their GPA.
     */
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
        assertEquals(0, heap.getMax().getIndex());
        assertEquals(3, heap.extractMax().gpa(), .000001);
        assertEquals(0, heap.getMax().getIndex());
        assertEquals(2.4, heap.extractMax().gpa(), .000001);
        assertEquals(0, heap.getMax().getIndex());
        assertEquals(2, heap.extractMax().gpa(), .000001);
    }

    /**
     * Tests conversion from a Java {@code Collection} to a {@code MaxHeap}.
     */
    @Test
    public void convertFromListTest() {
        List<Student> list = new LinkedList<>();

        for (int i = 0; i < 10; i++) {
            Student newStudent = new Student("Student " + i, Math.round(Math.random() * 400) / 100.0, 12);
            list.add(newStudent);
        }

        MaxHeap heap = new MaxHeap(list);
        int h_size = heap.size();
        assertEquals(10, h_size);
        for (int j = 0; j < h_size; ++j) {
            heap.extractMax();
            assertEquals(h_size - (j + 1), heap.size());
        }

        assertEquals(0, heap.size());
    }

    @Test
    public void changeGradeEmptyHeap() {
        MaxHeap heap = new MaxHeap(10);
        Student susan = new Student("Susan", 3.5, 60);
        heap.addGrade(susan, 4, 4);
        assertEquals(0, heap.size());
    }

}