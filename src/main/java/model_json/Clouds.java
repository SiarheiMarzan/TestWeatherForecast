package model_json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Clouds {
    @JsonProperty("all")
    public int getAll() {
        return this.all;
    }

    public void setAll(int all) {
        this.all = all;
    }

    int all;
}
