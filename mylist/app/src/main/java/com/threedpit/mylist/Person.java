package com.threedpit.mylist;

// 한 아이템을 위한 데이터를 담기위한 클래스
public class Person {
    String name;
    String moblie;

    //생성자
    public Person(String name, String moblie) {
        this.name = name;
        this.moblie = moblie;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMoblie() {
        return moblie;
    }

    public void setMoblie(String moblie) {
        this.moblie = moblie;
    }
}
