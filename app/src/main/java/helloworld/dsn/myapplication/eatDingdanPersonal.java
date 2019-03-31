package helloworld.dsn.myapplication;

public class eatDingdanPersonal {
    private int OK;
    private int cancel;
    private String show;
    eatDingdanPersonal(int i,int j,String name){
        this.OK=i;
        this.cancel=j;
        this.show=name;
    }

    public int getOK() {
        return OK;
    }

    public int getCancel() {
        return cancel;
    }

    public String getShow() {
        return show;
    }
}
