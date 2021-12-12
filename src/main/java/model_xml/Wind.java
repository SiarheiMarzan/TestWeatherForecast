package model_xml;

public class Wind {
    public Speed getspeed() {
        return this.speed;
    }

    public void setspeed(Speed speed) {
        this.speed = speed;
    }

    Speed speed;

    public Gusts getgusts() {
        return this.gusts;
    }

    public void setgusts(Gusts gusts) {
        this.gusts = gusts;
    }

    Gusts gusts;

    public Direction getdirection() {
        return this.direction;
    }

    public void setdirection(Direction direction) {
        this.direction = direction;
    }

    Direction direction;
}
