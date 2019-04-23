package com.teamshark.boysandgirlsclubevents.MemberOfMonth;



import android.support.annotation.NonNull;
import android.util.Log;

import com.teamshark.boysandgirlsclubevents.Util.Observable;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirebaseMemberOfTheMonth extends Observable
{
    private static final String TAG = "FirebaseMemberMonth";
    private static FirebaseMemberOfTheMonth instance;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static final String CLUBHOUSE_KEY = "clubhouse";
    private static final String NAME_KEY = "name";
    private static final String PATH = "member of the month";

    private List<MemberMonth> mMembersOfMonth = new ArrayList<>();

    public enum MessageCode
    {
        finishedLoadingWords
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

    public void addMemberOfTheMonth(MemberMonth memberMonth)
    {

        Map<String, Object> dataToSave = new HashMap<>();
        dataToSave.put(CLUBHOUSE_KEY, memberMonth.getClubhouse());
        dataToSave.put(NAME_KEY, memberMonth.getName());

        db.collection("member of the month")
                .add(dataToSave)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
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
