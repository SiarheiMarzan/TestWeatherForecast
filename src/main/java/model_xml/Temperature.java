package model_xml;

public class Temperature {
    public double getvalue() {
        return this.value;
    }

    public void setvalue(double value) {
        this.value = value;
    }

    double value;

    public double getmin() {
        return this.min;
    }

    public void setmin(double min) {
        this.min = min;
    }

    double min;

    public double getmax() {
        return this.max;
    }

    public void setmax(double max) {
        this.max = max;
    }

    double max;

    public String getunit() {
        return this.unit;
    }

    public void setunit(String unit) {
        this.unit = unit;
    }

    String unit;
}
