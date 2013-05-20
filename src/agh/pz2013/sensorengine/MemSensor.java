package agh.pz2013.sensorengine;

/**
 * Sensor pamięci. Sensor podaje wolną i całkowitą pamięć fizyczną zainstalowaną w komputerze.
 * @author Konrad Tabor
 */
public final class MemSensor extends Sensor
{
    /**
     * Konstruktor.
     * @param id id sesnora
     */
    public MemSensor(String id)
    {
        super(id, SensorTypes.Memory);
    }

    /**
     * Zwraca dane dotyczące pamięci w postaci: pamięć_wolna/pamięć_całkowita.
     * @return dane pomiarowe
     */
    @Override
    public String GetMeasurment()
    {
        long free = sensor.physical().getFreeBytes();
        long total = sensor.physical().getTotalBytes();
        String result = Long.toString(free) + "/" + Long.toString(total);

        return result;
    }

}
