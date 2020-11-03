package com.example.dockerdemo.domain;

public class CalcuTxtInfo {

    //识别文本
    private String iatTxt;

    //源文本
    private String targetTxt;

    //转写正确率
    private float sumAcc;

    //转写准确率
    private float SumCorr;


    public String getIatTxt() {
        return iatTxt;
    }

    public void setIatTxt(String iatTxt) {
        this.iatTxt = iatTxt;
    }

    public String getTargetTxt() {
        return targetTxt;
    }

    public void setTargetTxt(String targetTxt) {
        this.targetTxt = targetTxt;
    }

    public float getSumAcc() {
        return sumAcc;
    }

    public void setSumAcc(float sumAcc) {
        this.sumAcc = sumAcc;
    }

    public float getSumCorr() {
        return SumCorr;
    }

    public void setSumCorr(float sumCorr) {
        SumCorr = sumCorr;
    }

}
