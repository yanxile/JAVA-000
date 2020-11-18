package com.homework.w06sat.homework06;

import com.homework.w06sat.homework04.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OriginalJdbc {

    private static final String JDBC_URL = "jdbc:h2:~/test";
    private static final String JDBC_USER = "sa";
    private static final String JDBC_PASSWORD = "sa";
    private static final String DRIVER_CLASS = "org.h2.Driver";

    public static Connection getConn() throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER_CLASS);
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    }

    public static void createTable() {
        try(Connection conn = getConn();) {
            try(Statement stmt = conn.createStatement();) {
                stmt.execute("create table t_student (id INT, name VARCHAR(50), primary key(id))");
                conn.commit();
            } catch (SQLException exception) {
                conn.rollback();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void dropTable() {
        try(Connection conn = getConn();) {
            try(Statement stmt = conn.createStatement();) {
                stmt.execute("drop table t_student");
                conn.commit();
            } catch (SQLException exception) {
                conn.rollback();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void createStudent(Student student) {
        try(Connection conn = getConn();) {
            try(PreparedStatement ps = conn.prepareStatement("insert into t_student (id , name) values (?,?)")) {
                ps.setObject(1, student.getId());
                ps.setObject(2, student.getName());
                ps.execute();
                conn.commit();
            } catch (SQLException exception) {
                conn.rollback();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void createStudentBatch(List<Student> studentList) {
        try(Connection conn = getConn();) {
            try(PreparedStatement ps = conn.prepareStatement("insert into t_student (id , name) values (?,?)")) {
                for(Student student : studentList) {
                    ps.setObject(1, student.getId());
                    ps.setObject(2, student.getName());
                    ps.addBatch();
                }
                ps.executeBatch();
                conn.commit();
            } catch (SQLException exception) {
                conn.rollback();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static List<Student> reviewStudent() {
        List<Student> students = new ArrayList<>();
        try(Connection conn = getConn();) {
            try(PreparedStatement ps = conn.prepareStatement("select id,name from t_student order by id")) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    students.add(new Student(rs.getInt(1), rs.getString(2)));
                }
            } catch (SQLException exception) {
                System.out.println("查询出错");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return students;
    }

    public static int updateStudent(Student student) {
        int i = 0;
        try(Connection conn = getConn();) {
            try(PreparedStatement ps = conn.prepareStatement("update t_student set name = ? where id = ?")) {
                ps.setObject(1, student.getName());
                ps.setObject(2, student.getId());
                i = ps.executeUpdate();
                conn.commit();
            } catch (SQLException exception) {
                conn.rollback();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return i;
    }

    public static void deleteStudent(int id) {
        try(Connection conn = getConn()) {
            try(PreparedStatement ps = conn.prepareStatement("delete from t_student where id = ?")) {
                ps.setObject(1, id);
                ps.execute();
                conn.commit();
            } catch (SQLException exception) {
                conn.rollback();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
