package com.threedpit.myfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

/**
 * fragment_main.xml에 있는 것을
 * 인플레이션 한다. 즉, 메모리에 올리면서
 * 이 프래그먼트에 연결해준다 생각하면된다.
 * */
public class Main2Fragment extends Fragment {
    @Override
    /**
     *     현재 자바 파일과 xml 파일 이연결되는 메소드가
     *     onCreateView 이다.
     */
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        /**res폴더에 layout폴더안에 있는 fragment_main.xml을
         * container라는 뷰 그룹에 넣어라이고
         * 이게 연결되고 액티비티 화면에 올라가는 시간이 달라
         * false로 해준다고 일단은 생각하자 )*/
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_main2, container, false);
        Button button = rootView.findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 우리가 직접 화면을 띄우는것이 아니니
                 *액티비티에 권한을 위임해야한다.
                 *항상 프래그먼트는 액티비티 위에 올라가 있는다.
                 *그래서 올라가 있는 액티비티를 참조하기 위해서는  getActivity()라는 매소드를 쓴다.
                 * */
                MainActivity activity = (MainActivity)getActivity();
                activity.onFragmentChanged(0); /**1이면 Main2 프래그먼트를 띄우고
                 0이면 Main 프래그먼트를 띄움 */
            }
        });
        return rootView;
    }
}
