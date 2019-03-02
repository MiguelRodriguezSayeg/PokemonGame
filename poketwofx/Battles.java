package poketwofx;

import static java.lang.Integer.max;
import java.sql.SQLException;
import java.util.ArrayList;

public final class Battles {
    Trainers t1,t2;
    boolean gameOver=false;
    Trainers winner;
    Trainers loser;
    DatabaseConnections db;
    public Battles(Trainers me) throws ClassNotFoundException, SQLException{
        this.t1=me;
        db=new DatabaseConnections();
        this.t2=getFoe();
    }
    public Trainers returnWinner(){
        return winner;
    }
    public Trainers returnLoser(){
        return winner;
    }
    public Trainers getFoe() throws ClassNotFoundException, SQLException{
        ArrayList<String> pokemon=new ArrayList<String>();
        ArrayList<String> movearray=new ArrayList<String>();;
        ArrayList<String> pokemonstats=new ArrayList<String>();
        ArrayList<Pokemon> pokemon2=new ArrayList<Pokemon>();
        ArrayList<Moves> movelist=new ArrayList<Moves>();
        Pokemon pk;
        Moves move;
        Teams t;
        int max=db.getMaxPokemon();
        int random=0;
        for(int x=0;x<t1.Team.pkmn.size();x++){
               random=(int) (Math.random() * (max - 1))+1;
               pokemon.add(db.getRandomPokemon(random).trim());
        }
        System.out.println(pokemon);
        
        for(int x=0;x<pokemon.size();x++){
                    pokemonstats=db.getPokemonStats(pokemon.get(x));
                    movelist.clear();
                    for(int y=4;y<8;y++){
                        movearray=db.getMoveStats(pokemonstats.get(y));
                        movelist.add(new Moves(movearray.get(0),movearray.get(1),movearray.get(2),Integer.valueOf(movearray.get(3)),movearray.get(4)));
                        
                    }
                    pokemon2.add(new Pokemon(pokemonstats.get(0),pokemonstats.get(1),pokemonstats.get(2),pokemonstats.get(3),"",pokemonstats.get(10),Integer.valueOf(pokemonstats.get(8)),Integer.valueOf(pokemonstats.get(9)),Integer.valueOf(pokemonstats.get(11)),Integer.valueOf(pokemonstats.get(12)),Integer.valueOf(pokemonstats.get(13)),Integer.valueOf(pokemonstats.get(14)),Integer.valueOf(pokemonstats.get(15)),Integer.valueOf(pokemonstats.get(16)),Integer.valueOf(pokemonstats.get(17))));
                    for(int f=0;f<4;f++){
                        pokemon2.get(x).addMove(movelist.get(f));
                    }
                }
        t=new Teams("000",pokemon2,"botteam","Trnr. Null");
        Trainers newtr=new Trainers("plyr0","Trnr. Null",t);
        return newtr;
    }
    
}
