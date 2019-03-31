package helloworld.dsn.myapplication;

public class JsonUser {
        private  String ordernum;
        private String tradename;
        private String phone;
        private String remarks;
        private String money;
        private String buyadress;
        private String recieveadress;
        private String contactname;
        public void setOrdernum(String ordernum) {
            this.ordernum = ordernum;
        }
        public String getOdernum() {
            return ordernum;
        }


        public void setTradename(String tradename) {
            this.tradename = tradename;
        }
        public String getTradename() {
            return tradename;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
        public String getPhone() {
            return phone;
        }

        public void setRemarks (String remarks) {
            this.remarks = remarks;
        }
        public String getRemarks() {
            return remarks;
        }


        public void setMoney(String money) {
            this.money = money;
        }
        public String getMoney() {
            return money;
        }


        public void setBuyadress(String buyadress) {
            this.buyadress = buyadress;
        }
        public String getBuyadress() {
            return buyadress;
        }


        public void setRecieveadress(String recieveadress) {
            this.recieveadress = recieveadress;
        }
        public String getRecieveadress() {
            return recieveadress;
        }


        public void setName(String name) {
            this.contactname =name;
        }
        public String getName() {
            return contactname;
        }



}
