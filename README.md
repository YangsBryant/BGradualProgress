# BGradualProgress
渐变进度条
![这是一张图片](https://github.com/YangsBryant/BGradualProgress/blob/master/gifhome_320x693_6s.gif)

## 引入module
```java
allprojects {
    repositories {
        google()
        jcenter()
        maven { url 'https://www.jitpack.io' }
    }
}
```
```java
implementation 'com.github.YangsBryant:BGradualProgress:1.0.3'
```

## BGradualProgress控件属性
方法名 | 属性
--------- | -------------
app:grp_roundColor | 环形的背景颜色
app:grp_roundWidth | 环形的宽带
app:grp_progressFillet | 进度条是否用圆角画笔
app:grp_max | 环形的最大值
app:grp_text | 中间的文字内容
app:grp_textColor | 中间的文字颜色
app:grp_textSize | 中间文件的字体大小
app:grp_textShow | 是否显示文字
app:grp_startAngle | 进度初始点的绘制位置
app:grp_startColor | 渐变的起始色
app:grp_midColor | 渐变的中间色
app:grp_endColor | 渐变的终止色

## BGradualProgress动态属性
方法名 | 属性
--------- | -------------
setMax(int max) | 设置进度的最大值
getProgress() | 获取当前进度值
setProgress(int progress) | 设置当前进度
setText(String text | 更新文本内容

## 布局控件

```java
    <com.bryant.progresslibrary.BGradualProgress
        android:id="@+id/progress"
        android:layout_width="72dp"
        android:layout_height="72dp"
        app:grp_endColor="#00000000"
        app:grp_midColor="#5289F5"
        app:grp_startColor="#dce7fd"
        app:grp_max="100"
        app:grp_roundColor="#dce7fd"
        app:grp_roundWidth="6dp"
        app:grp_startAngle="-90"
        app:grp_textColor="#5289F5"
        app:grp_textSize="16sp"
        app:layout_constraintTop_toTopOf="parent"
        android:layout_marginTop="50dp"
        app:layout_constraintLeft_toLeftOf="parent"
        android:layout_marginStart="30dp"
        />
```
## GIF图里的代码上面的demo有
## 联系QQ：961606042
