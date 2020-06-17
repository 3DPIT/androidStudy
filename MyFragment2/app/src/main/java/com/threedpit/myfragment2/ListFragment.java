package com.threedpit.myfragment2;

import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ListFragment extends Fragment {
    //구현 클래스 부분 변수 하나 생성해서
    imageSelectionCallback callback;

    @Override
    public void onAttach(@NonNull Context context) {
        //프래그먼트가 액티비티에 나타날때 제일 먼저 실행되는 메소드
        super.onAttach(context);
        //여기서는 메인액티비티를 나타냄
        //onAttach에서 받아지는 부분이 현재 프래그먼트가 띄워지는 액티비티
        //여기서는 메인 액티비티
        if(context instanceof imageSelectionCallback ){
            callback = (imageSelectionCallback) context;
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_list,container,false);

        //봄 화면 나타내는 버튼
        Button button = rootView.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //프래그먼트는 액티비티 위에 생성되는것으로
                //혹시나 액티비티가 없는 경우가 있을수 잇으니
                // 널 값을 확인 하고 이미지 변경해주는 메소드 실행
              if(callback!=null) {
                  callback.onImageSelected(0);
              }
            }
        });

        //여름 화면 나타내는 버튼
        Button button2 = rootView.findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(callback!=null){
                    callback.onImageSelected(1);
                }
            }
        });

        //가을 화면 나타내는 버튼
        Button button3 = rootView.findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(callback!=null){
                    callback.onImageSelected(2);
                }
            }
        });
        return rootView;
    }
}
