package com.kh.member.model.service;

import java.sql.Connection;

import com.kh.member.exception.DuplicateMemberIdException;
import com.kh.member.exception.MemberIdToLargeException;
import com.kh.member.model.dao.MemberDao;
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
			throw new MemberIdToLargeException("아이디가 너무 길어요");
		}
		Connection conn = getConnection();
		
		MemberDao md = new MemberDao();
		if(md.idCheck(conn,member.getMemberId())) {
			throw new DuplicateMemberIdException("중복 아이디 입니다");
		}
		int result = md.signUp(conn,member);
	}


}
