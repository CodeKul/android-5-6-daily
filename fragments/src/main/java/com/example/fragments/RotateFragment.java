package com.example.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;


/**
 * A simple {@link Fragment} subclass.
 */
public class RotateFragment extends Fragment {


    public RotateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rtVw = inflater.inflate(R.layout.fragment_rotate, container, false);

        Animation anim = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate);
        rtVw.findViewById(R.id.imgVw).startAnimation(anim);

        return rtVw;
    }
}
