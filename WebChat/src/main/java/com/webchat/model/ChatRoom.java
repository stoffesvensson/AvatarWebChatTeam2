
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webchat.model;

import java.util.List;

/**
 *
 * @author Adam
 */
public class ChatRoom {

  
    private int isGroupRoom;

    private String groupname;

  
    private int roomId;
    private List<ChatMessage> messages;
    private List<ChatUserHelper> members;
    
    public ChatRoom(int roomId) {
        this.roomId = roomId;
    }
    
    public ChatRoom(int roomId, int isGroup) {
        this.roomId = roomId;
        this.isGroupRoom = isGroup;
    }
     
    public int getIsGroupRoom() {
        return isGroupRoom;
    }

    public void setIsGroupRoom(int isGroupRoom) {
        this.isGroupRoom = isGroupRoom;
    }
    
    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public List<ChatMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<ChatMessage> messages) {
        this.messages = messages;
    }

    public List<ChatUserHelper> getMembers() {
        return members;
    }

    public void setMembers(List<ChatUserHelper> members) {
        this.members = members;
    }
    
    public String getGroupname() {
        return groupname;
    }

    public void setGroupName(String groupname) {
        this.groupname = groupname;
    }
}
