package com.homework.w06sat.homework06;

import com.homework.w06sat.homework04.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class HikariDao {

    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;

    public Connection getConn() throws ClassNotFoundException, SQLException {
        return this.dataSource.getConnection();
    }

    public void createTable() {
        try(Connection conn = this.getConn();) {
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

    public void dropTable() {
        try(Connection conn = this.getConn();) {
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

    public void createStudent(Student student) {
        try(Connection conn = this.getConn();) {
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

    public void createStudentBatch(List<Student> studentList) {
        try(Connection conn = this.getConn();) {
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

    public List<Student> reviewStudent() {
        List<Student> students = new ArrayList<>();
        try(Connection conn = this.getConn();) {
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

    public int updateStudent(Student student) {
        int i = 0;
        try(Connection conn = this.getConn();) {
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

    public void deleteStudent(int id) {
        try(Connection conn = this.getConn()) {
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
