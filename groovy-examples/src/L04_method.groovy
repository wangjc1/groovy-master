/**
 * 方法，定义方法用def 关键字，可以知道返回类型也可不指定，不指定返回类型会根据返回值推断出来
 * 方法返回可以省略return关键字，方法有缺省参数 it
 *
 *  *  groovy  改良派
 *  *  scala 革命派
 *  * https://www.jianshu.com/p/ba55dc163dfd
 *  * http://www.groovy-lang.org/objectorientation.html
 */

//方法定义
//Method with no return type declared and no parameter
def someMethod() { 'method called' }
//Method with explicit return type and no parameter
String anotherMethod() { 'another method called' }
//Method with a parameter with no type defined
def thirdMethod(param1) { "$param1 passed" }
//Static method with a String parameter
static String fourthMethod(String param1) { "$param1 passed" }
assert anotherMethod() == 'another method called'

//命名参数
def foo(Map args) { "${args.name}: ${args.age}" }
foo(name: 'Marie', age: 1)

//缺省参数
def foo(String par1, Integer par2 = 1) { [name: par1, age: par2] }
assert foo('Marie').age == 1

//变长参数
def foo(Object... args) { args.length }
assert foo() == 0
assert foo(1) == 1
assert foo(1, 2) == 2

