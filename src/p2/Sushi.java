
package p2;

import java.util.ArrayList;
import java.util.Random;
/**
 *
 * @author van
 */
public class Sushi {
    
    public static final String NIGUIRI_SALMO = "Niguiri de Salmó";
    public static final String NIGUIRI_TRUITA = "Niguiri de Truita";
    public static final String SASHIMI_TONYINA = "Sashimi de Tonyina";
    public static final String SASHIMI_ANGUILA = "Sashimi d'anguila";
    public static final String MAKI_CRANC = "Maki de Cranc";    
    public static final String[] TYPES_OF_SUSHI  = {
        NIGUIRI_SALMO,  NIGUIRI_TRUITA, SASHIMI_TONYINA,SASHIMI_ANGUILA, MAKI_CRANC
        
    };   
    private int maxSushi;
    private int minSushi;   
    private int[] numSushi;
    private ArrayList<String> plat;
    private Random random; 
    private static final int minPermit = 1;
    private static final int maxPermit = 10;
    
    
    
    public Sushi(int min, int max){
        if(min<minPermit) min = minPermit;
        this.minSushi = min;
        if(max>maxPermit) max = maxPermit;
        this.maxSushi = max; 
        this.random = new Random();
        this.numSushi = new int[5];
        this.newPlat();
        
    }
    
    public Sushi(){
        this(Sushi.minPermit, Sushi.maxPermit);
    }
    
    synchronized public String take(){
        int pieceOfSushi;
        while(this.plat.isEmpty()){
            try {
                this.wait();
            } catch (InterruptedException ex) {
            }
        }
        pieceOfSushi = this.random.nextInt(this.plat.size());
        return this.plat.remove(pieceOfSushi);
    }
    
    synchronized public void eat(){
        long timeToEat;
        timeToEat = Integer.toUnsignedLong(Integer.remainderUnsigned(this.random.nextInt(),10000));
        try {
            this.wait(timeToEat);
        } catch (InterruptedException ex) {
        }
        
    }
    
    public boolean haySushi(){
        return !this.plat.isEmpty();
    }
    
    ////////////////////////Metodos privados de servicio///////////////////////
    
    private int cooking(){
        int bound, nRaciones;
        
        bound = this.maxSushi - this.minSushi;
        nRaciones = this.random.nextInt(bound) + this.minSushi;  
        return nRaciones;
    }
    
    private void newPlat(){
        
        this.plat = new ArrayList<>(TYPES_OF_SUSHI.length);
        for(int i=0; i<TYPES_OF_SUSHI.length; i++){
            int n=this.cooking();
            this.numSushi[i] = n;
            for(;n>0;n--){
                this.plat.add(TYPES_OF_SUSHI[i]+"_" + n);
            }
            
        }
        System.out.println(this);
    }
    
    @Override
    public String toString(){
        String comandaString;
        
        comandaString = "\nEL PLAT CONTE:\n";
        for(int i=0; i< TYPES_OF_SUSHI.length; i++){
            comandaString += String.valueOf(this.numSushi[i])+" racions de: "+TYPES_OF_SUSHI[i]+"\n";
        }  
        if(comandaString.equals("")) comandaString = "El plat de Sushi està buit";
        
        return comandaString;
    }
    
    
    
}
