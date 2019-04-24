package com.example.boysandgirlsclubevents;



import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.boysandgirlsclubevents.MemberMonth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

import static android.support.constraint.Constraints.TAG;

public class FirebaseMemberOfTheMonth{

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    public static final String CLUBHOUSE_KEY = "clubhouse";
    public static final String NAME_KEY = "name";
    public static final String TAG = "MemberOfTheMonth";
    //DocumentReference docRef = db.collection("member of the month").document()

    public void addMemberOfTheMonth(MemberMonth memberMonth) {
        //db.collection("member of the month").add(memberMonth);
        //MemberMonth newMember = new MemberMonth("Ashlen", "Hill Clubhouse");

        Map<String, Object> dataToSave = new HashMap<>();
        //data.put(memberMonth.getMemberClubhouse(), memberMonth.getMemberName());
        dataToSave.put(CLUBHOUSE_KEY, memberMonth.getMemberClubhouse());
        dataToSave.put(NAME_KEY, memberMonth.getMemberName());

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

    public void getAllUsers(){
        db.collection("member of the month")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot document : task.getResult()){
                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                        }
                        else{
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }




}
