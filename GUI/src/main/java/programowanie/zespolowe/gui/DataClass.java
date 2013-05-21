package programowanie.zespolowe.gui;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Krzychu
 */
public class DataClass extends Thread {

    private class Sample {

        Long time;
        Double value;

        public Sample(Long time, Double value) {
            this.time = time;
            this.value = value;
        }

        @Override
        public String toString() {
            return "[" + time + " , " + value + "]";
        }
    }
    private ArrayList<Sample> data = new ArrayList<Sample>();
    boolean work = true;

    public DataClass() {
    }

    public DataClass(String name) {
        super(name);
    }

    public String dataToJson() {
        StringBuilder sb = new StringBuilder("[");
        if (!data.isEmpty()) {
            for (Sample s : data) {
                sb.append(s.toString()).append(",\n");
            }
            sb.delete(sb.length() - 2, sb.length() - 1);
        }
        sb.append("]");

        System.out.println(sb.toString());
        return sb.toString();
    }

    @Override
    public void run() {
        while (work) {
            System.out.println("Ima workin'");
            try {
                sleep(400);
            } catch (InterruptedException ex) {
                Logger.getLogger(DataClass.class.getName()).log(Level.SEVERE, null, ex);
                work = false;
            }
        }
    }
}
