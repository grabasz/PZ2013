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

        
        int i = 0;
        DatagramSocket socket = null;
        
        try
        {
            // Convert the arguments first, to ensure that they are valid
            InetAddress host = InetAddress.getByName(Config.getHostname());
            int port = Integer.parseInt(Config.getPort());
            
            // Construct the socket
            socket = new DatagramSocket();
            
            while(true){
                // Construct the datagram packet
                String msgMem, msgCpu;

                byte[] dataMem;
                byte[] dataCpu;

                DatagramPacket packetMem;
                DatagramPacket packetCpu;

                if(i % 60 == 0){ //every minute send identyfying message
                    msgMem = constructIdentyfyingMessage(mem);
                    msgCpu = constructIdentyfyingMessage(cpu);
                    
                    dataMem = msgMem.getBytes();
                    dataCpu = msgCpu.getBytes();

                    packetMem = new DatagramPacket(dataMem, dataMem.length, host, port);
                    packetCpu = new DatagramPacket(dataCpu, dataCpu.length, host, port);
                    // Send it
                    socket.send(packetMem);
                    socket.send(packetCpu);
                }
                
                msgMem = constructStandardMessage(mem);
                msgCpu = constructStandardMessage(cpu);

                dataMem = msgMem.getBytes();
                dataCpu = msgCpu.getBytes();

                packetMem = new DatagramPacket(dataMem, dataMem.length, host, port);
                packetCpu = new DatagramPacket(dataCpu, dataCpu.length, host, port);
                // Send it
                socket.send(packetMem);
                socket.send(packetCpu);
                
                ++i;
                Thread.sleep(1000);
            }
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
    
    public static String constructIdentyfyingMessage(Sensor sensor){
        String msg = sensor.GetSensorId() + ";" + sensor.GetSensorType() + ";" + sensor.GetOsProperities() + ";";
        return msg;
    }
    
    public static String constructStandardMessage(Sensor sensor){
        String msg = sensor.GetSensorId() + ";" + sensor.GetMeasurment() + ";";
        return msg;
    }
}
