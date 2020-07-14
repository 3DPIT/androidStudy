package com.threedpit.mylist;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//뷰홀더 라는 것을 담고 있는  어댑터를 상속한것
public class PersonAdapter extends  RecyclerView.Adapter<PersonAdapter.ViewHolder>{
   ArrayList<Person> items = new ArrayList<Person>();

   OnPersonItemClickListener listener;

   public void addItem(Person item){//Person 객체를 추가하기 위한 메소드
       items.add(item);
   }
   public void setItems(ArrayList<Person> items){//ArrayList 전체를 설정할 수 있는 메소드
       this.items = items;
   }
   public Person getItem(int position){// 몇번째 있는 아이템을 리턴해 달라
       return items.get(position);
   }
    public void setItem(int position, Person item ){
       items.set(position,item);
    }

    public void setOnItemClickListener(OnPersonItemClickListener listener){
       this.listener= listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 이것은 뷰홀더가 생성되는 시점에 자동으로 실행됨
        // 여기서는 각각의 아이템에 대한 뷰홀더를 만들어 달라는 것이기 때문에
        // 그 아이템을 위한 레이아웃을 인플레이션 하고 그 레이아웃 인플레이션한 뷰를 가지고
        // 그 뷰 홀더를 리턴해주면된다.
        //그렇게 하면 하나의 아이템을 위한 뷰홀더가 만들어져서 리턴됨
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());// getSystems에서 참조하는것이랑 비슷하다.
       View itemView = inflater.inflate(R.layout.person_item,parent,false);

        return new ViewHolder(itemView,listener);
    }

    @Override
    //뷰 홀더라는게 계속해서 만들어지는것이 아니다.
    //전화번호부의 연락처에 아이템이 백개가 될 수 있고 만개가 될 수도 있는데
    //그런것을 올릴때 마다 new 해주면 메모리 증가로 이어진다.
    //그럼 메모리의 낭비를 줄이기 위해 ViewHolder 는 재사용이 된다.
    //화면상에서 우리가 화면을 내리면 기존의 위로 올라간 뷰를
    //재사용하여 다시 표현해주는것 이라고 생각하자.
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    Person item = items.get(position);
    holder.setItem(item);
    }

    @Override
    //안의 객체 몇개인가요
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends  RecyclerView.ViewHolder{
       //글자가 두개 표시될 수 있도록 두개의 텍스트 뷰를 만들것이다.
        TextView textView;
        TextView textView2;
        public ViewHolder(View itemView,final OnPersonItemClickListener listener){
            super(itemView);

           textView = itemView.findViewById(R.id.textView);
           textView2 = itemView.findViewById(R.id.textView2);

           itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   int position = getAdapterPosition();// 눌린 position 받고
                   if(listener != null){
                       //listener를 통해 눌린지에 대한 정보를 보낼 수 있다.
                       listener.onItemClick(ViewHolder.this,v,position);

                   }
               }
           });
        }
        public void setItem(Person item){
            textView.setText(item.getName());
            textView2.setText(item.getMoblie());
        }
    }
    //우리가 하나의 아이템이 화면에 보여질 때
    //레이아웃을 만들 예정인데 그 레이아웃에 해당하는 뷰를 담아둘 객체
    // 그것을 홀더라고 한다. 필요한때 쓰고

}
