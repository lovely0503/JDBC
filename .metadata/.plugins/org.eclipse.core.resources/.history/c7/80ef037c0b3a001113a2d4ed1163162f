package com.kh.animal.model.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.kh.animal.common.Template;
import com.kh.animal.model.dao.KeeperDao;
import com.kh.animal.model.dto.AnimalDto;
import com.kh.animal.model.dto.KeeperDto;

public class KeeperService {
	
	//시간
	//쿼리 실행시간 0.1ms
	// +
	// 네트워크 왕복(Round Trip) / 자바서버 / DB서버
	//
	//+
	//SQL파싱 + 실행계획수립 -> DB가SQL받아서 분석하는비율
	//+
	//커넥션 점유 -> 쿼리가 전부 끝날때까지 커넥션을 계속 붙잡고 있어야함
	
	//시나리오 : 사육사가 100명정도
	//한번에 사육사 + 사육사담당 동물목록 조회 => DB커넥션 풀 20개
	public List<KeeperDto> selectKeeperAndAnimals(){
		SqlSession session = Template.getSqlSession();
		
		//쿼리 1번 - 사육사 전체조회
		/*
		List<KeeperDto> keepers = new KeeperDao().selectKeeperList(session);
		
		// 쿼리N번 - 각 사육사마다 동물 목록 조회
		for(KeeperDto keeper : keepers) {
			List<AnimalDto> animals = 
					new KeeperDao().selectAnimalList(session,keeper.getKeeperId());
			keeper.setAnimals(animals);
		}*/
		List <KeeperDto> keepers = new KeeperDao().findKeeperAndAnimals(session);
		session.close();
		
		return keepers;
		
	}
	
	public List<KeeperDto> selectKeeperByCondition(Map<String, String>arguments){
		SqlSession session = Template.getSqlSession();
		
		List<KeeperDto> keepers
		= new KeeperDao().selectKeeperByCondition(session,arguments);
		
		session.close();
		
		return keepers;
	}
	
	
	
	
}
