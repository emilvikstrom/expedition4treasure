package com.expeditionfortreasure.logic;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import com.expeditionfortreasure.logic.building.Building;
import com.expeditionfortreasure.logic.building.*;

/**
 * Singleton logic that handles all player progress
 */

public class GameLogic implements Serializable{

	private GameLogic() {
        init();
    }
	
	//Initiate one of each kind
	private void init(){
		city = new HashMap<Building.Type, Building>();
		city.put(Building.Type.FARM, new Farm());
		city.put(Building.Type.TAVERN, new Tavern());
		city.put(Building.Type.LIBRARY, new Library());
		city.put(Building.Type.BARRACKS, new Barrack());


        //TODO Ändra start guld
		gold = 5000;
	}
	
	public boolean buyBuilding(Building.Type building){

		if(gold >= city.get(building).getCurrentPrice() ){
			gold -= city.get(building).getCurrentPrice();
			city.get(building).buyOneLevel();
            return true;
		}
		else 
			return false;
	}
	
	public int getBuildingLevel(Building.Type building){
		return city.get(building).getLevel();
	}

    public int getGold() { return gold; }

	public int getBuildingPrice(Building.Type building){ return city.get(building).getCurrentPrice(); }

    public void newQuest(){
        if(currentQuest == null) {
            Log.d("Quest","Getting new quest");

            currentQuest = Quest.getNewQuest();
            Log.d("Quest","Got quest");
            //TODO CHANGE, ONLY FOR DEBUG
            completedQuests.add(currentQuest);
        }
    }

    public void abortQuest(){
        currentQuest = null;
    }

    public ArrayList<Quest> readQuest(){
        //TODO SUMTHINGSUMTHING ARAYLIST
        return completedQuests;
    }

    public Quest getCurrentQuest(){
        return currentQuest;
    }

    public void completeQuest(){
        //Make proper methods
        gold += currentQuest.reward;
        completedQuests.add(currentQuest);
        currentQuest = null;
    }

    public void readQuestLog(){
        for(Quest q : completedQuests){
            Log.v("QUEST", "" + q.number);
        }
    }

    public static GameLogic getInstance() {
        if(instance == null ) {
            instance = new GameLogic();
        }

        return instance;
    }

    private static GameLogic instance = null;
    private int gold;
    private HashMap<Building.Type, Building> city;
    private Quest currentQuest = null;
    private ArrayList<Quest> completedQuests = new ArrayList<Quest>();



}