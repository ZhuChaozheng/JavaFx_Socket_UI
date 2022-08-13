/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.temphumimonitor;

/**
 *
 * @author jason
 */
public class ManifestData {
    // 库房名称	
    private String address;
    // 远程IP地址 
    private String remoteip;
    // 远程端口号		
    private int remoteport;
    // 本地地址	
    private String localip;
    // 本地端口号	
    private int localport;
    // 温度报警许可
    private String temppremession;
    // 温度报警上限	
    private double tempupper;
    // 温度报警下限
    private double templower;
    // 湿度报警许可
    private String humipremssion;
    // 湿度报警上限
    private double humiupper;
    // 湿度报警下限
    private double humilower;
    // 光照度度报警许可
    private String brightpremission;
    // 光照度报警上限
    private double brightupper;
    // 光照度报警下限
    private double brightlower;
    // 人员临近报警许可
    private String personapproachpermession;
    // 人员闯入报警许可
    private String personenterpermission;
    // 其他报警许可
    private String extrapermission;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemoteip() {
        return remoteip;
    }

    public void setRemoteip(String remoteip) {
        this.remoteip = remoteip;
    }

    public int getRemoteport() {
        return remoteport;
    }

    public void setRemoteport(int remoteport) {
        this.remoteport = remoteport;
    }

    public String getLocalip() {
        return localip;
    }

    public void setLocalip(String localip) {
        this.localip = localip;
    }

    public int getLocalport() {
        return localport;
    }

    public void setLocalport(int localport) {
        this.localport = localport;
    }

    public String getTemppremession() {
        return temppremession;
    }

    public void setTemppremession(String temppremession) {
        this.temppremession = temppremession;
    }

    public double getTempupper() {
        return tempupper;
    }

    public void setTempupper(double tempupper) {
        this.tempupper = tempupper;
    }

    public double getTemplower() {
        return templower;
    }

    public void setTemplower(double templower) {
        this.templower = templower;
    }

    public String getHumipremssion() {
        return humipremssion;
    }

    public void setHumipremssion(String humipremssion) {
        this.humipremssion = humipremssion;
    }

    public double getHumiupper() {
        return humiupper;
    }

    public void setHumiupper(double humiupper) {
        this.humiupper = humiupper;
    }

    public double getHumilower() {
        return humilower;
    }

    public void setHumilower(double humilower) {
        this.humilower = humilower;
    }

    public String getBrightpremission() {
        return brightpremission;
    }

    public void setBrightpremission(String brightpremission) {
        this.brightpremission = brightpremission;
    }

    public double getBrightupper() {
        return brightupper;
    }

    public void setBrightupper(double brightupper) {
        this.brightupper = brightupper;
    }

    public double getBrightlower() {
        return brightlower;
    }

    public void setBrightlower(double brightlower) {
        this.brightlower = brightlower;
    }

    public String getPersonapproachpermession() {
        return personapproachpermession;
    }

    public void setPersonapproachpermession(String personapproachpermession) {
        this.personapproachpermession = personapproachpermession;
    }

    public String getPersonenterpermission() {
        return personenterpermission;
    }

    public void setPersonenterpermission(String personenterpermission) {
        this.personenterpermission = personenterpermission;
    }

    public String getExtrapermission() {
        return extrapermission;
    }

    public void setExtrapermission(String extrapermission) {
        this.extrapermission = extrapermission;
    }
    
}
