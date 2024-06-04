package com.poscodx.guestbook.repository;

import org.springframework.stereotype.Repository;

import com.poscodx.guestbook.repository.template.JdbcContext;

import guestbook.vo.GuestbookVo;

@Repository
public class GuestbookRepositoryWithJdbcContext {
	private JdbcContext jdbcContext;
	
	public GuestbookRepositoryWithJdbcContext(JdbcContext jdbcContext) {
		this.jdbcContext = jdbcContext;
	}
	
	public int deleteByNoAndPassword(GuestbookVo vo) {
		return jdbcContext.executeUpdate(
				"delete from guestbook where no = ? and password = ?", 
				new Object[] {vo.getNo(), vo.getPassword()});
	}
	
	public int insert(GuestbookVo vo) {
		return jdbcContext.executeUpdate(
				"insert into guestbook values(null, ?, ?, ?, now())", 
				new Object[] {vo.getName(), vo.getPassword(), vo.getContents()});
	}
}
