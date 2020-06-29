package com.threedpit.myreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;

public class SmsReceiver extends BroadcastReceiver {
    private  static final String TAG = "SmsReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
            Log.d(TAG,"onReceive 호출됨");

            Bundle bundle = intent.getExtras();
            //이 번들에는 extraData인 부가데이터가 들어있다.
            //이 번들객체를 파싱하는 메소드
            SmsMessage[] messages =  parseSmsMessage(bundle);
            // SmsMessage이 클래스는 이미 이api 안드로이드에
            //정의 되어있다.  이게 표준 프로토콜을 이용해서 서로
            //메세지를 주고 받게 되어있는데 이게 미리 객체로 만들어 놓은것


        //sms 메세지는 그냥 할수 없기 때문에 권한설정이 필요하다.
        if(messages !=null && messages.length>0){//1건이라도 있으면
            String sender = messages[0].getOriginatingAddress();//전화번호
            String contents = messages[0].getMessageBody();

            Log.d(TAG,"sender : "+ sender +"contents : "+ contents);


            //추가한것---------------------------------------
            //데이터 다른 액티비티로 보내기
            sendToActivity(context,sender,contents);
            //------------------------------------------------


        }
    }


    //추가한것---------------------------------------
    public void sendToActivity (Context context, String sender, String contents){
        Intent intent = new Intent(context,SmsActivity.class);

        //화면이 없는것에서 화면을 띄우는것이기 때문에
        //Flag 해줘야한다. 아래의 single top은
        //기존의sms액티비티 떠있으면 두번째 보낼때는 새로 만들지 않고 재사용
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("sender",sender);
        intent.putExtra("contents",contents);

        context.startActivity(intent);
    }
//------------------------------------------------------


    private SmsMessage[] parseSmsMessage(Bundle bundle) {
        //pdus 는 표준프로토콜에 맞춰넘어온것
        Object[] objs = (Object[]) bundle.get("pdus");
        SmsMessage[] messages = new SmsMessage[objs.length];


        int smsCount = objs.length;
        for (int i = 0; i < smsCount; i++) {
            //안드로이드 버전마다다름 if는 23이상이면 위에꺼로 아니면 아래로 해라 의미
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                String format = bundle.getString("format");
                messages[i] = SmsMessage.createFromPdu((byte[]) objs[i], format);
            } else {
                messages[i] = SmsMessage.createFromPdu((byte[]) objs[i]);
            }
        }
        return messages;
    }
}
