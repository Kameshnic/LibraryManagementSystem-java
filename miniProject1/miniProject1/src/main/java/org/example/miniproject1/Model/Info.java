package org.example.miniproject1.Model;

import java.util.ArrayList;

public class Info {

    int id;
    String domain;
    ArrayList<String> infos;

    Info(int id, String domain)
    {
        this.id=id;
        this.domain=domain;
        this.infos=infos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public ArrayList<String> getInfos() {
        return infos;
    }

    public void setInfos(ArrayList<String> infos) {
        this.infos = infos;
    }
}
