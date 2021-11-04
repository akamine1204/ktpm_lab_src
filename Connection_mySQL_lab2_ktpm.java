//Truy van phpmyadmin bang qua netbeans và xây dựng chương trình java với các lệnh SQL cơ bản (SELECT,INSERT,UPDATE,DELETE)
package connection_mysql;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Connection_mySQL {
    
    
    
//method Update
public static void update() {
 //Sửa password của nơi có ID là ... thành ...
        String sqlUpdate = "UPDATE first_table "
                + "SET password = ? "
                + "WHERE ID = ?";
 
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/database","root","");
                PreparedStatement pstmt = conn.prepareStatement(sqlUpdate)) {
 
            // chuẩn bị dữ liệu để update
            String password = "duchoang12042001";
            int id = 20198191;
            pstmt.setString(1, password);
            pstmt.setInt(2, id);
 
            int rowAffected = pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
}
    
    
    
//method Insert
    public static int insertfirst_table(String userName, 
                                       String password) {
        ResultSet resu1 = null;
        int candidateId = 0;
         
        String sql = "INSERT INTO first_table(userName,password) "
                   + "VALUES(?,?)";
         
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/database","root","");
             PreparedStatement pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);) {
            pstmt.setString(1, userName);
            pstmt.setString(2, password);
            int rowAffected = pstmt.executeUpdate();
            if(rowAffected == 1)
            {
                // auto recrement ID 
                resu1 = pstmt.getGeneratedKeys();
                if(resu1.next())
                    candidateId = resu1.getInt(1);
 
            }
        System.out.println("Them co so du lieu thanh cong voi ID la"+candidateId);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if(resu1 != null)  resu1.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
         
        return candidateId;
    }
    
    
    //method Delete
    //xóa candidate với ID là...
    public static void delete(int ID){
        try{
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/database","root","");
            Statement stmt =conn.createStatement();
            String sql="delete from first_table where ID="+ID;
            stmt.executeUpdate(sql);
            System.out.println("\nXoa co so du lieu thanh cong voi ID: "+ID);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    
    //method Select viết trong hàm main
    
    
    
    
    //hàm main
    public static void main(String[] args) {
    // Truy vấn đến database trên phpmyadmin    
        java.sql.Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try{
            String dbURL = "jdbc:mysql://localhost/database";
            String username = "root";
            String password ="";
            // Tạo kết nối
            conn = DriverManager.getConnection(dbURL, username, password);
            if(conn!=null){
                System.out.println("Kết nối thành công");
            }
            
            //Câu lệnh xem dữ liệu
            String sql = "select * from first_table";
            //Tạo đối tượng thực thi câu lệnh Select
            st = conn.createStatement();
            // Thực thi 
            rs = st.executeQuery(sql);
            
            //Nếu không có dữ liệu trong bảng
            if(rs.isBeforeFirst()==false){
                JOptionPane.showMessageDialog(null  ,"Bảng không có dữ liệu!");
                return;
            }
            // Xử lý dữ liệu
            while(rs.next()){
                //Sử dụng phương thức rs.getXX("Tên cột trong bảng")
            }
        }catch (SQLException e){
            e.printStackTrace();
    }
        
        
        
        //Insert
//        int id = insertfirst_table("ngominhdat20198165","datnm198165");
//        int id1 = insertfirst_table("dohuuducmanh20198188","manhdhd198188");
//        int id2 = insertfirst_table("trantrungdung20198171","dungtt198171");
        int id3 = insertfirst_table("leducmanh20198189","manhld198189");
        
        //Update
        update();
        
        
        //Delete
        //delete(20198195);
        


        
        // Select (duyệt table trên database)
        String sql = "SELECT ID, userName, password " +
                     "FROM first_table";
         
        try (Connection conn1 = DriverManager.getConnection("jdbc:mysql://localhost/database","root","");
             Statement stmt  = conn1.createStatement();
             ResultSet resu    = stmt.executeQuery(sql)) {
            
            // loop through the result set
            while (resu.next()) {
                System.out.println(resu.getString("ID") + "\t" + 
                                   resu.getString("password")  + "\t" +
                                   resu.getString("userName"));
                     
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
