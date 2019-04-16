package com.example.boysandgirlsclubevents;

import java.util.ArrayList;

public class MemberMonth {

    public String memberName;
    public String memberClubhouse;

    public MemberMonth(String studentName, String studentClubhouse){
        memberName = studentName;
        memberClubhouse = studentClubhouse;
    }

    public String getMemberClubhouse(){

        return memberClubhouse;
    }

    public void setMemberClubhouse(String newMemberClubhouse){
        memberClubhouse = newMemberClubhouse;
    }

    public String getMemberName(){
        return memberName;
    }

    public void setMemberName(String newMemberName){
        memberName = newMemberName;
    }

    public static ArrayList<MemberMonth> createMembersList(int numMembers){
        ArrayList<MemberMonth> members = new ArrayList<>();

        for(int i = 1; i <= numMembers; i++){
            members.add(new MemberMonth("Person " + i, "Clubhouse " + i));
        }

        return members;
    }

}
