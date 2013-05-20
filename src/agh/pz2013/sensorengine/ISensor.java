package agh.pz2013.sensorengine;

/**
 * Interfejs wspólny dla wszystkich sensorów. Każdy sensor musi posiadać swoje id, swój typ oraz każdy musi zwracać dane pomiarowe.
 * @author Konrad Tabor
 */
public interface ISensor
{
    /**
     * Metoda zwracająca id sensora.
     * @return id sensora
     */
    String GetSensorId();
    /**
     * Metoda zwracająca typ sesnora.
     * @return typ sesnora
     */
    SensorTypes GetSensorType();
    /**
     * Metoda zwracająca aktualne dane pomiarowe w zależności od implementacji.
     * @return dane pomiarowe
     */
    String GetMeasurment();
}
