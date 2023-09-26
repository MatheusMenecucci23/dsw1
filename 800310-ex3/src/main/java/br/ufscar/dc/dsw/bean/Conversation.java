package br.ufscar.dc.dsw.bean;

public class Conversation {

    private int op1;
    private int op2;
    private int op3;

    public Conversation() {
    }

    public int getOp1() {
        return this.op1;
    }

    public void setOp1(int op1) {
        this.op1 = op1;
    }

    public int getOp2() {
        return this.op2;
    }

    public void setOp2(int op2) {
        this.op2 = op2;
    }

    public int getOp3() {
        return this.op3;
    }

    public void setOp3(int op3) {
        this.op3 = op3;
    }

    public double getConversao() {
        return this.op1 * 1.80 + 32;
    }

    public void setIncremento(int op3) {
        this.op1 = op3 + this.op1;
    }

}
