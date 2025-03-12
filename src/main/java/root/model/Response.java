package root.model;

public class Response {
    private double pNR;

    public Response(double pNR, double pR) {
        this.pNR = pNR;
        this.pR = pR;

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

    private double pR;

}
