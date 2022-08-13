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
public class MaxMinFunctions {
    public double getMaxValue(double max, double value) {
        if (value > max)
            return value;
        else
            return max;
    }
    
    public double getMinValue(double min, double value) {
        if (value < min)
            return value;
        else
            return min;
    }
}
