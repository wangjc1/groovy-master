package com.wjc.vo.sys;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UpdateRolePermVo implements Serializable{

    private Long rid;
    private Integer ptype;
    private List<String> pvals = new ArrayList<>();

    public Integer getPtype() {
        return ptype;
    }

    public void setPtype(Integer ptype) {
        this.ptype = ptype;
    }

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public List<String> getPvals() {
        return pvals;
    }

    public void setPvals(List<String> pvals) {
        this.pvals = pvals;
    }
}
