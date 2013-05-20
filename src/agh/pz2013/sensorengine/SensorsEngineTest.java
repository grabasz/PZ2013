package agh.pz2013.sensorengine;

import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Główna testowa klasa programu. Pokazuje, co i jak działa. Na pewno trzeba będzie dorobić jakiś launcher do sensora, ktory na podstawie linii poleceń będzie tworzył odpowiedni typ sensora.
 * @author Konrad Tabor
 */
public class SensorsEngineTest {

    /**
     * Główna metoda starotwa.
     * @param args
     */
    public static void main(String[] args)
    {
        // tworzy sensory z unikatowymi id
        Sensor cpu = new CpuSensor(UUID.randomUUID().toString());
        Sensor mem = new MemSensor(UUID.randomUUID().toString());

        // wyciaga z pliku konfiguracyjnego ustawienia
        System.out.println(Config.getHostname());
        System.out.println(Config.getPort());

        // przykład działania
        int i = 0;
        while(i < 10)
        {
            i++;

            System.out.println(cpu.GetSensorId() + " " + cpu.GetSensorType() + " " + cpu.GetMeasurment());
            System.out.println(mem.GetSensorId() + " " + mem.GetSensorType() + " "+ mem.GetMeasurment());
            try
            {
                Thread.sleep(500);
            } catch (InterruptedException ex)
            {
                Logger.getLogger(SensorsEngineTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
