package com.xiangbin.yang.study.generic;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

import static java.lang.System.out;

/**
 * @author xiangbin.yang
 * @since 2017/10/31
 */
public class GenericEcho {
    /**
     *
     * @param o
     * @param <T>
     */
    public static <T extends Iinfo> void echo(T o) {
        out.println(o.getEcho());
    }

    public static void echo(List<? extends Iinfo> list) {
        list.forEach(i -> out.println(i.getEcho()));
    }

    public static void echo2(List<Iinfo> list) {
        list.forEach(i -> out.println(i.getEcho()));
    }

    public static void setEcho(List<? super AClass> list, AClass aClassInstance, BClass bClassInstance) {
        list.add(aClassInstance);
        list.add(bClassInstance);
    }

    public static <T extends AClass>  void setEcho3(List<? super AClass> list, T o) {
        list.add(o);
    }

    public static <T extends AClass> void setEcho4(List<Iinfo> list, T o) {
        list.add(o);
    }

    public static <T extends Iinfo> void setEcho2(List<T> list, T aIntance, T bInstance) {
        list.add(aIntance);
        list.add(bInstance);
    }


    public static void main(String[] args) {
        List<AClass> aClassList = newArrayList(new AClass());
        echo(aClassList);

        List<BClass> bClassList = newArrayList(new BClass());
        echo(bClassList);

        out.println("-----");
        List<Iinfo> iAClassList = newArrayList(new AClass());
        echo2(iAClassList);

        out.println("------");
        List<Iinfo> aClassList1 = newArrayList();
        setEcho(aClassList1, new AClass(), new BClass());
        echo(aClassList1);

        out.println("-----");
        List<AClass> aClassList2 = newArrayList();
        setEcho2(aClassList2, new AClass(), new BClass());
        echo(aClassList2);

        out.println("------");
        List<Iinfo> infoList = newArrayList();
        setEcho3(infoList, new BClass());
        echo(infoList);
    }
}


interface Iinfo {
    String getEcho();
}

class AClass implements Iinfo {

    @Override
    public String getEcho() {
        return "AClass";
    }
}

class BClass extends AClass {
    @Override
    public String getEcho() {
        return "BClass";
    }
}