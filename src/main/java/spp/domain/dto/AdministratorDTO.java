package spp.domain.dto;

import java.util.Objects;

public class AdministratorDTO {
    private int id;
    private String userAccount;
    private int userId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.userAccount);
        hash = 47 * hash + this.userId;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AdministratorDTO other = (AdministratorDTO) obj;
        if (this.userId != other.userId) {
            return false;
        }
        return Objects.equals(this.userAccount, other.userAccount);
    }
}