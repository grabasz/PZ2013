package zespolowe;

import java.util.LinkedList;

public class ComplexMeasurement extends Measurement{
    ComplexMeasurement(int number,String operation){
        this.number = number;
        this.operation = operation;
    }
    
    int number;
    String operation;
    LinkedList <Integer> data = new LinkedList();
    
    public void collectData(){
        if (data.size()<number){
            data.push(1);
        }
        else {
            data.poll();
            data.push(1);
        }
    }
    
    public int mean(){
        int i;
        int sum=0;
        int mean;
        
        for (i=0;i<number;i++){
            sum += data.get(i);
        }
        
        mean = sum/number;
        return mean;
    }
    
    public int maksimum(){
        
        int i;
        int max;
        
        max = data.get(0);
        
        for (i=0;i<number;i++){
            if (max<data.get(i)){max = data.get(i);}
        }
        
        return max;
    }
    
}
