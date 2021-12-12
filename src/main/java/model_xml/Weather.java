package model_xml;

public class Weather {
    public int getnumber() {
        return this.number;
    }

    public void setnumber(int number) {
        this.number = number;
    }

    int number;

    public String getvalue() {
        return this.value;
    }

    public void setvalue(String value) {
        this.value = value;
    }

    String value;

    public String geticon() {
        return this.icon;
    }

    public void seticon(String icon) {
        this.icon = icon;
    }

    String icon;
}
