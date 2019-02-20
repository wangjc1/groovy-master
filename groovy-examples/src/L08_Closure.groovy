
//闭包缺省参数
def adder={x,y=5->return x+y}
assert adder(4,3) == 7
assert adder.call(2) == 7

//动态闭包参数，声明一个任意来下的参数
//如果一个函数只包含一个参数，并且参数是闭包类型，那么调用函数时，闭包可以写到最外面，如： method(){} == method({})
def doSomething(closure){
    if(closure){
        closure()
    }else {
        println 'using default implementation!'
    }
}
doSomething({-> println('input a closure param!')})
doSomething(){ -> println('input a dynamic closure param!')} //()也可以省略掉

//闭包缓存
fib = { long n -> n<2?n:fib(n-1)+fib(n-2) }.memoize()
assert fib(25) == 75025

//闭包的柯里化，减少传递参数数量，通过多次调用来返回结果，F(x,y)=F(x)(y)
//通过carry来传递一个参数
def fun={x,y->return x+y}
def funCurry=fun.curry(1)
assert funCurry(6) == 7

