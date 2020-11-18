package annotation;

import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@ToString
@Component("studentAnnotation")
public class Student {
    @Value("1")
    private int id;
    @Value("小王")
    private String name;

}
