package com.poscodx.guestbook.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import guestbook.vo.GuestbookVo;

@Repository
public class GuestbookRepositoryWithRawJdbc {

	private DataSource dateSource;
	
	public GuestbookRepositoryWithRawJdbc(DataSource dataSource) {
		this.dateSource=dataSource;
	}
	
	public int insert(GuestbookVo vo) {
	      int result = 0;
	      
	      try (
	    	Connection conn = dateSource.getConnection();
	        PreparedStatement pstmt1 = conn.prepareStatement("insert into guestbook (name, password, contents, reg_date) values(?, ?, ?, now())");
	        PreparedStatement pstmt2 = conn.prepareStatement("select last_insert_id() from dual");
	      ){
	    	 //바인딩
	         pstmt1.setString(1, vo.getName());
	         pstmt1.setString(2, vo.getPassword());
	         pstmt1.setString(3, vo.getContents());
	         result = pstmt1.executeUpdate();
	         
	         ResultSet rs = pstmt2.executeQuery();
	         vo.setNo(rs.next() ? rs.getLong(1) : null);
	         rs.close();
	      } catch (SQLException e) {
	         System.out.println("error:"+e);
	      }
	      
	      return result;
	   }

	public List<GuestbookVo> findAll() {
		List<GuestbookVo> result = new ArrayList<>();
		
		try (
			Connection conn= dateSource.getConnection();
			PreparedStatement pstmt= conn.prepareStatement("select no, name, contents, date_format(reg_date, '%Y-%m-%d %H:%i:%s') as date from guestbook order by date desc");
			ResultSet rs= pstmt.executeQuery();
		) { 
		
			while(rs.next()) {
				Long no= rs.getLong(1);
				String name= rs.getString(2);
				String contents= rs.getString(3);
				String regDate=rs.getString(4);
				
				GuestbookVo vo= new GuestbookVo();
				vo.setNo(no);
				vo.setName(name);
				vo.setContents(contents);
				vo.setRegDate(regDate);
				
				result.add(vo);
			}
		
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	
		return result;
	}

	public void deleteByNoAndPassword(GuestbookVo vo) {

		try (
			Connection conn= dateSource.getConnection();
			PreparedStatement pstmt= conn.prepareStatement("delete from guestbook where no = ? and password= ?");
		) { 
			pstmt.setLong(1, vo.getNo());
			pstmt.setString(2, vo.getPassword());
			pstmt.executeUpdate();			
		} catch (SQLException e) {
				System.out.println("error:" + e);
		}
	}
}
