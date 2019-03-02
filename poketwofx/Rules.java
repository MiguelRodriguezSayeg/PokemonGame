/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poketwofx;

import java.util.ArrayList;
import java.util.Random;

public class Rules {
    public int decideDamage(Moves selected, Pokemon attacker, Pokemon defender){
        
        int damage=0;
        int modifier=(int) Math.round((double)criticalRandom()*(randomRandom()*(double)typeEffectiveness(selected.type.trim(),defender.getFirstType().trim(),defender.getSecondType().trim())));
        if(selected.getMoveType().trim()=="Physical"){
        damage=(int) Math.round((((42)*selected.acc*(attacker.phAttack/defender.phDefense))/50)+2)*modifier;
        }
        else{
        damage=(int) Math.round((((42)*selected.acc*(attacker.spAttack/defender.spDefense))/50)+2)*modifier;
        }
        return damage;
    }
    public int criticalRandom(){
        int temp = (Math.random() <= 0.5) ? 1 : 2;
        return temp;
    }
    public double randomRandom(){
        Random r = new Random();
        double rand = 0.85 + (1 - 0.85) * r.nextDouble();
        return rand;
    }
    public int typeEffectiveness(String movtype,String receivertype1, String receivertype2){
        int i=1;
        System.out.println(movtype);
        switch (movtype) {
            case "Fire":  if("Grass".equals(receivertype1)||"Grass".equals(receivertype2)){
                          i=i*2;
                          }
                          if("Bug".equals(receivertype1)||"Bug".equals(receivertype2)){
                          i=i*2;
                          }
                          if("Steel".equals(receivertype1)||"Steel".equals(receivertype2)){
                          i=i*2;
                          }
                          if("Ice".equals(receivertype1)||"Ice".equals(receivertype2)){
                          i=i*2;
                          }
                     break;
            case "Water":  
                          System.out.println("aguita y el movimiento es tipo"+movtype+"el tipo 1 es "+receivertype1+", el tipo 2 es "+receivertype2);
                          if("Fire".equals(receivertype1)||"Fire".equals(receivertype2)){
                          i=i*2;
                          
                          }
                          if("Ground".equals(receivertype1)||"Ground".equals(receivertype2)){
                          i=i*2;
                          }
                          if("Rock".equals(receivertype1)||"Rock".equals(receivertype2)){
                          i=i*2;
                          }
                     break;
            case "Grass": System.out.println("hola soy pasto");
                          if("Water".equals(receivertype1)||"Water".equals(receivertype2)){
                          i=i*2;
                          }
                          if("Ground".equals(receivertype1)||"Ground".equals(receivertype2)){
                          i=i*2;
                          }
                          if("Rock".equals(receivertype1)||"Rock".equals(receivertype2)){
                          i=i*2;
                          }
                     break;
            case "Electric": if("Water".equals(receivertype1)||"Water".equals(receivertype2)){
                          i=i*2;
                          }
                          if("Ground".equals(receivertype1)||"Ground".equals(receivertype2)){
                          i=i*0;
                          }
                          if("Rock".equals(receivertype1)||"Rock".equals(receivertype2)){
                          i=i*2;
                          }
                     break;
            case "Fairy": if("Dragon".equals(receivertype1)||"Dragon".equals(receivertype2)){
                          i=i*2;
                          }
                          if("Fighting".equals(receivertype1)||"Fighting".equals(receivertype2)){
                          i=i*2;
                          }
                          if("Dark".equals(receivertype1)||"Dark".equals(receivertype2)){
                          i=i*2;
                          }
                     break;
            case "Rock":  if("Bug".equals(receivertype1)||"Bug".equals(receivertype2)){
                          i=i*2;
                          }
                          if("Fire".equals(receivertype1)||"Fire".equals(receivertype2)){
                          i=i*2;
                          }
                          if("Ice".equals(receivertype1)||"Ice".equals(receivertype2)){
                          i=i*2;
                          }
                          if("Flying".equals(receivertype1)||"Flying".equals(receivertype2)){
                          i=i*2;
                          }
                     break;
            case "Normal":if("Ghost".equals(receivertype1)||"Ghost".equals(receivertype2)){
                          i=i*0;
                          }
                     break;
            case "Steel": if("Fairy".equals(receivertype1)||"Fairy".equals(receivertype2)){
                          i=i*2;
                          }
                          if("Rock".equals(receivertype1)||"Rock".equals(receivertype2)){
                          i=i*2;
                          }
                          if("Ice".equals(receivertype1)||"Ice".equals(receivertype2)){
                          i=i*2;
                          } 
                     break;
            case "Ice":  if("Bug".equals(receivertype1)||"Bug".equals(receivertype2)){
                          i=i*2;
                          }
                          if("Fire".equals(receivertype1)||"Fire".equals(receivertype2)){
                          i=i*2;
                          }
                          if("Ice".equals(receivertype1)||"Ice".equals(receivertype2)){
                          i=i*2;
                          }
                          if("Flying".equals(receivertype1)||"Flying".equals(receivertype2)){
                          i=i*2;
                          }
                     break;
            case "Ground":if("Electric".equals(receivertype1)||"Electric".equals(receivertype2)){
                          i=i*2;
                          }
                          if("Rock".equals(receivertype1)||"Rock".equals(receivertype2)){
                          i=i*2;
                          }
                          if("Steel".equals(receivertype1)||"Steel".equals(receivertype2)){
                          i=i*2;
                          }
                          if("Fire".equals(receivertype1)||"Fire".equals(receivertype2)){
                          i=i*2;
                          }
                          if("Poison".equals(receivertype1)||"Poison".equals(receivertype2)){
                          i=i*2;
                          }
                          if("Flying".equals(receivertype1)||"Flying".equals(receivertype2)){
                          i=i*0;
                          }
                     break;
            case "Ghost": if("Ghost".equals(receivertype1)||"Ghost".equals(receivertype2)){
                          i=i*2;
                          }
                          if("Psychic".equals(receivertype1)||"Psychic".equals(receivertype2)){
                          i=i*2;
                          }
                          if("Normal".equals(receivertype1)||"Normal".equals(receivertype2)){
                          i=i*0;
                          }
                     break;
            case "Psychic":if("Fighting".equals(receivertype1)||"Fighting".equals(receivertype2)){
                          i=i*2;
                          }
                          if("Poison".equals(receivertype1)||"Poison".equals(receivertype2)){
                          i=i*2;
                          }
                          if("Dark".equals(receivertype1)||"Dark".equals(receivertype2)){
                          i=i*0;
                          }
                     break;
            case "Poison": if("Fairy".equals(receivertype1)||"Fairy".equals(receivertype2)){
                          i=i*2;
                          }
                          if("Grass".equals(receivertype1)||"Grass".equals(receivertype2)){
                          i=i*2;
                          }
                          if("Steel".equals(receivertype1)||"Steel".equals(receivertype2)){
                          i=i*0;
                          }
                    break;
            case "Flying": if("Grass".equals(receivertype1)||"Grass".equals(receivertype2)){
                          i=i*2;
                          }
                          if("Bug".equals(receivertype1)||"Bug".equals(receivertype2)){
                          i=i*2;
                          }
                          if("Fighting".equals(receivertype1)||"Fighting".equals(receivertype2)){
                          i=i*2;
                          }
                    break;
            case "Dragon": if("Dragon".equals(receivertype1)||"Dragon".equals(receivertype2)){
                          i=i*2;
                          }
                          if("Fairy".equals(receivertype1)||"Fairy".equals(receivertype2)){
                          i=i*0;
                          }
                    break;
            case "Bug": if("Psychic".equals(receivertype1)||"Psychic".equals(receivertype2)){
                          i=i*2;
                          }
                          if("Grass".equals(receivertype1)||"Grass".equals(receivertype2)){
                          i=i*2;
                          }
                          if("Dark".equals(receivertype1)||"Dark".equals(receivertype2)){
                          i=i*2;
                          }
                    break;
            case "Fighting": if("Ghost".equals(receivertype1)||"Ghost".equals(receivertype2)){
                          i=i*0;
                          }
                          if("Steel".equals(receivertype1)||"Steel".equals(receivertype2)){
                          i=i*2;
                          }
                          if("Normal".equals(receivertype1)||"Normal".equals(receivertype2)){
                          i=i*2;
                          }
                          if("Ice".equals(receivertype1)||"Ice".equals(receivertype2)){
                          i=i*2;
                          }
                          if("Dark".equals(receivertype1)||"Dark".equals(receivertype2)){
                          i=i*2;
                          }
                          if("Rock".equals(receivertype1)||"Rock".equals(receivertype2)){
                          i=i*2;
                          }
                          
                    break;
            default: i=i*1;
                     break;
        }
        System.out.println("El ataque fue x"+i+" de efectivo.");
        return i;
    }
}
