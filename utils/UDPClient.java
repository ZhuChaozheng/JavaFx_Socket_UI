/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;
import Extasys.Network.UDP.Client.Connectors.UDPConnector;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.nio.charset.Charset;
/**
 *
 * @author jason
 */
public class UDPClient extends Extasys.Network.UDP.Client.ExtasysUDPClient
{

    private AutoSendMessages fAutoSendMessagesThread;

    public UDPClient(String name, String description, int readTimeOut, int corePoolSize, int maximumPoolSize, InetAddress remoteHostIP, int remoteHostPort)
    {
        super(name, description, corePoolSize, maximumPoolSize);

        // Add a UDP connector to this UDP client.
        // You can add more than one connectors if you need to.
        super.AddConnector("My connector", 10240, 10000, remoteHostIP, remoteHostPort, Charset.forName("UTF-8"));
    }

    @Override
    public void OnDataReceive(UDPConnector connector, DatagramPacket packet)
    {
        System.out.println("Data received: " + new String(packet.getData()));
    }
    public void StartSendingMessages()
    {
        StopSendingMessages();
        fAutoSendMessagesThread = new AutoSendMessages(this);
        fAutoSendMessagesThread.start();
    }

    public void StopSendingMessages()
    {
        if (fAutoSendMessagesThread != null)
        {
            fAutoSendMessagesThread.Dispose();
            fAutoSendMessagesThread.interrupt();
        }
    }
}

/*
 * 重复发生数字给server
*/
class AutoSendMessages extends Thread
{

    private final UDPClient fMyClient;
    private boolean fActive = true;

    public AutoSendMessages(UDPClient client)
    {
        fMyClient = client;
    }

    @Override
    public void run()
    {
        int messageCount = 0;
        while (fActive)
        {
            try
            {
                messageCount++;
                String msgToSend = "MJZ0355627611121" + String.valueOf(messageCount);
                fMyClient.SendData(msgToSend.getBytes(), 0, msgToSend.getBytes().length);
                Thread.sleep(2000);
            }
            catch (Exception ex)
            {
                Dispose();
                fMyClient.StopSendingMessages();
            }
        }
    }

    public void Dispose()
    {
        fActive = false;
    }
}