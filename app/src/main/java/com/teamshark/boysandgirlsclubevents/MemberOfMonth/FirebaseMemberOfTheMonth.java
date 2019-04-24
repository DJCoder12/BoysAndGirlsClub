package com.teamshark.boysandgirlsclubevents.MemberOfMonth;



import android.support.annotation.NonNull;
import android.util.Log;

import com.teamshark.boysandgirlsclubevents.Util.Observable;
import com.google.android.gms.tasks.OnCompleteListener;

import com.teamshark.boysandgirlsclubevents.Util.Observable;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.firestore.WriteBatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirebaseMemberOfTheMonth extends Observable
{
    private static final String TAG = "FirebaseMemberMonth";
    private static FirebaseMemberOfTheMonth instance;
    public FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static final String CLUBHOUSE_KEY = "clubhouse";
    private static final String NAME_KEY = "name";
    private static final String PATH = "member of the month";

    private List<MemberMonth> mMembersOfMonth = new ArrayList<>();

    public enum MessageCode
    {
        finishedLoadingWords,
        finishedAddingMembers
    }

    private FirebaseMemberOfTheMonth() {}

    public static FirebaseMemberOfTheMonth getInstance()
    {
        if (instance == null)
        {
            instance = new FirebaseMemberOfTheMonth();
        }

        return instance;
    }

    public void addMembersOfTheMonth(HashMap<String, MemberMonth> members)
    {
        // Get a new write batch (set a whole bunch of values at once)
        WriteBatch batch = db.batch();

        // Set the values of each member
        List<String> memberKeys = new ArrayList<>(members.keySet());
        for (String curKey : memberKeys)
        {
            MemberMonth curMember = members.get(curKey);
            DocumentReference curRef = db.collection(PATH).document(curKey);
            batch.set(curRef, curMember);
        }

        // Commit the batch
        batch.commit().addOnSuccessListener(new OnSuccessListener<Void>()
        {
            @Override
            public void onSuccess(Void aVoid)
            {
                Log.d(TAG, "addMembersOfTheMonth -> onSuccess: Successfully wrote members" +
                        "to database.");
                notifyObservers(MessageCode.finishedAddingMembers.toString());
            }
        }).addOnFailureListener(new OnFailureListener()
        {
            @Override
            public void onFailure(@NonNull Exception e)
            {
                Log.e(TAG, "addMembersOfTheMonth -> onFailure: Failed to write members to" +
                        "database");
            }
        });

    }

    public void loadMembersOfMonth()
    {
        CollectionReference docRef = db.collection(PATH);
        docRef.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>()
                {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots)
                    {
                        for (QueryDocumentSnapshot document : queryDocumentSnapshots)
                        {
                            Log.d(TAG, document.getId() + " => " + document.getData());
                            MemberMonth memberMonth = document.toObject(MemberMonth.class);
                            Log.d(TAG, "onSuccess: " + memberMonth.getName());
                            mMembersOfMonth.add(memberMonth);
                        }

                        //Finished loading all the members, so alert observers
                        notifyObservers(MessageCode.finishedLoadingWords.toString());
                    }
                })
                .addOnFailureListener(new OnFailureListener()
                {
                    @Override
                    public void onFailure(@NonNull Exception e)
                    {
                        Log.e(TAG, "onFailure: loadMembersOfMonth failed");
                    }
                });
    }

    public List<MemberMonth> getMembersOfMonth()
    {
        return mMembersOfMonth;
    }
}
