package com.hrtek.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "temp")
public class Temp {
    @Id
    //@GeneratedValue(generator = "system-uuid")
   // @GenericGenerator(name = "system-uuid",strategy = "uuid")
    private  String id;
    private  String ename;
    private  String sex;
    private  Date hire;
    private  float sar;
    private  int did;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getHire() {
        return hire;
    }

    public void setHire(Date hire) {
        this.hire = hire;
    }

    public float getSar() {
        return sar;
    }

    public void setSar(float sar) {
        this.sar = sar;
    }

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

}
