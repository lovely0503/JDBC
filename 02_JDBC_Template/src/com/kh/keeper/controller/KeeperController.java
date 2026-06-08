package com.kh.keeper.controller;

import java.util.List;
import java.util.Map;

import com.kh.keeper.model.dto.KeeperDto;
import com.kh.keeper.model.service.KeeperService;
import com.kh.keeper.model.vo.Keeper;
/*
 * 컨트롤러의 역할
 * 
 * 클라이언트(View)의 요청 수신
 * 적절한 서비스단의 메소드 호출
 * 뷰에 데이터 반환
 * 
 */
public class KeeperController {
	
	public int insertKeeper(KeeperDto kd) {
		return new KeeperService().insertKeeper(kd);
	}
	
	public List<Keeper> selectKeeperList(){
		return new KeeperService().selectKeeperList();
	}
	
	public int updateKeeper(Map<String, String>keeper) {
		return new KeeperService().updateKeeper(keeper);
	}
	
	public int deleteKeeper(String keeperId) {
		return new KeeperService().deleteKeeper(keeperId);
	}
}
