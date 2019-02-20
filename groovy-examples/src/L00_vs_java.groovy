/**
 *  和java比较不同
 *  http://www.groovy-lang.org/differences.html
 */

/* ===================字符串定义不同=================== */
//在java中使用""来定义字符串，使用''来定义单个字符
//在groovy中可以使用'',"",''' '''来定义一个字符串
def a = 'wang' //和java中""一样
def b = "Hello ${a}" //里面可以包含其他变量，使用${}
//按内容原格式输出字符串
def c = '''
line1...
line2...
'''

/* ===================groovy是动态语言=================== */
//java在编译时就确定了参数的类型，而groovy是在运行时才确定类型的
//所以下面两个方法在java和groovy中调用会出现不同的结果
int method(String arg) {
    return 1;
}
int method(Object arg) {
    return 2;
}
Object o = "Object";
int result = method(o);
//assertEquals(2, result); //java
//assertEquals(1, result); //groovy

/* =====================数组初始化不同====================== */
//java中数组初始化
//int[] array = { 1, 2, 3}

//groovy数组初始化，不能用{},因为这个在groovy中用来定义闭包的
int[] array = [1,2,3]


/* =====================比ARM更强大的闭包====================== */
//循环打印文件内容，it是groovy中的内置变量
new File('d:\\server.log').eachLine('UTF-8') {
    println it
}
//或使用 参数->函数体 的形式来定制化打印文件内容
new File('d:\\server.log').withReader('UTF-8') { reader ->
    reader.eachLine {
        println it
    }
}
/* =====================在匿名类上使用和java基本相同====================== */
class A {
    static class B {}
}
new A.B()

public class Y {
    public class X {}
    public X foo() {
        return new X();
    }
    public static X createX(Y y) {
        return new X(y) //groovy,在groovy中支持一个参数的调用，即使这个方法没有任何参数
        //return y.new X(); //java,不能这样调用在groovy中
    }
}

/* =====================groovy中基本类型都使用包装类型====================== */
void m(long l) {
    println "in m(long)"
}
void m(Integer i) {
    println "in m(Integer)"
}
int i
m(i)
//Java call m():in m(long)
//Groovy call m():in m(Integer)

/* =====================groovy中==符号和java不同====================== */
//Groovy使用“==”符号代替了equals方法，来提供Groovy语言编程的敏捷性。
//Groovy中使用 a.is(b)来判断a和b的内存地址是否一样
def abc1 = 'abc'
def abc2 = "abc"
println abc1 == abc2

def list1 = [1]
def list2 = [1]
println (list1==list2)+","+list1.is(list2)

/* ===================== <<符号 ====================== */
//<<符号在java中是左移符号，但在groovy中除了左移，还有更多功能

//1. 给列表添加值，类似add方法
def list = ['a','b','c']
list << 'd'
assert list == ['a','b','c','d']

def set = ['a','b','c'] as Set
set  << 'd'

//2. 相当于StringBuffer的append方法
def sb = new StringBuffer()
sb << 'line'

//3. 是使用“<<”操作符代替了write方法。
def osw= new OutputStreamWriter(new FileOutputStream('abc.txt'))
osw<< 'abc'
osw.close()

//4. 运行时给类添加方法，使用<<把闭包赋值给方法名
Person.metaClass.toUpper << { -> delegate.name.toUpperCase() }
def p = new Person(name:'Json')
assert p.toUpper() == 'JSON'

/* ===================== 编译检查 ====================== */
//因为groovy是动态语言，所以错误在运行时才检查，要想编译检查，添加@TypeChecked注解
//@TypeChecked
class TypeCheck {
    def pp(String m){
        m.toUpperCase()
        m.toUppercase() //拼写错误
    }
}
