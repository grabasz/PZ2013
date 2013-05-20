package agh.pz2013.sensorengine;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * Klasa przykładowa pokazująca, jak odebrać dane z sensora.
 * @author Konrad Tabor
 */
public class SensorReciver_MOCKUP
{

    public static void main(String args[])
    {
        try
        {
            // Convert the argument to ensure that is it valid
            int port = Integer.parseInt(Config.getPort());

            // Construct the socket
            DatagramSocket socket = new DatagramSocket(port);
            System.out.println("The server is ready...");

            for (;;)
            {
                // Create a packet
                DatagramPacket packet = new DatagramPacket(new byte[100], 100);

                // Receive a packet (blocking)
                socket.receive(packet);

                // Print the packet
                System.out.println(packet.getAddress() + " " + packet.getPort() + ": " + new String(packet.getData()));

                // Return the packet to the sender
                socket.send(packet);
            }
        } catch (Exception e)
        {
            System.out.println(e);
        }
    }
}
