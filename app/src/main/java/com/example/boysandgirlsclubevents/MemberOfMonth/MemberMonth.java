package com.example.boysandgirlsclubevents.MemberOfMonth;

public class MemberMonth {

    public String name;
    public String clubhouse;

    public MemberMonth() {}

    public MemberMonth(String studentClubhouse, String studentName)
    {
        clubhouse = studentClubhouse;
        name = studentName;
    }

    public String getClubhouse()
    {

        return clubhouse;
    }

    public void setClubhouse(String newMemberClubhouse)
    {
        clubhouse = newMemberClubhouse;
    }

    public String getName(){
        return name;
    }

    public void setName(String newMemberName){
        name = newMemberName;
    }

}
