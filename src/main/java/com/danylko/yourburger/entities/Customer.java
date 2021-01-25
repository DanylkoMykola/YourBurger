package com.danylko.yourburger.entities;


import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "customers")
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   /* , generator = "customer-generator"
    @TableGenerator(name = "customer-generator",
            table = "customers",
            pkColumnName = "CUST_ID")*/
    @Column(name = "CUST_ID")
    private Long custId;

    @Column(name = "FIRST_NAME", length = 40)
    private String fristName;

    @Column(name = "LAST_NAME", length = 40)
    private String lastName;

    @Column(name = "PHONE_NUMBER", length = 25)
    private String phoneNumber;

    @Email
    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "RATING", length = 2)
    private int rating;

   /* @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> addresses;*/

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders;

    public Customer() {
    }

    public Customer(String fristName, String lastName, String phoneNumber,
                    @Email String email) {
        this.fristName = fristName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Customer(String fristName, String lastName, String phoneNumber, @Email String email,
                    String password, int rating, List<Order> orders) {
        this.fristName = fristName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.rating = rating;
        //this.addresses = addresses;
        this.orders = orders;
    }

    public long getCustId() {
        return custId;
    }

    public String getFristName() {
        return fristName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getRating() {
        return rating;
    }

    public List<Order> getOrders() { return this.orders; }

   /* public List<Address> getAddresses() {
        return addresses;
    }*/

    public void setCustId(long custId) {
        this.custId = custId;
    }

    public void setFristName(String fristName) {
        this.fristName = fristName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setOrders(List<Order> orders) { this.orders = orders; }

   /* public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public boolean addAddress(Address address) {
        address.setCustomer(this);
        return  getAddresses().add(address);
    }

    public void removeAddress(Address address) {
        getAddresses().remove(address);
    }
*/
    @Override
    public String toString() {
        return "Customer{" +
                "custid=" + custId +
                ", fristname='" + fristName + '\'' +
                ", surname='" + lastName + '\'' +
                ", phonenumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", rating=" + rating +
                '}';
    }
}
