package agh.pz2013.sensorengine;

import com.jezhumble.javasysmon.JavaSysMon;

/**
 * Bazowa, abstrakcyjna klasa dla wszystkich sensorów. Klasa implementuje interfejs ISensor.
 * @author Konrad Tabor
 */
public abstract class Sensor implements ISensor
{
    protected String sensorId;
    protected SensorTypes sensorType;
    protected JavaSysMon sensor;

    /**
     * Jedyny konstruktor. Przyjmuje id oraz typ. Tworzy wewnątrz obiekt sensora.
     * @param id id sensora
     * @param type typ sensora
     */
    protected Sensor(String id, SensorTypes type)
    {
        sensorId = id;
        sensorType = type;
        sensor = new JavaSysMon();
    }

    /**
     * Zwraca id sesnora.
     * @return id sesnora
     */
    @Override
    public String GetSensorId()
    {
        return sensorId;
    }

    /**
     * Zwraca typ sensora.
     * @return typ sensora
     */
    @Override
    public SensorTypes GetSensorType()
    {
        return sensorType;
    }
}
