package com.example.ranqianhao.badass;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button showPicBtn;
    int image[] = new int[]{
            R.drawable.ic_launcher_foreground,
            R.drawable.b ,
            R.drawable.c ,
            R.drawable.d ,
            R.drawable.ic_launcher_background ,
    };
    int count = 0;
    static Canvas canvas;

    static Bitmap drawableToBitmap(Drawable drawable){

        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE?Bitmap.Config.ARGB_8888:Bitmap.Config.RGB_565;
        Bitmap bitmap = Bitmap.createBitmap(width,height,config);
        drawable.draw(canvas);
        return bitmap;
    }
    static Drawable zoomDrawable(Drawable drawable, int w, int h)
    {
        int width = drawable.getIntrinsicWidth();
        int height= drawable.getIntrinsicHeight();
        Bitmap oldbmp = drawableToBitmap(drawable); // drawable 转换成 bitmap
        Matrix matrix = new Matrix();   // 创建操作图片用的 Matrix 对象
        float scaleWidth = ((float)w / width);   // 计算缩放比例
        float scaleHeight = ((float)h / height);
        matrix.postScale(scaleWidth, scaleHeight);         // 设置缩放比例
        Bitmap newbmp = Bitmap.createBitmap(oldbmp, 0, 0, width, height, matrix, true);       // 建立新的 bitmap ，其内容是对原 bitmap 的缩放后的图
        return new BitmapDrawable(newbmp);       // 把 bitmap 转换成 drawable 并返回
    }



    protected void sizePic(){
        for (int i = 0;i<5;i++) {
            Drawable drawable = getResources().getDrawable(image[i]);
            drawable.setBounds(0, 50, 80, 80);  //设置图片参数
            i++;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ImageView imageView = (ImageView) findViewById(R.id.BadAssPic);
        imageView.setImageResource(image[0]);
        showPicBtn = (Button) findViewById(R.id.changeToBadAssBtn);
        showPicBtn.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(getApplicationContext(),"你选择了成为BadAss",Toast.LENGTH_SHORT).show();
                imageView.setImageResource(image[count++]);

            }
         });

    }
}