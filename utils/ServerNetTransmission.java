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
import utils.UDPServer;

/**
 *
 * @author jason
 */
public class ServerNetTransmission {
    private UDPServer fUDPServer;
    private Thread fUpdateStatusThread;
    private boolean fUpdateStatusActive = true;
    // protocol MJZ0355627611111
    public void establishServerNet() {
        try
        {
            fUDPServer = new UDPServer("My UDP server", "Example", InetAddress.getByName("127.0.0.1"), 
                        44151, 1000, 100, 100);
            fUDPServer.Start();

            fUpdateStatusActive = true;

            fUpdateStatusThread = new Thread(new Runnable()
            {

                @Override
                public void run()
                {
                    try
                    {
                        while (fUpdateStatusActive)
                        {
                            if (fUDPServer != null)
                            {
//                                System.out.println("server in : " + String.valueOf(fUDPServer.getBytesIn()));
                                System.out.println("server: " + fUDPServer.getRemoteipfrompacket());
//                                if (fUDPServer.getRemoteipfrompacket().equals("null")) {
//                                    System.out.println("server!!: " + fUDPServer.getRemoteipfrompacket());
//                                }
//                                else {
                                    FXMLController fxmlc = new FXMLController();
        
                                    fxmlc.updateUI(fUDPServer.getRemoteipfrompacket(), fUDPServer.getInfo());
//                                }
//                                System.out.println("server: " + );
                                
                            }
                            Thread.sleep(1000);
                        }
                    }
                    catch (Exception ex)
                    {
                    }
                }
            });
        fUpdateStatusThread.start();
        }
        catch (Exception ex)
        {
            System.err.println(ex.getMessage());
        }

    }
    
    public UDPServer getUDPServer() {
        return fUDPServer;
    }
    
    public void stopServerNet() {
        try
        {
            if (fUDPServer != null)
            {
                fUDPServer.Stop();
            }

            if (fUpdateStatusThread != null)
            {
                fUpdateStatusActive = false;
                fUpdateStatusThread.interrupt();
            }
        }
        catch (Exception ex)
        {
            System.err.println(ex.getMessage());
        }
    }
}
