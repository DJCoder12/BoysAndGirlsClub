package com.example.boysandgirlsclubevents.Util;

import java.util.ArrayList;
import java.util.List;

public abstract class Observable
{
    private List<Observer> observers = new ArrayList<>();

    protected void notifyObservers(String messageCode)
    {
        for (Observer observer : observers)
        {
            observer.update(messageCode);
        }
    }

    public void subscribe(Observer observer)
    {
        observers.add(observer);
    }
}
