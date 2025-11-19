package woowa.myapp.model;

import java.io.Serializable;

public class Card implements Serializable {
    private String front;
    private String back;
    private boolean target;
    private int easyCount;
    private int mediumCount;
    private int hardCount;

    public Card(String front, String back) {
        this.front = front;
        this.back = back;
        this.target = true;
        this.easyCount = 0;
        this.mediumCount = 0;
        this.hardCount = 0;
    }

    public String getFront() {
        return front;
    }

    public String getBack() {
        return back;
    }

    public boolean isTarget() {
        return target;
    }

    public void setTarget(boolean target) {
        this.target = target;
    }

    public void easyCountup() {
        this.easyCount++;
    }

    public void mediumCountup() {
        this.mediumCount++;
    }

    public void hardCountup() {
        this.hardCount++;
    }

    public double getDifficulty() {
        return hardCount * 2 + mediumCount;
    }


}
