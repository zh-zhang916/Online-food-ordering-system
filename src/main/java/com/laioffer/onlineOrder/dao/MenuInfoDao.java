package com.laioffer.onlineOrder.dao;

import com.laioffer.onlineOrder.entity.MenuItem;
import com.laioffer.onlineOrder.entity.Restaurant;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MenuInfoDao {

    @Autowired
    private SessionFactory sessionFactory;
    // mapping, 连接数据库，定义的entity map 到 数据库里
    // 产生Session，去对数据库进行增删改查。

    public List<Restaurant> getRestaurant() {
        Session session = sessionFactory.openSession();
        try {
            // Select 语句的意思，去哪个表去哪个table query数据。
            return session.createCriteria(Restaurant.class)
                    .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY) // left join导致了重复。
                    .list();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
        return new ArrayList<>();
    }

    public List<MenuItem> getAllMenuItem(int restaurantId) {
        // 都会自动close掉session这个对象，Finally的时候。
        try (Session session = sessionFactory.openSession()) {
            // Select * from restaurant where id = restaurantId.
            Restaurant restaurant = session.get(Restaurant.class, restaurantId); // 一left join多
            if (restaurant != null) {
                return restaurant.getMenuItemList();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ArrayList<>();
    }

    public MenuItem getMenuItem(int menuItemId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(MenuItem.class, menuItemId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
