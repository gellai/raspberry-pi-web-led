package com.gellai.raspiwebled;

public class LedValueMapper 
{
    private String redValue,
                   greenValue,
                   blueValue;

    public void setRedValue(String redValue) {
        this.redValue = redValue;
    }

    public String getRedValue() {
        return this.redValue;
    }

    public void setGreenValue(String greenValue) {
        this.greenValue = greenValue;
    }

    public String getGreenValue() {
        return this.greenValue;
    }
    
    public void setBlueValue(String blueValue) {
        this.blueValue = blueValue;
    }
    
    public String getBlueValue() {
        return this.blueValue;
    }
}