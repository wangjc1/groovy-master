package trait

//运行时实现一个trait
trait Extra {
    String extra() { "I'm an extra method" }
}
class Something {
    String doSomething() { 'Something' }
}
def s = new Something() as Extra
s.extra()
s.doSomething()

//运行时实现多个trait
trait A { void methodFromA() {} }
trait B { void methodFromB() {} }
class C {}
def c = new C()
def d = c.withTraits A, B
d.methodFromA()
d.methodFromB()