package zespolowe;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author marcij
 */
public class ComplexMeasurement implements Runnable{

    /**
     * @param args the command line arguments
     */
     ComplexMeasurement(int number,String operation){
        this.number = number;
        this.operation = operation;
    }
    
    int number;
    String operation;
    LinkedList <Integer> data = new LinkedList();
    
    boolean dataCollected = false;
    protected int result = -1;
    protected String operationTime;
    int delay = 1000;
    
    public int getResult() {
        return result;
    }

    public String getOperationTime() {       
        return operationTime;
    }
    
    
    public void collectData(){
        Random rand = new Random();
        if (data.size()<number){
            data.push(rand.nextInt(100));
        }
        else {
            dataCollected = true;
            data.poll();
            Date date = new Date();
            SimpleDateFormat f = new SimpleDateFormat("HH:mm:ss:SS");
            operationTime = f.format(date);
            data.push(rand.nextInt(100));
        }
    }
    
    public void mean(){
        
        int i;
        int sum=0;
        
        for (i=0;i<number;i++){
            sum += data.get(i);
        }
        
        this.result = sum/number;
        
    }

//w zależności od wybranej opcji wykonuje obliczenia i zwraca wynik
    public void chooseOperation(){
        if (dataCollected){
            switch(operation){
                case("mean") : this.mean();
                case("maksimum") : this.maksimum();   
                default : this.mean();    
            }
        }
    }
    
    public void maksimum(){
        
        int i;
        int max;
        
        max = data.get(0);
        
        for (i=0;i<number;i++){
            if (max<data.get(i)){max = data.get(i);}
        }
        
        result = max;
    }
    
    public void minimum(){
        
        int i;
        int min;
        
        min = data.get(0);
        
        for (i=0;i<number;i++){
            if (min<data.get(i)){min = data.get(i);}
        }
        
        this.result = min;
    }    
    
    public static void main(String[] args) {
        ComplexMeasurement cm = new ComplexMeasurement(5,"mean");
        cm.run();
    }

    @Override
    public void run() {
        while(true){
            try{
                this.collectData();
                this.chooseOperation();
                
                Thread.sleep(delay);
            }
            catch(Exception e){}
        }
     }
}
