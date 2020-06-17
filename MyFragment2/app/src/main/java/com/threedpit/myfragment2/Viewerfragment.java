package com.threedpit.myfragment2;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Viewerfragment extends Fragment {
    ImageView imageView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_viewer,container,false);

        imageView = rootView.findViewById(R.id.imageView);

        return rootView;
    }
    public void setImage(int resID){
        //이미지 변경해주는 메소드
        imageView.setImageResource(resID);
    }
}
