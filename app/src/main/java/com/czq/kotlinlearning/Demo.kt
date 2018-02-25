package com.czq.kotlinlearning

/**
 * Created by czq on 2018/2/23.
 */
class Demo {

    fun t() {
        val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9,10)

        print(list.any { it > 5 })   //true
        print(list.all { it in 1..10 }) //true
        print(list.none { it < 0 }) //true
        print(list.count { it > 5 }) // 5
        print(list.reduce { total, next -> total + next })//55 累加
        print(list.fold(1) { total, next -> total + next })//56 累加有初始值
        list.forEach(::print)
        list.forEachIndexed { index, it ->
            print("index=$index item=$it")
        }
        println(list.max()) //9

    }
    fun t1(){

        val list= listOf(1,2,3,4,5,6,7,null)

        println(list.filter { it!=3 })
        println(list.filterNot { it==3 })
        println(list.take(4))//取前四个
        println(list.takeLast(4))//取后四个
        println(list.takeLastWhile { it!! >4 })//从后往前取符合条件的
        println(list.drop(4)) //去掉前四个
        println(list.slice(listOf(1,3,5))) //取指定下标

    }

    fun t2(){

        val list1= listOf(1,2,3,4,5,6,7)
        val list2= listOf(7,8,9,10,11,12,null)

        println(list1.map { it+1 })//转换
        println(listOf(list1,list2).flatMap { it->it })//合并
    }

    fun t3(){

        val list1= listOf(1,2,3,4,5,6,7)
        val list2= listOf(7,8,9,10,11,12)
        val list3= listOf("one","two","three","four")
        println(list1.zip(list3))
        println(list1.partition { it>3})
        println(list1.plus(list2))
        println(listOf(Pair(1, 2), Pair(11, 22)
                ,Pair(111, 222),Pair(1111, 2222)
                ,Pair(11111, 22222))
                .unzip())




    }

}