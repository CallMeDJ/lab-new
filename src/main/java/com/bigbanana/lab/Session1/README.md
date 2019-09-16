### 总纲

熟练掌握 Java 基础语法。时间:1周。

### 学习资料

<https://www.runoob.com/java/java-tutorial.html>

### 主要关注内容

变量

运算符

循环结构

条件结构

异常处理

数组

函数

### 作业

#### 目标：实现一个卖西瓜的摊摊函数

```java
public class SessionWatermelon {
	public static void main(String[] args) {
	}

	public static int[] sell(int[] buyNum){
		return null;
	}

	private static int sell0(int buyNum){
		return 0;
	}
}
```

逻辑：

1. 如果没顾客，那就不卖

2. 来了N个顾客，要按顺序卖，不卖的客户也要告诉他不卖（卖0）。

3. 单个卖的逻辑在  sell0 实现。

4. 如果超过50个，只卖50个。

5. 如果需求小于0个，sell0 抛出异常，在 sell 捕获异常并登记为不卖（卖0）。

6. 在控制台打印出最终这批卖出去多少个。术语：xxx总共卖出去N个。

7. 实现自己的打印数组函数。格式: `[1,2,3,4,5]`;























