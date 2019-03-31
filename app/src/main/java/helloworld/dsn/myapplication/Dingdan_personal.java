package helloworld.dsn.myapplication;

public class Dingdan_personal {
        private String name;
        private int OK;
        private int CANCEL;
        public Dingdan_personal(String name,int OK,int CANCEL){
            this.name=name;
            this.CANCEL=CANCEL;
            this.OK=OK;
        }
        public String getName(){
            return name;
        }

    public int getCANCEL() {
        return CANCEL;
    }

    public int getOK() {
        return OK;
    }
}
