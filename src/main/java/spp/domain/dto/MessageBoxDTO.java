package spp.domain.dto;

import spp.domain.enums.RoleMessageBox;
import spp.domain.enums.StatusMessageBox;


public class MessageBoxDTO {
    private int id;
    private RoleMessageBox role;
    private StatusMessageBox status;
    private int messageId;
    private int userId;

    public MessageBoxDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RoleMessageBox getRole() {
        return role;
    }

    public void setRole(RoleMessageBox role) {
        this.role = role;
    }

    public StatusMessageBox getStatus() {
        return status;
    }

    public void setStatus(StatusMessageBox status) {
        this.status = status;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

}