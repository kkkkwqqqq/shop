package com.fight.pojo;

public class test {
    private String name;
    private String sign;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public String toString() {
        return "test{" +
                "name='" + name + '\'' +
                ", sign='" + sign + '\'' +
                '}';
    }
}
