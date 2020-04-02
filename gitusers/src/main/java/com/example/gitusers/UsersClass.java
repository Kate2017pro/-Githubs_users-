package com.example.gitusers;

public class UsersClass {
    private String login;
  //  private String name;
    private String id;

    public UsersClass(String log, String id){
        login=log;
      //  name=nam;
        this.id=id;
    }

    public String getLogin(){
        return this.login;
    }
 /*   public String getName(){
        return this.name;
    }*/
    public String getId(){
        return this.id;
    }
}
