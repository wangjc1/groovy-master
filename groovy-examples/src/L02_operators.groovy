import java.util.regex.Matcher

/**
 * Groovy 操作
 * http://www.groovy-lang.org/operators.html
 */

//三元操作符
def string = null
// 直接用string ? 就可以判断string是否为空
def result = string ? 'Found' : 'Not found'
assert result == 'Not found'

//艾维斯运算符,不存在返回'Anonymous'，存在返回string
string = 'wang'
result = string ?: 'Anonymous'
assert result == 'wang'

//字符串也能做减法运算，屌爆啦
def msg = 'HelloWorld'
assert (msg-'Hello') == 'World'

//空对象安全引用运算符
def person = Person.find { it.name == 'wang' }
def name = person?.name
assert name == null

//无需定义get方法，通过.@调用
class User {
    String name
    User(String name) { this.name = name}
}
def user = new User('Bob')
assert user.name == 'Bob'
assert user.@name == 'Bob'

//方法指针.&,用这个指针很容易实现策略模式
def str = 'example of method reference'
def fun = str.&toUpperCase
def upper = fun()
assert upper == str.toUpperCase()

//正值表达式
def text = "some text to match"
def m = text =~ /match/
assert m instanceof Matcher
assert m.find() == true

//展开运算符（*.）
class Car {
    String make
    String model
}
def cars = [
        new Car(make: 'Peugeot', model: '508'),
        new Car(make: 'Renault', model: 'Clio')]
def makes = cars*.make
assert makes == ['Peugeot', 'Renault']

//解包方法参数
int function(int x, int y, int z) {
    x*y+z
}
def args = [4,5,6] //只取和方法数量相等的前几个参数
assert function(*args) == 26

//范围操作符
def range = 0..5
assert (0..5).collect() == [0, 1, 2, 3, 4, 5]  //collect方法转换成List

//as是类型转换符,还有is在groovy中用来判断对象地址是否相等
Integer x = 123
String s = x as String

//操作符重载
class Bucket {
    int size

    Bucket(int size) { this.size = size }

    Bucket plus(Bucket other) {
        return new Bucket(this.size + other.size)
    }
}
//方法 plus() 重载了 + 操作符, 只要重写plus方法，就能重载+符
def b1 = new Bucket(4)
def b2 = new Bucket(11)
assert (b1 + b2).size == 15