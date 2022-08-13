/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author jason
 */
public class AnalyseProtocol {

    private int controlcommand; // 1
    public double temp; // 3
    public double humi; // 3
    public double bright; // 3
    public int alert1; // 1
    public int alert2; // 1
    public int alert3; // 1
    /* 
    * 头必须是MJZ
    */
    public boolean verifyHeader(String info) {
        if ("MJZ".equals(info.substring(0, 3)))
            return true;
        else
            return false;
    }
    
    // protocol MJZ0 355 627611111
    public int getControlCommand(String info) {
        controlcommand = Integer.parseInt(info.substring(3, 4));
        return controlcommand;
    }
    
    public double getTemp(String info) {
//        int tem = Integer.parseInt(info.substring(4, 7));
        int tem = Integer.parseInt(info.substring(16, 17));
        temp = (double) tem / 10;
        System.out.println(temp);
        return temp;
    }

    public double getHumi(String info) {
        int hum = Integer.parseInt(info.substring(7, 10));
        temp = (double) hum / 10;
        return humi;
    }

    public double getBright(String info) {
        int bri = Integer.parseInt(info.substring(10, 13));
        temp = (double) bri / 10;
        return bright;
    }

    public int getAlert1(String info) {
        alert1 = Integer.parseInt(info.substring(13, 14));
        return alert1;
    }

    public int getAlert2(String info) {
        alert2 = Integer.parseInt(info.substring(14, 15));
        return alert2;
    }
    
    public int getAlert3(String info) {
        alert3 = Integer.parseInt(info.substring(15, 16));
        return alert3;
    }
}
