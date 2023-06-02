package root.model;

public class Response {
    private double pNR;
    public Response(double pNR, double pR, double pI,double pC) {
        this.pNR = pNR;
        this.pR = pR;
        this.pI = pI;
        this.pC = pC;
    }
    public double getpNR() {
        return pNR;
    }
    public void setpNR(double pNR) {
        this.pNR = pNR;
    }
    public double getpR() {
        return pR;
    }
    public void setpR(double pR) {
        this.pR = pR;
    }
    public double getpI() {
        return pI;
    }
    public void setpI(double pI) {
        this.pI = pI;
    }
    public double getpC() {
        return pC;
    }
    public void setpC(double pC) {
        this.pC = pC;
    }
    private double pR;
    private double pI;
    private double pC;
}
