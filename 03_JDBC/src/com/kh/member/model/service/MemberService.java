package com.kh.member.model.service;

import java.sql.Connection;
import java.util.List;

import static com.kh.keeper.common.JdbcTemplate.*;
import com.kh.member.exception.DuplicateMemberIdException;
import com.kh.member.exception.MemberIdToLargeException;
import com.kh.member.model.dao.MemberDao;
import com.kh.member.model.dto.BoardDto;
import com.kh.member.model.dto.LoginResponse;
import com.kh.member.model.dto.MemberDto;

public class MemberService {
	
	public boolean idCheck(String memberId) {
		Connection conn = getConnection();
		boolean result = new MemberDao().idCheck(conn,memberId);
		close(conn);
		return result;
	}
	
	public int signUp(MemberDto member) {
		
		if(member.getMemberId().length() > 30) {
			throw new MemberIdToLargeException("아이디가 너무길어요");
		}
		
		Connection conn = getConnection();
		
		MemberDao md = new MemberDao();
		if(md.idCheck(conn, member.getMemberId())) {
			throw new DuplicateMemberIdException("중복 아이디입니다");
		}
		
		int result = md.signUp(conn,member);
		
		if(result > 0) {
				commit(conn);
		}
			close(conn);
		
		return result;
		
	}
	
	public LoginResponse login(MemberDto loginmember) {
		Connection conn = getConnection();
		
		//1. INSERT를 먼저하고 SELECT를한다
		//2. SELECT를 먼저하고 INSERT를한다
		/*	
		int result = new MemberDao().insertCheck(conn,member);
		LoginResponse lr = new MemberDao().login(conn,member);
		
		if(lr != null && result >0) {
			commit(conn);
		}*/
		/*
		LoginResponse lr = new MemberDao().login(conn,member);
		if(lr != null) {
			new MemberDao()
		}
		*/
		
		LoginResponse lr = new MemberDao().login(conn,loginmember);
		if(lr != null) {
			int result = new MemberDao().loginCheck(conn,loginmember.getMemberId());
			
			if(result > 0) {
				commit(conn);
			}
		}
		close(conn);
		return lr;
	}
	
	public int selectInsertCount(String memberId) {
		Connection conn = getConnection();
		
		int result = new MemberDao().selectInsertCount(conn,memberId);
		
		close(conn);
		
		return result;
	}
	
	public int insertBoard(BoardDto board) {
		Connection conn = getConnection();
		
		int result = new MemberDao().insertBoard(conn,board);
		
		if(result > 0) {
			commit(conn);
		}
		close(conn);
		
		return result;
	}
	
	public List<BoardDto> selectBoardList(){
		Connection conn = getConnection();
		List<BoardDto> boards = new MemberDao().selectBoardList(conn);
		close(conn);
		return boards;
		
	}
	
	public BoardDto selectBoard(String boardNo) {
		Connection conn = getConnection();
		BoardDto board = new MemberDao()
							.selectBoard(conn,Integer.parseInt(boardNo));
		close(conn);
		return board;
		
	}

}
