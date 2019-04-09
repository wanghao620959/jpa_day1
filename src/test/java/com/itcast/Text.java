package com.itcast;

import com.itcast.pojo.Customer;
import com.itcast.util.JPAUtil;
import org.junit.Test;

import javax.persistence.*;
import java.util.List;

public class Text {

    @Test
    public void test() {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("myJpa");

        EntityManager em = factory.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();
        Customer c = new Customer();
        c.setCustName("hh");
        em.persist(c);
        tx.commit();
        em.close();
        factory.close();
    }

    @Test
    public void testAdd() {

        Customer customer = new Customer();

        customer.setCustName("传智学院");
        customer.setCustLevel("VIP客户");
        customer.setCustSource("网络");
        customer.setCustIndustry("IT教育");
        customer.setCustAddress("昌平区北七家镇");
        customer.setCustPhone("010-84389340");

        EntityManager em = null;
        EntityTransaction tx = null;

        try {
            // 获取实体管理对象
            em = JPAUtil.getEntityManager();
            // 获取事务对象
            tx = em.getTransaction();
            // 开启事务
            tx.begin();
            // 执行操作

            em.persist(customer);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            // 释放资源
            em.close();
        }


    }


    @Test
    public void testUpdate() {
        EntityManager em = null;
        EntityTransaction tx = null;
        try {

            em = JPAUtil.getEntityManager();
            tx = em.getTransaction();
            tx.begin();
            //执行操作
           // Customer customer = em.find(Customer.class, 1L);
            Customer customer = new Customer();
            customer.setCustName("江传1智学院");
            customer.setCustId(1L);
         // em.clear();//把c1对象从缓存中清除出去
            em.merge(customer);

            tx.commit();

        } catch (Exception e) {

            tx.rollback();

            e.printStackTrace();
        } finally {
            em.close();
        }


    }

    //7.3删除7.3删除
    @Test
    public void testDelete() {

        EntityManager em = null;
        EntityTransaction tx = null;

        try {

            em = JPAUtil.getEntityManager();
            tx = em.getTransaction();
            tx.begin();
            //执行操作
            Customer customer = em.find(Customer.class, 1l);
            customer.setCustName("江苏传智学院");
           // em.clear();//把c1对象从缓存中清除出去

            em.remove(customer);

            tx.commit();


        } catch (Exception e) {

            tx.rollback();

            e.printStackTrace();
        } finally {
            em.close();
        }

    }

   // 使用立即加载的策略


    @Test
    public void testFindOne() {

        EntityManager em = null;
        EntityTransaction tx = null;

        try {

            em = JPAUtil.getEntityManager();
            tx = em.getTransaction();
            tx.begin();
            //执行操作
            Customer customer = em.find(Customer.class, 1l);
            Customer customer1 = em.find(Customer.class, 1l);
            tx.commit();
            System.out.println(customer==customer1); // 输出查询对象

        } catch (Exception e) {

            tx.rollback();

            e.printStackTrace();
        } finally {
            em.close();
        }

    }

// 延迟加载策略的方法：

    @Test
    public void testFindTne() {

        EntityManager em = null;
        EntityTransaction tx = null;

        try {

            em = JPAUtil.getEntityManager();
            tx = em.getTransaction();
            tx.begin();
            //执行操作
            Customer customer = em.getReference(Customer.class, 1l);
            tx.commit();
        } catch (Exception e) {

            tx.rollback();

            e.printStackTrace();
        } finally {
            em.close();
        }

    }


    @Test
    public void testFindAll() {

        EntityManager em = null;
        EntityTransaction tx = null;

        try {

            em = JPAUtil.getEntityManager();
            tx = em.getTransaction();
            tx.begin();
            //执行操作
              // 创建query对象
                  String jpql="from Customer";

            // 查询并得到返回结果
            Query query = em.createQuery(jpql);

            List resultList = query.getResultList();

            for (Object o : resultList) {
                System.out.println(o);
            }

            tx.commit();
        } catch (Exception e) {

            tx.rollback();

            e.printStackTrace();
        } finally {
            em.close();
        }

    }


//7.5.2分页查询

    @Test
    public void testFindPage() {

        EntityManager em = null;
        EntityTransaction tx = null;

        try {

            em = JPAUtil.getEntityManager();
            tx = em.getTransaction();
            tx.begin();
            //执行操作
            // 创建query对象
            String jpql="from Customer";

            // 查询并得到返回结果
            Query query = em.createQuery(jpql);
            //起始索引
            query.setFirstResult(0);
            //每页显示条数
            query.setMaxResults(1);


            List resultList = query.getResultList();

            for (Object o : resultList) {
                System.out.println(o);
            }

            tx.commit();
        } catch (Exception e) {

            tx.rollback();

            e.printStackTrace();
        } finally {
            em.close();
        }

    }
//7.5.3条件查询

    @Test
    public void testFindLike() {

        EntityManager em = null;
        EntityTransaction tx = null;

        try {

            em = JPAUtil.getEntityManager();
            tx = em.getTransaction();
            tx.begin();
            //执行操作
            // 创建query对象
            String jpql="from Customer where custName like ? ";
            // 查询并得到返回结果
            Query query = em.createQuery(jpql);
              query.setParameter(1,"江传%");


            List resultList = query.getResultList();

            for (Object o : resultList) {
                System.out.println(o);
            }

            tx.commit();
        } catch (Exception e) {

            tx.rollback();

            e.printStackTrace();
        } finally {
            em.close();
        }

    }

   //7.5.4排序查询

    @Test
    public void testFindOrder() {

        EntityManager em = null;
        EntityTransaction tx = null;

        try {

            em = JPAUtil.getEntityManager();
            tx = em.getTransaction();
            tx.begin();
            //执行操作
            // 创建query对象
            String jpql="from Customer Order by custId asc ";
            // 查询并得到返回结果
            Query query = em.createQuery(jpql);
            List resultList = query.getResultList();

            for (Object o : resultList) {
                System.out.println(o);
            }

            tx.commit();
        } catch (Exception e) {

            tx.rollback();

            e.printStackTrace();
        } finally {
            em.close();
        }

    }

//7.5.5统计查询
    @Test
    public void testFindOrd() {

        EntityManager em = null;
        EntityTransaction tx = null;

        try {

            em = JPAUtil.getEntityManager();
            tx = em.getTransaction();
            tx.begin();
            //执行操作
            // 创建query对象
            String jpql="select count(custId) from Customer";
            // 查询并得到返回结果
            Query query = em.createQuery(jpql);
            List resultList = query.getResultList();

            for (Object o : resultList) {
                System.out.println(o);
            }

            tx.commit();
        } catch (Exception e) {

            tx.rollback();

            e.printStackTrace();
        } finally {
            em.close();
        }

    }



}