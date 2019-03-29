package com.cvte.base.pojo;

import java.util.List;

public class VersionList {

    private List<Version> versionList;

    public VersionList() {
    }

    public VersionList(List<Version> versionList) {
        this.versionList = versionList;
    }

    public List<Version> getVersionList() {
        return versionList;
    }

    public void setVersionList(List<Version> versionList) {
        this.versionList = versionList;
    }
}
