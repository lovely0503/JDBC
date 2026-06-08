package com.kh.animal.model.dto;

import java.util.ArrayList;
import java.util.List;

public class KeeperDto {
	
	private String keeperId;
	private String keeperName;
	List<AnimalDto> animals = new ArrayList();
	
	public KeeperDto() {}
	public KeeperDto(String keeperId, String keeperName, List<AnimalDto> animals) {
		this.keeperId = keeperId;
		this.keeperName = keeperName;
		this.animals = animals;
	}
	public String getKeeperId() {
		return keeperId;
	}
	public void setKeeperId(String keeperId) {
		this.keeperId = keeperId;
	}
	public String getKeeperName() {
		return keeperName;
	}
	public void setKeeperName(String keeperName) {
		this.keeperName = keeperName;
	}
	public List<AnimalDto> getAnimals() {
		return animals;
	}
	public void setAnimals(List<AnimalDto> animals) {
		this.animals = animals;
	}
	
	
}
