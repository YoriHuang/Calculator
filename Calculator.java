
/**
 * 业务层，实现计算功能
 * @version 1.0
 * @author admin
 */

public class Calculator{
	public double temp;  //存储当前要计算的数据
	public String mid;   //存储计算结果中间值
	public String midOperator;  //存储上一个运算符
	public String tempOperator; //存储当前运算符
	public void operation(String text,String operator){

		temp = Double.parseDouble(text);
		tempOperator = operator;
		//如果中间值为空，说明是第一个数据
		if(mid == null){
			mid = text;
			midOperator = operator;
		}else{
			//如果输入的是等号，那么将当前内存中存放的中间值与刚传递进来的数据通过上一个运算符进行运算,
			if(tempOperator.equals("=")){
				if(midOperator.equals("÷")){
					mid = String.valueOf((Double.parseDouble(mid))/temp);
				}else if(midOperator.equals("×")){
					mid = String.valueOf((Double.parseDouble(mid))*temp);
				}else if(midOperator.equals("-")){
					mid = String.valueOf((Double.parseDouble(mid))-temp);
				}else if(midOperator.equals("+")){
					mid = String.valueOf((Double.parseDouble(mid))+temp);
				}
			}else{
				//如果是加减乘除，那么将当前内存中存放的中间值与刚传递进来的数据通过上一个运算符进行运算，
				//并将当前运算符赋给上一个运算符
				if(midOperator.equals("÷")){
					mid = String.valueOf((Double.parseDouble(mid))/temp);
				}else if(midOperator.equals("×")){
					mid = String.valueOf((Double.parseDouble(mid))*temp);
				}else if(midOperator.equals("-")){
					mid = String.valueOf((Double.parseDouble(mid))-temp);
				}else if(midOperator.equals("+")){
					mid = String.valueOf((Double.parseDouble(mid))+temp);
				}
				midOperator = tempOperator;
			}			
		}		
	}

    //获得内存中存放的计算中间值，当点击等号是调用该方法，将中间值作为最终结果输出
	public String getMid(){
		return mid;
	}
	//将中间值置空，当点击等号后，说明用户已经完成一次运算，
	//则需要将中间值置空（即恢复初始值），为了下次计算重新开始
	public void setMid(){
		mid = null;
	}
}