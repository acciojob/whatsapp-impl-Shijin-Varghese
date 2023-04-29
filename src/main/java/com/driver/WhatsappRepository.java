package com.driver;

import java.util.*;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;

@Repository
@Getter
@Setter
public class WhatsappRepository {

    //Assume that each user belongs to at most one group
    //You can use the below mentioned hashmaps or delete these and create your own.
    private int cntcontent;
    private HashMap<Group, List<User>> groupUserMap;
    protected HashMap<String,String> MobileUserMap;
    private HashMap<Group, List<Message>> groupMessageMap;
    private HashMap<Message, User> senderMap;
    private HashMap<Group, User> adminMap;
    private HashMap<Integer,String>Id_Of_Content;
    private HashSet<String> userMobile;
    private int customGroupCount=0;
    private int messageId;

    public WhatsappRepository(){
        this.cntcontent=0;
        this.MobileUserMap=new HashMap<>();
        this.Id_Of_Content=new HashMap<>();
        this.groupMessageMap = new HashMap<Group, List<Message>>();
        this.groupUserMap = new HashMap<Group, List<User>>();
        this.senderMap = new HashMap<Message, User>();
        this.adminMap = new HashMap<Group, User>();
        this.userMobile = new HashSet<>();
        this.customGroupCount = 0;
        this.messageId = 0;
    }

    public String createUser(String name, String mobile) {
        MobileUserMap.put(mobile,name);
        userMobile.add(mobile);
        return "SUCCESS";
    }

    public Group createGroup(List<User>users) {
        if(users.size()==2){
            Group current=new Group(users.get(1).getName(),users.size());
            groupUserMap.put(current,users);
            adminMap.put(current,users.get(0));
            return current;
        }

        if(users.size()>2){
             customGroupCount++;
            Group current=new Group(""+customGroupCount,users.size());
            groupUserMap.put(current,users);
            adminMap.put(current,users.get(0));
            return current;
        }
       return null;
    }

    public int createMessage(String content) {
        Id_Of_Content.put(++cntcontent,content);
        return cntcontent-1;
    }

    public int sendmessage(Message message, User sender, Group group) throws Exception {
        if(!groupUserMap.containsKey(group)){
            throw new Exception("Group does not exist");
        }
        if(!groupUserMap.get(group).contains(sender)){
            throw new Exception("You are not allowed to send message");
        }
        List<Message>a=groupMessageMap.getOrDefault(group,new ArrayList<>());
        a.add(message);
        groupMessageMap.put(group,a);
       return groupMessageMap.get(group).size();
    }

    public String changeAdmin(User approver, User user, Group group) throws Exception {
        if(!groupUserMap.containsKey(group)){
            throw new Exception("Group does not exist");
        }
        if(!groupUserMap.get(group).contains(user)){
            throw new Exception("User is not a participant");
        }
        if(adminMap.get(group)!=approver){
            throw new Exception("Approver does not have rights");
        }
        adminMap.put(group,user);
        return "SUCCESS";
    }
}
