
package com.example.micha.remotekodi.model.playlist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Limits {

    @SerializedName("end")
    @Expose
    private Integer end;
    @SerializedName("start")
    @Expose
    private Integer start;
    @SerializedName("total")
    @Expose
    private Integer total;

    public Integer getEnd() {
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

}
