package com.example.boysandgirlsclubevents;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.boysandgirlsclubevents.Util.WebUtils;

public class JoinClubFragment extends Fragment
{
    public static final String TAG = "JoinClubFragment";
    private static final String ENG_MEMBERSHIP_FORM_URL = "http://bgclanc.org/wp-content/uploads/2017/02/Updated-Membership-Application-English-1.pdf";
    private static final String SPA_MEMBERSHIP_FORM_URL = "http://bgclanc.org/wp-content/uploads/2017/02/Updated-Membership-Application-Spanish-1.pdf";

    private Button engButton;
    private Button spaButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_join_club, container, false);
        engButton = view.findViewById(R.id.btn_joinEnglish_joinClub);
        spaButton = view.findViewById(R.id.btn_joinSpanish_joinClub);
        engButton.setOnClickListener(engListener);
        spaButton.setOnClickListener(spaListener);
        return view;
    }

    private View.OnClickListener engListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            WebUtils.openWebURL(ENG_MEMBERSHIP_FORM_URL, JoinClubFragment.this.getContext());
        }
    };

    private View.OnClickListener spaListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            WebUtils.openWebURL(SPA_MEMBERSHIP_FORM_URL, JoinClubFragment.this.getContext());
        }
    };
}
