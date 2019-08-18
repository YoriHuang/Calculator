
/**
 * 测试层，完成框架和组件的创建，布局和事件的处理
 * @version 1.0
 * @author admin
 */

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class CalculatorTest extends Frame implements ActionListener{

	Calculator c = new Calculator();  //创建业务层对象
	
	boolean flag = true;  //设置一个标志位，来判断用户是否点击了等号来结束一次运算
	String text = null;  //给文本框设值的变量

	TextField tf1 = new TextField();
	
	Font f=new Font("宋体",Font.BOLD,16);   //设置按钮的字体

	//添加菜单
	MenuBar bar = new MenuBar();
	Menu m1 = new Menu("文件");
	Menu m2 = new Menu("编辑");
	Menu m3 = new Menu("窗口");
	Menu m4 = new Menu("帮助");
	MenuItem mi1 = new MenuItem("打开");
	MenuItem mi2 = new MenuItem("关闭");
	MenuItem mi3 = new MenuItem("保存并退出");
	MenuItem mi4 = new MenuItem("退出");


	//添加按钮
	Button btn0 = new Button("0");
	Button btn1 = new Button("1");
	Button btn2 = new Button("2");
	Button btn3 = new Button("3");
	Button btn4 = new Button("4");
	Button btn5 = new Button("5");
	Button btn6 = new Button("6");
	Button btn7 = new Button("7");
	Button btn8 = new Button("8");
	Button btn9 = new Button("9");
	Button btnpoint = new Button(".");
	
	Button btnplus = new Button("+");
	Button btnminus = new Button("-");
	Button btnmulti = new Button("×");
	Button btndivide = new Button("÷");
	Button btnequals = new Button("=");
	

	Panel p1 = new Panel();

    //添加面板，采用网格布局，将按钮都添加到面板中
	public void below(){
	
		p1.setLayout(new GridLayout(4,4));

		p1.add(btn7);
		p1.add(btn8);
		p1.add(btn9);
		p1.add(btndivide);
		
		p1.add(btn4);
		p1.add(btn5);
		p1.add(btn6);
		p1.add(btnmulti);
		
		p1.add(btn1);
		p1.add(btn2);
		p1.add(btn3);
		p1.add(btnminus);
		
		p1.add(btn0);
		p1.add(btnpoint);
		p1.add(btnequals);
		p1.add(btnplus);
	
		add(p1);
	}

	//按钮触发的事件
	public void actionPerformed(ActionEvent e){
		Button btn = (Button)e.getSource();  //获取事件源
    	String lb = btn.getLabel();  //获取事件源的标签值
		
		//如果标志位为true，说明用户本次运算还没有结束
		if(flag == true){
			//如果用户点击的是0-9或.中的字符，则将其与当前文本框中的文本拼接起来生成新的字符串并将其显示在文本域中
			if(lb.matches("[0-9\\\\.]{1}")){
				text = tf1.getText() + lb;
				tf1.setText(text);
			}else if(lb != "="){
			//如果用户点击的是加减乘除之一的运算符，则调用业务层的函数，
			//将文本域中的值和当前点击的运算符作为参数传递过去
				c.operation(tf1.getText(),lb);
				tf1.setText("");
			}else{
			//如果用户点击的是等号，说明本次计算结束，
			//则调用业务层的函数来获取内存中存放的中间值作为最终结果输出到文本域中,将标志位改为false
				c.operation(tf1.getText(),lb);
				tf1.setText(c.getMid());
				c.setMid();
				flag = false;
			}
		}else{
	        //如果标志位为false，说明是新的一次运算开始，把按钮对应数字输入文本域之后，将标志位设置回true
			if(lb.matches("[\\d+]")){
				tf1.setText(lb);
				flag = true;
			}
		}
		
    }

	public CalculatorTest(String title){
		//设置框架标题，为按钮添加监听器
		super(title);
		//设置菜单
		m1.add(mi1);
		m1.add(mi2);
		m1.addSeparator();
		m1.add(mi3);
		m1.add(mi4);
		bar.add(m1);
		bar.add(m2);
		bar.add(m3);
		bar.setHelpMenu(m4);
		this.setMenuBar(bar);

		btn0.setFont(f);
		btn0.addActionListener(this);	
		btn1.setFont(f);
		btn1.addActionListener(this);
		btn2.setFont(f);
		btn2.addActionListener(this);
		btn3.setFont(f);
		btn3.addActionListener(this);
		btn4.setFont(f);
		btn4.addActionListener(this);
		btn5.setFont(f);
		btn5.addActionListener(this);
		btn6.setFont(f);
		btn6.addActionListener(this);
		btn7.setFont(f);
		btn7.addActionListener(this);
		btn8.setFont(f);
		btn8.addActionListener(this);
		btn9.setFont(f);
		btn9.addActionListener(this);
		btnpoint.setFont(f);
		btnpoint.addActionListener(this);
		btnplus.setFont(f);
		btnplus.addActionListener(this);
		btnminus.setFont(f);
		btnminus.addActionListener(this);
		btnmulti.setFont(f);
		btnmulti.addActionListener(this);
		btndivide.setFont(f);
		btndivide.addActionListener(this);	
		btnequals.setFont(f);
		btnequals.addActionListener(this);

		//将文本域和按钮面板按照边界布局添加到框架中
		add(tf1, BorderLayout.NORTH);
		add(p1, BorderLayout.SOUTH);
		
		//为框架添加windows事件，用来关闭窗口
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
	}

	public static void main(String[] args){
		CalculatorTest ct = new CalculatorTest("计算器");
		ct.below();
		ct.setSize(250,300);
		ct.show();
	}
}
