package com.example.demo.members;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Membership {

    @Id @GeneratedValue
    private Long id;

    private String email;

    private String password;

    private String username;

    private String address;

//    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime joined = LocalDateTime.now();

//    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updated = LocalDateTime.now();

    private boolean admin;

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getJoined() {
        return joined;
    }

    public void setJoined(LocalDateTime joined) {
        this.joined = joined;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Membership that = (Membership) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return email.equals(that.email);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + email.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Membership{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", address='" + address + '\'' +
                ", joined=" + joined +
                ", updated=" + updated +
                ", admin=" + admin +
                '}';
    }

    public static Membership createWith(MembershipDTO.Create c) {
        Membership result = new Membership();
        result.setEmail(c.getEmail());
        result.setPassword(c.getPassword());
        result.setAddress(c.getAddress());
        result.setAdmin(c.isAdmin());
        return result;
    }
}
