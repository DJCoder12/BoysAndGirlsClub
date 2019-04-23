package com.teamshark.boysandgirlsclubevents.MemberOfMonth;

public class MemberMonth
{

    public String name;
    public String clubhouse;

    public MemberMonth() {}

    public MemberMonth(String studentName, String studentClubhouse)
    {
        name = studentName;
        clubhouse = studentClubhouse;
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
