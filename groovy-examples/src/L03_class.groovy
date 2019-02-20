import groovy.transform.PackageScope
/**
 * 类和对象
 * http://www.groovy-lang.org/objectorientation.html
 */

//定义以类
class Person {
    @PackageScope String privateName //groovy中默认定义的变量都是公有的，要定义包私有变量使用@PackageScope
    String name
    Integer age
    def increaseAge(Integer years) {
        this.age += years
    }
}

//实例化一个类
def p = new Person()

//内部类
class Outer {
    private String privateStr

    def callInnerMethod() {
        new Inner().methodA()
    }

    class Inner {
        def methodA() {
            println "${privateStr}."
        }
    }
}

//内部类实现了一个接口
class Outer2 {
    private String privateStr = 'some string'

    def startThread() {
        new Thread(new Inner2()).start()
    }

    class Inner2 implements Runnable {
        void run() {
            println "${privateStr}."
        }
    }
}

//匿名内部类
class Outer3 {
    private String privateStr = 'some string'
    def startThread() {
        new Thread(new Runnable() {
            void run() {
                println "${privateStr}."
            }
        }).start()
    }
}

//抽象类
abstract class Abstract {
    String name

    abstract def abstractMethod()

    def concreteMethod() {
        println 'concrete'
    }
}

//构造方法,调用方式和java类似
class PersonConstructor {
    String name
    Integer age

    PersonConstructor(name, age) {
        this.name = name
        this.age = age
    }
}
def person1 = new PersonConstructor('Marie', 1)
def person2 = ['Marie', 2] as PersonConstructor
PersonConstructor person3 = ['Marie', 3]
assert person1 instanceof  PersonConstructor
assert person3.name == 'Marie'

//构造方法，可以通过参数名来调用，无需构造方法
class PersonWOConstructor {
    String name
    Integer age
}
def person4 = new PersonWOConstructor()
def person5 = new PersonWOConstructor(name: 'Marie')
def person6 = new PersonWOConstructor(age: 1)
def person7 = new PersonWOConstructor(name: 'Marie', age: 2)
assert person7.name == 'Marie'

//groovy自动会生成getName，getAge方法
class Person8 {
    //creates a backing private String name field, a getName and a setName method
    String name
    //creates a backing private int age field, a getAge and a setAge method
    int age
}
def p8 = new Person8()
p8.name = 'wang'
assert p8.getName() == 'wang'

//final申明的变量，是只读属性
class Person9 {
    //If a property is declared final, no setter is generated:
    final String name
    final int age
    Person9(String name, int age) {
        this.name = name
        this.age = age
    }
}
def p9 = new Person9('wang',25)
//p9.name = 'zhang' //name is readonly

//定义额外的方法来访问属性
class Person10 {
    String name
    void name(String name) {
        this.name = "Wonder$name"
    }
    String wonder() {
        this.name
    }
}
def p10 = new Person10()
p10.name = 'Marge'
assert p10.name == 'Marge'
p10.name('Marge')
assert p10.wonder() == 'WonderMarge'

//上面提到把Map传给一个方法，自动匹配方法的参数
//同理一个类的属性也可以转换成一个Map集合，通过操作Map集合来遍历类的属性
class Person11 {
    String name
    int age
}
def p11 = new Person11()
assert p11.properties.keySet().containsAll(['name','age'])

//即使没定义属性，只有存在属性的get，set方法，照样可以操作属性
class Person12 {
    // a pseudo property "name"
    void setName(String name) {}
    String getName() {}

    // a pseudo read-only property "age"
    int getAge() { 42 }

    // a pseudo write-only property "groovy"
    void setGroovy(boolean groovy) {  }
}
def p12 = new Person12()
p12.name = 'Foo'
assert p12.age == 42
p12.groovy = true

//TODO,疑问，type:Zip背后的逻辑是怎样的
/*task createDistribution(type: Zip) {
    //AbstractCopyTask.from(),相当于调用 Zip.from
    from war.outputs.files
}*/

//具名参数
class Robot {
    def type,height,width
    def access(location,weight,flag){
        println "receive data :($flag? weight:$weight:loc:$location)"
    }
}
class Zip {
    def war
}
//def robot = new Robot(type:Zip,width: 10,height: 20)
//println "$robot.type,$robot.height,$robot.width"
//robot.access(x:100,y:20,50,true)

def robotc = {}
robotc(type:Zip,width: 10,height: 20){
}

