package com.danylko.yourburger.entities;



import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /*, generator = "orders-generator"
    @TableGenerator(name = "orders-generator",
            table = "orders",
            pkColumnName = "ORDER_ID")*/
    @Column(name = "ORDER_ID")
    private Long orderId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    /*@JoinTable(name = "product_list",
            joinColumns = @JoinColumn(name = "ORDER_ID"),
            inverseJoinColumns = @JoinColumn(name = "NAME")
    )*/
    private List<ProductOrder> productOrderList;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FAC_ID")
    private Facility facility;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CUST_ID")
    private Customer customer;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ADDRESS_ID")
    private Address address;

    @Column(name = "TOTAL_PRICE")
    private int totalPrice;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ORDER_DATE")
    private Date orderDate;

    @Version
    @Column(name = "VERSION")
    private int version;

    @Transient
    private String jsonOrderlist;

    public Order() {
    }

    public Order(List<ProductOrder> productOrderList, Facility facility, Customer customer, Date orderDate, Address address, int totalPrice) {
        this.productOrderList = productOrderList;
        this.facility = facility;
        this.customer = customer;
        this.orderDate=orderDate;
        this.address = address;
        this.totalPrice = totalPrice;
    }

    public List<ProductOrder> getProductOrders() {
        return productOrderList;
    }

    public Facility getFacility() {
        return facility;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Address getAddress() {
        return address;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public int getVersion() {
        return version;
    }

    public void setFacility(Facility facility) {
        this.facility = facility;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Long getOrderId() {
        return orderId;
    }

    public List<ProductOrder> getProductOrderList() {
        return productOrderList;
    }

    public void setProductOrderList(List<ProductOrder> productOrderList) {
        this.productOrderList = productOrderList;
    }

    public String getJsonOrderlist() {
        return jsonOrderlist;
    }

    public void setJsonOrderlist(String jsonOrderlist) {
        this.jsonOrderlist = jsonOrderlist;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", productOrderList=" + productOrderList +
                ", facility=" + facility +
                ", customer=" + customer +
                ", address=" + address +
                ", price=" + totalPrice +
                ", orderDate=" + orderDate +
                ", version=" + version +
                ", jsonOrderList=" + jsonOrderlist +
                '}';
    }
}
