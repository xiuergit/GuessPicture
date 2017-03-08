package com.briup.guesspicture;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridLayout.Spec;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.briup.bean.*;
import com.briup.view.*;
public class MainActivity extends 
Activity implements OnClickListener {
	//玩家信息
	int score;//当前金币个数
	String level;//等级称号
	
	//当前题号
	int index;
    //问题集合  存放所有的问题
    ArrayList<Answer>answerObjects;
    
    //UI View
    ImageView icon;//图片	
	TextView nameView;//问题
    TextView numView;//题号
    TextView scoreView;//分数
    Button  levelView;//等级
    
	//答案
	LinearLayout answerView;
	//答案选择
    GridLayout answerOptionView;
    
    //初始化页面
	void init(){
		score=100;//金币：100；
		level="初来乍到";//刚开始
		answerObjects=(ArrayList<Answer>) Resouce.answers();
		icon=(ImageView)findViewById(R.id.ivPicture);
		nameView=(TextView)findViewById(R.id.tvName);
		numView=(TextView)findViewById(R.id.tvNumber);
		scoreView=(TextView)findViewById(R.id.tvScore);
		levelView=(Button)findViewById(R.id.btLevel);
		answerView=(LinearLayout)findViewById(R.id.answerView);
		answerOptionView=(GridLayout)findViewById(R.id.answerOptionsView);
	
	}
	/**每次挑到下一题时、整个UI界面重新加载参数
	* @param answer :当前题目
	*/
	void initLayoutValues(Answer answer){
		int num=index+1;
		
		levelView.setText(level);
		numView.setText(num+"/10");
		nameView.setText(answer.getTitle());
	    icon.setImageResource(answer.getIcon());  
		scoreView.setText("金币:"+score); 
	     
		//添加子控件给answerview 与answerOptionView  重新换选择答案
	     addviewForAnswer(answer);
		 addviewForOptios(answer);
	}
	
    /**
     * //添加子控件给答案视图  (answer  当前题目   通过当前题目中的 答案的长度  添加子控件的个数)
     * @param answer 当前问题
     */
	void addviewForAnswer(Answer answer){
    	 AnswerButton   btAnswer;
		 LinearLayout answerLayout=(LinearLayout)findViewById(R.id.answerView);
		 if(answerLayout.getChildCount()>0){
			 answerLayout.removeAllViewsInLayout();
		 }
		 for(int i=0;i<answer.getAnswer().length();i++){
			btAnswer=new AnswerButton(this);
			int width=20;
			//设置该选项是答案按钮
			btAnswer.setIsAnswer(true);
			btAnswer.setHeight(width);
			btAnswer.setWidth(width);
			answerLayout.addView(btAnswer, i);
            btAnswer.setOnClickListener(this);
		}
    	 
     }
   /**
    * //添加子控件给选项视图
    * @param answer 当前题目
    */
     void addviewForOptios(Answer answer){
    	 //Button btOptions;
    	 AnswerButton btOptions;
    	 GridLayout optionsLayout=(GridLayout)findViewById(R.id.answerOptionsView);
    	 
    	 if(optionsLayout.getChildCount()>0){
			 optionsLayout.removeAllViewsInLayout();
		 }
    	 for(int i=0;i<answer.getViewAnswer().length;i++){
    		 int row=i%3;
    		 int col=i%7;
    		 GridLayout.Spec rowspec=GridLayout.spec(row);
    		 GridLayout.Spec colspec=GridLayout.spec(col);
    	      GridLayout.LayoutParams params=new GridLayout.LayoutParams(rowspec,colspec );
    		//获取屏幕的宽
    	      DisplayMetrics metrics=new 
    				 DisplayMetrics();
			 getWindowManager().getDefaultDisplay().getMetrics(metrics);
		   int width =metrics.widthPixels/7;
    	         params.width=width;
     		 params.height=width;
    	      btOptions=new AnswerButton(this);
    	     
    	     btOptions.setLayoutParams(params);
    	     //设置该选项不是答案
    	     btOptions.setIsAnswer(false);
    		 btOptions.setText(answer.getViewAnswer()[i]);
    		 optionsLayout.addView(btOptions, i);
    		  btOptions.setOnClickListener(this);
    	 }
    	 
     }
      
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//初始化布局
		init();
		//第一次加载显示的数据
		initLayoutValues(answerObjects.get(index));
	}
	
	
	/**
	 * //1.点击答案按钮  （修改答案）
	 * @param v :用户点击的按钮
	 */
	public void answer(AnswerButton v){
	     //1.1点击的是哪个 拿到值
		  String answers=v.getText().toString();
		  
		 //1.2.从供选答案中找到放回去；
		  if(!answers.isEmpty()){
			  v.setText("");
		     revokeAnswer(answers);
		  }
		
		
	}
	/**
	 * 撤销一个答案
	 * @param answer 撤销的字符串
	 */
	void revokeAnswer(String answer){
		 for(int i=0;i<answerOptionView.getChildCount();i++){
			   AnswerButton options=(AnswerButton)answerOptionView.getChildAt(i);
			      //找到后
			   if(answer.equals(options.getText().toString())){	   
				   //撤销的答案显示
				   options.setVisibility(View.VISIBLE);
			   } }
		
	}
	
	
	/**
	 *  设置答案(找到第一个空白的地方放进去，一次放一个)
	 * @param answer 用户选中的答案
	 */
	void chooseAnswer(String answer){
		for(int i=0;i<answerView.getChildCount();i++){
			   AnswerButton  answerbt=(AnswerButton)  answerView.getChildAt(i);
			     if(answerbt.getText().toString().isEmpty()){
			    	 answerbt.setText(answer);
			    	 break;
			     }
		 }
	}
	
	/**
	 * 判断答案是否输入完成
	 * @return isfull  true  完成  false 没有完成 
	 */
	Boolean isfull(){
		 boolean isfull = true;
    	 for(int i=0;i<answerView.getChildCount();i++){
     AnswerButton answer= (AnswerButton)answerView.getChildAt(i);
    		 if(answer.getText().toString().isEmpty()){
    			System.out.println("答案没有输入完成");
    			 isfull=false;		 
                break;
    		 }
    		 }
        return isfull;
    
    	 
	}
	/**
	 * 判断答案是否正确
	 * @param anser
	 * @return
	 */
	Boolean isCorrect(String answer){
		
		//正确的答案
	    String answerStandard=answerObjects.get(index).getAnswer();
		if(answer.endsWith(answerStandard)){
			
			return true;
		}
		else {
			return false;
		}
		
	}
	
	/**
	 * 
	 * @return 用户答题完毕－－》答案
	 */
	String answerSelect(){
		 StringBuffer answerUser=new StringBuffer();
		 for(int i=0;i<answerView.getChildCount();i++){
			 AnswerButton answer= (AnswerButton)answerView.getChildAt(i);
			 answerUser.append(answer.getText().toString());
		 }
		 return answerUser.toString();
		
	}
	
	
	/**
	 *  //2.点击选项按钮	
	 * @param v  用户点击的按钮
	 */
	public void option(AnswerButton v){
		//2.1 不可操作 
		v.setVisibility(View.INVISIBLE);
		 String answers=v.getText().toString();//当前选中的答案
		 //找到第一个答案空的位置放上去
		 chooseAnswer(answers);//2.2 设置答案(找到第一个空白的地方放进去，一次放一个)
		  //答案输入完成
		   if(isfull()){
		    		  
		    	 
			   //2.3判断答案是否正确
		               System.out.println("答案输入完成");
		    		      
		    	 if (isCorrect(answerSelect())){
		    		    	//正确
		    		    	Toast.makeText(this, "答题成功", Toast.LENGTH_SHORT).show();
		    		    	if(index==9){
			    				 new AlertDialog.Builder(this).setTitle("猜图游戏").
			 		    	  	setMessage("恭喜恭喜，通关了").setPositiveButton("下次再会",null).show(); 
			    			 }else{
			    			 //答对了。。。。
			    				 score=score+10;
			    	  	new AlertDialog.Builder(this).setTitle("猜图游戏").
			    	  	      setMessage("恭喜恭喜，您答对了；\n目前得分："+score).setPositiveButton("进入下一题",new DialogInterface.OnClickListener() {
			
							@Override
							public void onClick(DialogInterface dialog, int which) {
								

								next();
							}
						}).show();}
			    		
		    		    	
		    		    }
		    		    else{
		    		    	//不正确
		    		    	 if(index==9){
		    		    		 new AlertDialog.Builder(this).setTitle("猜图游戏").
		    			    	  	setMessage("很遗憾，答错了，是否重头开始？").
		    			    	  	setPositiveButton("否",null).setNegativeButton("是",
		    			    	  			new  DialogInterface.OnClickListener() {
		    							
		    		           		@Override
		    							public void onClick(DialogInterface dialog, int which) {
		    								// TODO Auto-generated method stub
		    								index=-1;
		    								next();
		    							}
		    						}).show();
			    				 
			    			  }
			    			  else{
			    				  new AlertDialog.Builder(this).setTitle("猜图游戏").
			    		    	  	setMessage("很遗憾，答错了，是否重新选择？").
			    		    	  	setPositiveButton("是",null).setNegativeButton("否",
			    		    	  			new  DialogInterface.OnClickListener() {
			    						
			    						@Override
			    						public void onClick(DialogInterface dialog, int which) {
			    							// TODO Auto-generated method stub
			    							next();
			    							score=100;
			    						}
			    					}).show();
			    				 
			    				  
			    			  }
		    		    	
		    		    }
		    		 
		    		 
		    	 }
	}
	
	//禁用所有控件的点击事件
	public void cancelClick(ViewGroup group){
		  for(int i=0;i<group.getChildCount();i++){
			     AnswerButton answer=(AnswerButton)group.getChildAt(i);
			    
			    answer.setEnabled(false);
			    //  answer.setVisibility(View.VISIBLE);
			    
			    
			    
			    
			   
		  }
		
	}
	
	@Override
	public void onClick(View v) {
		
		AnswerButton answer=(AnswerButton)v;
		   //点击的是答案button  否则   点击对应的选项又有相应的操作
		if(answer.getIsAnswer()){
			 System.out.println(answer.getIsAnswer());
			 
			 //1.点击答案按钮
			   answer(answer);
	    
		}else{
			 System.out.println(answer.getIsAnswer());
			 //2.点击选项按钮
			 option(answer);
			 
			 
			 
			  			 
			 
			 
			    
		}
		 
		
		
	}
	
	
	public void next(){
		index++;
		if(score>150){
			level="游学四方";
		}
		initLayoutValues(answerObjects.get(index));	
	}
   //下一个
    public void level(View v){
    	
    	
		new AlertDialog.Builder(this).setMessage("当前处在第"+(index+1)+"关").
		setTitle(level).setPositiveButton(" 继续", null).show();
		
	}
    //提示
    public void prompt(View v){
    	
    	new AlertDialog.Builder(this).setMessage("确定花50金币获取提示？").
		setTitle("求助太没面子了").setPositiveButton(" 是", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				if(score>=50){
					score=score-50;
					
					for(int i=0;i<answerView.getChildCount();i++){
						AnswerButton answer=(AnswerButton) answerView.getChildAt(i);
						if(answer.getText().toString().isEmpty()){
							char[]answers=answerObjects.get(index).getAnswer().toCharArray();
							System.out.println(answers[i]);
							answer.setText(answers[i]+"");
							scoreView.setText("金币："+score);;
							break;
						}
						
					}
					
				}
				else{
					
					Toast.makeText(MainActivity.this, "金币不足50，请自行解决吧", Toast.LENGTH_SHORT).show();
					
				}
				
			}
		}).show();
    	// Toast.makeText(this, "提示", Toast.LENGTH_SHORT).show();
    	
    }
    //分享帮助
    public void help(View v){
    	 Toast.makeText(this, "帮助", Toast.LENGTH_SHORT).show();
    	
    } //大图
    public void bigpicture(View v){
    	Toast.makeText(this, "大图", Toast.LENGTH_SHORT).show();
    	Intent intent=new Intent(this,ImageActivity.class);
       
    	 Bundle data=new Bundle();
    	 //Bitmap bitmap＝(BitmapDrawable)icon.getDrawable();
    	// BitmapFactory.decodeResource(getResources(), )
    	 data.putInt("image", answerObjects.get(index).getIcon());
    	 intent.putExtra("data", data);
    	startActivity(intent);


   	
   }
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	
}
