package poketwofx;

/**
 *
 * @author MIGUEL RODRIGUEZ
 */
public class Moves {
    String moveid;
    String name;
    String type;
    int acc;
    String movtype;
    public Moves(String moveid,String name, String type, int acc, String movtype){
        this.moveid=moveid;
        this.name=name;
        this.type=type;
        this.acc=acc;
        this.movtype=movtype;
    }
    public String getMoveName(){
        return name;
    }
    public String getMoveType(){
        return type;
    }
    public int getMoveAcc(){
        return acc;
    }   
}
