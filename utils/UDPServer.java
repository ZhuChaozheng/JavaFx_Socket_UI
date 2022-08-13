/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;
import Extasys.Network.UDP.Server.Listener.UDPListener;
import com.mycompany.temphumimonitor.FXMLController;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author jason
 */
public class UDPServer extends Extasys.Network.UDP.Server.ExtasysUDPServer
{
    private String remoteipfrompacket;
    private String info;
    public UDPServer(String name, String description, InetAddress listenerIP, int port, int connectionsTimeOut, 
            int corePoolSize, int maximumPoolSize)
    {
        super(name, description, corePoolSize, maximumPoolSize);
        this.AddListener("My UDP Listener", listenerIP, port, 10240, connectionsTimeOut, Charset.forName("UTF-8"));
    }

    @Override
    public void OnDataReceive(UDPListener listener, DatagramPacket packet)
    {
        remoteipfrompacket = packet.getAddress().getHostAddress();
//        System.out.println("Data received from " + remoteipfrompacket);
        info = new String(packet.getData());
//        System.out.println("Server say data received from " + info);

        
//        try
//        {
            // Send data back to the sender.
//            listener.SendData(new DatagramPacket(packet.getData(), 0, packet.getLength(), packet.getAddress(), packet.getPort()));
//        }
//        catch (Exception ex)
//        {
//        }
    }

    public String getRemoteipfrompacket() {
        return remoteipfrompacket;
    }

    public String getInfo() {
        return info;
    }
    
}