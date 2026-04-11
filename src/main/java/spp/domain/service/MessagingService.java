/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package spp.domain.service;

import spp.domain.dto.MessageDTO;
import spp.domain.exception.IncompleteDataException;
import spp.domain.exception.EntityNotFoundException;
import spp.data.exception.DataAccessException;
import java.util.List;

public interface MessagingService {
    void sendMessage(int senderId, int receiverId, String subject, String body) 
        throws IncompleteDataException, EntityNotFoundException, DataAccessException;
        
    List<MessageDTO> getMyInbox(int userId) 
        throws DataAccessException;
        
    void markAsRead(int messageId) 
        throws EntityNotFoundException, DataAccessException;
}
