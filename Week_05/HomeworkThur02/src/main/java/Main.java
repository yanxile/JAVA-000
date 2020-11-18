import annotation.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import xml.Klass;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml" );
        Student studentAnnotation = (Student) context.getBean("studentAnnotation");
        System.out.println("studentAnnotation = " + studentAnnotation.toString());
        Student studentXml = (Student) context.getBean("studentXml");
        System.out.println("studentXml = " + studentXml.toString());
        Klass klass = (Klass) context.getBean("klass");
        System.out.println("klass = " + klass.toString());
    }
}
