package agh.pz2013.sensorengine;

import com.jezhumble.javasysmon.CpuTimes;

/**
 * Sensor CPU. Podaje procentowe użycie procesora względem ostatniego pomiaru.
 * @author Konrad Tabor
 */
public final class CpuSensor extends Sensor
{
    private CpuTimes previousTimes;

    /**
     * Konstruktor.
     * @param id id sensora
     */
    public CpuSensor(String id)
    {
        super(id, SensorTypes.Cpu);
        previousTimes = sensor.cpuTimes();
    }

    /**
     * Zwraca pomiar. Użycie liczone względem ostatniego wywołania. Pomiar zwracany jest jako String, ale de facto jest to float.
     * @return użycie procesora
     */
    @Override
    public String GetMeasurment()
    {
        CpuTimes current = sensor.cpuTimes();
        float result = current.getCpuUsage(previousTimes);
        previousTimes = current;

        return Float.toString(result);
    }

}
