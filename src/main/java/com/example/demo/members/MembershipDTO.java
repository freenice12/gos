package com.example.demo.members;

public class MembershipDTO {
    public static class Create {
        private String email;
        private String password;
        private String address;
        private boolean admin;

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

        public boolean isAdmin() {
            return admin;
        }

        public void setAdmin(boolean admin) {
            this.admin = admin;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        @Override
        public String toString() {
            return "Create{" +
                    "email='" + email + '\'' +
                    ", password='" + password + '\'' +
                    ", address='" + address + '\'' +
                    ", admin=" + admin +
                    '}';
        }

    }
}
