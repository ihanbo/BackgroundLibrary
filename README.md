forked from JavaNoober/BackgroundLibrary
# BackgroudLibrary
A framework for directly generating shape through Tags, no need to write shape.xml again（通过标签直接生成shape，无需再写shape.xml）

#### 1.文本颜色selector
```
app:tx_color="#0000ff"
app:tx_color_pressed="@color/red"
app:tx_color_checked="@color/red"
app:tx_color_selected="@color/red"
```
效果等同于使用如下selector：
`android:textColor="@color/tx_color_selecotr"`
```
//tx_color_selecotr.xml
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:color="@color/red" android:state_pressed="true" />
    <item android:color="#0000ff" />
</selector>
```
- 适用于textview子类，button、checkbox等,其他view会忽略掉
- 会覆盖android:textColor选项。

#### 2.背景selector
```
app:bg_normal="#00ff00"
app:bg_pressed="@drawable/ic_launcher_foreground" 
app:bg_checked="@color/colorAccent"
app:bg_selected="@drawable/ic_launcher_foreground" 
```
效果等同于使用如下selector：
`android:background="@drawable/xx_selector"`
```
//xx_selector.xml
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:drawable="@color/colorAccent" android:state_pressed="true" />
    <item android:drawable="@drawable/ic_launcher_background" android:state_checked="true" />
    <item android:drawable="@drawable/ic_launcher_foreground" />
</selector>
```
- 会覆盖android:background属性。


#### 3.shape背景以及selector
```
 <TextView
        android:layout_width="130dp"
        android:layout_height="36dp"
        android:text="TextView"
        android:clickable="true"

        app:bgs_shape="rectangle"               //选填，默认矩形，还有椭圆和圆环

        app:bgs_corners_radius="4dp"            //矩形的圆角，可以设统一角度，也可以单独设左上、右下等，单独设优先级高
        app:bgs_corners_radius_lb="15dp"
        app:bgs_corners_radius_lt="5dp"
        app:bgs_corners_radius_rb="15dp"
        app:bgs_corners_radius_rt="5dp"

        app:bgs_solid_color="#E3B666"           //可以设solid颜色也可以设置渐变色，solid_color优先级高

        app:bgs_gradient_centerColor="#00ff00"  
        app:bgs_gradient_endColor="@color/colorPrimaryDark"
        app:bgs_gradient_startColor="@color/colorAccent"

        app:bgs_gradient_type="linear"         //渐变类型，选填，默认线性
      
      
        app:bgs_stroke_color="#8c6822"          //边线
        app:bgs_stroke_dashGap="4dp"            //虚线
        app:bgs_stroke_dashWidth="4dp"
        app:bgs_stroke_width="2dp"


        //selector按下的配置，selector只允许配置如下几个颜色，shape之类的不能更改，不然太复杂
        app:bgs_solid_color_pressed="#000000"   
        app:bgs_stroke_color_pressed="#000000"
        app:bgs_gradient_startColor_pressed="@color/colorPrimary"
        app:bgs_gradient_centerColor_pressed="#000000"
        app:bgs_gradient_endColor_pressed="#000000"

        //selector选中的配置包括checked和selected
        app:bgs_solid_color_cs="#000000"
        app:bgs_stroke_color_cs="#000000"
        app:bgs_gradient_startColor_cs="@color/colorPrimary"
        app:bgs_gradient_centerColor_cs="#000000"
        app:bgs_gradient_endColor_cs="#000000"



         />

```



- 2的优先级高于3，有配置2的话忽略3.


