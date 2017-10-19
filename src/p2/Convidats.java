package p2;

public class Convidats extends Thread{
    
    public final String name;
    volatile private static Sushi SUSHI = new Sushi();
    
    public Convidats(String name){
        super(name);
        this.name = name;       
    }    
    
    @Override
    public void run(){
        this.eat();
    }
    
    private void eat(){
        String pieceOfSushi;       
        while(SUSHI.haySushi()){
            pieceOfSushi = SUSHI.take();
            SUSHI.eat();
            System.out.println(this.name+" come: "+pieceOfSushi);
        }
        
        
        
    }
    
}
