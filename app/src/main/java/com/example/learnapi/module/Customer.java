    package com.example.learnapi.module;

    import android.os.Parcel;
    import android.os.Parcelable;

    import androidx.annotation.NonNull;

    import com.google.gson.annotations.SerializedName;

    import java.io.Serializable;
    import java.util.Date;

    public class Customer implements Serializable {
        @SerializedName("IDCustomer")
        private int idCustomer;

        @SerializedName("UserName")
        private String userName;

        @SerializedName("Email")
        private String email;

        @SerializedName("PassWord")
        private String password;

        @SerializedName("Phone")
        private String phone;

        @SerializedName("Age")
        private Date age;

        @SerializedName("Gender")
        private String gender;

        public Customer() {

        }

        public Customer(int idCustomer, String userName, String email, String phone, Date age, String gender) {
            this.idCustomer = idCustomer;
            this.userName = userName;
            this.email = email;

            this.phone = phone;
            this.age = age;
            this.gender = gender;
        }
        public Customer(String userName, String phone, Date age, String gender) {

            this.userName = userName;


            this.phone = phone;
            this.age = age;
            this.gender = gender;
        }

        public int getIdCustomer() {
            return idCustomer;
        }

        public void setIdCustomer(int idCustomer) {
            this.idCustomer = idCustomer;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassWord() {
            return password;
        }

        public void setPassWord(String passWord) {
            this.password = passWord;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public Date getAge() {
            return age;
        }

        public void setAge(Date age) {
            this.age = age;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }


    }