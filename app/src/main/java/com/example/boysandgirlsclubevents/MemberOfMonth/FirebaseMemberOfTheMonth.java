package com.example.boysandgirlsclubevents.MemberOfMonth;



import android.support.annotation.NonNull;
import android.util.Log;

import com.example.boysandgirlsclubevents.Util.Observable;
import com.google.android.gms.tasks.OnCompleteListener;
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
    //public MemberMonth memberMonth = new MemberMonth("Placeholder", "Placeholder");

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

    public void addMemberOfTheMonth(MemberMonth newMember)
    {

        Map<String, Object> dataToSave = new HashMap<>();
        dataToSave.put(CLUBHOUSE_KEY, newMember.getClubhouse());
        dataToSave.put(NAME_KEY, newMember.getName());

        db.collection(PATH).add(dataToSave)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, "Error adding document", e);
            }
        });

        /*
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
                */
    }
    /*
    public void updateData() {

        for(int h = 0; h < mMembersOfMonth.size(); h++){
            db.collection(PATH).document()
                    .delete()
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d(TAG, "DocumentSnapshot successfully deleted!");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error deleting document", e);
                        }
                    });
            mMembersOfMonth.remove(h);
        }

        Map<String, Object> dataToSave = new HashMap<>();
        dataToSave.put(CLUBHOUSE_KEY, memberMonth.getClubhouse());
        dataToSave.put(NAME_KEY, memberMonth.getName());

        for (int i = 0; i < mMembersOfMonth.size(); i++) {
            //db.collection(PATH)
            //String id = db.collection(PATH).document().getId();
            DocumentReference docRef = db.collection(PATH).document();
            docRef.set(dataToSave);
        }
    }
    */

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
