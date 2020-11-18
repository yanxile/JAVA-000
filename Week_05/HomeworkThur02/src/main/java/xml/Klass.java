package xml;

import lombok.Data;
import lombok.ToString;
import annotation.Student;

import java.util.List;

@Data
@ToString
public class Klass {

    List<Student> students;
    
}
