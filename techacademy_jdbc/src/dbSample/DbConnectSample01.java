package dbSample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnectSample01 {

    public static void main(String[] args) {
       // データベース接続と結果表示のための変数
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            //１，ドライバーのクラスをJava上で読み込む
            Class.forName("com.mysql.cj.jdbc.Driver");
            //２，DBと接続する
            SELECT * FROM country WHERE Code = 'ABW';      con = DriverManager.getConnection(
                    "jdbc:mysql://localhost/world?useSSL=false&allowPublicKeyRetrieval=true",
                    "root",
                    "Obito&8045"
                    );
            
            //３，DBとやり取りする窓口の作成 (statementオブジェクト）の作成
             stmt = con.createStatement();

            
            //４，５Select分の実行と結果を格納・代入
             String sql = "SELECT * FROM country LIMIT 50";
             rs = stmt.executeQuery(sql);
            
            //６，結果の表示
            while( rs.next() ) {
                //Name列の値を取得
                String name = rs.getString("Name");
                //Population列の値を取得
                int population = rs.getInt("Population");
                //取得した値を表示
                System.out.println(name);
                System.out.println(population);
            }
            //　6-1 データの更新を行う。
            sql = "update country set Population = 105000 where Code = 'ABW'";
            int count = stmt.executeUpdate(sql);
            System.out.println(count);
            
        } catch (ClassNotFoundException e) {
            System.err.println("JDBCドライバーのロードに失敗しました。");
            e.printStackTrace();
        }catch(SQLException e) {
            System.err.println("データベースに異常が発生しました。");
            e.printStackTrace();
        }finally {
            //７，接続を閉じる
            if( rs != null) {
                try {
                    rs.close();
                }catch (SQLException e) {
                    System.err.println();
                    e.printStackTrace();
                    
                }
            if(stmt != null) {
                try {
                    stmt.close();
                }catch(SQLException e) {
                    System.err.println("Statementを閉じるときにエラーが発生しました。");
                    e.printStackTrace();
                }
            }
            if( con != null){
                try {
                    con.close();
                    }catch (SQLException e) {
                        System.err.println("データベース切断時にエラーが発生しました。");
                        e.printStackTrace();
                    }
            }
        }
    }
    }
}
            
        

    


