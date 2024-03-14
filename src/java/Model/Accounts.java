/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author nguye
 */
public class Accounts {
    private String userName,password, isAdmin, band;

    public Accounts() {
    }

    public Accounts(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.isAdmin= "0";
    }   

    public Accounts(String userName, String password, String isAdmin, String band) {
        this.userName = userName;
        this.password = password;
        this.isAdmin = isAdmin;
        this.band = "0";
    }

    public String getBand() {
        return band;
    }

    public void setBand(String band) {
        this.band = band;
    }
    

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(String isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public String toString() {
        return "Accounts{" + "userName=" + userName + ", password=" + password + ", isAdmin=" + isAdmin + ", band=" + band + '}';
    }

    
    

    
}
