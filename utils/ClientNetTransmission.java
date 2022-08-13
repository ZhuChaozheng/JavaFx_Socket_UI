/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.net.InetAddress;

/**
 *
 * @author jason
 */
public class ClientNetTransmission {
    private UDPClient fUDPClient;
    private Thread fUpdateStatusThread;
    private boolean fUpdateStatusActive = true;
    
    public void establishClientNet() {
        if (fUDPClient == null)
        {
            try
            {
                fUDPClient = new UDPClient("My UDP client", "Example", 1000, 100, 100, 
                        InetAddress.getByName("127.0.0.1"), 44151);
                fUDPClient.Start();

                fUpdateStatusActive = true;

                fUDPClient.StartSendingMessages();

                fUpdateStatusThread = new Thread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        try
                        {
                            while (fUpdateStatusActive)
                            {
                                if (fUDPClient != null)
                                {
//                                    System.out.println("client in : " + String.valueOf(fUDPClient.getBytesIn()));
//                                    System.out.println("client out : " + String.valueOf(fUDPClient.getBytesOut()));
                                }
                                Thread.sleep(100);
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
            }
        }
    }
    
    public void stopClientNet() {
        if (fUDPClient != null)
        {
            fUDPClient.Stop();
            fUDPClient.StopSendingMessages();
            fUDPClient.Dispose();

            if (fUpdateStatusThread != null)
            {
                fUpdateStatusActive = false;
                fUpdateStatusThread.interrupt();
            }

            fUDPClient = null;
        }
    }
}
