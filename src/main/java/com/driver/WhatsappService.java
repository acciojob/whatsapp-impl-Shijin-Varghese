package com.driver;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class WhatsappService {
    WhatsappRepository whatsappRepository=new WhatsappRepository();
    public String createUser(String name, String mobile) throws Exception {
        if(whatsappRepository.MobileUserMap.containsKey(mobile)){
            throw new Exception("User Already Exist");
        }
       return whatsappRepository.createUser(name,mobile);
    }

    public Group createGroup(List<User> users)  {
        return whatsappRepository.createGroup(users);
    }

    public int createMessage(String content) {
      return  whatsappRepository.createMessage(content);
    }

    public int sendMessage(Message message, User sender, Group group) throws Exception {
        return whatsappRepository.sendmessage(message,sender,group);
    }

    public String changeAdmin(User approver, User user, Group group) throws Exception {
       return  whatsappRepository.changeAdmin(approver,user,group);
    }

    public int removeUser(User user) {
        return 0;
    }

    public String findMessage(Date start, Date end, int k) {
        return "";
    }
}
