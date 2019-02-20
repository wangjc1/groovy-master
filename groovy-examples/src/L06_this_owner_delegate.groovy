//this,owner,delegate
//this在闭包中的含义指的是，表示定义该闭包的类的实例对象（实例闭包）或者类本身（静态闭包）
//owner的含义基本上跟this的含义一样，只是除了一种情况，如果该闭包是在其他的闭包中定义的，那么owner是指向定义它的闭包对象
//delegate的含义大多数情况下是跟owner的含义一样，除非它被显示的修改（通过Closure.setDelegate()方法进行修改）。


/***********************this**********************/
//this指向的是包含闭包的最近的类
class EnclosedInInnerClass {
    class Inner {
        Closure cl = {
            this
        }                               //#5
    }
    void run() {
        def inner = new Inner()
        assert inner.cl() == inner                        //#6
    }
}
def inInnerClass = new EnclosedInInnerClass()
inInnerClass.run()

/**********************owner*********************/
//owner 只能位于闭包中，但不能在一个方法中，因为闭包也是一个类对象，也就是必须要有载体
def owner1 = {
    this
    owner
}
String owner2() {
    println this //L04_method@5e600dd5
    owner //error: No such property owner
}


/**********************delegate*********************/
//delegate和owner一样只能位于闭包中，不能在一个方法中
//delegate：默认情况下和owner一样，但是可以改变。
class PersonDele{
    String name
    int age
    String toString() { "$name is $age years old" }

    String dump() {
        def cl = {
            String msg = delegate.toString()               //#1
            msg
        }
        cl()
    }
}
def p = new PersonDele(name:'Janice', age:74)
p.dump()
assert p.dump() == 'Janice is 74 years old'

//静态方法中的delegate是类本身，类实例对象中的delegate是类对象
class StaticDele {
    static int num = 1
    def static add = {
        ++delegate.num
    }
}
assert StaticDele.add() == 2

//本来upperName闭包中没有name属性，但是通过把p对象赋值给delegate对象，就具有了p的name属性
def upperName = { delegate.name.toUpperCase() }
upperCasedName.delegate = p
assert upperCasedName() == 'JANICE'

//本来ageAdd闭包中没有age属性，但是通过把p对象赋值给delegate对象，就具有了p的age属性
def ageAdd = { ++delegate.age }
ageAdd.delegate = p
assert ageAdd() == 75
