package agh.pz2013.sensorengine;

import java.io.IOException;
import java.util.Properties;

/**
 * Klasa odpowiedzialna za zaladowanie ustawień z pliku. Daje prosty dostep do konfiguracji.
 * @author Konrad Tabor
 */
public class Config
{
    private static final Properties properties = new Properties();

    static
    {
        try
        {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            properties.load(loader.getResourceAsStream("agh/pz2013/sensorengine/config.properties"));
        }
        catch (IOException e)
        {
            throw new ExceptionInInitializerError(e);
        }
    }

    /**
     * Zwraca ustwaiony hostname monitora na jaki aplikacja będzie się łączyć.
     * @return url monitora
     */
    public static String getHostname()
    {
        return properties.getProperty("hostname");
    }

    /**
     * Zwraca port monitora, na jaki ma wyjść połączenie.
     * @return port monitora
     */
    public static String getPort()
    {
        return properties.getProperty("port");
    }

}
