package dbSample;

import java.awt.RenderingHints.Key;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.xml.crypto.dsig.keyinfo.KeyInfo;

public class DbConnectSample03 {

    public static void main(String[] args) {
       // データベース接続と結果表示のための変数
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            //１，ドライバーのクラスをJava上で読み込む
            Class.forName("com.mysql.cj.jdbc.Driver");
            //２，DBと接続する
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost/world?useSSL=false&allowPublicKeyRetrieval=true",
                    "root",
                    "Obito&8045"
                    );
            
            //３，DBとやり取りする窓口の作成 (statementオブジェクト）の作成
             stmt = con.createStatement();

            
            //４，５Select分の実行と結果を格納・代入
             System.out.print("検索キーワードを入力してください > ");
             String input = keyIn();
             
             
             String sql = "select * from country where Name = '" + input +"'";
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
    
    private static String keyIn() {
            String line = null;
            try {
                    BufferedReader key = new BufferedReader(new InputStreamReader(System.in));
                    line = key.readLine();
            }catch(IOException e) {
    }
             return line;
    }
}
            
        

    


