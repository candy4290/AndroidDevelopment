package cxx.cxsw.com.cxx7;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
/*
* 不允许在其他线程中修改ＵＩ控件属性
* 不允许在主线程中执行耗时操作
* */
public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) this.findViewById(R.id.textView);
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                String s = (String) msg.obj;
                textView.setText(s);
            }
        };
    }

    public void change(View view) {
        new MyTask().start();
    }

    class MyTask extends Thread{
        public void run(){
            try {
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String result = "hello";
            Message msg = new Message();
            msg.obj = result;
            handler.sendMessage(msg);
        }
    }
}
