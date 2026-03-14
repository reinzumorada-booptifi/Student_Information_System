/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
/**
 *
 * @author jenniferobach
 */
public class StudentDAO {
public void addStudent(Student student) {
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = DBConnection.getConnection();
            String sql = "INSERT INTO students(student_id, first_name, last_name, age, email) VALUES (?,?,?,?,?)";
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, student.getStudentId());
            stmt.setString(2, student.getFirstName());
            stmt.setString(3, student.getLastName());
            stmt.setInt(4, student.getAge());
            stmt.setString(5, student.getEmail());

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Student Added Successfully!");

        } catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error adding student: " + e.getMessage());
        } finally {
            // Only close statement, not connection
            try {
                if (stmt != null) stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public List<Student> getAllStudents(){
        List<Student> students = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            String sql = "SELECT * FROM students";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while(rs.next()){
                Student student = new Student();
                student.setStudentId(rs.getInt("student_id"));
                student.setFirstName(rs.getString("first_name"));
                student.setLastName(rs.getString("last_name"));
                student.setAge(rs.getInt("age"));
                student.setEmail(rs.getString("email"));
                students.add(student);
            }

        } catch(Exception e){
            e.printStackTrace();
        } finally {
            // Close only ResultSet and Statement, not Connection
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return students;
    }
    
    public void updateStudent(Student student) {
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = DBConnection.getConnection();
            String sql = "UPDATE students SET first_name=?, last_name=?, age=?, email=? WHERE student_id=?";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, student.getFirstName());
            stmt.setString(2, student.getLastName());
            stmt.setInt(3, student.getAge());
            stmt.setString(4, student.getEmail());
            stmt.setInt(5, student.getStudentId());

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Student Updated Successfully!");

        } catch(Exception e){
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public void deleteStudent(int studentId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = DBConnection.getConnection();
            String sql = "DELETE FROM students WHERE student_id=?";
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, studentId);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Student Deleted Successfully!");

        } catch(Exception e){
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}