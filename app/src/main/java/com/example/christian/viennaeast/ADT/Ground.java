package com.example.christian.viennaeast.ADT;

import java.io.Serializable;

public class Ground implements Serializable {

    private String[] AP1, AP2, AP3, PAT, GONE;
    private String G01 = "";


    public Ground() {

    }

    public Ground(String[] AP1, String[] AP2, String[] AP3, String[] PAT, String[] GONE, String g01) {
        this.AP1 = AP1;
        this.AP2 = AP2;
        this.AP3 = AP3;
        this.PAT = PAT;
        this.GONE = GONE;
        G01 = g01;
    }

    public String[] getAP1() {
        return AP1;
    }

    public void setAP1(String[] AP1) {
        this.AP1 = AP1;
    }

    public String[] getAP2() {
        return AP2;
    }

    public void setAP2(String[] AP2) {
        this.AP2 = AP2;
    }

    public String[] getAP3() {
        return AP3;
    }

    public void setAP3(String[] AP3) {
        this.AP3 = AP3;
    }

    public String[] getPAT() {
        return PAT;
    }

    public void setPAT(String[] PAT) {
        this.PAT = PAT;
    }

    public String[] getGONE() {
        return GONE;
    }

    public void setGONE(String[] GONE) {
        this.GONE = GONE;
    }

    public String getG01() {
        return G01;
    }

    public void setG01(String g01) {
        G01 = g01;
    }

    public int getAP1Length() {
        int i = 0;
        for(String s:AP1){
            if(s != null){
                i++;
            }
        }
        return i;
    }

    public int getAP2Length() {
        int i = 0;
        for(String s:AP2){
            if(s != null){
                i++;
            }
        }
        return i;
    }

    public int getAP3Length() {
        int i = 0;
        for(String s:AP3){
            if(s != null){
                i++;
            }
        }
        return i;
    }

    public int getApronLength(int n){
        switch (n){
            case 1:
                return getAP1Length();
            case 2:
                return getAP2Length();
            case 3:
                return getAP3Length();
            default:
                return 0;
        }
    }
}
