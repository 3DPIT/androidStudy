# 솔 SW 어필

허균이



아이구



압전소자



IOT,BOT



블루투스 관련 소스



 음성인식 



## 허균이 VER.2 음성인식

#### 1. 하드웨어 음성인식 부분 발췌

``` c++
#include "VoiceRecognitionV3.h"

#define Check    (0)
#define Lighton   (1)
#define Lightoff   (2)
#define Call   (3)
VR myVR(13, 12);    // 13:RX 12:TX,  you can choose your favourite pins.

uint8_t records[7]; // save record
uint8_t buf[64];

  myVR.begin(9600);
  Serial.begin(9600);
  Serial.println("Elechouse Voice Recognition V3 Module\r\nControl LED sample");
  if (myVR.clear() == 0){
    Serial.println("Recognizer cleared.");
  }
  else{
    Serial.println("Not find VoiceRecognitionModule.");
    Serial.println("Please check connection and restart Arduino.");
    while (1);
  }
  if (myVR.load((uint8_t)Check) >= 0){
    Serial.println("Check");
  }
  if (myVR.load((uint8_t)Lighton) >= 0){
    Serial.println("on");
  }
  if (myVR.load((uint8_t)Lightoff) >= 0){
    Serial.println("off");
  }
  if (myVR.load((uint8_t)Call) >= 0){
    Serial.println("call");
  }
  END2();
    END();
}



  ret = myVR.recognize(buf, 50);
  if (ret > 0){
    switch (buf[1]){
    case Check:
      cycle = 0;
      cnt = 0;
      minn = 0;
      cc=0;
      break;
    case Lighton:
      All();
      break;
    case Lightoff:
      END2();
      break;
      case Call :
      for(i=0;i<50;i++){
        sprintf(data,"%03d%03d%03d",111,angle,c);
        bluetooth1.println(data);
        Serial.println("Ddddddddddddd");
        Serial.println(data);
      }
        break;
      default:
        Serial.println("Record function undefined");
        break;

	} 

}
```



영상  :  https://www.youtube.com/watch?v=UlxhAm_sl64

3분46초 - 13분까지 참고하면되고



#### 2. 허균이 데이터 보내는 부분

``` c++
//전화 전송시
sprintf(data,"%03d%03d%03d",111,angle,c);
        bluetooth1.write(data);
        Serial.println("Ddddddddddddd");
        Serial.println(data);
//평소
sprintf(data,"%03d%03d%03d",0,angle,c);
        bluetooth1.write(data);
        Serial.println(data);
```



#### 3. 전체소스

```c++
  **  허균이 본체 부분 **

  #include <SoftwareSerial.h>
#include "VoiceRecognitionV3.h"
SoftwareSerial bluetooth(3,2);
SoftwareSerial bluetooth1(10, 11);
VR myVR(13, 12);    // 13:RX 12:TX, you can choose your favourite pins.
int ret;
int end = 0;
uint8_t records[7]; // save record
uint8_t buf[64];
#define Check    (0)
#define Lighton   (1)
#define Lightoff   (2)
#define Call   (3)  
#include "Wire.h"
#include "MPU6050.h"
#include "I2Cdev.h"
float angle_x = 0, angle_y = 0, angle_z = 0;
#define pi 3.141592
#define RADIANS_TO_DEGREES 180/3.14159
#define fs 131.0;
int BUTTON = A1;
MPU6050 mpu;
int16_t ax, ay, az;
int16_t gx, gy, gz;
int16_t mx, my, mz;
//자이로(각속도)
float gyro_x, gyro_y, gyro_z;
//최종 가속도,자이로 각도
float accel_x, accel_y, accel_z;
float gyro_angle_x = 0, gyro_angle_y = 0, gyro_angle_z = 0;
//상보필터 거친 각도
//자이로센서 바이어스값
float base_gx = 0, base_gy = 0, base_gz = 0;
unsigned long pre_msec = 0;
void calibrate(){
  int loop = 10;
  for (int i = 0; i < loop; i++)
  {
    mpu.getMotion9(&ax, &ay, &az, &gx, &gy, &gz, &mx, &my, &mz);
    base_gx += gx;
    base_gy += gy;
    base_gz += gz;
    delay(80);
  }
  base_gx /= loop;
  base_gy /= loop;
  base_gz /= loop;
}
#define LED2_B  4 //LED1 B  포트
#define LED2_G  5 //LED1 G 포트
#define LED2_R  6 //LED1 R 포트

#define LED1_B  7 //LED2 B  포트
#define LED1_G  8 //LED2 G 포트
#define LED1_R  9 //LED2 R 포트

#define VibrateMotor1 A2
#define VibrateMotor2 A3
#define Caution -20
#define Safezone 13
char Send;
int chk = 0;//전역
int flex = 0;//전역
int minn = 0;//전역
int cnt = 0;//첫번째단계에서사용되는 초단위.  //전역
int cycle = 1;//loop속 몇번 도는지.   //전역
int c = 0;
int c_chk = 1;
void setup() {

  pinMode(BUTTON, INPUT);
  Serial.begin(9600);
  bluetooth.begin(9600);
  bluetooth1.begin(9600);
  pinMode(LED1_B, OUTPUT);
  pinMode(LED1_G, OUTPUT);
  pinMode(LED1_R, OUTPUT);
  pinMode(LED2_B, OUTPUT);
  pinMode(LED2_G, OUTPUT);
  pinMode(LED2_R, OUTPUT);
  pinMode(VibrateMotor1, OUTPUT);
  pinMode(VibrateMotor2, OUTPUT);
  Wire.begin();
  Serial.begin(9600);
  mpu.initialize();
  calibrate();
  myVR.begin(9600);
  Serial.begin(9600);
  Serial.println("Elechouse Voice Recognition V3 Module\r\nControl LED sample");
  if (myVR.clear() == 0){
    Serial.println("Recognizer cleared.");
  }
  else{
    Serial.println("Not find VoiceRecognitionModule.");
    Serial.println("Please check connection and restart Arduino.");
    while (1);
  }
  if (myVR.load((uint8_t)Check) >= 0){
    Serial.println("Check");
  }
  if (myVR.load((uint8_t)Lighton) >= 0){
    Serial.println("on");
  }
  if (myVR.load((uint8_t)Lightoff) >= 0){
    Serial.println("off");
  }
  if (myVR.load((uint8_t)Call) >= 0){
    Serial.println("call");
  }
  END2();
    END();
}
void Vibrate()
{
  digitalWrite(VibrateMotor1, LOW);
  digitalWrite(VibrateMotor2, LOW);
}
void First()//빨간불
{
  digitalWrite(LED1_R, LOW);
  digitalWrite(LED1_G, HIGH);
  digitalWrite(LED1_B, HIGH);
}
void Middle()//노란불
{
  digitalWrite(LED1_R, LOW);
  digitalWrite(LED1_G, LOW);
  digitalWrite(LED1_B, HIGH);
}void Last()// 그린불
{
  digitalWrite(LED1_R, HIGH);
  digitalWrite(LED1_G, LOW);
  digitalWrite(LED1_B, HIGH);
}
void END()
{
  digitalWrite(LED1_R, HIGH);
  digitalWrite(LED1_G, HIGH);
  digitalWrite(LED1_B, HIGH);

  digitalWrite(A2, HIGH);
  //digitalWrite(A3, HIGH);
}
void Restart()
{
  digitalWrite(LED1_R, LOW);
  digitalWrite(LED1_G, HIGH);
  digitalWrite(LED1_B, HIGH);

  digitalWrite(LED1_R, LOW);
  digitalWrite(LED1_G, LOW);
  digitalWrite(LED1_B, HIGH);

  digitalWrite(LED1_R, HIGH);
  digitalWrite(LED1_G, LOW);
  digitalWrite(LED1_B, HIGH);
  delay(500);
  END();
  delay(400);
  digitalWrite(LED1_R, LOW);
  digitalWrite(LED1_G, HIGH);
  digitalWrite(LED1_B, HIGH);

  digitalWrite(LED1_R, LOW);
  digitalWrite(LED1_G, LOW);
  digitalWrite(LED1_B, HIGH);

  digitalWrite(LED1_R, HIGH);
  digitalWrite(LED1_G, LOW);
  digitalWrite(LED1_B, HIGH);
  delay(500);

  END();

}
void Start()
{
  First();
  delay(500);
  Middle();
  delay(500);
  Last();
  delay(500);
  END();
  delay(500);
  Restart();
}
void All()
{
  digitalWrite(LED2_R, LOW);
  digitalWrite(LED2_G, LOW);
  digitalWrite(LED2_B, LOW);
}
void END2()
{
  digitalWrite(LED2_R, HIGH);
  digitalWrite(LED2_G, HIGH);
  digitalWrite(LED2_B, HIGH);
}
void RedLight()
{
  digitalWrite(LED1_R, LOW);
  digitalWrite(LED1_G, HIGH);
  digitalWrite(LED1_B, HIGH);
}
void GreenLight()
{
  digitalWrite(LED1_R, HIGH);
  digitalWrite(LED1_G, LOW);
  digitalWrite(LED1_B, HIGH);
}
int angle = 0;
char data[11] = { 0, };
void printSignature(uint8_t *buf, int len)
{
  int i;
  for (i = 0; i<len; i++){
    if (buf[i]>0x19 && buf[i] < 0x7F){
      Serial.write(buf[i]);
    }
    else{
      Serial.print("[");
      Serial.print(buf[i], HEX);
      Serial.print("]");
    }
  }
}
void printVR(uint8_t *buf)
{
  Serial.println("VR Index\tGroup\tRecordNum\tSignature");

  Serial.print(buf[2], DEC);
  Serial.print("\t\t");

  if (buf[0] == 0xFF){
    Serial.print("NONE");
  }
  else if (buf[0] & 0x80){
    Serial.print("UG ");
    Serial.print(buf[0] & (~0x80), DEC);
  }
  else{
    Serial.print("SG ");
    Serial.print(buf[0], DEC);
  }
  Serial.print("\t");

  Serial.print(buf[1], DEC);
  Serial.print("\t\t");
  if (buf[3] > 0){
    printSignature(buf + 4, buf[3]);
  }
  else{
    Serial.print("NONE");
  }
  Serial.println("\r\n");
}

int gyro()
{
  float dt = (millis() - pre_msec) / 1000.0;
  pre_msec = millis();
  mpu.getMotion9(&ax, &ay, &az, &gx, &gy, &gz, &mx, &my, &mz);
  //가속도값 아크탄젠트->각도변환
  accel_x = atan(ay / sqrt(pow(ax, 2) + pow(az, 2)))*RADIANS_TO_DEGREES;
  accel_y = atan(-1 * ax / sqrt(pow(ay, 2) + pow(az, 2)))*RADIANS_TO_DEGREES;
  accel_z = (atan(-1 * ax / sqrt(pow(az, 2) + pow(ay, 2)))*RADIANS_TO_DEGREES) + (atan(ay / sqrt(pow(ax, 2) + pow(ax, 2)))*RADIANS_TO_DEGREES);
  //자이로 -32766~+32766을 실제 250degree/s로 변환
  //앞에서 계산한 오차의 평균값을 빼줌 
  gyro_x = (gx - base_gx) / fs;
  gyro_y = (gy - base_gy) / fs;
  gyro_z = (gz - base_gz) / fs;
  //변화량을 적분 
  gyro_angle_x = angle_x + dt*gyro_x;
  gyro_angle_y = angle_y + dt*gyro_y;
  gyro_angle_z = angle_z + dt*gyro_z;
  //상보필터
  angle_x = 0.95*gyro_angle_x + 0.05*accel_x;
  angle_y = 0.95*gyro_angle_y + 0.05*accel_y;
  angle_z = 0.95*gyro_angle_z + 0.05*accel_z;
  return (-1*(angle_y-90))-20;
}
int i=0;
int cc=0;
void loop()
{
  END();
  if (digitalRead(BUTTON) == HIGH){
    cycle = 0;
    cnt = 0;
    minn = 0;
    cc=0;
  }
  ret = myVR.recognize(buf, 50);
  if (ret > 0){
    switch (buf[1]){
    case Check:
      cycle = 0;
      cnt = 0;
      minn = 0;
      cc=0;
      break;
    case Lighton:
      All();
      break;
    case Lightoff:
      END2();
      break;
      case Call :
      for(i=0;i<50;i++){
        sprintf(data,"%03d%03d%03d",111,angle,c);
        bluetooth1.write(data);
        Serial.println("Ddddddddddddd");
        Serial.println(data);
      }
        break;
      default:
        Serial.println("Record function undefined");
        break;
    }
    printVR(buf);
  }

  if (!cycle)
  {
    bluetooth.write('S');
    Start();
    //Start1();  
    delay(550);
    First();
    // First1();
    bluetooth.write('S');
  }
  while (cycle == 0)
  {
    First();
    // First1();
    angle = gyro();
    Serial.println(angle);
    if (minn < angle)
    {
      minn = angle;
    }
    cnt++;
    Serial.print("cnt : ");
    Serial.println(cnt);
    if (cnt == 300)    //1000은 임의의값
    {
      if (minn > -30)
      {
            angle = gyro();
        bluetooth.write('C');
        Middle();
        //Middle1();
        delay(1500);
        break;
      }
      else//튕긴값이라면 LED restart함수를 호출하면서 cnt 초기화.
      {
        bluetooth.write('R');
        Restart();
        // Restart1();
        cnt = 0;
      }
    }//if(시간이되었으면)끝괄호
  }
  if (cycle == 0)
  {
    Serial.print("minn : ");
    Serial.println(minn);
    bluetooth.write('G');
    //GreenLight();
    Last();
    //Last1();
    delay(1000);
    END();
    //END1();
  }
  angle = gyro();
  int count_c = 0;
  Serial.println(angle);
  if (minn -20 >= angle) // 초기값+주의값이면 삐 
  {
    int count = 0;
    while (1){
      count++;
      if (count == 300)
      {
        break;
      }
      angle = gyro();
      if (angle >= minn)
      {
        count_c = 1;
        break;
      }
       bluetooth1.print(data);
    }
    while (1)
    {
       if (digitalRead(BUTTON) == HIGH){
    cycle = 0;
    cnt = 0;
    minn = 0;
    cc=0;
  }
  ret = myVR.recognize(buf, 50);
  if (ret > 0){
    switch (buf[1]){
    case Check:
      cycle = 0;
      cnt = 0;
      minn = 0;
      cc=0;
      break;
    case Lighton:
      All();
      break;
    case Lightoff:
      END2();
      break;
      case Call :
      for(i=0;i<50;i++){
        sprintf(data,"%03d%03d%03d",111,angle,c);
        bluetooth1.println(data);
        Serial.println("Ddddddddddddd");
        Serial.println(data);
      }
        break;
      default:
        Serial.println("Record function undefined");
        break;
    }
    printVR(buf);
  }
      if (count_c == 1) break;
      angle = gyro();
      Serial.println(angle);
      bluetooth.write('R');
      RedLight();
      //RedLight1();
      if(cc==0){
     Vibrate();
    delay(1000);
    cc=1;
    }
    if(cc==1)
    {
      digitalWrite(VibrateMotor1, HIGH);
  digitalWrite(VibrateMotor2, HIGH);
    }
     Serial.println(cc);
      delay(100);
      if (c_chk)
      {
        c++;
        c_chk = 0;
      }
      sprintf(data,"%03d%03d%03d",0,angle,c);
      bluetooth1.println(data);
      delay(1);
      angle = gyro();
      Serial.println(angle);
      if (angle >= minn-5)
      {
        bluetooth.write('G');
        GreenLight();
        delay(1500);
        cc=0;
        END();
        //END1();
        break;
      }
    }
  }
  else
  {
    END();
    //END1();
  }
  Serial.println(c);
  c_chk = 1;
  cycle = 2;
  sprintf(data,"%03d%03d%03d",0,angle,c);
      bluetooth1.println(data);
   Serial.println(data);

}
```



## 아이구 소스 

#### 1. 데이터 보내는 부분

``````c++
char data[27]={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}; //처음위도값 저장 나중에 전체 데이터 저장
char data1[25]={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};// 경도값 저장
char battery[20]={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}; //배터리값 저장
String copy; //전체데이터 저장후 블루투스전송

float voltage = 0;
float latitude, longitude;



  Serial.println(longitude,5);
  dtostrf(latitude,5,5,data);
  dtostrf(longitude,5,5,data1);
  dtostrf(idx,3,3,battery);
  data[8]='/';
  int i=0;
  for(i=0;i<=8;i++)
  {
   data[i+9]=data1[i];
  }
  data[18]='/';
   for(i=0;i<=3;i++)
  {
   data[i+19]=battery[i];
  }
  data[23]='/';
  data[24]='0';
  copy=data;
  bluetooth.listen();
  bluetooth.println(copy);
  Serial.println(copy);
``````





#### 2. 전체소스

``` c++


#include <SoftwareSerial.h>
#include <TinyGPS.h>
#define TXPIN 2
#define RXPIN 3
#define GPSBAUD 9600
TinyGPS gps;
SoftwareSerial uart_gps(TXPIN, RXPIN);//tx2,rx3
SoftwareSerial bluetooth(4,5); //tx4,rx5
char data[27]={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}; //처음위도값 저장 나중에 전체 데이터 저장
char data1[25]={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};// 경도값 저장
char battery[20]={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}; //배터리값 저장
String copy; //전체데이터 저장후 블루투스전송
int LED = 7;
int LED1 = 13;
int BUTTON = 6;
int state = 0;
int buttonState;
int count = 0;
int count1 =0;
int chk=0;
int analogValue = 0;
float voltage = 0;
float latitude, longitude;
void getgps(TinyGPS &gps);
void init_F();
void Button();
void setup()
{
  Serial.begin(9600);
  uart_gps.begin(GPSBAUD);
  bluetooth.begin(9600);
  Serial.println("");
  Serial.println("GPS Shield QuickStart Example Sketch v12");
  Serial.println("       ...waiting for lock...           ");
  Serial.println("");
  pinMode(LED,OUTPUT);
  pinMode(11,OUTPUT);
  pinMode(12,OUTPUT);
  pinMode(13,OUTPUT);
 pinMode(BUTTON,INPUT);
}
float idx=0;
      int c=0;
      int cc=0;
void loop()
{

 if(c==0)
 {
R();
G();
c=1;
 }
   Serial.println(count1);
   uart_gps.listen();
 //  Serial.println(chk);
   while(uart_gps.available())
  {
  int c = uart_gps.read();    // load the data into a variable...
      if(gps.encode(c))      // if there is a new valid sentence...
      {
        getgps(gps);         //then grab the data.
      }   
       Button(); 
  }    
  init_F();
  Button();  

}
void getgps(TinyGPS &gps)
{
  chk=1;
  analogValue = analogRead(0);
  voltage = (((float)analogValue) / 1024.0 ) * 4.5;
  //Serial.println(voltage);
  idx=(voltage/4.2)*100;
  gps.f_get_position(&latitude, &longitude);
  Serial.print("Lat/Long: "); 
  Serial.print(latitude,5); 
  Serial.print(", "); 
  Serial.println(longitude,5);
  dtostrf(latitude,5,5,data);
  dtostrf(longitude,5,5,data1);
  dtostrf(idx,3,3,battery);
  data[8]='/';
  int i=0;
  for(i=0;i<=8;i++)
  {
   data[i+9]=data1[i];
  }
  data[18]='/';
   for(i=0;i<=3;i++)
  {
   data[i+19]=battery[i];
  }
  data[23]='/';
  data[24]='0';
  copy=data;
  bluetooth.listen();
  bluetooth.println(copy);
  Serial.println(copy);
  unsigned long chars;
  unsigned short sentences, failed_checksum;
  gps.stats(&chars, &sentences, &failed_checksum);
  delay(100);
    digitalWrite(LED,HIGH);
  delay(100);
  digitalWrite(LED,LOW);
  delay(100); 
}
void init_F()
{

  analogValue = analogRead(0);
  voltage = (((float)analogValue) / 1024.0 ) * 4.2;
  //Serial.println(voltage);
  idx=(voltage/4.2)*100;
  Serial.print("Lat/Long: "); 
  Serial.print(latitude,5); 
  Serial.print(", "); 
  Serial.println(longitude,5);
 latitude=35.0217234;
  longitude=126.7841259;
  dtostrf(latitude,5,5,data);
  dtostrf(longitude,5,5,data1);
  dtostrf(idx,3,4,battery);
  data[8]='/';
  int i=0;
  for(i=0;i<=8;i++)
  {
   data[i+9]=data1[i];
  }
  data[18]='/';
   for(i=0;i<=3;i++)
  {
   data[i+19]=battery[i];
  }
  data[23]='/';
  data[24]='0';
  copy=data;
  bluetooth.listen();
  bluetooth.println(copy);
  Serial.println(copy);
       digitalWrite(11,HIGH);
digitalWrite(12,HIGH);
digitalWrite(13,LOW);
delay(100);
  digitalWrite(11,HIGH);
digitalWrite(12,HIGH);
digitalWrite(13,HIGH);
delay(100);

}
void Button()
{

  buttonState = digitalRead(BUTTON);
 if(count1>0)
    {
    count1++;
    }
    if(count1==10){
      count =0;
      count1=0;
    }
 if(buttonState == HIGH){
  if(state == 0){
    delay(10);
    state = 1;
  }
 }
 if(buttonState == LOW){
  if(state == 1){
    count +=1;
    delay(10);
    state = 0;
    if(count1==0)count1=1;
  }
 }
 if(count >= 2){ //두번누르게되면 블루투스에 값전송
  int c = uart_gps.read();    // load the data into a variable...
      if(gps.encode(c))      // if there is a new valid sentence...
      {
        getgps(gps);         //then grab the data.
      }   
  data[24]='1';
  analogValue = analogRead(0);
  voltage = (((float)analogValue) / 1024.0 ) * 4.2;
  //Serial.println(voltage);
  idx=(voltage/4.2)*100;
 latitude=35.0217234;
  longitude=126.7841259;
  dtostrf(latitude,5,5,data);
  dtostrf(longitude,5,5,data1);
  dtostrf(idx,3,4,battery);
  data[8]='/';
  int i=0;
  for(i=0;i<=8;i++)
  {
   data[i+9]=data1[i];
  }
  data[18]='/';
   for(i=0;i<=3;i++)
  {
   data[i+19]=battery[i];
  }
  data[23]='/';
  data[24]='1';
  copy=data;
  bluetooth.listen();
  bluetooth.println(copy);
  Serial.println(copy);
  delay(100);
  digitalWrite(LED,HIGH);
  delay(1000);
  digitalWrite(LED,LOW);
  delay(10); 
  count=0;
  count1=0;
 }
}
void R()
{
 digitalWrite(11,HIGH);
digitalWrite(12,HIGH);
digitalWrite(13,LOW);
delay(500);
digitalWrite(11,HIGH);
digitalWrite(12,HIGH);
digitalWrite(13,HIGH);
delay(500);
}
void B()
{
 digitalWrite(11,HIGH);
digitalWrite(12,LOW);
digitalWrite(13,HIGH);
delay(500);
digitalWrite(11,HIGH);
digitalWrite(12,HIGH);
digitalWrite(13,HIGH);
delay(500);
}
void G()
{
 digitalWrite(11,LOW);
digitalWrite(12,HIGH);
digitalWrite(13,HIGH);
delay(1000);
digitalWrite(11,HIGH);
digitalWrite(12,HIGH);
digitalWrite(13,HIGH);
delay(1000);
}
void RGB()
{
 digitalWrite(11,HIGH);
digitalWrite(12,HIGH);
digitalWrite(13,LOW);
delay(500);
 digitalWrite(11,HIGH);
digitalWrite(12,LOW);
digitalWrite(13,HIGH);
delay(500);
 digitalWrite(11,LOW);
digitalWrite(12,HIGH);
digitalWrite(13,HIGH);
delay(500);
digitalWrite(11,HIGH);
digitalWrite(12,HIGH);
digitalWrite(13,HIGH);
delay(500);
}


```

