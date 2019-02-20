import groovy.transform.AnnotationCollector

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.reflect.Modifier
/**
 * groovy中注解和java差不多，基本延用java那套，只不过添加了以下闭包的功能
 */

/***********************一个完整例子******************/

//1. 定义一个OnlyIf注解
@Retention(RetentionPolicy.RUNTIME)
@interface OnlyIf {
    Class value()
}

//2. 定义Tasks类使用OnlyIf注解，<<相当于add方法
class Tasks {
    Set result = []
    void alwaysExecuted() {
        result << 1
    }
    @OnlyIf({ jdk>=6 })
    void supportedOnlyInJDK6() {
        result << 'JDK 6'
    }
    @OnlyIf({ jdk>=7 && windows })
    void requiresJDK7AndWindows() {
        result << 'JDK 7 Windows'
    }
}

//3. 定义一个Runner类解析OnlyIf注解
class Runner {
    static <T> T run(Class<T> taskClass) {
        def tasks = taskClass.newInstance()
        def params = [jdk:6, windows: false]
        tasks.class.declaredMethods.each { m ->
            if (Modifier.isPublic(m.modifiers) && m.parameterTypes.length == 0) {
                def onlyIf = m.getAnnotation(OnlyIf)
                if (onlyIf) {
                    //构建闭包实例
                    Closure cl = onlyIf.value().newInstance(tasks,tasks)
                    //从赋值给delegate中的params取值，将params参数传入闭包对象中
                    cl.delegate = params
                    //调用闭包方法，如何符合条件则调用相应的方法，这里符合第一个和第二个方法，所以返回集合就是 [1, 'JDK 6']
                    if (cl()) {
                        m.invoke(tasks)
                    }
                } else {
                    m.invoke(tasks)
                }
            }
        }
        tasks
    }
}

//4. 测试
def tasks = Runner.run(Tasks)
assert tasks.result == [1, 'JDK 6'] as Set

/**********************整合多个注解到一个***************************/
@interface Service {
}

@interface Transactional {
}

@Service
@Transactional
class MyTransactionalService {}

@Service
@Transactional
@AnnotationCollector
@interface TransactionalService {
}

//*.为展开运算符，详细请参考《操作》章节内容
def annotations = MyTransactionalService.annotations*.annotationType()
assert (Service in annotations)
assert (Transactional in annotations)


@Retention(RetentionPolicy.RUNTIME)
public @interface Foo {
    String value()
}
@Retention(RetentionPolicy.RUNTIME)
public @interface Bar {
    String value()
}

//..........
@Foo
@Bar
@AnnotationCollector
public @interface FooBar {}

@Foo('a')
@Bar('b')
class Bob {}

assert Bob.getAnnotation(Foo).value() == 'a'
println Bob.getAnnotation(Bar).value() == 'b'

@FooBar('a')
class Joe {}
assert Joe.getAnnotation(Foo).value() == 'a'
println Joe.getAnnotation(Bar).value() == 'a'