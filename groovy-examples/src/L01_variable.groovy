/**
 * 类型变量学习
 */


/* =====================Map====================== */
//初始化一个数组
def map = [:]
//设置值
map = [name: 'Gromit', likes: 'cheese', id: 1234]
map += [age: 30] //追加
//在Groovy的key允许使用，像一个破折号，一个空格，一个感叹号括起来
map.'single quote' = "ALLOWED"
map."double quote"  = "ALLOWED"
map.'''triple single quote'''  = "ALLOWED"
map."""triple double quote"""  = "ALLOWED"
map./slashy string/  = "ALLOWED"
map.$/dollar slashy string/$  = "ALLOWED"
map."true"  = 1

//读取
assert map.get('name') == 'Gromit'
assert map.name == 'Gromit'
assert map./slashy string/  == "ALLOWED"
assert map."true"  == 1

//遍历
map.each { key, value ->
    println "Name: $key Age: $value"
}

/* =====================闭包====================== */
// 闭包就是一个独立的代码块，可以独立运行，也可以传入参数，也可以作为变量传给其他方法
// 参数 -> 执行块
def closure = { String x, int y ->
    println "hey ${x} the value is ${y}"
}
closure('x',1)

//闭包其实是Closure类的一个对象
def listener = { e -> println "Clicked on $e.source" }
assert listener instanceof Closure
Closure callback = { println 'Done!' }
Closure<Boolean> isTextFile = {
    File it -> it.name.endsWith('.txt')
}

//闭包的调用,闭包名+（）或者闭包名.call()来调用闭包
closure.call('x',1)

//闭包的参数，当闭包没有显式声明参数时，其默认包含一个隐式的参数it
def greeting = { "Hello, $it!" }
assert greeting('Patrick') == 'Hello, Patrick!'




