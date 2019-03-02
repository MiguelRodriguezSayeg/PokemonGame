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
public class Pokemon {
        String Name, Type1, Type2, State;
        ArrayList<Moves> moves;
	String Evo;
	String PokemonID;
        boolean isAlive=true;
	int Accuracy;
	int phAttack,phDefense,spAttack,spDefense,HP,Height,Weight,speed,cHP;
        
        public Pokemon(String PokemonID,String name, String type1, String type2,String state, String Evo,  int speed, int Acc, int phAtt, int phDef, int spAtt, int spDef, int hp, int height, int weight){
            moves=new ArrayList<>();
            this.Name=name;
            this.State=state;
            this.Type1=type1;
            this.Type2=type2;
            this.speed=speed;
            this.Evo=Evo;
            this.PokemonID=PokemonID;
            this.Accuracy=Acc;
            this.phAttack=phAtt;
            this.phDefense=phDef;
            this.spAttack=spAtt;
            this.spDefense=spDef;
            this.HP=hp;
            this.Height=height;
            this.Weight=weight;
            this.cHP=hp;
        }
	public void addMove(Moves move){
            this.moves.add(move);   
	}
        public void setAlive(boolean alive){
            this.isAlive=alive;
        }
        public boolean getAlive(){
            return isAlive;
        }
        public String getName(){
            return Name;
        }
        public String getState(){
            return State;
        }
        public String getFirstType(){
            return Type1;
        }
        public String getSecondType(){
            return Type2;
        }
        public int getSpeed(){
            return speed;
        }
        public void setSpeed(int speed){
            this.speed=speed;
        }
	public ArrayList<Moves> getMoves(){
            return moves;
	}
        public String itEvolves(){
            return Evo;
        }
        public String getPokemonId(){
            return PokemonID;
        }
        public int getAccuracy(){
            return Accuracy;
        }
        public int getPhAttack(){
            return phAttack;
        }
        public int getPhDefense(){
            return phDefense;
        }
        public int getSpAttack(){
            return spAttack;
        }
        public int getSpDefense(){
            return spDefense;
        }
        public int getcHP(){
            return cHP;
        }
        public int getHP(){
            return HP;
        }
        public void setcHP(int diff){
        this.cHP=this.cHP+diff;
        if(this.cHP<=0){
            this.cHP=0;
            this.isAlive=false;
        }
        }
        public void setAccuracy(int diff){
        this.Accuracy=this.Accuracy+diff;
        }
        public void setPhAttack(int diff){
        this.phAttack=this.phAttack+diff;
        }
        public void setPhDefense(int diff){
        this.phDefense=this.phDefense+diff;
        }
        public void setSpAttack(int diff){
        this.spAttack=this.spAttack+diff;
        }
        public void setSpDefense(int diff){
        this.spDefense=this.spDefense+diff;
        }
        public void setState(String state){
            this.State=state;
        }
}
