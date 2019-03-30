package com.cvte.base.pojo;

import java.io.Serializable;

public class Version implements Serializable {

    private String branch;

    private String commit;

    public Version() {
    }

    public Version(String branch, String commit) {
        this.branch = branch;
        this.commit = commit;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getCommit() {
        return commit;
    }

    public void setCommit(String commit) {
        this.commit = commit;
    }
}
