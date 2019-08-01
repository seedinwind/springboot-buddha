package org.buddha.wise.dzj.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="qldzj")
public class Dzj {
    @Id
    private String id;
    private String buming;
    private String jingti;
    private String juanhao;
    private String pinming;
    private String pinhao;
    private String yizhe;
    private String content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBuming() {
        return buming;
    }

    public void setBuming(String buming) {
        this.buming = buming;
    }

    public String getJingti() {
        return jingti;
    }

    public void setJingti(String jingti) {
        this.jingti = jingti;
    }

    public String getJuanhao() {
        return juanhao;
    }

    public void setJuanhao(String juanhao) {
        this.juanhao = juanhao;
    }

    public String getPinming() {
        return pinming;
    }

    public void setPinming(String pinming) {
        this.pinming = pinming;
    }

    public String getPinhao() {
        return pinhao;
    }

    public void setPinhao(String pinhao) {
        this.pinhao = pinhao;
    }

    public String getYizhe() {
        return yizhe;
    }

    public void setYizhe(String yizhe) {
        this.yizhe = yizhe;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
