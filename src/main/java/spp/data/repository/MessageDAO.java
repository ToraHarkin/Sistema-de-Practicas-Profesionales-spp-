/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package spp.data.repository;

import spp.domain.dto.MessageDTO;
import java.util.List;

public interface MessageDAO {
    boolean sendMessage(MessageDTO message, int receiverId);
    List<MessageDTO> getInboxByUserId(int userId);
}
