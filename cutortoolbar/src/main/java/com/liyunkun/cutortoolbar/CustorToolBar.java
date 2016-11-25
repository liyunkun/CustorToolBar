package com.liyunkun.cutortoolbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by liyunkun on 2016/9/26 0026.
 */
public class CustorToolBar extends RelativeLayout{

    private Drawable leftImg;//接收用户输入左边的图片
    private Drawable RightImg;//接收用户输入右边的图片
    private String titleText;//接收用户设置文本的内容
    private int titleColor;//接收用户设置文本的颜色
    private int titleSize;//接收用户设置文本的颜色
    private ImageView leftIv;//放置用户左边图片
    private ImageView rightIv;//放置用户右边图片
    private TextView titleTv;//放置用户输入的文本信息


    public CustorToolBar(Context context) {
        this(context,null);
    }

    public CustorToolBar(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustorToolBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //获取TypedArray，通过context.obtainStyledAttributes方法
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CustorToolBar);

        //通过TypedArray获取用户设置属性leftImage传来的图片
        leftImg=ta.getDrawable(R.styleable.CustorToolBar_leftImage);
        //给ImageView初始化
        leftIv=new ImageView(context);
        //给ImageView设置图片源
        leftIv.setImageDrawable(leftImg);
        //给ImageView设置内边距（注意单位的换算）
        leftIv.setPadding((int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,10,getResources().getDisplayMetrics()),
                (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,10,getResources().getDisplayMetrics()),
                (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,10,getResources().getDisplayMetrics()),
                (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,10,getResources().getDisplayMetrics()));

        //通过TypedArray获取用户设置属性rightImage传来的图片
        RightImg=ta.getDrawable(R.styleable.CustorToolBar_rightImage);
        //给ImageView初始化
        rightIv=new ImageView(context);
        //给ImageView设置图片源
        rightIv.setImageDrawable(RightImg);
        //给ImageView设置内边距（注意单位的换算）
        rightIv.setPadding((int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,10,getResources().getDisplayMetrics()),
                (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,10,getResources().getDisplayMetrics()),
                (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,10,getResources().getDisplayMetrics()),
                (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,10,getResources().getDisplayMetrics()));

        //通过TypedArray获取用户设置属性titleColor传来的文本颜色
        titleColor=ta.getColor(R.styleable.CustorToolBar_titleColor, Color.GREEN);
        //通过TypedArray获取用户设置属性titleSize传来的文本大小（第二个参数为默认值）
        titleSize=ta.getDimensionPixelSize(R.styleable.CustorToolBar_titleSize,16);
        //通过TypedArray获取用户设置属性titleText传来的文本内容
        titleText=ta.getString(R.styleable.CustorToolBar_titleText);
        //给TextView初始化
        titleTv=new TextView(context);
        //根据用户传来的数据设置TextView属性
        titleTv.setText(titleText);
        titleTv.setTextColor(titleColor);
        titleTv.setTextSize(TypedValue.COMPLEX_UNIT_PX,titleSize);

        //加载布局文件，注意为RelativeLayout的LayoutParams
        LayoutParams leftLp=new LayoutParams((int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,48,getResources().getDisplayMetrics()),
                (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,48,getResources().getDisplayMetrics()));
        //添加规则（即RelativeLayout中的十六个属性）
        //设置图片位于父容器的左边
        leftLp.addRule(ALIGN_PARENT_LEFT,TRUE);
        //添加控件到容器中
        addView(leftIv,leftLp);

        //加载布局文件，注意为RelativeLayout的LayoutParams
        LayoutParams rightLp=new LayoutParams((int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,48,getResources().getDisplayMetrics()),
                (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,48,getResources().getDisplayMetrics()));
        //添加规则（即RelativeLayout中的十六个属性）
        //设置图片位于父容器的右边
        rightLp.addRule(ALIGN_PARENT_RIGHT,TRUE);
        //添加控件到容器中
        addView(rightIv,rightLp);

        //加载布局文件，注意为RelativeLayout的LayoutParams
        LayoutParams titleLp=new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        //添加规则（即RelativeLayout中的十六个属性）
        //设置图片位于父容器的中间
        titleLp.addRule(CENTER_IN_PARENT,TRUE);
        //添加控件到容器中
        addView(titleTv,titleLp);

        //TypedArray的回收
        ta.recycle();

        //设置左边图片的监听
        leftIv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(imgClick!=null){
                    //接口回调
                    imgClick.onLeftImgClick();
                }
            }
        });
        //设置右边图片的监听
        rightIv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(imgClick!=null){
                    //接口回调
                    imgClick.onRightImgClick();
                }
            }
        });

    }
    //定义一个内部接口
    public interface IOnImgClick{
        void onLeftImgClick();//左边图片的方法
        void onRightImgClick();//右边图片的方法
    }
    //定义接口的属性
    private IOnImgClick imgClick;

    //给接口的属性赋值
    public void setImgClick(IOnImgClick imgClick) {
        this.imgClick = imgClick;
    }

}
