/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poketwofx;

import java.util.ArrayList;

/**
 *
 * @author MIGUEL RODRIGUEZ
 */
public class Teams {
        String teamID;
	ArrayList<Pokemon> pkmn;
	String TeamName;
	String TeamTrainer;
        public Teams(String teamid,ArrayList<Pokemon> pkmn,String TeamName, String TeamTrainer){
            this.teamID=teamid;
            this.pkmn=pkmn;
            this.TeamName=TeamName;
            this.TeamTrainer=TeamTrainer;
        }
    
}
