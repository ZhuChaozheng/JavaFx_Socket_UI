package com.mycompany.temphumimonitor;

import Extasys.Network.UDP.Server.Listener.UDPListener;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.util.ListUtils;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import utils.AnalyseProtocol;
import utils.ClientNetTransmission;
import utils.MaxMinFunctions;
import utils.ServerNetTransmission;
import utils.UDPServer;

public class FXMLController implements Initializable {
    
    @FXML
    private Label position1;
    @FXML
    private Label temp1;
    @FXML
    private Label humi1;
    @FXML
    private Label bright1;
    @FXML
    private Label time1;
    @FXML
    private Label temp2;
    @FXML
    private Label temp3;
    @FXML
    private Button btnexcel;
    ExcelRW erw = new ExcelRW();
    ArrayList<ManifestData> dataList;
    private boolean uiupdateactive = true;
    
    private UDPServer fUDPServer;
    private Thread fUpdateStatusThread;
    private boolean fUpdateStatusActive = true;
    private ServerNetTransmission snt = new ServerNetTransmission();
    private ClientNetTransmission cnt = new ClientNetTransmission();
    private boolean faction = true;
    
    private double maxtemp = 0;
    private double maxhumi = 0;
    private double maxbright = 0;
    private double mintemp = 0;
    private double minhumi = 0;
    private double minbright = 0;
    AnalyseProtocol ap = new AnalyseProtocol();
    MaxMinFunctions mmf = new MaxMinFunctions();
    
    private String address = "wubu";
    
    @FXML
    private void handleGridPaneAction(ActionEvent event) {
        position1.setText("helloWorld!");
    }
    @FXML
    private void handleButtonAction(ActionEvent event) {
//        if (faction) {
//            faction = false;
//            snt.establishServerNet();
//            cnt.establishClientNet();
//            position1.setText("establish!");
//        }
//        else {
//            faction = true;
//            snt.stopServerNet();
//            cnt.stopClientNet();
//            position1.setText("stop!");
//        }
//        snt.stopServerNet();
//        try
//        {
//            if (fUDPServer != null)
//            {
//                fUDPServer.Stop();
//            }
//
//            if (fUpdateStatusThread != null)
//            {
//                fUpdateStatusActive = false;
//                fUpdateStatusThread.interrupt();
//            }
//        }
//        catch (Exception ex)
//        {
//            System.err.println(ex.getMessage());
//        }
//        cnt.stopClientNet();
        position1.setText("stop!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        // 读取配置文件
        erw.simpleRead();
        dataList = erw.getDataList();
        
        System.out.println("size: " + dataList.size());
        // 建立udp连接
        try
        {
            fUDPServer = new UDPServer("My UDP server", "Example", InetAddress.getByName("127.0.0.1"), 
                        44151, 1000, 100, 100);
            fUDPServer.Start();
            
            Task task = new Task<Void>() {
            @Override
            public Void call() throws Exception {
              while (true) {
                Platform.runLater(new Runnable() {
                  @Override
                  public void run() {
                          if (fUDPServer != null)
                            {
//                                System.out.println("server in : " + String.valueOf(fUDPServer.getBytesIn()));
                                System.out.println("server: " + fUDPServer.getRemoteipfrompacket());
                                updateUI(fUDPServer.getRemoteipfrompacket(), fUDPServer.getInfo());
                            }
                  }
                });
               Thread.sleep(2000);
              }
            }
          };
          Thread th = new Thread(task);
          th.setDaemon(true);
          th.start();
        }
         catch (Exception ex)
        {
            System.err.println(ex.getMessage());
        }
        
        cnt.establishClientNet();
        }
        
                      
    
    public void updateUI(String ip, String info) {
        //        erw.simpleWrite();
        System.out.println("Server max temp!!! " + ip + " info, " + info);
        
//        position1.setText("hello");
        // find address and ip
        for (ManifestData data : dataList) {
            System.out.println("Server max");
            if (ip.equals(data.getRemoteip())) {
                address = data.getAddress();
            }
        }
        if(ap.verifyHeader(info)) {
            System.out.println("Server max temp!!! ");
            double temp = ap.getTemp(info);
            maxtemp = mmf.getMaxValue(maxtemp, temp);
            mintemp = mmf.getMinValue(mintemp, temp);
            
            double humi = ap.getHumi(info);
            maxhumi = mmf.getMaxValue(maxhumi, humi);
            minhumi = mmf.getMinValue(minhumi, humi);
            
            double bright = ap.getBright(info);
            maxbright = mmf.getMaxValue(maxbright, bright);
            minbright = mmf.getMinValue(minbright, bright);
            System.out.println("Server max temp " + maxtemp + ", min temp " + mintemp);
            Date date = new Date();
//            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
            String formatdate = formatter.format(date);
//            
//            // updatedata
            position1.setText(address);
            temp1.setText("温度：" + temp + "°C");
            humi1.setText("湿度：" + humi + "%");
            bright1.setText("光照度：" + bright + "%");
            time1.setText("更新时间：" + formatdate);
            
//            if (position1.getText().equals("hello"))
//                position1.setText("world");
//            else
//                position1.setText("hello");
//            // 将温度、湿度、光照度以及时间戳写入excel
        }
    }
}
