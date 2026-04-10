/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package spp.domain.service;

import spp.domain.dto.MessageDTO;
import java.util.List;

public interface MessagingService {
    void sendMessage(int senderId, int receiverId, String subject, String body);
    List<MessageDTO> getMyInbox(int userId);
    void markAsRead(int messageId);
}
