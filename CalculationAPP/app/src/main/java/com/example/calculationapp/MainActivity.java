package com.example.calculationapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static androidx.lifecycle.ViewModelProviders.*;

public class MainActivity extends AppCompatActivity {

    MyViewModel myviewmodeel;
    TextView text_score,text_text;
    Button button_yaku_1,button_yaku_2,button_yaku_3,button_yaku_4,button_yaku_5,button_yaku_6,
            button_yaku_7,button_yaku_8,button_yaku_9,button_yaku_10,button_yaku_11,button_yaku_12,
            button_yaku_13,button_yaku_14,button_yaku_15,button_yaku_16,button_yaku_17,
            button_yaku_18,button_yaku_19,button_yaku_20,button_yaku_21,button_back,button_next;
     int flag1,flag2,flag3,flag4,flag5,flag6,flag7,flag8,flag9,flag10,flag11,flag12,flag13,
            flag14,flag15,flag16,flag17,flag18,flag19,flag20,flag21;
     String yaku_1="",yaku_2="",yaku_3="",yaku_4="",yaku_5="",yaku_6="",yaku_7="",yaku_8="",
             yaku_9="",yaku_10="",yaku_11="",yaku_12="",yaku_13="",yaku_14="",yaku_15="",yaku_16="",
             yaku_17="",yaku_18="",yaku_19="",yaku_20="",yaku_21="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myviewmodeel = ViewModelProviders.of(this).get(MyViewModel.class);
        button_yaku_1 = findViewById(R.id.button_yaku_1);
        button_yaku_2 = findViewById(R.id.button_yaku_2);
        button_yaku_3 = findViewById(R.id.button_yaku_3);
        button_yaku_4 = findViewById(R.id.button_yaku_4);
        button_yaku_5 = findViewById(R.id.button_yaku_5);
        button_yaku_6 = findViewById(R.id.button_yaku_6);
        button_yaku_7 = findViewById(R.id.button_yaku_7);
        button_yaku_8 = findViewById(R.id.button_yaku_8);
        button_yaku_9 = findViewById(R.id.button_yaku_9);
        button_yaku_10 = findViewById(R.id.button_yaku_10);
        button_yaku_11 = findViewById(R.id.button_yaku_11);
        button_yaku_12 = findViewById(R.id.button_yaku_12);
        button_yaku_13 = findViewById(R.id.button_yaku_13);
        button_yaku_14 = findViewById(R.id.button_yaku_14);
        button_yaku_15 = findViewById(R.id.button_yaku_15);
        button_yaku_16 = findViewById(R.id.button_yaku_16);
        button_yaku_17 = findViewById(R.id.button_yaku_17);
        button_yaku_18 = findViewById(R.id.button_yaku_18);
        button_yaku_19 = findViewById(R.id.button_yaku_19);
        button_yaku_20 = findViewById(R.id.button_yaku_20);
        button_yaku_21 = findViewById(R.id.button_yaku_21);
        button_back = findViewById(R.id.button_back);
        button_next = findViewById(R.id.button_next);
        text_score =findViewById(R.id.textView_score);
        text_text = findViewById(R.id.textView_text);

        button_yaku_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag1++;
                if (flag1%2==1) {
                    myviewmodeel.Score++;
                    yaku_1="自摸 ";
                }
                else
                {
                    myviewmodeel.Score--;
                    yaku_1="";

                }
                text_text.setText(yaku_1+yaku_2+yaku_3+yaku_4+yaku_5+yaku_6+yaku_7+yaku_8+
                        yaku_9+yaku_10+yaku_11+yaku_12+yaku_13+yaku_14+yaku_15+yaku_16+
                        yaku_17+yaku_18+yaku_19+yaku_20+yaku_21);
                text_score.setText(String.valueOf(myviewmodeel.Score));
            }
        });
        button_yaku_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag2++;
                if (flag2%2==1) {
                    myviewmodeel.Score++;
                    yaku_2="門清 ";
                }
                else
                {
                    myviewmodeel.Score--;
                    yaku_2="";
                }
                text_text.setText(yaku_1+yaku_2+yaku_3+yaku_4+yaku_5+yaku_6+yaku_7+yaku_8+
                        yaku_9+yaku_10+yaku_11+yaku_12+yaku_13+yaku_14+yaku_15+yaku_16+
                        yaku_17+yaku_18+yaku_19+yaku_20+yaku_21);
                text_score.setText(String.valueOf(myviewmodeel.Score));
            }
        });
        button_yaku_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag3++;
                if (flag3%4==1) {
                    myviewmodeel.Score++;
                    yaku_3="三元 ";
                }
                else if(flag3%4==2)
                {
                    myviewmodeel.Score++;
                    yaku_3="三元*2";
                }
                else if(flag3%4==3)
                {
                    myviewmodeel.Score+=6;
                    yaku_3="大三元";
                }
                else if(flag3%4==0)
                {
                    myviewmodeel.Score-=8;
                    yaku_3="";
                }
                text_text.setText(yaku_1+yaku_2+yaku_3+yaku_4+yaku_5+yaku_6+yaku_7+yaku_8+
                        yaku_9+yaku_10+yaku_11+yaku_12+yaku_13+yaku_14+yaku_15+yaku_16+
                        yaku_17+yaku_18+yaku_19+yaku_20+yaku_21);
                text_score.setText(String.valueOf(myviewmodeel.Score));
            }
        });
        button_yaku_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag4++;
                if (flag4%2==1) {
                    myviewmodeel.Score++;
                    yaku_4="場風 ";
                }
                else
                {
                    myviewmodeel.Score--;
                    yaku_4="";
                }
                text_text.setText(yaku_1+yaku_2+yaku_3+yaku_4+yaku_5+yaku_6+yaku_7+yaku_8+
                        yaku_9+yaku_10+yaku_11+yaku_12+yaku_13+yaku_14+yaku_15+yaku_16+
                        yaku_17+yaku_18+yaku_19+yaku_20+yaku_21);
                text_score.setText(String.valueOf(myviewmodeel.Score));
            }
        });
        button_yaku_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag5++;
                if (flag5%2==1) {
                    myviewmodeel.Score++;
                    yaku_5="門風 ";
                }
                else
                {
                    myviewmodeel.Score--;
                    yaku_5="";
                }
                text_text.setText(yaku_1+yaku_2+yaku_3+yaku_4+yaku_5+yaku_6+yaku_7+yaku_8+
                        yaku_9+yaku_10+yaku_11+yaku_12+yaku_13+yaku_14+yaku_15+yaku_16+
                        yaku_17+yaku_18+yaku_19+yaku_20+yaku_21);
                text_score.setText(String.valueOf(myviewmodeel.Score));
            }
        });
        button_yaku_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flag11 % 2 == 0) {
                    flag6++;
                    if (flag6 % 3 == 1) {
                        myviewmodeel.Score++;
                        yaku_6 = "正花*1 ";
                    } else if (flag6 % 3 == 2) {
                        myviewmodeel.Score++;
                        yaku_6 = "正花*2 ";
                    }else  {
                        myviewmodeel.Score-=2;
                        yaku_6 = "";
                    }
                    text_text.setText(yaku_1 + yaku_2 + yaku_3 + yaku_4 + yaku_5 + yaku_6 + yaku_7 + yaku_8 +
                            yaku_9 + yaku_10 + yaku_11 + yaku_12 + yaku_13 + yaku_14 + yaku_15 + yaku_16 +
                            yaku_17 + yaku_18 + yaku_19 + yaku_20 + yaku_21);
                    text_score.setText(String.valueOf(myviewmodeel.Score));
                }
                else{
                    yaku_6 = "";
                }
            }
        });
        button_yaku_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag7++;
                if (flag7%2==1) {
                    myviewmodeel.Score++;
                    yaku_7="獨聽 ";
                }
                else
                {
                    myviewmodeel.Score--;
                    yaku_7="";
                }
                text_text.setText(yaku_1+yaku_2+yaku_3+yaku_4+yaku_5+yaku_6+yaku_7+yaku_8+
                        yaku_9+yaku_10+yaku_11+yaku_12+yaku_13+yaku_14+yaku_15+yaku_16+
                        yaku_17+yaku_18+yaku_19+yaku_20+yaku_21);
                text_score.setText(String.valueOf(myviewmodeel.Score));
            }
        });
        button_yaku_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag8++;
                if (flag8%2==1) {
                    myviewmodeel.Score++;
                    yaku_8="槓上開花 ";
                }
                else
                {
                    myviewmodeel.Score--;
                    yaku_8="";
                }
                text_text.setText(yaku_1+yaku_2+yaku_3+yaku_4+yaku_5+yaku_6+yaku_7+yaku_8+
                        yaku_9+yaku_10+yaku_11+yaku_12+yaku_13+yaku_14+yaku_15+yaku_16+
                        yaku_17+yaku_18+yaku_19+yaku_20+yaku_21);
                text_score.setText(String.valueOf(myviewmodeel.Score));
            }
        });
        button_yaku_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag9++;
                if (flag9%2==1) {
                    myviewmodeel.Score++;
                    yaku_9="海底撈月 ";
                }
                else
                {
                    myviewmodeel.Score--;
                    yaku_9="";
                }
                text_text.setText(yaku_1+yaku_2+yaku_3+yaku_4+yaku_5+yaku_6+yaku_7+yaku_8+
                        yaku_9+yaku_10+yaku_11+yaku_12+yaku_13+yaku_14+yaku_15+yaku_16+
                        yaku_17+yaku_18+yaku_19+yaku_20+yaku_21);
                text_score.setText(String.valueOf(myviewmodeel.Score));
            }
        });
        button_yaku_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag10++;
                if (flag10%2==1) {
                    myviewmodeel.Score++;
                    yaku_10="搶槓 ";
                }
                else
                {
                    myviewmodeel.Score--;
                    yaku_10="";
                }
                text_text.setText(yaku_1+yaku_2+yaku_3+yaku_4+yaku_5+yaku_6+yaku_7+yaku_8+
                        yaku_9+yaku_10+yaku_11+yaku_12+yaku_13+yaku_14+yaku_15+yaku_16+
                        yaku_17+yaku_18+yaku_19+yaku_20+yaku_21);
                text_score.setText(String.valueOf(myviewmodeel.Score));
            }
        });
        button_yaku_11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag11++;
                if (flag11%2==1) {
                    myviewmodeel.Score+=2;
                    yaku_11="花槓 ";
                }
                else
                {
                    myviewmodeel.Score-=2;
                    yaku_11="";
                }
                text_text.setText(yaku_1+yaku_2+yaku_3+yaku_4+yaku_5+yaku_6+yaku_7+yaku_8+
                        yaku_9+yaku_10+yaku_11+yaku_12+yaku_13+yaku_14+yaku_15+yaku_16+
                        yaku_17+yaku_18+yaku_19+yaku_20+yaku_21);
                text_score.setText(String.valueOf(myviewmodeel.Score));
            }
        });
        button_yaku_12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag12++;
                if (flag12%2==1) {
                    myviewmodeel.Score+=2;
                    yaku_12="全求人 ";
                }
                else
                {
                    myviewmodeel.Score-=2;
                    yaku_12="";
                }
                text_text.setText(yaku_1+yaku_2+yaku_3+yaku_4+yaku_5+yaku_6+yaku_7+yaku_8+
                        yaku_9+yaku_10+yaku_11+yaku_12+yaku_13+yaku_14+yaku_15+yaku_16+
                        yaku_17+yaku_18+yaku_19+yaku_20+yaku_21);
                text_score.setText(String.valueOf(myviewmodeel.Score));
            }
        });
        button_yaku_13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag13++;
                if (flag13%2==1) {
                    myviewmodeel.Score+=2;
                    yaku_13="平胡 ";
                }
                else
                {
                    myviewmodeel.Score-=2;
                    yaku_13="";
                }
                text_text.setText(yaku_1+yaku_2+yaku_3+yaku_4+yaku_5+yaku_6+yaku_7+yaku_8+
                        yaku_9+yaku_10+yaku_11+yaku_12+yaku_13+yaku_14+yaku_15+yaku_16+
                        yaku_17+yaku_18+yaku_19+yaku_20+yaku_21);
                text_score.setText(String.valueOf(myviewmodeel.Score));
            }
        });
        button_yaku_14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag14++;
                if (flag14%2==1) {
                    myviewmodeel.Score+=2;
                    yaku_14="三暗刻 ";
                }
                else
                {
                    myviewmodeel.Score-=2;
                    yaku_14="";
                }
                text_text.setText(yaku_1+yaku_2+yaku_3+yaku_4+yaku_5+yaku_6+yaku_7+yaku_8+
                        yaku_9+yaku_10+yaku_11+yaku_12+yaku_13+yaku_14+yaku_15+yaku_16+
                        yaku_17+yaku_18+yaku_19+yaku_20+yaku_21);
                text_score.setText(String.valueOf(myviewmodeel.Score));
            }
        });
        button_yaku_15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag15++;
                if (flag15%2==1) {
                    myviewmodeel.Score+=3;
                    yaku_15="門清一摸三 ";
                }
                else
                {
                    myviewmodeel.Score-=3;
                    yaku_15="";
                }
                text_text.setText(yaku_1+yaku_2+yaku_3+yaku_4+yaku_5+yaku_6+yaku_7+yaku_8+
                        yaku_9+yaku_10+yaku_11+yaku_12+yaku_13+yaku_14+yaku_15+yaku_16+
                        yaku_17+yaku_18+yaku_19+yaku_20+yaku_21);
                text_score.setText(String.valueOf(myviewmodeel.Score));
            }
        });
        button_yaku_16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag16++;
                if (flag16%2==1) {
                    myviewmodeel.Score+=4;
                    yaku_16="碰碰胡 ";
                }
                else
                {
                    myviewmodeel.Score-=4;
                    yaku_16="";
                }
                text_text.setText(yaku_1+yaku_2+yaku_3+yaku_4+yaku_5+yaku_6+yaku_7+yaku_8+
                        yaku_9+yaku_10+yaku_11+yaku_12+yaku_13+yaku_14+yaku_15+yaku_16+
                        yaku_17+yaku_18+yaku_19+yaku_20+yaku_21);
                text_score.setText(String.valueOf(myviewmodeel.Score));
            }
        });
        button_yaku_17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag17++;
                if (flag17%2==1) {
                    myviewmodeel.Score+=4;
                    yaku_17="混一色 ";
                }
                else
                {
                    myviewmodeel.Score-=4;
                    yaku_17="";
                }
                text_text.setText(yaku_1+yaku_2+yaku_3+yaku_4+yaku_5+yaku_6+yaku_7+yaku_8+
                        yaku_9+yaku_10+yaku_11+yaku_12+yaku_13+yaku_14+yaku_15+yaku_16+
                        yaku_17+yaku_18+yaku_19+yaku_20+yaku_21);
                text_score.setText(String.valueOf(myviewmodeel.Score));
            }
        });
        button_yaku_18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag18++;
                if (flag18%2==1) {
                    myviewmodeel.Score+=4;
                    yaku_18="小三元 ";
                }
                else
                {
                    myviewmodeel.Score-=4;
                    yaku_18="";
                }
                text_text.setText(yaku_1+yaku_2+yaku_3+yaku_4+yaku_5+yaku_6+yaku_7+yaku_8+
                        yaku_9+yaku_10+yaku_11+yaku_12+yaku_13+yaku_14+yaku_15+yaku_16+
                        yaku_17+yaku_18+yaku_19+yaku_20+yaku_21);
                text_score.setText(String.valueOf(myviewmodeel.Score));
            }
        });
        button_yaku_19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag19++;
                if (flag19%2==1) {
                    myviewmodeel.Score+=4;
                    yaku_19="地聽 ";
                }
                else
                {
                    myviewmodeel.Score-=4;
                    yaku_19="";
                }
                text_text.setText(yaku_1+yaku_2+yaku_3+yaku_4+yaku_5+yaku_6+yaku_7+yaku_8+
                        yaku_9+yaku_10+yaku_11+yaku_12+yaku_13+yaku_14+yaku_15+yaku_16+
                        yaku_17+yaku_18+yaku_19+yaku_20+yaku_21);
                text_score.setText(String.valueOf(myviewmodeel.Score));
            }
        });
        button_yaku_20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag20++;
                if (flag20%2==1) {
                    myviewmodeel.Score+=5;
                    yaku_20="四暗刻 ";
                }
                else
                {
                    myviewmodeel.Score-=5;
                    yaku_20="";
                }
                text_text.setText(yaku_1+yaku_2+yaku_3+yaku_4+yaku_5+yaku_6+yaku_7+yaku_8+
                        yaku_9+yaku_10+yaku_11+yaku_12+yaku_13+yaku_14+yaku_15+yaku_16+
                        yaku_17+yaku_18+yaku_19+yaku_20+yaku_21);
                text_score.setText(String.valueOf(myviewmodeel.Score));
            }
        });

        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myviewmodeel.Score=0;
                flag1=0;flag2=0;flag3=0;flag4=0;flag5=0;flag6=0;flag7=0;flag8=0;flag9=0;flag10=0;
                flag11=0;flag12=0;flag13=0;flag14=0;flag15=0;flag16=0;flag17=0;flag18=0;flag19=0;
                flag20=0;
                yaku_1="";yaku_2="";yaku_3="";yaku_4="";yaku_5="";yaku_6="";yaku_7="";yaku_8="";
                        yaku_9="";yaku_10="";yaku_11="";yaku_12="";yaku_13="";yaku_14="";yaku_15="";yaku_16="";
                        yaku_17="";yaku_18="";yaku_19="";yaku_20="";yaku_21="";
                text_text.setText(yaku_1+yaku_2+yaku_3+yaku_4+yaku_5+yaku_6+yaku_7+yaku_8+
                        yaku_9+yaku_10+yaku_11+yaku_12+yaku_13+yaku_14+yaku_15+yaku_16+
                        yaku_17+yaku_18+yaku_19+yaku_20+yaku_21);
                text_score.setText(String.valueOf(myviewmodeel.Score));
            }
        });
        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myviewmodeel.Score=0;
                flag1=0;flag2=0;flag3=0;flag4=0;flag5=0;flag6=0;flag7=0;flag8=0;flag9=0;flag10=0;
                flag11=0;flag12=0;flag13=0;flag14=0;flag15=0;flag16=0;flag17=0;flag18=0;flag19=0;
                flag20=0;
                yaku_1="";yaku_2="";yaku_3="";yaku_4="";yaku_5="";yaku_6="";yaku_7="";yaku_8="";
                yaku_9="";yaku_10="";yaku_11="";yaku_12="";yaku_13="";yaku_14="";yaku_15="";yaku_16="";
                yaku_17="";yaku_18="";yaku_19="";yaku_20="";yaku_21="";
                text_text.setText(yaku_1+yaku_2+yaku_3+yaku_4+yaku_5+yaku_6+yaku_7+yaku_8+
                        yaku_9+yaku_10+yaku_11+yaku_12+yaku_13+yaku_14+yaku_15+yaku_16+
                        yaku_17+yaku_18+yaku_19+yaku_20+yaku_21);
                text_score.setText(String.valueOf(myviewmodeel.Score));
            }
        });


    }
}