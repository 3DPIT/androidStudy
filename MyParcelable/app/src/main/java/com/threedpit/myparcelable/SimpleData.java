package com.threedpit.myparcelable;

import android.os.Parcel;
import android.os.Parcelable;

public class SimpleData implements Parcelable {
    /**
     * 메시지와 코드값을 넣기 위한 변수 생성
     * */
    int code;
    String message;

    public SimpleData(int code, String message){/**생성자 함수*/
        this.code= code;
        this.message=message;
    }
    public SimpleData(Parcel src){
        /**Parcel 안에 코드와 메시지가 있다고 전제하는것 */
        /**parcel에서 read 부분은
         * parcel 안에 있는 정보를 이용하여 Simple데이터를 만드는것 */

        code = src.readInt();
        message =src.readString();
    }
    /**Parcelable 형태로 만들기 위한 코드*/
public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public SimpleData createFromParcel(Parcel in) {
            /**createFormParcel 이라는 SimpleData 객체를 만들 떄
             * Parcel 안에 있는것을 만들겟다.
             * */
            return new SimpleData(in);
        }
        public SimpleData[] newArray(int size) {
            return new SimpleData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        /**Simple 데이터를 이용해서 parcel에 쓰는 역활*/
        dest.writeInt(code);
        dest.writeString(message);
    }
}
/**Serializerable로 안하고 Parceable로 하는 이유는
 * Serializerable 안에 데이터가 어마어마하게 많은
 * parceable은 원하는 데이터만 보내기 위해 사용( 귀찬긴한 단점이있다)
 * */