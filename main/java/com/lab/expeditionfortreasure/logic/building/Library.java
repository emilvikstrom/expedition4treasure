package com.lab.expeditionfortreasure.logic.building;

public class Library extends Building {

	public Library() {
		super(350);
		// TODO Auto-generated constructor stub
	}
	@Override
	public Type getType(){
		return Type.LIBRARY;
	}

}
