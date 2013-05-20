package agh.pz2013.sensorengine;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Główna testowa klasa programu. Pokazuje, co i jak działa. Na pewno trzeba będzie dorobić jakiś launcher do sensora, ktory na podstawie linii poleceń będzie tworzył odpowiedni typ sensora.
 * @author Konrad Tabor
 */
public class SensorsEngineTest
{
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
        while (i < 5)
        {
            i++;

            System.out.println(cpu.GetSensorId() + " " + cpu.GetSensorType() + " " + cpu.GetMeasurment());
            System.out.println(mem.GetSensorId() + " " + mem.GetSensorType() + " " + mem.GetMeasurment());
            try
            {
                Thread.sleep(500);
            } catch (InterruptedException ex)
            {
                Logger.getLogger(SensorsEngineTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        DatagramSocket socket = null;

        try
        {
            // Convert the arguments first, to ensure that they are valid
            InetAddress host = InetAddress.getByName(Config.getHostname());
            int port = Integer.parseInt(Config.getPort());

            // Construct the socket
            socket = new DatagramSocket();

            // Construct the datagram packet
            String msg = mem.GetSensorId() + " " + mem.GetSensorType() + " " + mem.GetMeasurment();
            byte[] data = msg.getBytes();
            DatagramPacket packet = new DatagramPacket(data, data.length, host, port);

            // Send it
            socket.send(packet);

            // Set a receive timeout, 2000 milliseconds
            socket.setSoTimeout(2000);

            // Prepare the packet for receive
            packet.setData(new byte[100]);

            // Wait for a response from the server
            socket.receive(packet);

            // Print the response
            System.out.println(new String(packet.getData()));

        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        finally
        {
            if (socket != null)
            {
                socket.close();
            }
        }
    }
}
