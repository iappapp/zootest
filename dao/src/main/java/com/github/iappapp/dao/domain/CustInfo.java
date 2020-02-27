package com.github.iappapp.dao.domain;

public class CustInfo {
    private Long id;
    private String name;
    private Short age;
    private String idCardNo;
    private Boolean sex;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Short getAge() {
        return age;
    }

    public void setAge(Short age) {
        this.age = age;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "CustInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", idCardNo='" + idCardNo + '\'' +
                ", sex=" + sex +
                '}';
    }
}
