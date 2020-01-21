# 1.字符串中的第一个唯一字符

#### 题目

给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。

**案例:**

```
s = "leetcode"
返回 0.

s = "loveleetcode",
返回 2.
```

 

**注意事项：**您可以假定该字符串只包含小写字母。

#### 我的代码

```java
    static int firstUniqChar(String s) {
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (count[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }
```

#### 我的理解

第一次写Java，有点迷，完全不会，发现虽然看了好多，但还有那么多自己不熟悉的东西。需要多看一看。

就这个程序而来，思想很简单，常见一个26的数组，每个元素对应一个字母，如果在字符串中遇到那个字母就给那个字母加一，最后直接通过直接访问字符串的每一个元素，判断那个元素先出现1次。从而得到结果。charAt()访问的是元素对应的char值，所以需要借用Char判断。

> count[s.charAt(i) - 'a']	这儿也应该注意。

#### 大佬的代码

```java
class Solution {
      public int firstUniqChar(String s) {
        int index = -1;
        //反过来，只有26个字符
        for (char ch = 'a'; ch <= 'z'; ch++) {
            int beginIndex = s.indexOf(ch);
            // 从头开始的位置是否等于结束位置，相等说明只有一个，
            if (beginIndex != -1 && beginIndex == s.lastIndexOf(ch)) {
                //取小的，越小代表越前。
                index = (index == -1 || index > beginIndex) ? beginIndex : index;
            }
        }
        return index;
    }
}
```

#### 我对大佬代码的理解

给一个标志（如果符合条件，就用标志把值带出），从a到z开始判断，把字符串的每一个字母位置循环给beginIndex，然后判断beginIndex和对应字母最后一次出现的地方是否相同。最后判断，index是不是-1（如果是则说明之前的判断有问题，或者第一次循环）或者已经拿到的位置是不是大于我们现在拿到的位置，如果大于就说明之前的位置有问题，需要重新更新。

---

# 2. 有效的字母异位词 

#### 题目：

给定两个字符串 *s* 和 *t* ，编写一个函数来判断 *t* 是否是 *s* 的字母异位词。

**示例 1:**

```
输入: s = "anagram", t = "nagaram"
输出: true
```

**示例 2:**

```
输入: s = "rat", t = "car"
输出: false
```

**说明:**
你可以假设字符串只包含小写字母。

**进阶:**
如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？

#### 我的代码

```java
    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length())
            return false;
        boolean Boolean = true;
        int[] a = new int[26];
        int[] b = new int[26];
        for(int i = 0;i < s.length();i ++){
            a[s.charAt(i) - 'a'] ++;
            b[t.charAt(i) - 'a'] ++;
        }
        for (int j = 0 ;j < 26; j ++)
        {
            if (a[j] == b[j])
                continue;
            else
                Boolean = false;
        }
        return Boolean;
    }

```

#### 我的理解

这个题呢，暴力解题，哈哈！首先判断两个字符串是否相等，不相等直接返回错误。创建2个int数组，分别统计s和t中每个字母出现的次数。再逐个比较，如果有不相同的，false。否则就输出TRUE。运行竟然5ms，真快！

#### 大佬的代码

```java
    public boolean isAnagram(String s, String t) {
        int[] cnt = new int[26];

        for (char c: s.toCharArray()){
            cnt[c - 'a']++;
        }

        for (char c: t.toCharArray()){
            cnt[c - 'a']--;
        }

        for (int i : cnt){
            if (i != 0)
                return false;
        }
        return true;
    }
```

#### 我对大佬代码的理解

创建一个int数组。for出字符串s每一个元素，给数组对应位置加一。然后for出字符串t每一个元素，给数组对应位置减一。最后判断数组中的每个元素是不是0。如果不是那么就false。

我觉得可以这样写：

```java 
        for(int i = 0;i < s.length();i ++){
            a[s.charAt(i) - 'a'] ++;
            a[t.charAt(i) - 'a'] --;
        }
		for(int i : a){
            if(i != 0)
                return false;
        }
```

---

# 3.验证回文字符串

#### 题目

给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。

**说明：**本题中，我们将空字符串定义为有效的回文串。

**示例 1:**

```
输入: "A man, a plan, a canal: Panama"
输出: true
```

**示例 2:**

```
输入: "race a car"
输出: false
```

#### 我的代码

写了半天，最后还是错误满天飞，我跪了！

```java
    public static boolean isPalindrome(String s) {
        if (s.length() == 0)
            return true;
        s = s.toLowerCase();
        int last = s.length()-1;
        int pre = 0;
        int i;
        for (i = 0;i < s.length() / 2;i ++) {
            char flag1 = '*';
            while((flag1 == '*') && (pre < (last-1))) {
                if((s.charAt(pre) >= 'a' && s.charAt(pre) <= 'z') || (s.charAt(pre) >= '0' && s.charAt(pre) <= '9')) {
                    flag1 = s.charAt(pre);
                    pre++;
                }
                else
                    pre++;
            }
            char flag2 = '*';
            while((flag2 == '*') && (last > 0)) {
                if((s.charAt(last) >= 'a' && s.charAt(last) <= 'z') || (s.charAt(last) >= '0' && s.charAt(last) <= '9') ) {
                    flag2 = s.charAt(last);
                    last--;
                }
                else
                    last--;
            }
            if (flag1 != flag2) {
                return false;
            }
        }
        if (i == s.length()/2)
            return true;
        return false;
    }
```

#### 我的理解

设置两个指针，一个从字符串头向后，另一个从字符串尾向前。排除非字母数字，然后对比，如果有一个不相等，那么就false。但是问题就出到这儿了，变态输入，如果全部不是字母或者数字。这样就会出现问题。啊！我太难了！！！

#### 我改的代码

```java
    public static boolean isPalindrome(String s) {
        if (s.length() == 0)
            return true;
        s = s.toLowerCase();
        StringBuilder t = new StringBuilder();
        for (int i = 0;i < s.length();i ++){
            if ((s.charAt(i) >= 'a' && s.charAt(i) <= 'z') ||(s.charAt(i) >= '0' && s.charAt(i) <= '9'))
                t.append(s.charAt(i));
        }
        if (t.length() == 0)
            return true;
        int pre = 0;
        int end = t.length() - 1;
        while (pre < end) {
            if (t.charAt(pre) == t.charAt(end)) {
                pre++;
                end--;
            }
            else
                return false;
        }
        return true;
    }
```

#### 我的理解

它首先利用StringBuilder的方法，直接把有效字符全部放入一个新的字符串中。然后再逐个比较。这个方法，真是好！

#### 大佬的代码

```java
public  boolean isPalindrome(String s) {
        if (s.length() <= 1)
            return true;
        int left = 0;
        int right = s.length() - 1;
        char[] chars = s.toCharArray();
        while (left < right) {
            //调整大写 A-Z 65-90 || a-z 97-122
            if ((int) chars[left] >= 65 && (int) chars[left] <= 90)
                chars[left] = (char) ((int) chars[left] + 32);
            if ((int) chars[right] >= 65 && (int) chars[right] <=90)
                chars[right] = (char) ((int) chars[right] + 32);
            //验证目标字符为数字或字母
            boolean flag1 = (chars[left] >= '0' && chars[left] <= '9') || (chars[left] >= 'a' && chars[left] <= 'z');
            boolean flag2 = (chars[right] >= '0' && chars[right] <= '9') || (chars[right] >= 'a' && chars[right] <= 'z');
            //正式比较
            if (flag1 && flag2 && chars[left] != chars[right]) {
                return false;
            }else if (!flag1){
                left++;
            }else if (!flag2){
                right--;
            }else {
                left++;
                right--;
            }
        }
        return true;
    }
```

#### 我对大佬代码的理解

get到一个知识点：把字符串转化成字符数组。

```java
char[] chars = s.toCharArray();
```

然后再进行操作。虽然时间用的短，但是代码有很多赘余。我觉得并不好。

---

# 4.字符串转换整数

#### 题目

请你来实现一个 `atoi` 函数，使其能将字符串转换成整数。

首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。

当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，作为该整数的正负号；假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。

该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。

注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换。

在任何情况下，若函数不能进行有效的转换时，请返回 0。

**说明：**

假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231, 231 − 1]。如果数值超过这个范围，请返回  INT_MAX (2^31 − 1) 或 INT_MIN (−2^31) 。

**示例 1:**

```
输入: "42"
输出: 42
```

**示例 2:**

```
输入: "   -42"
输出: -42
解释: 第一个非空白字符为 '-', 它是一个负号。
     我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。
```

**示例 3:**

```
输入: "4193 with words"
输出: 4193
解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。
```

**示例 4:**

```
输入: "words and 987"
输出: 0
解释: 第一个非空字符是 'w', 但它不是数字或正、负号。
     因此无法执行有效的转换。
```

**示例 5:**

```
输入: "-91283472332"
输出: -2147483648
解释: 数字 "-91283472332" 超过 32 位有符号整数范围。 
     因此返回 INT_MIN (−231) 。
```

#### 我的代码

```java
	public static int myAtoi(String str) {
    	int n = str.length();
    	//字符串的长度
    	int i = 0;
    	//用于索引字符串的指针
    	while(i < n && str.charAt(i) == ' ') {
    		i ++;
    	}
    	//跳过空格
    	if(i == n||!((str.charAt(i) >= '0' && str.charAt(i) <= '9' )|| str.charAt(i) == '+' || str.charAt(i) == '-'))
    		return 0;
    	//判断是不是已经到达了字符串的顶端，判断当前索引位置的元素是不是【0，9】，‘+’，‘-’。
    	StringBuilder s = new StringBuilder();
    	//构造一个StringBuilder 的字符串。（StringBuilder中有很方便的字符串操作方法）。
    	if(str.charAt(i) == '-') {
    		s.append('-');
    		i ++;
    		//判断当前索引位置的元素是不是‘-’，如果是直接将其append进新建的字符串中。
    	}else if (str.charAt(i) == '+') {
    		i ++;
    		//判断当前索引位置的元素是不是‘+’，如果是就进入下一个索引的位置（正数我们没有必要再在前边加一个‘+’）。
    	}
    	if(i == n||!(str.charAt(i) >= '0' && str.charAt(i) <= '9' ))
    		return 0;
    	//再判断一次，如果超过字符串长度，那么直接结束。如果不是【0，9】那么直接返回0.
    	while(i < n && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
    		s.append(str.charAt(i));
    		i ++;
    	}
    	System.out.println(s);
    	try {
    		return Integer.valueOf(s.toString());
    	}catch(Exception e){
    		if(s.substring(0,1).equals("-")) {
    			return Integer.MIN_VALUE;
    		}else {
    			return Integer.MAX_VALUE;
    		}
    	}
    	//try……catch……，判断异常，抛异常时直接进入。
    	//valueOf()返回保存指定的 String 的值的 Integer对象。(java的number方法)
    	//equals,对比字符，必须用""。不然，不然就会跟我一样，花好长时间。
    	//substring(beging,ending)，获取字符串的【beging，ending）的
    }
```

#### 我的理解

```
这个题就是需要考虑很多的条件，
1.判断是不是空格开头，
2.判断第一个非空是不是“+”或者“-”
3.判断第一个是不是非数字
4.判断得到的数是否大于INT_MAX，或者小于INT_MIN
5.判断第一个非空不是正负号，不是数字时的处理。

valueOf(String s):返回保存指定的 String 的值的 Integer 对象。(java的number方法)
Integer.toString(): 返回表示 Integer 值的 String 对象。
	string.toString();
```

这个程序真的很有趣，它完全时一种全新的。我们一般都会想着在一个循环中解决问题，但是这个就是利用一个变量来把这个问题给解决了。详细注释在程序中！

#### 大佬的代码

```java
	public int myAtoi(String str) {
		if (str.isEmpty())
			return 0;
		char[] mychar = str.toCharArray();
		long ans = 0;
		int index = 0, flag = 1, n = str.length();
		//排除字符串开头的空格元素
		while (index < n && mychar[index] == ' ') {
			index++;
		}
		//排除空格后判断首字符是+还是-还是都不是
		if (index < n && mychar[index] == '+') {
			index++;
		} else if (index < n && mychar[index] == '-') {
			index++;
			flag = -1;
		}
		//重点：只管是数字的时候，其余取0
		while (index < n && (mychar[index] >= '0' && mychar[index] <= '9')) {
			if (ans != (int) ans) {//超出int范围
				return (flag == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;//提前结束
			}
			ans = ans * 10 + mychar[index++] - '0';
		}

		if (ans != (int) ans) {
			return (flag == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
		}

		return (int) (ans * flag);

	}
```

#### 我对大佬代码的理解

首先判断字符串是不是为空，如果是空，返回0。然后把字符串转化成字符数组。判断非空（拿到非空的位置）。再判断这个元素是不是“+”或者“-”，按找其对应的方法处理。然后判断后边的元素是不是在【0，9】，再判断结果有没有超出int范围，没超过则处理数据。最终再判断一次有没有超出【0，9】，再判断结果有没有超出int范围。最终输出得到的值。过程都比较复杂。

通过这个算法，get到了这种循环处理方式。

---

# 9.回文数

#### 我的代码

```java
	public static boolean isPalindrome(int x) {
		if(x < 0)
			return false;
		String s = (x + "");
		int pre = 0;
		int end = s.length() - 1;
		int i = 0;
		while(i < s.length()/2) {
			if(s.charAt(pre) == s.charAt(end)) {
				pre ++;
				end --;
			}else {
				return false;
			}
			i ++;
		}
		return true;
	}
```

#### 我的理解

```
回文数：
	1.回文数肯定不是负数。
	2.将回文数转化为字符串，然后直接进行字符串操作就可以对比出来。
```

排除负数。直接将回文数转化为字符串，然后逐个判断字符串的每一位。

#### 大佬的代码

```java
	public boolean isPalindrome(int x) {        
        if(x < 0 ){
            return false;
        }
        int original = x;
        int revs = 0;
        while(x != 0 ){
            revs = revs * 10 + x % 10;
            x = x / 10;
        }
        return original == revs;
    }
```

#### 我对大佬代码的理解

直接将回文数反转，然后互相比较。即可！

---

# 14.最长公共前缀

#### 我的代码

```java
	public static String longestCommonPrefix(String[] strs) {
	    if(strs.length == 0) 
	    	return "";
	    int lengths = strs[0].length();
	    for (int p = 1;p < strs.length;p ++) {
	    	if(lengths <= strs[p].length()) {
	    		continue;
	    	}else {
	    		lengths = strs[p].length();
	    	}
	    }
	    if(lengths == 0)
	    	return "";
		int i;
	    StringBuilder s = new StringBuilder();
	    s.append("");
	    for (i = 0;i < lengths;i ++) {
	    	char a = strs[0].charAt(i);
	    	int j = 1;
	    	while (j < strs.length) {
	    		if( a == strs[j].charAt(i)) {
	    			j ++;
	    			continue;
	    		}
	    		else {
	    			return s + "";
	    		}
	    	}
	    	if (j == strs.length) {
	    		s.append(a);
	    	}
	    }
		return s + "";
	}
```

#### 我的理解

这个玩意怎么说呢？挺简单的，但是需要考虑的东西挺多的。首先判断这个字符串是不是为空，如果为空直接返回“”；然后再判断得到字符数组中字符串的最短长度，创建一个StringBuilder字符串，然后再进行循环，判断字符串相同位置的字符是不是相同，如果相同继续比较下一个，如果不同返回String字符串。（在把所有字符串循环完之后，如果还没有退出，那么就append当前字符。）

```
将StringBuilder转化为String:
StringBuilder s = new StringBuilder();
String t = s + "";(这就是一个字符串。)
```

#### 大佬的代码

```java
    public String longestCommonPrefix(String[] strs) {
        if(strs.length==0){
            return "";
            //若字符串数组长度为0，直接返回“”；
        }
        String res=strs[0];
        //把字符串数组的第一个元素给res
        for(int i=1;i<strs.length;i++){
            //从字符串数组的1——>strs.length-1循环
            while(strs[i].indexOf(res)!=0){
                // 判断这个res字符串在不在strs[i]中
                res=res.substring(0,res.length()-1);
                // 
                if(res.length()==0){
                    return "";
                }
            }
        }
        return res;
    }
```

#### 我对大佬代码的理解

```
indexOf(String str): 返回指定字符在字符串中第一次出现处的索引，如果此字符串中没有这样的字符，则返回 -1。
substring() 方法返回字符串的子字符串。
```

有点尴尬，我看不懂！

---

# 53.最大子序数

#### 我的代码

```java
    public static int maxSubArray(int[] nums) {
        
    	/*
    	 * 这个题怎么说呢，他就是需要判断我们之前所选择的是不是最优的解，如果找到更好的，那么就直接更新，如果不是那么还是直接用之前的继续操作
    	 */
    	
    	int sum = 0;
        int ans = nums[0];
        //定义了两个变量用来保存我们所要获取的值
        for (int num:nums) {
        	//遍历nums数组
        	if ( sum > 0 ) {
        		//判断我们当前所拿到的和是不是大于0；如果不是大于0，说明我们的之前的操作并不是最优的，所以从现在重新开始，如果大于0，那么直接就继续进行加处理
        		sum += num;
        	}else {
        		sum = num;
        	}
        	ans = Math.max(ans, sum);
        	//判断这次拿到的值跟上次的值相比，把最大的值给我们所要输出的结果。
        }
        return ans;
    }
```

#### 我的理解

题目要求直接从整数数组中找到一个具有最大和的连续子数组（子数组最少柏寒一个元素），返回其最大和。这个题的最大难点，就是计算连序子数组的最大值。

其实可以用贪心，遇到数都加，拿到最大的值，但是从哪儿开始加才会拿到最优的呢？（问题就在这儿）。

看了看就是直接从第一个开始处理，每次处理之前都判断之前的是不是合理的，如果不太合理的合理的话直接从这儿开始重新处理，知到拿到最优的解。

#### 大佬的代码

```java
    public int maxSubArray(int[] nums) {
        int sum= 0;
        int res = Integer.MIN_VALUE;
        //初始化两个变量，首先将一个值赋int型的最小值
        for (int i = 0;i < nums.length;i++) {
            //循环nums中的所有值，
            if (sum + nums[i] > nums[i]) {
                //判断之前所有的和再加上当前nums的值，判断这个值和当前值的大小，如果有增长的趋势，那么就直接相加，如果没有，那么就把当前的值给sum。（这么想，如果之前所有的处理过程的值都没有当前一个值大，那么就直接将我们的值给它。）
                sum += nums[i];
            } else {
                sum = nums[i];
            }
            //res这个值就是我们所要输出的值。这值就是判断之前的res和sum那个大（因为前边的处理可能会改变我们sum值，也就是最终的结果）
            res = Math.max(res,sum);
        }
        return res;
    }
```

#### 我对大佬代码的理解

这个题真挺难理解的。主要就是什么时刻覆盖之前处理的值。具体注释在代码中。

---

# 121.买股票的最佳时机

#### 我的代码

```java
    public static int maxProfit(int[] prices) {
    	if ( prices.length == 0 )
    		return 0;
        int min = prices[0];
        int max = prices[0];
        int ans = 0;
        for (int i = 1; i < prices.length; i++) {
        	if (prices[i] < min) {
        		min = prices[i];
        		max = prices[i];
        	}else if(prices[i] > max){
        		max =  prices[i];        		
        	}
        	ans = (ans > (max -min)? ans :(max -min));
		}
        return ans;
    }
```

#### 我的理解

这个问题与之前《买股票的最佳时机2》不一样，《 买股票的最佳时机2 》中没有限制买入卖出次数要求，只要能够得到最大利润即可（也就是可以使用贪婪原则）。

当前问题：限制了买入卖出次数，所以需要找到最大利润区间（神仙视角）。我们可以将找出最低价和最高价，这样就可以拿到这个区间，但怎么控制最低价和最高价是个问题。

晃荡了半天终于写出来了。先定义三个变量，min,max,ans。遍历数组的没一位，如果当前元素小于最小值，那么就更新当前min,max。如果大于最大值那么就更新max。最后判断当前得到的值（max-min）是不是最大的，如果是最大的就更新，不是则不更新。（我以为会测试出错呢。竟然没有！哈哈哈）。

怎么说呢，它是对的，（debug一次就明白了！哈哈！（ 执行用时 :1 ms, 在所有 java 提交中击败了99.98%的用户 ））。

#### 大佬的代码

```java
    public int maxProfit(int prices[]) {
        int minprice = Integer.MAX_VALUE;
        //最低价格
        int maxprofit = 0;
        //最大利益
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minprice)
            //判读当前元素的价格是不是小于最低价格（如果是则更新）。
                //最低价格就只有遇到最低价时更新。
                minprice = prices[i];
            else if (prices[i] - minprice > maxprofit)
            //判断当前收益与原来利益的关系，若当前收益大于原来的收益，那么就更新。
                maxprofit = prices[i] - minprice;
        }
        return maxprofit;
    }
```

#### 我对大佬代码的理解

这个比我的好理解多了，注释在代码中！

---

# 67.二进制求和

#### 我的代码

```java
    public static String addBinary(String a, String b) {
        StringBuilder ans = new StringBuilder();
        int aLength = a.length() - 1;
        int bLength = b.length() - 1;
        int n = Math.max(a.length(), b.length());
        int p,q;
        int flag = 0;
        for (int i = 0;i< n;i++) {
        	if(aLength >= 0) {
        		p = (int)a.charAt(aLength) - 48;
        		aLength --;
        	}else {
        		p = 0;
        	}
        	if(bLength >= 0) {
        		q = (int)b.charAt(bLength) - 48;
        		bLength --;
        	}else {
        		q = 0;
        	}
        	int sum = p + q;
        	if(sum + flag == 2) {      
        		ans.append(0);
        		flag = 1;
        	}else if(sum + flag == 3) {
        		ans.append(1);
        		flag = 1;
        	}else if(sum + flag == 1){
        		ans.append(1); 
        		System.out.println(sum);
        		flag = 0;
        	}else if(sum + flag == 0) {
        		ans.append(0);
        		flag = 0;
        	}
        }
        if(flag == 1)
        	ans.append(1);
        return ans.reverse() + "";
    }
```

#### 我的理解

经过修改终于通过了。 执行用时 :20 ms, 在所有 java 提交中击败了5.06%的用户 。 内存消耗 :35.9 MB, 在所有 java 提交中击败了55.84%的用户 。

但是这个效率有点太低了呀！但是遇到的问题都很好！

```
从字符串中charAt的字符int强制转型后还是ASCLL码，所以在以后的使用中需要注意(int)charAt(i)的使用
StringBuilder它的存储形式。
强制转型后，p = (int)a.charAt(aLength) - 48;我们需要拿到准确的数字，不然会产生额外的错误。
```

我的理解它很简单，从后往前两个字符串相加，然后利用flag标志变量，保存进位的标志。（但是实现的时候太难！）。

#### 大佬的代码

```java
    public String addBinary(String a, String b) {
        int aLen = a.length();
        int bLen = b.length();
        //拿到字符串的长度
        int length = aLen;
        if (bLen > aLen) {
            length = bLen;
        }
        //拿到最长字符串的长度
        char[] aChars = a.toCharArray();
        char[] bChars = b.toCharArray();
		//将字符串转化成char型数组。
        int j = aLen - 1;
        int k = bLen - 1;
		//循环条件判断的值。
        char l = '0';//进位
        char[] result = new char[length];
        //创建了一个新的char数组
        int m = length - 1;
        while (j >= 0 || k >= 0) {
			//保证字符串中的每个元素都能访问到。
            char aVal = j >= 0 ? aChars[j--] : '0';
            char bVal = k >= 0 ? bChars[k--] : '0';
			//这儿牛逼，直接将我写了好多行的代码直接就两行解决了。
            char val;
            if ('1' == aVal && '1' == bVal) {
                val = '0';
                if ('1' == l) {
                    val = '1';
                } else {
                    l = '1';
                }
            } else if ('0' == aVal && '0' == bVal) {
                val = '0';
                if ('1' == l) {
                    val = '1';
                }
                l = '0';
            } else {
                val = '1';
                if ('1' == l) {
                    val = '0';
                }
            }
            //上面的if—else处理的进位的问题。
            result[m--] = val;
            //进位的处理
        }

        if(l != '1') return new String(result);
        else return "1" + new String(result);
        //这儿处理了最后一位的进位
    }
```

#### 我对大佬代码的理解

这个呢，它优化了好多细节，大致处理过程与我的一样，但是处理方法特别好！详细注释在代码中！

---

# 88.合并两个有序数组

#### 我的代码

```java
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m;
        int j = n + m;
        int flag = 0;
        while (i < j) {
			nums1[i] = nums2[flag];
			i ++;
			flag ++;
		}
        Arrays.sort(nums1);
    }
```

#### 我的理解

这个问题比较简单（相当简单），题目中已经把我们要用到的数据全部提供了，只需要先赋值，然后直接sort排序即可。但是实际算法确实有点慢！

#### 大佬的代码

```java
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (n == 0) return;
        //判断第二个字符串的长度，若为0，直接退出。
        int cur_index = 0, index2 = 0, index = 0;
        int[] nums = new int[m];
        //创建了一个新的数组
        for (int i = 0; i < m; i++) {
            nums[i] = nums1[i];
            //把num1中有效的值全部放入新数组nums中。
        }
        while (index < m && index2 < n) {
            //下面的就是给排序（相当于用nums&nums2给nums1中重新输入）。
            if (nums[index] <= nums2[index2]) {
                nums1[cur_index++] = nums[index++];
            } else {
                nums1[cur_index++] = nums2[index2++];
            }
        }
        //排序结束后如果数组还没有循环完，那就直接将这个数组中剩余的元素全部送入nums中。但是下面的这两中情况只能出现一次。
        while (index < m) {
            nums1[cur_index++] = nums[index++];
        }
        while (index2 < n) {
            nums1[cur_index++] = nums2[index2++];
        }
    }
```

#### 我对大佬代码的理解

它这个理解起来还是挺难的，理解了还是挺难的。我那个中用了sort，但是sort是冒泡排序，所以需要很长时间。虽然时间长，但是使用方便，在大量数据的时候还是得择优排序！！！

---

# 198.打家劫舍

#### 我的代码

```java
    public static int robx(int[] nums) {
        int sum1 = 0;
        int sum2 = 0;
        int i = 0;
        while(i < nums.length) {
        	if(i % 2 == 0) {
                //奇偶判断
        		sum1 += nums[i];
        		sum1 = sum1 > sum2 ? sum1 : sum2;
                //将奇和、偶和比较，拿到最大的值。
        	}else {
        		sum2 += nums[i];        		
        		sum2 = sum1 > sum2 ? sum1 : sum2;
				//将奇和、偶和比较，拿到最大的值。
            }
        	i ++;
        }
        return i = sum1 > sum2 ? sum1 : sum2;
        //最后再判断一次。保证拿到最大值（因为最后一次循环我们不能确认那个是最大的！）。
    }
```

#### 我的理解

这个题呢，我刚开始想的太简单了，以为只是单纯的奇偶相加，但是却是只要满足不是相邻的，这个就比较难判断，这是最大的问题。我想了半天还是没有解决办法，只好去看解析，解析是动态规划，但是动态规划却看不懂，我太难了！

最终找到了一个比较简单的，容易理解的，但我还是有点没有看懂！（挺难理解的）！能理解的注释在代码中！

#### 大佬的代码

```java
    public int rob(int[] nums) {
        int pre =0, cur = 0;
        //两个变量
        for(int x : nums){
            int tem = cur;
            //设置一个中间变量，
            cur = Math.max(pre+x,cur);
            //比较当前值和前边第二个值，
            pre = tem;
           	//将之前第一个的值直接给现在第二个值，因为需要不断交换，所以需要一个中间变量，将数据加工交换
        }

        return cur;
    }
```

#### 我对大佬代码的理解

真看不懂，他应该是将动态规划用的最简单的那一种！看完动态规划之后再更新！

昨天看了动态规划，今天再看了看差不多明白了。已经更新了注释！

---

# 263.丑数

#### 我的代码

```java
    public static boolean isUgly(int num) {
        if(num == 1) {
        	return true;
        }else if(num == 0) {
        	return true;
        }
        //排除两个特征值的问题（1和0）
        while(num != 1) {
            //只要num的值不为1，那么就直接循环，因为只要正常循环，那个答案num最终会编程1.
        	if(num % 2 == 0) {
                //num取余2是不是0.
        		num /= 2;
        		continue;
        	}else if(num % 3 == 0) {
                //num取余3是不是0.
        		num /= 3;
        		continue;
        	}else if(num % 5 == 0) {
                //num取余5是不是0.
        		num /= 5;
                //num除去相应值得结果。
        		continue;
        	}else {
        		return false;
        	}
        }
        return true;
    }
```

#### 我的理解

这个就是直接将num处理，判断最终结果是不是可以整除2、3、5。难得就是这个循环过程，我把这个循环过程没有想明白！

#### 大佬的代码

```java
    public boolean isUgly(int num) {
        if(num<=0){
            return false;
        }
        //排除num是0的问题。
        //判断是否只包含这三种因子
        while((num&1)==0){
            num>>=1;
        }
        //利用>>来解决除以2的问题
        while(num%3==0){
            num/=3;
        }//判断3||5的问题
        while(num%5==0){
            num/=5;
        }
        return num==1;
        //判断num == 1的情况。
    }
```

#### 我对大佬代码的理解

这个就是将他们分类处理，但是这个我还是不太理解。感觉还是我之前写的简单！

---

# 258.各位相加

#### 我的代码

```java
    public static int addDigits(int num) {
        if (num < 10) {
        	return num;
        }
        /*
         * 98
		 * 9 + 8 = 17
		 * 1 + 7 = 8
		 *
         * 38
		 * 3 + 8 = 11
		 * 1 + 1 = 2
		 */
        //个位数时直接退出循环，返回原来的数。
        while (num > 9) {
            //保证我们最终的结果小于10，也就是只有一位
        	int n = 0;
            //设置一个局部变量，用来保存这一次得到的值，最后把它给num
			while (num > 0) {
                //保证一次循环最终 num > 0，也就是将它退出。
					n = num % 10 + n;
					num = num / 10;
			}
			num = n;
		}
        return num;
    }
```

#### 我的理解

设置两个循环，按照我们所想的那样循环。在写这类循环的时候一定要分清主次。

#### 大佬的代码

```java
    public int addDigits(int num) {
        return (num-1)%9+1;
    }
```

#### 我对大佬代码的理解

这个操作太秀了！这个过程真不好想，关键是我太菜。检验了好多，是正确的，但是原理是什么，真的不了解！

```
思路
时间复杂度为O(1)的解法：

除个位外，每一位上的值都是通过(9+1)进位的过程得到的，想一下拨算盘进位
把整数n看成n样物品，原本是以10个1份打包的，现在从这些10个1份打包好的里面，拿出1个，让它们以9个为1份打包。
这样就出现了两部分的东西：
·原本10个现在9个1份的，打包好的物品，这些，我们不用管
·零散的物品，它们还可以分成：
·从原来打包的里面拿出来的物品，它们的总和 =》 原来打包好的份数 =》 10进制进位的次数 =》 10进制下，除个位外其他位上的值的总和
·以10个为1份打包时，打不进去的零散物品 =》 10进制个位上的值
·如上零散物品的总数，就是第一次处理num后得到的累加值
·如果这个累加值>9，那么如题就还需要将各个位上的值再相加，直到结果为个位数为止。也就意味着还需要来一遍如上的过程。
·那么按照如上的思路，似乎可以通过n % 9得到最后的值
·但是有1个关键的问题，如果num是9的倍数，那么就不适用上述逻辑。原本我是想得到n被打包成10个1份的份数+打不进10个1份的散落个数的和。通过与9取模，去获得那个不能整除的1，作为计算份数的方式，但是如果可以被9整除，我就无法得到那个1，也得不到个位上的数。
 ·所以需要做一下特殊处理，(num - 1) % 9 + 1
可以这么做的原因：原本可以被完美分成9个为一份的n样物品，我故意去掉一个，那么就又可以回到上述逻辑中去得到我要的n被打包成10个一份的份数+打不进10个一份的散落个数的和。而这个减去的1就相当于从，在10个1份打包的时候散落的个数中借走的，本来就不影响原来10个1份打包的份数，先拿走再放回来，都只影响散落的个数，所以没有关系。
```

---

# 70.爬楼梯

#### 我的代码

```java
    public static int climbStairs(int n) {
        int pre = 1;
        int last = 2;
        if (n == 0) {
        	return 0;
        }else if(n == 1) {
        	return 1;
        }
        int i = 3;
        while (i <= n) {
        	int tmp = last;
        	last = pre + last;
        	pre = tmp;
        	i ++;
        }
        return last;
    }
```

#### 我的理解

每增加一层台阶，方案数是斐波那契数列，所以直接使用动态规划处理，不适用递归的方法！

#### 大佬的代码

```java
    public int climbStairs(int n) {
        if(n == 0 || n == 1){
            return 1;
        }
        int preOneStep = 1;
        int preTwoStep = 1;
        int result = 0;
        for(int i = 2; i <= n ;i++){
            result = preTwoStep + preOneStep;
            preOneStep = preTwoStep;
            preTwoStep = result;
            //动态规划的步骤
        }
        return result;
    }
```

#### 我对大佬代码的理解

方法还是一样的，找到发展的规律，然后使用动态规划。

---

# 268.缺少数字

#### 我的代码

```java
    public static int missingNumber(int[] nums) {
    	Arrays.sort(nums);
    	int i;
    	for (i = 0; i < nums.length; i++) {
			if(nums[i] != i)
				return i;
		}
    	return i;
    }
```

#### 我的理解

直接sort，再进行排序，然后与下标对比就行了。但是sort花费的时间太多。

#### 大佬的代码

```java
    public int missingNumber(int[] nums) {
       int sum = 0;
       for(int num:nums) sum+=num;
       int N = nums.length;
       int expactSum = (1+N)*N/2;
       return expactSum - sum;
    }
```

#### 我对大佬代码的理解

把下标与值分别求和，然后相减即可求出答案！