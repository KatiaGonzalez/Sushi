
package p2;

public class P2 {

    private static final int NUM_CONVIDATS = 4;
    private static final String[] NAME_CONVIDATS = {"Maria", "Jaume","Joan","Llucia"};
    private static Convidats[] CONVIDATS = new Convidats[NUM_CONVIDATS];
       
    public static void main(String[] args) {
        for(int i=0; i<NUM_CONVIDATS;i++){
            Convidats convidat = new Convidats(NAME_CONVIDATS[i]);
            CONVIDATS[i] = convidat;
        }
        for(int i=0; i<NUM_CONVIDATS;i++){
            CONVIDATS[i].start();
        }
        for(int i=0; i<NUM_CONVIDATS;i++){
            try {
                CONVIDATS[i].join();
            } catch (InterruptedException ex) {
            }
        }
    }
    
}
