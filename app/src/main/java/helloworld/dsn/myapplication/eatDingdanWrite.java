package helloworld.dsn.myapplication;

public class eatDingdanWrite {
    private String name;
    private String money;
    private String address;
    public eatDingdanWrite(String address,String name,String money){
        this.name=name;
        this.money=money;
    }

    public String getName() {
        return name;
    }

    public String getMoney() {
        return money;
    }

    public String getAddress() {
        return address;
    }
}
