/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package javaapplication1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author AryaDanu
 */

public class Coba extends Mysql {
    static int jml1=0;
    public static void main(String[]args){
        String b="";
        String a="";
        String c="";
        String d="";
//        
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sbd_takehome?zeroDateTimeBehavior=convertToNull",USER,PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT count(*) As jumlah FROM knj_dos");
            
            while ( rs.next() ) {
                d = rs.getString("jumlah");
            }
            conn.close();
        } catch (Exception e) {
        }
        int jumlah = Integer.parseInt(d);
//        
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sbd_takehome?zeroDateTimeBehavior=convertToNull",USER,PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT * FROM knj_kunci");
            
            while ( rs.next() ) {
                b = rs.getString("JAWABAN");
            }
            conn.close();
        } catch (Exception e) {
        }
//        
//        
        for(int jml=1;jml<jumlah+1;jml++){
//            
//            
//            
            try {
                
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sbd_takehome?zeroDateTimeBehavior=convertToNull",USER,PASS);
                Statement stmt = conn.createStatement();
//                ResultSet rs;
                String query="SELECT * FROM knj_dos limit ?,1";
                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setInt   (1,jml1);
                ResultSet rs=preparedStmt.executeQuery();
                while ( rs.next() ) {
                    a = rs.getString("JAWAB");
                    c = rs.getString("KD_DOS");
                }
                
                conn.close();
            } catch (Exception e) {
            }
//            
            int total=0,j=0,jawaban_kunci=0,berbeda=0,tidak_terjawab=0;
            int k=4;
//            String a="1111511111111111111111151";
//            b="1234123412341234123412341234123412341234123412341234123412341234123412341234123412341234123412344321";
for(int i=0;i<a.length();i++){
    char jwb_kd_dos[]=a.toCharArray();
    String jwb_kd_kunci=b.substring(j,k);
    int jawab_kd_dos=jwb_kd_dos[i]-'0';
    if(jawab_kd_dos>0 && jawab_kd_dos<=4){
        jawaban_kunci=Integer.parseInt(jwb_kd_kunci.substring(jawab_kd_dos-1,jawab_kd_dos));
    }else{
        if(jawab_kd_dos==0){
            jawaban_kunci=0;
            tidak_terjawab++;
        }else{
            jawaban_kunci=0;
            berbeda++;
        }
    }
    total=total+jawaban_kunci;
    j=j+4;
    k=k+4;
    if(i==a.length()-1){
        int jumlah_soal=25-tidak_terjawab;
        int jumlahkali4=jumlah_soal*4;
        Double Skore=total*1.00;
        Double rs=(Skore/jumlahkali4)*100;
        int rasio=(int) (rs+0);
        String grade="";
        if(rasio>75){
            grade="A";
        }else if(rasio<75 && rasio>=60){
            grade="B";
        }else if(rasio<60 && rasio>=45){
            grade="C";
        }else{
            grade="D";
        }
        try{
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sbd_takehome?zeroDateTimeBehavior=convertToNull",USER,PASS);
        String query = "UPDATE KNJ_DOS SET SKORE=?,RASIO=?,GRADE=? WHERE JAWAB=?";
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setInt   (1, total);
        preparedStmt.setInt   (2, rasio);
        preparedStmt.setString(3, grade);
        preparedStmt.setString(4, a);
        
        preparedStmt.executeUpdate();
        
        conn.close();
    }
            catch (Exception e)
            {
            }
            System.out.println(total+" "+jumlah_soal+" "+rasio +" "+grade);
            jml1=jml1+1;
            }
}
//
//
        }
    }
    
}
