/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.temphumimonitor;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author jason
 */

public class DemoData {
    // @ExcelProperty("字符串标题")
    private String string;
    // @ExcelProperty("日期标题")
    private Date date;
    // @ExcelProperty("数字标题")
    private Double doubleData;
    /**
     * 忽略这个字段
     */
    private String ignore;

    public String getString() {
        return string;
    }

    public Date getDate() {
        return date;
    }

    public Double getDoubleData() {
        return doubleData;
    }

    public String getIgnore() {
        return ignore;
    }

    public void setString(String string) {
        this.string = string;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDoubleData(Double doubleData) {
        this.doubleData = doubleData;
    }

    public void setIgnore(String ignore) {
        this.ignore = ignore;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.string);
        hash = 11 * hash + Objects.hashCode(this.date);
        hash = 11 * hash + Objects.hashCode(this.doubleData);
        hash = 11 * hash + Objects.hashCode(this.ignore);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DemoData other = (DemoData) obj;
        if (!Objects.equals(this.string, other.string)) {
            return false;
        }
        if (!Objects.equals(this.ignore, other.ignore)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.doubleData, other.doubleData)) {
            return false;
        }
        return true;
    }
    
}