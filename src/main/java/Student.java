
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class Student {

    public List<Course> uniqCourses(List<Student> studentStream) {
        return studentStream.stream()
                .map(x -> x.getAllCourses())
                .flatMap(x -> x.stream())
                .distinct()
                .collect(Collectors.toList());
    }

    public List<Student> iniqStudents(List<Student> studentStream) {
        return studentStream.stream()
                .sorted(Comparator.comparing(p -> p.getAllCourses().size(), Comparator.reverseOrder()))
                .limit(3)
                .collect(Collectors.toList());
    }

    public List<Student> listStudentsOnCourse(List<Student> studentStream, Course course) {
        return studentStream.stream()
                .filter(p -> p.getAllCourses().contains(course))
                .collect(Collectors.toList());
    }

    public interface Student {
        String getName();

        List<Course> getAllCourses();
    }

    public interface Course {
        String getName();
    }
}