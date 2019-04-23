package com.example.boysandgirlsclubevents;

import java.util.ArrayList;

public class MemberMonth {

    public String memberName;
    public String memberClubhouse;

    public MemberMonth(){
    }

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

}
